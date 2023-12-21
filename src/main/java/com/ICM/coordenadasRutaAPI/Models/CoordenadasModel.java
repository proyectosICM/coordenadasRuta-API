package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class represents the model of a route (Ruta) in the application.
 * Contains information about the "Ruta" table, its status, associated companies, country, disabling and deletion dates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Coordenadas")
public class CoordenadasModel {
    /**
     * Unique record identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    /**
     *
     */
    private String coordenadas;
    private Integer radio;

    @ManyToOne
    @JoinColumn(name = "velocidad", referencedColumnName = "id", nullable = false)
    private SonidosVelocidadModel sonidosVelocidadModel;

    @ManyToOne
    @JoinColumn(name = "geocerca", referencedColumnName = "id", nullable = false)
    private SonidosGeocercaModel sonidosGeocercaModel;

    @ManyToOne
    @JoinColumn(name = "ruta", referencedColumnName = "id", nullable = false)
    private RutasModel rutasModel;

}