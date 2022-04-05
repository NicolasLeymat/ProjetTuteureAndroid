package com.example.projettueur;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;


public class Suivi_seances extends AppCompatActivity {

    private int i = 0;
    private int dureeTotalExo = 0;
    private Button startbtn;
    private Button pausebtn;
    private Button stopbtn;
    private Button quitbtn;
    public static final String SHARES_PREFS = "shares prefs";
    public static final String NAME_LIST_EXO = "List Exercies";
    public static final String NAME_LIST_SEANCE = "map Seance";
    public ProgressBar pb;
    public ProgressBar pbis;


    int counter;
    private int counterExo;
    Timer timer;
    int tempsexo;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer bCountDownTimer;
    private Boolean isTimerRunning;
    private TextView timeLeftText;
    private TextView titre_Seances;
    private TextView timeLeftExoText;
    private long timeLeft;
    private long timeLeftbis;
    private RecyclerView recycler_View;
    private boolean exo_Fini = false;

    private TextView titre_Exo;
    private TextView duree_Exo;
    private TextView frequence_Exo;
    private TextView puissances_Exo;
    private TextView repetition_Exo;
    private TextView next_Exo;
    private long timeCurrentExo;
    private long timeLeftExo;
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.suivi_seance);

        //Ajouts des listes
        ArrayList<Exercices> list_Exercices = new ArrayList<>();
        list_Exercices.add(new Exercices("Exercice 1", 18, 1, 220, (float) 15.90));
        list_Exercices.add(new Exercices("Exercice 2", 16, 2, 180, (float) 10.5));
        list_Exercices.add(new Exercices("Exercice 3", 9, 3, 260, (float) 19.0));
        list_Exercices.add(new Exercices("Exercice 4", 16, 2, 180, (float) 11.0));
        list_Exercices.add(new Exercices("Exercice 5", 16, 2, 180, (float) 15.0));
        list_Exercices.add(new Exercices("Exercice 6", 16, 2, 180, (float) 16.5));
        list_Exercices.add(new Exercices("Exercice 7", 16, 2, 180, (float) 19.8));
        list_Exercices.add(new Exercices("Exercice 8", 16, 2, 180, (float) 18.9));
        list_Exercices.add(new Exercices("Exercice 9", 8, 1, 280, (float) 20.00));
        //List<Seances> listSeances = PrefConfig.readListFromPref(this,NAME_LIST_SEANCE);
        List<Seances> listSeances = new ArrayList<>();
        listSeances.add(new Seances(list_Exercices,"Seance de test"));

        //Declaration des variables
        quitbtn = findViewById(R.id.QuitSuivi);
        startbtn = findViewById(R.id.startButton);
        stopbtn = findViewById(R.id.stopButton);
        pausebtn = findViewById(R.id.pauseButton);
        pb = findViewById(R.id.SuiviProgress);
        pbis = findViewById(R.id.progressbis);
        titre_Seances = findViewById(R.id.titreSeance);
        timeLeftText  = findViewById(R.id.tpsRestant);
        timeLeftExoText = findViewById(R.id.exoTpsRestant);

        titre_Exo = findViewById(R.id.titreExo);
        duree_Exo  = findViewById(R.id.dureeExo);
        puissances_Exo  = findViewById(R.id.puissanceExo);
        repetition_Exo  = findViewById(R.id.repetitionExo);
        frequence_Exo  = findViewById(R.id.frequenceExo);
        next_Exo  = findViewById(R.id.exoSuivant);


        //Set le texte du titre
        String titre = listSeances.get(0).getTitreSeances();
        titre_Seances.setText(titre);

        //time left
        counter = listSeances.get(0).getTempsMax()*1000;
        timeLeft = counter;


        //temps exo
        tempsexo = list_Exercices.get(i).getDuree()*1000;
        timeLeftbis = tempsexo;
        //progress exo setup
        pbis.setMax(tempsexo);

        //Progress setup
        pb.setMax(counter);

        stopbtn.setText("Reset");


        //Bouton retour
        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Suivi_seances.this, MainPage.class);
                startActivity(intent);
            }
        });



        //Bouton Start
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    start(pb, listSeances.get(0).getListExercices());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        //Bouton Stop
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(pb);
            }
        });



        //Bouton Pause
        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning){
                    pause(pb);
                }else{
                    try {
                        start(pb, listSeances.get(0).getListExercices());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void start(ProgressBar pb, List<Exercices> exercices_List) throws InterruptedException {

        timeLeftExo = exercices_List.get(i).getDuree()* 1000L;
        mCountDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                pb.setProgress((int) timeLeft, true );
                updateCountDownTimer();
                titre_Exo.setText(exercices_List.get(i).getNom());
                duree_Exo.setText("Durée : "+ exercices_List.get(i).getDuree()+" s");
                puissances_Exo.setText(exercices_List.get(i).getPuissance()+" Watts");
                if (exercices_List.get(i).getPuissance() < 155){
                    puissances_Exo.setTextColor(Color.rgb(182,227,127));
                }else if (exercices_List.get(i).getPuissance() >= 155 && exercices_List.get(i).getPuissance() < 245){
                    puissances_Exo.setTextColor(Color.rgb(254, 210, 100));
                }else if (exercices_List.get(i).getPuissance() >= 245 && exercices_List.get(i).getPuissance() < 395){
                    puissances_Exo.setTextColor(Color.rgb(242, 113, 0));
                }else if (exercices_List.get(i).getPuissance() >= 395){
                    puissances_Exo.setTextColor(Color.rgb(173, 22, 3));
                }else{
                    puissances_Exo.setTextColor(Color.BLACK);
                }
                repetition_Exo.setText(exercices_List.get(i).getRepetition() +" / " + exercices_List.get(i).getTotalRep());
                frequence_Exo.setText(exercices_List.get(i).getFrequence_pedalage()+" RPM");
                if (i == exercices_List.size()-1){next_Exo.setText(" Dernier exercice en cours"); }
                else{next_Exo.setText("Le prochain exercice est : " + exercices_List.get(i+1).getNom());}
                timeCurrentExo = exercices_List.get(i).getDuree()*1000;
                if (i == exercices_List.size()){
                    i=0;
                }


            }
            @Override
            public void onFinish() {
                isTimerRunning = false;
                stopbtn.setEnabled(true);
            }
        }.start();

        bCountDownTimer = new CountDownTimer(timeLeftExo, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftExo = millisUntilFinished;
                pbis.setProgress((int) timeLeftExo, true);
                updateCountDownTimerExo((int) timeLeftExo);
            }

            @Override
            public void onFinish() {
                if (exercices_List.get(i).getRepetition() > 1){
                    exercices_List.get(i).setRepetition(exercices_List.get(i).getRepetition() - 1);
                }else if (exercices_List.get(i).getRepetition() == 1){ i+=1;
                    timeLeftExo = exercices_List.get(i+1).getDuree()*1000;}

                this.start();
            }
        }.start();

        pausebtn.setText("Pause");
        isTimerRunning = true;
        startbtn.setEnabled(false);
        stopbtn.setEnabled(true);
    }

    private void updateCountDownTimer() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timeLeftText.setText(timeLeftFormatted);
    }

    private void updateCountDownTimerExo(int time){
        int minutes = (int) (time / 1000) / 60;
        int seconds = (int) (time / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timeLeftExoText.setText(timeLeftFormatted);
    }

    private void stop(ProgressBar pb) {
        timeLeft = counter;
        timeLeftExo = counterExo;
        bCountDownTimer.cancel();
        mCountDownTimer.cancel();
        isTimerRunning = false;
        startbtn.setEnabled(true);
        updateCountDownTimer();
        pb.setProgress(counter);
        stopbtn.setEnabled(false);
        i = 0;
        pausebtn.setEnabled(false);
    }

    private void pause(ProgressBar pb) {
        mCountDownTimer.cancel();
        bCountDownTimer.cancel();
        isTimerRunning = false;
        pausebtn.setText("Resume");
        startbtn.setEnabled(false);
    }



}
