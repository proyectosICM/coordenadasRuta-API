package com.ICM.coordenadasRutaAPI.repositories;

import com.ICM.coordenadasRutaAPI.models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.models.RutasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing routes (RutasModel) in the application.
 */
@Repository
public interface RutasRepository extends JpaRepository<RutasModel, Long> {
    /**
     * Find routes by associated company.
     *
     * @param empresasModel The company model to search routes for.
     * @return A list of routes associated with the given company.
     */
    List<RutasModel> findByEmpresasModel(EmpresasModel empresasModel);
    /**
     * Find routes by associated company ID and status (active/inactive).
     *
     * @param idempresa The ID of the company to search routes for.
     * @param estado     The status of the routes (true for active, false for inactive).
     * @param pageable   The pagination information.
     * @return A page of routes associated with the given company ID and status.
     */
    Page<RutasModel> findByEmpresasModelIdAndEstado(Long idempresa, Boolean estado, Pageable pageable);
    /**
     * Find routes by associated company ID and status (active/inactive).
     *
     * @param idempresa The ID of the company to search routes for.
     * @param estado     The status of the routes (true for active, false for inactive).
     * @return A list of routes associated with the given company ID and status.
     */
    List<RutasModel> findByEmpresasModelIdAndEstado(Long idempresa, Boolean estado);
}
