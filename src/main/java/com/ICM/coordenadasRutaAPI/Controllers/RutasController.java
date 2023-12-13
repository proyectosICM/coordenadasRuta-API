package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.RutasModel;
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

    @GetMapping("/xempresa/{estado}/{empresa}")
    public ResponseEntity<List<RutasModel>> GetxEmpresa (@PathVariable Long empresa, @PathVariable Boolean estado){
        List<RutasModel> data = rutasService.GetxEmpresa(empresa, estado);
        if(data.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    // This controller retrieves all data from the RutasModel
    @GetMapping
    public ResponseEntity<List<RutasModel>> GetAll (){

        List<RutasModel> data =  rutasService.Get();

        if (data.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(data);
    }

    // Retrieves data for a given ID input
    @GetMapping("/{id}")
    public ResponseEntity<RutasModel> GetById(@PathVariable Long id){
        Optional<RutasModel> data  = rutasService.GetById(id);

        return data.map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // This controller creates a new RutasModel data
    @PostMapping
    public ResponseEntity<RutasModel> Save(@RequestBody RutasModel paisesModel){
        RutasModel cruta = rutasService.Save(paisesModel);
        return new ResponseEntity<>(cruta, HttpStatus.CREATED);
    }

    // This controller edits a specific data of RutasModel
    @PutMapping("/{id}")
    public ResponseEntity<RutasModel> Edit(@PathVariable Long id, @RequestBody RutasModel rutasModel){
        RutasModel eruta = rutasService.Edit(id, rutasModel);
        if (eruta!=null){
            return new ResponseEntity<>(eruta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<RutasModel> Deshabilitar(@PathVariable Long id){
        RutasModel eruta = rutasService.Deshabilitar(id);
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
