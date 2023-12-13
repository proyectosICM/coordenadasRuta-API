package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresasRepository extends JpaRepository<EmpresasModel, Long> {
    Optional<EmpresasModel> findByUsuarioAndPassword(String usuario, String password);
    Optional<EmpresasModel> findByNombre(String nombre);
    Optional<EmpresasModel> findByUsuario(String usuario);
}
