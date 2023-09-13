package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.TipoSModel;
import com.ICM.coordenadasRutaAPI.Services.TipoSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("api/tipoS")
public class TipoSController {
    @Autowired
    TipoSService tipoSService;
    @GetMapping
    public List<TipoSModel> GetAll (){
        return tipoSService.Get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSModel> GetById(@PathVariable Long id){
        Optional<TipoSModel> tipoS = tipoSService.GetById(id);
        return new ResponseEntity<>(tipoS.get(), HttpStatus.OK);
    }
}
