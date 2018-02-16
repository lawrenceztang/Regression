package Linear;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Display extends Canvas{

    static Random rand;
    static ArrayList<Double> trainingDataX;
    static ArrayList<Double> trainingDataY;
    static int numPoints = 20;
    static double stepSize = .08;
    static int numIterations = 50;
    static int boxSize = 800;

    public void paint(Graphics g) {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }
        catch (Exception e) {

        }

        setBackground(Color.WHITE);
        Regression regression = new Regression(trainingDataX, trainingDataY, stepSize);

        for(int p = 0; p < numIterations; p++) {
            g.clearRect(0, 0, boxSize, boxSize);
            g.setColor(Color.RED);
            g.drawLine(0, (int) (boxSize - regression.b), boxSize, (int) (boxSize - boxSize * regression.m));
            g.setColor(Color.BLACK);
            for (int i = 0; i < numPoints; i++) {
                g.drawOval(trainingDataX.get(i).intValue(), (int) (boxSize - trainingDataY.get(i)), 4, 4);
            }

            regression.takeStep();

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch (Exception e) {

            }
        }

    }

    public static void main(String[] args) {
        rand = new Random();
        getPoints();

        startDisplay();

    }

    public static void getPoints() {
        trainingDataX = new ArrayList<>();
        trainingDataY = new ArrayList<>();
        for(int i = 0; i < numPoints; i++) {
            trainingDataX.add((double) rand.nextInt(boxSize));
            trainingDataY.add(trainingDataX.get(i) - 50 + rand.nextInt(100));
        }
    }

    public static void startDisplay() {
        Display m = new Display();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(boxSize,boxSize);
        //f.setLayout(null);
        f.setVisible(true);
    }

}
