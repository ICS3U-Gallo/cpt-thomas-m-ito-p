//import processing.core.*;
import java.util.*;

class Main{

    public static Scanner in = new Scanner(System.in);
    public static Random rng = new Random();

    public static Character hero = new Character("Jim", 1, 20, 5, 3);
    public static Character monster = new Character("boar", 1,  20, 5, 3);



    public static void main(String[] args) {
        //PApplet.main(new String[] {"Main"});

        battle();
    }
    public static void battle(){

        int damageDealt;
        int actionValue;

        boolean inBattle = true;


        while (monster.getHealth() > 0 && inBattle) {

            System.out.println("You Encountered: " + monster.getName());
            System.out.println("1. Attack 2. Run");
            actionValue = in.nextInt();

            if (actionValue == 1) {
                damageDealt = hero.getAttack() - monster.getDefence();
                monster.setHealth(monster.getHealth() - damageDealt);
                System.out.println("You dealt: " + damageDealt + " damage");
            } else if (actionValue == 2) {
                System.out.println("Got away");
                inBattle = false;
            } else {

            }
        }
        hero.setLevel(hero.getLevel() + 1);
        levelUp(1, 3);
    }

    public static void levelUp(int min, int max) {
        int statPoints = rng.nextInt((max - min) + 1) + min;
        int leveledStat;

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