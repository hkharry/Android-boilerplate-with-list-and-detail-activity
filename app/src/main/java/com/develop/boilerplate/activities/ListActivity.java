package com.develop.boilerplate.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.develop.boilerplate.R;
import com.develop.boilerplate.adapter.ItemAdapter;
import com.develop.boilerplate.model.Item;
import com.develop.boilerplate.network.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        itemList =new ArrayList<>();

        activity = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);

        addDummydata();

        String url = "http://192.168.1.8:8000/list/";
        getList(activity, url);
        setAdapter();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }





    //get data from server if required
    public void getList(Context context, String url)
    {
        final String[] result = new String[1];

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response",response.toString());
                        result[0] =response.toString();
                        handleResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("responseerror",error.toString());
                result[0] =error.toString();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(req);

    }

    public void handleResponse(String responce)
    {
        try {
            JSONArray jsonArray=new JSONArray(responce);
            for (int i=0;i<jsonArray.length();i++)
            {
                String object=jsonArray.getString(i);
                Item item =jsonToObject(object);
                itemList.add(item); //get data from server if required

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    private Item jsonToObject(String response) {
        final ObjectMapper mapper = new ObjectMapper();
        Item item = new Item();
        try {

            item = mapper.readValue(response, Item.class);

        } catch (IOException e) {
            e.printStackTrace();

        }

        return item;
    }



    private void addDummydata()
    {
        final Item item1=new Item();
        item1.setName("name1");
        item1.setAddress("address1");

        final Item item2=new Item();
        item2.setName("name2");
        item2.setAddress("address2");

        final Item item3=new Item();
        item3.setName("name3");
        item3.setAddress("address3");

        final Item item4=new Item();
        item4.setName("name1");
        item4.setAddress("address1");

        final Item item5=new Item();
        item5.setName("name5");
        item5.setAddress("address5");

        final Item item6=new Item();
        item6.setName("name6");
        item6.setAddress("address6");

        final Item item7=new Item();
        item7.setName("name7");
        item7.setAddress("address7");

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);
        itemList.add(item7);
    }

    private void setAdapter()
    {
        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
        adapter.SetOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Intent intent =new Intent(activity,DetailActivity.class);
                startActivity(intent);
            }
        });
    }

}



