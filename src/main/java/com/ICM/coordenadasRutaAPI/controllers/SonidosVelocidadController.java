package com.ICM.coordenadasRutaAPI.controllers;

import com.ICM.coordenadasRutaAPI.models.SonidosVelocidadModel;
import com.ICM.coordenadasRutaAPI.services.SonidosVelocidadService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // This controller retrieves all data from the SonidosVelocidadModel
    @GetMapping
    public ResponseEntity<List<SonidosVelocidadModel>> getAllSoundSpeeds (){

        List<SonidosVelocidadModel> data =  sonidosVelocidadService.getAllSoundSpeeds();

        if(data.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(data);
    }

    // Retrieves data for a given ID input
    @GetMapping("/{id}")
    public ResponseEntity<SonidosVelocidadModel> getSoundSpeedById(@PathVariable Long id){
        Optional<SonidosVelocidadModel> data = sonidosVelocidadService.getSoundSpeedById(id);

        return data.map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
