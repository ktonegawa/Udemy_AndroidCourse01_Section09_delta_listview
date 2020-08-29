package com.delta.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceAdapter extends ArrayAdapter<Place> {

    Context mContext;
    int mLayoutResourceId;
    Place mData[] = null;

    public PlaceAdapter(@NonNull Context context, int resource, @NonNull Place[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }

    @Nullable
    @Override
    public Place getItem(int position) {
        return super.getItem(position);
    }

//    // Custom ListView
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View row = convertView;
//        // infiltrate the layout for a single row
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        row = inflater.inflate(mLayoutResourceId, parent, false);
//
//        //get a reference to the different view elements we wish to update
//        TextView nameView = (TextView) row.findViewById(R.id.nameTextView);
//        TextView zipView = (TextView) row.findViewById((R.id.zipcodeTextView));
//        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
//
//        // get the data from the data array
//        Place place = mData[position];
//
//        //setting the view  to reflect the data we need to display
//        nameView.setText(place.mNameOfPlace);
//        zipView.setText(String.valueOf(place.mZipCode)); // converting from int to string
//
//        int resId = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable", mContext.getPackageName());
//        imageView.setImageResource(resId);
//
//        //returning the row view (because this is called getView after all)
//        return row;
//
//    }

    // Smooth ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PlaceHolder holder = null;
        // if we currently dont have a row View to reuse...
        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new PlaceHolder();

            holder.nameView = (TextView) row.findViewById(R.id.nameTextView);
            holder.zipView = (TextView) row.findViewById(R.id.zipcodeTextView);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(holder);
        }else{
            // otherwise use an existing one
            holder = (PlaceHolder) row.getTag();
        }

        // get the data from the data array
        Place place = mData[position];

        // setup and resuse same listener for each row
        holder.imageView.setOnClickListener(PopupListener);
        Integer rowPosition = position;
        holder.imageView.setTag(rowPosition);

        //setting the view  to reflect the data we need to display
        holder.nameView.setText(place.mNameOfPlace);
        holder.zipView.setText(String.valueOf(place.mZipCode)); // converting from int to string

        // get the image
        int resId = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable", mContext.getPackageName());
        holder.imageView.setImageResource(resId);

        //returning the row view (because this is called getView after all)
        return row;

    }

    View.OnClickListener PopupListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Integer viewPosition = (Integer) view.getTag();
            Place p = mData[viewPosition];
            Toast.makeText(getContext(), p.mPopup, Toast.LENGTH_SHORT).show();
        }
    };

    private static class PlaceHolder {
        TextView nameView;
        TextView zipView;
        ImageView imageView;
    }
}
