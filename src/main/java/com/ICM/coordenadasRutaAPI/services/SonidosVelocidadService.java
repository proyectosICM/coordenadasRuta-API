package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.SonidosVelocidadModel;
import com.ICM.coordenadasRutaAPI.repositories.SonidosVelocidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SonidosVelocidadService {
    @Autowired
    private SonidosVelocidadRepository sonidosVelocidadRepository;

    public List<SonidosVelocidadModel> getAllSoundSpeeds(){
        return sonidosVelocidadRepository.findAll();
    }

    public Optional<SonidosVelocidadModel> getSoundSpeedById(Long id){
        return sonidosVelocidadRepository.findById(id);
    }
}
