package com.ICM.coordenadasRutaAPI.services;

import com.ICM.coordenadasRutaAPI.models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.models.RutasModel;
import com.ICM.coordenadasRutaAPI.repositories.CoordenadasRepository;
import com.ICM.coordenadasRutaAPI.repositories.DispositivosRepository;
import com.ICM.coordenadasRutaAPI.RequestData.CoordenadasDTOtxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CoordenadasService {
    @Autowired
    private CoordenadasRepository coordenadasRepository;
    @Autowired
    private DispositivosRepository dispositivosRepository;


    public List<CoordenadasModel> GetxRutas(Long ruta){
        RutasModel rutasModel = new RutasModel();
        rutasModel.setId(ruta);
        return coordenadasRepository.findByRutasModel(rutasModel);
    }


    /* Paginado TXT */
    public Page<CoordenadasDTOtxt> GetxRutasPtxt(Long dispositivo, int pageNumber, int defaultPageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, defaultPageSize);

        return dispositivosRepository.findById(dispositivo)
                .map(DispositivosModel::getRutasModel)
                .map(rutasModel -> coordenadasRepository.findByRutasModelId(rutasModel.getId(), pageRequest))
                .map(coordenadasPage -> coordenadasPage.map(coordenadaModel -> {
                    CoordenadasDTOtxt coordenadasDTOtxt = new CoordenadasDTOtxt();
                    // Mapea los valores desde coordenadaModel a coordenadasDTOtxt
                    // Por ejemplo:
                    coordenadasDTOtxt.setCoordenadas(coordenadaModel.getCoordenadas());
                    coordenadasDTOtxt.setRdo(coordenadaModel.getRadio());
                    coordenadasDTOtxt.setNsv(coordenadaModel.getSonidosVelocidadModel().getNombre());
                    coordenadasDTOtxt.setCodv(coordenadaModel.getSonidosVelocidadModel().getCodvel());
                    coordenadasDTOtxt.setCods(coordenadaModel.getSonidosGeocercaModel().getCodsonido());
                    // ... resto de mapeo de atributos ...

                    return coordenadasDTOtxt;
                }))
                .orElse(Page.empty());
    }

    public Long countPages(Long dispositivo) {
        PageRequest pageRequest = PageRequest.of(1, 4);

        return dispositivosRepository.findById(dispositivo)
                .map(DispositivosModel::getRutasModel)
                .map(rutasModel -> coordenadasRepository.findByRutasModelId(rutasModel.getId(), pageRequest))
                .map(coordenadasPage -> {
                    // Aquí simplemente retornamos el total de páginas disponibles.
                    return Long.valueOf(coordenadasPage.getTotalPages());
                })
                .orElse(0L); // Retorna 0 si no encuentra ningún resultado.
    }


    /* */
    public Page<CoordenadasModel> GetxRutasP(Long ruta, int pageNumber, int defaultPageSize) {
        RutasModel rutasModel = new RutasModel();
        rutasModel.setId(ruta);

        // Obtener el número total de elementos para la ruta
        Long totalElements = coordenadasRepository.countByRutasModelId(ruta);


        PageRequest pageRequest = PageRequest.of(pageNumber, defaultPageSize);
        if (totalElements <= defaultPageSize) {
            return new PageImpl<>(coordenadasRepository.findByRutasModelId(ruta));
        } else {

           // Page<CoordenadasModel> elements = coordenadasRepository.findByRutasModel(rutasModel, pageRequest);
            return coordenadasRepository.findByRutasModelId(ruta, pageRequest);
        }
    }




    //CRUD

    public List<CoordenadasModel> Get(){
        return coordenadasRepository.findAll();
    }

    public Optional<CoordenadasModel> GetById(Long id){
        return coordenadasRepository.findById(id);
    }

    private String formatearCoordenadas(String coordenadas) {
        String[] coordenadasArray = coordenadas.split(", ");
        String latitud = coordenadasArray[0].replace(',', '.');
        String longitud = coordenadasArray[1].replace(',', '.');

        latitud = String.format(Locale.US, "%.7f", Double.parseDouble(latitud));
        longitud = String.format(Locale.US, "%.7f", Double.parseDouble(longitud));

        return latitud + ", " + longitud;
    }

    public CoordenadasModel Save(CoordenadasModel coordenadasModel) {
        String coordenadaFormateada = formatearCoordenadas(coordenadasModel.getCoordenadas());
        coordenadasModel.setCoordenadas(coordenadaFormateada);
        return coordenadasRepository.save(coordenadasModel);
    }

    public CoordenadasModel Edit(Long id, CoordenadasModel coordenadasModel) {
        Optional<CoordenadasModel> existing = coordenadasRepository.findById(id);
        if(existing.isPresent()){
            CoordenadasModel coordenadas = existing.get();

            String coordenadaFormateada = formatearCoordenadas(coordenadasModel.getCoordenadas());
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
