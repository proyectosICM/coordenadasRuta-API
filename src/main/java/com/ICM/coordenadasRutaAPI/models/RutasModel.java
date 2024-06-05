package com.ICM.coordenadasRutaAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Esta clase es un modelo que representa una ruta en la aplicación.
 * Se usa para almacenar asociar a las coordenadas y poder luego hacer filtro de coordenadas por ruta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ruta")
public class RutasModel {
    /**
     * Identificador unico para cada ruta, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    /**
     * Nombre de la ruta
     */
    @NonNull
    private String nomruta;

    /**
     * El estado de la ruta, puede ser activo o inhabilitado
     */
    @NonNull
    private boolean estado;

    /**
     * La empresa asociada a la ruta
     */
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;

    /**
     * Pais asociado a esta ruta
     */
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id", nullable = false)
    private PaisesModel paisesModel;


    /**
     * Estos campos representan la fecha en que se deshabilitó el registro y
     * la fecha en la que se eliminará el registro, respectivamente
     */

    /**
     * Fecha en la que se deshabilitó el registro.
     */
    private LocalDate diadeshabilitacion;


    /**
     * Fecha en la que se eliminará el registro
     */
    private LocalDate diaeliminacion;
}
