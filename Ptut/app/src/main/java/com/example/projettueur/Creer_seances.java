package com.example.projettueur;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import java.util.HashMap;
import java.util.Map;

public class Creer_seances extends AppCompatActivity  {
    private Button cancelBtn;
    private ImageButton addExosBtn;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_seances);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        saveBtn = (Button) findViewById(R.id.save_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Creer_seances.this,MainPage.class);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Creer_seances.this,List_seances.class);

            }
        });

    }

}
