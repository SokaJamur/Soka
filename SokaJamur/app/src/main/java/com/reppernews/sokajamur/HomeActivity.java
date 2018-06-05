package com.reppernews.sokajamur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.reppernews.sokajamur.app.AppController;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID_ARTIKEL = "id_artikel";
    public static final String TAG_GAMBAR = "gambar";
    public static final String TAG_JUDUL = "judul";
    public static final String URL_GET_ALL = "http://tifpolije16.com/tampilArtikel.php/";
    public static final String TAG = AppController.class.getSimpleName();
    private SliderLayout sliderLayout;
    ListView listView;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private AppController mInstance;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getJSON();
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Jamur", R.drawable.jamurtiram);
        file_maps.put("Menu Jamur 1", R.drawable.cripsyjamur);
        file_maps.put("Menu Jamur 2", R.drawable.tumisjamur);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))

                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);

        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_infoJamur) {
            Intent intent = new Intent(getApplicationContext(),InfoJamur.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_infoBaglog) {
            Intent intent = new Intent(getApplicationContext(),InfoBaglog.class);
            startActivity(intent);
}

        else if (id == R.id.nav_login) {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            finish();
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id_artikel = jo.getString(TAG_ID_ARTIKEL);
                String gambar ="http://tifpolije16.com/soka/assests/img/" +jo.getString(TAG_GAMBAR);
                String judul = jo.getString(TAG_JUDUL);
                HashMap<String,String> employees = new HashMap<>();
                employees.put(TAG_ID_ARTIKEL,id_artikel);
                employees.put(TAG_GAMBAR,gambar);
                employees.put(TAG_JUDUL,judul);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new MyAdapter(
                HomeActivity.this, list, R.layout.list_row,
                new String[]{TAG_GAMBAR,TAG_JUDUL},
                new int[]{R.id.gambar, R.id.judul});

        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HomeActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(HomeActivity.this, Artikel.class);
        HashMap<String,String> map=(HashMap)parent.getItemAtPosition(position);
        String id_artikel = map.get(TAG_ID_ARTIKEL).toString();
        intent.putExtra(TAG_ID_ARTIKEL,id_artikel);
        startActivity(intent);
    }

    public class MyAdapter extends SimpleAdapter {

            public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to){
                super(context, data, resource, from, to);
            }

            public View getView(int position, View convertView, ViewGroup parent){
                // here you let SimpleAdapter built the view normally.
                View v = super.getView(position, convertView, parent);

                // Then we get reference for Picasso
                ImageView img = (ImageView) v.getTag();
                if(img == null){
                    img = (ImageView) v.findViewById(R.id.gambar);
                    v.setTag(img); // <<< THIS LINE !!!!
                }
                // get the url from the data you passed to the `Map`
                Object url = ((Map)getItem(position)).get(TAG_GAMBAR);
                // do Picasso
                Picasso.with(v.getContext()).load((String) url).into(img);

                // return the view
                return v;

        }
    }

}
