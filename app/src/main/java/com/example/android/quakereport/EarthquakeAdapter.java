package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(currentEarthquake.getMagnitude());

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
