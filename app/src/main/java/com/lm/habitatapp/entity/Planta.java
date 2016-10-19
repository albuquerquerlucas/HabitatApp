package com.lm.habitatapp.entity;

import java.io.Serializable;

/**
 * Created by Mrluke on 14/10/2016.
 */

public class Planta implements Serializable {

    private String nomePlanta;
    private String caminhoFotoPlanta;

    public Planta(String nomePlanta, String caminhoFotoPlanta) {
        this.nomePlanta = nomePlanta;
        this.caminhoFotoPlanta = caminhoFotoPlanta;
    }

    public String getNomePlanta() {
        return nomePlanta;
    }

    public void setNomePlanta(String nomePlanta) {
        this.nomePlanta = nomePlanta;
    }

    public String getCaminhoFotoPlanta() {
        return caminhoFotoPlanta;
    }

    public void setCaminhoFotoPlanta(String caminhoFotoPlanta) {
        this.caminhoFotoPlanta = caminhoFotoPlanta;
    }
}
