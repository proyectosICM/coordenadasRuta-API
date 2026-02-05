package com.ICM.coordenadasRutaAPI.controllers;

import com.ICM.coordenadasRutaAPI.models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.services.DispositivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/page")
    public Page<DispositivosModel> getAllDispositivosPage(
            @PageableDefault(size = 10) Pageable pageable
    ){
        return dispositivosService.getAllDispositivos(pageable);
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

    @GetMapping("/empresax/page")
    public Page<DispositivosModel> findByEmpresaAndEstadoPageable(
            @RequestParam(name = "empresaId") Long empresaId,
            @RequestParam(name = "estado") Boolean estado,
            @PageableDefault(size = 10) Pageable pageable
    ){
        return dispositivosService.findByEmpresaIdAndEstado(empresaId, estado, pageable);
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
    public String getProps(@PathVariable Long id) {
        String propsString = dispositivosService.props(id);

        if (propsString != null) {
            return propsString;
        } else {
            // Manejar el caso cuando no se encuentra el dispositivo
            return "Dispositivo no encontrado";
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
