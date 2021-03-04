package Model;
import java.io.Serializable;
import java.util.ArrayList;

import Utils.Constants;
import Utils.E_ModelType;

public class Vehicle  implements Serializable {
	
	
	/** Key: Vehicle identification number  */
	private String vin;
	
	/** Weight of the loaded vehicle*/
	private double weight; 
	
	/** Vehicle color */
	private String color;
	
	/** Vehicle model */
	private E_ModelType type;
	
	/** Verify if vehicle can be used*/
	private boolean inUse;
	
	/**each vehicle register for one driver-*/
	private Driver driver;
	
	/**list of small parcel to deliver to client*/
	private ArrayList <Parcel> parcels;


	/**
	 * Full constructor
	 * @param vin
	 * @param color
	 * @param type
	 */
	public Vehicle(String vin, String color, E_ModelType type,Driver driver) {
		setVin(vin);
		setDriver(driver);
		this.color = color;
		this.type = type;
		this.inUse=false;
		this.weight = 0;
		parcels= new ArrayList<>();
	}

	

	/**
	 * Add driver to car
	 * @return true if the driver was added successfully, false otherwise
	 */
	public boolean addDriver(Driver driver) {

		if(isInUse())
			return false;
		if(this.getDriver()!=null){
			return false;
		}
		this.setDriver(driver);
		return true;

	}
	
	/**
	 * Partial constructor
	 * @param vin Vehicle identification number
	 */
	public Vehicle(String vin) {
		setVin(vin);
	}
	
	/**
	 * Set VIN
	 * VIN has a fixed number of characters
	 * There is a list of illegal characters that cannot be in a VIN 
	 * @param vin The VIN to set
	 */
	protected void setVin(String vin) {
		//TODO
		char ch;
		char[] charArray = vin.toCharArray();/*Converting String to “Character” array*/
		if (vin.length()!=Constants.NUM_OF_VIN_CHARACTERS)/*the lenght of the VIN MUST be a 17 characters only!!*/
			{this.vin=null;
			return;
			}
		for (int i=0; i<Constants.FORBIDDEN_VIN_CHARACTERS.length;i++)
		{
			ch=Constants.FORBIDDEN_VIN_CHARACTERS[i];
			for (int j=0;j<Constants.NUM_OF_VIN_CHARACTERS;j++)/*check each char from the string if it contain one of the frobbiden letters*/
				if (ch==charArray[j])
				{
					this.vin=null;
					return;
				}
		}
		this.vin=vin;	
	}

	
	/**
	 * Remove parcel from truck.
	 * @param parcel
	 * @return
	 */
	public boolean removeParcelFromTruck(Parcel parcel) {
		if(!getParcels().contains(parcel)) {
			return false;
		}
		
		setWeight(getWeight() - parcel.getWeight());
		ArrayList<Parcel> temp = getParcels();
		temp.remove(parcel);
		setParcels(temp);
		parcel.getLocations().get(parcel.getLocations().size()-1).addParcel(parcel);
		
		return true;
		
	}
	
	public boolean sendParcelToDestination( Parcel parcel) {
	    
		parcel.setSentToReceiver(true);
		this.setWeight(this.getWeight()-parcel.getWeight());
		return this.getParcels().remove(parcel) ;
//return(this.getParcels().isEmpty());
	}

	/***
	 * Add parcel to vehicle
	 * @param parcel
	 * @return
	 */
	public boolean addParcel(Parcel parcel) {
		double CAPACITY = 0;
		if(this instanceof Car) {
			CAPACITY = Constants.CAR_CAPACTITY;
		}
		else if(this instanceof Truck) {
			CAPACITY = Constants.TRUCK_CAPACTITY;
		}
		
		

		//		if(isInUse())
//			return false;

		if((getWeight() + parcel.getWeight()) <= CAPACITY) {
			setWeight(getWeight() + parcel.getWeight());
			ArrayList<Parcel> temp = getParcels();
			temp.add(parcel);
			setParcels(temp);
			
			parcel.getLocations().get(parcel.getLocations().size()-1).removeParcel(parcel);
			return true;
		}
		return false;
	}
	
	/*
	 * Add parcel to vehicle
	 * @param parcel
	 * @return
	 */
	public boolean addParcel2(Parcel parcel) {
		double CAPACITY = 0;
		if(this instanceof Car) {
			CAPACITY = Constants.CAR_CAPACTITY;
		}
		else if(this instanceof Truck) {
			CAPACITY = Constants.TRUCK_CAPACTITY;
		}
		
		if(isInUse())
			return false;

		if((getWeight() + parcel.getWeight()) <= CAPACITY) {
			setWeight(getWeight() + parcel.getWeight());
			ArrayList<Parcel> temp = getParcels();
			temp.add(parcel);
			setParcels(temp);
			
			//parcel.getLocations().get(parcel.getLocations().size()-1).removeParcel(parcel);
			return true;
		}
		return false;
	}
	
	// -------------------------------Getters And Setters------------------------------
	
	
	public String getColor() {
		return color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public E_ModelType getType() {
		return type;
	}

	public void setType(E_ModelType type) {
		this.type = type;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public Driver getDriver() {
		return driver;
	}

	
	public boolean setDriver(Driver driver) {
		if(isInUse())
			return false;
		this.driver = driver;
		return true;
	}

	public String getVin() {
		return vin;
	}
	
	public void releaseVehicle() {
		this.inUse = false;
	}
	
	public void useVehcile() {
		this.inUse = true;
	}


	public ArrayList<Parcel> getParcels() {
		return parcels;
	}

	public void setParcels(ArrayList<Parcel> parcels) {
		this.parcels = parcels;
	}

	@Override
	public String toString() {
		return "Vehicle [vin=" + vin + ", weight=" + weight + ", color=" + color + ", type=" + type + ", inUse=" + inUse
				+ ", driver=" + driver + ", parcels=" + parcels +  "]";
	}


	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		
		if(obj instanceof Car) {
			Car c = (Car) obj;
			return this.getVin().equals(c.getVin());

		}
		else {
			Truck v = (Truck) obj;
			return this.getVin().equals(v.getVin());

		}
		
	}
	
	

}
