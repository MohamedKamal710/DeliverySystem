package InternalFrames;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Address;
import Model.Car;
import Model.Driver;
import Model.Parcel;
import Model.Truck;
import Model.Vehicle;
import Model.WareHouse;
import Utils.Constants;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class CarDriverView extends JInternalFrame {
	private JTable table;
//	ArrayList<WareHouse> carFrom = new ArrayList<>();// parcels in the wareHouse
//	//		   HashSet<Parcel> Addressarray = new HashSet<>() ;
//
//	ArrayList<Parcel> parcelsInWh = new ArrayList<>( ); 
//	WareHouse temp = null ; // selected warehouse in the table 
//	Integer pCount =0  ;// parcels in the wareHouse
	
	
	Car tempCar2 = null ; // the car - 
	
	/***
	 * this frame is built to show all the receivers  that the driver suppose to deliver the loaded parcels to .
	 * @param driver
	 */
	public CarDriverView(Driver driver) {
		getContentPane().setLayout(null);
//		carFrom.clear();
//		carFrom =  new ArrayList<>(driver.getFrom()) ;
//	   
    	if(SysData.getInstance().getVehclesMap().isEmpty()) {
			System.out.println("THere Are no VEHICLES ");
		}
    	
		ArrayList<Vehicle> vv = new ArrayList<>(SysData.getInstance().getVehclesMap().values());
		for(Vehicle v : vv) {
			if(v.isInUse()) {
			if(v instanceof Car) {
				Car car =(Car) v ;
			//	System.out.println(car.getDriver().getId()+"---"+driver.getId());
				if(car.getDriver().getId()==driver.getId() ) {
					tempCar2 = car ;
					break ;
				}
			}
			}
		}
			
		
		JLabel lblWelcome = new JLabel("Welcome , ");
		lblWelcome.setBounds(24, 11, 121, 37);
		lblWelcome.setForeground(new Color(106, 90, 205));
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(lblWelcome);
		
		JLabel lblTodaysShcedual = new JLabel("Today's Shcedual : ");
		lblTodaysShcedual.setBounds(24, 77, 195, 37);
		lblTodaysShcedual.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTodaysShcedual.setForeground(new Color(0, 0, 128));
		lblTodaysShcedual.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTodaysShcedual);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(Color.WHITE);
		scrollPane_1.setBounds(41, 175, 595, 196);
		getContentPane().add(scrollPane_1);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {  
				if(column==4) return true ;
                return false;     
			}};
	
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Parcel ID", "Parcel's Weight", "Receiver's ID", "Address", "Done"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(68);
		table.getColumnModel().getColumn(1).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(171);
		table.getColumnModel().getColumn(4).setPreferredWidth(41);
		scrollPane_1.setViewportView(table);
			
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		Object newData[] = new Object[5];
		if(tempCar2 != null) {
			if(!tempCar2.getParcels().isEmpty()) {
				for(Parcel parcel : tempCar2.getParcels()) {
					newData[0] = parcel.getParcelId();
					newData[1]= parcel.getWeight();
					newData[2] = parcel.getReceiver().getId();
					newData[3] = parcel.getReceiver().getAddress();
					newData[4]= false;
					((DefaultTableModel) table.getModel()).addRow(newData);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "EMPTY !");
		}
		JLabel lblParcelsToDeliver = new JLabel("Parcels To Deliver  :");
		lblParcelsToDeliver.setFont(new Font("Traditional Arabic", Font.BOLD, 14));
		lblParcelsToDeliver.setHorizontalAlignment(SwingConstants.CENTER);
		lblParcelsToDeliver.setBounds(80, 346, 215, 25);
		getContentPane().add(lblParcelsToDeliver);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				Object newData[] = new Object[5];
				if(tempCar2 != null) {
					if(!tempCar2.getParcels().isEmpty()) {
						for(Parcel parcel : tempCar2.getParcels()) {
							newData[0] = parcel.getParcelId();
							newData[1]= parcel.getWeight();
							newData[2] = parcel.getReceiver().getId();
							newData[3] = parcel.getReceiver().getAddress();
							newData[4]= false;
							((DefaultTableModel) table.getModel()).addRow(newData);
						}
					}
				}
			}
			
		});
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col == 4) {
		           // TODO: handle cell click
		        if(	Boolean.valueOf((boolean) table.getValueAt(row, 4)) ==true ) {
		        	tempCar2.sendParcelToDestination( tempCar2.getParcels().get(row));
		        	model.removeRow(row);
		        }	 
		        }
		       if(tempCar2.getParcels().isEmpty()) {
					PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
		    	   JOptionPane.showMessageDialog(null, "Parcels have been delivered , well done mate !");
		    	   driver.setWarehouseToLoadFrom(null);
		    	   driver.setDriverInUse(false);
		    	   System.out.println(tempCar2);
		    	   tempCar2.setInUse(false);
		    	   tempCar2.setDriver(null);
		    	   
		    	   dispose();
		       }
		    }
		   
		});
		btnRefresh.setBounds(408, 77, 89, 23);
		getContentPane().add(btnRefresh);
		
		JLabel lblNoJobsAvailable = new JLabel("No Jobs Available");
		lblNoJobsAvailable.setForeground(Color.RED);
		lblNoJobsAvailable.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNoJobsAvailable.setBounds(211, 81, 134, 32);
		getContentPane().add(lblNoJobsAvailable);
		lblNoJobsAvailable.setVisible(false);
		
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