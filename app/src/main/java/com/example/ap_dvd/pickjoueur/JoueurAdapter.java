package com.example.ap_dvd.pickjoueur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ap_dvd.R;

public class JoueurAdapter extends ArrayAdapter {


    public JoueurAdapter(Context context, int textViewRessourceId){
        super(context, textViewRessourceId);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View Result = convertView;
        if(convertView == null) {
            Result = LayoutInflater.from(getContext()).inflate(R.layout.ligne, parent, false);
        }
        JoueurModele joueur = (JoueurModele) getItem(position);

        TextView nom = (TextView) Result.findViewById(R.id.nom);
        nom.setText(joueur.getNomJoueur());

        TextView prenom = (TextView) Result.findViewById(R.id.prenom);
        prenom.setText(joueur.getPrenomJoueur());

        TextView poste = (TextView) Result.findViewById(R.id.poste);
        poste.setText(joueur.getPoste());

       // TextView numero = (TextView) Result.findViewById(R.id.numero);
        //numero.setText(joueur.getNumero());

        ImageView img = (ImageView) Result.findViewById(R.id.ligne_img);
        img.setImageResource(joueur.getImg());

        return Result;
    }

    public void updateData() {this.notifyDataSetChanged();}

}
