package com.reppernews.sokajamur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.reppernews.sokajamur.app.AppController;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import static com.reppernews.sokajamur.SplashScreen.TAG_ID;

public abstract class Artikel extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    ImageView thumb_image;
    TextView judul, isi;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    SwipeRefreshLayout swipeRefreshLayout;
    String ID_ARTIKEL;

    private static final String TAG = Artikel.class.getSimpleName();

    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID_ARTIKEL = "id_artikel";
    public static final String TAG_GAMBAR = "gambar";
    public static final String TAG_JUDUL = "judul";
    public static final String TAG_ISI = "isi";
    public static final String url_detail_artikel= "http://tifpolije16.com/tampilArtikel.php/";
    private SwipeRefreshLayout swipe;
    private String tag_json_obj;
    private String id_artikel;

}
