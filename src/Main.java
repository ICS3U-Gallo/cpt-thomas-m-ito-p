import processing.core.*;
import java.util.*;

public class Main extends PApplet {

    public static Scanner in = new Scanner(System.in);
    public static Random rng = new Random();//random number generator

    public static Character hero = new Character("Jim", 1, 20, 5, 3, 4);

    int speed = 7;

    PVector sansLoc;

    PImage papyrus;
    PImage sans;
    PImage tree = new PImage();

    int dialogueT = 1;

    int screenState;

    public static void main(String[] args) {
        PApplet.main(new String[] {"Main"});
        //PApplet.Character(new String[] {"Character"});

        //battleScreen("Monster");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        background(0);
        papyrus = loadImage("Assets/papyrus.png");
        sans = loadImage("Assets/sans.png");
        tree = loadImage("Assets/tree.png");
        //frameRate(30);
        screenState = 1;//map
        sansLoc = new PVector(width - 300, height -200);
    }

    public void draw() {
        if (screenState == 1){
            mapScreen();
        }else if (screenState == 2){
            dialogueScreen();
        }
    }
    public void keyPressed() {
        if (key == 'w' || key == 'W') {
            sansLoc.y -= speed;
        } else if (key == 's' || key == 'S') {
            sansLoc.y += speed;
        } else if (key == 'a' || key == 'A') {
            sansLoc.x -= speed;
        } else if (key == 'd' || key == 'D') {
            sansLoc.x += speed;
        }
    }

    public void keyReleased() {
        if (key == 't' || key == 'T') {
            dialogueT = 0;
        }
    }

    void mapScreen(){
        background(255, 241, 195);//#fff1c3

        //path

        stroke(232, 185, 39);//#e8b927
        strokeWeight(10);
        fill(237, 200, 85);//#edc855
        rect(225, height/2, width, 150, 15);

        noStroke();
        fill(111, 227, 0);//#6fe300
        rect(width - 150, -5, 150, height, 7);
        rect(400, 0, width, 225, 10);
        ellipse(width - 150, 225, 100, 100);

        //characters

        image(papyrus, width - 150, height/2, 100, 100);

        image(sans, sansLoc.x, sansLoc.y, 75, 75);

        //water

        fill(116, 204, 244);//#74ccf4
        rect(0, height - 100, width, 100);

        //trees

        int numTrees = 18;

        for (int i = 0; i < numTrees; i++){
            float x = 225 + 50 * i;
            float y = -135;
            image(tree, x, y, 400, 400);
        }

//text box and boundaries

        if (sansLoc.x >= width - 225) {
            sansLoc.x = width - 225;
            fill(0);
            rect(5, 5, 375, 195, 15);
            fill(255);
            textSize(25);
            text("keep off the grass!", 10, 30);
        } if (sansLoc.x <= 0) {
            sansLoc.x = 0;
        } if (sansLoc.y >= height - 175) {
            sansLoc.y = height - 175;
            fill(0);
            rect(5, 5, 375, 195, 15);
            textSize(25);
            fill(255);
            text("stay out of the water!", 10, 30);
        }
        if (sansLoc.y <= 0) {
            sansLoc.y = 0;
        }
        if (sansLoc.x <= width - 225 && sansLoc.x >= width - 260 && sansLoc.y <= height/2 + 75 && sansLoc.y >= height/2 - 75) {
            fill(0);
            rect(5, 5, 375, 195, 15);
            textSize(25);
            fill(255);
            text("speak to Gavin?", 10, 30);
            text("press space", 10, 55);

            //dialogue

            if (keyPressed && key == ' '){
                screenState = 2;
            }
        }
    }

    void dialogueScreen(){
        background(0);

        //dialogue

        fill(255);

        textSize(height/30);
        text("press t to speak", 10, 30);

        textSize(height/20);
        if (dialogueT == 0){
            text("hello!", width/2 - 500, height/2);
            fill(0);
            rect(width/2, 0, width/2, height);
            fill(255);
            text("hey", width/2 + 175, height/2 - 100);
        }
        //int m = millis();
        //if(m <
        //fill(m % 255);
        //rect(25, 25, 50, 50);

        //characters
        image(papyrus, width - 250, height/2, height/3, height/3);
        image(sans, 0, height/2 + 40, height/3 - 50, height/3 -50);
    }

    public static void battleScreen(String name){

        Character monster = new Character(name, 1,  20, 5, 3, 2);
        int damageDealt;
        int actionValue;

        boolean inBattle = true;
        boolean wonBattle = false;

        System.out.println("You Encountered: " + monster.getName());

        while (monster.getHealth() > 0 && inBattle) {

            System.out.println("Your HP: " + hero.getHealth() + "\n1. Attack 2. Run");
            actionValue = in.nextInt();

            if (actionValue == 1) {
                //your attack
                damageDealt = hero.getAttack() - monster.getDefence();
                monster.setHealth(monster.getHealth() - damageDealt);
                System.out.println("You dealt: " + damageDealt + " damage");
                //enemy attack
                damageDealt = monster.getAttack() - hero.getDefence();
                hero.setHealth(hero.getHealth() - damageDealt);
                System.out.println(monster.getName() + " dealt: " + damageDealt + " damage");
            } else if (actionValue == 2) {
                System.out.println("Got away");
                inBattle = false;
            } else {

            }
        }

        if (monster.getHealth() == 0) { //if you win the battle
            hero.setLevel(hero.getLevel() + 1); //get a level
            levelUp(1, 3);//range of stats you can level
        }
    }

    public static void levelUp(int min, int max) {
        int statPoints = rng.nextInt((max - min) + 1) + min; // set range for how many stats can level up
        int leveledStat; //use numbers to id stats

        while (statPoints > 0) {
            System.out.println("You can level stats " + statPoints + " times \n 1.HP 2. Attack 3. Defence");
            leveledStat = in.nextInt();
            statPoints -= 1;

            if (leveledStat == 1) {
                hero.setHealth(hero.getHealth() + 1);
            } else if (leveledStat == 2) {
                hero.setAttack(hero.getAttack() + 1);
            } else if (leveledStat == 3) {
                hero.setDefence(hero.getDefence() + 1);
            }
        }
        System.out.println("\nHP: " + hero.getHealth() + "\nAttack: " + hero.getAttack() + "\nDefense: " + hero.getDefence());
    }

}