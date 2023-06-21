package com.zybooks.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private int spinnerPosition = 0;
    private TextView MicroChipTextView;
    private EditText MicroChipEditView;
    private TextView NameTextView;
    private EditText NameEditView;
    private TextView GenderTextView;
    private TextView EmailTextView;
    private EditText EmailEditView;
    private TextView AccessCodeTextView;
    private EditText AccessCodeEditView;
    private TextView ConfirmCodeTextView;
    private EditText ConfirmCodeEditView;
    private TextView BreedTextView;
    private TextView NeuteredCodeTextView;
    private CheckBox NeuteredCheckBox;
    private RadioButton GenderGroupFemale;
    private RadioButton GenderGroupMale;
    private boolean checkBox = true;
    private boolean radioButton = true;

    //*****************************************************
    //**********************OnCreate***********************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //******************************
        //************LAYOUT************

        setContentView(R.layout.linear);
        //setContentView(R.layout.constraint);
        //setContentView(R.layout.relative);
        //setContentView(R.layout.grid);

        //*****************************************************
        //**********************ELEMENTS***********************

        spinner = findViewById(R.id.spinner_size);

        MicroChipTextView = findViewById(R.id.microchip_text_view);
        MicroChipEditView = findViewById(R.id.microchip_edit_text);
        MicroChipEditView.setText("ID");

        NameTextView = findViewById(R.id.name_text_view);
        NameEditView = findViewById(R.id.name_edit_text);

        GenderTextView = findViewById(R.id.gender_text_view);

        EmailTextView = findViewById(R.id.email_text_view);
        EmailEditView = findViewById(R.id.email_edit_text);
        EmailEditView.setText("none@none.com");

        AccessCodeTextView = findViewById(R.id.access_code_text_view);
        AccessCodeEditView = findViewById(R.id.access_code_edit_text);

        ConfirmCodeTextView = findViewById(R.id.confirm_code_text_view);
        ConfirmCodeEditView = findViewById(R.id.confirm_edit_text);

        BreedTextView = findViewById(R.id.breed_text_view);

        NeuteredCodeTextView = findViewById(R.id.neutered_text_view);

        NeuteredCheckBox = findViewById(R.id.checkbox_neutered);

        GenderGroupFemale = findViewById(R.id.radio_female);
        GenderGroupMale = findViewById(R.id.radio_male);
        //*****************************************************
        //**********************SPINNER************************

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sizes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String item = "Pick something";
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
        //*****************************************************



    }

    public void onCheckboxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        // Which checkbox was selected?
        if(v.getId() == R.id.checkbox_neutered){
            if (checked) {
                checkBox =true;
            } else {
                checkBox =true;
            }
        }
        checkBox =true;

    }

    public void onRadioButtonClicked(View view) {
        // Which radio button was selected?

        if(view.getId() == R.id.radio_male){
            radioButton = true;
        } else if(view.getId() == R.id.radio_female){
            radioButton = true;
        }
    }


    public void resetClick(View view) {
        MicroChipEditView.getText().clear();
        MicroChipEditView.setText("ID");
        NameEditView.getText().clear();
        EmailEditView.getText().clear();
        EmailEditView.setText("none@none.com");
        AccessCodeEditView.getText().clear();
        ConfirmCodeEditView.getText().clear();
        NeuteredCheckBox.setChecked(true);
        spinner.setSelection(0);
        GenderGroupFemale.setChecked(true);

        MicroChipTextView.setTextColor(Color.parseColor("#818285"));
        NameTextView.setTextColor(Color.parseColor("#818285"));
        EmailTextView.setTextColor(Color.parseColor("#818285"));
        AccessCodeTextView.setTextColor(Color.parseColor("#818285"));
        ConfirmCodeTextView.setTextColor(Color.parseColor("#818285"));

        String item = "Reset!!!";
        Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
    }

    public void submitClick(View view) {
        String microChip = MicroChipEditView.getText().toString();
        String name = NameEditView.getText().toString();
        String email = EmailEditView.getText().toString();
        String accessCode = AccessCodeEditView.getText().toString();
        String confirmCode = ConfirmCodeEditView.getText().toString();

        boolean emptyError = false;
        boolean userError = false;

        //*************************MICROCHIP*************************
        boolean mchip = false;

        if(!microChip.isEmpty()){
            if(microChip.length() >= 5 && microChip.length() <= 15){
                //mchip = true;
                //String item = "CORRECT MICROCHIP INPUT!";
                //Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                String[] microChipsAvailable = getResources().getStringArray(R.array.chips);
                for(int i = 0; i < microChipsAvailable.length; i++){
                    if(microChipsAvailable[i].equals(microChip)){
                        mchip = true;
                    }
                }
                if(!mchip){
                    userError = true;
                    String item = "MICROCHIP NOT FOUND";
                    Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                }
            } else{ userError = true; }
        } else{ emptyError = true; }

        if(!mchip){
            MicroChipTextView.setTextColor(Color.RED);
            //String item = "INCORRECT MICROCHIP INPUT";
            //Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
        } else{
            MicroChipTextView.setTextColor(Color.parseColor("#818285"));
        }

        //***********************************************************

        //***************************NAME****************************

        if(name.isEmpty()){
            emptyError = true;
            NameTextView.setTextColor(Color.RED);
        } else{
            NameTextView.setTextColor(Color.parseColor("#818285"));
        }

        //***********************************************************

        //***************************EMAIL***************************
        String address = ""; //min 3
        String domain = "";
        String dType = "";
        boolean flag = false;

        if(!(email.isEmpty()) && !(email.equals("none@none.com"))){
            String[] dTypes = getResources().getStringArray(R.array.domainTypes);
            if(email.contains("@") && email.contains(".")){
                boolean atFound = false;
                boolean pFound = false;

                for(int i = 0; i < email.length(); i++){
                    if(!(email.charAt(i) == '@') && (atFound == false)){
                        address += email.charAt(i);
                    } else {
                        atFound = true;
                        if(email.charAt(i) == '@'){ continue; }

                        if(!(email.charAt(i) == '.') && (pFound == false)){
                            domain += email.charAt(i);
                        } else{
                            pFound = true;
                            if(email.charAt(i) == '.'){ continue; }

                            if(pFound == true && atFound == true){
                                dType += email.charAt(i);
                            }
                        }
                    }
                }//end of for loop

                boolean addyError = false;
                boolean domainError = false;

                if(!(address.length() >= 3)){
                    userError = true;
                    addyError = true;
                    EmailTextView.setTextColor(Color.RED);
                }

                if(domain.isEmpty()){
                    userError = true;
                    domainError = true;
                    EmailTextView.setTextColor(Color.RED);
                }

                for(int i = 0; i < dTypes.length; i++){
                    if(dTypes[i].equals(dType)){
                        flag = true;
                    }
                }

                if(flag == false){
                    userError = true;
                    EmailTextView.setTextColor(Color.RED);
                }

                if(flag == true && addyError == false && domainError == false){
                    EmailTextView.setTextColor(Color.parseColor("#818285"));
                }

            } else{
                userError = true;
                EmailTextView.setTextColor(Color.RED);
            }
        }else{
            emptyError = true;
            EmailTextView.setTextColor(Color.RED);
        }

        //***********************************************************

        //*************************PASSCODE**************************

        if(!(accessCode.isEmpty()) && !(confirmCode.isEmpty())){
            if(!(accessCode.equals(confirmCode))){
                userError = true;
                AccessCodeTextView.setTextColor(Color.RED);
                ConfirmCodeTextView.setTextColor(Color.RED);
                String item = "CODES DON'T MATCH";
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            } else{
                AccessCodeTextView.setTextColor(Color.parseColor("#818285"));
                ConfirmCodeTextView.setTextColor(Color.parseColor("#818285"));
            }
        } else{
            emptyError = true;
            AccessCodeTextView.setTextColor(Color.RED);
            ConfirmCodeTextView.setTextColor(Color.RED);
        }

        //***********************************************************
        //**************************SPINNER**************************

        if(spinnerPosition == 0){
            emptyError = true;
            BreedTextView.setTextColor(Color.RED);
        } else{
            BreedTextView.setTextColor(Color.parseColor("#818285"));
        }

        //***********************************************************
        //**************************RADIO_BUTTON**************************

        if(radioButton == false){
            GenderTextView.setTextColor(Color.RED);
        } else{
            GenderTextView.setTextColor(Color.parseColor("#818285"));
        }

        //***********************************************************
        //**************************CHECKBOX**************************

        if(checkBox == false){
            NeuteredCodeTextView.setTextColor(Color.RED);
        } else{
            NeuteredCodeTextView.setTextColor(Color.parseColor("#818285"));
        }

        //***********************************************************
        //**************************ENDING***************************

        if(emptyError == true && userError == true){
            String item = "EMPTY REQUIRED FIELD & IMPROPER INPUT";
            Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
        }else if(emptyError == true){
            String item = "EMPTY REQUIRED FIELD";
            Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
        } else if(userError == true){
            String item = "IMPROPER INPUT";
            Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
        } else{
            String item = "INFO STORED SUCCESSFULLY IN SERVER!";
            Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
        }
    }
}