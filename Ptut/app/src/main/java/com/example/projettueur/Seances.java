package com.example.projettueur;

import java.util.ArrayList;
import java.util.List;



public class Seances {




    private ArrayList<Exercices> listExercices;


    private String TitreSeances;


    public Seances(ArrayList<Exercices> newListExercices, String newTitresSeance) throws NullPointerException{
        this.listExercices = newListExercices;
        this.TitreSeances = newTitresSeance;
        if (newListExercices == null) {
            throw new NullPointerException("La liste d'exercices est vide");
        }
        if (this.TitreSeances == null) {
            throw new NullPointerException("Le titre est vide");
        }
    }



    public String getTitreSeances() {

        return this.TitreSeances;
    }


    public void setTitreSeances(String value) {

        this.TitreSeances = value;
    }


    public ArrayList<Exercices> getListExercices() {

        return this.listExercices;
    }


    public void setListExercices(ArrayList<Exercices> value) {

        this.listExercices = value;
    }




    public int getTempsMax() {
        int result = 0;
        for (int i = 0; i< listExercices.size(); i++) {

            result += listExercices.get(i).getDuree() * listExercices.get(i).getRepetition();
        }

        return result;
    }



	@Override
	public String toString() {
		return "Seances [TitreSeances=" + TitreSeances + ", getTitreSeances()=" + getTitreSeances() + ", getTempsMax()="
				+ getTempsMax() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
    
    

}
