package com.ewa.flamingraven;

import com.ewa.flamingraven.model.RawData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Joost
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("hallo");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files\\KniestrekkenAhmed.txt"));
            String line = null;
            List<double[]> rawData = new LinkedList<double[]>();
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
               String[] rawdata = line.split(",");
                double[] data2 = new double[6];
                for(int i=0;i<rawdata.length;i++) {
                    data2[i] = Double.parseDouble(rawdata[i]);

                }
                rawData.add(data2);

            }
            RawData data = new RawData(rawData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
