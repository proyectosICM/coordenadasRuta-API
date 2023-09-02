package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Models.SonidosVelocidadModel;
import com.ICM.coordenadasRutaAPI.Services.SonidosVelocidadService;
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
@RequestMapping("api/sonidosVelocidad")
public class SonidosVelocidadController {
    @Autowired
    SonidosVelocidadService sonidosVelocidadService;
    @GetMapping
    public List<SonidosVelocidadModel> GetAll (){
        return sonidosVelocidadService.Get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SonidosVelocidadModel> GetById(@PathVariable Long id){
        Optional<SonidosVelocidadModel> svelocidad = sonidosVelocidadService.GetById(id);
        return new ResponseEntity<>(svelocidad.get(), HttpStatus.OK);
    }
}
