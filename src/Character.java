import processing.core.PApplet;

//import java.util.*;
import processing.core.*;
class Character {

    private String name;
    private String job;
    private int level;
    private int health;
    private int attack;
    private int defence;
    private int speed;

    public Character(String name, int level, int health, int attack, int defence, int speed) { //create a character with stats and name
        this.name = name;
        this.level = level;
        this.health = health;
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

    public int getHealth() {
        return health;
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

    public void setHealth(int health){
        this.health = health;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

