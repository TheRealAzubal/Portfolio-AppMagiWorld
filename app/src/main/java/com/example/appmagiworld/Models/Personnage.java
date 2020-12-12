package com.example.appmagiworld.Models;


import android.os.Parcel;
import android.os.Parcelable;


/**
 * Abstract class for the construction of a personnage
 */
public abstract class Personnage implements Parcelable {

    public int niveau;
    public int vie;
    public int force;
    public int agilite;
    public int intelligence;
    public int playerNumber;
    public int classe;
    public String removeLifeResult;
    public String attackBaseResult;
    public String attackSpecialResult;

    /**
     * Default constructor
     *
     * @param niveau       The level of the personnage
     * @param force        the force
     * @param agilite      the agility
     * @param intelligence the intelligence
     * @param playerNumber the number of the actual player
     */

    public Personnage(int classe, int niveau, int force, int agilite, int intelligence, int playerNumber, String attackBaseResult, String attackSpecialResult, String removeLifeResult) {
        this.niveau = niveau;
        this.vie = niveau * 5;
        this.force = force;
        this.agilite = agilite;
        this.intelligence = intelligence;
        this.playerNumber = playerNumber;
        this.attackBaseResult = attackBaseResult;
        this.attackSpecialResult = attackSpecialResult;
        this.removeLifeResult = removeLifeResult;
        this.classe = classe;
    }

    // ---- Getters ----

    public int getNiveau() {
        return niveau;
    }

    public int getVie() {
        return vie;
    }

    public int getForce() {
        return force;
    }

    public int getAgilite() {
        return agilite;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getAttackBaseResult() {
        return attackBaseResult;
    }

    public String getAttackSpecialResult() {
        return attackSpecialResult;
    }

    public String getRemoveLifeResult() {
        return removeLifeResult;
    }

    public int getClasse() {
        return classe;
    }

    // ---- Setters ----

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setAttackBaseResult(String attackBaseResult) {
        this.attackBaseResult = attackBaseResult;
    }

    public void setAttackSpecialResult(String attackSpecialResult) {
        this.attackSpecialResult = attackSpecialResult;
    }

    public void setRemoveLifeResult(String removeLifeResult) {
        this.removeLifeResult = removeLifeResult;
    }

    // ---- Class methods ----

    /**
     * Method for remove life after an attack
     *
     * @param damage the amount of damage
     */

    public void removeLife(int damage) {
        int currentLife = getVie();
        setRemoveLifeResult("Joueur " + getPlayerNumber() + " perd " + damage + " points de vie");
        setVie(currentLife - damage);
        if (getVie() <= 0) {
            setRemoveLifeResult("Joueur " + getPlayerNumber() + " est mort");
        }
    }

    public void addLife(int health) {
        setAttackSpecialResult("Joueur " + getPlayerNumber() + " utilise Soin et gagne " + health + " en vitalité.");
        setVie(getVie() + health);

    }

    public void addAgility(int agility) {
        System.out.println("Joueur " + getPlayerNumber() + " utilise Concentration et gagne " + agility + " en agilité.");
        setAgilite(getAgilite() + agility);
    }

    // ---- Abstract methods ----

    /**
     * Abstract method used for return the inherited attack 1
     */

    public abstract void attackBase(Personnage p);

    /**
     * Abstract method used the return the inherited attack 2
     */

    public abstract void attackSpecial(Personnage p);

    public abstract int getImage();

    protected Personnage(Parcel in) {
        niveau = (int) in.readLong();
        force = (int) in.readLong();
        vie = (int) in.readLong();
        agilite = (int) in.readLong();
        intelligence = (int) in.readLong();
        playerNumber = (int) in.readLong();
        removeLifeResult = in.readString();
        attackBaseResult = in.readString();
        attackSpecialResult = in.readString();
        removeLifeResult = in.readString();
        classe = (int) in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(niveau);
        dest.writeLong(force);
        dest.writeLong(vie);
        dest.writeLong(agilite);
        dest.writeLong(intelligence);
        dest.writeLong(playerNumber);
        dest.writeString(removeLifeResult);
        dest.writeString(attackBaseResult);
        dest.writeString(attackSpecialResult);
        dest.writeString(removeLifeResult);
        dest.writeLong(classe);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
