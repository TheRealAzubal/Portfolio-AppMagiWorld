package com.example.appmagiworld.Models;

import android.os.Parcel;

import com.example.appmagiworld.R;


/**
 * Class Rodeur extends Personnage
 * Used for create a Rodeur
 */
public class Rodeur extends Personnage {

    /**
     * Default constructor for Rodeur
     *
     * @param classe
     * @param niveau       The level of the personnage
     * @param force        the force
     * @param agilite      the agility
     * @param intelligence the intelligence
     * @param playerNumber the number of the actual player
     */
    public Rodeur(int classe, int niveau, int force, int agilite, int intelligence, int playerNumber, String attackBaseResult, String attackSpecialResult, String removeLifeResult) {
        super(classe, niveau, force, agilite, intelligence, playerNumber, attackBaseResult, attackSpecialResult, removeLifeResult);
    }

    protected Rodeur(Parcel in) {
        super(in);
    }

    // ---- class method ----

    @Override
    public void attackBase(Personnage p) {
        int damage = getAgilite();
        setAttackBaseResult("Joueur " + getPlayerNumber() + " utilise Tir à l'Arc et inflige " + damage + " dommages. Joueur" + p.getPlayerNumber() + " perd " + damage + " points de vie");

        p.removeLife(damage);
    }

    @Override
    public void attackSpecial(Personnage p) {
        int agilityWon = getNiveau() / 2;
        addAgility(agilityWon);
        setAttackSpecialResult("Joueur " + getPlayerNumber() + " utilise Concentration et gagne " + agilityWon + " en agilité.");
    }

    @Override
    public int getImage() {
        return R.drawable.image_rodeur;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

    }

    public static final Creator<Rodeur> CREATOR = new Creator<Rodeur>() {

        @Override
        public Rodeur createFromParcel(Parcel in) {
            return new Rodeur(in);
        }

        @Override
        public Rodeur[] newArray(int size) {
            return new Rodeur[size];
        }

    };

}
