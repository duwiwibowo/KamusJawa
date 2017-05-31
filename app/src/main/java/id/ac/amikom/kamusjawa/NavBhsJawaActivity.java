package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.view.View;

public class NavBhsJawaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bhsjawa);

        // mengatur tool bar sesuai id dan memberi tombol kembali ke activity sebelumnya
        Toolbar ToolBarAtas = (Toolbar)findViewById(R.id.toolbar_satu);
        setSupportActionBar(ToolBarAtas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // memberi aksi tombol ke link website
        Button button = (Button)findViewById(R.id.bNavBjawa);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://id.wikipedia.org/wiki/Bahasa_Jawa"));
                startActivity(intent);
            }
        });

    }
}
