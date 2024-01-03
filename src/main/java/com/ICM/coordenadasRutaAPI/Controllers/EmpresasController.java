package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Services.EmpresasService;
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
    private EmpresasService empresasService;

    @PostMapping("/login")
    public ResponseEntity<EmpresasModel> authenticateCompany(@RequestBody EmpresasModel empresasModel){
        EmpresasModel login = empresasService.authenticateCompany(empresasModel);
        if (login!=null){
            return new ResponseEntity<>(login, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findNombre/{nombre}")
    public ResponseEntity<String> findCompanyByName(@PathVariable String nombre){
        Optional<EmpresasModel> data = empresasService.findCompanyByName(nombre);
        return data.map(response -> ResponseEntity.ok("Ya está en uso ese nombre"))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/findUsuario/{usuario}")
    public ResponseEntity<String> FindByUsuario(@PathVariable String usuario){
        Optional<EmpresasModel> data = empresasService.findCompanyByUser(usuario);
        return data.map(response -> ResponseEntity.ok("El usuario ya se encuentra en uso"))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    //CRUD

    @GetMapping
    public List<EmpresasModel> getAllCompanies (){
        return empresasService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresasModel> getCompanyById(@PathVariable Long id){
        Optional<EmpresasModel> data = empresasService.getCompanyById(id);
        return data.map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresasModel> saveCompany(@RequestBody EmpresasModel empresasModel){
        EmpresasModel cempresa = empresasService.saveCompany(empresasModel);
        return new ResponseEntity<>(cempresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasModel> editCompany(@PathVariable Long id, @RequestBody EmpresasModel empresasModel){
        EmpresasModel eempresa = empresasService.editCompany(id, empresasModel);
        if (eempresa!=null){
            return new ResponseEntity<>(eempresa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresasModel> deleteCompany(@PathVariable Long id){
        empresasService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
