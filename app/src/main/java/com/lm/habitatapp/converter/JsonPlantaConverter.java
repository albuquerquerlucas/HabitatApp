package com.lm.habitatapp.converter;

import com.lm.habitatapp.entity.Peixe;
import com.lm.habitatapp.entity.Planta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mrluke on 13/10/2016.
 */

public class JsonPlantaConverter {

    public static List<Planta> converteJson(String json) throws JSONException{

        List<Planta> listaPlantas = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objectJson = (JSONObject) jsonArray.get(i);
            String nome = objectJson.getString("nome");
            String caminhoFotoPlanta = objectJson.getString("foto");

            listaPlantas.add(new Planta(nome, caminhoFotoPlanta));
        }
        return listaPlantas;
    }
}
