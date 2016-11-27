package ca.cegepdrummond.tp03_dd1;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Guillaume on 2016-10-18.
 * Modified by Steve on 2016-11-15.
 */

public class DecompteTimer extends CountDownTimer {

    private long millisUntilFinished;

    public long get_millisUntilFinished(){
        return millisUntilFinished;
    }

    private TextView mTextView_countDown;

    private void set_TextTimer(){
        String text = "";
        text = text + seconde;
        if (minute > 0){
            text = String.format(Locale.CANADA_FRENCH,"%1$02d:%2$02d", minute,seconde);
            //text = minute + "," + text;
        }else{
            text = String.format(Locale.CANADA_FRENCH,"%1$02d:%2$s", seconde,String.valueOf(milli).substring(0,1));
            //text = text + ":" + milli;
        }
        mTextView_countDown.setText(text);
    }

    private boolean is_visible = true;
    private boolean is_timerFini;

    private int milli;
    private int seconde;
    private int minute;

    public DecompteTimer(int seconde, TextView mTextTemps, boolean is_visible) {
        super(seconde*1000, 1);
        this.is_visible = is_visible;
        mTextView_countDown = mTextTemps;
    }

    public DecompteTimer(long millis, TextView mTextTemps, boolean is_visible) {
        super(millis, 1);
        this.is_visible = is_visible;
        mTextView_countDown = mTextTemps;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
        milli = (int) this.millisUntilFinished % 1000;
        seconde = Math.round((this.millisUntilFinished / 1000) % 60);
        minute = (int) Math.floor((millisUntilFinished/1000) / 60);
        set_TextTimer();

    }

    @Override
    public void onFinish() {
        is_timerFini = true;
    }

    public boolean is_visible() {
        return is_visible;
    }

    public void set_is_visible(boolean is_visible) {
        this.is_visible = is_visible;
    }

    public boolean is_timerFini() {
        return is_timerFini;
    }

    public void setIs_timerFini(boolean is_timerFini) {
        this.is_timerFini = is_timerFini;
    }

}
