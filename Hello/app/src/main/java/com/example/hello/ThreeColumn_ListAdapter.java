package com.example.hello;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ThreeColumn_ListAdapter extends ArrayAdapter<Pets> {

    private Context context;
    private  int layout;

    private LayoutInflater mInflater;
    private ArrayList<Pets> pets1;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Pets> pets1) {
        super(context, textViewResourceId, pets1);

        this.context = context;
        this.layout = layout;

        this.pets1 = pets1;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }



    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = mInflater.inflate(mViewResourceId, null);

        Pets pets = pets1.get(position);

        if (pets != null) {
            TextView petName = (TextView) convertView.findViewById(R.id.textPetName);
            TextView petSpecies = (TextView) convertView.findViewById(R.id.textPetSpecies);
            TextView petBreeds = (TextView) convertView.findViewById(R.id.textPetBreeds);
            TextView petBirthDate = (TextView) convertView.findViewById(R.id.textPetBirthDate);           //---add columns here---
            TextView petGender = (TextView) convertView.findViewById(R.id.textPetGender);
            TextView petPhone = (TextView) convertView.findViewById(R.id.textPetPhone);

            ImageView petImage = (ImageView) convertView.findViewById(R.id.picturePet);


            if (petName != null) {
                petName.setText(pets.getPetName());
            }
            if (petSpecies != null) {
                petSpecies.setText((pets.getPetSpecies()));
            }
            if (petBreeds != null) {
                petBreeds.setText((pets.getPetBreeds()));
            }
            if (petBirthDate != null) {
                petBirthDate.setText((pets.getPetBirthDate()));                  //---add columns here---
            }
            if (petGender != null) {
                petGender.setText((pets.getPetGender()));                  //---add columns here---
            }
            if (petImage != null) {
                byte[] image = pets.getImage();
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                petImage.setImageBitmap(bitmap);
            }
            if (petPhone != null) {
                petPhone.setText((pets.getPetPhone()));                  //---add columns here---
            }
        }

        return convertView;
    }
}
///////////////end
