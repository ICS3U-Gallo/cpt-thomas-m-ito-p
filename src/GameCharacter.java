//import java.util.*;

class GameCharacter {

    private String name;
    private int level;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int defence;
    private int speed;

    public GameCharacter(String name, int level, int maxHP, int currentHP, int attack, int defence, int speed) { //create a character with stats and name
        this.name = name;
        this.level = level;
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

    public int getLevel() {
        return level;
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

    public void setLevel(int level){
        this.level = level;
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