package com.ICM.coordenadasRutaAPI.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/files")
public class RsFilesController {

    private final Path imagesBaseDir;
    private final Path audiosBaseDir;

    public RsFilesController(
            @Value("${rs.storage.imagesDir:/var/www/rutasegura/files-storage/imagenes-RS}") String imagesDir,
            @Value("${rs.storage.audioDir:/var/www/rutasegura/files-storage/audios-RS}") String audioDir
    ) {
        this.imagesBaseDir = Paths.get(imagesDir);
        this.audiosBaseDir = Paths.get(audioDir);
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        if (isBadName(filename)) return ResponseEntity.badRequest().build();

        Path found = findInCountryFolders(imagesBaseDir, filename);
        if (found == null || !Files.exists(found)) return ResponseEntity.notFound().build();

        Resource res = new UrlResource(found.toUri());
        String ct = Files.probeContentType(found);
        if (ct == null) ct = "image/jpeg";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(ct))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(res);
    }

    @GetMapping("/audios/{filename:.+}")
    public ResponseEntity<Resource> getAudio(@PathVariable String filename) throws IOException {
        if (isBadName(filename)) return ResponseEntity.badRequest().build();

        Path file = audiosBaseDir.resolve(filename).normalize();
        if (!file.startsWith(audiosBaseDir) || !Files.exists(file)) return ResponseEntity.notFound().build();

        Resource res = new UrlResource(file.toUri());
        String ct = Files.probeContentType(file);
        if (ct == null) ct = "audio/mpeg";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(ct))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(res);
    }

    private boolean isBadName(String filename) {
        return filename.contains("..") || filename.contains("/") || filename.contains("\\");
    }

    // Busca SR-01.jpg dentro de /imagenes-RS/*/SR-01.jpg
    private Path findInCountryFolders(Path base, String filename) throws IOException {
        if (!Files.exists(base)) return null;

        try (Stream<Path> paths = Files.list(base)) {
            return paths
                    .filter(Files::isDirectory)
                    .map(dir -> dir.resolve(filename).normalize())
                    .filter(p -> p.startsWith(base))
                    .filter(Files::exists)
                    .findFirst()
                    .orElse(null);
        }
    }
}
