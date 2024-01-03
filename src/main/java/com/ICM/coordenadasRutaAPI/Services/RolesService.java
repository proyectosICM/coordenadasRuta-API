package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.RolesModel;
import com.ICM.coordenadasRutaAPI.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    /**
     * This service class provides methods for performing CRUD operations on the RolesModel entity.
     */
    @Autowired
    private RolesRepository rolesRepository;

    public List<RolesModel> getAllRoles(){
        return rolesRepository.findAll();
    }

    public Optional<RolesModel> getRoleById(Long id){
        return rolesRepository.findById(id);
    }

    public RolesModel createRole(RolesModel rolesModel){
        return rolesRepository.save(rolesModel);
    }

    public RolesModel updateRole(RolesModel rolesModel, Long id){
        Optional<RolesModel> existing = rolesRepository.findById(id);
        if(existing.isPresent()){
            RolesModel rol = existing.get();
            rol.setNombre(rolesModel.getNombre());
            return rolesRepository.save(rol);
        }
        return null;
    }

    public void deleteRole(Long id){
        rolesRepository.deleteById(id);
    }
}
