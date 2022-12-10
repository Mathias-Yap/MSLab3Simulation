/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package Simulation;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Simulation {

    public CEventList list;
    public Queue queue;
    public Source source;
    public Sink sink;
    public Machine mach;
	

        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
    	// Create an eventlist
	CEventList l = new CEventList();
	// A queue for each outer hexagon. qC is the inner hexagon
	Queue q1 = new Queue();
    Queue q2 = new Queue();
    Queue q3 = new Queue();
    Queue q4 = new Queue();
    Queue q5 = new Queue();
    Queue q6 = new Queue();
    //Queue qC = new Queue();
    /*naming for stations: (x,y). x is station type. 0 for source and 1-5 for ambulance 1-5 respectively
      y is the hexagon number. 1-6 for each outer hexagon, 0 for an inner hexagon.
    */
	// A source for each hexagon
	Source s1 = new Source(q1,l,"0,1", 0.4, 10);
    Source s2 = new Source(q2,l,"0,2", 0.4, 10);
    Source s3 = new Source(q3,l, "0,3", 0.4,10);
    Source s4 = new Source(q4,l, "0,4", 0.4,10);
    Source s5 = new Source(q5,l, "0,5", 0.4,10);
    Source s6 = new Source(q6,l, "0,6", 0.4,10);
   // Source sC = new Source(qC,l, "0,0", 0.4,10);

	// A sink
	Sink si = new Sink("Sink 1");

	// five ambulances for each queue:
    Machine m1 = new Machine(q1,si,l,"1,1");
    Machine m2 = new Machine(q1,si,l,"2,1");
    //Machine m3 = new Machine(q1,si,l,"3,1");
   // Machine m4 = new Machine(q1,si,l,"4,1");
    //Machine m5 = new Machine(q1,si,l,"5,1");

    Machine m6 = new Machine(q2,si,l,"1,2");
    Machine m7 = new Machine(q2,si,l,"2,2");
    //Machine m8 = new Machine(q2,si,l,"3,2");
    //Machine m9 = new Machine(q2,si,l,"4,2");
    //Machine m10 = new Machine(q2,si,l,"5,2");

    Machine m11 = new Machine(q3,si,l,"1,3");
    Machine m12 = new Machine(q3,si,l,"2,3");
   // Machine m13 = new Machine(q3,si,l,"3,3");
    //Machine m14 = new Machine(q3,si,l,"4,3");
    //Machine m15 = new Machine(q3,si,l,"5,3");

    Machine m16 = new Machine(q4,si,l,"1,4");
    Machine m17 = new Machine(q4,si,l,"2,4");
    //Machine m18 = new Machine(q4,si,l,"3,4");
   // Machine m19 = new Machine(q4,si,l,"4,4");
    //Machine m20 = new Machine(q4,si,l,"5,4");

    Machine m21 = new Machine(q5,si,l,"1,5");
    Machine m22 = new Machine(q5,si,l,"2,5");
    //Machine m23 = new Machine(q5,si,l,"3,5");
    //Machine m24 = new Machine(q5,si,l,"4,5");
    //Machine m25 = new Machine(q5,si,l,"5,5");

    Machine m26 = new Machine(q6,si,l,"1,6");
    Machine m27 = new Machine(q6,si,l,"2,6");
    //Machine m28 = new Machine(q6,si,l,"3,6");
    //Machine m29 = new Machine(q6,si,l,"4,6");
    //Machine m30 = new Machine(q6,si,l,"5,6");

        // ambulances in the center, the boolean edits travel times!
    /*Machine m31 = new Machine(qC,si,l,"1,0", true);
    Machine m32 = new Machine(qC,si,l,"2,0", true);
    Machine m33 = new Machine(qC,si,l,"3,0", true);
    Machine m34 = new Machine(qC,si,l,"4,0",true);
    Machine m35 = new Machine(qC,si,l,"5,0",true);*/




	// start the eventlist
	l.start(1440); // 2000 is maximum time
        si.ToCSV("OuterHex2.csv");
    }
    
}

