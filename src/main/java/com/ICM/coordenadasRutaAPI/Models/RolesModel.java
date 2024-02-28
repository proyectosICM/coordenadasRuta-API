package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase es un modelo que representa un rol en la aplicación.
 * Se usa para determinar el nievel de permisos de un usuario
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class RolesModel {
    /**
     * Identificador unico para cada rol, autogenerado automadicamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Tipo de rol que influye en los permisos dentro de la aplicacion.
     */
    private String nombre;
}
