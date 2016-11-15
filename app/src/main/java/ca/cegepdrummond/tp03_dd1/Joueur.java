package ca.cegepdrummond.tp03_dd1;

/**
 * Created by Steve on 15 nov..
 */

public class Joueur {

        // attributs de l'objet

        private Integer mId = 0;
        private String mNom = "";
        private Integer mNumero = 0;
        private Integer mBut = 0;
        private String mEquipe = "";

        // Constructeurs

        public Joueur(){
            // Constructeur vide. Il faut alors utiliser les setters pour initialiser les valeurs de l'objet.
        }

        public Joueur(Integer id, String nom, Integer numero, Integer but, String equipe) {
            mId = id;
            mNom = nom;
            mNumero = numero;
            mBut = but;
            mEquipe = equipe;
        }

        // Setters.
        // On ne donne pas accès directement aux propriétés de l'objet. On utilise les setters
        // pour affecter des valeurs aux attributs. Ceci permet d'effectuer, entre autre, des validations.

        public void setId(Integer id){this.mId = id;}
        public void setTitre(String nom){ this.mNom = nom;}
        public void setNumero(Integer numero){ this.mNumero = numero;}
        public void setBut(Integer but){ this.mBut = but;}
        public void setEquipe(String equipe){ this.mEquipe = equipe;}

        // Getters

        public Integer getId() {return this.mId;}
        public String getNom() {return this.mNom;}
        public Integer getNumero() {return this.mNumero;}
        public Integer getBut() {return this.mBut;}
        public String getEquipe() {return this.mEquipe;}
}
