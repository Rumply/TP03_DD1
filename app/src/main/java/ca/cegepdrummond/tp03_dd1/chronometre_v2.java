package ca.cegepdrummond.tp03_dd1;

import android.content.Context;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Guillaume on 2016-11-26.
 */

public class chronometre_v2{

    private DecompteTimer timer;

    /**
     * TextView utilisé par le chronomètre.
     */
    private TextView mTemps;

    private boolean mVisible;

    private String error = "";

    private long temps;

    private boolean is_pause = false;

    private boolean is_stop = true;

    private boolean is_working = false;


    /**
     * Cette méthode permet de faire pause au chronomètre.
     */
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

    /**
     * Cette méthode permet de partir chronomètre.
     */
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

    /**
     * Cette méthode permet de start le chronomètre.
     */
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

    /**
     * Cette méthode permet de mettre en pause et en marche le chronomètre.
     */
    public void toggle_chrono(){
        if (is_pause){
            resumeTimer();
        }else if (is_working){
            pause();
        }
    }

    /**
     * Cette méthode permet de reset le chronomètre à 0.
     */
    public void reset(){
        if (!is_stop){
            is_stop = true;
            is_pause = false;
            is_working = false;
            timer.cancel();
            temps = 0;
        }
    }

    /**
     * Cette fonction permet de set le TextView utilisé par le chronomètre.
     *
     * @param timerView TextView utilisé par le chronomètre.
     */
    public void set_Timer(TextView timerView){
        mTemps = timerView;
    }

    /**
     * Cette fonction retourne si il y a un message d'erreur dans le chronomètre.
     *
     * @return boolean Si il y a une erreur ou non.
     */
    public boolean had_error(){
        return !(Objects.equals(error, ""));
    }

    public String get_error(){
        return error;
    }
    /**
     * Cette fonction permet de setter le temps pour le chronomètre.
     *
     * @param milliseconde long Le temps que l'on veut setter le chronomètre.
     */
    public void set_Time(long milliseconde){
        temps = milliseconde;
    }

    /**
     * @return Le TextView utilisé par le chronomètre.
     */
    public TextView get_ViewTimer(){
        return mTemps;
    }

    /**
     * Permet de setup le chronomètre.
     *
     * @param time Temps en seconde voulu.
     * @param mTextTemps TextView utilisé par le chronomètre.
     */
    public void setup_timer(int time, TextView mTextTemps){
        timer = new DecompteTimer( time, mTextTemps, mVisible);
    }

    /**
     * Permet de setup le chronomètre avec les valeur fournie manuellement avant.
     * Bugs si rien le temps, la view n'ont pas été fournie.
     *
     */
    public void setup_timer(){
        timer = new DecompteTimer( temps, get_ViewTimer(), true);
    }


    /**
     * Cette fonction retourne un valeur indiquant la visibilité du chronomètre.
     *
     * @return boolean Retourne si le chronomètre est visible ou non.
     */
    public boolean is_visible(){
        if (timer != null){
            return timer.is_visible();
        }else{
            return false;
        }
    }

    /**
     * Cette fonction permet de savoir si le chronomètre est en pause ou non.
     *
     * @return boolean Retourne si le chronomètre est en pause ou non.
     */
    public boolean get_is_pause(){
        return is_pause;
    }

    /**
     * Cette fonction permet de savoir si le chronomètre est arrêté ou non.
     *
     * @return boolean Retourne si le chronomètre est arrêté ou non.
     */
    public boolean get_is_stop(){
        return is_stop;
    }

    /**
     * Cette fonction permet de savoir si le chronomètre est en marche ou non.
     *
     * @return boolean Retourne si le chronomètre est en marche ou non.
     */
    public boolean get_is_working(){
        return is_working;
    }

}
