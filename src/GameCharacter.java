//import java.util.*;

class GameCharacter {

    private String name;
    private int killCount;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int defence;
    private int speed;

    public GameCharacter(String name, int killCount, int maxHP, int currentHP, int attack, int defence, int speed) { //create a character with stats and name
        this.name = name;
        this.killCount = killCount;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
    }

    //Getters
    public String getName() {
       return name;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setKillCount(int killCount){
        this.killCount = killCount;
    }

    public void setMaxHP(int maxHealth){
        this.maxHP = maxHealth;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}