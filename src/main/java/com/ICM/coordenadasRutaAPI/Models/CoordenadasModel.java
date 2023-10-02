package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Coordenadas")
public class CoordenadasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
/*
    @Column(precision = 20, scale = 15)
    private BigDecimal latitud;
    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;
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
