package Model;


import java.io.Serializable;
import java.util.ArrayList;

import Utils.Constants;
import Utils.E_ParcelType;

public class SmallParcel extends Parcel implements Serializable{
	
	/** Small parcel type */
	private E_ParcelType parcelType;
	
	
	
	
	public SmallParcel (Receiver receiver){
		//TODO
		super(receiver);
		setParcelType();
	}

	 
	public SmallParcel(String parcelId,Receiver receiver) {
		 super(parcelId,receiver);
		 setParcelType();
	 }
	
	 public SmallParcel(Receiver receiver, Item item) {
		 super(receiver);
		 addItem(item);
	 }
	 
	 
	 
		/**
		 * @author Adam
		 * remove an item from list.
		 * @param itemToRemove
		 * @return true if removing item succeed
		 */
		public boolean removeItem(Item itemToRemove) {
			if(super.removeItem(itemToRemove)) {
				setParcelType();
				return true;
			}
			return false;
			
			
		}
	/**
	 * Insert new item to the list for the package
	 * @param newItem
	 * @return
	 */
	public boolean addItem (Item newItem){
		if(super.addItem(newItem)) {
			setParcelType();
			return true;
		}
		return false;
	}
	

	
	
	public E_ParcelType getParcelType() {
		return parcelType;
	}
	
	/**
	 * Decide small parcel type by the weight
	 * if total weight is less than 1.5kg - items will send by envelope 
	 * else will send by box
	 */
	public void setParcelType() {

		if (getWeight()<Constants.SMALL_LIMMITE_DOWN)
			this.parcelType=E_ParcelType.ENVELOPE;
		else 
			this.parcelType=E_ParcelType.BOX;
	}
	
	
	
}
