package Simulation;
import java.awt.Polygon;
import java.lang.Math.*;
import java.util.Arrays;

public class Hexagon {
    //diameter of the circle surrounding the hexagon
    private double diameter;
    //center of the hexagon
    private double[] center;
    Polygon hexagon;


    public Hexagon(int diameter) {
        this.diameter = diameter;
        //set the center so that 0,0 is at the bottom left corner of the polygon bound
        center = new double[] {Math.sqrt(3)*diameter/4,diameter/2};
        hexagon = new Polygon();
        //each external angle of a hexagon is 60
        //we get our corner points at angles 0, 60, 120, 180, 240 and 300 with the origin
        for (int i = 0; i < 7; i++) {
            //add a x and a y coordinate for each corner point to the hexagon class.
            hexagon.addPoint((int) (center[0]+diameter / 2 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (center[1]+diameter / 2 * Math.sin(i * 2 * Math.PI / 6)));
        }

        System.out.println("center is:" + Arrays.toString(center));
    }

    public Polygon getHexagon(){
        return hexagon;
    }
    /**
     * use acceptance/rejection sampling to select a point in the hexagon uniformly:
     *
     * uniformly select a value between 0 and the diameter for both the x and y coordinate to simulate
     * uniformly selecting from a square.
     * check if the coordinate this generates is in bounds of the hexagon
     * if yes, return the coordinate you found
     @return a uniformly selected point within the hexagon
     */
    public double[] getPoint(){
        double[] coordinates = {};
        boolean valid = false;
        while(!valid) {
            double yValue = Math.random() * diameter;
            double xValue = Math.random() * diameter;
            if(hexagon.contains(xValue,yValue)){
                valid = true;
                coordinates = new double[]{xValue, yValue};
            }
        }
        return coordinates;
    }

     /*we can remove complexity from our simulation to not need the exact cartesian coordinates of
         our hexagonal grid system due to the two following observations:
         -  Manhattan distance from any point in the hexagon to the hospital is equal to the horizontal
            distance it is removed from the ambulance post and the vertical distance from the bottom
            of the hexagon + the dimension of the hexagon/2.

         -  Our hexagonal grid is symmetrical. There are no differences in distances between the different
            hexagons.

         Therefore, we can simplify our simulations to 6 individual hexagons and calculate distances individually


        */
    public double getDistanceAmbulance() {
        return 0;
    }
}
