package com.example.projettueur;


public class Exercices {

    private String nom;


    private int duree;


    private int repetition;


    private int puissance;


    private float frequence_pedalage;

    private int totalRep;


    public Exercices(String nom, int duree, int repetition, int puissance, float frequence_pedalage) {
        this.nom = nom;
        this.duree = duree;
        this.repetition = repetition;
        this.puissance = puissance;
        this.frequence_pedalage = frequence_pedalage;
        this.totalRep = repetition;
    }


    int getDuree() {

        return this.duree;
    }

    public int getTotalRep() {
        return this.totalRep;
    }

    public void setTotalRep(int totalRep) {
        this.totalRep = totalRep;
    }


    void setDuree(int value) {

        this.duree = value;
    }


    int getRepetition() {

        return this.repetition;
    }


    void setRepetition(int value) {

        this.repetition = value;
    }


    String getNom() {

        return this.nom;
    }


    void setNom(String value) {

        this.nom = value;
    }


    int getPuissance() {

        return this.puissance;
    }


    void setPuissance(int value) {

        this.puissance = value;
    }


    float getFrequence_pedalage() {

        return this.frequence_pedalage;
    }


    void setFrequence_pedalage(float value) {

        this.frequence_pedalage = value;
    }

    @Override
    public String toString() {
        return "Exercices [nom=" + nom + ", duree=" + duree + ", repetition=" + repetition + ", puissance=" + puissance
                + ", frequence_pedalage=" + frequence_pedalage + ", getDuree()=" + getDuree() + ", getRepetition()="
                + getRepetition() + ", getNom()=" + getNom() + ", getPuissance()=" + getPuissance()
                + ", getFrequence_pedalage()=" + getFrequence_pedalage() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
