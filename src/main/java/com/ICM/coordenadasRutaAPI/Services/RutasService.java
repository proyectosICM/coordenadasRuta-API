package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Repositories.CoordenadasRepository;
import com.ICM.coordenadasRutaAPI.Repositories.PaisesRepository;
import com.ICM.coordenadasRutaAPI.Repositories.RutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutasService {
    @Autowired
    RutasRepository rutasRepository;
    @Autowired
    CoordenadasRepository coordenadasRepository;

    public List<RutasModel> GetxEmpresa(Long empresa){
        EmpresasModel empresasModel = new EmpresasModel();
        empresasModel.setId(empresa);
        return rutasRepository.findByEmpresasModel(empresasModel);
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

    /*
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

     */
}
