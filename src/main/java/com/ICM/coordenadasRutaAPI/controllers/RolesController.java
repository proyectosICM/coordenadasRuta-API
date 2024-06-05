package com.ICM.coordenadasRutaAPI.controllers;

import com.ICM.coordenadasRutaAPI.models.RolesModel;
import com.ICM.coordenadasRutaAPI.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;


    @GetMapping
    public List<RolesModel> getAllRoles() {return rolesService.getAllRoles();}

    @GetMapping("/{id}")
    public ResponseEntity<RolesModel> getRoleById(@PathVariable Long id) {
        Optional<RolesModel> rol =rolesService.getRoleById(id);
        if(rol.isPresent()){
            return new ResponseEntity<>(rol.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RolesModel> createRole(RolesModel rolesModel){
        RolesModel crol = rolesService.createRole(rolesModel);
        return new ResponseEntity<>(crol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesModel> updateRole(@RequestBody RolesModel rolesModel, @PathVariable Long id){
        RolesModel erol = rolesService.updateRole(rolesModel, id);
        if (erol!=null){
            return new ResponseEntity<>(erol, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RolesModel> deleteRole(@PathVariable Long id){
        rolesService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
