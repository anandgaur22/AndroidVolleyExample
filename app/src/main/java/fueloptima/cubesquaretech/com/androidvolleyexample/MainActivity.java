package fueloptima.cubesquaretech.com.androidvolleyexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Creating a List of PumpDetail
    private List<PumpDetail> mRecyclerViewItems;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        mRecyclerViewItems = new ArrayList<>();

        //Calling method to get data
        getData();
    }

    //This method will get data from the web api
    private void getData(){
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        parseData(response);
                        Log.d("data", "onResponse: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("data", "onResponse: "+error);
                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array){
        Log.d("data", "onResponse: "+array);
        for(int i = 0; i<array.length(); i++) {
            PumpDetail pumpDetail = new PumpDetail();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                pumpDetail.setName(json.getString(Config.TAG_NAME));
                pumpDetail.setAddress(json.getString(Config.TAG_ADDRESS));
                pumpDetail.setDistance(json.getString(Config.TAG_DISTANCE));
                pumpDetail.setPetrolprice(json.getString(Config.TAG_PETROL_PRICE));
                pumpDetail.setDieselprice(json.getString(Config.TAG_DIESEL_PRICE));

                ArrayList<String> powers = new ArrayList<String>();

//                JSONArray jsonArray = json.getJSONArray(Config.TAG_POWERS);
//
//                for(int j = 0; j<jsonArray.length(); j++){
//                    powers.add(((String) jsonArray.get(j))+"\n");
//                }
//                pumpDetail.setPowers(powers);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            mRecyclerViewItems.add(pumpDetail);
        }

        //Finally initializing our adapter
        adapter = new PetrolPumpRecyleviewAdapter(this,mRecyclerViewItems);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
