package com.ICM.coordenadasRutaAPI.repositories;

import com.ICM.coordenadasRutaAPI.models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * Repository interface for managing companies (EmpresasModel) in the application.
 */

public interface EmpresasRepository extends JpaRepository<EmpresasModel, Long> {
    /**
     * Find a company by its username and password.
     *
     * @param usuario  The username of the company to search for.
     * @param password The password of the company to search for.
     * @return An optional containing the company with the given username and password, if present.
     */
    Optional<EmpresasModel> findByUsuarioAndPassword(String usuario, String password);

    /**
     * Find a company by its name.
     *
     * @param nombre The name of the company to search for.
     * @return An optional containing the company with the given name, if present.
     */
    Optional<EmpresasModel> findByNombre(String nombre);

    /**
     * Find a company by its username.
     *
     * @param usuario The username of the company to search for.
     * @return An optional containing the company with the given username, if present.
     */
    Optional<EmpresasModel> findByUsuario(String usuario);
}
