package Simulation;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[]args) {
        Hexagon test = new Hexagon(10);
        Path2D yeayea = test.getHexagon();
        System.out.println(yeayea.getBounds2D().toString());
        double[] point = test.getPoint();
        System.out.println("point: " + Arrays.toString(point));
        System.out.println("distance from center: " + test.getDistanceCenter(point));
        System.out.println("distance from hospital: " + test.getDistanceHospital(point));
        }




    }


