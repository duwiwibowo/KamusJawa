package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EntitasKamus kamus = new EntitasKamus();
    ArrayList<EntitasKamus> listKamus = new ArrayList<EntitasKamus>();

    // variabel
    EditText indo;
    TextView jawa;
    Button btTrans;

    // mengubungkan lokasi file php untuk menghubungkan ke database ( alamat ipv4 )
    String url = "http://172.20.10.5/kamusjawa/kamus.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // deklarasi variabel berdasarkan id
        indo = (EditText) findViewById(R.id.input);
        jawa = (TextView) findViewById(R.id.output);
        btTrans = (Button) findViewById(R.id.bSearch);

        // menghapus text
        Button reset = (Button) findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                indo.setText("");
                jawa.setText("Jawa");
            }
        });

        // memberi aksi tombol cari
        btTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilData ambilData = new AmbilData();
                ambilData.init(indo.getText().toString(),url,MainActivity.this,jres);
                indo.setText(indo.getText());
            }
        });

        // membuat fab dan snackbar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tambah Kosakata", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(MainActivity.this, TambahActivity.class);
                                Bundle b = new Bundle();
                                b.putString("var","kosong");
                                in.putExtras(b);
                                startActivity(in);
                            }
                        })
                        .setDuration(1500)
                        .setActionTextColor(getResources().getColor(R.color.pink))
                        .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // mengambil data dari database dengan json
    AmbilData.JSONObjectResult jres = new AmbilData.JSONObjectResult() {
        @Override
        public void gotJSONObject(JSONObject jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("kamusjawa");
                for(int i=0; i<jsonArray.length(); i++){
                    jawa.setText(jsonArray.getJSONObject(i).getString("jawa"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    // navigasi drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        // menghubungkan activity di nav drawer
        if (id == R.id.nav_bhsjawa) {
            Intent intentku= new Intent(MainActivity.this, NavBhsJawaActivity.class);
            startActivity(intentku);
        } else if (id == R.id.nav_aksara) {
            Intent intentku= new Intent(MainActivity.this, NavAksaraActivity.class);
            startActivity(intentku);
        } else if (id == R.id.nav_tentang) {
            Intent intentku= new Intent(MainActivity.this, NavTentangActivity.class);
            startActivity(intentku);
        } else if (id == R.id.nav_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setTitle("Keluar");
            builder.setMessage("Apakah Kamu Ingin Keluar Dari Aplikasi ?");
            builder.setNegativeButton("Tidak",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Log.e("info", "Tidak");
                        }
                    });
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MainActivity.this.finish();
                }
            });
            builder.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // dialog exit ketika tombol kembali ditekan
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah Kamu Ingin Keluar Dari Aplikasi ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
