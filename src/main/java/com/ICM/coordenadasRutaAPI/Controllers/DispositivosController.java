package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Services.DispositivosService;
import com.ICM.coordenadasRutaAPI.Services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/dispositivos")
public class DispositivosController {
    @Autowired
    DispositivosService dispositivosService;

    @GetMapping
    public List<DispositivosModel> GetAll (){
        return dispositivosService.Get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivosModel> GetById(@PathVariable Long id){
        Optional<DispositivosModel> dispositivos = dispositivosService.GetById(id);
        return new ResponseEntity<>(dispositivos.get(), HttpStatus.OK);
    }

    @GetMapping("/verificar/{nombre}/{empresa}")
    public ResponseEntity<DispositivosModel> getDispositivoByNombreAndEmpresa(
            @PathVariable String nombre,
            @PathVariable Long empresa) {

        Optional<DispositivosModel> dispositivo = dispositivosService.findByNombreAndEmpresasModelId(nombre, empresa);

        if (dispositivo.isPresent()) {
            return new ResponseEntity<>(dispositivo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<DispositivosModel> Save(@RequestBody DispositivosModel dispositivosModel){
        DispositivosModel cdispositivos = dispositivosService.Save(dispositivosModel);
        return new ResponseEntity<>(cdispositivos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivosModel> Edit(@PathVariable Long id, @RequestBody DispositivosModel dispositivosModel){
        DispositivosModel edispositivos = dispositivosService.Edit(id, dispositivosModel);
        if (edispositivos!=null){
            return new ResponseEntity<>(edispositivos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DispositivosModel> Delete(@PathVariable Long id){
        dispositivosService.Delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
