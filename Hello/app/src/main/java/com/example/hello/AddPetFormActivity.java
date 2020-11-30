package com.example.hello;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
//import android.icu.util.Calendar;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;


public class AddPetFormActivity extends AppCompatActivity {

    private static final String TAG = "AddPetFormActivity";

    DatabaseHelper mDatabaseHelper;
    Button addpetbutton, viewpetbutton;
    ImageButton imageDate;
    FloatingActionButton cameraButton;
    ImageView petImage;
    EditText petNameInputText,petSpeciesInputText,petBreedsInputText,petBirthDateInputText,petPhoneInputText;       ////add input variables here
    Calendar calendar;
    int year,month,day;

    final int REQUEST_CODE_GALLERY = 999;

    private static RadioGroup radio_g;
    private static RadioButton radio_b;


    //--------------------------------RADIO BUTTON CODE-----------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//--------------------------------RADIO BUTTON CODE-----------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_form);



        petNameInputText = (EditText) findViewById(R.id.petNameInputText);
        petSpeciesInputText = (EditText) findViewById(R.id.petSpeciesInputText);
        petBreedsInputText = (EditText) findViewById(R.id.petBreedsInputText);       //// add new variables here
        petBirthDateInputText = (EditText) findViewById(R.id.petBirthDateInputText);
        petPhoneInputText = (EditText) findViewById(R.id.petPhoneInputText);

        addpetbutton = (Button) findViewById(R.id.addpetbutton);
        viewpetbutton = (Button) findViewById(R.id.viewpetbutton);
        imageDate = (ImageButton) findViewById(R.id.imageDate);

        petImage = (ImageView) findViewById(R.id.petImage);
        cameraButton = (FloatingActionButton) findViewById(R.id.cameraButton);

        mDatabaseHelper = new DatabaseHelper(this);

        radio_g = (RadioGroup)findViewById(R.id.rg_genders);
        int selected_id = radio_g.getCheckedRadioButtonId();
        radio_b = (RadioButton)findViewById(selected_id);

//--------------------------------CALENDAR CODE-----------------------
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //accessing EditText and Button
        petBirthDateInputText=(EditText)findViewById(R.id.petBirthDateInputText);
        imageDate=(ImageButton)findViewById(R.id.imageDate);

        imageDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPetFormActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        //sets date in EditText
                        petBirthDateInputText.setText(dayOfMonth+"-"+ (month+1) +"-"+year);
                    }
                }, year, month, day);
                //shows DatePickerDialog
                datePickerDialog.show();
            }
        });

        petBirthDateInputText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPetFormActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        //sets date in EditText
                        petBirthDateInputText.setText(dayOfMonth+"-"+ (month+1) +"-"+year);
                    }
                }, year, month, day);
                //shows DatePickerDialog
                datePickerDialog.show();
            }
        });

//--------------------------------CALENDAR CODE-----------------------

//--------------------------------IMAGE GALLERY CODE-----------------------

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddPetFormActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

//--------------------------------IMAGE GALLERY CODE-----------------------


        addpetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pName = petNameInputText.getText().toString();
                String pSpecies = petSpeciesInputText.getText().toString();       //// add new variables here
                String pBreeds = petBreedsInputText.getText().toString();       //// add new variables here
                String pBirthDate = petBirthDateInputText.getText().toString();
                String pPhone = petPhoneInputText.getText().toString();
                petImageToByte(petImage);

               // Toast.makeText(NgoMyPetsActivity.this, radio_b.getText().toString(),Toast.LENGTH_SHORT ).show();
               // toastMessage(radio_g.getCheckedRadioButtonId().toString());


                if (pName.length() != 0 && pSpecies.length() != 0 && pBreeds.length() != 0 && pBirthDate.length() != 0 && pPhone.length() != 0 && radio_g.getCheckedRadioButtonId() != -1) {  //// add condition here


                    int selected_id = radio_g.getCheckedRadioButtonId();
                    radio_b = (RadioButton)findViewById(selected_id);
                    String pGender = radio_b.getText().toString();


                    AddData(pName,pSpecies,pBreeds,pBirthDate,pGender,petImageToByte(petImage),pPhone);       //// add new variables here

                    petNameInputText.setText("");
                    petSpeciesInputText.setText("");        //// add new variables here
                    petBreedsInputText.setText("");        //// add new variables here
                    petBirthDateInputText.setText("");
                    petPhoneInputText.setText("");

                    radio_g.clearCheck();

                    petImage.setImageResource(R.drawable.logo);
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });



        viewpetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPetFormActivity.this, NgoMyPetsActivity.class);
                startActivity(intent);
            }
        });

    }


    //--------------------------------IMAGE GALLERY CODE-----------------------

    public static byte[] petImageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                petImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //--------------------------------IMAGE GALLERY CODE-----------------------


    public void AddData(String petName,String petSpecies,String petBreeds, String petBirthDate, String petGender,byte[] image, String petPhone) {          //// add new variables here
        boolean insertData = mDatabaseHelper.addData(petName,petSpecies,petBreeds,petBirthDate,petGender,image,petPhone); //// add new variables

        if (insertData==true) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

/////////////////////////////////////////////end










