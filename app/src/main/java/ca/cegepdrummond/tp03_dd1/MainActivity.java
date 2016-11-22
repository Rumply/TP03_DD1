package ca.cegepdrummond.tp03_dd1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BDHelper bdHelper;

    private TextView mTimerMinute;
    private TextView mTimerSeconde;

    DecompteTimer main_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mTimerMinute = (TextView) findViewById(R.id.timer_minute);
        mTimerSeconde = (TextView) findViewById(R.id.timer_seconde);
    }


    private void partirTimer(int temps, boolean is_visible){
        main_timer = new DecompteTimer(temps,is_visible, mTimerMinute, mTimerSeconde);
        main_timer.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        partirTimer(128,true);
    }
}
