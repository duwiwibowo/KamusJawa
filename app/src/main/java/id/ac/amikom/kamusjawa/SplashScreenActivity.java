package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

public class SplashScreenActivity extends AppCompatActivity {

    // mengatur waktu lama splashscreen
    private static int LamaTampilSplash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // mengatur jendela activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */

        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // menghubungkan activity
                Intent bukaapp = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(bukaapp);

                // jeda setelah splashscren

                this.selesai();
            }

            private void selesai(){
                //auto
            }
        },LamaTampilSplash);

    };
}
