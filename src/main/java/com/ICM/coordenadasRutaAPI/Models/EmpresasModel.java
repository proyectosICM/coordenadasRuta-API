package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es un modelo que representa una empresa en la aplicación.
 * Continiene informacion acerca del nombre mismo de la empresa y su usuario para acceder al sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Empresas")
public class EmpresasModel {
    /**
     * Identificador unico para cada empresa, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Nombre de la empresa
     */
    private String nombre;

    /**
     * Usuario para la empresa
     */
    private String usuario;

    /**
     * Contraseña para la empresa
     */
    private String password;
}
