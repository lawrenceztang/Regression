package HigherOrder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class RegressionH {

    Random rand = new Random();
    double stepSize;
    ArrayList<ArrayList<Double>> weights;
    ArrayList<ArrayList<Double>> trainingData;
    int attributeNum;
    int numExamples;
    int degreePolynomial;
    ArrayList<Double> output;
    double intercept;

    public RegressionH(ArrayList<ArrayList<Double>> trainingData, ArrayList<Double> output, double stepSize, int degreePolynomial) {
        this.trainingData = trainingData;
        attributeNum = trainingData.get(0).size();
        numExamples = trainingData.size();
        this.degreePolynomial = degreePolynomial;
        this.output = output;
        weights = new ArrayList<>();

        for (int i = 0; i < attributeNum; i++) {
            weights.add(new ArrayList<>());
            for (int u = 0; u < degreePolynomial; u++) {
                weights.get(i).add((double) rand.nextInt(5));
            }
        }

        this.stepSize = stepSize;
    }

    public void takeStep() {
        for (int i = 0; i < attributeNum; i++) {
            for (int p = 0; p < degreePolynomial; p++) {
                double previousLoss = getLoss();
                weights.get(i).set(p, weights.get(i).get(p) + stepSize);
                if (getLoss() > previousLoss) {
                    weights.get(i).set(p, weights.get(i).get(p) - stepSize * 2);
                }
            }
        }

    }

    public double getLoss() {
        double totalLoss = 0;

            for (int u = 0; u < numExamples; u++) {
                double yCoordPredict = getYPredict(trainingData.get(u));
                totalLoss += output.get(u) - yCoordPredict;
            }
        return totalLoss;
    }

    public double getYPredict(ArrayList<Double> inputs) {
        int yCoordPredict = 0;
        for (int a = 0; a < attributeNum; a++) {
            for(int i = 0; i < degreePolynomial; i++) {
                yCoordPredict += weights.get(a).get(i) * Math.pow(inputs.get(a), i);
            }
        }
        return yCoordPredict;
    }

    public double getYPredict(double input) {
        int yCoordPredict = 0;
            for(int i = 0; i < degreePolynomial; i++) {
                yCoordPredict += weights.get(0).get(i) * Math.pow(input, i);
            }
        return yCoordPredict;
    }
}
