package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.RolesModel;
import com.ICM.coordenadasRutaAPI.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public List<RolesModel> getAll(){
        return rolesRepository.findAll();
    }

    public Optional<RolesModel> getById(Long id){
        return rolesRepository.findById(id);
    }

    public RolesModel createRol(RolesModel rolesModel){
        return rolesRepository.save(rolesModel);
    }

    public RolesModel editRol(RolesModel rolesModel, Long id){
        Optional<RolesModel> existing = rolesRepository.findById(id);
        if(existing.isPresent()){
            RolesModel rol = existing.get();
            rol.setNombre(rolesModel.getNombre());
            return rolesRepository.save(rol);
        }
        return null;
    }

    public void deleteRol(Long id){
        rolesRepository.deleteById(id);
    }
}
