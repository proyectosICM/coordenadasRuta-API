package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.Services.DispositivosService;
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
    public List<DispositivosModel> getAllDispositivos (){
        return dispositivosService.getAllDispositivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivosModel> getDispositivoById(@PathVariable Long id){
        Optional<DispositivosModel> dispositivos = dispositivosService.getDispositivoById(id);
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
    public ResponseEntity<DispositivosModel> saveDispositivo(@RequestBody DispositivosModel dispositivosModel){
        DispositivosModel cdispositivos = dispositivosService.saveDispositivo(dispositivosModel);
        return new ResponseEntity<>(cdispositivos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivosModel> actualizarDispositivo  (@PathVariable Long id, @RequestBody DispositivosModel dispositivosModel){
        DispositivosModel edispositivos = dispositivosService.actualizarDispositivo(id, dispositivosModel);
        if (edispositivos!=null){
            return new ResponseEntity<>(edispositivos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reasignar/{id}")
    public ResponseEntity<DispositivosModel> reasignarRuta(@PathVariable Long id, @RequestBody DispositivosModel dispositivosModel){
        DispositivosModel edispositivos = dispositivosService.reasignarRuta(id, dispositivosModel);
        if (edispositivos!=null){
            return new ResponseEntity<>(edispositivos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DispositivosModel> deleteDispositivo(@PathVariable Long id){
        dispositivosService.deleteDispositivoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
