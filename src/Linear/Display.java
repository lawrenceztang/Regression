package Linear;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Display extends Canvas{

    static Random rand;
    static ArrayList<Double> trainingDataX;
    static ArrayList<Double> trainingDataY;
    static int numPoints = 20;
    static int numIterations = 999999999;
    static int boxSize = 1000;
    static int pointVariation = 50;
    static double m;
    static double b;
    static double weightRate = .000001;
    static double biasRate = 1;

    public void paint(Graphics g) {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }
        catch (Exception e) {

        }

        setBackground(Color.WHITE);
        Regression regression = new Regression(trainingDataX, trainingDataY, weightRate, biasRate);

        for(int p = 0; p < numIterations; p++) {
            g.clearRect(0, 0, boxSize, boxSize);
            g.setColor(Color.RED);
            g.drawLine(0, (int) (boxSize - regression.b), boxSize, (int) (boxSize - boxSize * regression.m - regression.b));
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
        m = rand.nextDouble() * 5;
        b = rand.nextInt(boxSize) - 200;

        Scanner scan = new Scanner(System.in);
        System.out.println("Default settings? (y/n)");

        if(scan.nextLine().equals("n")) {
            System.out.println("How many points?");
            numPoints = scan.nextInt();
            System.out.println("How many iterations?");
            numIterations = scan.nextInt();
            System.out.println("What is the variation of training data?");
            pointVariation = scan.nextInt();
            System.out.println("What is the learning rate for the weight? (default is .000001)");
            weightRate = scan.nextInt();
            System.out.println("What is the learning rate for the bias? (default is 1)");
            biasRate = scan.nextInt();
        }
        System.out.println("Starting...");

        getPoints();

        startDisplay();

    }

    public static void getPoints() {
        trainingDataX = new ArrayList<>();
        trainingDataY = new ArrayList<>();
        for(int i = 0; i < numPoints; i++) {
            trainingDataX.add((double) rand.nextInt(boxSize));
            trainingDataY.add(trainingDataX.get(i) * m - pointVariation + rand.nextInt(pointVariation * 2) - b);
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
