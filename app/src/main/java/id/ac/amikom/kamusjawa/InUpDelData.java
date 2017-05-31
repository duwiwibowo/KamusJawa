package id.ac.amikom.kamusjawa;

/**
 * Created by apple on 5/8/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InUpDelData extends AsyncTask<String,String,String> {
    ProgressDialog pg;

    // mengubungkan lokasi file php untuk menghubungkan ke database ( alamat ipv4 )
    String url = "http://172.20.10.5/kamusjawa/tambah.php";

    // membuat variabel
    Context context;
    JSONParser jsonParser;
    JSONObject jsonObject;
    String act;

    public void init(Context c,String ids, String indo, String jawa, String action){
        context = c;
        act = action;
        InUpDelData inUpDelData = this;
        inUpDelData.execute(indo,jawa,action,ids);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pg = new ProgressDialog(context);
        pg.setTitle("Menyimpan Data");
        pg.setMessage("Mohon Tunggu");
        pg.show();
    }

    @Override
    protected String doInBackground(String... params) {
        jsonParser = new JSONParser();
        jsonObject = null;

        String indo,jawa,aksi,sukses,id;
        sukses = "";
        indo = (String) params[0];
        jawa = (String) params[1];
        aksi = (String) params[2];
        id = (String) params[3];

        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("bIndo",indo));
        param.add(new BasicNameValuePair("bJawa",jawa));
        param.add(new BasicNameValuePair("action",aksi));
        param.add(new BasicNameValuePair("id",id));

        try {
            jsonObject = jsonParser.getJsonObject("POST",url,param);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int stat = jsonObject.getInt("status");
            if(stat == 1){
                sukses = "sukses";
            }else{
                sukses = "gagal";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sukses;
    }

    // menampilkan berhasil atau gagal
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s=="sukses"){
            if(act=="delete"){
                Toast.makeText(context,"Kata berhasil dihapus!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Kata berhasil disimpan!", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(act=="delete"){
                Toast.makeText(context,"Kata gagal dihapus!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Kata gagal disimpan!", Toast.LENGTH_SHORT).show();
            }
        }
        pg.dismiss();
    }
}

