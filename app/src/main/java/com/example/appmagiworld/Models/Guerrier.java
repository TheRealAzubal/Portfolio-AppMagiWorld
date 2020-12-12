package com.example.appmagiworld.Models;

import android.os.Parcel;

import com.example.appmagiworld.R;


/**
 * Class Guerrier extends Personnage
 * Used for create a Guerrier
 */
public class Guerrier extends Personnage  {

  /**
   * Default constructor for Guerrier
   * @param classe
   * @param niveau       The level of the personnage
   * @param force        the force
   * @param agilite      the agility
   * @param intelligence the intelligence
   * @param playerNumber the number of the actual player
   */
  public Guerrier(int classe, int niveau, int force, int agilite, int intelligence, int playerNumber, String attackBaseResult, String attackSpecialResult, String removeLifeResult){
    super(classe,niveau, force, agilite, intelligence, playerNumber,attackBaseResult,attackSpecialResult,removeLifeResult);
  }

  protected Guerrier(Parcel in) {
    super(in);

  }

  // ---- class method ----

  @Override
  public void attackBase(Personnage p) {
    int damage = getForce();
    setAttackBaseResult("Joueur " + getPlayerNumber() + " utilise Coup d'Epee et inflige " + damage + " dommages. Joueur " + p.getPlayerNumber() + " perd " + damage + " points de vie");
    p.removeLife(damage);
  }

  @Override
  public void attackSpecial(Personnage p) {
    int damage = getForce() * 2;
    int autoDamage = getForce() / 2;
    setAttackSpecialResult("Joueur " + getPlayerNumber() + " utilise Coup de rage et inflige " + damage + " dommages. Joueur " + p.getPlayerNumber() + " perd " + damage + " points de vie . Joueur " + getPlayerNumber() + " perd " + autoDamage + " points de vie");
    p.removeLife(damage);
    removeLife(autoDamage);
  }

  @Override
  public int getImage() {
    return R.drawable.image_guerrier;
  }

  /*   Sub class   */

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
  }

  public static final Creator<Guerrier> CREATOR = new Creator<Guerrier>() {

    @Override
    public Guerrier createFromParcel(Parcel in) {
      return new Guerrier(in);
    }

    @Override
    public Guerrier[] newArray(int size) {
      return new Guerrier[size];
    }

  };

}
