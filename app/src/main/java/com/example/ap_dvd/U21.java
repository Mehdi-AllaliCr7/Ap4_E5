package com.example.ap_dvd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

public class U21 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u21);

        int idCat = this.getIntent().getIntExtra("idCat", 0);

        ListView listeU21 = (ListView) findViewById(R.id.listeview_u21);
        JoueurAdapter adapter = new JoueurAdapter(this, R.layout.ligne);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest arrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                C.LISTE_TABLE_URL + "?idCat" + idCat,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("table");
                            int count = 0;
                            while (count < data.length()) {
                                JSONObject jsonObject = new JSONObject(data.getString(count));
                                JoueurModele unU21 = new JoueurModele();
                                unU21.setNomJoueur(jsonObject.getString("titrevideo"));
                                unU21.setPrenomJoueur(jsonObject.getString("annee"));
                                unU21.setPoste(jsonObject.getString("nonRealisateur"));
//Récupération de L'identifiont de L'image
                                String imgName = jsonObject.getString("imgvideo");
                                int resid = getResources().getIdentifier(imgName, "drawable", getPackageName());
                                unU21.setImg(resid);
                                adapter.add(unU21);
                                count++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                    }
                });


                Button btnRetourU21 = (Button) findViewById(R.id.btnRetourU21);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnRetourU21.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(U21.this);
                //creation alertdialog

                Intent intent = new Intent(U21.this, MainActivity.class) ;
                startActivity (intent);
                //Parametrage des bouton ok ou annuler



                //fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();
            }
        });//fin du bouton
    }
    public class C{
        private static final String ROOT_URL = "http://10.0.2.2/api_dvd/server";
        public static final String LISTE_TABLE_URL = ROOT_URL+"/istByCategory.php";
    }
}