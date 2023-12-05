package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoordenadasRepository extends JpaRepository<CoordenadasModel, Long> {
   Page<CoordenadasModel> findByRutasModel(RutasModel rutasModel, Pageable pageable);
   List<CoordenadasModel> findByRutasModel(RutasModel rutasModel);

   List<CoordenadasModel> findByRutasModelId(Long rutasModelId);
}
