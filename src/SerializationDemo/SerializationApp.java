package SerializationDemo;

import java.io.File;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Conotroller.SysData;
import Model.Driver;
import Model.Item;
import Model.Vehicle;
import Model.WareHouse;
import gui.login;

public class SerializationApp implements Serializable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Object> List = new ArrayList<Object>();
		Delivery D = new Delivery();
//		Delivery D1 = Delivery.getInstance() ; 
		 ArrayList<Object> list1 = D.getSaveData() ;
//		 ArrayList<WareHouse> list2 = D1.getWareHouses();
//		System.out.println("the size of array Items : "+D1.getAllItems().size());
//		System.out.println("the size of array WareHouse : "+D1.getWareHouses().size());
//     	System.out.println("the size of array drivese :  : "+D1.getAllDrivers());
//		System.out.println(" just created the delivery Instance ! ");
//		listt.addAll((Collection<?>) D1.getAllItems()) ;
//		listt.addAll( D1.getWareHouses());
//		System.out.println("the lllllist is : "+list1);
		Serialization demo = new Serialization() ;
        File f = new File("src/SerializationDemo/Del2.ser"); 
        if(f.exists()) {
        	if(f != null)
        	try {
        	 List = new ArrayList<>(demo.deserialize("src/SerializationDemo/Del2.ser"));
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	if(List!=null || !List.isEmpty())
        		SysData.getInstance().Setter(List,D.getValue());
        	login mainframe = new login();
    		mainframe.getJframe().setVisible(true);
        }
        else {
        	login mainframe = new login();
    		mainframe.getJframe().setVisible(true);
        }
        	
//			try {
//			demo.serialize(list1, "src/SerializationDemo/Del2.ser");
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//		
//			e.printStackTrace();
//			return ;
//		}
//			
//	//	demo.serialize(list2, "src/SerializationDemo/Del2.ser");
//		System.out.println(" Serialization Done -I think! ");
//		
//		
//		
//		
//		
//		
//		
//		ArrayList<Object> List = new ArrayList<>(demo.deserialize("src/SerializationDemo/Del2.ser"));
//		System.out.println("is the deserialization successfull  ?");

 
	}

	
	
}