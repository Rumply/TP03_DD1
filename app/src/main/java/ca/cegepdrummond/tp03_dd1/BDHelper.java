package ca.cegepdrummond.tp03_dd1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guillaume on 2016-10-25.
 */

public class BDHelper extends SQLiteOpenHelper {

    private static final Integer DB_VERSION = 1;

    // Lorsqu'on crée un nouvel instance de cette classe à partir d'un activité, on passe l'activité en paramètre (donc new DBHelper(this))
    //
    public BDHelper(Context context, String DB_NAME) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_Joueur.CREATE_TABLE_CMD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BD_Joueur.DROP_TABLE_CMD);

        onCreate(db);
    }
}
