package com.example.projettueur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainPage extends AppCompatActivity {
    private Button creerSeancebtn;
    private Button listeSeancebtn;
    private Button historiqueSeancebtn;
    private Button quitAppbtn;
    private Button suivibtn;
    public static List<Seances> listSeance;
    public static List<Exercices> list_Exercices = new ArrayList<>();
    public static final String SHARES_PREFS = "shares prefs";
    public static final String NAME_LIST_EXO = "List Exercies";
    public static final String NAME_LIST_SEANCE = "map Seance";
    private Timer timer;
    public Set<String> set_Exo_Load;
    private Button consulbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        creerSeancebtn = (Button) findViewById(R.id.creerSeance);
        suivibtn = (Button) findViewById(R.id.Suivi);
        listeSeancebtn = (Button) findViewById(R.id.listeSeance);
        historiqueSeancebtn = (Button) findViewById(R.id.historiqueSeance);
        quitAppbtn = (Button) findViewById(R.id.quit);


        creerSeancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentCreerSeances = new Intent(MainPage.this,Creer_seances.class);
//                startActivity(intentCreerSeances);
                Toast.makeText(getApplicationContext(),"Not yet Implemented", Toast.LENGTH_LONG).show();
            }
        });

        listeSeancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentListSeance = new Intent(MainPage.this,List_seances.class);
//                startActivity(intentListSeance);
                Toast.makeText(getApplicationContext(),"Not yet Implemented", Toast.LENGTH_LONG).show();
            }
        });

        suivibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentListSeance = new Intent(MainPage.this,Suivi_seances.class);
                startActivity(intentListSeance);
            }
        });

        historiqueSeancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentHistoriqueSeances = new Intent(MainPage.this,Historique_seances.class);
//                startActivity(intentHistoriqueSeances);
                Toast.makeText(getApplicationContext(),"Not yet Implemented", Toast.LENGTH_LONG).show();
            }
        });

        quitAppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }

    private boolean checkFirstRun() {
        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // TODO This is just a normal run
            return false;

        } else if (savedVersionCode == DOESNT_EXIST) {

            // TODO This is a new install (or the user cleared the shared preferences)
            return true;

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
            return false;
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
        return true;
    }
}
