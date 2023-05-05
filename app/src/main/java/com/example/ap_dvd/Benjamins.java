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

import com.example.ap_dvd.pickjoueur.JoueurAdapter;
import com.example.ap_dvd.pickjoueur.JoueurModele;

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


        ListView ListeBenjamins = (ListView) findViewById(R.id. benjamins_listview);
        JoueurAdapter adapterJoueur = new JoueurAdapter(this, R.layout. ligne);


       try {
            XmlPullParser xmlPullParser = getResources().getXml(R.xml.liste_benjamins);

           // while(xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT){
                if(xmlPullParser.getEventType()== XmlPullParser.START_TAG){
                    JoueurModele unBenjamin = new JoueurModele();
                    unBenjamin.setNomJoueur(xmlPullParser.getAttributeValue(3));
                    unBenjamin.setPrenomJoueur(xmlPullParser.getAttributeValue(0));
                    unBenjamin.setAge(xmlPullParser.getAttributeValue(2));
                    unBenjamin.setNumero(Integer.parseInt(xmlPullParser.getAttributeValue(2)));
                    int resId = getResources().getIdentifier(xmlPullParser.getAttributeValue(1), "drawable", getPackageName());
                    unBenjamin.setImg(resId);

                   adapterJoueur.add(unBenjamin);
                }
            //}
            xmlPullParser.next();

        }catch (Exception e){
            Log.i("locDVD", "Erreurs trouvées" + e.getMessage());
            e.printStackTrace();
        }
        ListeBenjamins.setAdapter(adapterJoueur);

    }

}