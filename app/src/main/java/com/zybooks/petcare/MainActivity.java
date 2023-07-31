package com.zybooks.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zybooks.petcare.model.Pet;
import com.zybooks.petcare.repo.PetRepository;

import java.util.List;

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

    private Button submit_button;

    private Button repository_button;

    //DBHelper DB;

    private PetRepository petRepo;

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

        submit_button = findViewById(R.id.submit_button);
        repository_button = findViewById(R.id.repository_button);

        //DB = new DBHelper(this);

        petRepo = PetRepository.getInstance(this);

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

        repository_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewRepository.class);
                startActivity(intent);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String microChip = MicroChipEditView.getText().toString();
                String name = NameEditView.getText().toString();
                String email = EmailEditView.getText().toString();
                String accessCode = AccessCodeEditView.getText().toString();
                String confirmCode = ConfirmCodeEditView.getText().toString();
                String breed = "";
                String gender = "";
                String neutered = "";
                Boolean flagId = false;
                int errorCounter = 0;

                /*************************************/

                List<Pet> getPets = petRepo.getPets();

                errorCounter += checkMicroId(microChip, getPets);

                errorCounter += checkName(name);

                errorCounter += checkEmail(email);

                errorCounter += checkPasscode(accessCode, confirmCode);

                errorCounter += checkSpinner();

                if(spinnerPosition == 1){
                    breed = "German Shepherd";
                } else if(spinnerPosition == 2){
                    breed = "Bulldog";
                } else if(spinnerPosition == 3){
                    breed = "Retriever";
                } else{
                    breed = "Husky";
                }

                if(radioButton == false){
                    gender = "male";
                } else{
                    gender = "female";
                }

                if(checkBox == true){
                    neutered = "True";
                }else{
                    neutered = "False";
                }

                /*************************************/
                /*************************************/
                //petRepo.deleteSubject(getPets.get(1));

                if(errorCounter == 0){
                    Pet pet = new Pet(microChip, name, gender, email, accessCode,breed, neutered);
                    //Pet pet = new Pet(microChip, name, email);
                    petRepo.addPet(pet);
                    String item = "Registration completed";
                    Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();


                } else {
                    String item = "EMPTY REQUIRED FIELD OR IMPROPER INPUT";
                    Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public int checkMicroId(String microId, List<Pet> getPets){
        Boolean flag = false;
        Boolean flagId = false;

        if(!microId.isEmpty()){
            if(microId.length() >= 5 && microId.length() <= 15){
                for(int i = 0; i < getPets.size(); i++){
                    String chipID = getPets.get(i).getMicroId();
                    if(chipID.equals(microId)){
                        flagId = true;
                    }
                }

                if(flagId == false){
                    flag = false;
                } else{
                    flag = true;
                }
            } else{
                flag = true;
            }
        } else{
            flag = true;
        }

        if(flag == true){
            MicroChipTextView.setTextColor(Color.RED);
            return 1;
        } else{
            MicroChipTextView.setTextColor(Color.parseColor("#818285"));
            return 0;
        }
    }

    public int checkName(String name){
        if(name.isEmpty()){
            NameTextView.setTextColor(Color.RED);
            return 1;
        } else{
            NameTextView.setTextColor(Color.parseColor("#818285"));
            return 0;
        }
    }

    public int checkEmail(String email){
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
                    //userError = true;
                    addyError = true;
                    EmailTextView.setTextColor(Color.RED);
                    return 1;
                }

                if(domain.isEmpty()){
                    //userError = true;
                    domainError = true;
                    EmailTextView.setTextColor(Color.RED);
                    return 1;
                }

                for(int i = 0; i < dTypes.length; i++){
                    if(dTypes[i].equals(dType)){
                        flag = true;
                    }
                }

                if(flag == false){
                    //userError = true;
                    EmailTextView.setTextColor(Color.RED);
                    return 1;
                }

                if(flag == true && addyError == false && domainError == false){
                    EmailTextView.setTextColor(Color.parseColor("#818285"));
                    return 0;
                }

            } else{
                //userError = true;
                EmailTextView.setTextColor(Color.RED);
                return 1;
            }
        }else{
            //emptyError = true;
            EmailTextView.setTextColor(Color.RED);
            return 1;
        }
        return 1;
    }

    public int checkPasscode(String accessCode, String confirmCode){
        if(!(accessCode.isEmpty()) && !(confirmCode.isEmpty())){
            if(!(accessCode.equals(confirmCode))){
                //userError = true;
                AccessCodeTextView.setTextColor(Color.RED);
                ConfirmCodeTextView.setTextColor(Color.RED);
                //String item = "CODES DON'T MATCH";
                //Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                return 1;
            } else{
                AccessCodeTextView.setTextColor(Color.parseColor("#818285"));
                ConfirmCodeTextView.setTextColor(Color.parseColor("#818285"));
                return 0;
            }
        } else{
            //emptyError = true;
            AccessCodeTextView.setTextColor(Color.RED);
            ConfirmCodeTextView.setTextColor(Color.RED);
            return 1;
        }
    }

    public int checkSpinner(){
        if(spinnerPosition == 0){
            //emptyError = true;
            BreedTextView.setTextColor(Color.RED);
            return 1;
        } else{
            BreedTextView.setTextColor(Color.parseColor("#818285"));
            return 0;
        }
    }

    public void onCheckboxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        // Which checkbox was selected?
        if(v.getId() == R.id.checkbox_neutered){
            if (checked) {
                checkBox = true;
            } else {
                checkBox = false;
            }
        }
        //checkBox =true;

    }

    public void onRadioButtonClicked(View view) {
        // Which radio button was selected?

        if(view.getId() == R.id.radio_male){
            radioButton = false;
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

}