package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Repositories.CoordenadasRepository;
import com.ICM.coordenadasRutaAPI.Repositories.RutasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RutasService {
    @Autowired
    RutasRepository rutasRepository;
    @Autowired
    CoordenadasRepository coordenadasRepository;

    public class RutaNoEncontradaException extends RuntimeException {
        public RutaNoEncontradaException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Method that returns a list of routes by company and state.
     *
     * @param empresa Company ID.
     * @param estado  Route state (active/inactive).
     * @return List of routes by the specified company and state.
     */
    public List<RutasModel> GetxEmpresa(Long empresa, Boolean estado){
        return rutasRepository.findByEmpresasModelIdAndEstado(empresa, estado);
    }

    /**
     * Method that returns a page of paginated routes by company and state.
     *
     * @param empresa        Company ID.
     * @param estado         Route state (active/inactive).
     * @param pageNumber     Page number.
     * @param defaultPageSize Default page size.
     * @return Page of routes by the specified company and state.
     */
    public Page<RutasModel> GetxEmpresaP(Long empresa, Boolean estado, int pageNumber, int defaultPageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, defaultPageSize);
        Page<RutasModel> coordenadasPage = rutasRepository.findByEmpresasModelIdAndEstado(empresa, estado, pageRequest);
        return coordenadasPage;
    }


    // CRUD methods for routes
    /**
     * Method to get all routes.
     *
     * @return List of all routes.
     */
    public List<RutasModel> Get(){
        return rutasRepository.findAll();
    }

    /**
     * Method to get a route by its ID.
     *
     * @param id Route ID.
     * @return Optional containing the route with the specified ID if found.
     */

    public Optional<RutasModel> GetById(Long id){
        return rutasRepository.findById(id);
    }
    /**
     * Method to save a new route.
     *
     * @param rutasModel Route model to be saved.
     * @return Saved route model.
     */
    public RutasModel Save(RutasModel rutasModel) {
        return rutasRepository.save(rutasModel);
    }

    /**
     * Method to edit an existing route by its ID.
     *
     * @param id           Route ID.
     * @param rutasModel   Route model containing updated information.
     * @return Updated route model if found and updated; otherwise, null.
     */
    public RutasModel Edit(Long id, RutasModel rutasModel){
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel ruta = existing.get();
            ruta.setNomruta(rutasModel.getNomruta());
            ruta.setEmpresasModel(rutasModel.getEmpresasModel());
            ruta.setPaisesModel(rutasModel.getPaisesModel());
            return rutasRepository.save(ruta);
        }
        return null;
    }

    /**
     * Method to disable a route by its ID.
     *
     * @param id Route ID.
     * @return Disabled route model if found and disabled; otherwise, null.
     */
    public RutasModel Deshabilitar(Long id){
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel ruta = existing.get();
            ruta.setEstado(false);

            // Get the current date
            LocalDate fechaDeshabilitacion = LocalDate.now();
            ruta.setDiadeshabilitacion(fechaDeshabilitacion);

            // Set the deletion date 7 days after the disablement date
            LocalDate fechaEliminacion = fechaDeshabilitacion.plus(7, ChronoUnit.DAYS);
            ruta.setDiaeliminacion(fechaEliminacion);

            return rutasRepository.save(ruta);
        } else {
            throw new RutaNoEncontradaException("La ruta con ID " + id + " no fue encontrada.");
        }
    }

    /**
     * Method to enable a route by its ID.
     *
     * @param id Route ID.
     * @return Enabled route model if found and enabled; otherwise, null.
     */
    public RutasModel Habilitar(Long id){
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel ruta = existing.get();
            ruta.setEstado(true);
            ruta.setDiadeshabilitacion(null);
            ruta.setDiaeliminacion(null);

            return rutasRepository.save(ruta);
        } else {
            throw new RutaNoEncontradaException("La ruta con ID " + id + " no fue encontrada.");
        }
    }

    /**
     * Method to delete a route by its ID.
     *
     * @param id Route ID.
     */
    @Transactional
    public void Delete(Long id){
        // Get the list of coordinates associated with the route
        List<CoordenadasModel> coordenadas = coordenadasRepository.findByRutasModelId(id);

        // Delete each coordinate
        for (CoordenadasModel coordenada : coordenadas) {
            coordenadasRepository.delete(coordenada);
        }

        // Then delete the route
        rutasRepository.deleteById(id);
    }

}
