package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



public class Driver extends Person implements Serializable{
	
	/**
	 * Is the driver has valid license
	 */	
	private HashMap<WareHouse,Integer> warehousesToGo;
	private boolean hasValidLicense;
	private boolean driverInUse;
	private ArrayList<WareHouse> from;
	private WareHouse warehouseToLoadFrom;
    public Driver(long id, String firstName, String surname, Date birthDate, Address address,
    		boolean hasValidLicense) 
    {
		super(id, firstName, surname, birthDate, address);
		this.hasValidLicense = hasValidLicense;
		this.driverInUse = false;
		warehousesToGo = new HashMap<WareHouse,Integer>();
		this.from = new ArrayList<WareHouse>();
	}
    
	public Driver(long id) {
		super(id);
	
	}


	/********************** Getters/Setters of class*****************************/
	public HashMap<WareHouse,Integer> getWareHousesToGo(){
		return warehousesToGo;
	}
	public boolean getHasValidLicense() {
		return hasValidLicense;
	}

	public void setDriverInUse(boolean isDriverInUse) {
		this.driverInUse = isDriverInUse;
	}
	
	public boolean isDriverInUse() {
		return this.driverInUse;
	}
	public void setHasValidLicense(boolean hasValidLicense) {
		this.hasValidLicense = hasValidLicense;
	}

	public HashMap<WareHouse, Integer> getWarehousesToGo() {
		return warehousesToGo;
	}

	public void setWarehousesToGo(HashMap<WareHouse, Integer> warehousesToGo) {
		this.warehousesToGo = warehousesToGo;
	}

	public ArrayList<WareHouse> getFrom() {
		return from;
	}

	public void setFrom(ArrayList<WareHouse> from) {
		this.from = from;
	}
	
	
	public WareHouse getWarehouseToLoadFrom() {
		return warehouseToLoadFrom;
	}

	public void setWarehouseToLoadFrom(WareHouse warehouseToLoadFrom) {
		this.warehouseToLoadFrom = warehouseToLoadFrom;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
}
