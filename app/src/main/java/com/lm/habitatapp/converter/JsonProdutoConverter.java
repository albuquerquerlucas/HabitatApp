package com.lm.habitatapp.converter;

import com.lm.habitatapp.entity.Peixe;
import com.lm.habitatapp.entity.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class JsonProdutoConverter {

    public static List<Produto> converteJson(String json) throws JSONException{

        List<Produto> listaProdutos = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objectJson = (JSONObject) jsonArray.get(i);
            String nome = objectJson.getString("nome");
            String caminhoFotoProduto = objectJson.getString("foto");

            listaProdutos.add(new Produto(nome, caminhoFotoProduto));
        }
        return listaProdutos;
    }
}
