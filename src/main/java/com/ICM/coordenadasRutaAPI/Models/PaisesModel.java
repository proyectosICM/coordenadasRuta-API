package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es un modelo que representa una pais en la aplicación.
 * Se usa para asociarlo en rutas de la empresa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Paises")
public class PaisesModel {
    /**
     * Identificador unico para cada pais, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Nombre del pais
     */
    private String nombre;
}
