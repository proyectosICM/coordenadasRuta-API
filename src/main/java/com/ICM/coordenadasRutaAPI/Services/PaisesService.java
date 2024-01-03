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
    private PaisesRepository paisesRepository;

    /**
     * This service class provides methods for performing CRUD operations on the PaisesModel entity.
     */

    public List<PaisesModel> getAllCountries(){
        return paisesRepository.findAll();
    }

    public Optional<PaisesModel> getCountryById(Long id){
        return paisesRepository.findById(id);
    }

    public PaisesModel saveCountry(PaisesModel paisesModel) {
        return paisesRepository.save(paisesModel);
    }

    public PaisesModel updateCountry(Long id, PaisesModel paisesModel){
        Optional<PaisesModel> existing = paisesRepository.findById(id);
        if(existing.isPresent()){
            PaisesModel pais = existing.get();
            pais.setNombre(paisesModel.getNombre());
            return paisesRepository.save(pais);
        }
        return null;
    }

    public void deleteCountry(Long id){
        paisesRepository.deleteById(id);
    }
}
