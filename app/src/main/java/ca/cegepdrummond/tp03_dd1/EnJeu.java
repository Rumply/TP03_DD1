package ca.cegepdrummond.tp03_dd1;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Steve on 15 nov..
 */

public class EnJeu {

    protected TextView mTextTimer;
    protected CountDownTimer timer;
    protected boolean is_timerFini;

    public EnJeu() {
        mTextTimer = (TextView) mTextTimer.findViewById(R.id.timer_minute);
    }

    public void partirTimer(int temps, boolean is_visible){
        timer = new DecompteTimer(temps,is_visible,mTextTimer);
        timer.start();
    }
}
