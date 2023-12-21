package com.ICM.coordenadasRutaAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the model of a company (Empresa) in the application.
 * Contains information about the "Empresas" table, including its identifier, name,
 * user, and password.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Empresas")
public class EmpresasModel {
    /**
     * Unique identifier for the company record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Name of the company.
     */
    private String nombre;

    /**
     * User associated with the company.
     */
    private String usuario;

    /**
     * Password associated with the company.
     */
    private String password;


}
