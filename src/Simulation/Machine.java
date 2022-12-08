package Simulation;



/**
 *	Machine in a factory
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Machine implements CProcess,ProductAcceptor
{
	/** Product that is being handled  */
	private Product product;
	/** Eventlist that will manage events */
	private final CEventList eventlist;
	/** Queue from which the machine has to take products */
	private Queue queue;
	/** Sink to dump products */
	private ProductAcceptor sink;
	/** Status of the machine (b=busy, i=idle) */
	private char status;
	/** Machine name */
	private final String name;
	/** Mean processing time */
	private double meanProcTime;
	/** Processing times (in case pre-specified) */
	private double[] processingTimes;
	/** Processing time iterator */
	private int procCnt;
	/** 0 if at the ambulance center, 1 if at the hospital*/
	private int location;

	/** hexagon used to calculated distances*/
	private Hexagon hexagon;
	

	/**
	*	Constructor
	*        Service times are exponentially distributed with mean 30
	*	@param q	Queue from which the machine has to take products
	*	@param s	Where to send the completed products
	*	@param e	Eventlist that will manage events
	*	@param n	The name of the machine
	*/
	public Machine(Queue q, ProductAcceptor s, CEventList e, String n)
	{
		status='i';
		queue=q;
		sink=s;
		eventlist=e;
		name=n;
		meanProcTime=30;
		queue.askProduct(this);
		location = 0;
		hexagon = new Hexagon(10);
	}

	/**
	*	Constructor
	*        Service times are exponentially distributed with specified mean
	*	@param q	Queue from which the machine has to take products
	*	@param s	Where to send the completed products
	*	@param e	Eventlist that will manage events
	*	@param n	The name of the machine
	*        @param m	Mean processing time
	*/
	public Machine(Queue q, ProductAcceptor s, CEventList e, String n, double m)
	{
		status='i';
		queue=q;
		sink=s;
		eventlist=e;
		name=n;
		meanProcTime=m;
		queue.askProduct(this);
		location = 0;
		hexagon = new Hexagon(10);
	}
	
	/**
	*	Constructor
	*        Service times are pre-specified
	*	@param q	Queue from which the machine has to take products
	*	@param s	Where to send the completed products
	*	@param e	Eventlist that will manage events
	*	@param n	The name of the machine
	*        @param st	service times
	*/
	public Machine(Queue q, ProductAcceptor s, CEventList e, String n, double[] st)
	{
		status='i';
		queue=q;
		sink=s;
		eventlist=e;
		name=n;
		meanProcTime=-1;
		processingTimes=st;
		procCnt=0;
		queue.askProduct(this);
		location = 0;
		hexagon = new Hexagon(10);
	}

	/**
	*	Method to have this object execute an event
	*	@param type	The type of the event that has to be executed
	*	@param tme	The current time
	*/
	public void execute(int type, double tme)
	{
		switch	(type) {
			case 0:
				product.stamp(tme,"arrival complete",name);
				System.out.println("arrival complete at time = " + tme);
				break;

			case 1:
				product.stamp(tme,"processing complete", name);
				System.out.println("processing complete at time = " + tme);
				break;

			case 2:
				product.stamp(tme,"drop off complete", name);
				location = 1;
				sink.giveProduct(product);
				product=null;
				status = 'i';

				if(!queue.askProduct(this)) {
					//drive back to the top of the hexagon if there is no patient waiting
					eventlist.add(this, 3, tme + Math.sqrt(3) * 10);
					status = 'b';
				}
				System.out.println("drop off completed at time = " + tme);
				break;

			case 3:
				product.stamp(tme,"drove back to post", name);
				location = 0;
				status = 'i';
				System.out.println("drove back to post at time = " + tme);
		}

		/*
		// show arrival
		System.out.println("Product finished at time = " + tme);
		// Remove product from system
		product.stamp(tme,"Production complete",name);
		sink.giveProduct(product);
		product=null;
		// set machine status to idle
		status='i';
		// Ask the queue for products
		queue.askProduct(this); */
	}
	
	/**
	*	Let the machine accept a product and let it start handling it
	*	@param p	The product that is offered
	*	@return	true if the product is accepted and started, false in all other cases
	*/
        @Override
	public boolean giveProduct(Product p)
	{
		// Only accept something if the machine is idle
		if(status=='i')
		{
			// accept the product
			product=p;
			// mark starting time
			product.stamp(eventlist.getTime(),"Pickup started",name);
			// start production
			startProduction();
			// Flag that the product has arrived
			return true;
		}
		// Flag that the product has been rejected
		else return false;
	}
	
	/**
	*	TODO: implement Erlang-3 distribution for processing times instead of drawRandomExponential
	 *
	*/
	private void startProduction()
	{
		double processTime = drawErlang3();
		//the case where the ambulance is at the center of the hexagon
		if(location == 0) {
			double tme = eventlist.getTime();
			//add an arrival event
			double tmeArr = tme+hexagon.getDistanceCenter(product.getPosition());
			eventlist.add(this,0,tmeArr);
			//add a processed event:
			double tmeProc = tmeArr + processTime;
			eventlist.add(this,1,tmeProc);
			//add a drop off event
			double tmeDrop = tmeProc + hexagon.getDistanceHospital(product.getPosition());
			eventlist.add(this,2,tmeDrop);
		}
		//the case where the ambulance is already at the hospital and moves straight to the patient
		if(location == 1) {
			double tme = eventlist.getTime();
			//add a pickup event
			double tmeArr = tme+hexagon.getDistanceHospital(product.getPosition());
			eventlist.add(this,0,tmeArr);
			//add a processed event:
			double tmeProc = tmeArr + processTime;
			eventlist.add(this,1,tmeProc);
			//add a drop off event
			double tmeDrop = tmeProc + hexagon.getDistanceHospital(product.getPosition());
			eventlist.add(this,2,tmeDrop);
		}
		/*
		// generate duration
		if(meanProcTime>0)
		{
			double duration = drawRandomExponential(meanProcTime);
			// Create a new event in the eventlist
			double tme = eventlist.getTime();
			eventlist.add(this,0,tme+duration); //target,type,time
			// set status to busy
			status='b';
		}
		else
		{
			if(processingTimes.length>procCnt)
			{
				eventlist.add(this,0,eventlist.getTime()+processingTimes[procCnt]); //target,type,time
				// set status to busy
				status='b';
				procCnt++;
			}
			else
			{
				eventlist.stop();
			}
		} */
	}

	public static double drawRandomExponential(double mean)
	{
		// draw a [0,1] uniform distributed number
		double u = Math.random();
		// Convert it into a exponentially distributed random variate with mean 33
		double res = -mean*Math.log(u);
		return res;
	}

	/**
	 * draw random erlang-3
	 */
	public static double Erlangfct(double t,int n){

		return Math.exp(-t)*(Math.pow(t,n-1))/fact(n-1);
	}

	public static int fact(int n){
		if((n==1)||(n==0)){
			return 0;
		}
		else return n*fact(n-1);
	}

	public static double drawErlang3() {
	return 0;
}
}