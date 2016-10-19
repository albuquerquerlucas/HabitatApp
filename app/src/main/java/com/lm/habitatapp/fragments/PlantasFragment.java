package com.lm.habitatapp.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lm.habitatapp.R;
import com.lm.habitatapp.adapter.ItemPeixeAdapter;
import com.lm.habitatapp.adapter.ItemPlantaAdapter;
import com.lm.habitatapp.app.AppConfiguracao;
import com.lm.habitatapp.converter.JsonConverter;
import com.lm.habitatapp.converter.JsonPlantaConverter;
import com.lm.habitatapp.entity.Peixe;
import com.lm.habitatapp.entity.Planta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlantasFragment extends Fragment {

    private ItemPlantaAdapter plantaAdapter;

    public PlantasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plantas_fragment, container, false);

        ListView lista = (ListView) view.findViewById(R.id.listaDePlantas);
        plantaAdapter = new ItemPlantaAdapter(getActivity().getBaseContext(), new ArrayList<Planta>());
        lista.setAdapter(plantaAdapter);

        setupToolbar(view);
        new RequisicaoTask().execute();
        return view;
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        final ActionBar bar = activity.getSupportActionBar();
        if (bar != null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setShowHideAnimationEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.ic_navigation_menu);
            bar.setTitle(activity.getString(R.string.plantas));
        }
    }

    public class RequisicaoTask extends AsyncTask<Void, Void, List<Planta>> {

        @Override
        protected List<Planta> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL(AppConfiguracao.URL_PLANTAS);
                //URL url = new URL("http://10.54.108.19:80/peixesWs/exibirPeixesJson.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String linha;
                StringBuffer buffer = new StringBuffer();

                while ((linha = reader.readLine()) != null){
                    buffer.append(linha);
                    buffer.append("\n");
                }

                return JsonPlantaConverter.converteJson(buffer.toString());

            }catch (Exception e){
                e.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Planta> itemPlantas) {

            plantaAdapter.clear();
            plantaAdapter.addAll(itemPlantas);
            plantaAdapter.notifyDataSetChanged();
        }
    }
}