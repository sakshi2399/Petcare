package com.example.hello;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_pets extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_pets,container,false);
            FloatingActionButton button1 = (FloatingActionButton) view.findViewById(R.id.fab);
                    button1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent in =new Intent(getActivity(),AddPetFormActivity.class);
                            in.putExtra("some","some data");
                            startActivity(in);
                        }
                    });
                    return view;
    }

}

