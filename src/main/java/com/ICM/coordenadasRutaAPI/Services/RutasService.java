package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Repositories.CoordenadasRepository;
import com.ICM.coordenadasRutaAPI.Repositories.PaisesRepository;
import com.ICM.coordenadasRutaAPI.Repositories.RutasRepository;
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

    public List<RutasModel> GetxEmpresa(Long empresa, Boolean estado){
        return rutasRepository.findByEmpresasModelIdAndEstado(empresa, estado);
    }

    public Page<RutasModel> GetxEmpresaP(Long empresa, Boolean estado, int pageNumber, int defaultPageSize){
        List<RutasModel> data = rutasRepository.findByEmpresasModelIdAndEstado(empresa, estado);

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, defaultPageSize);
        Page<RutasModel> coordenadasPage = rutasRepository.findByEmpresasModelIdAndEstado(empresa, estado, pageRequest);

        return coordenadasPage;
    }

    //CRUD
    public List<RutasModel> Get(){
        return rutasRepository.findAll();
    }

    public Optional<RutasModel> GetById(Long id){
        return rutasRepository.findById(id);
    }

    public RutasModel Save(RutasModel rutasModel) {
        return rutasRepository.save(rutasModel);
    }

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

    public RutasModel Deshabilitar(Long id){
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel ruta = existing.get();
            ruta.setEstado(false);

            // Obtener la fecha actual
            LocalDate fechaDeshabilitacion = LocalDate.now();
            ruta.setDiadeshabilitacion(fechaDeshabilitacion);

            // Establecer la fecha de eliminación 7 días después de la fecha de deshabilitación
            LocalDate fechaEliminacion = fechaDeshabilitacion.plus(7, ChronoUnit.DAYS);
            ruta.setDiaeliminacion(fechaEliminacion);

            return rutasRepository.save(ruta);
        }
        return null;
    }

    public RutasModel Habilitar(Long id){
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel ruta = existing.get();
            ruta.setEstado(true);
            ruta.setDiadeshabilitacion(null);
            ruta.setDiaeliminacion(null);

            return rutasRepository.save(ruta);
        }
        return null;
    }


    public void Delete(Long id){
        Optional<RutasModel> rutaOptional = rutasRepository.findById(id);

    RutasModel ruta = new RutasModel();
    ruta.setId(id);
            // Obtén la lista de coordenadas asociadas a la ruta
            List<CoordenadasModel> coordenadas = coordenadasRepository.findByRutasModel(ruta);

            // Elimina cada coordenada
            for (CoordenadasModel coordenada : coordenadas) {
                coordenadasRepository.delete(coordenada);
            }

            // Luego elimina la ruta
            rutasRepository.deleteById(id);
    }

}
