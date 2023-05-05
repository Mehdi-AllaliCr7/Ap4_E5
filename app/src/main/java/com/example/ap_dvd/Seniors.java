package com.example.ap_dvd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ap_dvd.pickjoueur.JoueurAdapter;
import com.example.ap_dvd.pickjoueur.JoueurModele;

import org.json.JSONArray;
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

        ListView ListeSeniors = (ListView) findViewById(R.id.liste_Seniors);
        JoueurAdapter adapter = new JoueurAdapter(this, R.layout.ligne);

        String strJson = LireLeJson();
        try {
            JSONArray jsonArray = new JSONArray((strJson));
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject (i);
                JoueurModele Sen = new JoueurModele();
                Sen.setNomJoueur(jsonObject.getString("titrevideo"));
                Sen.setPrenomJoueur(jsonObject.getString("annee"));
                Sen.setPoste(jsonObject.getString("nonRealisateur"));
//Récupération de L'identifiont de L'image
                        String imgName = jsonObject.getString ("imgvideo");
                int resid = getResources().getIdentifier(imgName, "drawable", getPackageName());
                Sen.setImg(resid) ;
                adapter.add(Sen);


            }} catch (Exception e) {
            e.printStackTrace();
        }

        ListeSeniors.setAdapter(adapter);
    }

    public String LireLeJson() {
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
    }



}
