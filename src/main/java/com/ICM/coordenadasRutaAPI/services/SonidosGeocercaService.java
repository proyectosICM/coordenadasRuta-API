package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.*;
import com.ICM.coordenadasRutaAPI.repositories.SonidosGeocercaRepository;
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

    public List<SonidosGeocercaModel> GetxPais(Long pais){
        PaisesModel paisModel = new PaisesModel();
        paisModel.setId(pais);
        return sonidosGeocercaRepository.findByPaisesModel(paisModel);
    }

    public List<SonidosGeocercaModel> GetxTipos(Long tipo){
        TipoSModel tipoSModel = new TipoSModel();
        tipoSModel.setId(tipo);
        return sonidosGeocercaRepository.findByTipoSModel(tipoSModel);
    }

    public List<SonidosGeocercaModel> GetxPaisxTipoS(Long pais, Long tipoS){
        PaisesModel paisModel = new PaisesModel();
        paisModel.setId(pais);

        TipoSModel tipoSModel = new TipoSModel();
        tipoSModel.setId(tipoS);
        return sonidosGeocercaRepository.findByPaisesModelAndTipoSModel(paisModel, tipoSModel);
    }

}
