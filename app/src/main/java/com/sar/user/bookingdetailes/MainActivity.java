package com.sar.user.bookingdetailes;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView,recyclerViewp;
    public RecyclerView.Adapter adapter,adapterp;
    public List<listitem> listitems;
    public List<listitem> listitemsp;
    private static final String URL_DATA = "https://api.myjson.com/bins/lelh4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        recyclerViewp=findViewById(R.id.recp);
        recyclerViewp.setHasFixedSize(true);
        recyclerViewp.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();
        listitemsp = new ArrayList<>();

        loadrecyview();
    }

    public void loadrecyview() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                adapter = new myadap(listitems, getApplicationContext());
                adapterp = new myadapp(listitemsp, getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerViewp.setAdapter(adapterp);
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArra = jsonObject.getJSONArray("hero");



                    for (int j = 0; j <= jsonArra.length(); j++) {
                        JSONObject p = jsonArra.getJSONObject(j);
                        listitem listitemk = new listitem(p.getString("name"),
                                p.getString("des"));
                        listitem listitemku = new listitem(p.getString("id"),
                                p.getString("amt"));

                        listitemsp.add(listitemku);

                        listitems.add(listitemk);
                    }





                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(stringRequest);


    }
}

