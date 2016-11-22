package ca.cegepdrummond.tp03_dd1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 2016-10-25.
 */
public class BD_Joueur {

    // Cette classe définit la structure et les opérations pour la table qui persiste la liste des joueurs.
    // Elle contient aussi une sous-classe qui définit la structure et les opérations d'un joueur.

    // Nom de la table
    public static final String TABLE_NAME = "joueurs";

    // Nom des champs de la table
    public static final String TBL_FIELD_ID = "_id";   // Important que votre clé primaire se nomme _id
    public static final String TBL_FIELD_NOM = "nom";
    public static final String TBL_FIELD_NUMERO = "numero";
    public static final String TBL_FIELD_BUTS = "buts";
    public static final String TBL_FIELD_EQUIPE = "equipe";

    // Définition de chacun des champs
    private static final String[] TBL_FIELD_ID_DEF = {TBL_FIELD_ID, "integer","primary key autoincrement"};
    private static final String[] TBL_FIELD_NOM_DEF = {TBL_FIELD_NOM,"text"};
    private static final String[] TBL_FIELD_NUMERO_DEF = {TBL_FIELD_NUMERO,"integer"};
    private static final String[] TBL_FIELD_BUTS_DEF = {TBL_FIELD_BUTS,"integer"};
    private static final String[] TBL_FIELD_EQUIPE_DEF = {TBL_FIELD_EQUIPE,"text"};

    // Commande pour créer la table dans la base de données
    public static final String CREATE_TABLE_CMD =
            String.format("create table %s (%s %s %s, %s %s %s)",
                    TABLE_NAME,
                    TBL_FIELD_ID_DEF[0],TBL_FIELD_ID_DEF[1],TBL_FIELD_ID_DEF[2],
                    TBL_FIELD_NOM_DEF[0], TBL_FIELD_NOM_DEF[1],
                    TBL_FIELD_NUMERO_DEF[0],  TBL_FIELD_NUMERO_DEF[1],
                    TBL_FIELD_BUTS_DEF[0],  TBL_FIELD_BUTS_DEF[1],
                    TBL_FIELD_EQUIPE_DEF[0],  TBL_FIELD_EQUIPE_DEF[1]
                    );

    // Commande pour supprimer la table dans la base de données
    public static final String DROP_TABLE_CMD =
            String.format("drop table if exists %s", TABLE_NAME);

    // Constructeur de la classe
    public BD_Joueur() {
        // Rien de spécial à faire.
    }

    // Base de données CRUD Operations

    public static long inserer(BDHelper dbHelper, JoueurArray joueur) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(TBL_FIELD_NOM,joueur.getNom());
        valeurs.put(TBL_FIELD_NUMERO,joueur.getNumero());
        valeurs.put(TBL_FIELD_BUTS,joueur.getButs());
        valeurs.put(TBL_FIELD_EQUIPE,joueur.getEquipe());
        long rec_id = db.insert("joueurs",null,valeurs);
        return rec_id; // Retourne le _id du nouvel enregistrement
    }

    public static int modifier(BDHelper dbHelper, JoueurArray joueur) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(TBL_FIELD_NOM,joueur.getNom());
        valeurs.put(TBL_FIELD_NUMERO,joueur.getNumero());
        valeurs.put(TBL_FIELD_BUTS,joueur.getButs());
        valeurs.put(TBL_FIELD_EQUIPE,joueur.getEquipe());
        int rec_id = db.update(TABLE_NAME,
                valeurs,
                String.format("%s = ?", TBL_FIELD_ID),
                new String[]{String.valueOf(joueur.getId())}
        );
        return rec_id;
    }

    public static void supprimer(BDHelper dbHelper, long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,String.format("%s = ?",TBL_FIELD_ID),new String[] {String.valueOf(id)});
    }

    // Retourne les enregistrement des joueurs à l'aide d'un curseur

    public static List<JoueurArray> recupererTousAsJoueurArray(BDHelper bdHelper) {
        List<JoueurArray> listeJoueur = new ArrayList<>();
        SQLiteDatabase bd = bdHelper.getReadableDatabase();
        String sql = String.format("select * from %s order by %s", TABLE_NAME, TBL_FIELD_ID);
        Cursor cursor = bd.rawQuery(sql, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                JoueurArray joueur;
                do {
                    joueur = new JoueurArray(
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_ID)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_NOM)),
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_NUMERO)),
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_BUTS)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_EQUIPE))
                    );
                    listeJoueur.add(joueur);
                } while (cursor.moveToNext());
            }
        }
        return listeJoueur;
    }

    public static List<JoueurArray> recupererTousAsJoueurArray(BDHelper bdHelper, String colonne, String valeur) {
        List<JoueurArray> listeJoueur = new ArrayList<>();
        SQLiteDatabase bd = bdHelper.getReadableDatabase();
        String sql = String.format("select * from %s where %s='%s' order by %s", TABLE_NAME, colonne, valeur, TBL_FIELD_ID);
        Cursor cursor = bd.rawQuery(sql, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                JoueurArray joueur;
                do {
                    joueur = new JoueurArray(
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_ID)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_NOM)),
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_NUMERO)),
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_BUTS)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_EQUIPE))
                    );
                    listeJoueur.add(joueur);
                } while (cursor.moveToNext());
            }
        }
        return listeJoueur;
    }

    // Retourne la liste des titres des joueurs sous forme de liste de string
    public static List<String> recupererTousAsArrayString(BDHelper dbHelper) {
        List<String> listeJoueurs = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = String.format("select %s from %s order by %s",TBL_FIELD_NOM, TABLE_NAME, TBL_FIELD_NOM);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    listeJoueurs.add(cursor.getString(cursor.getColumnIndex(TBL_FIELD_NOM)));
                } while (cursor.moveToNext());
            }
        }
        return listeJoueurs;
    }

    public static Cursor recupererTousAsCursor(DBHelper dbHelper) {
        Cursor cursor;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery(String.format("select * from %s order by %s",TABLE_NAME, TBL_FIELD_NOM),null);
        cursor.moveToFirst();
        return cursor;
    }
}
