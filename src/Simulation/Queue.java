package Simulation;

import java.util.ArrayList;

/**
 *	Queue that stores products until they can be handled on a machine machine
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Queue implements ProductAcceptor
{
	/** List in which the products are kept */
	private ArrayList<Product> rowA1;
	private ArrayList<Product> rowB;
	private ArrayList<Product> rowA2;
	
	/** Requests from machine that will be handling the products */
	private ArrayList<Machine> requests;
	
	/**
	*	Initializes the queue and introduces a dummy machine
	*	the machine has to be specified later
	*/
	public Queue()
	{
		rowA1 = new ArrayList<>();
		rowA2 = new ArrayList<>();
		rowB = new ArrayList<>();
		requests = new ArrayList<>();
	}

	/**
	 * returns true if there is a product in waiting, false if there is not.
	 * @return
	 */
	public boolean peekProduct(){
		if(rowA1.size()>0||rowA2.size()>0||rowB.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	*	Asks a queue to give a product to a machine
	*	True is returned if a product could be delivered; false if the request is queued
	*/
	public boolean askProduct(Machine machine)
	{
		// This is only possible with a non-empty queue
		if(rowA1.size()>0)
		{
			// If the machine accepts the product
			if(machine.giveProduct(rowA1.get(0)))
			{
				rowA1.remove(0);// Remove it from the queue
				return true;
			}
			else
				return false; // Machine rejected; don't queue request
		}
		else if (rowB.size()>0)
		{
			if(machine.giveProduct(rowB.get(0)))
			{
				rowB.remove(0);// Remove it from the queue
				return true;
			}
			else
				return false; // Machine rejected; don't queue request
		}
		else if (rowA2.size()>0)
		{
			if(machine.giveProduct(rowA2.get(0)))
			{
				rowA2.remove(0);// Remove it from the queue
				return true;
			}
			else
				return false; // Machine rejected; don't queue request
		}
		else
		{
			requests.add(machine);
			return false; // queue request
		}
	}
	
	/**
	*	Offer a product to the queue
	*	It is investigated whether a machine wants the product, otherwise it is stored
	*/
	public boolean giveProduct(Product p)
	{
		// Check if the machine accepts it
		if(requests.size()<1)
			switch(p.getPriority()){
				case 1:
					rowA1.add(p);
					break;
				case 2:
					rowA2.add(p);
					break;
				case 3:
					rowB.add(p);
					break;
			}
			// storing in appropriate queue if need be
		else
		{
			boolean delivered = false;
			while(!delivered & (requests.size()>0))
			{
				delivered=requests.get(0).giveProduct(p);
				// remove the request regardless of whether or not the product has been accepted
				requests.remove(0);
			}
			if(!delivered)
			if(requests.size()<1)
			switch(p.getPriority()){
				case 1:
					rowA1.add(p);
					break;
				case 2:
					rowA2.add(p);
					break;
				case 3:
					rowB.add(p);
					break;
			}
		}
		return true;
	}
}