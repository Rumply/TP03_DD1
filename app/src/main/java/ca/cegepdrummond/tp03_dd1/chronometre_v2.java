package ca.cegepdrummond.tp03_dd1;

import android.content.Context;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Guillaume on 2016-11-26.
 */

public class chronometre_v2{

    private DecompteTimer timer;

    private TextView mTemps;
    private boolean mVisible;

    private String error = "";

    private long temps;

    private boolean is_pause = false;

    private boolean is_stop = true;

    private boolean is_working = false;

    public chronometre_v2(){

    }

    public void pause(){
        if (is_working){
            if (!is_stop && !is_pause){
                is_working = false;
                is_pause = true;
                mVisible = timer.is_visible();
                temps = timer.get_millisUntilFinished();
                timer.cancel();
            }
        }
    }

    public void resumeTimer(){
        if (is_pause){
            if (!is_stop && !is_working){
                is_pause = false;
                is_working = true;
                timer = new DecompteTimer(temps,mTemps, mVisible);
                timer.start();
            }
        }
    }

    public void start(){
        if (is_stop){
            if (!is_pause && !is_working){
                is_stop = false;
                is_working = true;
                timer = new DecompteTimer(temps, mTemps, mVisible);
                timer.start();
            }
        }
    }

    public void toggle_chrono(){
        if (is_pause){
            resumeTimer();
        }else if (is_working){
            pause();
        }
    }

    public void reset(){
        if (!is_stop){
            is_stop = true;
            is_pause = false;
            is_working = false;
            timer.cancel();
            temps = 0;
        }
    }

    public void set_Timer(TextView timerView){
        mTemps = timerView;
    }

    public boolean had_error(){
        return !(Objects.equals(error, ""));
    }

    public void set_Time(long milliseconde){
        temps = milliseconde;
    }

    public TextView get_ViewTimer(){
        return mTemps;
    }

    public void setup_timer(String temps, boolean invisible){

    }

    public void setup_timer(boolean invisible){
        timer = new DecompteTimer( temps, get_ViewTimer(), invisible);
    }

    public void setup_timer(int time, TextView mTextTemps){
        timer = new DecompteTimer( time, mTextTemps, mVisible);
    }

    public void setup_timer(){
        timer = new DecompteTimer( temps, get_ViewTimer(), true);
    }

    public boolean is_visible(){
        if (timer != null){
            return timer.is_visible();
        }else{
            return false;
        }
    }

    public boolean get_is_pause(){
        return is_pause;
    }

    public boolean get_is_stop(){
        return is_stop;
    }

    public boolean get_is_working(){
        return is_working;
    }

}
