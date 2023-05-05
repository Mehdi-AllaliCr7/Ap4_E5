package com.example.ap_dvd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Recherche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

// Association des attributs privés & La vue
        edtCherche = findViewById(R.id.edtRecherche) ;
        btnCherche = findViewById(R.id.btnRecherche) ;

        //listener pour tester que l'on reçoit bien la donnée
        btnCherche.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // Toast toast = Toast.makeText(getApplicationContext(), edtCherche.getText(), Toast.LENGTH_SHORT);
               // toast.show();

                Intent intent = new Intent (Recherche.this, Juniors.class) ;
                intent.putExtra("titre", edtCherche.getText().toString());
                setResult (Activity.RESULT_OK, intent);

                finish();

            }
        });
    }

    private Button btnCherche;
    private EditText edtCherche;

}

