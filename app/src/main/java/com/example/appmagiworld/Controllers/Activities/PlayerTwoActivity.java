package com.example.appmagiworld.Controllers.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmagiworld.Models.Guerrier;
import com.example.appmagiworld.Models.Mage;
import com.example.appmagiworld.Models.Personnage;
import com.example.appmagiworld.Models.Rodeur;
import com.example.appmagiworld.R;
import com.google.android.material.slider.Slider;

public class PlayerTwoActivity extends AppCompatActivity {
    int classe, level, strenght, agility, intelligence;
    public Slider selectClassPlayerTwo;
    public Slider levelsPlayerTwo;
    public Slider strenghtPlayerTwo;
    public Slider intelligencePlayerTwo;
    public Slider agilityPlayerTwo;
    private Personnage player2;
    private Personnage player1;
    public String attackBaseResult;
    public String attackSpecialResult;
    public String removeLifeResult;
    public ImageView imagePlayerTwo;
    private Button followingThirdlyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_two);
        setUpViews();
        UserClickOnButtonForPassedToNextActivity();
        ChangeForSelectClassDetected();
    }

    public void setUpViews() {
        followingThirdlyButton = findViewById(R.id.buttonStartFourthActivity);
        selectClassPlayerTwo = findViewById(R.id.selectClassPlayerTwo);
        levelsPlayerTwo = findViewById(R.id.selectLevelsPlayerTwo);
        strenghtPlayerTwo = findViewById(R.id.selectStreightPlayerTwo);
        intelligencePlayerTwo = findViewById(R.id.selectIntelligencePlayerTwo);
        agilityPlayerTwo = findViewById(R.id.selectAgilityPlayerTwo);
        imagePlayerTwo = findViewById(R.id.imagePlayerTwo);
    }

    public void UserClickOnButtonForPassedToNextActivity() {
        followingThirdlyButton.setOnClickListener((View v) -> {
            player2 = createPlayer(2);
            Intent GameActivity = new Intent(PlayerTwoActivity.this, GameActivity.class);
            GameActivity.putExtra("PlayerTwo", player2);
            player1 = getIntent().getParcelableExtra("PlayerOne");
            GameActivity.putExtra("PlayerOne", player1);
            // code block
            if (strenght + agility + intelligence > level) {// code block
                Context context = getApplicationContext();
                CharSequence text = "un Personnage niveau " + level + " ne peut pas avoir " + strenght + " de force" +
                        " + " + agility + " d'agilité + " + intelligence + " d’intelligence : le total doit faire " + level;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if (strenght + agility + intelligence + level == 0) {
                Context context = getApplicationContext();
                CharSequence text = "Veuillez selectionnez les caracateristiques du Personnage 2";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if (strenght + agility + intelligence == level) {// code block
                Context context = getApplicationContext();
                CharSequence text = "Le Personnage a un niveau de " + level + " , " + strenght + " de force" +
                        " ," + agility + " d'agilité ," + intelligence + "d'intelligence ";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                startActivity(GameActivity);
            }
        });
    }

    public void ChangeForSelectClassDetected() {
        selectClassPlayerTwo.addOnChangeListener((slider, value, fromUser) -> {
            switch ((int) value) {
                case 1:
                    imagePlayerTwo.setImageResource(R.drawable.image_guerrier);
                    break;
                case 2:
                    imagePlayerTwo.setImageResource(R.drawable.image_mage);
                    // code block
                    break;
                case 3:
                    imagePlayerTwo.setImageResource(R.drawable.image_rodeur);
                    // code block
                    break;
                default:
                    // code block
            }
            //Use the value
        });
    }

    public Personnage createPlayer(int playerNumber) {
        classe = (int) selectClassPlayerTwo.getValue();
        level = (int) levelsPlayerTwo.getValue();
        strenght = (int) strenghtPlayerTwo.getValue();
        agility = (int) agilityPlayerTwo.getValue();
        intelligence = (int) intelligencePlayerTwo.getValue();
        return instanciationPersonnage(classe, level, strenght, agility, intelligence, playerNumber, attackBaseResult, attackSpecialResult, removeLifeResult);
    }

    /**
     * Method used for instanciate the personnage with the good value
     */

    public Personnage instanciationPersonnage(int classe, int level, int strenght, int agility, int intelligence, int playerNumber, String attackBaseResult, String attackSpecialResult, String removeLifeResult) {
        Personnage temp = null;
        switch (classe) {
            case 1:
                temp = new Guerrier(classe, level, strenght, agility, intelligence, playerNumber, attackBaseResult, attackSpecialResult, removeLifeResult);
                break;
            case 3:
                temp = new Rodeur(classe, level, strenght, agility, intelligence, playerNumber, attackBaseResult, attackSpecialResult, removeLifeResult);
                break;
            case 2:
                temp = new Mage(classe, level, strenght, agility, intelligence, playerNumber, attackBaseResult, attackSpecialResult, removeLifeResult);
                break;
        }
        return temp;
    }

}
