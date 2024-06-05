package com.ICM.coordenadasRutaAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es un modelo que representa un dispositivo en la aplicación.
 * Continiene informacion acerca de los mismas y sus propiedad asignadas
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Dispositivos")
public class DispositivosModel {
    /**
     * Identificador unico para cada dispositivo, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * nombre en codigo del dispositivo
     */
    private String nombre;

    /**
     * Informacion de velocidad relevante para el dispositivo
     */
    private Integer velocidad;

    /**
     * Informacion del volumen para el dispositivo
     */
    private Integer volumen;

    /**
     * Estado del dispositivo para determidar si esta activo o no
     */
    private Boolean estado;

    /**
     * Ruta a la que el dispositivo esta asociado
     */
    @ManyToOne
    @JoinColumn(name = "rutas", referencedColumnName = "id", nullable = true)
    private RutasModel rutasModel;

    /**
     * Empresa dueña del dispositivo
     */
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;
}
