package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the model of a device (Dispositivo) in the application.
 * Contains information about the "Dispositivos" table, including its identifier, name,
 * associated route, and associated company.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Dispositivos")
public class DispositivosModel {
    /**
     * Unique identifier for the device record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Code name of the device.
     */
    private String nombre;

    /**
     * Speed information for the device.
     */
    private Integer velocidad;

    /**
     * Volume information for the device.
     */
    private Integer volumen;

    private Boolean estado;

    /**
     * The route (RutaModel) associated with this device.
     */
    @ManyToOne
    @JoinColumn(name = "rutas", referencedColumnName = "id", nullable = true)
    private RutasModel rutasModel;

    /**
     * The company (empresa) associated with this device.
     */
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;
}
