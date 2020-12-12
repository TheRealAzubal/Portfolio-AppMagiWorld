package com.example.appmagiworld.Models;

import android.os.Parcel;

import com.example.appmagiworld.R;

/**
 * Class Mage extends Personnage
 * Used for create a Mage
 */
public class Mage extends Personnage {

    /**
     * Default constructor for Mage
     *
     * @param classe
     * @param niveau       The level of the personnage
     * @param force        the force
     * @param agilite      the agility
     * @param intelligence the intelligence
     * @param playerNumber the number of the actual player
     */
    public Mage(int classe, int niveau, int force, int agilite, int intelligence, int playerNumber, String attackBaseResult, String attackSpecialResult, String removeLifeResult) {
        super(classe, niveau, force, agilite, intelligence, playerNumber, attackBaseResult, attackSpecialResult, removeLifeResult);
    }

    protected Mage(Parcel in) {
        super(in);
    }

    // ---- class method ----

    @Override
    public void attackBase(Personnage p) {
        int damage = getIntelligence();
        setAttackBaseResult("Joueur " + getPlayerNumber() + " utilise Boule de Feu et inflige " + damage + " dommages. Joueur " + p.getPlayerNumber() + " perd " + damage + " points de vie");
        p.removeLife(damage);
    }

    @Override
    public void attackSpecial(Personnage p) {
        int defaultLife = getNiveau() * 5;
        int health = getIntelligence() * 2;
        if (getVie() + health >= defaultLife) {
            health = defaultLife - getVie();
            addLife(health);
        } else
            addLife(health);
    }

    @Override
    public int getImage() {
        return R.drawable.image_mage;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

    }

    public static final Creator<Mage> CREATOR = new Creator<Mage>() {

        @Override
        public Mage createFromParcel(Parcel in) {
            return new Mage(in);
        }

        @Override
        public Mage[] newArray(int size) {
            return new Mage[size];
        }

    };

}
