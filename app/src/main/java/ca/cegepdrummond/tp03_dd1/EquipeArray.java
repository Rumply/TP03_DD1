package ca.cegepdrummond.tp03_dd1;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Guillaume on 2016-10-25.
 */

public class EquipeArray {

    private BDHelper bdHelper;

    private int id;
    private String nom;
    private int score;

    public EquipeArray(int id, String nom, int score) {
        this.id = id;
        this.nom = nom;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int numero) {
        this.score = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}