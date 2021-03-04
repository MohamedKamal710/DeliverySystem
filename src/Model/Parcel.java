package Model;
import java.io.Serializable;
import java.util.ArrayList;

import Conotroller.SysData;
import Utils.Constants;





public class Parcel implements Serializable 
{
	
	/**Key: Parcel ID, increase automatic*/
	public static int ID = 1;
	
	/** Tracking parcel location, keep all warehouse on the road.*/
	protected ArrayList<WareHouse> locations;
	
	
	/** List of item in the package */
	protected ArrayList<Item> listOfItem;
	 
	 
	 /**The weight of the of the package*/
	 private double weight;
	 

	 /**Is parcel reach to receiver*/
	 private boolean sentToReceiver;
	 
	 /**Detail about the client ordered the parcel*/
	 private Receiver receiver;

	 /**Item ID, should generated automaticlly**/
	 private String parcelId;
	 
    private double currentCost = 0;

    private int currentIndexInWareHouse = 0; //added in order to track over the index in output.

     WareHouse currentWareHouse;
    
	 public double getCurrentCost() {
			return currentCost;
		}

		public void setCurrentCost(double currentCost) {
			this.currentCost = currentCost;
		}


	/***
	 * On HW2, will send ID to constructor.
	 * @param parcelId
	 * @param receiver
	 * @param item
	 */
	public Parcel(String parcelId,Receiver receiver)
	{
		 this.receiver=receiver;
		 this.weight=0;
		 this.parcelId = parcelId;
		 this.sentToReceiver = false;
		 locations = new ArrayList<WareHouse>();
		 locations.add(SysData.getInstance().WareHouses().get(0));
		 SysData.getInstance().WareHouses().get(0).addParcel(this);
		 currentWareHouse = SysData.getInstance().WareHouses().get(0);
		 listOfItem = new ArrayList<Item>();
	 }
	
	

	 
	 public Parcel(Receiver receiver) {
		 this.receiver=receiver;
		 this.weight=0;
		 this.parcelId = getNextId();
		 this.sentToReceiver = false;
		 listOfItem = new ArrayList<Item>();
//		 Constants.BASE_WAREHOUSE.addParcel(this);
		 SysData.getInstance().WareHouses().get(0).addParcel(this);
		 locations = new ArrayList<WareHouse>();
		 locations.add(SysData.getInstance().WareHouses().get(0));
		 currentWareHouse = SysData.getInstance().WareHouses().get(0);
	 }
	 

	/**
	 * Add new location for the parcel
	 * @param wh
	 * @return
	 */
	public boolean addToLocations(WareHouse wh) {
		currentWareHouse = wh;
		return this.locations.add(wh);
		
	}

	/***
	 * Not used in HW2
	 * @return
	 */
	public String getNextId() {

		String id = ID+"";
		String updatedID="";
		for(int i=0;i<(10-id.length());i++) 
		{
			updatedID = updatedID+"0";
		}
		ID++;
		return updatedID+id;
	}
	
	
	/**
	 * in case and the parcel sent to receiver (By car),
	 * Update the list of items which the receiver received.
	 * 
	 * */
	public void setSentToReceiver(boolean sentToRec) {
		if(sentToRec) {
			getReceiver().updateReceivedItems(this);
			this.sentToReceiver = sentToRec;
			currentWareHouse= null;
		}
	}
	
	/**
	 * @author Adam
	 * remove an item from list.
	 * @param itemToRemove
	 * @return true if removing item succeed
	 */
	public boolean removeItem(Item itemToRemove) {
		if(!listOfItem.contains(itemToRemove))
			return false;
		boolean itemRemoved = listOfItem.remove(itemToRemove);
		setWeight(getWeight() - itemToRemove.getItemWeight());
		currentCost-= itemToRemove.getPrice();
		currentWareHouse.setParcelListSortecByPriceList(this);

		return itemRemoved;
		
	}
	/**
	 * Insert new item to the list for the package
	 * @param newItem
	 * @return
	 */
	public boolean addItem (Item newItem){
		if(listOfItem.contains(newItem))
			return false;
		if(!CheckWeight(getWeight()+newItem.getItemWeight())) {
			return false;
		}
		
		currentCost+= newItem.getPrice();
//		currentWareHouse.setParcelListSortecByPriceList(this);

		return listOfItem.add(newItem);
	}

	public ArrayList<WareHouse> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<WareHouse> locations) {
		this.locations = locations;
	}

	public ArrayList<Item> getListOfItem() {
		return listOfItem;
	}

	public void setListOfItem(ArrayList<Item> listOfItem) {
		this.listOfItem = listOfItem;
	}

	public boolean isSentToReceiver() {
		return sentToReceiver;
	}



	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}


	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	public int getCurrentIndexInWareHouse() {
		return currentIndexInWareHouse;
	}

	public void setCurrentIndexInWareHouse(int currentIndexInWareHouse) {
		this.currentIndexInWareHouse = currentIndexInWareHouse;
	}

	public double getWeight() {
		return weight;
	}
	
	public String getParcelId() {
		return this.parcelId;
	}
	

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Parcel [parcelId="+parcelId+", locations=" + locations + ", listOfItem=" + listOfItem + ", weight=" + weight
				+ ", sentToReceiver=" + sentToReceiver + ", receiver=" + receiver
				+ "]";
	}

	
	
	/**
	 * Check valid of weight
	 * @param weight
	 * @return
	 */
	public boolean CheckWeight(double weight) {
		if(this instanceof SmallParcel)
		{
			if (weight<=Constants.SMALL_PARCEL_MAX_WEIGHT)
			{
				setWeight(weight);
				return true;
			}
		}
		else if(this instanceof LargeParcel) {
			if (weight<=Constants.LARGE_PARCEL_MAX_WEIGHT)
			{
				setWeight(weight);
				return true;
			}
		}
		return false;
		
	}

	
	
	@Override
	public boolean equals(Object obj)
	{
		if(null == obj) {
			return false;
		}
		if(obj instanceof SmallParcel) {
			return this.getParcelId().equals(((SmallParcel)obj).getParcelId());
		}
		else {

			return this.getParcelId().equals(((LargeParcel)obj).getParcelId());
		}
	}

	
	
	
}
