package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
/**
 * This class represents the model of a route (Ruta) in the application.
 * Contains information about the "Ruta" table, its status, associated companies, country, disabling and deletion dates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ruta")
public class RutasModel {
    /**
     * Unique record identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    /**
     * Route name
     */
    @NonNull
    private String nomruta;

    /**
     * Current route status (active/inactive).
     */
    @NonNull
    private boolean estado;

    /**
     * The company (empresa) associated with this route.
     */
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;

    /**
     * The country (pais) to which this route belongs.
     */
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id", nullable = false)
    private PaisesModel paisesModel;

    /**
     *  These fields represent the date when registration was disabled and
     *  the date when the record will be deleted, respectively
     */

    /**
     * Date on which registration was disabled.
     */
    private LocalDate diadeshabilitacion;

    /**
     * Date on which the record will be deleted
     */
    private LocalDate diaeliminacion;
}
