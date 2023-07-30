package com.zybooks.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.zybooks.petcare.model.Pet;
import com.zybooks.petcare.repo.PetRepository;

import java.util.List;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class ViewRepository extends AppCompatActivity {
    private PetRepository petRepo;
    private ToDoList mToDoList;
    //private EditText mItemEditText;
    private TextView mItemListTextView;

    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_repository);

        back_button = findViewById(R.id.back_button);

        petRepo = PetRepository.getInstance(this);
        List<Pet> getPets = petRepo.getPets();

        //mItemEditText = findViewById(R.id.todo_item);
        mItemListTextView = findViewById(R.id.item_list);

        mToDoList = new ToDoList(this);
        String item = "";
        for(int i = 0; i < getPets.size(); i++){
            item = "Microchip ID:" + getPets.get(i).getMicroId()
                    + ", Name: " + getPets.get(i).getName()
                    + ", Gender: " + getPets.get(i).getGender()
                    + ", Email: " + getPets.get(i).getEmail()
                    + ", Access Code: " + getPets.get(i).getCode()
                    + ", Breed: " + getPets.get(i).getBreed()
                    + ", Neutered: " + getPets.get(i).getNeutered();

            addToList(item);
            item = "";
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayList() {

        // Display a numbered list of items
        StringBuffer itemText = new StringBuffer();
        String[] items = mToDoList.getItems();
        for (int i = 0; i < items.length; i++) {
            itemText.append(i + 1).append(". ").append(items[i]).append("\n");
        }

        mItemListTextView.setText(itemText);
    }

    private void addToList(String item) {

        // Ignore any leading or trailing spaces
        //String item = mItemEditText.getText().toString().trim();

        // Clear the EditText so it's ready for another item
        //mItemEditText.setText("");

        // Add the item to the list and display it
        if (item.length() > 0) {
            mToDoList.addItem(item);
            displayList();
        }
    }
}