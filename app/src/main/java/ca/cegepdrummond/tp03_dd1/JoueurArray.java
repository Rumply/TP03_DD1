package ca.cegepdrummond.tp03_dd1;

/**
 * Created by Guillaume on 2016-10-25.
 */

public class JoueurArray {

    private int id;
    private String nom;
    private int numero;

    public JoueurArray(int id, String nom, int numero) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
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
