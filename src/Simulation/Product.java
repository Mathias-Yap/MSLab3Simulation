package Simulation;

import java.util.ArrayList;
/**
 *	Product that is send trough the system
 *	@author Joel Karel
 *	@version %I%, %G%
 */
class Product
{
	/** Stamps for the products */
	private ArrayList<Double> times;

	/**	event types:
	 Creation = 0;
	Pickup start = 1;
	Ambulance arrives at patient = 2;
	Patient processed = 3;
	Patient dropped off = 4;*/

	private ArrayList<Integer> events;
	private ArrayList<String> stations;
	private int priority;

	private double[] position;

	private int hexagon;
	
	/** 
	*	Constructor for the product
	*	Mark the time at which it is created
	 * @param position the position within the hexagon the patient originates from
	 * @param hexagon the number of the hexagon the product is located in
	*	@param create The current time
	*/
	public Product(double[] position, int hexagon)
	{
		times = new ArrayList<>();
		events = new ArrayList<>();
		stations = new ArrayList<>();
		this.position = position;
		this.hexagon = hexagon;
		priority = 1 + (int)(Math.random() * ((3 - 1) + 1));


	}
	
	
	public void stamp(double time,int event,String station)
	{
		times.add(time);
		events.add(event);
		stations.add(station);
	}
	
	public ArrayList<Double> getTimes()
	{
		return times;
	}

	public ArrayList<Integer> getEvents()
	{
		return events;
	}

	public ArrayList<String> getStations()
	{
		return stations;
	}

	public double[] getPosition() { return position; }
	
	public double[] getTimesAsArray()
	{
		times.trimToSize();
		double[] tmp = new double[times.size()];
		for (int i=0; i < times.size(); i++)
		{
			tmp[i] = (times.get(i)).doubleValue();
		}
		return tmp;
	}

	public String[] getEventsAsArray()
	{
		String[] tmp = new String[events.size()];
		tmp = events.toArray(tmp);
		return tmp;
	}

	public String[] getStationsAsArray()
	{
		String[] tmp = new String[stations.size()];
		tmp = stations.toArray(tmp);
		return tmp;
	}


	public int getPriority()
	{
		return priority;
	}
}