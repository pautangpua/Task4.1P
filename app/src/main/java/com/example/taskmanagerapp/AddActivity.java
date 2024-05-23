package com.example.taskmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText title_input, description_input, date_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        description_input = findViewById(R.id.description_input);
        date_input = findViewById(R.id.date_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addTask(title_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        Integer.valueOf(date_input.getText().toString().trim()));
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}