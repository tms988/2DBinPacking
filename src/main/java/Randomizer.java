import java.util.Random;

/**
 * Created by ben on 02/01/2015.
 */
public class Randomizer {
    public static void printRandomBoxes(int nbBoxes, int min, int max) {
        for(int i = 0 ; i < nbBoxes-1 ; i ++) {
            Random rand = new Random();

            int w = rand.nextInt((max - min) + 1) + min;
            int h = rand.nextInt((max - min) + 1) + min;
            System.out.print(w + "x" + h + ", ");
        }
        Random rand = new Random();

        int w = rand.nextInt((max - min) + 1) + min;
        int h = rand.nextInt((max - min) + 1) + min;
        System.out.print(w + "x" + h);

    }
}
