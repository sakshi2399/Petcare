package com.example.hello;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    TextView SHOPS,PETS,VETS;
    ViewPager viewPager;
    PagerViewAdapter PagerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        SHOPS=(TextView)findViewById(R.id.SHOPS);
        PETS=(TextView)findViewById(R.id.PETS);
        VETS=(TextView)findViewById(R.id.VETS);
        viewPager=(ViewPager)findViewById(R.id.fragment_container);
        PagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(PagerViewAdapter);

        SHOPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);

            }
        });

        PETS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewPager.setCurrentItem(1);
            }
        });
        VETS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
                ;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onChangeTab(int position){
        if(position==0){
            SHOPS.setTextSize(31);
            SHOPS.setTextColor(getColor(R.color.black));
            PETS.setTextSize(29);
            PETS.setTextColor(getColor(R.color.white));
            VETS.setTextSize(29);
            VETS.setTextColor(getColor(R.color.white));


        }
        if(position==1){
            SHOPS.setTextSize(29);
            SHOPS.setTextColor(getColor(R.color.white));
            PETS.setTextSize(31);
            PETS.setTextColor(getColor(R.color.black));
            VETS.setTextSize(29);
            VETS.setTextColor(getColor(R.color.white));
        }
        if(position==2){
            SHOPS.setTextSize(29);
            SHOPS.setTextColor(getColor(R.color.white));
            PETS.setTextSize(29);
            PETS.setTextColor(getColor(R.color.white));
            VETS.setTextSize(31);
            VETS.setTextColor(getColor(R.color.black));
        }

    }




}