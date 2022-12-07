package Simulation;
import java.awt.geom.Path2D;
import java.lang.Math.*;
import java.util.Arrays;

public class Hexagon {
    //diameter of the circle surrounding the hexagon
    private double diameter;
    //center of the hexagon
    private double[] center;
    Path2D hexagon;

    /**
     * constructor
     * creates a path2D object for the hexagon with 0,0 set at the bottom middle of the hexagon.
     * @param diameter
     */
    public Hexagon(double diameter) {
        this.diameter = diameter;
        //set the center so that 0,-5 is where the hospital is
        center = new double[] {0,(Math.sqrt(3)*(diameter/2))/2};
        //each external angle of a hexagon is 60
        //we get our corner points at angles 0, 60, 120, 180, 240 and 300 with the origin
        //draw the hexagon using path2D
        hexagon = new Path2D.Double();
        boolean isFirst = true;
        for (int i = 0; i < 7; i++) {
            //add a x and a y coordinate for each corner point to the hexagon class.
            double x = (center[0] + (diameter/2) * Math.cos(i * 2 * Math.PI / 6));
            double y = (center[1]+(diameter/2) * Math.sin(i * 2 * Math.PI / 6));
            if(isFirst){
                hexagon.moveTo(x,y);
                isFirst = false;
            } else {
                hexagon.lineTo(x,y);
            }
        }
        hexagon.closePath();

        System.out.println("center is:" + Arrays.toString(center));
    }

    public Path2D getHexagon(){
        return hexagon;
    }
    /**
     * use acceptance/rejection sampling to select a point in the hexagon uniformly:
     *
     * uniformly select a value within a bounding box of the hexagon
     * check if the coordinate this generates is in bounds of the hexagon
     * if yes, return the coordinate you found
     @return a uniformly selected point within the hexagon
     */
    public double[] getPoint(){
        double[] coordinates = {};
        boolean valid = false;
        while(!valid) {
            double yValue = Math.random() * diameter;
            double xValue = Math.random() * diameter-5;
            if(hexagon.contains(xValue,yValue)){
                valid = true;
                coordinates = new double[]{xValue, yValue};
            }
        }
        return coordinates;
    }

     /*
      * we can remove complexity from our simulation to not need the exact cartesian coordinates of
         our hexagonal grid system due to the two following observations:
         -  Manhattan distance from any point in the hexagon to the hospital is equal to the horizontal
            distance it is removed from the ambulance post and the vertical distance from the bottom
            of the hexagon + the dimension of the hexagon/2.

         -  Our hexagonal grid is symmetrical. There are no differences in distances between the different
            hexagons.

         Therefore, we can simplify our simulations to 6 individual hexagons and calculate distances individually

        !! be careful, there is one exception to this. The center hexagon will only need to calculate distances to the
        center and will not need to use getDistanceHospitalOuter.
        */

    /**
     * @param point a point within the hexagon
     * @return the Manhattan distance from the center of the hexagon to the point
     */
    public double getDistanceCenter(double[] point) {
        double distance = 0;
        // x distance from ambulance post to hospital
        distance += Math.abs(center[0]-point[0]);
        // y distance from ambulance post to hospital
        distance += Math.abs(center[1]-point[1]);
        return distance;
    }

    /**
     *
     * @param point a point within the hexagon. Assume that the hexagon is not the one containing
     *             the hospital
     * @return the Manhattan distance from the point to the hospital in the center of the hexagonal grid
     */
    public double getDistanceHospital(double[] point) {
        double distance = 0;
        // x distance from patient to hospital
        distance += Math.abs(point[0]);
        // y distance from patient to bottom of hexagon area
        distance += Math.abs(point[1]);
        // y distance from bottom of hexagon area to hospital
        distance += (Math.sqrt(3)*(diameter/2))/2;
        return distance;
    }
}
