package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the model of a country (Pais) in the application.
 * Contains information about the "Paises" table, including its identifier and name.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Paises")
public class PaisesModel {
    /**
     * Unique identifier for the country record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Unique identifier for the country record.
     */
    private String nombre;
}
