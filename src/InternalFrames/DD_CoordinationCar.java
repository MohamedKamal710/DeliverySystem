package InternalFrames;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Address;
import Model.Car;
import Model.Driver;
import Model.Parcel;
import Model.Vehicle;
import Model.WareHouse;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class DD_CoordinationCar extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private Driver driver ;
	private ArrayList<WareHouse> From = new ArrayList();
	  TreeSet<WareHouse> WhArray = new TreeSet<>();
		ArrayList<Driver> tempDriver = new ArrayList<>();
		 ArrayList<Parcel> Parray = new ArrayList<>() ;
		   HashSet<Address> Addressarray = new HashSet<>() ;
		   Car tempCar2 = null ;
		   
		   /***
		    * this frame is built to load all the possible parcels in a car ,
		    * it has 4 tables
		    * first table to show the cars in use,
		    * second table shows the warehouses ,
		    * 3rd table shows the parcels that suppose to be loaded in the car,
		    * 4th table shows the address of the receivers that the driver should deliver to ,
		    */
	public DD_CoordinationCar() {
		setTitle("cartset");
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setClosable(true);
		
		
		getContentPane().setLayout(null);
		
		
		ArrayList<Vehicle> vv = new ArrayList<>(SysData.getInstance().getVehclesMap().values());
		for(Vehicle v : vv) {
			if(v.isInUse()) {
			if(v instanceof Car) {
				Car car =(Car) v ;
if(car.getDriver()!= null  && driver != null)
	if(car.getDriver().getId()==driver.getId() ) {
					tempCar2 = car ;
					break ;
				}
			}
			}
		}
		
		
		JLabel lblNoD = new JLabel("No Drivers To Display ");
		lblNoD.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoD.setBounds(10, 337, 167, 38);
		getContentPane().add(lblNoD);
		lblNoD.setVisible(false);
		
		
		
		JButton btnAddRoute = new JButton("Add route To Driver");
        btnAddRoute.setEnabled(true);
		btnAddRoute.setBounds(510, 337, 167, 31);
		getContentPane().add(btnAddRoute);
		
		JLabel lblNewLabel = new JLabel("WareHouses To send to Receivers  : ");
		lblNewLabel.setBounds(510, 23, 259, 54);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
	
		
		
		scrollPane.setBounds(360, 88, 422, 222);
		getContentPane().add(scrollPane);
		
		
		
		
		//table = new JTable();
		table = new JTable()  {
			public boolean isCellEditable(int row, int column) {                
            return false;               
    }
			
			};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"WareHouse Id", "Region", "Address"
			}
		));
		
	
		
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(2).setPreferredWidth(208);
		scrollPane.setViewportView(table);
		
		JLabel EmptyWhlbl = new JLabel("WareHouses Are All Empty ! ");
		EmptyWhlbl.setForeground(Color.RED);
		EmptyWhlbl.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		EmptyWhlbl.setHorizontalAlignment(SwingConstants.CENTER);
		EmptyWhlbl.setBounds(293, 372, 213, 31);
		getContentPane().add(EmptyWhlbl);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 415, 378, 171);
		getContentPane().add(scrollPane_1);
		
		//table_1 = new JTable();
		table_1 = new JTable()  {
			public boolean isCellEditable(int row, int column) {                
            return false;               
    }
			};
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "Address"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(311);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(451, 415, 318, 171);
		getContentPane().add(scrollPane_2);
		
		//table_2 = new JTable();
		table_2 = new JTable()  {
			public boolean isCellEditable(int row, int column) {                
            return false;               
    }
			};
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ParcelID", "Receiver ID", "Receiver Name"
			}
		));
		table_2.getColumnModel().getColumn(2).setPreferredWidth(113);
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblAddress = new JLabel("Destination Addresses ");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(141, 373, 130, 31);
		getContentPane().add(lblAddress);
		
		JLabel lblParcelsToBe = new JLabel("Parcels To Be Delivered !");
		lblParcelsToBe.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblParcelsToBe.setHorizontalAlignment(SwingConstants.CENTER);
		lblParcelsToBe.setBounds(542, 383, 151, 24);
		getContentPane().add(lblParcelsToBe);
		
		JButton btnRefresh = new JButton("Refresh");
	
		btnRefresh.setBounds(693, 337, 89, 23);
		getContentPane().add(btnRefresh);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		scrollPane_3.setBounds(25, 88, 292, 245);
		getContentPane().add(scrollPane_3);
		
		table_3 = new JTable(){
				public boolean isCellEditable(int row, int column) {                
                return false;               
        }
				};
		table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
	
				
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CarDriver", "CarId"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_3.setViewportView(table_3);
		
		
	
		JLabel lblChooseDriver = new JLabel("Choose Driver : ");
		lblChooseDriver.setBounds(60, 29, 137, 48);
		getContentPane().add(lblChooseDriver);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.setBounds(167, 39, 83, 38);
		getContentPane().add(btnRefresh_1);
		
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				((DefaultTableModel) table_3.getModel()).setRowCount(0);
				Object[] Row = new Object[2];
				for(Map.Entry<String, Vehicle> d : SysData.getInstance().getVehclesMap().entrySet()) {	
					if(d.getValue() instanceof Car) {
						if(d.getValue().isInUse() && d.getValue().getParcels().isEmpty() && d.getValue().getDriver() != null) {
							Row[0] = d.getValue().getDriver().getFirstName() + " " + d.getValue().getDriver().getSurname();
							Row[1] = d.getKey();
							((DefaultTableModel) table_3.getModel()).addRow(Row);
						}
				}
				}
			}
		});
		
		
		JLabel lblPickADriver = new JLabel("Pick A Driver & A wareHouse pls :");
		lblPickADriver.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPickADriver.setForeground(Color.RED);
		lblPickADriver.setBounds(248, 330, 238, 48);
		getContentPane().add(lblPickADriver);
		 lblPickADriver.setVisible(false);
	   
		//////////////// driver's table -******
	table_3.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
    if (table_3.getSelectedRow() != -1 ) {
    	int Selected = table_3.getSelectedRow() ;
    	
    	ArrayList<Driver> temp= new ArrayList<>();
		for (Driver d : SysData.getInstance().allDrivers()) {
			if(!d.getFrom().isEmpty()) {
				temp.add(d) ;
			}
		}
		
		if(temp.isEmpty()) {

		}else {
			
			System.out.println("the array is not empty !! ");
			driver = temp.get(Selected);
			System.out.println("the driver Selected is : "+driver+ "  -  "+Selected );
		}
    }
		}
	});
		/*
		

		
	                                               EmptyWhlbl.setVisible(false);		
		
		/*
           ArrayList<WareHouse> WhArray = new ArrayList<>();
           for(WareHouse w : SysData.getInstance().WareHouses()) {
        	   if(!w.getParcels().isEmpty()) {
        		WhArray.add(w) ;   
        	   }
           }
           
           
		if(!WhArray.isEmpty() ) {
			EmptyWhlbl.setVisible(false);	
			((DefaultTableModel) table.getModel()).setRowCount(0);
	for(WareHouse w :WhArray) {
			Object[] Row = new Object[3];
		
			Row[0] = w.getWarehouseId() ;
			Row[1]= w.getRegion() ;
			Row[2]= w.getAddress();
	      
			((DefaultTableModel) table.getModel()).addRow(Row);
			
		
	         
		}
	
	}else {
		// TODO - check this Massage !! 
		EmptyWhlbl.setVisible(true);	
	}
			
		
		   ArrayList<Parcel> Parray = new ArrayList<>() ;
		   ArrayList<Address> Addressarray = new ArrayList<>() ;
		/*
		table.addMouseListener(new MouseAdapter() {
			
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			    	System.out.println("the two chicks is here !! ");
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      System.out.println("printing Row  : "+row);
			   //   int column = target.getSelectedColumn();
			      // do some action if appropriate column
			      
			      
			   //   ((DefaultTableModel) table_1.getModel()).setRowCount(0);
			    //  ((DefaultTableModel) table_2.getModel()).setRowCount(0);
			      
			          WareHouse Wh =  WhArray.get(row);
               Parray.addAll((ArrayList<Parcel>) SysData.getInstance().sendParcelsToReceivers2(Wh.getWarehouseId(), car.getVin()) );
//   ArrayList<Address> Addressarray = new ArrayList<>();
   
   for(Parcel p : Parray) {
	   Addressarray.add(p.getReceiver().getAddress());
   }
               
   
	if(!Parray.isEmpty() ) {
		((DefaultTableModel) table_2.getModel()).setRowCount(0);
for(Parcel p  :Parray) {
		Object[] Row = new Object[3];
	
		Row[0] = p.getParcelId() ;
		Row[1]= p.getReceiver().getId() ;
		Row[2]= p.getReceiver().getFirstName()+" "+p.getReceiver().getSurname();
      
		((DefaultTableModel) table_2.getModel()).addRow(Row);
	       
	}

}else {
	// TODO - check this Massage !! 
	table_2.setVisible(false);	
}
	
	
	
	if(!Addressarray.isEmpty() ) {
		table_1.setVisible(true);
		((DefaultTableModel) table_1.getModel()).setRowCount(0);
for(Address a :Addressarray) {
		Object[] Row = new Object[2];
	
		Row[0] = "*" ;
		Row[1]= a ;
		//Row[2]= w.getAddress();
      
		((DefaultTableModel) table_1.getModel()).addRow(Row);
		
	
         
	}

}else {
	// TODO - check this Massage !! 
	table_1.setVisible(false);	
}
	
	
      
			      
			    
			  }
			  }});
		
		*/
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// int row =0 ;
			    ((DefaultTableModel) table.getModel()).setRowCount(0);				  
//				  boolean flag= false;
		           for(WareHouse w : SysData.getInstance().WareHouses()) {
		           	   if(!w.getParcels().isEmpty() && !WhArray.contains(w)) {
		           		   for(Parcel par : w.getParcels()) {
		           			   if(par.getReceiver().getAddress().getCity().equals(w.getAddress().getCity())) {
				        		   WhArray.add(w) ; 
		           			   }
		           		   }
		        	   }
		           }
		           
				if(!WhArray.isEmpty() ) {
					  ((DefaultTableModel) table.getModel()).setRowCount(0);
					  EmptyWhlbl.setVisible(false);	
					  for(WareHouse w :WhArray) {
						  Object[] Row = new Object[3];
						  Row[0] = w.getWarehouseId() ;
						  Row[1]= w.getRegion() ;
						  Row[2]= w.getAddress();
						  ((DefaultTableModel) table.getModel()).addRow(Row);
					  }
				}
				else {
				EmptyWhlbl.setVisible(true);
				}
				
			  }});
				
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
//				 SwingUtilities.invokeLater( 
//					        new Runnable() {
//					            public void run() {
//					            }
//					        }
//					    );
				 	((DefaultTableModel) table_2.getModel()).setRowCount(0);
					((DefaultTableModel) table_1.getModel()).setRowCount(0);
				 Parray.clear();
				 Addressarray.clear();
				 	if(table.getSelectedRow() >= 0) {
				 		WareHouse warehous = SysData.getInstance().getWareHouseById((int)table.getValueAt(table.getSelectedRow(), 0)); 
				 		for(Parcel p : warehous.getParcels()) {
				 				if(p.getReceiver().getAddress().getCity().equals(warehous.getAddress().getCity())) {
				 					Parray.add(p);
				 				}
				 		}
				 		for(Parcel lol : Parray) {
			       			   Addressarray.add(lol.getReceiver().getAddress());
				 		}
				 		
				 		if(!Parray.isEmpty() ) {
			       			//((DefaultTableModel) table_2.getModel()).setRowCount(0);
			       		for(Parcel p  :Parray) {
			       				Object[] Row1 = new Object[3];
			       			
			       				Row1[0] = p.getParcelId() ;
			       				Row1[1]= p.getReceiver().getId() ;
			       				Row1[2]= p.getReceiver().getFirstName()+" "+p.getReceiver().getSurname();
			       		      
			       				((DefaultTableModel) table_2.getModel()).addRow(Row1);
			       			       
			       			}

			       		}else {
			       			// TODO - check this Massage !! 
			       			table_2.setVisible(false);	
			       		}
						  
			       			if(!Addressarray.isEmpty() ) {
			       				table_1.setVisible(true);
			       				//((DefaultTableModel) table_1.getModel()).setRowCount(0);
			       		for(Address a :Addressarray) {
			       				Object[] Row2 = new Object[2];
			       				Row2[0] = "*" ;
			       				Row2[1]= a ;
			       				//Row[2]= w.getAddress();
			       				((DefaultTableModel) table_1.getModel()).addRow(Row2);
			       			}

			       		}else {
			       			// TODO - check this Massage !! 
			       			table_1.setVisible(false);	
			       			}     
				 	}
				 }
			});
//		table.addMouseListener(new MouseAdapter() {
//			  public void mouseClicked(MouseEvent e) {
//			
//			    if (e.getClickCount() == 2) {
//			      JTable target = (JTable)e.getSource();
//			      int row = target.getSelectedRow();
//			      int column = target.getSelectedColumn();
//			  	((DefaultTableModel) table_2.getModel()).setRowCount(0);
//				((DefaultTableModel) table_1.getModel()).setRowCount(0);
//				Parray.clear();
//				  Addressarray.clear();
//			      for(Parcel p : WhArray.get(row).getParcels()) {
//					 
//						   Parray.add(p);
//			      }
//					   
//			      for(Parcel p : Parray) {
//	       			   Addressarray.add(p.getReceiver().getAddress());
//	       		   }
//			      
//			      //-------------------------------------------------------------
//			      
//	       			if(!Parray.isEmpty() ) {
//	       			//((DefaultTableModel) table_2.getModel()).setRowCount(0);
//	       		for(Parcel p  :Parray) {
//	       				Object[] Row1 = new Object[3];
//	       			
//	       				Row1[0] = p.getParcelId() ;
//	       				Row1[1]= p.getReceiver().getId() ;
//	       				Row1[2]= p.getReceiver().getFirstName()+" "+p.getReceiver().getSurname();
//	       		      
//	       				((DefaultTableModel) table_2.getModel()).addRow(Row1);
//	       			       
//	       			}
//
//	       		}else {
//	       			// TODO - check this Massage !! 
//	       			table_2.setVisible(false);	
//	       		}
//				  
//	       			
//	       			
//	       			
//	       			if(!Addressarray.isEmpty() ) {
//	       				table_1.setVisible(true);
//	       				//((DefaultTableModel) table_1.getModel()).setRowCount(0);
//	       		for(Address a :Addressarray) {
//	       				Object[] Row2 = new Object[2];
//	       			
//	       				Row2[0] = "*" ;
//	       				Row2[1]= a ;
//	       				//Row[2]= w.getAddress();
//	       		      
//	       				((DefaultTableModel) table_1.getModel()).addRow(Row2);
//	       				
//	       			
//	       		         
//	       			}
//
//	       		}else {
//	       			// TODO - check this Massage !! 
//	       			table_1.setVisible(false);	
//	       			}     
//			    }
//			  }
//			});
		
		
		
		
		
		//-----------------------------------------------------------------------------------------------------------
					  /*
					   for(Parcel p : SysData.getInstance().getParcels()) {
						   if((SysData.getInstance().getParcelLocation(p.getParcelId()).city).equals(w.getAddress().city)   ) {
							   Parray.add(p);
							   
						   }
						   
					   }
		    
		       		   for(Parcel p : Parray) {
		       			   Addressarray.add(p.getReceiver().getAddress());
		       		   }
		       		               
		       		   
		       			if(!Parray.isEmpty() ) {
		       			//	((DefaultTableModel) table_2.getModel()).setRowCount(0);
		       		for(Parcel p  :Parray) {
		       				Object[] Row1 = new Object[3];
		       			
		       				Row1[0] = p.getParcelId() ;
		       				Row1[1]= p.getReceiver().getId() ;
		       				Row1[2]= p.getReceiver().getFirstName()+" "+p.getReceiver().getSurname();
		       		      
		       				((DefaultTableModel) table_2.getModel()).addRow(Row);
		       			       
		       			}

		       		}else {
		       			// TODO - check this Massage !! 
		       			table_2.setVisible(false);	
		       		}
		       			
		       			
		       			
		       			if(!Addressarray.isEmpty() ) {
		       				table_1.setVisible(true);
		       				//((DefaultTableModel) table_1.getModel()).setRowCount(0);
		       		for(Address a :Addressarray) {
		       				Object[] Row2 = new Object[2];
		       			
		       				Row2[0] = "*" ;
		       				Row2[1]= a ;
		       				//Row[2]= w.getAddress();
		       		      
		       				((DefaultTableModel) table_1.getModel()).addRow(Row);
		       				
		       			
		       		         
		       			}

		       		}else {
		       			// TODO - check this Massage !! 
		       			table_1.setVisible(false);	
		       		}
					   
					   
					   
					   
					   
			         
				}
			
			}else {
				// TODO - check this Massage !! 
				EmptyWhlbl.setVisible(true);	
			}
			
			*/
			
			
			/***********************************
					
				/*
				   ArrayList<Parcel> Parray = new ArrayList<>() ;
				   ArrayList<Address> Addressarray = new ArrayList<>() ;
				
				
				   row =table.getSelectedRow() ;
					      System.out.println("printing Row  : "+row);
					   //   int column = target.getSelectedColumn();
					      // do some action if appropriate column
					      
					      
					   //   ((DefaultTableModel) table_1.getModel()).setRowCount(0);
					    //  ((DefaultTableModel) table_2.getModel()).setRowCount(0);
					      if(row != -1) {
					          WareHouse Wh =  WhArray.get(row);
		               Parray.addAll((ArrayList<Parcel>) SysData.getInstance().sendParcelsToReceivers2(Wh.getWarehouseId(), car.getVin()) );
		//   ArrayList<Address> Addressarray = new ArrayList<>();
		   
		   for(Parcel p : Parray) {
			   Addressarray.add(p.getReceiver().getAddress());
		   }
		               
		   
			if(!Parray.isEmpty() ) {
			//	((DefaultTableModel) table_2.getModel()).setRowCount(0);
		for(Parcel p  :Parray) {
				Object[] Row = new Object[3];
			
				Row[0] = p.getParcelId() ;
				Row[1]= p.getReceiver().getId() ;
				Row[2]= p.getReceiver().getFirstName()+" "+p.getReceiver().getSurname();
		      
				((DefaultTableModel) table_2.getModel()).addRow(Row);
			       
			}

		}else {
			// TODO - check this Massage !! 
			table_2.setVisible(false);	
		}
			
			
			
			if(!Addressarray.isEmpty() ) {
				table_1.setVisible(true);
				//((DefaultTableModel) table_1.getModel()).setRowCount(0);
		for(Address a :Addressarray) {
				Object[] Row = new Object[2];
			
				Row[0] = "*" ;
				Row[1]= a ;
				//Row[2]= w.getAddress();
		      
				((DefaultTableModel) table_1.getModel()).addRow(Row);
				
			
		         
			}

		}else {
			// TODO - check this Massage !! 
			table_1.setVisible(false);	
		}
			
					      }
				
					
					    */
					  
//-*---------------------------------------------------------------------
		
	       
		btnAddRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 lblPickADriver.setVisible(false);
				if(table_3.getSelectedRow() >= 0  && table.getSelectedRow() >=0 ) {
						Driver driverTemp = SysData.getInstance().getVehclesMap().get(table_3.getValueAt(table_3.getSelectedRow(), 1)).getDriver();
						WareHouse selectedWH = SysData.getInstance().getWareHouseById((int)table.getValueAt(table.getSelectedRow(), 0));
						if(driverTemp.getWarehouseToLoadFrom() == null) {
							SysData.getInstance().sendParcelsToReceivers(selectedWH.getWarehouseId(),(String)table_3.getValueAt(table_3.getSelectedRow(), 1));
							btnRefresh_1.doClick();
							PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
							JOptionPane.showMessageDialog(null, "DONE ! ");
							btnRefresh.doClick();
							btnRefresh_1.doClick();
						}
							else if(driverTemp.getWarehouseToLoadFrom() != null)	{
							JOptionPane.showMessageDialog(null, "Already selected !");
							PlayMusic("Sound\\\\windows_xp_ding.wav");

							}
						
						
//				int selected3=	 table_3.getSelectedRow() ;
//			   driver = tempDriver.get(selected3) ;
//			   int selected = table.getSelectedRow() ;
//				Driver driver2 = driver ;
//				System.out.println("*"+selected+ "//- 3 "+selected3);
//				driver2.getFrom().add(WhArray.get(selected)) ;
//				SysData.getInstance().sendParcelsToReceivers(WhArray.get(selected).getWarehouseId(), tempCar2.getVin());
//			    SysData.getInstance().getAllDriversMap().replace(driver.getId(),driver, driver2);
//			     SysData.getInstance().allDrivers().remove(driver);
//			     SysData.getInstance().allDrivers().add(driver2) ;
//			 System.out.println( "sys driver from : "+SysData.getInstance().getAllDriversMap().get(driver.getId()).getFrom());
//			     System.out.println("this is driver 2"+driver2.getFrom());
			     
				}else {
					 lblPickADriver.setVisible(true);
				}
				
				
			}
		});
	
		//--------------------------------------------------------------------------------------
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 899);
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		
	}
	
	public void PlayMusic(String filepath) {
		InputStream music;
		try {
			music = new FileInputStream(new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR with file path ");
		}
		
	}
}