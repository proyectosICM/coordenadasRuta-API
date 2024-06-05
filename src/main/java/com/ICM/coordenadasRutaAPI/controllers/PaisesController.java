package com.ICM.coordenadasRutaAPI.controllers;

import com.ICM.coordenadasRutaAPI.models.PaisesModel;
import com.ICM.coordenadasRutaAPI.services.PaisesService;
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
    public List<PaisesModel> getAllCountries (){
        return paisesService.getAllCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisesModel> getCountryById(@PathVariable Long id){
        Optional<PaisesModel> paises = paisesService.getCountryById(id);
        return new ResponseEntity<>(paises.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaisesModel> saveCountry(@RequestBody PaisesModel paisesModel){
        PaisesModel cpais = paisesService.saveCountry(paisesModel);
        return new ResponseEntity<>(cpais, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisesModel> updateCountry(@PathVariable Long id, @RequestBody PaisesModel paisesModel){
        PaisesModel epais = paisesService.updateCountry(id, paisesModel);
        if (epais!=null){
            return new ResponseEntity<>(epais, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaisesModel> deleteCountry(@PathVariable Long id){
        paisesService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
