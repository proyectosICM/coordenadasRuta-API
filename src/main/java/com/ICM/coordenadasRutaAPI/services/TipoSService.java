package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.TipoSModel;
import com.ICM.coordenadasRutaAPI.repositories.TipoSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSService {
    @Autowired
    private TipoSRepository tipoSRepository;

    public List<TipoSModel> getAllTypesSignals(){
        return tipoSRepository.findAll();
    }

    public Optional<TipoSModel> getTypeSignalsById(Long id){
        return tipoSRepository.findById(id);
    }
}
