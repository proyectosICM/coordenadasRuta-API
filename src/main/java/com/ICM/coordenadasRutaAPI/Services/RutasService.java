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
     * @param empresaId Company ID.
     * @param estado  Route state (active/inactive).
     * @return List of routes by the specified company and state.
     */
    public List<RutasModel> getRoutesByCompanyAndState(Long empresaId, Boolean estado){
        return rutasRepository.findByEmpresasModelIdAndEstado(empresaId, estado);
    }

    /**
     * Method that returns a page of paginated routes by company and state.
     * @param empresaId        Company ID.
     * @param estado         Route state (active/inactive).
     * @param pageNumber     Page number.
     * @param defaultPageSize Default page size.
     * @return Page of routes by the specified company and state.
     */
    public Page<RutasModel> getPagedRoutesByCompanyAndState(Long empresaId, Boolean estado, int pageNumber, int defaultPageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, defaultPageSize);
        Page<RutasModel> coordenadasPage = rutasRepository.findByEmpresasModelIdAndEstado(empresaId, estado, pageRequest);
        return coordenadasPage;
    }


    public List<RutasModel> getAllRoutes(){
        return rutasRepository.findAll();
    }

    public Optional<RutasModel> findRouteById(Long id){
        return rutasRepository.findById(id);
    }


    public RutasModel saveNewRoute(RutasModel rutasModel) {
        return rutasRepository.save(rutasModel);
    }

    /**
     * Method to edit an existing route by its ID.
     *
     * @param id           Route ID.
     * @param rutasModel   Route model containing updated information.
     * @return Updated route model if found and updated; otherwise, null.
     */
    public RutasModel updateExistingRouteById(Long id, RutasModel rutasModel){
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
     * Method to change the state (enable/disable) of a route by its ID.
     *
     * @param id           Route ID.
     * @param habilitar    Boolean value to determine whether to enable or disable the route.
     * @return Updated route model with the modified state if found and updated; otherwise, throws RutaNoEncontradaException.
     */
    public RutasModel updateRouteStateById(Long id, boolean habilitar) {
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel ruta = existing.get();
            ruta.setEstado(habilitar);
            if (habilitar) {
                ruta.setDiadeshabilitacion(null);
                ruta.setDiaeliminacion(null);
            } else {
                // Get the current date
                LocalDate fechaDeshabilitacion = LocalDate.now();
                ruta.setDiadeshabilitacion(fechaDeshabilitacion);

                // Set the deletion date 7 days after the disablement date
                LocalDate fechaEliminacion = fechaDeshabilitacion.plus(7, ChronoUnit.DAYS);
                ruta.setDiaeliminacion(fechaEliminacion);
            }
            return rutasRepository.save(ruta);
        } else {
            throw new RutaNoEncontradaException("La ruta con ID " + id + " no fue encontrada.");
        }
    }


    @Transactional
    public void deleteRouteByIdAndAssociatedCoordinates(Long id){
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
