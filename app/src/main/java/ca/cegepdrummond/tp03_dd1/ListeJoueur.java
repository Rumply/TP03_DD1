package ca.cegepdrummond.tp03_dd1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ListeJoueur extends Activity {

    private ListView mListViewJoueurs;
    private Cursor mJoueursList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On va chercher notre listView
        mListViewJoueurs = (ListView) this.findViewById(R.id.listViewJoueurs);
        // Charger notre layout pour l'entête (Header)
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.header_joueur, mListViewJoueurs, false);
        mListViewJoueurs.addHeaderView(header, null, false);
        // On va définir le code pour répondre au onItemClick du ListView
        mListViewJoueurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor joueurData;
                joueurData = (Cursor) adapterView.getItemAtPosition(i); // Ceci va nous retourner un Joueur en position i

                Integer joueurId = joueurData.getInt(joueurData.getColumnIndex(BD_Joueur.TBL_FIELD_ID));
                // Aller chercher les autres données de votre joueur pour votre message.

                String mot_nom = getResources().getString(R.string.nom_joueur);
                String nom = joueurData.getString(joueurData.getColumnIndex(BD_Joueur.TBL_FIELD_NOM));
                String mot_numero = getResources().getString(R.string.);
                String numero = joueurData.getString(joueurData.getColumnIndex(BD_Joueur.TBL_FIELD_NUMERO));
                String mot_buts = getResources().getString(R.string.buts);
                String buts = joueurData.getString(joueurData.getColumnIndex(BD_Joueur.TBL_FIELD_BUTS));
                String mot_equipe = getResources().getString(R.string.equipe);
                String equipe = joueurData.getString(joueurData.getColumnIndex(BD_Joueur.TBL_FIELD_EQUIPE));
            }
        });
    }

    @Override
    // onStart est appelé à chaque fois qu'on entre dans l'écran. La première fois où lors de retour à celui-ci.
    // Donc on rafraichit notre listView dans les 2 cas
    protected void onStart() {
        super.onStart();
        chargerJoueurs();
    }

    private void chargerJoueurs() {

        // Méthode qui charge les donnees de la table categories dans le listeView

        DBHelper db = new DBHelper(this);

        // ListView utilise un autre layout comme template pour afficher chacun des enregistrements (item_categorie.xml)

        // Nous allons donc identifier le nom des champs que nous voulons extraire de la table
        String[] articleColonnes = new String[] {Articles.TBL_FIELD_TITRE, Articles.TBL_FIELD_QUANTITE, Articles.TBL_FIELD_MAGASIN, Articles.TBL_FIELD_CATEGORIE, Articles.TBL_FIELD_SELECTION};
        // Et les Id des vues auxquelles qui seront liées aux champs de la table ci-dessus
        int[] viewColumns = new int[] {R.id.textViewArticle, R.id.textViewQuantite, R.id.textViewMagasin, R.id.textViewCategorie, R.id.checkBoxSelection};

        mArticlesList = Articles.recupererTousAsCursor(db); // On récupère toutes les données dans notre cursor (déclaré comme propriété globale de la classe.)
        // Les données de notre cursor doivent être transposées dans un adapter (dans notre cas un SimpleCursorAdapter)
        // avec comme paramètres d'initialisation, le layout qu'on utilise pour afficher chacun des enregistrement dans le liste view
        // les colonnes que l'on extrait de la table categories et les Id des view (contrôles) dans lesquels seront affichés ces données.
        SimpleCursorAdapter adapteurArticles = new SimpleCursorAdapter(this,R.layout.item_article, mArticlesList, articleColonnes, viewColumns,0);
        // On va faire notre propre liaison entre les données du curseur et les views dans notre layout item_article.xml
        adapteurArticles.setViewBinder(new ArticleViewBinder());
        // On assigne notre adapteur
        mListViewArticles.setAdapter(adapteurArticles);
    }


    public class ArticleViewBinder implements SimpleCursorAdapter.ViewBinder {
        // Utilisé juste par la Méthode 01

        @Override
        public boolean setViewValue(View view, Cursor cursor, int i) {
            if (cursor.getColumnIndex(Articles.TBL_FIELD_TITRE) == i) {
                ((TextView) view).setText(cursor.getString(i));
                return true;
            }
            else if (cursor.getColumnIndex(Articles.TBL_FIELD_QUANTITE) == i) {
                ((TextView) view).setText(String.valueOf(cursor.getInt(i)));
                return true;
            }
            else if (cursor.getColumnIndex(Articles.TBL_FIELD_MAGASIN) == i) {
                ((TextView) view).setText(cursor.getString(i));
                return true;
            }
            else if (cursor.getColumnIndex(Articles.TBL_FIELD_CATEGORIE) == i) {
                ((TextView) view).setText(cursor.getString(i));
                return true;
            }
            else if (cursor.getColumnIndex(Articles.TBL_FIELD_SELECTION) == i) {
                ((CheckBox) view).setChecked(cursor.getInt(i) == 1);
                return true;
            }
            else
                return false;
        }
    }


}
