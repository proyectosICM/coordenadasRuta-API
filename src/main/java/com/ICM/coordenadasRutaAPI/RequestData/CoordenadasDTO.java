package com.ICM.coordenadasRutaAPI.RequestData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordenadasDTO {
    private String coordenadas;
    private int radio;
    private String nombreSonidoVelocidad;
    private int codvel;
    private int codsonido;
}
