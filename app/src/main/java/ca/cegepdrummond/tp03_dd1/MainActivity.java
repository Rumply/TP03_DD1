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
    private Chronometer mTimer;

    Button btn_menu;

    //DecompteTimer main_timer;

    private chronometre_v2 main_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        main_timer = new chronometre_v2();

        mViewTimer = (TextView) findViewById(R.id.timer_minute);
        main_timer.set_Timer(mViewTimer);

        btn_menu = (Button) findViewById(R.id.button_menu_principal);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main_menu.class);
                startActivity(intent);
            }
        });

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
        Toast.makeText(this, "Clique sur pause global", Toast.LENGTH_SHORT).show();
    }

    private void partirTimer(int temps, boolean is_visible){
        main_timer.set_Time(temps * 1000);
        main_timer.set_Timer(main_timer.get_ViewTimer());
        main_timer.setup_timer();
        main_timer.start();
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
