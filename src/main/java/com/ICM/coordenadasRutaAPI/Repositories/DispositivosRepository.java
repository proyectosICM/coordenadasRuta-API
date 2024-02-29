package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.DispositivosModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Interfaz del repositorio para la gestión de dispositivos en la aplicación.
 */
@Repository
public interface DispositivosRepository extends JpaRepository<DispositivosModel, Long> {
    /**
     * Busca un dispositivo por su ID de ruta asociada.
     *
     * @param rutaId El id de la ruta para buscar dispositivos.
     * @return Opcional que contiene la informacion del dispositivo
     */
    Optional<DispositivosModel> findByRutasModelId(Long rutaId);

    /**
     * Busca un dispositivo por su nombre y el ID de empresa asociado.
     *
     * @param nombre El nombre del dispositivo a buscar.
     * @param empresaId El ID de la empresa asociada al dispositivo.
     * @return Un opcional que contiene el dispositivo con el nombre de pila y el ID de la empresa asociada, si está presente.
     */
    Optional<DispositivosModel> findByNombreAndEmpresasModelId(String nombre, Long empresaId);

    /**
     * Busca un dispositivo por su nombre.
     *
     * @param nombre El nombre del dispositivo a buscar.
     * @return Un opcional que contiene el dispositivo con el nombre de pila, si está presente.
     */
    Optional<DispositivosModel> findByNombre(String nombre);

    /**
     * Obtine una lista paginada de dispositivos asociados con una empresa específica y en un estado determinado.
     *
     * @param empresaId el id de la empresa para la que buscar dispositivos.
     * @param estado El estado de los dispositivos a filtrar.
     * @param pageable La información de la paginación.
     * @return Página de objetos DispositivosModel asociados a la empresa y estado especificados.
     */
    Page<DispositivosModel> findByEmpresasModelIdAndEstado(Long empresaId, Boolean estado, Pageable pageable);
}
