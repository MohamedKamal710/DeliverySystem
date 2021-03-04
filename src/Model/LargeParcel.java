package Model;


import java.io.Serializable;

import Utils.Constants;
import Utils.E_Color;

public class LargeParcel extends Parcel implements Serializable{
	
	/** Color of the sticker for customs*/
	public E_Color color;
	
	
	public LargeParcel(Receiver receiver) {
		//TODO
		super(receiver);
		setColor();
	}

	 
	 public LargeParcel(String parcelId,Receiver receiver) {
		 super(parcelId,receiver);
		 setColor();
	 }
	


	/**
	 * @author Adam
	 * remove an item from list.
	 * @param itemToRemove
	 * @return true if removing item succeed
	 */
	public boolean removeItem(Item itemToRemove) {
		if(super.removeItem(itemToRemove)) {
			setColor();
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
			setColor();
			return true;
		}
		return false;
	}
	

	public E_Color getColor() {
		return color;
	}
	
	/**
	 * set color of large parcel
	 * if total price of parcel is bigger than the tax free - will be get red sticker
	 * else, green sticker.
	 */
	public void setColor() {
		double totalPrice=0;
		for (Item item : listOfItem){
			totalPrice+=item.getPrice();
		}
		if (totalPrice<=Constants.TAX_FREE)
			this.color = E_Color.GREEN;
		else
			this.color = E_Color.RED;
	}
	
	
}
