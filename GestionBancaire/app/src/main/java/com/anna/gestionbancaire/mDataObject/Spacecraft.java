package com.anna.gestionbancaire.mDataObject;

/**
 * Created by annaanjalli on 7/7/16.
 */

public class Spacecraft {

    String numCompte;
    String nomClient;
    int solde;
    int id;

    public Spacecraft() {
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
