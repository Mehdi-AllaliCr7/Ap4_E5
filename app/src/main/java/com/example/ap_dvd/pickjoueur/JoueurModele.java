package com.example.ap_dvd.pickjoueur;

import android.renderscript.ScriptIntrinsicYuvToRGB;

public class JoueurModele {
    int idAdherent;
    String nomJoueur;
    String prenomJoueur;
    String Age;
    String Poste;
    int numero;
    int img;

    public JoueurModele(){
    }
    public int getIdAdherent(){return idAdherent;}
    public void setIdAdherent(int idAdherent){this.idAdherent = idAdherent;}

    public String getNomJoueur(){return nomJoueur;}
    public void setNomJoueur(String nomJoueur){this.nomJoueur = nomJoueur;}

    public String getPrenomJoueur(){return prenomJoueur;}
    public void setPrenomJoueur(String prenomJoueur){this.prenomJoueur = prenomJoueur;}

    public String getAge(){return Age;}
    public void setAge(String Age){this.Age = Age;}

    public String getPoste(){return Poste;}
    public void setPoste(String Poste){this.Poste = Poste;}

    public int getNumero(){return numero;}
    public void setNumero(int numero){this.numero = numero;}

    public int getImg(){return img;}
    public void setImg(int img){this.img = img;}
}
