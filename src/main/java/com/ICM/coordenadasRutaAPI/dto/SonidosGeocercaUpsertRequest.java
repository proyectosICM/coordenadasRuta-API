package com.ICM.coordenadasRutaAPI.dto;

import lombok.Data;

@Data
public class SonidosGeocercaUpsertRequest {
    private String nombre;
    private Long tipoSId;
    private Long paisId;
    private Integer codsonido;
    private String detalle;

    private String urlImagen;
    private String urlSonido;
}
