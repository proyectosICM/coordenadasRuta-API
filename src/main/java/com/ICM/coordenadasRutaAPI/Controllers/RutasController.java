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

    @GetMapping("/xempresa")
    public ResponseEntity<List<RutasModel>> getRoutesByCompanyAndState(
            @RequestParam Long empresaId,
            @RequestParam Boolean estado
    ) {
        List<RutasModel> data = rutasService.getRoutesByCompanyAndState(empresaId, estado);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/xempresaP/{estado}/{empresaId}")
    public ResponseEntity<Object> getPagedRoutesByCompanyAndState(@PathVariable Long empresaId, @PathVariable Boolean estado,
                                               @RequestParam(defaultValue = "1") int pageNumber,
                                               @RequestParam(defaultValue = "6") int pageSize) {
        Page<RutasModel> data = rutasService.getPagedRoutesByCompanyAndState(empresaId, estado, pageNumber, pageSize);

        if (data != null && !data.isEmpty()) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron datos");
        }
    }

    @GetMapping
    public ResponseEntity<List<RutasModel>> getAllRoutes (){

        List<RutasModel> data =  rutasService.getAllRoutes();

        if (data.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutasModel> getRouteById(@PathVariable Long id){
        Optional<RutasModel> data  = rutasService.findRouteById(id);

        return data.map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<RutasModel> createNewRoute(@RequestBody RutasModel routeModel) {
        RutasModel createdRoute = rutasService.saveNewRoute(routeModel);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutasModel> updateRouteById(@PathVariable Long id, @RequestBody RutasModel rutasModel){
        RutasModel data = rutasService.updateExistingRouteById(id, rutasModel);
        if (data!=null){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/estado/{id}/{habilitar}")
    public ResponseEntity<?> updateRouteState(@PathVariable Long id, @PathVariable boolean habilitar){
        try {
            RutasModel eruta = rutasService.updateRouteStateById(id, habilitar);
            return new ResponseEntity<>(eruta, HttpStatus.OK);
        } catch (RutasService.RutaNoEncontradaException e) {
            return new ResponseEntity<>("Ruta no encontrada: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al cambiar el estado de la ruta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RutasModel> deleteRouteAndAssociatedCoordinatesById(@PathVariable Long id){
        rutasService.deleteRouteByIdAndAssociatedCoordinates(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
