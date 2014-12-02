package com.ewa.flamingraven.model;

import com.ewa.flamingraven.MovingAverage;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joost on 1-12-2014.
 */
public class RawData {
    private List<double[]> data = new LinkedList<double[]>();
    private List<double[]> dataMA = new LinkedList<double[]>(); //moving average version
    private int repetitions;
    private double totalTime;
    private int avgTime;
    private int minTime;
    private int maxTime;
    private int[] maxSpeed;

    private double[] maxHeight;
    private double[] minHeight;

    public RawData(List<double[]> data) {
        this.data = data;
        setDataMA();
        setRepetitions();
        setTotalTime();
        System.out.println(totalTime);
    }

    public List<double[]> getData() {
        return data;
    }

    public List<double[]> getDataMA() {
        return dataMA;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int[] getMaxSpeed() {
        return maxSpeed;
    }

    public void setData(List<double[]> data) {
        this.data = data;
    }


    public void setDataMA(List<double[]> dataMA) {
        this.dataMA = dataMA;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void setMaxSpeed(int[] maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    //the overloading private setters

    private void setDataMA() {
        int[] windowSizes = {5};
        List<double[]> dataMA = new LinkedList<double[]>();
        for (int windSize : windowSizes) {
            MovingAverage[] ma = {new MovingAverage(windSize),new MovingAverage(windSize),new MovingAverage(windSize)};
            for (int i = 0; i < data.size(); i++) {

                double[] data2 = new double[data.get(i).length];
                for (int j = 0; j < 3; j++) {
                    ma[j].newNum(data.get(i)[j]);
                    System.out.println("Next number = " + data.get(i)[j] + ", SMA = " + ma[j].getAvg());
                    data2[j] = ma[j].getAvg();
                }
                dataMA.add(data2);
            }
            System.out.println();
        }
        this.dataMA = dataMA;
    }

    private void setRepetitions() {
        setMaxHeight();
        setMinHeight();
        double[] avg = new double[3];
        double[] prevVal = new double[3];
        int[] intersections = new int[3];
        int repetitions=2000000000;

        for(int i=0;i<minHeight.length;i++) {
            avg[i] = (minHeight[i] + maxHeight[i])/2;
            System.out.println(avg[i]);
            System.out.println(minHeight[i] + " " + maxHeight[i]);
        }
        for (int i = 0; i < dataMA.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if(prevVal[j] < avg[j] && avg[j] < dataMA.get(i)[j]) {
                    intersections[j]++;
                }
                if(prevVal[j] > avg[j] && avg[j] > dataMA.get(i)[j]) {
                    intersections[j]++;
                }
                if(avg[j] == dataMA.get(i)[j]){
                    intersections[j]++;
                }
                prevVal[j] = dataMA.get(i)[j];
            }
        }

        for(int i=0;i<intersections.length;i++) {
            System.out.println(intersections[i]);
            if(repetitions > intersections[i]) {
                repetitions = intersections[i]/2;
            }
        }
        System.out.println(repetitions);
    }

    private void setTotalTime() {
        double start = data.get(0)[3];
        double end = data.get(data.size()-1)[3];
        totalTime = end - start;

    }

    private void setAvgTime() {
        this.avgTime = avgTime;
    }

    private void setMinTime() {
        this.minTime = minTime;
    }

    private void setMaxTime() {
        this.maxTime = maxTime;
    }

    private void setMaxSpeed() {
        this.maxSpeed = maxSpeed;
    }

    //extra vars
    private void setMaxHeight() {
        double[] maxHeight = {0, 0, 0};
        for (int i = 0; i < dataMA.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (dataMA.get(i)[j] > maxHeight[j]) {
                    maxHeight[j] = dataMA.get(i)[j];
                }
            }
        }
        this.maxHeight = maxHeight;
    }

    private void setMinHeight() {
        double[] minHeight = {0, 0, 0};
        for (int i = 0; i < dataMA.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (dataMA.get(i)[j] < minHeight[j]) {
                    minHeight[j] = dataMA.get(i)[j];
                }
            }
        }
        this.minHeight = minHeight;
    }
}
