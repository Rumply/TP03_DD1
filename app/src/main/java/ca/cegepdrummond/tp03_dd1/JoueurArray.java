package ca.cegepdrummond.tp03_dd1;

/**
 * Created by Steve on 2016-10-25.
 */

public class JoueurArray {

    private int id;
    private String nom;
    private int numero;
    private int buts;
    private String equipe;

    public JoueurArray(int id, String nom, int numero, int buts, String equipe) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.buts = buts;
        this.equipe = equipe;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(int buts) {
        this.equipe = equipe;
    }

    public int getButs() {
        return buts;
    }

    public void setButs(int buts) {
        this.buts = buts;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
