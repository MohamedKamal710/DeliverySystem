package Model;
import java.io.Serializable;
import java.util.ArrayList;
//ADAM
import Utils.Constants;
import Utils.E_ModelType;

public class Car extends Vehicle implements Serializable{
	

	
	private boolean hybrid;
	/**
	 * Full constructor
	 * @param vin
	 * @param weight
	 * @param color
	 * @param type
	 * @param inuse
	 * @param driver
	 */
	public Car(String vin, String color, E_ModelType type,boolean hybrid,Driver driver) {
		super(vin, color, type,driver);
		this.hybrid=hybrid;
	}

	/**
	 * partial constructor
	 * @param vin
	 */
	public Car (String vin){
		super(vin);
	}
	
	
	/**
	 * Deliver all parcel of the client 
	 * and remove them from Car.
	 * @param client
	 * @return true if process of sending car to destination succeed
	 */
	public boolean sendToDestination() {
	    
		
		for(Parcel p :getParcels()) {
				p.setSentToReceiver(true);
		}
		this.getParcels().clear();
		
		this.setWeight(0);
		
		return(this.getParcels().isEmpty());
	}

	
	
	public boolean isHybrid() {
		return hybrid;
	}

	public void setHybrid(boolean hybrid) {
		this.hybrid = hybrid;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
