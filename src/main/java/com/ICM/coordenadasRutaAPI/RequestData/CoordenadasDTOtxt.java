package com.ICM.coordenadasRutaAPI.RequestData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordenadasDTOtxt {
    private String coordenadas;
    private int rdo;
    private String nsv;
    private int codv;
    private int cods;
}
