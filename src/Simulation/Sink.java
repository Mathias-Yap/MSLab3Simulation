package Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *	A sink
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Sink implements ProductAcceptor
{
	private File mycsv;
	/** All products are kept */
	private ArrayList<Product> products;
	/** All properties of products are kept */
	private ArrayList<Integer> numbers;
	private ArrayList<Double> times;
	private ArrayList<Integer> events;
	private ArrayList<String> stations;
	/** Counter to number products */
	private ArrayList<Integer> priority;
	private int number;
	/** Name of the sink */
	private String name;
	
	/**
	*	Constructor, creates objects
	*/
	public Sink(String n)
	{
		name = n;
		products = new ArrayList<>();
		numbers = new ArrayList<>();
		times = new ArrayList<>();
		events = new ArrayList<>();
		stations = new ArrayList<>();
		priority = new ArrayList<>();
		number = 0;
	}
	
        @Override
	public boolean giveProduct(Product p)
	{
		number++;
		products.add(p);
		// store stamps
		ArrayList<Double> t = p.getTimes();
		ArrayList<Integer> e = p.getEvents();
		ArrayList<String> s = p.getStations();
		for(int i=0;i<t.size();i++)
		{
			numbers.add(number);
			times.add(t.get(i));
			events.add(e.get(i));
			stations.add(s.get(i));
			priority.add(p.getPriority());
		}
		return true;
	}
	
	public int[] getNumbers()
	{
		numbers.trimToSize();
		int[] tmp = new int[numbers.size()];
		for (int i=0; i < numbers.size(); i++)
		{
			tmp[i] = (numbers.get(i)).intValue();
		}
		return tmp;
	}

	public double[] getTimes()
	{
		times.trimToSize();
		double[] tmp = new double[times.size()];
		for (int i=0; i < times.size(); i++)
		{
			tmp[i] = (times.get(i)).doubleValue();
		}
		return tmp;
	}

	public void ToCSV(String name) throws FileNotFoundException {
		mycsv=new File(name);
		PrintWriter csvwriter = new PrintWriter(mycsv);

		for (int i=0;i<numbers.size();i++){
			csvwriter.write(numbers.get(i)+","+times.get(i)+","+events.get(i)+","+stations.get(i)+","+priority.get(i)+"\n");
		}

		csvwriter.close();
	}

	public String[] getEvents()
	{
		String[] tmp = new String[events.size()];
		tmp = events.toArray(tmp);
		return tmp;
	}

	public String[] getStations()
	{
		String[] tmp = new String[stations.size()];
		tmp = stations.toArray(tmp);
		return tmp;
	}
}