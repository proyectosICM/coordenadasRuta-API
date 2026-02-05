package com.ICM.coordenadasRutaAPI.controllers;

import com.ICM.coordenadasRutaAPI.dto.SonidosGeocercaUpsertRequest;
import com.ICM.coordenadasRutaAPI.models.PaisesModel;
import com.ICM.coordenadasRutaAPI.models.SonidosGeocercaModel;
import com.ICM.coordenadasRutaAPI.models.TipoSModel;
import com.ICM.coordenadasRutaAPI.repositories.PaisesRepository;
import com.ICM.coordenadasRutaAPI.services.RsFileStorageService;
import com.ICM.coordenadasRutaAPI.services.SonidosGeocercaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// These controllers handle retrieval of information related to the SonidoGeocercaModel
@RestController
@RequestMapping("api/SonidoGeo")
public class SonidoGeocercaController {
    @Autowired
    public SonidosGeocercaService sonidosGeocercaService;

    @Autowired
    private RsFileStorageService rsFileStorageService;

    @Autowired
    private PaisesRepository paisesRepository;

    // This controller retrieves all data from the SonidoGeocercaModel
    @GetMapping
    public ResponseEntity<List<SonidosGeocercaModel>> GetAll() {
        List<SonidosGeocercaModel> data = sonidosGeocercaService.GetAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SonidosGeocercaModel>> page(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(sonidosGeocercaService.getPage(page, size), HttpStatus.OK);
    }

   // Retrieves data for a given ID input
    @GetMapping("/{id}")
    public ResponseEntity<SonidosGeocercaModel> GetById(@PathVariable Long id){
        Optional<SonidosGeocercaModel> sonidosG = sonidosGeocercaService.GetById(id);

        return sonidosG.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Retrieves data based on a given country id
    @GetMapping("/xpais/{countryId}")
    public ResponseEntity<List<SonidosGeocercaModel>> Getxpais(@PathVariable Long countryId) {
        if (countryId == null || countryId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<SonidosGeocercaModel> data = sonidosGeocercaService.GetxPais(countryId);

        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Retrieves data based on a given type of geosignal
    @GetMapping("/xtipoS/{tipoS}")
    public ResponseEntity<List<SonidosGeocercaModel>> GetxtipìS(@PathVariable Long tipoS) {
        if(tipoS == null || tipoS <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<SonidosGeocercaModel> data = sonidosGeocercaService.GetxTipos(tipoS);

        if (data.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Retrieves data based on a given country and geosignal type
    @GetMapping("/xpaisxtipo/{pais}/{tipo}")
    public ResponseEntity<List<SonidosGeocercaModel>> GetxPais(@PathVariable Long pais, @PathVariable Long tipo) {
        if (pais == null || tipo == null || pais <= 0 || tipo <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<SonidosGeocercaModel> data = sonidosGeocercaService.GetxPaisxTipoS(pais, tipo);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMultipart(
            @RequestPart("data") SonidosGeocercaUpsertRequest data,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            @RequestPart(value = "audio", required = false) MultipartFile audio
    ) {
        try {
            if (data.getNombre() == null || data.getNombre().isBlank()) {
                return ResponseEntity.badRequest().body("nombre es obligatorio");
            }
            if (data.getTipoSId() == null || data.getTipoSId() <= 0) {
                return ResponseEntity.badRequest().body("tipoSId es obligatorio");
            }
            if (data.getPaisId() == null || data.getPaisId() <= 0) {
                return ResponseEntity.badRequest().body("paisId es obligatorio");
            }

            // armar entity
            SonidosGeocercaModel geo = new SonidosGeocercaModel();
            geo.setId(null);
            geo.setNombre(data.getNombre());
            geo.setCodsonido(data.getCodsonido());
            geo.setDetalle(data.getDetalle());

            TipoSModel tipo = new TipoSModel();
            tipo.setId(data.getTipoSId());
            geo.setTipoSModel(tipo);

            PaisesModel paisRef = new PaisesModel();
            paisRef.setId(data.getPaisId());
            geo.setPaisesModel(paisRef);

            // Si no suben archivo, se respeta URL manual (opcional)
            geo.setUrlImagen(data.getUrlImagen());
            geo.setUrlSonido(data.getUrlSonido());

            // subir archivos si vienen
            if (imagen != null && !imagen.isEmpty()) {
                String paisNombre = paisesRepository.findById(data.getPaisId())
                        .map(PaisesModel::getNombre)
                        .orElse("SIN_PAIS");

                String urlImg = rsFileStorageService.storeImage(imagen, paisNombre);
                geo.setUrlImagen(urlImg);
            }

            if (audio != null && !audio.isEmpty()) {
                String urlAud = rsFileStorageService.storeAudio(audio);
                geo.setUrlSonido(urlAud);
            }

            SonidosGeocercaModel created = sonidosGeocercaService.Create(geo);
            return new ResponseEntity<>(created, HttpStatus.CREATED);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error subiendo archivos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creando geocerca: " + e.getMessage());
        }
    }

    // ==========================================================
    // ✅ UPDATE TODO-EN-UNO (multipart: data + imagen + audio)
    // PUT /api/SonidoGeo/{id}
    // ==========================================================
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateMultipart(
            @PathVariable Long id,
            @RequestPart("data") SonidosGeocercaUpsertRequest data,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            @RequestPart(value = "audio", required = false) MultipartFile audio
    ) {
        try {
            SonidosGeocercaModel current = sonidosGeocercaService.GetById(id)
                    .orElseThrow(() -> new RuntimeException("Geocerca no encontrada ID=" + id));

            // actualizar campos
            if (data.getNombre() != null) current.setNombre(data.getNombre());

            if (data.getTipoSId() != null && data.getTipoSId() > 0) {
                TipoSModel tipo = new TipoSModel();
                tipo.setId(data.getTipoSId());
                current.setTipoSModel(tipo);
            }

            Long paisIdFinal = null;
            if (data.getPaisId() != null && data.getPaisId() > 0) {
                PaisesModel paisRef = new PaisesModel();
                paisRef.setId(data.getPaisId());
                current.setPaisesModel(paisRef);
                paisIdFinal = data.getPaisId();
            } else if (current.getPaisesModel() != null) {
                paisIdFinal = current.getPaisesModel().getId();
            }

            if (data.getCodsonido() != null) current.setCodsonido(data.getCodsonido());
            if (data.getDetalle() != null) current.setDetalle(data.getDetalle());

            // si NO suben archivo pero mandan url manual, opcional
            if (data.getUrlImagen() != null) current.setUrlImagen(data.getUrlImagen());
            if (data.getUrlSonido() != null) current.setUrlSonido(data.getUrlSonido());

            // subir archivos si vienen (pisan la URL)
            if (imagen != null && !imagen.isEmpty()) {
                String paisNombre = "SIN_PAIS";
                if (paisIdFinal != null) {
                    paisNombre = paisesRepository.findById(paisIdFinal)
                            .map(PaisesModel::getNombre)
                            .orElse("SIN_PAIS");
                }
                String urlImg = rsFileStorageService.storeImage(imagen, paisNombre);
                current.setUrlImagen(urlImg);
            }

            if (audio != null && !audio.isEmpty()) {
                String urlAud = rsFileStorageService.storeAudio(audio);
                current.setUrlSonido(urlAud);
            }

            SonidosGeocercaModel updated = sonidosGeocercaService.Update(id, current);
            return ResponseEntity.ok(updated);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error subiendo archivos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error editando geocerca: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        try {
            sonidosGeocercaService.Delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error eliminando geocerca: " + e.getMessage());
        }
    }


}
