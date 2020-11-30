package com.example.hello;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;


public class NgoMyPetsActivity extends AppCompatActivity {
    private static final String TAG = "NgoMyPetsActivity";

    DatabaseHelper mDatabaseHelper;
    ArrayList<Pets> petsList;
    ListView mListView;
    Pets pets;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_my_pets);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);


        //populateListView();

        petsList = new ArrayList<>();
        Cursor data = mDatabaseHelper.getListContents();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(NgoMyPetsActivity.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else {
            int i = 0;
            while (data.moveToNext()) {                 //------make column changes here----------
                pets = new Pets(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5) , data.getBlob(6), data.getString(7));
                petsList.add(i, pets);                //-----------make column changes here-----------
                System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4)+ " " + data.getString(5) + " " + data.getBlob(6) + " " + data.getString(7));
                System.out.println(petsList.get(i).getPetName());
                i++;
            }

            ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter(this, R.layout.list_adapter_view, petsList);
            mListView = (ListView) findViewById(R.id.listView);
            mListView.setAdapter(adapter);
        }

    }
/*
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(NgoMyPetsActivity.this, EditPetFormActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }
*/
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}

////////////////////end




