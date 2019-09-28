package com.lrams.worknshare_tickettracking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Ticket> ticketList = new ArrayList<>();
    private TicketsAdapter mAdapter;
    private RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.

    //RecyclerView recyclerView;
    String baseUrl = "https://co-work-lrams.herokuapp.com";  // This is the API base URL
    String url;  // This will hold the full URL which will include the username entered in the etGitHubUser.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setSupportActionBar(toolbar);

        mAdapter = new TicketsAdapter(ticketList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        requestQueue = Volley.newRequestQueue(this);

        if(haveInternetConnection()) {
            getTicketList();
        } else {
            addToTicketList(new Ticket("Veuillez-vous connecter à internet.","Veuillez-vous connecter à internet.",":("));
        }

    }

    private void addToTicketList(Ticket ticket) {
        ticketList.add(ticket);
    }

    private boolean haveInternetConnection(){
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (network != null && network.isConnected());
    }

    private void getTicketList() {
        this.url = this.baseUrl + "/listTickets";

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, this.url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObj = response.getJSONObject(i);
                        String objet = jsonObj.optString("objet");
                        String description = jsonObj.optString("description");
                        String equipment = jsonObj.optString("equipment");
                        String status = jsonObj.optString("status");
                        String id_open_space = jsonObj.optString("id_open_space");
                        addToTicketList(new Ticket(objet, description, status));
                        Log.v("Volley", "Object Json Added.");
                    } catch (JSONException e) {
                        Log.e("Volley", "Invalid JSON Object.");
                    }

                }
                mAdapter.notifyDataSetChanged();

            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error Http Response");
            }
        });

        requestQueue.add(arrReq);
    }
}