package com.ewa.flamingraven;

import com.ewa.flamingraven.dao.AssignmentResultDAO;
import com.ewa.flamingraven.model.AssignmentResult;
import com.ewa.flamingraven.model.RawData;

import java.io.*;
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
            File folder = new File("files\\");
            File[] listOfFiles = folder.listFiles();
            for(int i=0;i<listOfFiles.length;i++) {
                BufferedReader reader = new BufferedReader(new FileReader(listOfFiles[i]));
                String line = null;
                List<double[]> rawData = new LinkedList<double[]>();
                line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] rawdata = line.split(";");
                    double[] data2 = new double[5];
                    for (int j = 0; j < rawdata.length; j++) {
                        data2[j] = Double.parseDouble(rawdata[j]);

                    }
                    rawData.add(data2);

                }
                AssignmentResultDAO assignmentResultDAO = new AssignmentResultDAO();
                RawData data = new RawData(rawData);
                AssignmentResult assignmentResult = new AssignmentResult();
                assignmentResult.setAvgTime((int) Math.floor(data.getAvgTime()));
                assignmentResult.setDate(new Date(data.getTime()));
                assignmentResult.setMaxSpeed(0);
                assignmentResult.setMaxTime((int) Math.floor(data.getMaxTime()));
                assignmentResult.setTotalTime((int) Math.floor(data.getTotalTime()));
                assignmentResult.setMinTime((int) Math.floor(data.getMinTime()));
                assignmentResult.setRepetitions(data.getRepetitions());
                assignmentResult.setId(assignmentResultDAO.nextId()+1);
                assignmentResultDAO.add(assignmentResult);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }



