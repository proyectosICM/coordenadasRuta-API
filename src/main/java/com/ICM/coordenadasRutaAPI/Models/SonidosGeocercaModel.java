package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sonidosGeocerca")
public class SonidosGeocercaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipoS", referencedColumnName = "id", nullable = false)
    private TipoSModel tipoSModel;

    private String urlImagen;

    private String urlSonido;

    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id", nullable = false)
    private PaisesModel paisesModel;

    private Integer codsonido;

}
