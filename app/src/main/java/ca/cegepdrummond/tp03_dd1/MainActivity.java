package ca.cegepdrummond.tp03_dd1;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private BDHelper bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
