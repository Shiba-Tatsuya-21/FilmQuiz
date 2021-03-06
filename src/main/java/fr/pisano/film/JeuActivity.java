package fr.pisano.film;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JeuActivity extends AppCompatActivity implements   android.view.View.OnClickListener {

    private int numero;
    private TextView joueur;
    private TextView laQuestion;
    private RadioGroup groupQuestions;
    String questions[][] = {
            { "John McLane", "Piège en eaux troubles", "Pulp Fiction", "Piège de Cristal", "3" },
            { "Martin McFly", "Mars Attacks !", "Spin City", "Retour vers le futur", "3" },
            { "Alan Parrish", "Jumanji", "Good Morning, Vietnam", "Popeye", "1" }
    };
    private RadioButton reponses[];


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        String message = this.getIntent().getExtras().getString("Joueur");
        numero = this.getIntent().getExtras().getInt("Numero");
        joueur = this.findViewById(R.id.tv_jeu);
        joueur.setText("A vous de jouer "+message+"...");

        // Initialisation des contrôles
        laQuestion = findViewById(R.id.tv_question);
        laQuestion.setMinimumWidth(400);
        groupQuestions = findViewById(R.id.rg_question);
        reponses = new RadioButton[3];

        // Création des boutons radio
        reponses[0] = new RadioButton(this);
        reponses[0].setTextColor(Color.parseColor("#1111FF"));
        reponses[0].setMinimumWidth(400);
        reponses[1] = new RadioButton(this);
        reponses[1].setTextColor(Color.parseColor("#1111FF"));
        reponses[1].setMinimumWidth(400);
        reponses[2] = new RadioButton(this);
        reponses[2].setTextColor(Color.parseColor("#1111FF"));
        reponses[2].setMinimumWidth(400);

        // Affectation d'un ID aux boutons radio afin de savoir lequel est sélectionné
        reponses[0].setId(0);
        reponses[1].setId(1);
        reponses[2].setId(2);

        // Gestion du bouton
        Button resultat = findViewById(R.id.btn_reponse);
        resultat.setOnClickListener(this);

        // Démarrage du jeu
        initQuestions(numero);
    }
    private void initQuestions(int numero) {
        Log.i("numero",""+numero);
        laQuestion.setText("Dans quel film le personnage de "+questions[numero][0]+ " joue ?");

        reponses[0].setText(questions[numero][1]);
        groupQuestions.addView(reponses[0]);
        reponses[1].setText(questions[numero][2]);
        groupQuestions.addView(reponses[1]);
        reponses[2].setText(questions[numero][3]);
        groupQuestions.addView(reponses[2]);
    }

    @Override
    public void onClick(View v) {
        int retour = groupQuestions.getCheckedRadioButtonId();
        int repOk = Integer.parseInt(questions[numero][4]);
        if (retour+1 == repOk ) {
            Toast.makeText(this,"Bonne réponse !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"Mauvaise réponse, c'était : "+questions[numero][repOk],
                    Toast.LENGTH_LONG).show();
        }
        // Jeu suivant
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Numero",numero);
        setResult(RESULT_OK,returnIntent);
        finish();
    }
}
