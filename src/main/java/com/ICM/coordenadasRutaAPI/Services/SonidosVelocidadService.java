package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Models.SonidosVelocidadModel;
import com.ICM.coordenadasRutaAPI.Repositories.SonidosVelocidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SonidosVelocidadService {
    @Autowired
    SonidosVelocidadRepository sonidosVelocidadRepository;

    public List<SonidosVelocidadModel> Get(){
        return sonidosVelocidadRepository.findAll();
    }

    public Optional<SonidosVelocidadModel> GetById(Long id){
        return sonidosVelocidadRepository.findById(id);
    }
}
