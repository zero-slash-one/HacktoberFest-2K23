package com.example.earthquake_finder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context,0,earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //setting the magnitude
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());

        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        magnitudeView.setText(formattedMagnitude);

        //setting the place
        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;

        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //setting the primary location
        TextView primaryLocationView = listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        //setting the location_offset
        TextView locationOffsetView = listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        //setting the date
        Date dateObject = new Date(currentEarthquake.getTimeInMillisecond());

        TextView DateView = listItemView.findViewById(R.id.date);
        String formatDate = formatDate(dateObject);
        DateView.setText(formatDate);

        //setting the time
        TextView TimeView = listItemView.findViewById(R.id.time);
        String formatTime = formatTime(dateObject);
        TimeView.setText(formatTime);

        return listItemView;
    }

    public String formatMagnitude(double magnitude){
        DecimalFormat formatter = new DecimalFormat("0.0");
        return  formatter.format(magnitude);
    }

    public String formatDate(Date dateObject){
        SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy");
        return date.format(dateObject);
    }

    public String formatTime(Date dateObject){
        SimpleDateFormat time = new SimpleDateFormat("h:mm a");
        return time.format(dateObject);
    }

    public int getMagnitudeColor(double Magnitude){
        int magnitudeColorResourceId = 0;
        int MagnitudeFloor = (int)Math.floor(Magnitude);

        switch(MagnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            case 10:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
