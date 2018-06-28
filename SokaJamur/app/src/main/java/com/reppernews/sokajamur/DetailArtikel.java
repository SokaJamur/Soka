package com.reppernews.sokajamur;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.reppernews.sokajamur.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.reppernews.sokajamur.HomeActivity.TAG_GAMBAR;
import static com.reppernews.sokajamur.HomeActivity.TAG_ID_ARTIKEL;
import static com.reppernews.sokajamur.PesananSaya.TAG_ID_PESAN;

public class DetailArtikel extends AppCompatActivity {
    TextView txtJudul, txtIsi;
    ImageView gambar;
    private String url = Server.URL + "detailArtikel.php";
    String tag_json_obj = "json_obj_req";
    public static final String TAG_ID_ARTIKEL2 = "id_artikel";
    public static final String TAG_JUDUL= "judul";
    public static final String TAG_ISI= "isi";
    public static final String TAG_GAMBAR= "gambar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        txtJudul = (TextView) findViewById(R.id.judul);
        txtIsi = (TextView) findViewById(R.id.isi);
        gambar = (ImageView) findViewById(R.id.gambar);
        Bundle b = getIntent().getExtras();
        String id_artikel = b.getString(TAG_ID_ARTIKEL);
        Toast.makeText(getApplicationContext(), "ini id artikel" + id_artikel, Toast.LENGTH_LONG).show();
        detailartikel(id_artikel);

    }

    public void detailartikel(final String id_artikel) {
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jObj = new JSONObject(response);
//                    String id_artikel = jObj.getString(TAG_ID_ARTIKEL2);
                    String judul = jObj.getString(TAG_JUDUL);
                    String isi = jObj.getString(TAG_ISI);
                    txtJudul.setText(judul);
                    txtIsi.setText(isi);

                    new DetailArtikel.DownLoadImageTask(gambar).execute(jObj.getString(TAG_GAMBAR));
                    //String nama = jObj.get(TAG_ID_NAMA);



                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idartikel", id_artikel);
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        finish();
        startActivity(intent);
    }
    //--------------------Load Image View dengan URL ------------------------------
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
