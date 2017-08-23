package com.example.android1.placesearch;
import android.location.Location;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android1.placesearch.HomeActivity.current_lat;
import static com.example.android1.placesearch.HomeActivity.current_lng;

/**
 * Created by Android1 on 6/15/2017.
 */

public class PlaceAdapter extends ArrayAdapter<PlaceItem> {

    private final Context context;
    private final ArrayList<PlaceItem> itemsArrayList;

    public PlaceAdapter(Context context, ArrayList<PlaceItem> itemsArrayList) {
        super(context, R.layout.list_item, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView placename = (TextView) rowView.findViewById(R.id.placename);
        placename.setText(itemsArrayList.get(position).getPlacename());

        double lat=itemsArrayList.get(position).getLat();
        double lng=itemsArrayList.get(position).getLongi();

        TextView address = (TextView) rowView.findViewById(R.id.address);
        address.setText(itemsArrayList.get(position).getPlacevicinity());

        TextView dist=(TextView)rowView.findViewById(R.id.distance);
        dist.setText("Distance "+ itemsArrayList.get(position).getDistance()+" km");

        return rowView;
    }




}