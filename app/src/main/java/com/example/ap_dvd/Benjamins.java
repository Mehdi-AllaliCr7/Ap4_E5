package com.example.ap_dvd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import org.xmlpull.v1.XmlPullParser;

public class Benjamins extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benjamins);

        Button btnRetourBenj = (Button) findViewById(R.id.BtnRetourBenj);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnRetourBenj.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Benjamins.this);
                //creation alertdialog

                Intent intent = new Intent(Benjamins.this, MainActivity.class) ;
                startActivity (intent);
                //Parametrage des bouton ok ou annuler



                //fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();
            }
        });//fin du bouton


        int idCat = this.getIntent().getIntExtra("idCat", 0);

        ListView liste_Benjamins = (ListView) findViewById(R.id.benjamins_listview);
        JoueurAdapter adapter = new JoueurAdapter(this, R.layout.ligne);

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
                                JoueurModele unBenjamin = new JoueurModele();
                                unBenjamin.setNomJoueur(jsonObject.getString("nomAdherent"));
                                Log.i("nom",unBenjamin.getNomJoueur());
                                unBenjamin.setPrenomJoueur(jsonObject.getString("prenomAdherent"));
                                unBenjamin.setPoste(jsonObject.getString("poste"));
//Récupération de L'identifiont de L'image
                               /* String imgName = jsonObject.getString("imgvideo");
                                int resid = getResources().getIdentifier(imgName, "drawable", getPackageName());
                                unU21.setImg(resid);*/
                                adapter.add(unBenjamin);
                                count++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(Benjamins.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Benjamins.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.getMessage();
                    }
                });
        requestQueue.add(arrayRequest);
        liste_Benjamins.setAdapter(adapter);

    }

}