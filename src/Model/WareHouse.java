package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
// ADAM
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import Utils.E_Region;
public class WareHouse implements Comparable<WareHouse> , Serializable{
	
	public static int transatctionId = 1;
	/**Key: id of warehouse*/
	private int warehouseId;
	private E_Region region;
	/**
	 * Warehouse address location
	 */
	Address address;
	/**
	 * list of parcels in warehouse
	 */
	ArrayList<Parcel> parcels;
	
	LinkedList<Parcel> parcelsListSortedByPrice; //Order parcels per price. Key and Value are the same.
	//KeySet not accept two items with the same cost. so chose this DS.
	
	
	LinkedList<Parcel> parcelsByReceiverHouseNumber; //Parcels ordered by receiver housenumber. 
	
	
	ArrayList<Parcel> parcelsByOrder; //Used to save index of parcel.
	
	
	LinkedList<Parcel> parcelsByPriceAndIndex;// Parcels saved in PQ by order and price. 
	//Currently, the comparator use only order. price commented. can use but need to verify output correctness. 
	
	
	HashMap<Integer, HashMap<Truck, ArrayList<Parcel>>> transactionsHashMap;
	//All transaction.
	//Integer: Transaction indexer.
	//Map: The truck and the parcels which the truck dismantled.
	
	//================================================
	
//	ArrayList<Parcel> parcels = new ArrayList<>();
//	LinkedList<Parcel> parcelsListSortedByPrice = new LinkedList<Parcel>();	
//	PriorityQueue<Parcel> parcelsByReceiverHouseNumber = new PriorityQueue<Parcel>(new CompareByHouseNumber() );
//	HashMap<Integer, HashMap<Truck, ArrayList<Parcel>>> transactionsHashMap = new HashMap<Integer, HashMap<Truck,ArrayList<Parcel>>>();	
//	PriorityQueue<Parcel> 	parcelsByPriceAndIndex = new PriorityQueue<Parcel>(new CompareByPriceandIndex() );
//	ArrayList<Parcel>	parcelsByOrder = new ArrayList<Parcel>();
	class CompareByHouseNumber implements Comparator<Parcel>,Serializable {
		@Override
		public int compare(Parcel arg0, Parcel arg1) {
			return arg0.getReceiver().getAddress().getHouseNumber()-arg1.getReceiver().getAddress().getHouseNumber();
//			return 1;
		}
		
	} 
	
	class CompareByPriceandIndex implements Comparator<Parcel>,Serializable {
		@Override
		public int compare(Parcel arg0, Parcel arg1) {
	double val1 = 100*getParcelIndex(arg0) + arg0.getCurrentCost();
			double val2 = 100*getParcelIndex(arg1) + arg1.getCurrentCost();
			
			return ((int)(getParcelIndex(arg0)-getParcelIndex(arg1)));
//			return 1;
		}
		
	}
	
	public WareHouse(int warehouseId,Address address) 
	{
		this.warehouseId=warehouseId;
		this.address = address;
		parcels = new ArrayList<>();
		this.region = this.address.city.getRegion();
		parcelsListSortedByPrice = new LinkedList<Parcel>();

		
		parcelsByReceiverHouseNumber = new LinkedList<Parcel>( );
		Collections.sort(parcelsByReceiverHouseNumber,new CompareByHouseNumber());

		transactionsHashMap = new HashMap<Integer, HashMap<Truck,ArrayList<Parcel>>>();
		
		
		
		parcelsByPriceAndIndex = new LinkedList<Parcel>( );
		Collections.sort(parcelsByPriceAndIndex,new CompareByPriceandIndex());

		
		
		parcelsByOrder = new ArrayList<Parcel>();
//		this.warehouseId=warehouseId;
//		this.address = address;
//		parcels = new ArrayList<>();
//		this.region = this.address.city.getRegion();
//		transactionsHashMap = new HashMap<Integer, HashMap<Truck,ArrayList<Parcel>>>();
//		
//		parcelsListSortedByPrice = new LinkedList<Parcel>();
//		
//		
//		parcelsByReceiverHouseNumber = new PriorityQueue<Parcel>(new Comparator<Parcel>() {
//			@Override
//			public int compare(Parcel arg0, Parcel arg1) {
//				return arg0.getReceiver().getAddress().getHouseNumber()-arg1.getReceiver().getAddress().getHouseNumber();
//			}
//		});
//		
//		parcelsByPriceAndIndex = new PriorityQueue<Parcel>(new Comparator<Parcel>() {
//			@Override
//			public int compare(Parcel arg0, Parcel arg1) {
////				double val1 = 100*getParcelIndex(arg0) + arg0.getCurrentCost();
////				double val2 = 100*getParcelIndex(arg1) + arg1.getCurrentCost();
//				
//				return ((int)(getParcelIndex(arg0)-getParcelIndex(arg1)));
//				
//			}
//		});
//		parcelsByOrder = new ArrayList<Parcel>();
	}
	
	

	public WareHouse(int warehouseId)
	{
//		this.warehouseId = warehouseId;
//		parcels = new ArrayList<>();
		this.warehouseId = warehouseId;
		parcels = new ArrayList<>();
		this.region = this.address.city.getRegion();
        parcelsListSortedByPrice = new LinkedList<Parcel>();
        parcelsByReceiverHouseNumber = new LinkedList<Parcel>( );
		Collections.sort(parcelsByReceiverHouseNumber,new CompareByHouseNumber());
		transactionsHashMap = new HashMap<Integer, HashMap<Truck,ArrayList<Parcel>>>();
		parcelsByPriceAndIndex = new LinkedList<Parcel>( );
		Collections.sort(parcelsByPriceAndIndex,new CompareByPriceandIndex());
		parcelsByOrder = new ArrayList<Parcel>();
	}
	
	/***
	 * Remove parcel from warehouse.
	 * @param parcel
	 * @return boolean if removing parcel succeed
	 */
	public boolean removeParcel(Parcel parcel) {
		if(!parcels.contains(parcel)) {
			return false;
		}
		parcelsByOrder.remove(parcel);
		while(parcelsListSortedByPrice.contains(parcel))
			parcelsListSortedByPrice.remove(parcel);
		parcelsByPriceAndIndex.remove(parcel); //Should change .
		return parcels.remove(parcel);
	}
	
	
	
	/***
	 * given p, and destinaton warehouse,
	 * check validaty of removing the the parcel from current warehouse and add to the destination warehouse.
	 * @param p
	 * @param destinationWareHouse
	 * @param limitWeight
	 * @return
	 */
	private boolean checkValidity(Parcel p, WareHouse destinationWareHouse,double limitWeight) {
		//The parcel exist on the city of the receiver.
		if(p.getWeight()>limitWeight) {
			return false;
		}
		if(p.getReceiver().getAddress().getCity() == this.getAddress().getCity()) {
			return false;
		}
		if(p.getListOfItem().size()==0) {
			return false;
		}
		//The parcel and the warehouse on the same region, and the truck destination is to another region.
		if(this.getAddress().getCity().getRegion()==p.getReceiver().getAddress().getCity().getRegion()) 
		{
			if(destinationWareHouse.getAddress().getCity().getRegion()!=p.getReceiver().getAddress().getCity().getRegion())
			{
				return false;
			}
		}
		
		return true;
		
		
	}
	
	/***
	 * Get the next parcel according to price and index;
	 * @param destinationWareHouse
	 * @return
	 */
	public Parcel getNextParcelForTruck(WareHouse destinationWareHouse,double limitWeight) {
		
		HashSet<Parcel> tmp = new HashSet<Parcel>();
		while(parcelsByPriceAndIndex.peek()!=null)
		{
			Parcel p = parcelsByPriceAndIndex.poll();
			if(checkValidity(p, destinationWareHouse,limitWeight))
			{
				parcelsByOrder.remove(p);
				while(parcelsListSortedByPrice.contains(p))
					parcelsListSortedByPrice.remove(p);
				if(tmp.size()>0) {
					
					parcelsByPriceAndIndex.addAll(tmp);
					
				}
				return p;
			}
			else {
				tmp.add(p);
			}
		}
		parcelsByPriceAndIndex.addAll(tmp);

		return null;
		
	}
	

	/***
	 * Get the next parcel according to price and index;
	 * @param destinationWareHouse
	 * @return
	 */
	public Parcel getNextParcelForCar(double limitWeight) {
		
		//TODO
		while(parcelsByReceiverHouseNumber.peek()!=null)
		{
			if(parcelsByReceiverHouseNumber.peek().getWeight()>limitWeight)
				return null;
			Parcel p = parcelsByReceiverHouseNumber.poll();
			if(p.getReceiver().getAddress().getCity()==(this.getAddress().getCity()))
				return p;
		}
		return null;
		
	}
	
	public int getParcelIndex(Parcel parcel) {
		return parcelsByOrder.indexOf(parcel);
	}
	
	public E_Region getRegion() {
		return region;
	}



	public void setRegion(E_Region region) {
		this.region = region;
	}


	public HashMap<Integer, HashMap<Truck, ArrayList<Parcel>>> getTransactionsHashMap() {
		return transactionsHashMap;
	}



	public void setTransactionsHashMap(HashMap<Integer, HashMap<Truck, ArrayList<Parcel>>> transactionsHashMap) {
		this.transactionsHashMap = transactionsHashMap;
	}


	public void setParcelListSortecByPriceList(Parcel p)
	{
		
		while(parcelsListSortedByPrice.contains(p)) {
			parcelsListSortedByPrice.remove(p);
		}
		if(!parcelsListSortedByPrice.contains(p))
			addToParcelListSortecByPriceList(p);
		
	}
	
	public void addToParcelListSortecByPriceList(Parcel parcel)
	{
		parcelsListSortedByPrice.add(parcel);

	}
	/***
	 * Add parcel to warehouse.
	 * @param parcel
	 * @return if adding parcel succeed
	 */
	public boolean addParcel(Parcel parcel) {
		if (parcels.contains(parcel))
			return false;
		
		//In case and the parcel received on warehouse which exist on the same City as the receiver.
		if(parcel.getReceiver().getAddress().getCity()==this.getAddress().getCity()) {
			parcelsByReceiverHouseNumber.add(parcel);
		}
		
		parcelsByOrder.add(parcel);
		parcel.setCurrentIndexInWareHouse(parcelsByOrder.indexOf(parcel)+1);
		addToParcelListSortecByPriceList(parcel);
		
		
		
		
		parcelsByPriceAndIndex.add(parcel);

		return parcels.add(parcel);
	}

	public LinkedList<Parcel> getParcelsSetByPrice() {
		return parcelsListSortedByPrice;
	}






	public LinkedList<Parcel> getParcelsByReceiverHouseNumber() {
		return parcelsByReceiverHouseNumber;
	}



	public void setParcelsByReceiverHouseNumber(LinkedList<Parcel> parcelsByReceiverHouseNumber) {
		this.parcelsByReceiverHouseNumber = parcelsByReceiverHouseNumber;
	}



	public ArrayList<Parcel> getParcelsByOrder() {
		return parcelsByOrder;
	}

	public void setParcelsByOrder(ArrayList<Parcel> parcelsByOrder) {
		this.parcelsByOrder = parcelsByOrder;
	}

	public LinkedList<Parcel> getParcelsByPriceAndIndex() {
		return parcelsByPriceAndIndex;
	}

	public void setParcelsByPriceAndIndex(LinkedList<Parcel> parcelsByPriceAndIndex) {
		this.parcelsByPriceAndIndex = parcelsByPriceAndIndex;
	}



	public LinkedList<Parcel> getParcelsByReceiver() {
		return parcelsByReceiverHouseNumber;
	}



	public void setParcelsByReceiver(LinkedList<Parcel> parcelsByReceiver) {
		this.parcelsByReceiverHouseNumber = parcelsByReceiver;
	}



	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Parcel> getParcels() {
		return parcels;
	}

	public void setParcels(ArrayList<Parcel> parcels) {
		this.parcels = parcels;
	}

	/****
	 * add transaction to warehouse history.
	 * @param truck
	 * @param parcels
	 */
	public void addTransaction(Truck truck,ArrayList<Parcel> parcels) {
		HashMap<Truck,ArrayList<Parcel>> truckParcelsMap = new HashMap<>();
		truckParcelsMap.put(truck, parcels);	
		this.transactionsHashMap.put(transatctionId,truckParcelsMap);
		transatctionId++;
	}

	@Override
	public String toString() {
		return "WareHouse [warehouseId=" + warehouseId + ", address=" + address + "]";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return this.getWarehouseId() == ((WareHouse)obj).getWarehouseId();
	}

	@Override
	public int compareTo(WareHouse o) {
		if (this.equals(o))
			return 0;
		else 
			if (this.getWarehouseId()>o.getWarehouseId())
				return 1;
		return -1;
	}
	
	

}
