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

public class PlayerOneActivity extends AppCompatActivity {
    public Slider selectClassPlayerOne;
    public Slider levelsPlayerOne;
    public Slider streightPlayerOne;
    public Slider intelligencePlayerOne;
    public Slider agilityPlayerOne;
    public ImageView imagePlayerOne;
    private Personnage player1;
    private Button followingSecondButton;
    public String attackBaseResult;
    public String attackSpecialResult;
    public String removeLifeResult;
    int classe;
    int level;
    int strenght;
    int agility;
    int intelligence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_one);
        setUpViews();
        UserClickOnButtonForPassedToNextActivity();
        ChangeForSelectClassDetected();
    }

    public void setUpViews() {
        selectClassPlayerOne = findViewById(R.id.selectClassPlayerOne);
        levelsPlayerOne = findViewById(R.id.selectLevelsPlayerOne);
        streightPlayerOne = findViewById(R.id.selectStreightPlayerOne);
        intelligencePlayerOne = findViewById(R.id.selectIntelligencePlayerOne);
        agilityPlayerOne = findViewById(R.id.selectAgilityPlayerOne);
        followingSecondButton = findViewById(R.id.buttonStartThirdlyActivity);
        imagePlayerOne = findViewById(R.id.imagePlayerOne);
    }

    public void UserClickOnButtonForPassedToNextActivity() {
        followingSecondButton.setOnClickListener((View v) -> {
            player1 = createPlayer(1);
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
                CharSequence text = "Veuillez selectionnez les caracateristiques du Personnage 1";
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
                Intent PlayerTwoActivity = new Intent(PlayerOneActivity.this, PlayerTwoActivity.class);
                PlayerTwoActivity.putExtra("PlayerOne", player1);
                startActivity(PlayerTwoActivity);

            }
        });
    }

    public void ChangeForSelectClassDetected() {
        selectClassPlayerOne.addOnChangeListener((slider, value, fromUser) -> {
            switch ((int) value) {
                case 1:
                    imagePlayerOne.setImageResource(R.drawable.image_guerrier);
                    break;
                case 2:
                    imagePlayerOne.setImageResource(R.drawable.image_mage);
                    // code block
                    break;
                case 3:
                    imagePlayerOne.setImageResource(R.drawable.image_rodeur);
                    // code block
                    break;
                default:
                    // code block
            }
            //Use the value
        });
    }

    public Personnage createPlayer(int playerNumber) {
        classe = (int) selectClassPlayerOne.getValue();
        level = (int) levelsPlayerOne.getValue();
        strenght = (int) streightPlayerOne.getValue();
        agility = (int) agilityPlayerOne.getValue();
        intelligence = (int) intelligencePlayerOne.getValue();
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