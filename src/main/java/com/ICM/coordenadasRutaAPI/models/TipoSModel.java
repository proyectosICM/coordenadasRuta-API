package com.ICM.coordenadasRutaAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase es un modelo que representa un tipo de señal en la aplicacion
 * Se usa para luego filtrar las señales por tipo en la aplicacion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipoSenal")
public class TipoSModel {
    /**
     * Identificador unico para cada tipo de señal, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Nombre del tipo de señal, usado para realizar filtros de las geocercas
     */
    private String nombre;
}
