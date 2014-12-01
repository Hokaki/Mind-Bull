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
    private int totalTime;
    private int avgTime;
    private int minTime;
    private int maxTime;
    private int[] maxSpeed;

    private int[] maxHeight;
    private int[] minHeigth;

    public RawData(List<double[]> data) {
        this.data = data;
        setDataMA();
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

    public int getTotalTime() {
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
        int[] windowSizes = {13};
        for (int windSize : windowSizes) {
            MovingAverage ma = new MovingAverage(windSize);
            for(int i=0;i<data.size();i++) {
                List<double[]> dataMA = new LinkedList<double[]>();
                double[] data2 = new double[data.get(i).length];
                for(int j=0;j<data.get(i).length;j++) {
                    ma.newNum(data.get(i)[j]);
                    System.out.println("Next number = " + data.get(i)[j] + ", SMA = " + ma.getAvg());
                    data2[j] = ma.getAvg();
                }
                dataMA.add(data2);
            }
            System.out.println();
        }
    }

    private void setRepetitions() {
        setMaxHeight();
        setMinHeigth();
    }

    private void setTotalTime() {
        this.totalTime = totalTime;
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


    }
    private void setMinHeigth() {

    }
}
