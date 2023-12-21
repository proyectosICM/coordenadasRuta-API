package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class represents the model of geofence sounds (Sonidos de Geocerca) in the application.
 * Contains information about the "sonidosGeocerca" table, including its identifier, name,
 * type of signal, image URL, sound URL, associated country, sound code, and details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sonidosGeocerca")
public class SonidosGeocercaModel {
    /**
     * Unique identifier for the geofence sound record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Name of the geofence sound.
     */
    private String nombre;

    /**
     * Type of signal associated with the geofence sound.
     */
    @ManyToOne
    @JoinColumn(name = "tipoS", referencedColumnName = "id", nullable = false)
    private TipoSModel tipoSModel;

    /**
     * URL of the image related to the geofence sound.
     */
    private String urlImagen;

    /**
     * URL of the sound related to the geofence.
     */
    private String urlSonido;

    /**
     * Associated country of the geofence sound.
     */
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id", nullable = false)
    private PaisesModel paisesModel;

    /**
     * Code representing the geofence sound.
     */
    private Integer codsonido;

    /**
     * Additional details about the geofence.
     */
    private String detalle;

}
