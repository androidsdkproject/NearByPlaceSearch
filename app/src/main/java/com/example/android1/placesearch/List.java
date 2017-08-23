package com.example.android1.placesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android1.placesearch.HomeActivity.current_lat;
import static com.example.android1.placesearch.HomeActivity.current_lng;
import static com.example.android1.placesearch.HomeActivity.db;
import static com.google.android.gms.internal.zzagz.runOnUiThread;

public class List extends Fragment {


    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_list, container, false);



        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.pink, R.color.indigo, R.color.lime);

        swipeRefreshLayout.setOnRefreshListener(new  SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });



        listView = (ListView) view.findViewById(R.id.listView);

        ArrayList<PlaceItem> loclist = (ArrayList<PlaceItem>) db.getAllPlace();



       final PlaceAdapter adapter = new PlaceAdapter(getActivity(),loclist);

       listView.setAdapter(adapter);



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent in=new Intent(getActivity(),RouteActivity.class);
               PlaceItem item = (PlaceItem)parent.getItemAtPosition(position);
               in.putExtra("current_lat",current_lat);
               in.putExtra("current_lng",current_lng);
               in.putExtra("place_name",item.getPlacename());
               in.putExtra("lat",item.getLat());
               in.putExtra("lng",item.getLongi());
               startActivity(in);
           }
       });

        return view;
    }



    private void refreshData() {
        Log.d("Reading: ", "Reading all Locations");
        ArrayList<PlaceItem> loclist = (ArrayList<PlaceItem>) db.getAllPlace();
        PlaceAdapter adapter = new PlaceAdapter(getActivity(), loclist);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }




}