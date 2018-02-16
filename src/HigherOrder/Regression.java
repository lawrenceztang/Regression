package HigherOrder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Regression {

    Random rand = new Random();
    double stepSize;
    ArrayList<ArrayList<Double>> weights;
    ArrayList<ArrayList<Double>> trainingData;
    int inputNum;

    public Regression(ArrayList<ArrayList<Double>> trainingData, double stepSize, int degreePolynomial) {
        this.trainingData = trainingData;
        inputNum = trainingData.get(0).size();

        for(int i = 0; i < inputNum; i++) {
            for(int u = 0; u < degreePolynomial; u++) {
                weights.get(i).add((double) rand.nextInt(5));
            }
        }


        this.stepSize = stepSize;
    }

    public void takeStep () {
        for(int i = 0; i < weights.size(); i++) {
            double previousLoss = getLoss();
            weights.set(i, weights.get(i) + stepSize);
            if (getLoss() > previousLoss) {
                weights.set(i, weights.get(i) - stepSize * 2);
            }
        }

    }

    public double getLoss() {
        double totalLoss = 0;
        for(int u = 0; u < trainingData.size(); u++) {
            int yCoord = 0;
            for (int i = 0; i < trainingData.get(0).size(); i++) {
                yCoord += Math.pow(trainingData.get(u).get(i) * weights.get(i), 2);
            }
        }
        return totalLoss;
    }
}
