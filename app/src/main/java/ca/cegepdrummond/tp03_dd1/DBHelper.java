package ca.cegepdrummond.tp03_dd1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by coupalm on 2015-11-16.
 */
public class DBHelper  extends SQLiteOpenHelper {

    private static final String DB_NAME = "Hockey.db";
    private static final Integer DB_VERSION = 1;

    // Lorsqu'on crée un nouvel instance de cette classe à partir d'un activité, on passe l'activité en paramètre (donc new DBHelper(this))
    //


    public DBHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création tables.

        db.execSQL(BD_Equipe.CREATE_TABLE_CMD);
        db.execSQL(BD_Joueur.CREATE_TABLE_CMD);
        db.execSQL(BD_Punition.CREATE_TABLE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BD_Equipe.DROP_TABLE_CMD);
        db.execSQL(BD_Joueur.DROP_TABLE_CMD);
        db.execSQL(BD_Punition.DROP_TABLE_CMD);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        //db.execSQL(Categories.DROP_TABLE_CMD);
        //db.execSQL(Articles.DROP_TABLE_CMD);
        //db.execSQL(Categories.CREATE_TABLE_CMD);
        //db.execSQL(Articles.CREATE_TABLE_CMD);
    }

}
