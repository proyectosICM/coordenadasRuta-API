package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Repositories.CoordenadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CoordenadasService {
    @Autowired
    CoordenadasRepository coordenadasRepository;

    public List<CoordenadasModel> GetxRutas(Long ruta){
        RutasModel rutasModel = new RutasModel();
        rutasModel.setId(ruta);
        return coordenadasRepository.findByRutasModel(rutasModel);
    }

    //CRUD

    public List<CoordenadasModel> Get(){
        return coordenadasRepository.findAll();
    }

    public Optional<CoordenadasModel> GetById(Long id){
        return coordenadasRepository.findById(id);
    }

    public CoordenadasModel Save(CoordenadasModel coordenadasModel) {
        return coordenadasRepository.save(coordenadasModel);
    }

    public CoordenadasModel Edit(Long id, CoordenadasModel coordenadasModel){
        Optional<CoordenadasModel> existing = coordenadasRepository.findById(id);
        if(existing.isPresent()){
            CoordenadasModel coordenadas = existing.get();

            coordenadas.setLatitud(coordenadasModel.getLatitud());
            coordenadas.setLongitud(coordenadasModel.getLongitud());
            coordenadas.setRadio(coordenadasModel.getRadio());
            coordenadas.setVelocidad(coordenadasModel.getVelocidad());
            coordenadas.setSonidoVelocidad(coordenadasModel.getSonidoVelocidad());
            coordenadas.setSonidoGeocerca(coordenadasModel.getSonidoGeocerca());
            coordenadas.setRutasModel(coordenadasModel.getRutasModel());

            return coordenadasRepository.save(coordenadas);
        }
        return null;
    }

    public void Delete(Long id){
        coordenadasRepository.deleteById(id);
    }
}
