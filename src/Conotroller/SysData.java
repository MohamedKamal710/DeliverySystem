package Conotroller;




import Model.*;

import Utils.Constants;
import Utils.E_Cities;
import Utils.E_Color;
import Utils.E_ModelType;
import Utils.E_ParcelType;
import Utils.E_Region;
import Utils.E_TypeTruck;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;



public class SysData {
    // -------------------------------Class Members------------------------------
	private static SysData instance;
	private ArrayList <Parcel> allParcels;
    private ArrayList <Driver> allDrivers;
    private ArrayList <WareHouse> WareHouses;
    private ArrayList <Item> allItems;
    private ArrayList <Vehicle> allVehicles;

    // -------------------------------DATA STRUCTURES---------------------------//
    HashMap<String, Parcel> allParcelsMap;
    HashMap<String,Vehicle> allVehiclesMap;
    HashMap<Integer, WareHouse> allWareHouses;
    HashMap<Long, Receiver> allReciversMap;
    HashMap<Long,Driver> allDriversMap;
    HashMap<Long,Coordinator> allCoordinators;
    HashMap<Long,Receiver>allReceivers;
    
    //--------------------------------------------------------------------------//
    // -------------------------------Constructors------------------------------
    public SysData() {
        allParcels = new ArrayList<Parcel>();
        allDrivers = new ArrayList<Driver>();
        WareHouses = new ArrayList<WareHouse>();
        if(!WareHouses.contains(Constants.BASE_WAREHOUSE))
        	WareHouses.add(Constants.BASE_WAREHOUSE);
        allItems = new ArrayList<Item>();
        allParcelsMap = new HashMap<String, Parcel>();
        allVehiclesMap = new HashMap<String, Vehicle>();
        allWareHouses = new HashMap<Integer, WareHouse>();
        if(!allWareHouses.containsValue(Constants.BASE_WAREHOUSE))
        	allWareHouses.put(1, Constants.BASE_WAREHOUSE);
        allReciversMap = new HashMap<Long, Receiver>();
        allVehicles = new ArrayList(allVehiclesMap.values());
        allDriversMap = new HashMap<Long,Driver>();
        allCoordinators=new HashMap<Long,Coordinator>();
        allReceivers = new HashMap<Long,Receiver>();
        

     }
    // -----------------------------------------Getters--------------------------------------
    public void Setter(ArrayList<Object> Darray,int valueID) {
    	
      	 
    	this.allParcels=(ArrayList<Parcel>) Darray.get(0) ;
    	this.allDrivers = (ArrayList<Driver>) Darray.get(1);
    	this.WareHouses=(ArrayList<WareHouse>) Darray.get(2);
    	this.allItems = (ArrayList<Item>) Darray.get(3);
    	this.allVehicles =(ArrayList<Vehicle>) Darray.get(4);
   
    	this.allParcelsMap = (HashMap<String, Parcel>) Darray.get(5);
    	this.allVehiclesMap = (HashMap<String, Vehicle>) Darray.get(6);
    	this.allWareHouses = (HashMap<Integer, WareHouse>) Darray.get(7);
    	this.allReciversMap = (HashMap<Long, Receiver>) Darray.get(8);
    	this.allDriversMap = (HashMap<Long, Driver>) Darray.get(9);
    	this.allCoordinators = (HashMap<Long, Coordinator>) Darray.get(10);
    	this.allReceivers = (HashMap<Long,Receiver>) Darray.get(11);
     }
    
    
	public static SysData getInstance() {
		if (instance == null) 
			instance = new SysData();
		return instance;
	}
    
    public ArrayList<Parcel> allParcels() {
        return allParcels;
    }


    public ArrayList<Driver> allDrivers() { 
    	return allDrivers; 
    }


    public ArrayList<WareHouse> WareHouses() {
        return WareHouses;
    }

    public ArrayList<Item> allItems() {
        return allItems;
    }
    public HashMap<String, Parcel> getParcelsMap(){
    	
    	return allParcelsMap ;
    }
    
    public  HashMap<String,Vehicle> getVehclesMap(){
    	 return allVehiclesMap ;
    }
    
    public  HashMap<Integer, WareHouse> getWareHouesMap(){
    	return allWareHouses ;
    }
    
    public HashMap<Long,Receiver> getReceiversMap(){
    	return allReciversMap ;
    }
    public ArrayList<Vehicle> getVehicles(){
    	return allVehicles;
    }
    public HashMap<Long,Driver> getAllDriversMap(){
    	return this.allDriversMap;
    }
    public HashMap<Long,Coordinator> getAllCoordinators(){
    	return allCoordinators;
    }
    public HashMap<Long,Receiver> getAllReceivers(){
    	return allReceivers;
    }
// -------------------------------Add && Remove Methods------------------------------
    

   
    
    /**
     * This method adds a new Car to our company IF the Car doesn't
     * already exist and the details are valid.
     * @return true if the Car was added successfully, false otherwise
     */
    public boolean addCar(String vin, String color, E_ModelType type,long driverId,boolean hybryd) {
        //TODO
    	if (vin != null && color != null && type != null ) {
        	Driver driver = null;
        	
        	/*check if car already exist*/
        	if(allVehiclesMap.containsKey(vin)) {
        		return false;
        	}
        	
//        	for(Vehicle v:allVehiclesMap.values()) {
//        		if(v.getDriver().getId()==driverId) {
//        			return false;
//        		}
//        	}

        	

//        	for(Driver d:allDrivers) {
//        		if(d.getId()==driverId) {
//        			driver = d;
//        			break;
//        		}
//        	}
//        	if(driver==null) 
//        		return false;
//        	
//        	if(!driver.getHasValidLicense()) 
//        		return false;
//        	
//	    	if (allReciversMap.values().contains(driver)) 
//	    		return false; //Driver should not be receiver.
//	    	
            Car newCar = new Car(vin,color,type,hybryd,driver);
            
            if(newCar.getVin()==null) 
            {
            	return false;
            }
            if (newCar != null) 
            {
//                driver.setDriverInUse(true);
            	allVehicles.add(newCar);
                allVehiclesMap.put(vin,newCar);
                return true;
                
                
            }

        }
        return false;
    }// ~ END OF addCar

  
    
    /**
     * This method adds a new Truck to our company IF the Truck doesn't
     * already exist and the details are valid.
     * @return true if the Truck was added successfully, false otherwise
     */
    public boolean addTruck(String vin, String color, E_ModelType type, long driverId, E_TypeTruck typeTrunk) {
    	//TODO
    	if (vin != null && color != null && type != null  && typeTrunk!=null) 
    	{
    		/*check if truck already exist*/
    		if(allVehiclesMap.containsKey(vin)) {
        		return false;
        	}
//    		for(Vehicle v:allVehiclesMap.values()) {
//        		if(v.getDriver().getId()==driverId) {
//        			return false;
//        		}
//        	}
        	
        	Driver driver = null;
//        	//Get the proposed driver.
//        	for(Driver d:allDrivers) {
//        		if(d.getId()==driverId) {
//        			driver = d;
//        			break;
//        		}
//        	}
//        	if(driver==null) 
//        		return false;//If cannot find driver.
//        	
//        	if(driver.isDriverInUse()) 
//        		return false; //if driver already in use in another vehicle.
//        	
//	    	if (allReciversMap.containsValue(driver)) 
//	    		return false; //Driver should not be receiver.
//	    	
	    	Truck newTruck = new Truck(vin, color, type, typeTrunk, driver);
	    	
	    	if(newTruck.getVin()==null) 
	    	{
            	return false;
            }
            if (newTruck != null ) 
            {
//                driver.setDriverInUse(true);
            	allVehicles.add(newTruck);
                allVehiclesMap.put(vin,newTruck);
                return true;
            }
        }
        return false;
    
    } // ~ END OF addTruck

    
    /***
     * Given receiver ID, create new parcel which related to this receiver.
     * @param id
     * @return the parcel id if adding small parcel succeed.
     */
    public String addSmallParcel(String parcelId,long id) 
    {
    	//TODO
    	Receiver currentReceiver = null;
    	
    	currentReceiver = allReciversMap.get(id);
    	if(currentReceiver == null) 
    	{
    		return null;
    	}
    	if (parcelId==null) {
    		SmallParcel sp =new SmallParcel(currentReceiver);
    		allParcels.add(sp);
    		return sp.getParcelId();
    	}
    		
    	SmallParcel sp = new SmallParcel(parcelId,currentReceiver);
    	for(Parcel temp : allParcels) {
    		if(temp.getParcelId().equals(sp.getParcelId()))
    			return null;
    	}
    	if(sp!=null) 
    	{
    		allParcelsMap.put(sp.getParcelId(), sp);
    		if(allParcels.add(sp)) 
    			
    			return sp.getParcelId();
    	}
    	return null;
    }
    
    
    
    /***
     * Given receiver ID, create new parcel which related to this receiver.
     * @param id
     * @return  parcel id  if adding small parcel succeed.
     */
    public String addLargeParcel(String parcelId,long id) 
    {
    	//TODO
    	System.out.println(id);
    	Receiver currentReceiver = null;
    	if (!allReciversMap.containsKey(id)) {
    		System.out.println("RECEIVER IS NULLs");
    		return null;
    	}
    	currentReceiver = allReciversMap.get(id);
    	if (parcelId==null) {
    		LargeParcel lp =new LargeParcel(currentReceiver);
    		allParcels.add(lp);
    		return lp.getParcelId();
    	}
    	LargeParcel lp = new LargeParcel(parcelId,currentReceiver);
    	for(Parcel temp : allParcels) {
    		if(temp.getParcelId().equals(lp.getParcelId()))
    			return null;
    	}
    	if(lp!=null) 
    	{
    		allParcelsMap.put(lp.getParcelId(), lp);
    		if(allParcels.add(lp)) 
    		{
    			return lp.getParcelId();
    		}
    	}
    	System.out.println("la 7awla wla kowa");
    	return null;
    }
    
    
    
    /**
     * Creates and adds a new Driver into the relevant data-structure
     * @return true IF this Driver was added successfully, false otherwise
     */
    public boolean addDriver(long id, String firstName, String surname, Date birthDate, Address address,
                             boolean hasValidLicense,String password)   {
    	//TODO
    	if (id > 0 && !firstName.equals("") && !surname.equals("") && birthDate != null && address != null) 
    	{
            Person newPerson = new Person(id, firstName, surname, birthDate, address);
            
            Driver newDriver = new Driver(id, firstName, surname, birthDate, address, hasValidLicense);
            for(Map.Entry<Long, Coordinator> temp : allCoordinators.entrySet())
        		if(temp.getValue().getId()==id)
        			return false;
            if (newPerson != null && !allDrivers.contains(newDriver) && !allReciversMap.containsValue(newDriver)) 
            {
            	newDriver.setPassword(password);
            	allDriversMap.put(id, newDriver);
                return allDrivers.add(newDriver);
            }
        }
        return false;
    } // ~ END OF addDriver


    
    
    /**
     * Creates and adds a new Receiver into the relevant data-structure
     * @return true IF this Receiver was added successfully, false otherwise
     */
    public boolean addReceiver(long id, String firstName, String surname, Date birthDate, Address address, String email,String password) {
    //TODO
    	if (id > 0 && !firstName.equals("") && !surname.equals("") && birthDate != null && address != null &&
           email != null) 
    	{
            Receiver newReceiver = new Receiver (id, firstName, surname, birthDate, address, email);
            	for(Map.Entry<Long, Coordinator> temp : allCoordinators.entrySet())
            		if(temp.getValue().getId()==id)
            			return false;
            if (newReceiver != null && !allReciversMap.containsValue(newReceiver) && !allDrivers.contains(newReceiver)) 
            {
            	if (newReceiver.getEmail()!=null)
            	{
            		newReceiver.setPassword(password);
            		allReceivers.put(id, newReceiver);
            		allReciversMap.put(id, newReceiver);
            		return true;
            	}
            }
        }
        return false;
    } // ~ END OF addReceiver

    /**
     * Creates and adds a new WareHouse into the relevant data-structure
     * @return true IF this WareHouse was added successfully, false otherwise
     */
    public boolean addWarehouse(int id, Address address) {
    //TODO
    	if (id > 0 && address.getCity()!=null && address.getHouseNumber()>0 && address.getStreet().length()>0&&address.getZipCode().length()>0) 
    	{
        	
            WareHouse newWareHouse = new WareHouse(id, address);
            if (newWareHouse != null && !WareHouses.contains(newWareHouse)) 
            {
            	allWareHouses.put(id, newWareHouse);
                return WareHouses.add(newWareHouse);
            }
        }
        return false;
    } // ~ END OF addWareHouse

    /**
     * Creates and adds a new Item into the relevant data-structure
     * @return true IF this Item was added successfully, false otherwise
     */
    public boolean addItem (long catalogID, String itemName,  double price, boolean hasFreeShipping,double itemWeight)  {
    //TODO
    	if (catalogID > 0 && !itemName.equals("") && price > 0 &&  itemWeight > 0) 
    	{
            Item newItem = new Item(catalogID, itemName, price, hasFreeShipping, itemWeight);
            if (newItem != null && !allItems.contains(newItem)) 
            {
                return allItems.add(newItem);
            }
        }
        return false;
    } // ~ END OF addItem




    /**
     * this method adds a parcel to a wareHouse IF the wareHouse already exist.
     * @return true if the parcel was added to the wareHouse, false otherwise.
     */
    public boolean addParcelToWarehouse (int id,String pacelId)  {
    //TODO
    	WareHouse currentWareHouse = null;
        // Check validity first
        if (id > 0 && !pacelId.equals("")&&pacelId.length()==10) 
        {

            for(WareHouse wh:WareHouses) 
            {
            	if(wh.getWarehouseId()==id) 
            	{
            		currentWareHouse = wh;
            	}
            }
            
            if(currentWareHouse==null) 
            {
            	return false;
            }
            Parcel p = getParcelById(pacelId);
            
            if(p!=null) 
            {
            	return currentWareHouse.addParcel(p);
            }
            

        }
        return false;
    }// ~ END OF addSmallParcelToWareHouse


    
    /***
     * Send car to its destination.
     * Destination extracted according to the parcels receivers.
     * @param vin
     * @return
     */
    public boolean sendCarToDestination(String vin) {
    	//TODO
    	if(vin!=null) 
    	{
    		Car car = getCarByVin(vin);
    		if(car == null)
    		{
    			return false;
    		}
    		if(car.getParcels().isEmpty())
    			return false;
    		return car.sendToDestination();
    	}
    	return false;
    	
    	
    }
    
    /***
     * Get received all item for specific receiver.
     * @param receiverId
     * @return list of items
     */
    public ArrayList<Item> getReceiverItems(Long receiverId){
    	//TODO
    	if(receiverId>0) 
    	{
    		Receiver receiver = allReciversMap.get(receiverId);
    		if (receiver!=null)
    			return receiver.getReceivedItems();
    	}
    	return null;
    	
    }
    
    /***
     * Given parcel Id, add the relevant parcel to truck which has the given vin.
     * @param parcelId
     * @param vin
     * @return
     */
    public boolean addParcelToTruck(String parcelId, String vin) {
    	//TODO
    	if(parcelId!=null && vin!=null) 
    	{
    		Truck currentTruck = getTruckByVin(vin);

    		if(currentTruck==null) return false;
    		
    		Parcel p = getParcelById(parcelId);
    		
    		
    		for(Vehicle v:allVehiclesMap.values()) {
    			if(p!=null && v.getParcels().contains(p)) {
    				return false;
    			}
    		}
    		if(p!=null) 
    		{
    			if(p.getListOfItem().isEmpty() || p.isSentToReceiver() || currentTruck.getParcels().contains(p)) 
    				return false;
				return currentTruck.addParcel(p);
    		}

    	}
    	return false;
    }
    
    
    
    /***
     * Given parcel Id, add the relevant parcel to truck which has the given vin.
     * @param parcelId
     * @param vin
     * @return
     */
    public boolean addParcelToCar(String parcelId, String vin) {
    	//TODO
    	if(parcelId!=null && vin!=null) 
    	{
    		Car currentCar = getCarByVin(vin);
    		if(currentCar==null) 
    			return false;
    		
    		Parcel p = getParcelById(parcelId);
    			
    		if(p!=null)
    		{
    			if(p.isSentToReceiver() || currentCar.getParcels().contains(p)) 
    				return false;
    			
    			//In case and the parcel's warehouse location and receiver location are not the same.
    			ArrayList<WareHouse> wrs = p.getLocations();

    			if(!p.getReceiver().getAddress().getCity().equals(wrs.get(wrs.size()-1).getAddress().getCity())) 
    			{
    				return false;
    			}
    			
				return currentCar.addParcel(p);
    		}
    		
    		
    	
    	}
    	return false;
    }
    
    
    /***
     * Given parcel id, catalog id and item name, 
     * add the relevant item to the parcel with the given parcelId.
     * @param parcelId
     * @param catalogID
     * @param itemName
     * @return
     */
    public boolean addItemToParcel(String parcelId, Long catalogID, String itemName){
    	//TODO
    	if(parcelId!=null && catalogID>0 && itemName!=null) {
    		Item currentItem = null;
    		
    		//Search for item.
    		for(Item item:allItems) 
    		{
    			if(item.getCatalogID()==catalogID)
    			{
    				if(item.getItemName().equals(itemName)) 
    				{
    					currentItem = item;
    					break;
    				}
    			}
    		}
    		if(currentItem==null) return false; // If item did not found;
    		Parcel p = getParcelById(parcelId);
    		
    		if(p!=null)
    		{
    			if(p.getListOfItem().contains(currentItem)) 
    			//Parcel already has item.
					return false;
				return p.addItem(currentItem);
    		}
    		
    	}
    	
    	return false;
    }
    //-------------------------Queries------------------------------------------------------


    /**
     * In order to analyze data about trucks, write a query 
     * To get the heaviest Truck which has the highest weight.
     * @return Truck
     */
    public Truck getTruckWithMaxWeight() {
    	//TODO 
    	Truck tMax = null;
    	double max=0;
    	double currentWeight = 0;
    	for(Vehicle v:allVehiclesMap.values())
    	{
    		if (v instanceof Truck )
    			for(Parcel p:v.getParcels()) 
    				currentWeight+= p.getWeight();
    		if(max<currentWeight) {
    			tMax = (Truck)v;
    			max= currentWeight;
    		}
    		currentWeight = 0;
    	}
    	return tMax;
    }
    
    /***
     * In order to assigned trucks to work write a query
     * That get a list of free trucks - truck that doesn't has any delivery.
     * @return list of truck or null 
     */
    public ArrayList<Truck> getFreeTrucks(){
    	//TODO
    	ArrayList<Truck> freeTrucks = new ArrayList<Truck>();
    	
    	for(Vehicle v:allVehiclesMap.values()) {	
    		if(v instanceof Truck && ((Truck)v).getParcels().isEmpty())
    			freeTrucks.add((Truck)v);
    	}
    	
    	return freeTrucks;
    }
    
    
    /***
     * Returns the receiver which received max number of items.
     * Pay attention : number of parcels doens't indicate max received items.
     * @return Receiver
     */
    public Receiver getPersonWithMaxReceivedItems()  {
    	//TODO
    	Receiver currReceiver = null;
    	int maxItems =0;
    	for(Receiver r:allReciversMap.values())
    	{
    		if(r.getReceivedItems().size() > maxItems) 
    		{
    			maxItems = r.getReceivedItems().size();
    			currReceiver = r;
    		}
    	}
    	return currReceiver;
    }
    
    /***
     * Given a vin of vehicle and warehousId
     * Send truck to wareHouse if truck exists or truck is available.
     * Truck has capacity of loaded.
     * @param vin
     * @param wareHouseId
     * @return true if succeed.
     */
    public boolean sendTruckToWareHouse(String vin){
    	//TODO
    	if(vin!=null )
    	{
    		Truck truck = (Truck)allVehiclesMap.get(vin);
    		if(truck==null) 
    		{
    			return false;
    		}
    		
    		if(truck.getParcels().isEmpty())
    			return false;
    		return truck.dismantleAllParcelsToDestinationWareHouse();
    		
    	}
    	return false;
    	
    }
    
    /***
     * given parcel id,
     * Returns the currently address of the parcel 
     * parcel can be located at WareHouse or Receiver.
     * @param parcelId
     * @return
     */
    public Address getParcelLocation(String parcelId) {
    	//TODO
    	Parcel p = allParcelsMap.get(parcelId);
    	
    	if(p!=null) {
    		if(p.isSentToReceiver()) {
				return p.getReceiver().getAddress();
			}
			else {
				return p.getLocations().get(p.getLocations().size()-1).getAddress();
			}
    		
    	}

    	return null;
    }
   
    
    
    /**
     * given a minimum barrier price
     * The system want to see the large parcel with total price higher the minPrice
     * and have Green sticker, the list must be order by the proportion of price/weight of the parcel (Max must be at the top)
     * You need to find for each parcel his proportion and than to sort the list
     * @param minPrice
     * @return ArrayList LargeParcel or Null
     */
    public ArrayList<LargeParcel> smartBuy (double minPrice){
    	//TODO
    	ArrayList<LargeParcel> listGreen = new ArrayList<>();
    	double total=0;
    	for (Parcel p : allParcels()){
    		if(p instanceof LargeParcel) {
    			for (Item it : p.getListOfItem())
    				 total +=it.getPrice();
    		    if (((LargeParcel) p).getColor().equals(E_Color.GREEN) && total>=minPrice)
    		    	 listGreen.add((LargeParcel)p);
    		}
    		total = 0;	
    	}
    	if (listGreen.isEmpty())
    		return null;
    	Collections.sort(listGreen, new PriceComparator());
    	return listGreen;
    }
    
    /**
     * return all the parcels that have been (during the road to customer) 
     * At least twice at the same warehouse And to present the number of appearance at the warehouse
     * @return HashMap Parcel
     */
	public HashMap<Parcel, Integer> TwiceAtWarehouse(){
    	//TODO
    	HashMap<Parcel, Integer> atLeasttwice=new HashMap<>();
    	for (WareHouse warehouse :allWareHouses.values()){
    		for (Parcel sp : allParcelsMap.values()){
    			for (WareHouse we : sp.getLocations()){
    				if (warehouse.equals(we)){
    					if (!atLeasttwice.containsKey(sp))
    						atLeasttwice.put(sp, 1);
    					else
    						atLeasttwice.put(sp,atLeasttwice.get(sp)+1 );
    				}
    			}
    			if(atLeasttwice.containsKey(sp) && atLeasttwice.get(sp)<2)
    				atLeasttwice.remove(sp);
    		}    		
    	}
    	if (atLeasttwice.isEmpty())
    		return null;
    	return  atLeasttwice;
    }
	
	

    /*****
     * This method add as much as possible parcels from warehouse to car 
     * (If they stand on the requirement. city of warehouse = city of receiver)
     * and then, it divide the parcels to receivers by house number.
     * @see on WareHouse Class.
     * @param wareHouseId
     * @param vin
     * @return Collection<Receiver>: the order of the receivers which received the parcels.
     */
    public Collection<Parcel> sendParcelsToReceivers(int wareHouseId, String vin){
    	//TODO
    	if (!allWareHouses.containsKey(wareHouseId) || !allVehiclesMap.containsKey(vin))
    		return null;
    	
    	
    	WareHouse wh = allWareHouses.get(wareHouseId);
    	
    	if (!(allVehiclesMap.get(vin) instanceof Car)) 
    		return null;
    	
    	Car car = (Car)allVehiclesMap.get(vin);
    	
    	car.getParcels().clear();
    	
    	while(car.getWeight() < Constants.CAR_CAPACTITY)
    	{
    		Parcel p;
    		p = wh.getNextParcelForCar((Constants.CAR_CAPACTITY-car.getWeight()));
    		if(p!=null)
    		{
    			car.addParcel(p);
    			wh.removeParcel(p);
    		}
    		else {
    			break;
    		}
    	}
    	ArrayList<Parcel> tmp = new ArrayList<Parcel>();
    	tmp.addAll(car.getParcels());
    	if(car.getDriver() != null)
    		car.getDriver().setWarehouseToLoadFrom(wh);
//    	car.sendToDestination();
    	return tmp;
    }
    
    /**
     * For analyst the system, we want to know with of the warehouses sorted by their id
     * busy warehouse is called if at least five parcels have been in this warehouse 
     * the parcel must be with red stamp or packed as a box.
     * @return collection of warehouse or null if no warehouse exist
     */
    public Collection <WareHouse> busyWarehouse (){
    	//TODO
    	TreeSet<WareHouse> returnlist = new TreeSet<>();
    	int count=0;
    	
    	for(Entry<Integer, WareHouse> entryWareHouse:allWareHouses.entrySet())
    	{
    		WareHouse w = entryWareHouse.getValue();
    		for(Entry<Integer,  HashMap<Truck, ArrayList<Parcel>>> entry1:w.getTransactionsHashMap().entrySet())
    		{
    			HashMap<Truck,ArrayList<Parcel>> truckParcel = entry1.getValue();
    			count =0;
    			for(Entry<Truck,ArrayList<Parcel>> entry:truckParcel.entrySet())
    			{
    				for(Parcel p:entry.getValue()) 
    				{
    					if (p instanceof SmallParcel){
							if (((SmallParcel) p).getParcelType()==E_ParcelType.BOX)
								count++;
						}
    					else if (p instanceof LargeParcel){
							if (((LargeParcel) p).getColor()==E_Color.GREEN)
							count++;
						}
    				}
    			}
    			if(count>=4) {
					returnlist.add(w);

				}
    		}

    	}
    	return returnlist;
    }
    
    /***
     * Get all unique category IDs of the received items.
     * @param receiverID
     * @return
     */
    public int getAllUniqCategories(Long receiverID) {
    	//TODO
    	Receiver r = allReciversMap.get(receiverID);
    	
    	TreeSet<Item> itemsPerCategory = new TreeSet<Item>(new Comparator<Item>() {
    		@Override
    		public int compare(Item o1, Item o2) {
    			if(o1.getCatalogID()==o2.getCatalogID()) {
    				return 0;
    			}
    			return 1;
    		}
    		
		});
    	
    	itemsPerCategory.addAll(r.getReceivedItems());
    	return itemsPerCategory.size();	
    }
    
    /***
     * By Given a warehouseID,
     * return all the trucks which passed from this warehouse all the history of parcels (Print also duplicates).
     * @see  WareHouse Class.
     * @param wareHouseId
     * @return allTrucks
     */
    public Collection<Truck> getAllTrucksTransactions(int wareHouseId){
    	//TODO
    	WareHouse wh = allWareHouses.get(wareHouseId);
    	HashSet<Truck> allTrucks = new HashSet<Truck>();
    	if(wh!=null) {
    		for(Entry<Integer, HashMap<Truck, ArrayList<Parcel>>> e1:wh.getTransactionsHashMap().entrySet()) 
    		{
    			HashMap<Truck,ArrayList<Parcel>> tmp =e1.getValue();
    			if(tmp==null) {
    				break;
    			}
    			for(Entry<Truck, ArrayList<Parcel>> entry:tmp.entrySet()) {
    				allTrucks.add(entry.getKey());
    			}
    		}
    	}
    	
    	return allTrucks;
    }
    
    /***
     * this method only add parcels to truck, add all the possible parcels to truck.
     * Given the truck and its destination, 
     * This method should add all the possible parcels from the source warehouse to the truck according to:
     * 1- add as much as possible.
     * 2- parcels which stands on the top of the data structure has an advantage.
     * 3- Parcels which exist on the same district as the receiver, are not allowed to be moved to another district.
     * 4- Parcels which exist on the same city as the receiver, are not allowed to be added to truck. only to car.
     * @param vin 	 		: Truck which will move the parcels.
     * @param fromWh 		: source warehouse. 
     * @param toWh   		: Destination warehouse.
     * @return collection 	: all the parcels which added to the truck.
     */
    public Collection<Parcel> addAllPossibleParcelsToTruck(String vin,int fromWh, int toWh){
    	//TODO
    	Truck t = null;
    	if(allVehiclesMap.get(vin) instanceof Truck) {
    		t = (Truck)allVehiclesMap.get(vin);
    	}
    	else {
    		return null;
    	}
    	WareHouse sourceWareHouse = allWareHouses.get(fromWh);
    	WareHouse distenationWareHouse = allWareHouses.get(toWh);
    	while(t.getWeight() < Constants.TRUCK_CAPACTITY)
    	{
    		System.out.println("TRUCK WEIGHT : " + t.getWeight());
    		Parcel p;
    		p = sourceWareHouse.getNextParcelForTruck(distenationWareHouse, (Constants.TRUCK_CAPACTITY-t.getWeight()));
    	System.out.println("THIS IS " + p);
    		if(p!=null)
    		{
    			System.out.println("3aaaaaaaaaaaaaaaaaaaaaw");
    			t.addParcel(p);
    		}
    		else {
    			break;
    		}
    	}
    	
    	t.setDestinationWareHouse(distenationWareHouse);
    	System.out.println(t.getParcels().isEmpty());
    	System.out.println(t.getParcels());
    	return t.getParcels();
    }
    
    
    /****
     * Get all the parcels from given warehouse which have a price on the given range.
     * The query must run with the lowest time Complexity 
     * @param wareHouseId
     * @param lower
     * @param higher
     * @return
     */
    public Collection<Parcel> getParcelsFromWareHouseByPriceRange(int wareHouseId,double lower,double higher){
    	WareHouse wh = allWareHouses.get(wareHouseId);
    	ArrayList<Parcel> out  =  new ArrayList<Parcel>();
    	if(wh!=null) 
    	{
    		SmallParcel from = new SmallParcel(null);
    		from.setCurrentCost(lower);
    		
    		SmallParcel to = new SmallParcel(null);
    		to.setCurrentCost(higher);
    		
    		LinkedList<Parcel> parcels = wh.getParcelsSetByPrice();
    		parcels.sort(new Comparator<Parcel>() {
    			@Override
    			public int compare(Parcel o1, Parcel o2) {
    				if(o1.getCurrentCost()>o2.getCurrentCost()) {
    					return 1;
    				}
    				return -1;
    			}
			});
    		for(Parcel p:parcels) {
    			if(p.getCurrentCost()<=higher && p.getCurrentCost()>=lower) {
    				out.add(p);
    			}
    		}
    	}
    	return out;
    }
    
    
    public Collection<Parcel> sendParcelsToReceivers2(int wareHouseId, String vin){
    	//TODO
    	if (!allWareHouses.containsKey(wareHouseId) || !allVehiclesMap.containsKey(vin))
    		return null;
    	
    	
    	WareHouse wh = allWareHouses.get(wareHouseId);
    	ArrayList<Parcel> tmp = new ArrayList<Parcel>();
    	if (!(allVehiclesMap.get(vin) instanceof Car)) 
    		return null;
    	
    	Car car = (Car)allVehiclesMap.get(vin);
    	
    	car.getParcels().clear();
    	
    	while(car.getWeight() < Constants.CAR_CAPACTITY)
    	{
    		Parcel p;
    		p = wh.getNextParcelForCar((Constants.CAR_CAPACTITY-car.getWeight()));
    		if((p!=null)&&(p.getReceiver().getAddress().getCity().equals(wh.getAddress().city)) ) {// maybe delete !!!
   System.out.println("the city of the warhouse "+wh.getAddress().city+"and the city of the receiver is : "+p.getReceiver().getAddress().getCity());
    		
    			car.addParcel(p);
    		//	wh.removeParcel(p);
    		}
    		else {
    			break;
    		}
    	}
    	
    	tmp.addAll(car.getParcels());
    	return tmp ;
    	//car.sendToDestination();
    	//return tmp;
    }
    
    /****************************** Common Methods *************************************/

    
    public Parcel getParcelById(String parcelId) {
    	for(Parcel p:allParcels) {
    		if(p.getParcelId().contentEquals(parcelId)) {
    			return p;
    		}
    	}
    	return null;
    }
    
    
    public Truck getTruckByVin(String vin) {
    	if(allVehiclesMap.get(vin) instanceof Truck) {
    		return (Truck)allVehiclesMap.get(vin);
    	}
    	return null;
    }

    public Car getCarByVin(String vin) {
    	if(allVehiclesMap.get(vin) instanceof Car) {
    		return (Car)allVehiclesMap.get(vin);
    	}
    	return null;
    }
    
    public Vehicle getVehicleByVin(String vin) {
    	for(Vehicle v:allVehiclesMap.values()) {
    		if(v.getVin().contentEquals(vin)) {
    			return v;
    		}
    	}
    	return null;
    }
    
    public WareHouse getWareHouseById(int id) {
    	for(WareHouse wh:WareHouses) {
    		if(wh.getWarehouseId()==id) {
    			return wh;
    		}
    	}
    	return null;
    }

    /**
     * 
     * @param array
     * @returns an array of the Available Trucks in the SysData .
     */
 public ArrayList<Vehicle> SetTruckArray(){
	 ArrayList<Vehicle> toReturn = new ArrayList<Vehicle>();
	 if(this.allVehiclesMap.isEmpty()) {
		 return toReturn;
	 }
	 for(Vehicle V : this.allVehiclesMap.values()) {
		 if(V instanceof Truck) {
			 if(! V.isInUse()) {
				 toReturn.add(V) ;
			 }
		 }
	 }
	 
	 return toReturn ;
 }
 
 public ArrayList<Driver> setDriverArray(){
	 ArrayList<Driver> toReturn = new ArrayList<Driver>() ;
	 if(this.allDrivers.isEmpty()) {
		 return toReturn ;
	 }
	 for(Driver d : this.allDrivers) {
		 if(d.getHasValidLicense()) {
		 if(!d.isDriverInUse()) {
			 toReturn.add(d) ;
		 }
	 }
	 }
	 return toReturn ;
 }
 
 /**
  * 
  * @param array
  * @returns an array of the Available Cars in the SysData .
  */
public ArrayList<Vehicle> SetCarArray(){
	 ArrayList<Vehicle> toReturn = new ArrayList<Vehicle>();
	 if(this.allVehiclesMap.isEmpty()) {
		 return toReturn;
	 }
	 for(Vehicle V : this.allVehiclesMap.values()) {
		 if(V instanceof Car) {
			 if(! V.isInUse()) {
				 toReturn.add(V) ;
			 }
		 }
	 }
	 
	 return toReturn ;
}
 
public boolean doesEmailExist(String email) {
	for(Map.Entry<Long, Receiver> temp : SysData.getInstance().getReceiversMap().entrySet()) {
		if(temp.getValue().getEmail().equals(email))
			return false;
	}
	return true;
}
}
