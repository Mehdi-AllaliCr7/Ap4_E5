package com.example.ap_dvd;



import androidx.annotation.Nullable;
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

public class Register extends AppCompatActivity {


    Button btn_Valider;

    EditText edt_Email_Register;
    EditText edt_Pswd_Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_Valider = (Button) findViewById(R.id.btn_register);
        edt_Email_Register = (EditText) findViewById(R.id.edt_Email_Reg);
        edt_Pswd_Register = (EditText) findViewById(R.id.edt_pwd_reg);

        btn_Valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Register.this, Login.class) ;
                startActivity (intent);
            }
        });
    } // fin on create

    public void registerUser() {
        final String email = edt_Email_Register.getText().toString().trim();

        final String pwd = edt_Pswd_Register.getText().toString().trim();

        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                C.REGISTER_URL,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("Test ajout", response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            //Toast.makeText(Register.this, response.toString(), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError erreur) {

                        Toast.makeText(getApplicationContext(),erreur.getMessage(), Toast .LENGTH_LONG).show();
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









}