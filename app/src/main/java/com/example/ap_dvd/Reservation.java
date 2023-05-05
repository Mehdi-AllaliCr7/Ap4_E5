package com.example.ap_dvd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Reservation extends AppCompatActivity {
public Button btnConfirmer;
public Button btnAnnuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        btnConfirmer = findViewById(R.id.btnConfirmer);
        btnConfirmer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        btnAnnuler = findViewById(R.id.btnAnnuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setResult(Activity.RESULT_CANCELED);
                finish();
                Toast.makeText(Reservation.this, "Réservation Annulé", Toast.LENGTH_SHORT).show();

            }
        });




        //boutton retour

        Button btnRetourRes = (Button) findViewById(R.id.btnRetourRes);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnRetourRes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Reservation.this);
                //creation alertdialog

                Intent intent = new Intent(Reservation.this, MainActivity.class) ;
                startActivity (intent);
                //Parametrage des bouton ok ou annuler



                //fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();
            }
        });//fin du bouton

    }
}