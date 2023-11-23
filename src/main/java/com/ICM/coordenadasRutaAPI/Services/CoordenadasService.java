package com.ICM.coordenadasRutaAPI.Services;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import com.ICM.coordenadasRutaAPI.Repositories.CoordenadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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



    public Page<CoordenadasModel> GetxRutasP(Long ruta, int pageNumber, int defaultPageSize) {
        RutasModel rutasModel = new RutasModel();
        rutasModel.setId(ruta);

        // Si defaultPageSize es menor o igual a 0, se utilizará un tamaño de página predeterminado
        //int pageSize = defaultPageSize <= 0 ? 10 : defaultPageSize;

        PageRequest pageRequest = PageRequest.of(pageNumber, 4);
        return coordenadasRepository.findByRutasModel(rutasModel, pageRequest);
    }


    //CRUD

    public List<CoordenadasModel> Get(){
        return coordenadasRepository.findAll();
    }

    public Optional<CoordenadasModel> GetById(Long id){
        return coordenadasRepository.findById(id);
    }

    public CoordenadasModel Save(CoordenadasModel coordenadasModel) {
        String[] coordenadas = coordenadasModel.getCoordenadas().split(", ");

        // Suponiendo que siempre tienes el formato latitud, longitud
        String latitud = coordenadas[0].replace(',', '.');
        String longitud = coordenadas[1].replace(',', '.');

        // Redondear las coordenadas a 7 decimales después de la coma
        latitud = String.format(Locale.US, "%.7f", Double.parseDouble(latitud));
        longitud = String.format(Locale.US, "%.7f", Double.parseDouble(longitud));

        // Volver a armar la cadena de coordenadas
        String coordenadaFormateada = latitud + ", " + longitud;

        // Actualizar la coordenada en el modelo antes de guardar
        coordenadasModel.setCoordenadas(coordenadaFormateada);
        return coordenadasRepository.save(coordenadasModel);
    }

    public CoordenadasModel Edit(Long id, CoordenadasModel coordenadasModel) {
        Optional<CoordenadasModel> existing = coordenadasRepository.findById(id);
        if(existing.isPresent()){
            CoordenadasModel coordenadas = existing.get();

            // Redondear las coordenadas al formato adecuado
            String[] coordenadasArray = coordenadasModel.getCoordenadas().split(", ");
            String latitud = coordenadasArray[0].replace(',', '.');
            String longitud = coordenadasArray[1].replace(',', '.');

            latitud = String.format(Locale.US, "%.7f", Double.parseDouble(latitud));
            longitud = String.format(Locale.US, "%.7f", Double.parseDouble(longitud));

            // Volver a armar la cadena de coordenadas
            String coordenadaFormateada = latitud + ", " + longitud;

            coordenadas.setCoordenadas(coordenadaFormateada);
            coordenadas.setRadio(coordenadasModel.getRadio());
            coordenadas.setSonidosVelocidadModel(coordenadasModel.getSonidosVelocidadModel());
            coordenadas.setSonidosGeocercaModel(coordenadasModel.getSonidosGeocercaModel());
            coordenadas.setSonidosGeocercaModel(coordenadasModel.getSonidosGeocercaModel());
            coordenadas.setRutasModel(coordenadasModel.getRutasModel());

            return coordenadasRepository.save(coordenadas);
        }
        return null;
    }

    public void Delete(Long id){
        coordenadasRepository.deleteById(id);
    }
}
