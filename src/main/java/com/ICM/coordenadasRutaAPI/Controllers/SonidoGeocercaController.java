package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.SonidosGeocercaModel;
import com.ICM.coordenadasRutaAPI.Services.SonidosGeocercaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/SonidoGeo")
public class SonidoGeocercaController {
    @Autowired
    public SonidosGeocercaService sonidosGeocercaService;

    @GetMapping
    public List<SonidosGeocercaModel> GetAll(){
        return sonidosGeocercaService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SonidosGeocercaModel> GetById(@PathVariable Long id){
        Optional<SonidosGeocercaModel> sonidosG = sonidosGeocercaService.GetById(id);
        return new ResponseEntity<>(sonidosG.get(), HttpStatus.OK);
    }
}
