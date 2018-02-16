import java.util.ArrayList;
import java.util.Random;

public class Regression {

    Random rand = new Random();
    double m;
    double b;
    ArrayList<Double> trainingDataX;
    ArrayList<Double> trainingDataY;
    double stepSize;

    public Regression(ArrayList<Double> trainingDataX, ArrayList<Double> trainingDataY, double stepSize) {

        m = rand.nextDouble() * 8 - 2;
        b = rand.nextDouble() * 500 - 250;
        this.trainingDataX = trainingDataX;
        this.trainingDataY = trainingDataY;
        this.stepSize = stepSize;
    }

    public void takeStep () {
        double previousLoss = getLoss();
        m += stepSize;
        if(getLoss() > previousLoss) {
            m -= stepSize * 2;
        }

        previousLoss = getLoss();
        b += stepSize * 100;
        if(getLoss() > previousLoss) {
            b -= stepSize * 200;
        }
    }

    public double getLoss() {
        double totalLoss = 0;
        for(int i = 0; i < trainingDataX.size(); i++) {
            totalLoss += Math.pow(trainingDataY.get(i) - (trainingDataX.get(i) * m + b), 2);
        }
        return totalLoss;
    }
}
