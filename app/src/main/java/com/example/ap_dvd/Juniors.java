package com.example.ap_dvd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

public class Juniors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juniors);

        Button btnRetourJunior = (Button) findViewById(R.id.btnRetourJunior);

        int idCat = this.getIntent().getIntExtra("idCat", 0);

        ListView liste_Juniors = (ListView) findViewById(R.id.junior_listview);
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
                                JoueurModele unJunior = new JoueurModele();
                                unJunior.setNomJoueur(jsonObject.getString("nomAdherent"));
                                Log.i("nom",unJunior.getNomJoueur());
                                unJunior.setPrenomJoueur(jsonObject.getString("prenomAdherent"));
                                unJunior.setPoste(jsonObject.getString("poste"));
//Récupération de L'identifiont de L'image
                               /* String imgName = jsonObject.getString("imgvideo");
                                int resid = getResources().getIdentifier(imgName, "drawable", getPackageName());
                                unU21.setImg(resid);*/
                                adapter.add(unJunior);
                                count++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(Juniors.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Juniors.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.getMessage();
                    }
                });
        requestQueue.add(arrayRequest);
        liste_Juniors.setAdapter(adapter);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnRetourJunior.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Juniors.this);
                //creation alertdialog

                Intent intent = new Intent(Juniors.this, MainActivity.class) ;
                startActivity (intent);
                //Parametrage des bouton ok ou annuler



                //fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();
            }
        });//fin du bouton





       /* String Titre = this.getIntent().getStringExtra("titre");
        TextView titreDemande = findViewById(R.id.titreDemande);
        titreDemande.setText(Titre);

        final ArrayAdapter<String> adapterList = new
                ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, joueursJuniors);

        ListView listJuniors = (ListView) findViewById(R.id.junior_listview);
        listJuniors.setAdapter(adapterList);

        listJuniors.setOnItemClickListener
                (
                new AdapterView.OnItemClickListener(){

                    public void OnItemClick (AdapterView<?> adapter, View arg1, int position, String arg3){
                        String titre = adapterList.getItem(position);
                        Toast toast = Toast.makeText(getApplicationContext(), "Position : "
                                + String.valueOf(position), Toast.LENGTH_SHORT);
                        toast.show();
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Titre : "
                                + titre, Toast.LENGTH_SHORT);
                        toast2.show();
                    }
                } );

        listJuniors.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> adapter, View arg1, int position, long args) {
                                String titre = adapterList.getItem(position);
                                Toast toast = Toast.makeText(getApplicationContext(), "Position : "
                                        + String. valueOf(position), Toast.LENGTH_SHORT);
                                toast.show();
                                Toast toast2 = Toast.makeText(getApplicationContext(), "Titre : "
                                        + titre, Toast. LENGTH_SHORT) ;
                                toast2.show();

                            }
                        } );*/
    }

    /*public String joueursJuniors [] =
            {
                    "9alouch",
                    "Bouzebal",
                    "Kiliminni",
                    "Wjeh khanez"
            };*/

}