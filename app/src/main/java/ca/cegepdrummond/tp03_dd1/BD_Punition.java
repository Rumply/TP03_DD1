package ca.cegepdrummond.tp03_dd1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 2016-10-31.
 */
public class BD_Punition {

    // Cette classe définit la structure et les opérations pour la table qui persiste la liste des punitions.
    // Elle contient aussi une sous-classe qui définit la structure et les opérations d'une punition.

    // Nom de la table
    public static final String TABLE_NAME = "punitions";

    // Nom des champs de la table
    public static final String TBL_FIELD_ID = "_id";   // Important que votre clé primaire se nomme _id
    public static final String TBL_FIELD_CODE = "code";
    public static final String TBL_FIELD_JOUEUR = "joueur";
    public static final String TBL_FIELD_TEMPS = "temps";

    // Définition de chacun des champs
    private static final String[] TBL_FIELD_ID_DEF = {TBL_FIELD_ID, "integer","primary key autoincrement"};
    private static final String[] TBL_FIELD_CODE_DEF = {TBL_FIELD_CODE,"text"};
    private static final String[] TBL_FIELD_JOUEUR_DEF = {TBL_FIELD_JOUEUR,"text"};
    private static final String[] TBL_FIELD_TEMPS_DEF = {TBL_FIELD_TEMPS,"integer"};

    // Commande pour créer la table dans la base de données
    public static final String CREATE_TABLE_CMD =
            String.format("create table %s (%s %s %s, %s %s %s)",
                    TABLE_NAME,
                    TBL_FIELD_ID_DEF[0],TBL_FIELD_ID_DEF[1],TBL_FIELD_ID_DEF[2],
                    TBL_FIELD_CODE_DEF[0], TBL_FIELD_CODE_DEF[1],
                    TBL_FIELD_JOUEUR_DEF[0],  TBL_FIELD_JOUEUR_DEF[1],
                    TBL_FIELD_TEMPS_DEF[0],  TBL_FIELD_TEMPS_DEF[1]
            );

    // Commande pour supprimer la table dans la base de données
    public static final String DROP_TABLE_CMD =
            String.format("drop table if exists %s", TABLE_NAME);

    // Constructeur de la classe
    public BD_Punition() {
        // Rien de spécial à faire.
    }

    // Base de données CRUD Operations

    public static long inserer(BDHelper dbHelper, PunitionArray punition) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(TBL_FIELD_CODE,punition.getCode());
        valeurs.put(TBL_FIELD_JOUEUR,punition.getJoueur());
        valeurs.put(TBL_FIELD_TEMPS,punition.getTemps());
        long rec_id = db.insert("punitions",null,valeurs);
        return rec_id; // Retourne le _id du nouvel enregistrement
    }

    public static int modifier(BDHelper dbHelper, PunitionArray punition) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valeurs = new ContentValues();
        valeurs.put(TBL_FIELD_CODE,punition.getCode());
        valeurs.put(TBL_FIELD_JOUEUR,punition.getJoueur());
        valeurs.put(TBL_FIELD_TEMPS,punition.getTemps());
        int rec_id = db.update(TABLE_NAME,
                valeurs,
                String.format("%s = ?", TBL_FIELD_ID),
                new String[]{String.valueOf(punition.getId())}
        );
        return rec_id;
    }

    public static void supprimer(BDHelper dbHelper, long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,String.format("%s = ?",TBL_FIELD_ID),new String[] {String.valueOf(id)});
    }

    // Retourne les enregistrement des joueurs à l'aide d'un curseur

    public static List<PunitionArray> recupererTousAsPunitionArray(BDHelper bdHelper) {
        List<PunitionArray> listePunition = new ArrayList<>();
        SQLiteDatabase bd = bdHelper.getReadableDatabase();
        String sql = String.format("select * from %s order by %s", TABLE_NAME, TBL_FIELD_ID);
        Cursor cursor = bd.rawQuery(sql, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                PunitionArray punition;
                do {
                    punition = new PunitionArray(
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_ID)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_CODE)),
                            cursor.getString(cursor.getColumnIndex(TBL_FIELD_JOUEUR)),
                            cursor.getInt(cursor.getColumnIndex(TBL_FIELD_TEMPS))
                    );
                    listePunition.add(punition);
                } while (cursor.moveToNext());
            }
        }
        return listePunition;
    }

    // Retourne la liste des titres des punitions sous forme de liste de string
    public static List<String> recupererTousAsArrayString(BDHelper dbHelper) {
        List<String> listePunitions = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = String.format("select %s from %s order by %s",TBL_FIELD_CODE, TABLE_NAME, TBL_FIELD_CODE);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    listePunitions.add(cursor.getString(cursor.getColumnIndex(TBL_FIELD_CODE)));
                } while (cursor.moveToNext());
            }
        }
        return listePunitions;
    }
}