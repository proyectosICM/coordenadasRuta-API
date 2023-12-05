package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispositivosRepository extends JpaRepository<DispositivosModel, Long> {
    Optional<DispositivosModel> findByRutasModelId(Long rutasModelId);

    Optional<DispositivosModel> findByNombreAndEmpresasModelId(String nombre, Long empresaModelId);

    Optional<DispositivosModel> findByNombre(String nombre);
}
