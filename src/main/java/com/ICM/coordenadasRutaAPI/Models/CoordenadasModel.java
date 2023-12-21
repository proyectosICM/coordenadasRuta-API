package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class represents the model of coordinates (Coordenadas) in the application.
 * Contains information about the "Coordenadas" table, coordinates, associated route, geofence, speed sounds, and radius.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Coordenadas")
public class CoordenadasModel {
    /**
     * Unique record identifier for coordinates.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Coordinates represented as a String.
     */
    private String coordenadas;

    /**
     * Radio de efecto asociado a las coordenadas.
     */
    private Integer radio;

    /**
     * Speed sounds associated with these coordinates.
     */
    @ManyToOne
    @JoinColumn(name = "velocidad", referencedColumnName = "id", nullable = false)
    private SonidosVelocidadModel sonidosVelocidadModel;

    /**
     * Geofence associated with these coordinates.
     */
    @ManyToOne
    @JoinColumn(name = "geocerca", referencedColumnName = "id", nullable = false)
    private SonidosGeocercaModel sonidosGeocercaModel;

    /**
     * The route (RutasModel) to which these coordinates belong.
     */
    @ManyToOne
    @JoinColumn(name = "ruta", referencedColumnName = "id", nullable = false)
    private RutasModel rutasModel;

}