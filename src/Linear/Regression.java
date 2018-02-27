package Linear;

import java.util.ArrayList;
import java.util.Random;

public class Regression {

    Random rand = new Random();
    double m;
    double b;
    ArrayList<Double> trainingDataX;
    ArrayList<Double> trainingDataY;
    double biasRate = 1;
    double weightRate = .000001;

    public Regression(ArrayList<Double> trainingDataX, ArrayList<Double> trainingDataY, double weightRate, double biasRate) {

        m = (rand.nextDouble() - 1) * 10;
        b = rand.nextInt(1000);
        this.trainingDataX = trainingDataX;
        this.trainingDataY = trainingDataY;
        this.weightRate = weightRate;
        this.biasRate = biasRate;

    }

    public void takeStep () {
        m -= .001;
        double previousLoss = getLoss();
        m += .002;
        double gradientMToLoss = (getLoss() - previousLoss) / .002;
        m -= .001;
        m = m - weightRate * gradientMToLoss;

        b -= .001;
        previousLoss = getLoss();
        b += .002;
        gradientMToLoss = (getLoss() - previousLoss) / .002;
        b -= .001;
        b = b - biasRate * gradientMToLoss;

    }

    public double getLoss() {
        double totalLoss = 0;
        for(int i = 0; i < trainingDataX.size(); i++) {
            totalLoss += Math.pow(trainingDataY.get(i) - (trainingDataX.get(i) * m + b), 2);
        }
        return totalLoss / trainingDataX.size();
    }
}
