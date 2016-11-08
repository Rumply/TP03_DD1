package ca.cegepdrummond.tp03_dd1;

/**
 * Created by Steve on 2016-10-31.
 */

public class PunitionArray {

    private int id;
    private String code;
    private String joueur;
    private int temps;

    public PunitionArray(int id, String code, String joueur, int temps) {
        this.id = id;
        this.code = code;
        this.joueur = joueur;
        this.temps = temps;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public String getCode() {return code;}

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}