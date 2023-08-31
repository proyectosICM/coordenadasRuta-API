package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Repositories.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisesService {
    @Autowired
    PaisesRepository paisesRepository;

    public List<PaisesModel> Get(){
        return paisesRepository.findAll();
    }

    public Optional<PaisesModel> GetById(Long id){
        return paisesRepository.findById(id);
    }

    public PaisesModel Save(PaisesModel paisesModel) {
        return paisesRepository.save(paisesModel);
    }

    public PaisesModel Edit(Long id, PaisesModel paisesModel){
        Optional<PaisesModel> existing = paisesRepository.findById(id);
        if(existing.isPresent()){
            PaisesModel pais = existing.get();
            pais.setNombre(paisesModel.getNombre());
            return paisesRepository.save(pais);
        }
        return null;
    }

    public void Delete(Long id){
        paisesRepository.deleteById(id);
    }
}
