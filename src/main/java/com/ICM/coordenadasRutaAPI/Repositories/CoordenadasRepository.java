package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing coordinates (CoordenadasModel) in the application.
 */

@Repository
public interface CoordenadasRepository extends JpaRepository<CoordenadasModel, Long> {
   /**
    * Find coordinates by associated route.
    *
    * @param ruta The route model to search coordinates for.
    * @param pageable   The pagination information.
    * @return A page of coordinates associated with the given route.
    */
   Page<CoordenadasModel> findByRutasModelId(Long ruta, Pageable pageable);

   /**
    * Find coordinates by associated route.
    *
    * @param rutasModel The route model to search coordinates for.
    * @return A list of coordinates associated with the given route.
    */
   List<CoordenadasModel> findByRutasModel(RutasModel rutasModel);

   /**
    * Find coordinates by associated route ID.
    *
    * @param rutasModelId The ID of the route to search coordinates for.
    * @return A list of coordinates associated with the given route ID.
    */
   List<CoordenadasModel> findByRutasModelId(Long rutasModelId);

   /**
    * Count the number of coordinates associated with a route.
    *
    * @param rutaid The route model to count coordinates for.
    * @return The count of coordinates associated with the given route.
    */
   Long countByRutasModelId(Long rutaid);
}
