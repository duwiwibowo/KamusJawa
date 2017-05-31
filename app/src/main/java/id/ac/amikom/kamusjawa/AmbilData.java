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
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmbilData extends AsyncTask<Object,Object,Object> {
    JSONParser jsonParser;
    JSONObject jsonObject;
    ProgressDialog pg;
    Context context;
    JSONObjectResult jres;
    public void init(String var,String url,Context con, JSONObjectResult jsonObjectResult){
        context = con;
        jres = jsonObjectResult;
        AmbilData ambilData = this;
        ambilData.execute(var,url);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pg = new ProgressDialog(context);
        pg.setTitle("Loading");
        pg.setMessage("Mohon Tunggu");
        pg.show();
    }

    @Override
    protected Object doInBackground(Object... params) {
        jsonObject = null;
        jsonParser = new JSONParser();

        String bIndo = (String) params[0];
        String urls = (String) params[1];

        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("bIndo",bIndo));

        try {
            jsonObject = jsonParser.getJsonObject("POST",urls,param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if(result != null){

            JSONObject jObj = (JSONObject) result;
            jres.gotJSONObject(jObj);
        }else{
            Toast.makeText(context, "Kosakata tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
        pg.dismiss();
    }

    public static abstract class JSONObjectResult{
        public abstract void gotJSONObject(JSONObject jsonObject);
    }
}

