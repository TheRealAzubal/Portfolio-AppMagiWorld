package com.example.appmagiworld.Controllers.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appmagiworld.Models.Personnage;
import com.example.appmagiworld.Models.ResultGame;
import com.example.appmagiworld.R;
import com.example.appmagiworld.UI.ResultAdapter;
import java.util.LinkedList;

public class GameActivity extends AppCompatActivity {

    final int  ATTACK_TYPE_BASIC = 1 ;
    final int  ATTACK_TYPE_SPECIAL = 2 ;
    int choice;
    int turn = 1;
    private ImageView imagePlayerOneLife;
    private ImageView imagePlayerTwoLife;
    private Personnage player1;
    private Personnage player2;
    public TextView textChoicePlayer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ResultAdapter resultAdapter;
    private Button buttonAttackBase;
    private Button buttonAttackSpecial;
    private Button buttonRestartGame;
    private ProgressBar progressBarLifePlayerOne;
    private ProgressBar progressBarLifePlayerTwo;

    LinkedList<ResultGame> listResult = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpViews();
        setUpRecyclerView();
        getPlayerWithImage();
        setUpProgressBars();
        prepareGameAndFirstAttack();

        UserClickOnButtonAttackBase();
        UserClickOnButtonAttackSpecial();
        UserClickOnButtonRestart();
    }

    public void setUpViews(){
        textChoicePlayer = findViewById(R.id.textchoixPlayer);
        buttonAttackBase = findViewById(R.id.attackBase);
        buttonAttackSpecial = findViewById(R.id.attackSpeciale);
        buttonRestartGame = findViewById(R.id.restartGame);
        imagePlayerOneLife = findViewById(R.id.imagePlayerOne);
        imagePlayerTwoLife = findViewById(R.id.imagePlayerTwo);
        progressBarLifePlayerOne = findViewById(R.id.determinateBar);
        progressBarLifePlayerTwo = findViewById(R.id.determinateBar2);
        // On récupère notre RecyclerView via son id
        recyclerView = findViewById(R.id.resultGame_recyclerview);
    }

    public void setUpRecyclerView(){
        // On veut un RecyclerView qui utilise un LinearLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Ondonne notre_adapter à notre RecyclerView
        resultAdapter = new ResultAdapter(listResult);
        recyclerView.setAdapter(resultAdapter);
    }

    public void getPlayerWithImage() {
        player1 = getIntent().getParcelableExtra("PlayerOne");
        player2 = getIntent().getParcelableExtra("PlayerTwo");
        imagePlayerOneLife.setImageResource(player1.getImage());
        imagePlayerTwoLife.setImageResource(player2.getImage());
    }

    public void setUpProgressBars(){
        progressBarLifePlayerTwo.setProgress(player2.getNiveau() * 5);
        progressBarLifePlayerTwo.setMax(player2.getNiveau() * 5);
        progressBarLifePlayerOne.setMax(player1.getNiveau() * 5);
        progressBarLifePlayerOne.setProgress(player1.getNiveau() * 5);
    }

    public void prepareGameAndFirstAttack(){
        textChoicePlayer.setText("Joueur  1" + "(" + player1.getVie() + ")" + "  veuillez choisir " + "votre action (1 :" + " Attaque Basique, 2 : Attaque Spéciale)");
        buttonRestartGame.setEnabled(false);
    }

    public void start() {
        if (turn % 2 == 1) {
            turn(player1, player2);
        } else {
            turn(player2, player1);
            listResult.addFirst(ResultGame.createSection("Tour" + (turn / 2)));
        }
        turn++;

        if (player1.getVie() <= 0) {
            listResult.addFirst(ResultGame.createRow(R.drawable.image_mort, "Joueur 1 à perdu !"));
            progressBarLifePlayerOne.setProgress(0);
            buttonAttackBase.setEnabled(false);
            buttonAttackSpecial.setEnabled(false);
            buttonRestartGame.setEnabled(true);
        } else if (player2.getVie() <= 0) {
            listResult.addFirst(ResultGame.createRow(R.drawable.image_mort, "Joueur 2 à perdu !"));
            progressBarLifePlayerTwo.setProgress(0);
            buttonAttackBase.setEnabled(false);
            buttonAttackSpecial.setEnabled(false);
            buttonRestartGame.setEnabled(true);
        }

    }

    /*
     * Method used for a turn of the game
     * @param attack the personnage who attack
     * @param defend the personnage who defend
     */

    public void turn(Personnage attack, Personnage defend) {
        textChoicePlayer.setText("Joueur " + defend.getPlayerNumber() + " (" + defend.getVie() + " de vitalité) veuillez choisir " + "votre action (1 :" + " Attaque Basique, 2 : Attaque Spéciale)");

        switch (choice) {
            case ATTACK_TYPE_BASIC:
                attack.attackBase(defend);
                listResult.addFirst(ResultGame.createRow(attack.getImage(), attack.getAttackBaseResult()));
                // code block
                break;
            case ATTACK_TYPE_SPECIAL:
                attack.attackSpecial(defend);
                listResult.addFirst(ResultGame.createRow(attack.getImage(), attack.getAttackSpecialResult()));
                // code block
                break;
            default:
                // code block
        }
        refreshLife();
    }

    public void refreshLife(){                      //This method refreshed the lives of our game characters
        progressBarLifePlayerTwo.setProgress(player2.getVie());
        progressBarLifePlayerOne.setProgress(player1.getVie());
    }

    public void UserClickOnButtonAttackBase(){      //This method is used for each task to be done when the user presses the specified button
        buttonAttackBase.setOnClickListener((View v) -> {
            choice = ATTACK_TYPE_BASIC;
            resultAdapter.notifyDataSetChanged();
            start();
            recyclerView.scrollToPosition(resultAdapter.getItemCount());
        });
    }

    public void UserClickOnButtonAttackSpecial(){   //This method is used for each task to be done when the user presses the specified button
        buttonAttackSpecial.setOnClickListener((View v) -> {
            choice = ATTACK_TYPE_SPECIAL;
            resultAdapter.notifyDataSetChanged();
            start();
            recyclerView.scrollToPosition(resultAdapter.getItemCount());
        });
    }

    public void UserClickOnButtonRestart(){         //This method is used for each task to be done when the user presses the specified button
        buttonRestartGame.setOnClickListener((View v) -> {
            player1.setVie(player1.getNiveau() * 5);
            player2.setVie(player2.getNiveau() * 5);
            progressBarLifePlayerTwo.setProgress(player2.getNiveau() * 5);
            progressBarLifePlayerTwo.setMax(player2.getNiveau() * 5);
            progressBarLifePlayerOne.setMax(player1.getNiveau() * 5);
            progressBarLifePlayerOne.setProgress(player1.getNiveau() * 5);
            recreate();
        });
    }

}