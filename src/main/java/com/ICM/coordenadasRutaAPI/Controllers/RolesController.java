package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.RolesModel;
import com.ICM.coordenadasRutaAPI.Services.RolesService;
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
    public List<RolesModel> getAll() {return rolesService.getAll();}

    @GetMapping("/{id}")
    public ResponseEntity<RolesModel> getById(@PathVariable Long id) {
        Optional<RolesModel> rol =rolesService.getById(id);
        if(rol.isPresent()){
            return new ResponseEntity<>(rol.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RolesModel> crearRol(RolesModel rolesModel){
        RolesModel crol = rolesService.createRol(rolesModel);
        return new ResponseEntity<>(crol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesModel> editarRol(@RequestBody RolesModel rolesModel, @PathVariable Long id){
        RolesModel erol = rolesService.editRol(rolesModel, id);
        if (erol!=null){
            return new ResponseEntity<>(erol, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RolesModel> eliminarRol(@PathVariable Long id){
        rolesService.deleteRol(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
