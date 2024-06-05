package com.ICM.coordenadasRutaAPI.controllers;

import com.ICM.coordenadasRutaAPI.models.SonidosGeocercaModel;
import com.ICM.coordenadasRutaAPI.services.SonidosGeocercaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// These controllers handle retrieval of information related to the SonidoGeocercaModel
@RestController
@RequestMapping("api/SonidoGeo")
public class SonidoGeocercaController {
    @Autowired
    public SonidosGeocercaService sonidosGeocercaService;

    // This controller retrieves all data from the SonidoGeocercaModel
    @GetMapping
    public ResponseEntity<List<SonidosGeocercaModel>> GetAll() {
        List<SonidosGeocercaModel> data = sonidosGeocercaService.GetAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
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
}
