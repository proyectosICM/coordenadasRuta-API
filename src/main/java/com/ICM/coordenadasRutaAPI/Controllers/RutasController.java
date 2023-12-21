package com.ICM.coordenadasRutaAPI.Controllers;

import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Services.RutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/xempresaP/{estado}/{empresa}")
    public ResponseEntity<Object> GetxEmpresaP(@PathVariable Long empresa, @PathVariable Boolean estado,
                                               @RequestParam(defaultValue = "1") int pageNumber,
                                               @RequestParam(defaultValue = "6") int pageSize) {
        Page<RutasModel> data = rutasService.GetxEmpresaP(empresa, estado, pageNumber, pageSize);

        if (data != null && !data.isEmpty()) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron datos");
        }
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
    public ResponseEntity<?> Deshabilitar(@PathVariable Long id){
        try {
            RutasModel eruta = rutasService.Deshabilitar(id);
            return new ResponseEntity<>(eruta, HttpStatus.OK);
        } catch (RutasService.RutaNoEncontradaException e) {
            return new ResponseEntity<>("Ruta no encontrada: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al deshabilitar la ruta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/habilitar/{id}")
    public ResponseEntity<?> Habilitar(@PathVariable Long id){
        try {
            RutasModel eruta = rutasService.Habilitar(id);
            return new ResponseEntity<>(eruta, HttpStatus.OK);
        } catch (RutasService.RutaNoEncontradaException e) {
            return new ResponseEntity<>("Ruta no encontrada: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al habilitar la ruta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RutasModel> Delete(@PathVariable Long id){
        rutasService.Delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
