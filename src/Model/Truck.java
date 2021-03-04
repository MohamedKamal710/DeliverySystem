package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

//ADAMs
import Utils.Constants;
import Utils.E_ModelType;
import Utils.E_TypeTruck;

public class Truck extends Vehicle implements Serializable{

	/** Type of trunk -depend on the load*/
	private E_TypeTruck typeTrunk;
	
	
	private WareHouse destinationWareHouse;
	
	
	/**
	 * Full constructor
	 * @param vin
	 * @param weight
	 * @param color
	 * @param type
	 * @param inuse
	 * @param driver
	 * @param typeTrunk
	 */
	public Truck(String vin, String color, E_ModelType type,  E_TypeTruck typeTrunk,Driver driver) {
		super(vin, color, type,driver);
		this.typeTrunk=typeTrunk;
		destinationWareHouse = null;

	}

	/**
	 * Partial constructor
	 * @param vin
	 */
	public Truck(String vin) {
		super(vin);
	}


	/**
	 * After filling the truck, we can send it to the warehouse destination.
	 * move all parcels to warehouse
	 * @param wh
	 */
	public boolean dismantleAllParcelsToDestinationWareHouse() {
		int numberOfParcelsBefore = destinationWareHouse.getParcels().size();

		int countOfParcels = 0;
				
		for(Parcel p: getParcels()) {
			p.addToLocations(destinationWareHouse);
			destinationWareHouse.addParcel(p);
			countOfParcels++;
//			p.currentWareHouse = destinationWareHouse;
		}
		if(numberOfParcelsBefore+countOfParcels != destinationWareHouse.getParcels().size()) {
			return false;
		}
		destinationWareHouse.addTransaction(this, getParcels());
		setWeight(0);
		getParcels().clear();
		
		setDestinationWareHouse(null);
		return true;
	}

	

	public E_TypeTruck getTypeTrunk() {
		return typeTrunk;
	}

	public void setTypeTrunk(E_TypeTruck typeTrunk) {
		this.typeTrunk = typeTrunk;
	}

	public WareHouse getDestinationWareHouse() {
		return destinationWareHouse;
	}

	public void setDestinationWareHouse(WareHouse destinationWareHouse) {
		this.destinationWareHouse = destinationWareHouse;
	}

	@Override
	public String toString() {
		return super.toString() + "Truck [typeTrunk=" + typeTrunk + "]";
	}


}
