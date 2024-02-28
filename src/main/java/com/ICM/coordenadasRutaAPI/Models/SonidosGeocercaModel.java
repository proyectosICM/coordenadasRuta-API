package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es un modelo que representa una geocerca en la aplicación.
 * Se usa almacenar informacion relacioanada a la geocerca como su nombre, la imagen y el sonido a emitir
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sonidosGeocerca")
public class SonidosGeocercaModel {
    /**
     * Identificador unico para cada geocerca, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Nombre en codigo de la geocerca
     */
    private String nombre;

    /**
     * Tipo de señal que es esta geocerca
     */
    @ManyToOne
    @JoinColumn(name = "tipoS", referencedColumnName = "id", nullable = false)
    private TipoSModel tipoSModel;

    /**
     * URL de la imagen relacionada a la geocerca
     */
    private String urlImagen;

    /**
     * URL del sonido de la geocerca
     */
    private String urlSonido;

    /**
     * Pais al que esta asociado la geocerca
     */
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id", nullable = false)
    private PaisesModel paisesModel;

    /**
     * Codigo del sonido que se usa para reproducir el sondio en el dispisitivo
     */
    private Integer codsonido;

    /**
     * Detalles adicionales sobre la geocerca, como un nombre mas descriptivo
     */
    private String detalle;

}
