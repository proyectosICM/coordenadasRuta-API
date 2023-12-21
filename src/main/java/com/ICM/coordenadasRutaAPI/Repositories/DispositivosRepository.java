package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository interface for managing devices (DispositivosModel) in the application.
 */
@Repository
public interface DispositivosRepository extends JpaRepository<DispositivosModel, Long> {
    /**
     * Find a device by its associated route ID.
     *
     * @param rutasModelId The ID of the route to search devices for.
     * @return An optional containing the device associated with the given route ID, if present.
     */
    Optional<DispositivosModel> findByRutasModelId(Long rutasModelId);

    /**
     * Find a device by its name and associated company ID.
     *
     * @param nombre         The name of the device to search for.
     * @param empresaModelId The ID of the company associated with the device.
     * @return An optional containing the device with the given name and associated company ID, if present.
     */
    Optional<DispositivosModel> findByNombreAndEmpresasModelId(String nombre, Long empresaModelId);

    /**
     * Find a device by its name.
     *
     * @param nombre The name of the device to search for.
     * @return An optional containing the device with the given name, if present.
     */
    Optional<DispositivosModel> findByNombre(String nombre);
}
