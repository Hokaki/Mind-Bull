package com.ewa.flamingraven;

import com.ewa.flamingraven.dao.AssignmentResultDAO;
import com.ewa.flamingraven.model.AssignmentResult;
import com.ewa.flamingraven.model.RawData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
           BufferedReader reader = new BufferedReader(new FileReader("files\\Kniestrekken4.txt"));
            String line = null;
            List<double[]> rawData = new LinkedList<double[]>();
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] rawdata = line.split(";");
                double[] data2 = new double[5];
                for(int i=0;i<rawdata.length;i++) {
                    data2[i] = Double.parseDouble(rawdata[i]);

                }
                rawData.add(data2);

            }
            RawData data = new RawData(rawData);
            AssignmentResult assignmentResult = new AssignmentResult();
            assignmentResult.setAvgTime((int)Math.floor(data.getAvgTime()));
            assignmentResult.setDate(new Date(data.getTime()));
            assignmentResult.setMaxSpeed(0);
            assignmentResult.setMaxTime((int)Math.floor(data.getMaxTime()));
            assignmentResult.setTotalTime((int)Math.floor(data.getTotalTime()));
            assignmentResult.setMinTime((int)Math.floor(data.getMinTime()));
            new AssignmentResultDAO().add(assignmentResult);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }



