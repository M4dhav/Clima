package dev.avi.AQI;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class AQIPredictor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> aqiList = new ArrayList<>();

        int n = 5; // number of days in the data
        System.out.println("Enter the AQI values for the last " + n + " days:");
        for (int i = 0; i < n; i++) {
            int aqi = scanner.nextInt();
            aqiList.add(aqi);
        }

        SimpleRegression regression = new SimpleRegression();
        for (int i = 0; i < n; i++) {
            regression.addData(i, aqiList.get(i));
        }

        double predictedAQI = regression.predict(n);
        System.out.println("The predicted AQI for tomorrow is: " + predictedAQI);
    }
}
