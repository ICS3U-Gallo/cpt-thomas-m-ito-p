import processing.core.*;
import java.util.*;

public class Main extends PApplet {

    public static Scanner in = new Scanner(System.in);
    public static Random rng = new Random();//random number generator
        //to set rng range: rng.nextInt((max - min) + 1) + min;
    public static GameCharacter hero = new GameCharacter("Lincoln", 1, 20, 20, 5, 3, 4);
    ArrayList<GameCharacter> enemies  = new ArrayList<GameCharacter>();
    ArrayList<PImage> enemyPics = new ArrayList<PImage>();

    int enemyId;
    int moveSpeed = 7;
    PVector sansLoc;
    PImage gavin;
    PImage sans;
    PImage tree;

    int screenState;
    PGraphics grove;

    public static void main(String[] args) {
        PApplet.main(new String[] {"Main"});
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        background(0);
        gavin = loadImage("Assets/gavin.png");
        sans = loadImage("Assets/sans.png");
        tree = loadImage("Assets/tree.png");
        screenState = 0;//map
        sansLoc = new PVector(width - 300, height - 200);
        createEnemies();

        grove = createGraphics(width, 700);
        grove.beginDraw();
        //trees

        int numTrees = 35;

        for (int i = 0; i < numTrees; i++){
            float x = 225 + 50 * i;
            float y = -135;
            grove.image(tree, x, y, 400, 400);
        }
        grove.endDraw();
    }

    public void draw() {
        if (screenState == 0){
            menuScreen();
        }else if (screenState == 1){
            mapScreen();
        }else if (screenState == 2){
            dialogueScreen();
        }else if (screenState == 3) {
            createEnemies();
            battleScreen(enemyId);
        }
    }

    void createEnemies() {
        enemies.add(new GameCharacter("Gavin", 1,  20, 20, 5, 3, 2));
        enemies.add(new GameCharacter("Tree", 1, 15, 15, 4, 1, 0));

        //enemyPics.add(loadImage("Assets/gavin.png"));
        //enemyPics.add(loadImage("Assets/tree.png"));
    }

    void resetCharacters(int id) {
        enemies.get(id).setCurrentHP(enemies.get(id).getMaxHP());
        hero.setCurrentHP(hero.getMaxHP());
    }

    boolean wasdPressed(){
        if(screenState == 1 && keyPressed) {
            if (key == 'w' || key == 'W'
                    || key == 's' || key == 'S'
                    || key == 'a' || key == 'A'
                    || key == 'd' || key == 'D') {
                return true;
            }
        }
        return false;
    }

    int encounterValue(){
        //use two random nubers and find the average to reduce encounter chance
        int firstNum = rng.nextInt(100 - 0) + 1;
        int secondNum = rng.nextInt(100 - 0) + 1;

        return (firstNum + secondNum) / 2;

    }

    public void keyPressed() {
        if (screenState == 1 && wasdPressed()) {
            if (key == 'w' || key == 'W') {
                sansLoc.y -= moveSpeed;
            } else if (key == 's' || key == 'S') {
                sansLoc.y += moveSpeed;
            } else if (key == 'a' || key == 'A') {
                sansLoc.x -= moveSpeed;
            } else if (key == 'd' || key == 'D') {
                sansLoc.x += moveSpeed;
            }
            if(encounterValue() <= 10) {
                enemyId = rng.nextInt(enemies.size() - 1) + 1;
                screenState = 3;
            }
        }
    }

  void menuScreen() {
        background(111, 227, 0);
        noStroke();
        fill(255, 241, 195);
        rect(width / 2 - 475, height / 2 - 100, width / 2 + 250, height, 50);

        int numTreesV1 = 3;

        for (int i = 0; i < numTreesV1; i++) {
            float x = -110;
            float y = -135 + 340 * i;
            image(tree, x, y, 400, 400);
        }

        int numTreesH = 7;

        for (int i = 0; i < numTreesH; i++) {
            float x = -110 + 200 * i;
            float y = -135;
            image(tree, x, y, 400, 400);
        }

        int numTreesV2 = 3;

        for (int i = 0; i < numTreesV2; i++) {
            float x = 1090;
            float y = -135 + 340 * i;
            image(tree, x, y, 400, 400);
        }

        fill(0);
        textSize(45);
        textAlign(CENTER);
        text("Gavin And Lincoln's Tree Adventure", width / 2, height / 2 + 25);
        textSize(30);
        text("press [SPACE] to start", width / 2, height / 2 + 150);
        if (key == ' ') {
            screenState = 1;
        }
    }
    
    void mapScreen(){
        background(255, 241, 195); //#fff1c3 sand
        textAlign(LEFT);
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

        image(gavin, width - 150, height/2, 100, 100);

        image(sans, sansLoc.x, sansLoc.y, 75, 75);

        //water

        fill(116, 204, 244);//#74ccf4
        rect(0, height - 100, width, 100);

        //trees
        image(grove, 50, -10);

        //text box and boundaries
        
        fill(0);
        rect(5, 5, 375, 195, 15);
        fill(255);
        textSize(25);
        text("Welcome to the town of harb!", 10, 35);
        text("Introduce yourself to the", 10, 65);
        text("mayor, Gavin.", 10, 95);
        text("Beware of hidden enemies", 10, 125);
        text("on the way", 10, 155);

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
        textAlign(LEFT);
        textSize(height/30);
        text("press t to speak", 10, 30);

        textSize(height/20);
        if (key == 't'|| key == 'T'){
            text("hello!", width/2 - 500, height/2);
            fill(0);
            rect(width/2, 0, width/2, height);
            fill(255);
            text("hey", width/2 + 175, height/2 - 100);
            enemyId = 0;
            screenState = 3;
        }
        //int m = millis();
        //if(m <
        //fill(m % 255);
        //rect(25, 25, 50, 50);

        //characters
        image(gavin, width - 250, height/2, height/3, height/3);
        image(sans, 0, height/2 + 40, height/3 - 50, height/3 -50);
    }

    void battleScreen(int id) {
        background(0);
        fill(255);
        int damageDealt;
        int actionValue;

        //text("You Encountered: " + enemies.get(id).getName(), width/2, height/2);

        if(enemies.get(id).getCurrentHP() > 0) {
            textSize(25);
            textAlign(LEFT);
            text("Your HP: " + hero.getCurrentHP() + "\n1. Attack 2. Run", 50, 50);
            textAlign(RIGHT);
            text(enemies.get(id).getName() + "'s HP: " + enemies.get(id).getCurrentHP(), width - 50, 50);
            if (keyPressed && key == '1') {
                //your attack
                damageDealt = hero.getAttack() - enemies.get(id).getDefence();
                enemies.get(id).setCurrentHP(enemies.get(id).getCurrentHP() - damageDealt);
                //enemy attack
                damageDealt = enemies.get(id).getAttack() - hero.getDefence();
                hero.setCurrentHP(hero.getCurrentHP() - damageDealt);
            } else if (keyPressed && key == '2') {
                resetCharacters(id);
                screenState = 1;
            } else {

            }
        }

        if (enemies.get(id).getCurrentHP() <= 0) { //if you win the battle
            hero.setLevel(hero.getLevel() + 1); //get a level
            //levelUp(1, 3);//range of stats you can level
            resetCharacters(id);
            screenState = 1;
        }
    }

    void levelUp(int min, int max) {
        int statPoints = rng.nextInt((max - min) + 1) + min; // set range for how many stats can level up
        int leveledStat; //use numbers to id stats

        while (statPoints > 0) {
            System.out.println("You can level stats " + statPoints + " times \n 1.HP 2. Attack 3. Defence");
            leveledStat = in.nextInt();
            statPoints -= 1;

            if (leveledStat == 1) {
                hero.setMaxHP(hero.getMaxHP() + 1);
            } else if (leveledStat == 2) {
                hero.setAttack(hero.getAttack() + 1);
            } else if (leveledStat == 3) {
                hero.setDefence(hero.getDefence() + 1);
            }
        }
    }
}
