package fr.pisano.film;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    private Button go;
    private EditText prenom;
    private Intent intentJeu;
    private String leprenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = this.findViewById(R.id.btn_go);
        go.setOnClickListener(this);
        prenom = this.findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
         intentJeu = new Intent(this, JeuActivity.class);
        leprenom = prenom.getText().toString();
        if (leprenom.equals("")) {
            Toast.makeText(this, "Vous n'avez pas mis de prénom !", Toast.LENGTH_LONG).show();
        } else {
            lancementJeu(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode > 1) {
            // Si l'on veut que l'application s'arrête… il faudra changer le 1
            // finish();
        } else {
            lancementJeu(requestCode + 1);
        }
    }

    public void lancementJeu(int num) {
        intentJeu.putExtra("Joueur", leprenom);
        intentJeu.putExtra("Numero", num);
        this.startActivityForResult(intentJeu, num );
    }

}
