package com.ICM.coordenadasRutaAPI.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.Set;

@Service
public class RsFileStorageService {

    private final Path imagesBaseDir;
    private final Path audiosBaseDir;

    // ✅ ahora el público apunta a tu API
    @Value("${rs.storage.publicBaseUrl:http://telemetriaperu.com:8087/api/files}")
    private String publicBaseUrl;

    // ✅ para cumplir “.jpg sí o sí” conviene soportar jpeg/png y guardar como jpg
    private static final Set<String> IMG_TYPES = Set.of("image/jpeg", "image/png");
    // ✅ para cumplir “.mp3 sí o sí”
    private static final Set<String> AUDIO_TYPES = Set.of("audio/mpeg"); // mp3

    public RsFileStorageService(
            @Value("${rs.storage.imagesDir:/var/www/rutasegura/files-storage/imagenes-RS}") String imagesDir,
            @Value("${rs.storage.audioDir:/var/www/rutasegura/files-storage/audios-RS}") String audioDir
    ) {
        this.imagesBaseDir = Paths.get(imagesDir);
        this.audiosBaseDir = Paths.get(audioDir);
    }

    // =========================
    // URL builders (DB)
    // =========================
    public String buildImageUrl(String geoNombre) {
        String file = safeFile(geoNombre) + ".jpg";
        return publicBaseUrl + "/images/" + file;
    }

    public String buildAudioUrl(Integer codsonido) {
        if (codsonido == null) return null;
        String file = pad5(codsonido) + ".mp3";
        return publicBaseUrl + "/audios/" + file;
    }

    // =========================
    // Store (overwrite)
    // =========================
    public String storeImageAsJpg(MultipartFile file, String paisNombre, String geoNombre) throws IOException {
        if (file == null || file.isEmpty()) throw new IOException("Archivo vacío");

        String ct = file.getContentType();
        if (ct == null || !IMG_TYPES.contains(ct)) {
            throw new IOException("Tipo de imagen no permitido (solo JPG/PNG): " + ct);
        }

        String paisFolder = safeFolder(paisNombre);
        Path targetDir = imagesBaseDir.resolve(paisFolder);
        Files.createDirectories(targetDir);

        String filename = safeFile(geoNombre) + ".jpg";
        Path target = targetDir.resolve(filename).normalize();
        if (!target.startsWith(targetDir)) throw new IOException("Ruta inválida");

        // ✅ guardamos SIEMPRE como jpg (si viene png lo convertimos)
        if ("image/jpeg".equals(ct)) {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } else {
            // png -> jpg
            BufferedImage input = ImageIO.read(file.getInputStream());
            if (input == null) throw new IOException("No se pudo leer la imagen");

            BufferedImage rgb = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = rgb.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, rgb.getWidth(), rgb.getHeight());
            g.drawImage(input, 0, 0, null);
            g.dispose();

            // write atomically: temp + move
            Path tmp = targetDir.resolve(filename + ".tmp").normalize();
            ImageIO.write(rgb, "jpg", tmp.toFile());
            Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        }

        // ✅ OJO: el URL NO incluye país (como pediste)
        return publicBaseUrl + "/images/" + filename;
    }

    public String storeAudioAsMp3(MultipartFile file, Integer codsonido) throws IOException {
        if (file == null || file.isEmpty()) throw new IOException("Archivo vacío");
        if (codsonido == null) throw new IOException("codsonido es obligatorio para audio");

        String ct = file.getContentType();
        if (ct == null || !AUDIO_TYPES.contains(ct)) {
            throw new IOException("Tipo de audio no permitido (solo MP3): " + ct);
        }

        Files.createDirectories(audiosBaseDir);

        String filename = pad5(codsonido) + ".mp3";
        Path target = audiosBaseDir.resolve(filename).normalize();
        if (!target.startsWith(audiosBaseDir)) throw new IOException("Ruta inválida");

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return publicBaseUrl + "/audios/" + filename;
    }

    // =========================
    // Rename/move (para coherencia si cambian nombre/pais/codsonido SIN re-subir)
    // =========================
    public void moveImageIfExists(String oldPaisNombre, String oldGeoNombre, String newPaisNombre, String newGeoNombre) throws IOException {
        Path oldDir = imagesBaseDir.resolve(safeFolder(oldPaisNombre));
        Path newDir = imagesBaseDir.resolve(safeFolder(newPaisNombre));
        Files.createDirectories(newDir);

        Path oldFile = oldDir.resolve(safeFile(oldGeoNombre) + ".jpg").normalize();
        Path newFile = newDir.resolve(safeFile(newGeoNombre) + ".jpg").normalize();

        if (!oldFile.startsWith(oldDir) || !newFile.startsWith(newDir)) throw new IOException("Ruta inválida");

        if (Files.exists(oldFile)) {
            Files.move(oldFile, newFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void moveAudioIfExists(Integer oldCod, Integer newCod) throws IOException {
        if (oldCod == null || newCod == null) return;

        Path oldFile = audiosBaseDir.resolve(pad5(oldCod) + ".mp3").normalize();
        Path newFile = audiosBaseDir.resolve(pad5(newCod) + ".mp3").normalize();
        if (!oldFile.startsWith(audiosBaseDir) || !newFile.startsWith(audiosBaseDir)) throw new IOException("Ruta inválida");

        if (Files.exists(oldFile)) {
            Files.move(oldFile, newFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // =========================
    // Helpers
    // =========================
    private String safeFolder(String input) {
        if (input == null || input.isBlank()) return "SIN_PAIS";
        String s = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        s = s.trim().replaceAll("[^a-zA-Z0-9-_]+", "_");
        return s.isBlank() ? "SIN_PAIS" : s;
    }

    private String safeFile(String input) {
        if (input == null || input.isBlank()) return "SIN_NOMBRE";
        String s = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        s = s.trim().replaceAll("[^a-zA-Z0-9-_]+", "_");
        return s.isBlank() ? "SIN_NOMBRE" : s;
    }

    private String pad5(int n) {
        if (n < 0) n = 0;
        return String.format("%05d", n);
    }
}
