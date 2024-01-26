package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Services.DispositivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/empresax")
    public ResponseEntity<Page<DispositivosModel>> findByEmpresaAndEstado(
            @RequestParam(name = "empresaId") Long empresaId,
            @RequestParam(name = "estado") Boolean estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<DispositivosModel> dispositivos = dispositivosService.findByEmpresaIdAndEstado(empresaId, estado, page, size);
        return new ResponseEntity<>(dispositivos, HttpStatus.OK);
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

    @PutMapping("/prop/{id}")
    public ResponseEntity<DispositivosModel> actualizarDispositivoprop  (@PathVariable Long id, @RequestBody DispositivosModel dispositivosModel){
        DispositivosModel edispositivos = dispositivosService.actualizarDispositivoprop(id, dispositivosModel);
        if (edispositivos!=null){
            return new ResponseEntity<>(edispositivos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/props/{id}")
    public ResponseEntity<String> getProps(@PathVariable Long id) {
        String propsString = dispositivosService.props(id);

        if (propsString != null) {
            return ResponseEntity.ok(propsString);
        } else {
            return ResponseEntity.notFound().build();
        }
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
