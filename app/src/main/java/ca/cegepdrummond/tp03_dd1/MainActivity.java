package ca.cegepdrummond.tp03_dd1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BDHelper bdHelper;

    private TextView mViewTimer;
    private TextView mViewTimer1;
    private TextView mViewTimer2;
    private TextView mViewTimer3;
    private TextView mViewTimer4;
    private TextView mViewTimer5;
    private TextView mViewTimer6;
    private Chronometer mTimer;
    private TextView viewScoreVisitor;
    private TextView viewScoreHome;

    Button btn_menu;

    //DecompteTimer main_timer;

    private chronometre_v2 main_timer;
    private chronometre_v2 main_timer1;
    private chronometre_v2 main_timer2;
    private chronometre_v2 main_timer3;
    private chronometre_v2 main_timer4;
    private chronometre_v2 main_timer5;
    private chronometre_v2 main_timer6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        main_timer = new chronometre_v2();
        main_timer1 = new chronometre_v2();
        main_timer2 = new chronometre_v2();
        main_timer3 = new chronometre_v2();
        main_timer4 = new chronometre_v2();
        main_timer5 = new chronometre_v2();
        main_timer6 = new chronometre_v2();

        mViewTimer = (TextView) findViewById(R.id.timer_minute);
        main_timer.set_Timer(mViewTimer);

        mViewTimer1 = (TextView) findViewById(R.id.punition_visiteur_seconde_1);
        main_timer1.set_Timer(mViewTimer1);

        mViewTimer2 = (TextView) findViewById(R.id.punition_visiteur_seconde_2);
        main_timer2.set_Timer(mViewTimer2);

        mViewTimer3 = (TextView) findViewById(R.id.punition_visiteur_seconde_3);
        main_timer3.set_Timer(mViewTimer3);

        mViewTimer4 = (TextView) findViewById(R.id.punition_local_seconde_1);
        main_timer4.set_Timer(mViewTimer4);

        mViewTimer5 = (TextView) findViewById(R.id.punition_local_seconde_2);
        main_timer5.set_Timer(mViewTimer5);

        mViewTimer6 = (TextView) findViewById(R.id.punition_local_seconde_3);
        main_timer6.set_Timer(mViewTimer6);

        viewScoreVisitor = (TextView) findViewById(R.id.score_visitor);
        viewScoreVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusUnScore(viewScoreVisitor);
            }
        });

        viewScoreHome = (TextView) findViewById(R.id.score_home);
        viewScoreHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusUnScore(viewScoreHome);
            }
        });

        btn_menu = (Button) findViewById(R.id.button_menu_principal);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main_menu.class);
                startActivity(intent);
            }
        });

    }

    private void plusUnScore(TextView scoreEquipe) {
        String score = String.valueOf(scoreEquipe.getText());
        scoreEquipe.setText(String.valueOf(Integer.valueOf(score) + 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pause_global:
                // User chose the "Settings" item, show the app settings UI...
                pause_global();
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                break;
        }
        return true;
    }

    public void pause_global(){
        main_timer.toggle_chrono();
        main_timer1.toggle_chrono();
        main_timer2.toggle_chrono();
        main_timer3.toggle_chrono();
        main_timer4.toggle_chrono();
        main_timer5.toggle_chrono();
        main_timer6.toggle_chrono();
        Toast.makeText(this, "Clique sur pause global", Toast.LENGTH_SHORT).show();
    }

    private void partirTimer(int temps, boolean is_visible){
        main_timer.set_Time(temps * 1000);
        main_timer.set_Timer(main_timer.get_ViewTimer());
        main_timer.setup_timer();
        main_timer.start();

        main_timer1.set_Time(temps * 1000);
        main_timer1.set_Timer(main_timer1.get_ViewTimer());
        main_timer1.setup_timer();
        main_timer1.start();

        main_timer2.set_Time(temps * 1000);
        main_timer2.set_Timer(main_timer2.get_ViewTimer());
        main_timer2.setup_timer();
        main_timer2.start();

        main_timer3.set_Time(temps * 1000);
        main_timer3.set_Timer(main_timer3.get_ViewTimer());
        main_timer3.setup_timer();
        main_timer3.start();

        main_timer4.set_Time(temps * 1000);
        main_timer4.set_Timer(main_timer4.get_ViewTimer());
        main_timer4.setup_timer();
        main_timer4.start();

        main_timer5.set_Time(temps * 1000);
        main_timer5.set_Timer(main_timer5.get_ViewTimer());
        main_timer5.setup_timer();
        main_timer5.start();

        main_timer6.set_Time(temps * 1000);
        main_timer6.set_Timer(main_timer6.get_ViewTimer());
        main_timer6.setup_timer();
        main_timer6.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        partirTimer(77,true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
