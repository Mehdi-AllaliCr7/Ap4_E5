package com.example.ap_dvd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ap_dvd.pickjoueur.JoueurAdapter;
import com.example.ap_dvd.pickjoueur.JoueurModele;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seniors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniors);

        //liste Json
        //ListView ListeSeniors = (ListView) findViewById(R.id.liste_Seniors);


        Button btnRetourSenior = (Button) findViewById(R.id.btnRetourSenior);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnRetourSenior.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Seniors.this);
                //creation alertdialog

                Intent intent = new Intent(Seniors.this, MainActivity.class);
                startActivity(intent);
                //Parametrage des bouton ok ou annuler


                //fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();
            }
        });//fin du bouton



        //ListView ListeSeniors = (ListView) findViewById(R.id.liste_Seniors);
        ListView liste_Seniors = (ListView) findViewById(R.id.liste_Seniors);
        JoueurAdapter adapter = new JoueurAdapter(this, R.layout.ligne);
        int idCat = this.getIntent().getIntExtra("idCat", 0);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest arrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                C.LISTE_TABLE_URL + "?idCat=" + idCat,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("table");
                            int count = 0;
                            while (count < data.length()) {
                                JSONObject jsonObject = new JSONObject(data.getString(count));
                                JoueurModele unSenior = new JoueurModele();
                                unSenior.setNomJoueur(jsonObject.getString("nomAdherent"));
                                //Log.i("nom",unSenior.getNomJoueur());
                                unSenior.setPrenomJoueur(jsonObject.getString("prenomAdherent"));
                                unSenior.setPoste(jsonObject.getString("poste"));
//Récupération de L'identifiont de L'image
                               /* String imgName = jsonObject.getString("imgvideo");
                                int resid = getResources().getIdentifier(imgName, "drawable", getPackageName());
                                unU21.setImg(resid);*/
                                adapter.add(unSenior);
                                count++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(Seniors.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seniors.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.getMessage();
                    }
                });
        requestQueue.add(arrayRequest);
        liste_Seniors.setAdapter(adapter);
    }

    /*public String LireLeJson() {
        StringBuilder builder = new StringBuilder();
        AssetManager assetManager;
        InputStreamReader isr;
        BufferedReader data;

        try {
            assetManager = getAssets();
            isr = new InputStreamReader(assetManager.open("Seniors.json"));
            data = new BufferedReader(isr);
            String line;
            while ((line = data.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }*/



}
