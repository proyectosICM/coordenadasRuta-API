package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Services.PaisesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/paises")
public class PaisesController {
    @Autowired
    PaisesService paisesService;

    @GetMapping
    public List<PaisesModel> GetAll (){
        return paisesService.Get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisesModel> GetById(@PathVariable Long id){
        Optional<PaisesModel> paises = paisesService.GetById(id);
        return new ResponseEntity<>(paises.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaisesModel> Save(@RequestBody PaisesModel paisesModel){
        PaisesModel cpais = paisesService.Save(paisesModel);
        return new ResponseEntity<>(cpais, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisesModel> Edit(@PathVariable Long id, @RequestBody PaisesModel paisesModel){
        PaisesModel epais = paisesService.Edit(id, paisesModel);
        if (epais!=null){
            return new ResponseEntity<>(epais, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaisesModel> Delete(@PathVariable Long id){
        paisesService.Delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
