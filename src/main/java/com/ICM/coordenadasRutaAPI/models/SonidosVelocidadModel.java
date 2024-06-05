package com.ICM.coordenadasRutaAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Esta clase es un modelo que representa un sonido preventivo de velocidad
 * Se usa almacenar informacion relacioanada al sonido preventivo como su nombre(Kilometros), el sonido a emitir
 * y el codigo del audio para su reproduccion en el dispositivo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sonidosVelocidad")
public class SonidosVelocidadModel {
    /**
     * Identificador unico para cada sonido de veloidad, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Kilometros por hora de la velocidad (Debe ser la cantidad de kilometros que el audio emite)
     */
    private String nombre;

    /**
     * URL del sonido que se emitira
     */
    private String sonidoVelocidad;

    /**
     * Codigo del sonido que se usa para reproducir el sondio en el dispositivo
     */
    private Integer codvel;
}
