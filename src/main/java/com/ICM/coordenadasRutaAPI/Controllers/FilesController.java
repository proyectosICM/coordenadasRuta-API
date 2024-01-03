package com.ICM.coordenadasRutaAPI.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * FilesController handles HTTP requests to serve different types of files.
 *
 * This controller is responsible for delivering image, audio, and binary files.
 * It uses injected property values to determine the directories of the files.
 *
 * Routes:
 * - GET /images/{filename}: Serves images from the configured directory. Be sure to adjust the content type according to the image type.
 * - GET /audios/{filename}: Serves audio files. The content type is adjusted based on the audio file format.
 * - GET /desbin/{filename}: Serves binary files with a 'attachment' content disposition, indicating they should be downloaded.
 *
 * The routes for serving files ensure that the requested files are within the allowed directories to prevent the exposure of unauthorized files.
 *
 * @author Eduardo Aguilar
 * @version 1.0
 * @since 3-01-2024
 */
@RestController
@RequestMapping("api/files")
public class FilesController {
    @Value("${file.image}")
    private String pathimg;

    @Value("${file.audio}")
    private String pathadc;

    @Value("${file.bin}")
    private String bindir;

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            Path file = Paths.get(pathimg).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG) // Asegúrate de cambiar esto según el tipo de imagen
                        .body(resource);
            } else {
                throw new RuntimeException("No se pudo leer el archivo!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/audios/{filename:.+}")
    public ResponseEntity<Resource> serveAudio(@PathVariable String filename) {
        try {
            Path file = Paths.get(pathadc).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                // Ajusta el MediaType según el tipo de archivo de audio, por ejemplo, audio/mpeg para MP3
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("audio/mpeg"))
                        .body(resource);
            } else {
                throw new RuntimeException("No se pudo leer el archivo de audio!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/bin/{binaryFilename}")
    public ResponseEntity<Resource> downloadBinaryFile(@PathVariable String binaryFilename) {
        // Definir y normalizar el path del directorio base.
        Path bindirPath = Paths.get(bindir).toAbsolutePath().normalize();

        // Construir y normalizar el path del archivo solicitado.
        Path filePath = bindirPath.resolve(binaryFilename).normalize();

        // Verificar que el path del archivo esté dentro del directorio permitido.
        if (!filePath.startsWith(bindirPath)) {
            // Si no lo está, devuelve un error de solicitud incorrecta.
            return ResponseEntity.badRequest().build();
        }

        try {
            Resource file = new UrlResource(filePath.toUri());
            if (file.exists() || file.isReadable()) {
                String filename = filePath.getFileName().toString();
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM) // Cambiado a tipo genérico de archivo binario
                        .body(file);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
