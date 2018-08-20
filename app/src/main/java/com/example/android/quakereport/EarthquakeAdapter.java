package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private String offset;
    private String locaton;
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    public String dateFormat(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("mmm dd, yyyy");
        String displayDate = dateFormatter.format(date);
        return displayDate;
    }

     public String timeFormat(Date date) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
        String displayTime = timeFormatter.format(date);
        return displayTime;
    }

    public String magnitudeFormat(double mag) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.0");
        String displayMagnitude = decimalFormatter.format(mag);
        return displayMagnitude;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorId;
        int mag = (int) Math.floor(magnitude);
        switch (mag) {
            case 0:
            case 1:
                magnitudeColorId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorId = R.color.magnitude9;
                break;
            default:
                magnitudeColorId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        String magnitude = magnitudeFormat(currentEarthquake.getMagnitude());
        magnitudeView.setText(magnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String place = currentEarthquake.getLocation();

        boolean hasOffset = place.contains(LOCATION_SEPARATOR);
        if (hasOffset) {
            String[] parts = place.split(LOCATION_SEPARATOR);
            offset = parts[0] + LOCATION_SEPARATOR;
            locaton = parts[1];
        } else {
            offset = getContext().getString(R.string.near_the);
            locaton = place;
        }
        TextView offsetView = (TextView) listItemView.findViewById(R.id.offset);
        offsetView.setText(offset);
        TextView locationView = (TextView) listItemView.findViewById(R.id.location);
        locationView.setText(locaton);

        Date date = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String displayDate = dateFormat(date);
        dateView.setText(displayDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String displayTime = timeFormat(date);
        timeView.setText(displayTime);

        return listItemView;
    }
}
