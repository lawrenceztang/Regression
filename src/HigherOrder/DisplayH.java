package HigherOrder;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DisplayH extends Canvas{

    static Random rand;
    static ArrayList<ArrayList<Double>> trainingData;
    static ArrayList<Double> output;
    static int numPoints = 20;
    static double stepSize = .08;
    static int numIterations = 100000;
    static int boxSize = 800;
    static int numAttributes = 1;
    static int degreePoly = 1;

    public void paint(Graphics g) {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }
        catch (Exception e) {

        }

        setBackground(Color.WHITE);
        RegressionH regression = new RegressionH(trainingData, output, stepSize, degreePoly);

        for(int p = 0; p < numIterations; p++) {
            g.clearRect(0, 0, boxSize, boxSize);
            g.setColor(Color.RED);

            for(int y = 0; y < boxSize; y++) {
                g.drawOval(y, boxSize - (int) regression.getYPredict(y), 2, 2);
            }

            g.setColor(Color.BLACK);

            for (int i = 0; i < numPoints; i++) {
                g.drawOval(trainingData.get(i).get(0).intValue(), (int) (boxSize - output.get(i)), 4, 4);
            }

            regression.takeStep();

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
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
        trainingData= new ArrayList<>();
        output = new ArrayList<>();
        for(int u = 0; u < numPoints; u++) {
            trainingData.add(new ArrayList<>());
            for (int i = 0; i < numAttributes; i++) {

                trainingData.get(u).add((double) rand.nextInt(boxSize));
                output.add(trainingData.get(u).get(i) - 50 + rand.nextInt(100));
            }
        }
    }

    public static void startDisplay() {
        DisplayH m = new DisplayH();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(boxSize,boxSize);
        //f.setLayout(null);
        f.setVisible(true);
    }

}
