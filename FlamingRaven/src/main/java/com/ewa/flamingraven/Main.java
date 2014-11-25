package com.ewa.flamingraven;

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
            List<String[]> data = new LinkedList<String[]>();
            while ((line = reader.readLine()) != null) {
                String rawData[] = line.split(",");
                int count=0;
                String[] temp = null;
                for(int i=5;i<rawData.length;i++) {
                    switch (count) {
                        case 0:
                            temp[0]=rawData[i];
                            break;
                        case 1:
                            temp[1]=rawData[i];
                            break;
                        case 2:
                            temp[2]=rawData[i];
                            break;
                        case 3:
                            temp[3]=rawData[i];
                            break;
                        case 5:
                            data.add(temp);
                            count=-1;
                            break;

                    }
                    count++;
                }
            }
            for (int i=0;i<data.size();i++) {
                System.out.println(data.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
