package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class represents the model of a signal type (Tipo de Señal) in the application.
 * Contains information about the "tipoSenal" table, including its identifier and name.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipoSenal")
public class TipoSModel {
    /**
     * Unique identifier for the signal type record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Name of the signal type.
     */
    private String nombre;
}
