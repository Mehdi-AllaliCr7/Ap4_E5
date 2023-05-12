package com.example.ap_dvd;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------------------------------------------------------------------------
        // Gestion du bouton BtnPolicier associé 4 la ressource btnPolicier
        //---------------------------------------------------------------------------------
        Button btnJuniors = (Button) findViewById(R.id.btnJuniors);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnJuniors.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                //creation alertdialog
                //alertDialog.setTitle("Juniors");
                //alertDialog.setMessage("Voulez-vous choisir une categorie junior ?");

                //Parametrage des bouton ok ou annuler
                //alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    //public void onClick(DialogInterface argo, int argi) {
                        Intent intent = new Intent(MainActivity.this, Juniors.class) ;
                        startActivity (intent);

                    //}
               // });


                /*alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface argo, int argi) {
                        // Pas de traitement particulier
                    };
                });//fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();*/
            }
        });//fin du bouton

        //---------------------------------------------------------------------------------
        // Gestion du bouton BtnFiction associé a la ressource btnFiction
        //---------------------------------------------------------------------------------
        Button btnBenjamins = (Button) findViewById(R.id.btnBenjamins);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnBenjamins.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                //creation alertdialog
                //alertDialog.setTitle("Benjamins");
                //alertDialog.setMessage("Voulez-vous choisir la categorie Benjamin ?");

                //Parametrage des bouton ok ou annuler
                //alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                  //  public void onClick(DialogInterface argo, int argi) {
                        //Toast.makeText(getApplicationContext(), btnBenjamins.getText(), Toast.LENGTH_LONG).show();

                        //Lance L'activité qui affiche la fenétre Policier

                        Intent intent = new Intent(MainActivity.this, Benjamins.class) ;
                        startActivity (intent);


                 //   }
                //});

                /*alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface argo, int argi) {
                        // Pas de traitement particulier
                    };
                });//fin Listener
                //Affichage de l'alertdialog
                alertDialog.show();*/
            }
        });//fin du bouton

        //---------------------------------------------------------------------------------
        // Gestion du bouton BtnFiction associé a la ressource btnFiction
        //---------------------------------------------------------------------------------
        Button btnU21 = (Button) findViewById(R.id.btnU21);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnU21.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                //creation alertdialog

                        Intent intent = new Intent(MainActivity.this, U21.class) ;
                        intent.putExtra("idCat", 4 );
                        setResult(Activity.RESULT_OK,intent);
                        startActivity (intent);


            }
        });//fin du bouton

        //---------------------------------------------------------------------------------
        // Gestion du bouton BtnFiction associé a la ressource btnFiction
        //---------------------------------------------------------------------------------
        Button btnSeniors = (Button) findViewById(R.id.btnSeniors);
        //utilisation d'un Listener [interface de gestion d'événenents] pour récupérer L'interaction avec le bouton
        btnSeniors.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                //creation alertdialog
                Intent intent = new Intent(MainActivity.this, Seniors.class) ;
                intent.putExtra("idCat", 3 );
                setResult(Activity.RESULT_OK,intent);
                startActivity (intent);
            }
        });//fin du bouton





    }//fin oncreate

    ActivityResultLauncher<Intent> reservationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Toast.makeText(MainActivity.this, "Réservation confirmée", Toast.LENGTH_SHORT).show();
                    }
                }
            });



    ActivityResultLauncher<Intent> RechercheLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                    }
                }
            }
    );


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menugeneral, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.menuRechercher:
                Log.i("Ap_DVD", "Menu:Recherche un joueur");
                Intent intentRecherche = new Intent(MainActivity.this, Recherche.class);
                 RechercheLauncher.launch(intentRecherche) ;


            return true;

            case R.id.menuReserve:
                Log.i("Ap_DVD", "Menu:Reserver un joueur");


                Intent intentReserver = new Intent(MainActivity.this, Reservation.class) ;
                reservationLauncher. launch(intentReserver);
                return true;

            case R.id.menuMagasin:
                Log.i("Ap_DVD", "Menu:Magasin");
                return true;

            case R.id.menuPresentation:
                Log.i("Ap_DVD", "Menu:Presentation");
                return true;


        }
        return true;

    }

}





