/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package Simulation;

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
    public static void main(String[] args) {
    	// Create an eventlist
	CEventList l = new CEventList();
	// A queue for each outer hexagon. qC is the inner hexagon
	Queue q1 = new Queue();
    Queue q2 = new Queue();
    Queue q3 = new Queue();
    Queue q4 = new Queue();
    Queue q5 = new Queue();
    Queue q6 = new Queue();
    Queue qC = new Queue();
	// A source for each hexagon
	Source s1 = new Source(q1,l,"Source 1", 0.4, 10);
    Source s2 = new Source(q2,l,"Source 2", 0.4, 10);
    Source s3 = new Source(q3,l, "Source 3", 0.4,10);
    Source s4 = new Source(q4,l, "Source 4", 0.4,10);
    Source s5 = new Source(q5,l, "Source 5", 0.4,10);
    Source s6 = new Source(q6,l, "Source 6", 0.4,10);
    Source sC = new Source(qC,l, "Source Center", 0.4,10);

	// A sink
	Sink si = new Sink("Sink 1");

	// five ambulances for each queue:
    Machine m1 = new Machine(q1,si,l,"Machine 1 Hexagon 1");
    Machine m2 = new Machine(q1,si,l,"Machine 2 Hexagon 1");
    Machine m3 = new Machine(q1,si,l,"Machine 3 Hexagon 1");
    Machine m4 = new Machine(q1,si,l,"Machine 4 Hexagon 1");
    Machine m5 = new Machine(q1,si,l,"Machine 5 Hexagon 1");

    Machine m6 = new Machine(q2,si,l,"Machine 1 Hexagon 2");
    Machine m7 = new Machine(q2,si,l,"Machine 2 Hexagon 2");
    Machine m8 = new Machine(q2,si,l,"Machine 3 Hexagon 2");
    Machine m9 = new Machine(q2,si,l,"Machine 4 Hexagon 2");
    Machine m10 = new Machine(q2,si,l,"Machine 5 Hexagon 2");

    Machine m11 = new Machine(q3,si,l,"Machine 1 Hexagon 3");
    Machine m12 = new Machine(q3,si,l,"Machine 2 Hexagon 3");
    Machine m13 = new Machine(q3,si,l,"Machine 3 Hexagon 3");
    Machine m14 = new Machine(q3,si,l,"Machine 4 Hexagon 3");
    Machine m15 = new Machine(q3,si,l,"Machine 5 Hexagon 3");

    Machine m16 = new Machine(q4,si,l,"Machine 1 Hexagon 4");
    Machine m17 = new Machine(q4,si,l,"Machine 2 Hexagon 4");
    Machine m18 = new Machine(q4,si,l,"Machine 3 Hexagon 4");
    Machine m19 = new Machine(q4,si,l,"Machine 4 Hexagon 4");
    Machine m20 = new Machine(q4,si,l,"Machine 5 Hexagon 4");

    Machine m21 = new Machine(q5,si,l,"Machine 1 Hexagon 5");
    Machine m22 = new Machine(q5,si,l,"Machine 2 Hexagon 5");
    Machine m23 = new Machine(q5,si,l,"Machine 3 Hexagon 5");
    Machine m24 = new Machine(q5,si,l,"Machine 4 Hexagon 5");
    Machine m25 = new Machine(q5,si,l,"Machine 5 Hexagon 5");

    Machine m26 = new Machine(q6,si,l,"Machine 1 Hexagon 6");
    Machine m27 = new Machine(q6,si,l,"Machine 2 Hexagon 6");
    Machine m28 = new Machine(q6,si,l,"Machine 3 Hexagon 6");
    Machine m29 = new Machine(q6,si,l,"Machine 4 Hexagon 6");
    Machine m30 = new Machine(q6,si,l,"Machine 5 Hexagon 6");

    Machine m31 = new Machine(qC,si,l,"Machine 1 Hexagon Center");
    Machine m32 = new Machine(qC,si,l,"Machine 2 Hexagon Center");
    Machine m33 = new Machine(qC,si,l,"Machine 3 Hexagon Center");
    Machine m34 = new Machine(qC,si,l,"Machine 4 Hexagon Center");
    Machine m35 = new Machine(qC,si,l,"Machine 5 Hexagon Center");




	// start the eventlist
	l.start(100); // 2000 is maximum time

    }
    
}

