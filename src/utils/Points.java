package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Points {


    public ArrayList<Point> point;

    public Points() {
        point = new ArrayList<Point>();
    }


    public void loadFromFile(String filename) throws IOException {
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(filename));

        String line;
        point = new ArrayList<Point>();
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] buf = line.split(" ");
            if (buf.length == 2) {
                point.add(new Point(Integer.parseInt(buf[0]), Integer.parseInt(buf[1])));
                i++;
            }
        }
        br.close();
    }


    public void saveToFile(String filename) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (Point item : point) {
                bw.write(item.getX() + " " + item.getY() + "\n");
                System.out.println(item.getX() + " " + item.getY());
            }

            bw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
