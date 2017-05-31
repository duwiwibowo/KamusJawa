package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class NavAksaraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_aksara);

        // mengatur tool bar sesuai id dan memberi tombol kembali ke activity sebelumnya
        Toolbar ToolBarAtas = (Toolbar)findViewById(R.id.toolbar_dua);
        setSupportActionBar(ToolBarAtas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
