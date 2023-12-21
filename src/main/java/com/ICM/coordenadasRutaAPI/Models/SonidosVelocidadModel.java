package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class represents the model of speed sounds (Sonidos Velocidad) in the application.
 * Contains information about the "speedsounds" table, including its identifier, name, speed sound, and code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sonidosVelocidad")
public class SonidosVelocidadModel {
    /**
     * Unique identifier for the speed sound record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     *Kilometer per hour of speed
     */
    private String nombre;

    /**
     * Sound related to a particular speed.
     */
    private String sonidoVelocidad;

    /**
     * Code representing the speed.
     */
    private Integer codvel;
}
