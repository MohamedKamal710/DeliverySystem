//package Conotroller;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Scanner;
//import java.util.Stack;
//import java.util.TimeZone;
//
//import Model.Address;
//import Model.Item;
//import Model.LargeParcel;
//import Model.Parcel;
//import Model.*;
//import Model.WareHouse;
//import Utils.E_Cities;
//import Utils.E_ModelType;
//import Utils.E_Region;
//import Utils.E_TypeTruck;
//import Utils.MyFileLogWriter;
//
///**
// * This MainClass object - represents the program runner
// * 
// * @author Java Course Team 2019 - Ron Ellenbogen
// * @author University Of Haifa - Israel
// */
//public class MainClass {
//
//	/**
//	 * The command read from the input file
//	 */
//	private static String command;
//
//	/**
//	 * To check if the command updated the objects
//	 */
//	private static boolean isUpdated;
//
//	/**
//	 * The date format
//	 */
//	private static DateFormat df;
//
//	/**
//	 * The date & time format
//	 */
//	private static DateFormat dft;
//
//	/**
//	 * The main object for the program
//	 */
//	private static SysData sysData;
//
//	/**
//	 * Scanner for input
//	 */
//	private static Scanner input;
//
//	/**
//	 * The main method of this system, gets input from input.txt and Writes
//	 * output to output.txt file
//	 * 
//	 * @param args
//	 * @throws IOException
//	 * @throws ParseException
//	 * @throws ClassNotFoundException
//	 */
//	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
//		// Create Scanner for the text file named "input.txt"
//		input = new Scanner(new File("input.txt"));
//		// Define Date format
//		df = new SimpleDateFormat("dd/MM/yyyy");
//		df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
//		// Define Date & Time format
//		dft = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
//		dft.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
//		// Writer buffer creation used after update
//		MyFileLogWriter.initializeMyFileWriter();
//		// if the file has next - not empty
//		if (input.hasNext()) {
//			sysData = SysData.getInstance();
//		}
//		/*
//		 * read the commands. loop while input file [input.hasnext()] and the
//		 * SysData is not null
//		 */
//		while (input.hasNext() && sysData != null) {
//			/*
//			 * read the command, must be first at line because command value
//			 * determine which method will be activated in SysData object.
//			 */
//			command = input.next();
//			isUpdated = false;
//			// ================ Building Command ================
//			if (command.equals("addItem")) {
//				// create and add new item to javaDelivery
//				long id = Long.parseLong(input.next());
//				String name = input.next();
//				double price = Double.parseDouble(input.next());
//				boolean freeShipping = Boolean.valueOf(input.next());
//				double weight = Double.parseDouble(input.next());
//				isUpdated = sysData.addItem(id, name, price, freeShipping, weight);
//				if (isUpdated) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added Item: " + id + " | " + name);
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new Item");
//				}
//			}
//			// ================ Building Command ================
//			else if (command.equals("addItemToParcel")) {
//				 String parcelId = input.next();
//				 Long categoryId = Long.parseLong(input.next());
//				 String itemName = input.next();
//				 
//				 isUpdated = sysData.addItemToParcel(parcelId, categoryId, itemName);
//				 if(isUpdated) {
//						MyFileLogWriter.writeToFileInSeparateLine("Successfully added Item: " + categoryId + " | " + itemName+" to parcel: "+parcelId);
//
//				 }
//				 else {
//					 MyFileLogWriter.writeToFileInSeparateLine("Failed to add item to parcel");
//				 }
//			}
//			// ================ Building Command ================
//			else if (command.equals("addSmallParcel")) {
//				// create and add new small parcel to javaDelivery
//				String plId = input.next();
//				long receiver = Long.parseLong(input.next());
//
//				String parcelId = sysData.addSmallParcel(plId,receiver);
//				
//
//				if (parcelId!=null) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added Small Parcel, Receiver ID:"+receiver+", generated id to parcel:"+parcelId  );
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new Small Parcel");
//				}
//			}
//			
//			// ================ Building Command ================
//			else if (command.equals("addLargeParcel")) {
//				// create and add new large parcel to javaDelivery
//				String plId = input.next();
//				long receiver = Long.parseLong(input.next());
//
//				String parcelId = sysData.addLargeParcel(plId,receiver);
//				
//
//				if (parcelId!=null) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added large Parcel, Receiver ID:"+receiver+", generated id to parcel:"+parcelId  );
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new Large Parcel");
//				}
//			}
//			
//			// ================ Building Command ================
//			else if (command.equals("addDriver")) {
//				// create and add new driver to javaDelivery
//				long id = Long.parseLong(input.next());
//				String firstname = input.next();
//				String surName = input.next();
//				Date birthDate = df.parse(input.next());
//				E_Cities city = E_Cities.valueOf(input.next());
//				String street = input.next();
//				int housNumber = Integer.parseInt(input.next());
//				String zipCode = input.next();
//				Address address = new Address(city, street, housNumber, zipCode);
//				boolean hasValidLicence=  Boolean.valueOf(input.next());
//				
//				isUpdated = sysData.addDriver(id, firstname, surName, birthDate, address,hasValidLicence);
//				if (isUpdated) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added new Driver: " + id + " | " + firstname + " | " + surName);
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new Driver");
//				}
//			}
//			
//			// ================ Building Command ================
//			else if (command.equals("addReceiver")) {
//				// create and add new Receiver to JavaDelivery
//				long id = Long.parseLong(input.next());
//				String firstName = input.next();
//				String lastName = input.next();
//				Date birthDate = df.parse(input.next());
//				E_Cities city = E_Cities.valueOf(input.next());
//				String street = input.next();
//				int housNumber = Integer.parseInt(input.next());
//				String zipCode = input.next();
//				Address address = new Address(city, street, housNumber, zipCode);
//				String email=  input.next();
//				
//				isUpdated = sysData.addReceiver(id, firstName, lastName, birthDate, address,email);
//				if (isUpdated) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added Receiver: " + id + " | " + firstName + " " + lastName);
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new Receiver");
//				}
//			}
//
//			// ================ Building Command ================
//			else if (command.equals("addCar")) {
//				// create and add new car to JavaDelivery
//				String vin = input.next();
//				String color = input.next();
//				E_ModelType type= E_ModelType.valueOf(input.next());
//				long driverId = Long.parseLong(input.next());
//				boolean hybird = Boolean.valueOf(input.next());
//
//				isUpdated = sysData.addCar(vin,color,type,driverId,hybird);
//				if (isUpdated) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added Car: " + vin);
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new Car");
//				}
//			}
//
//			// ================ Building Command ================
//			else if (command.equals("addTruck")) {
//				// create and add new truck to JavaDelivery
//				String vin = input.next();
//				String color = input.next();
//				E_ModelType type= E_ModelType.valueOf(input.next());
//				long driverId = Long.parseLong(input.next());
//				E_TypeTruck typeTuck= E_TypeTruck.valueOf(input.next());
//
//				isUpdated = sysData.addTruck(vin, color, type, driverId, typeTuck);
//				if (isUpdated) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added truck: " + vin);
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new truck");
//				}
//			}
//
//			// ================ Building Command ================
//			else if (command.equals("addWarehouse")) {
//				// create and add new warehouse to JavaDelivery
//				int warehouseId = Integer.parseInt(input.next());
//				E_Cities city = E_Cities.valueOf(input.next());
//				String street = input.next();
//				int housNumber = Integer.parseInt(input.next());
//				String zipCode = input.next();
//				Address address = new Address(city, street, housNumber, zipCode);
//
//				isUpdated = sysData.addWarehouse(warehouseId, address);
//				if (isUpdated) { // if adding successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added warehouse: " + warehouseId);
//				} else {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to add new warehouse");
//				}
//			}
//			else if(command.equals("addAllPossibleParcelsToTruck")) {
//				String vin = input.next();
//				int fromId = Integer.parseInt(input.next());
//				int toId = Integer.parseInt(input.next());
//
//				Collection<Parcel> out= sysData.addAllPossibleParcelsToTruck(vin, fromId, toId);
//				
//				if(out==null || out.size()==0) {
//					MyFileLogWriter.writeToFileInSeparateLine("TRUCK-"+vin+"- is empty. no parcels added to the truck");
//				}
//				else {
//					MyFileLogWriter.writeToFileInSeparateLine("TRUCK-"+vin+"-CURRENT PARCELS:");
//					for(Parcel p:out)
//					{
//						MyFileLogWriter.writeToFileInSeparateLine(p.getParcelId()+" | index is:"+ p.getCurrentIndexInWareHouse()+" | Receiver Address:"+p.getReceiver().getAddress().getCity());
//					}
//				}
//				
//				MyFileLogWriter.writeToFileInSeparateLine("\n");
//			}
//			
//			// ================ Building Command ================
//			
//
//			// ================ Building Command ================
//			else if (command.equals("addParcelToTruck")) {
//				// Connect between an existing large parcel to warehouse
//				
//				String parcelId = input.next();
//				String vin = input.next();
//				
//				isUpdated = sysData.addParcelToTruck(parcelId, vin);
//				if (isUpdated) { // if added successfully, then true returned,
//					// the following message is written to the output file
//					MyFileLogWriter.writeToFileInSeparateLine("Successfully added parcel: " + parcelId + " to truck " + vin);
//					} else {
//						MyFileLogWriter.writeToFileInSeparateLine("Failed to add parcel "+parcelId+" to truck");
//					}
//				}
//			// ================ Building Command ================
//			else if (command.equals("addParcelToCar")) {
//				// Connect between an existing small parcel to warehouse
//				String parcelId = input.next();
//				String vin = input.next();
//					
//					isUpdated = sysData.addParcelToCar(parcelId, vin);
//					if (isUpdated) { // if added successfully, then true returned,
//						// the following message is written to the output file
//						MyFileLogWriter.writeToFileInSeparateLine("Successfully added parcel: " + parcelId + " to car " + vin);
//						} else {
//							MyFileLogWriter.writeToFileInSeparateLine("Failed to add parcel to car "+vin);
//						}
//				}
//			// ================ Query Command ================
//			else if (command.equals("getFreeTrucks")) {
//				
//				ArrayList<Truck> trucks = sysData.getFreeTrucks();
//				
//				if(trucks.size()==0) {
//					MyFileLogWriter.writeToFileInSeparateLine("There are no free trucks at the moment");
//				}
//				else {
//					StringBuilder str = new StringBuilder("Available trucks:");
//					
//					for(Truck t:trucks) {
//						str.append(t.getVin().toString()+",");
//					}
//					MyFileLogWriter.writeToFileInSeparateLine(str.toString());
//				}
//				
//			}
//			else if(command.equals("getAllTrucksTransactions")) {
//				String whId = input.next();
//				Collection<Truck> trucks = sysData.getAllTrucksTransactions(Integer.parseInt(whId));
//				if(trucks.size()>0) {
//					for(Truck t:trucks) {
//						MyFileLogWriter.writeToFileInSeparateLine("Truck:"+t.getVin()+" passed from warehouse "+whId+"\n");
//					}
//				}
//				else {
//					MyFileLogWriter.writeToFileInSeparateLine("No trucks passed from warehouse "+whId);
//				}
//			}
//			else if(command.equals("getParcelsFromWareHouseByPriceRange")) {
//				String whId = input.next();
//				String lower = input.next();
//				String higher = input.next();
//				Collection<Parcel> parcels = sysData.getParcelsFromWareHouseByPriceRange(
//						Integer.parseInt(whId), Integer.parseInt(lower), Integer.parseInt(higher));
//				
//				
//				if(parcels!=null&&parcels.size()!=0) 
//				{
//					StringBuilder str = new StringBuilder("\nParcels on warehouse "+whId+" and their price between "+lower+" and "+
//				higher+ "  are:\n");
//					int i=1;
//					for(Parcel p:parcels) {
//						str.append(i+": Parcel ID:"+p.getParcelId() + " and its price is:"+p.getCurrentCost()+"\n");
//						i++;
//					}
//					MyFileLogWriter.writeToFileInSeparateLine(str.toString());
//
//				}
//				else {
//					MyFileLogWriter.writeToFileInSeparateLine("No parcels found on warehouse "+whId+" and their price exist on the range");
//
//				}
//			}
//			else if(command.equals("getAllUniqCategories")) {
//				Long receiverId = Long.parseLong(input.next());
//				
//				int number= sysData.getAllUniqCategories(receiverId);
//				MyFileLogWriter.writeToFileInSeparateLine("Number of unique categories to receiver:"+receiverId+" is:"+number);
//			}
//			// ================ Query Command ================
//			else if (command.equals("getPersonWithMaxReceivedItems"))
//			{
//				Receiver r = sysData.getPersonWithMaxReceivedItems();
//				
//				if(r==null) {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to receive person with max received items");
//				}
//				else {
//					MyFileLogWriter.writeToFileInSeparateLine("Max received items is:"+r.toString());
//				}
//			}
//			// ================ Query Command ================
//			else if(command.equals("getParcelLocation")) {
//				String parcelId = input.next();
//				
//				Address currentLocation = sysData.getParcelLocation(parcelId);
//				if(currentLocation == null) {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to get parcel location.");
//				}
//				else {
//					MyFileLogWriter.writeToFileInSeparateLine("Parcel "+parcelId+" existing on "+currentLocation.toString());
//				}
//			}
//		
//			// ================ Query Command ================
//			else if (command.equals("getTruckWithMaxWeight")) {
//				
//				Truck truck = sysData.getTruckWithMaxWeight();
//				if(truck == null) {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to get truck");
//				}
//				else {
//					MyFileLogWriter.writeToFileInSeparateLine("Truck with max weight:"+truck.getVin());
//				}
//				
//			}
//			// ================ Query Command ================
//			
//				else if(command.equals("busyWarehouse")) {
//				Collection<WareHouse> RecList =  sysData.busyWarehouse();
//				if(RecList==null || RecList.size()==0) {
//					MyFileLogWriter.writeToFileInSeparateLine("No busy warehouses found");
//				}
//				else {
//					int i=1;
//					MyFileLogWriter.writeToFileInSeparateLine("Busy warehouse:");
//					for(WareHouse w : RecList)
//						MyFileLogWriter.writeToFileInSeparateLine((i++) + "\t" + w.toString());
//			}
//		
//			}
//		
//		
//			// ================ Query Command ================
//			else if (command.equals("smartBuy")) {
//				double minPrice = Double.parseDouble(input.next());
//				ArrayList<LargeParcel> list= sysData.smartBuy(minPrice);
//				if(list == null) {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to find large parcels");
//				}
//				else {
//						int i = 1;
//						for (LargeParcel lp : list)
//							if (lp != null) 
//							MyFileLogWriter.writeToFileInSeparateLine((i++) + "\t" + lp);
//				}
//				
//			}
//			// ================ Query Command ================
//			else if (command.equals("TwiceAtWarehouse")) {
//				HashMap<Parcel,Integer> list= sysData.TwiceAtWarehouse();
//				if(list == null) {
//					MyFileLogWriter.writeToFileInSeparateLine("Failed to find warehouses");
//				}
//				else {
//						int i = 1;
//						for (Parcel p : list.keySet())
//							if (p != null) 
//							MyFileLogWriter.writeToFileInSeparateLine((i++) + "\t Parcel :" + p.getParcelId()+"\t was " + list.get(p)+" times");
//				}
//				
//			}
//			// ================ Query Command ================
//
//				else if (command.equals("sendTruckToWareHouse")) {
//					String vin = input.next();
//					
//					isUpdated = sysData.sendTruckToWareHouse(vin);
//					if(isUpdated)
//					{
//						MyFileLogWriter.writeToFileInSeparateLine("Truck "+vin+" dismantle its parcels to destination warehouse successfully");
//					}
//					else {
//						MyFileLogWriter.writeToFileInSeparateLine("Failed to dismantle truck "+vin+" parcels.");
//
//					}
//				}
////			// ================ Query Command ================
//			
//				else if (command.equals("sendCarToDestination")) {
//					String vin = input.next();
//					
//					isUpdated = sysData.sendCarToDestination(vin);
//					if(isUpdated) {
//						MyFileLogWriter.writeToFileInSeparateLine("Car "+vin+" sent to receivers.");
//					}
//					else {
//						MyFileLogWriter.writeToFileInSeparateLine("Failed to send car "+vin+" to receivers.");
//
//					}
//				}
////			// ================ Query Command ================
//			
//				else if(command.equals("getReceiverItems")) {
//					Long id= Long.parseLong(input.next());
//					ArrayList<Item> receivedItems = sysData.getReceiverItems(id);
//					if(receivedItems==null || receivedItems.size()==0) {
//						MyFileLogWriter.writeToFileInSeparateLine("Receiver "+id+" did not recieved any items !!!");
//					}
//					else {
//
//						StringBuilder str = new StringBuilder("Received items for "+id+" are: ");
//						for(Item i:receivedItems) {
//							str.append(i.getItemName()+",");
//							
//						}
//						MyFileLogWriter.writeToFileInSeparateLine(str.toString());
//						
//					}
//					
//					
//					
//				}
//			// ================ Query Command ================
//			
//				else if(command.equals("sendParcelsToReceivers")) {
//					int warehosueid= Integer.parseInt(input.next());
//					String vin = input.next();
//					ArrayList<Parcel> RecList = (ArrayList<Parcel>) sysData.sendParcelsToReceivers(warehosueid,vin);
//					if(RecList==null || RecList.size()==0) {
//						MyFileLogWriter.writeToFileInSeparateLine("Failed to upload list");
//					}
//					else {
//						int i = 1;
//						MyFileLogWriter.writeToFileInSeparateLine("Parcels which car "+vin+" sent to receivers are:");
//						for (Parcel p : RecList)
//							if (p != null) 
//							MyFileLogWriter.writeToFileInSeparateLine((i++) + "\t" +"Parcel ID:"+p.getParcelId()+",Receiver: "+ p.getReceiver().getId());
//				}
//				
//				}
//			
//			// ================ Separate Command ================
//			else if (command.equals("//")) {
//				// Separate line command
//				MyFileLogWriter.writeToFileInSeparateLine("");
//				input.nextLine();
//				// ignores line starts by '//' the result is empty lines in the output.txt
//			}
//			// ================ Command ================
//			else
//				System.out.printf("Command %s does not exist\n", command);
//		} // ~ end of clause - while input has next
//		
//		MyFileLogWriter.saveLogFile(); // save the output file
//		input.close(); // close connection to input file
//		System.out.println("[End Of process]");
//		System.out.println("\n NOTICE:\n\t\"End of process\" " + "does NOT mean that all your methods are correct.\n"+ "\n==>\t You NEED to check the \"output.txt\" file");
//	}// END OF ~ main
//	
//	
//}// ~ END OF Class MainClass
