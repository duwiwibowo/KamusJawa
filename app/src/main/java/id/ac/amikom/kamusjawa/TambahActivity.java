package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */


import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import id.ac.amikom.kamusjawa.AmbilData.JSONObjectResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TambahActivity extends AppCompatActivity {
    ArrayList<EntitasKamus> listKamus = new ArrayList<EntitasKamus>();

    // membuat variabel
    EntitasKamus kamus;
    int count = 0;
    ListView lv;
    Button btSimpan;
    EditText bIndo, bJawa;

    // mengubungkan lokasi file php untuk menghubungkan ke database ( alamat ipv4 )
    String url = "http://172.20.10.5/kamusjawa/kamus.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        // mengatur tool bar sesuai id dan memberi tombol kembali ke activity sebelumnya
        Toolbar ToolBarAtas = (Toolbar)findViewById(R.id.toolbar_empat);
        setSupportActionBar(ToolBarAtas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // deklarasi variabel
        lv = (ListView) findViewById(R.id.listView1);
        bIndo = (EditText) findViewById(R.id.addIndo);
        bJawa = (EditText) findViewById(R.id.addJawa);

        btSimpan = (Button) findViewById(R.id.bSimpan);

        loadKamus();

        // memberi aksi tombol simpan
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InUpDelData inUpDelData = new InUpDelData();
                inUpDelData.init(TambahActivity.this,"",bIndo.getText().toString(), bJawa.getText().toString(), "insert");
                loadKamus();
                bIndo.setText("");
                bJawa.setText("");

            }
        });

    }

    // mengambil data dari database
    public void loadKamus(){
        Bundle b = this.getIntent().getExtras();
        String var = b.getString("var");
        final AmbilData ambilData = new AmbilData();
        ambilData.init(var,url,TambahActivity.this,jres);
    }

    // deklarasi abstrak class dengan json
    public JSONObjectResult jres = new JSONObjectResult() {
        @Override
        public void gotJSONObject(JSONObject jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("kamusjawa");
                for(int i=0; i < jsonArray.length();i++){
                    kamus = new EntitasKamus();
                    kamus.setId(jsonArray.getJSONObject(i).getString("id"));
                    kamus.setIndo(jsonArray.getJSONObject(i).getString("indonesia"));
                    kamus.setJawa(jsonArray.getJSONObject(i).getString("jawa"));

                    listKamus.add(kamus);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(count>0){
                listKamus.clear();
                KamusBaseAdapter kamusBaseAdapter = new KamusBaseAdapter(TambahActivity.this,listKamus);
                lv.setAdapter(kamusBaseAdapter);
                loadKamus();
                count = -1;
            }else{
                KamusBaseAdapter kamusBaseAdapter = new KamusBaseAdapter(TambahActivity.this,listKamus);
                lv.setAdapter(kamusBaseAdapter);
            }

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    String ids,indo,jawa;
                    ids = listKamus.get(position).getId();
                    indo = listKamus.get(position).getIndo();
                    jawa = listKamus.get(position).getJawa();
                    dialogOption(ids,indo,jawa);
                    return false;
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

            count +=1;
        }
    };

    // dialog pilih aksi
    public void dialogOption(final String id, final String indo, final String jawa){
        final Dialog dlg = new Dialog(this);
        dlg.setContentView(R.layout.dialog_option);
        dlg.setTitle("Pilih Aksi");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dlg.getWindow().getAttributes());
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dlg.getWindow().setAttributes(lp);

        Button update,delete;
        update = (Button) dlg.findViewById(R.id.opUpdate);
        delete = (Button) dlg.findViewById(R.id.opDelete);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(id, indo, jawa);
                dlg.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(id);
                dlg.dismiss();
            }
        });

        dlg.show();
    }

    // dialog update
    public void dialogUpdate(final String id, String indo, String jawa){
        final Dialog dialog = new Dialog(TambahActivity.this);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.setTitle("");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

        final EditText edIndo,edJawa;

        edIndo = (EditText) dialog.findViewById(R.id.edIndo);
        edJawa = (EditText) dialog.findViewById(R.id.edJawa);

        Button btUpdate, btBack;

        edIndo.setText(indo);
        edJawa.setText(jawa);


        btUpdate = (Button) dialog.findViewById(R.id.btUpdate);


        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InUpDelData inUpDelData = new InUpDelData();
                inUpDelData.init(TambahActivity.this, id, edIndo.getText().toString(), edJawa.getText().toString(), "update");
                dialog.dismiss();
                loadKamus();
            }
        });


        dialog.show();
    }

    // dialog delete
    public void dialogDelete(final String id){
        final Dialog dlg = new Dialog(this);
        dlg.setContentView(R.layout.dialog_delete);
        dlg.setTitle("Apakah kamu yakin?");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dlg.getWindow().getAttributes());
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dlg.getWindow().setAttributes(lp);

        Button btYa, btTidak;

        btYa = (Button) dlg.findViewById(R.id.btYa);
        btTidak = (Button) dlg.findViewById(R.id.btTidak);

        btYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
                InUpDelData inUpDelData = new InUpDelData();
                inUpDelData.init(TambahActivity.this,id,"","","delete");

                loadKamus();
            }
        });

        btTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        dlg.show();
    }
}
