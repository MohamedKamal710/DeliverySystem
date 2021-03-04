package SerializationDemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Conotroller.SysData;
import Model.Address;
import Model.Coordinator;
import Model.Driver;
import Model.Item;
import Model.Parcel;
import Model.Receiver;
import Model.SmallParcel;
import Model.Vehicle;
import Model.WareHouse;
import Utils.Constants;
public class Delivery implements Serializable {

//	static Delivery obj = new Delivery();
	private ArrayList<Object> SaveData = new ArrayList<>();
	private int value;
	public Delivery() {
	SaveData.clear();
    SaveData.add(SysData.getInstance().allParcels()) ;
	SaveData.add(SysData.getInstance().allDrivers()) ;
    SaveData.add(SysData.getInstance().WareHouses()) ;
    SaveData.add(SysData.getInstance().allItems()) ;
    SaveData.add(SysData.getInstance().getVehicles());
    
    SaveData.add(SysData.getInstance().getParcelsMap()) ;
   SaveData.add(SysData.getInstance().getVehclesMap()) ;
   SaveData.add(SysData.getInstance().getWareHouesMap()) ;
   SaveData.add(SysData.getInstance().getReceiversMap()) ;
   SaveData.add(SysData.getInstance().getAllDriversMap()) ;
   SaveData.add(SysData.getInstance().getAllCoordinators()) ;
   SaveData.add(SysData.getInstance().getAllReceivers());
	}

	public ArrayList<Object> getSaveData() {
		return SaveData;
	}

	public int getValue() {
		return value;
	}
	
//	public static Delivery getInstance() {
//		return obj;
//	}
}

//	
//
//
//
//	public static Delivery getInstance() 
//	   { 
//	       if (instance== null) {
//	    	   
//	    	   System.out.println("Helllllllllllllllo  creating new Deilivery - cuhs its NNNNNNNNNuuuuuuullllllllllllll ");
//	    	   instance = new Delivery(); 
//	    	   
//	       }
//	 
//	       return instance; 
//	   } 
//
//
//  private Delivery() { 
//    	
//    	// mabe Remove !! 
//    	SaveData = new ArrayList<>();
//
//    }
//}
    
	
