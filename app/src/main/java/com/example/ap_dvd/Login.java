package com.example.ap_dvd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edt_Email;
    EditText edt_Pswd;
    Button btn_cnx;
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Email = (EditText) findViewById(R.id.edt_Email);
        edt_Pswd = (EditText) findViewById(R.id.edt_pwd);
        btn_cnx = (Button) findViewById(R.id.btn_connexion);
        btn_reg = (Button) findViewById(R.id.btn_inscription);


        btn_cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edt_Email.getText().toString();
                String motDePasse = edt_Pswd.getText().toString();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);




                if (Email.isEmpty() || motDePasse.isEmpty()) {

                    Toast.makeText(Login.this, "Saisie manquante, veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();


                } else {
                    // Connexion réussie
                    Intent intent = new Intent(Login.this, MainActivity.class) ;
                    startActivity (intent);
                    Toast.makeText(Login.this, "Bienvenue, " + Email + " !", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this, Register.class) ;
                startActivity (intent);
            }
        });


    }

    public void loginUser() {
        final String email = edt_Email.getText().toString().trim();

        final String pwd = edt_Pswd.getText().toString().trim();

        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                C.LOGIN_URL,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError erreur) {

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                params.put("pwd", pwd);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this) ;

        requestQueue.add(stringrequest);




    }
    public void onErrorResponse(VolleyError erreur){
        Toast.makeText(getApplicationContext(),erreur.getMessage(), Toast .LENGTH_LONG).show();
    }

    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.i("Test ajout", response);
            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}