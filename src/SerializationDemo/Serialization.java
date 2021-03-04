package SerializationDemo;

import Model.Item;
import Model.Parcel;
import Model.WareHouse;

import java.io.*;
import java.util.*;

public class Serialization implements Serializable {

		String filename1 = "transaction.ser";
		String filename2 = "parcel.ser";
		Integer transaction = null;
		Integer parcel = null;
		
	public void serialize(String fileName){
		Delivery lists = new Delivery();
		
		try {
			
			 FileOutputStream file1 = new FileOutputStream(filename1); 
             ObjectOutputStream out1 = new ObjectOutputStream(file1); 
             
             FileOutputStream file2 = new FileOutputStream(filename2); 
             ObjectOutputStream out2 = new ObjectOutputStream(file2); 
             
             out1.writeObject(WareHouse.transatctionId);
             
             out2.writeObject(Parcel.ID);
             
             out1.close(); 
             file1.close(); 
               
             out2.close();
             file2.close();
             
			
		}
		catch(NotSerializableException ex) 
        { 
          System.out.println("Problem");
          ex.printStackTrace();
        } 
          
        catch(IOException ex) 
        { 
           ex.getMessage();
        } 

		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
			
			out.writeObject(lists);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("the file u are looking for does Not Exist ! - Sooooorry ");
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Object> deserialize(String fileName) {
		
		ArrayList<Object> it = new ArrayList<>() ;
		Delivery dIt;
		   
	        
	        
	        try {
	        	FileInputStream file1 = new FileInputStream(filename1); 
		        ObjectInputStream in1 = new ObjectInputStream(file1); 
		        
		        FileInputStream file2 = new FileInputStream(filename2); 
		        ObjectInputStream in2 = new ObjectInputStream(file2); 
		        
		        parcel = (Integer)in2.readObject();
		        transaction = (Integer)in1.readObject();
		        
		        in1.close();
		        file1.close();
		        
		        in2.close();
		        file2.close();
	        }
	        catch(IOException e) {
	        	
	        	System.out.println("not Able to Deserialize the file"+fileName);
				System.out.println(e.getMessage());
	        	
	        }
	        catch(ClassNotFoundException ex) 
		    { 
		        System.out.println("ClassNotFoundException is caught"); 
		    } 
	        
	        System.out.println(parcel);
	        System.out.println(transaction);
	        
	        
	        if(parcel != null) {
	        	
	        	Parcel.ID = parcel;
	        }
	        if(transaction != null) {
	        	
	        	WareHouse.transatctionId = transaction;
	        }
	        System.out.println(WareHouse.transatctionId);
	        System.out.println(Parcel.ID);
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName)) ){
			dIt = (Delivery)in.readObject();
//			it =(ArrayList<Object>)in.readObject() ;
			it = dIt.getSaveData();		
			System.out.println("*"+it);
		}catch (IOException | ClassNotFoundException ex) {
			System.out.println("not Able to Deserialize the file"+fileName);
			System.out.println(ex.getMessage());
			
		}
		
		
		return it ;
	}
	
	 
     public Serialization() {
    	 super();
    	 
     }
     
     
     
}