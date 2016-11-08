package ca.cegepdrummond.tp03_dd1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 2016-10-31.
 */
public class BD_Equipe {

    // Cette classe définit la structure et les opérations pour la table qui persiste la liste des équipes.
    // Elle contient aussi une sous-classe qui définit la structure et les opérations d'une équipe.

    // Nom de la table
    public static final String TABLE_NAME = "equipes";

    // Nom des champs de la table
    public static final String TBL_FIELD_ID = "_id";   // Important que votre clé primaire se nomme _id
    public static final String TBL_FIELD_NOM = "nom";
    public static final String TBL_FIELD_SCORE = "score";

    // Définition de chacun des champs
    private static final String[] TBL_FIELD_ID_DEF = {TBL_FIELD_ID, "integer","primary key autoincrement"};
    private static final String[] TBL_FIELD_NOM_DEF = {TBL_FIELD_NOM,"text"};
    private static final String[] TBL_FIELD_SCORE_DEF = {TBL_FIELD_SCORE,"integer"};

    // Commande pour créer la table dans la base de données
    public static final String CREATE_TABLE_CMD =
            String.format("create table %s (%s %s %s, %s %s %s)",
                    TABLE_NAME,
                    TBL_FIELD_ID_DEF[0],TBL_FIELD_ID_DEF[1],TBL_FIELD_ID_DEF[2],
                    TBL_FIELD_NOM_DEF[0], TBL_FIELD_NOM_DEF[1],
                    TBL_FIELD_SCORE_DEF[0],  TBL_FIELD_SCORE_DEF[1]
            );

    // Commande pour supprimer la table dans la base de données
    public static final String DROP_TABLE_CMD =
            String.format("drop table if exists %s", TABLE_NAME);

    // Constructeur de la classe
    public BD_Equipe() {
        // Rien de spécial à faire.
    }

    // Base de données CRUD Operations

    public static long inserer(BDHelper dbHelper, EquipeArray equipe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(TBL_FIELD_NOM,equipe.getNom());
        valeurs.put(TBL_FIELD_SCORE,equipe.getScore());
        long rec_id = db.insert("equipes",null,valeurs);
        return rec_id; // Retourne le _id du nouvel enregistrement
    }

    public static int modifier(BDHelper dbHelper, EquipeArray equipe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(TBL_FIELD_NOM,equipe.getNom());
        valeurs.put(TBL_FIELD_SCORE,equipe.getScore());
        int rec_id = db.update(TABLE_NAME,
                valeurs,
                String.format("%s = ?", TBL_FIELD_ID),
                new String[]{String.valueOf(equipe.getId())}
        );
        return rec_id;
    }

    public static void supprimer(BDHelper dbHelper, long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,String.format("%s = ?",TBL_FIELD_ID),new String[] {String.valueOf(id)});
    }

    // Retourne les enregistrement des équipes à l'aide d'un curseur

    public static List<EquipeArray> recupererTousAsEquipeArray(BDHelper bdHelper) {
        List<EquipeArray> listeEquipe = new ArrayList<>();
        SQLiteDatabase bd = bdHelper.getReadableDatabase();
        String sql = String.format("select * from %s order by %s", TABLE_NAME, TBL_FIELD_ID);
        Cursor cursor = bd.rawQuery(sql, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                EquipeArray equipe;
                do {
                    equipe = new EquipeArray(
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_ID)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_NOM)),
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_SCORE))
                    );
                    listeEquipe.add(equipe);
                } while (cursor.moveToNext());
            }
        }
        return listeEquipe;
    }

    // Retourne la liste des titres des equipes sous forme de liste de string
    public static List<String> recupererTousAsArrayString(BDHelper dbHelper) {
        List<String> listeEquipes = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = String.format("select %s from %s order by %s",TBL_FIELD_NOM, TABLE_NAME, TBL_FIELD_NOM);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    listeEquipes.add(cursor.getString(cursor.getColumnIndex(TBL_FIELD_NOM)));
                } while (cursor.moveToNext());
            }
        }
        return listeEquipes;
    }
}