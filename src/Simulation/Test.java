package Simulation;

import java.awt.*;

public class Test {
    public static void main(String[]args) {
        Hexagon test = new Hexagon(10);
        Polygon yeayea = test.getHexagon();
        System.out.println(yeayea.getBounds().toString());




    }

}
