package com.lm.habitatapp.entity;

import java.io.Serializable;

/**
 * Created by Mrluke on 14/10/2016.
 */

public class Produto implements Serializable {

    private String nomeProduto;
    private String caminhoFotoProduto;

    public Produto(String nomeProduto, String caminhoFotoProduto) {
        this.nomeProduto = nomeProduto;
        this.caminhoFotoProduto = caminhoFotoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCaminhoFotoProduto() {
        return caminhoFotoProduto;
    }

    public void setCaminhoFotoProduto(String caminhoFotoProduto) {
        this.caminhoFotoProduto = caminhoFotoProduto;
    }
}
