package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es un modelo que representa un conjunto de coordenadas en la aplicación.
 * Continiene informacion acerca de las mismas y sus propiedad asignadas (Sonidos, imagenes y radio en el que hace efecto)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Coordenadas")
public class CoordenadasModel {
    /**
     * Identificador unico para cada coordenada, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Coordenada en si misma que debe contener latitud y longuitud
     */
    private String coordenadas;

    /**
     * Radio de efecto asociado a las coordenadas.
     */
    private Integer radio;

    /**
     * Sonido de alerta para la reducion de velocidad
     */
    @ManyToOne
    @JoinColumn(name = "velocidad", referencedColumnName = "id", nullable = false)
    private SonidosVelocidadModel sonidosVelocidadModel;

    /**
     * Geocerca asociada a la coordenada
     */
    @ManyToOne
    @JoinColumn(name = "geocerca", referencedColumnName = "id", nullable = false)
    private SonidosGeocercaModel sonidosGeocercaModel;

    /**
     * La ruta a la que pertenece esta coordenada
     */
    @ManyToOne
    @JoinColumn(name = "ruta", referencedColumnName = "id", nullable = false)
    private RutasModel rutasModel;
}