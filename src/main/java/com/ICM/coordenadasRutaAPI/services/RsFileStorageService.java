package com.ICM.coordenadasRutaAPI.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.Set;
import java.util.UUID;

@Service
public class RsFileStorageService {

    private final Path imagesBaseDir;
    private final Path audiosBaseDir;

    // 👉 Este es el “prefijo público” con el que después se verá en el front.
    // Ej: si NGINX expone /rutasegura/files-storage/ -> /var/www/rutasegura/files-storage/
    @Value("${rs.storage.publicBaseUrl:http://telemetriaperu.com/rutasegura/files-storage}")
    private String publicBaseUrl;

    private static final Set<String> IMG_TYPES = Set.of("image/png", "image/jpeg", "image/webp");
    private static final Set<String> AUDIO_TYPES = Set.of("audio/mpeg", "audio/wav", "audio/ogg", "audio/mp4");

    public RsFileStorageService(
            @Value("${rs.storage.imagesDir:/var/www/rutasegura/files-storage/imagenes-RS}") String imagesDir,
            @Value("${rs.storage.audioDir:/var/www/rutasegura/files-storage/audios-RS}") String audioDir
    ) {
        this.imagesBaseDir = Paths.get(imagesDir);
        this.audiosBaseDir = Paths.get(audioDir);
    }

    public String storeImage(MultipartFile file, String paisNombre) throws IOException {
        if (file == null || file.isEmpty()) throw new IOException("Archivo vacío");
        if (file.getContentType() == null || !IMG_TYPES.contains(file.getContentType())) {
            throw new IOException("Tipo de imagen no permitido: " + file.getContentType());
        }

        String paisFolder = safeFolder(paisNombre);
        Path targetDir = imagesBaseDir.resolve(paisFolder);
        Files.createDirectories(targetDir);

        String ext = extensionFromOriginal(file.getOriginalFilename(), "jpg");
        String filename = UUID.randomUUID() + "." + ext;

        Path target = targetDir.resolve(filename).normalize();
        // seguridad contra path traversal
        if (!target.startsWith(targetDir)) throw new IOException("Ruta inválida");

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return publicBaseUrl + "/imagenes-RS/" + paisFolder + "/" + filename;
    }

    public String storeAudio(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) throw new IOException("Archivo vacío");
        if (file.getContentType() == null || !AUDIO_TYPES.contains(file.getContentType())) {
            throw new IOException("Tipo de audio no permitido: " + file.getContentType());
        }

        Files.createDirectories(audiosBaseDir);

        String ext = extensionFromOriginal(file.getOriginalFilename(), "mp3");
        String filename = UUID.randomUUID() + "." + ext;

        Path target = audiosBaseDir.resolve(filename).normalize();
        if (!target.startsWith(audiosBaseDir)) throw new IOException("Ruta inválida");

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return publicBaseUrl + "/audios-RS/" + filename;
    }

    private String safeFolder(String input) {
        if (input == null || input.isBlank()) return "SIN_PAIS";
        String s = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        s = s.trim().replaceAll("[^a-zA-Z0-9-_]+", "_");
        return s.isBlank() ? "SIN_PAIS" : s;
    }

    private String extensionFromOriginal(String original, String fallback) {
        if (original == null) return fallback;
        int idx = original.lastIndexOf('.');
        if (idx < 0 || idx == original.length() - 1) return fallback;
        String ext = original.substring(idx + 1).toLowerCase();
        if (ext.length() > 6) return fallback;
        return ext.replaceAll("[^a-z0-9]", "");
    }
}
