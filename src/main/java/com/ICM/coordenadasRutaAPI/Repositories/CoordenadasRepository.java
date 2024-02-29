package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz del repositorio para la gestión de coordenadas en la aplicación.
 */
@Repository
public interface CoordenadasRepository extends JpaRepository<CoordenadasModel, Long> {
   /**
    * Busca coordenadas por ruta asociada.
    *
    * @param rutaId El id de la ruta para buscar coordenadas.
    * @param pageable La información de paginación.
    * @return Una página de coordenadas asociadas con la ruta dada.
    */
   Page<CoordenadasModel> findByRutasModelId(Long rutaId, Pageable pageable);

   /**
    * Buscar coordenadas por ruta asociada.
    *
    * @param rutasModel El modelo de ruta para buscar coordenadas.
    * @return Una lista de coordenadas asociadas con la ruta dada.
    */
   List<CoordenadasModel> findByRutasModel(RutasModel rutasModel);

   /**
    * Buscar coordenadas por ID de ruta asociada.
    *
    * @param rutaId El id de la ruta para buscar coordenadas.
    * @return Una lista de coordenadas asociadas con el ID de ruta dado.
    */
   List<CoordenadasModel> findByRutasModelId(Long rutaId);

   /**
    * Cuenta el número de coordenadas asociadas a una ruta.
    *
    * @param rutaId El id de la ruta para contar las coordenadas.
    * @return El recuento de coordenadas asociadas con la ruta dada.
    */
   Long countByRutasModelId(Long rutaId);
}
