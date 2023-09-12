package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.SonidosGeocercaModel;
import com.ICM.coordenadasRutaAPI.Repositories.SonidosGeocercaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SonidosGeocercaService {
    @Autowired
    public SonidosGeocercaRepository sonidosGeocercaRepository;

    public List<SonidosGeocercaModel> GetAll() {
        return sonidosGeocercaRepository.findAll();
    }

    public Optional<SonidosGeocercaModel> GetById(Long id) {
        return sonidosGeocercaRepository.findById(id);
    }
}
