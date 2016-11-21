package ca.cegepdrummond.tp03_dd1;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Guillaume on 2016-10-18.
 * Modified by Steve on 2016-11-15.
 */

public class DecompteTimer extends CountDownTimer {

    private int secondsUntilFinished;
    private TextView mTextView_countDown_minute;
    private TextView mTextView_countDown_seconde;

    private boolean is_visible;
    private boolean is_timerFini;

    public DecompteTimer(int seconde, boolean is_visible, TextView coundDown_minute, TextView coundDown_seconde) {
        super(seconde*1000, 1000);
        this.is_visible = is_visible;
        mTextView_countDown_minute = coundDown_minute;
        mTextView_countDown_seconde = coundDown_seconde;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        int secondsTemp = Math.round(millisUntilFinished / 1000);
        if (secondsUntilFinished != secondsTemp){
            secondsUntilFinished = secondsTemp;
            if (is_visible){
                set_Time(secondsUntilFinished);
            }
        }
    }

    @Override
    public void onFinish() {
        mTextView_countDown_seconde.setText("0");
        is_timerFini = true;
    }

    private void set_Time(long time){
        if (is_visible)
            mTextView_countDown_seconde.setText(String.valueOf(time % 60));
            mTextView_countDown_minute.setText(String.valueOf((int) Math.floor(time / 60)));
    }

    public boolean is_visible() {
        return is_visible;
    }

    public void setIs_visible(boolean is_visible) {
        this.is_visible = is_visible;
    }

    public boolean is_timerFini() {
        return is_timerFini;
    }

    public void setIs_timerFini(boolean is_timerFini) {
        this.is_timerFini = is_timerFini;
    }

    public TextView get_mTextTimer() {
        return mTextView_countDown_seconde;
    }

    public void set_mTextTimer(TextView mTextTimer) {
        this.mTextView_countDown_seconde = mTextTimer;
    }

}
