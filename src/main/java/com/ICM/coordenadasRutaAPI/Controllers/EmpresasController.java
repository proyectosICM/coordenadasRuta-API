package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Services.EmpresasService;
import com.ICM.coordenadasRutaAPI.Services.PaisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/empresas")
public class EmpresasController {
    @Autowired
    EmpresasService empresasService;

    @PostMapping("/login")
    public ResponseEntity<EmpresasModel> Login(@RequestBody EmpresasModel empresasModel){
        EmpresasModel login = empresasService.autenticar(empresasModel);
        if (login!=null){
            return new ResponseEntity<>(login, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findNombre/{nombre}")
    public ResponseEntity<String> FindByNombre(@PathVariable String nombre){
        Optional<EmpresasModel> data = empresasService.FindByNombre(nombre);
        return data.map(response -> ResponseEntity.ok("Ya está en uso ese nombre"))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/findUsuario/{usuario}")
    public ResponseEntity<String> FindByUsuario(@PathVariable String usuario){
        Optional<EmpresasModel> data = empresasService.FindByUsuario(usuario);
        return data.map(response -> ResponseEntity.ok("El usuario ya se encuentra en uso"))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    //CRUD

    @GetMapping
    public List<EmpresasModel> GetAll (){
        return empresasService.Get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresasModel> GetById(@PathVariable Long id){
        Optional<EmpresasModel> data = empresasService.GetById(id);
        return data.map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresasModel> Save(@RequestBody EmpresasModel empresasModel){
        EmpresasModel cempresa = empresasService.Save(empresasModel);
        return new ResponseEntity<>(cempresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasModel> Edit(@PathVariable Long id, @RequestBody EmpresasModel empresasModel){
        EmpresasModel eempresa = empresasService.Edit(id, empresasModel);
        if (eempresa!=null){
            return new ResponseEntity<>(eempresa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresasModel> Delete(@PathVariable Long id){
        empresasService.Delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
