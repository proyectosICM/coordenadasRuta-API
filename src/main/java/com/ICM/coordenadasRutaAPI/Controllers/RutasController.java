package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Services.PaisesService;
import com.ICM.coordenadasRutaAPI.Services.RutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rutas")
public class RutasController {
    @Autowired
    RutasService rutasService;

    @GetMapping("/xempresa/{empresa}")
    public List<RutasModel> GetxEmpresa (@PathVariable Long empresa){
        return rutasService.GetxEmpresa(empresa);
    }

    //CRUD
    @GetMapping
    public List<RutasModel> GetAll (){
        return rutasService.Get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutasModel> GetById(@PathVariable Long id){
        Optional<RutasModel> rutas = rutasService.GetById(id);
        return new ResponseEntity<>(rutas.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RutasModel> Save(@RequestBody RutasModel paisesModel){
        RutasModel cruta = rutasService.Save(paisesModel);
        return new ResponseEntity<>(cruta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutasModel> Edit(@PathVariable Long id, @RequestBody RutasModel rutasModel){
        RutasModel eruta = rutasService.Edit(id, rutasModel);
        if (eruta!=null){
            return new ResponseEntity<>(eruta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RutasModel> Delete(@PathVariable Long id){
        rutasService.Delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
