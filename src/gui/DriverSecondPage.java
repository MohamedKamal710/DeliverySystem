package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import InternalFrames.UNLOAD;
import Model.Car;
import Model.Driver;
import Model.Parcel;
import Model.Truck;
import Model.Vehicle;
import Model.WareHouse;
import SerializationDemo.Serialization;

import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class DriverSecondPage extends JFrame {

	private JPanel contentPane;
	private Truck truck = null;
	private Driver driver = null;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverPage frame = new DriverPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DriverSecondPage() {
		setTitle("Driver page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 695);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login loginPage = new login();
				loginPage.getJframe().setVisible(true);
				dispose();
			}
		});
		
		mntmLogOut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnNewMenu.add(mntmLogOut);
		
		JLabel label_1 = new JLabel("");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Sitka Small", Font.PLAIN, 35));
		label_1.setBounds(32, 182, 970, 61);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null,"Do you want to save changes?", "Save Data",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	Serialization serial = new Serialization();
							
							serial.serialize("src//SerializationDemo//Del2.ser");
				            System.exit(0);
			            }else if (result == JOptionPane.NO_OPTION){
				            System.exit(0);
			            }else {
			            }
				System.exit(0);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mntmNewMenuItem.doClick();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1057, 648);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(label_1);
		label_1.setVisible(true);
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.ORANGE);
		lblWelcome.setFont(new Font("Verdana Pro", Font.PLAIN, 40));
		lblWelcome.setBounds(12, 31, 204, 66);
		panel.add(lblWelcome);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("Verdana Pro", Font.PLAIN, 40));
		label.setBounds(228, 48, 662, 36);
		panel.add(label);
		
		JLabel jobLabel = new JLabel("You have no job for today , ");
		jobLabel.setForeground(new Color(253, 245, 230));
		jobLabel.setFont(new Font("Sitka Small", Font.PLAIN, 35));
		jobLabel.setBounds(22, 110, 1011, 83);
		panel.add(jobLabel);
		
		System.out.println(login.idUser);
		Car car = null;
		if(login.idUser  != null)
			 driver = SysData.getInstance().getAllDriversMap().get(login.idUser);
		
		if(driver != null) {
			System.out.println("DRIVER IS FOUND");
			label.setText(driver.getFirstName() + " " + driver.getSurname());
		for(Map.Entry<String, Vehicle> ridenV : SysData.getInstance().getVehclesMap().entrySet()) {
				System.out.println("TRUCK:     " + ridenV.getValue().getVin());
			if(ridenV.getValue().isInUse())	
				if(ridenV.getValue() instanceof Truck) {
					System.out.println("ITS A TRUCK PEAPLE");
					System.out.println(driver);
					System.out.println(ridenV.getValue().getDriver().getId());
					if(ridenV.getValue().getDriver().equals(driver)) {
						System.out.println("DRIVER IS matched");
						truck = (Truck)ridenV.getValue(); 
						if(truck!= null ) { 
							jobLabel.setText(" You are Driving : " + truck.getVin() + " for today ,");
//							label_1.setText("the truck load should be unloaded in Warehouse : " + truck.getDestinationWareHouse().getWarehouseId());
						}
						else {
							jobLabel.setText("You have no job for today , ");
							label_1.setText("");
						}
							
					}
			}
			else if(ridenV.getValue() instanceof Car) {
				System.out.println("ITS A CAR");
				if(ridenV.getValue().getDriver().equals(driver)) {
					car = (Car)ridenV.getValue();
				}
			}
		}
		}
		
		JCheckBox checked = new JCheckBox("Destination reached.");
		checked.setForeground(Color.WHITE);
		checked.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		checked.setBounds(403, 339, 254, 41);
		panel.add(checked);
		checked.setOpaque(false);
		
		JLabel lblUnloadedSuccessfuly = new JLabel("UNLOADED SUCCESSFULY !");
		lblUnloadedSuccessfuly.setForeground(new Color(0, 255, 0));
		lblUnloadedSuccessfuly.setFont(new Font("Verdana Pro", Font.PLAIN, 25));
		lblUnloadedSuccessfuly.setBounds(411, 514, 383, 55);
		panel.add(lblUnloadedSuccessfuly);
		lblUnloadedSuccessfuly.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(32, 233, 363, 386);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(152, 5, 0, 0);
		panel_1.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 26, 218, 347);
		panel_1.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"from Warehouse"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("LOAD");
		panel_1.setOpaque(false);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(242, 107, 109, 39);
		panel_1.add(btnNewButton);
		panel_1.setVisible(true);
		btnNewButton.setEnabled(false);
		DefaultTableModel model_1 = (DefaultTableModel)table_1.getModel();
		Object rowData[] = new Object[1];
		model_1.setRowCount(0);
		for(Map.Entry<WareHouse, Integer> temp : driver.getWareHousesToGo().entrySet()) {
			rowData[0] = temp.getKey().getWarehouseId();
			model_1.addRow(rowData);
		}
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 btnNewButton.setEnabled(false);
				 SwingUtilities.invokeLater( 
					        new Runnable() {
					            public void run() {
									 if(table_1.getSelectedRow()>=0)
										 btnNewButton.setEnabled(true);
					            }
					        }
					    );
				 }
			});
		
		JButton btnUnload = new JButton("UNLOAD");
		btnUnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UNLOAD dialog = new UNLOAD();
				dialog.setVisible(true);
				Object newData[] = new Object[1];
				DefaultTableModel model = (DefaultTableModel)dialog.getTable().getModel();
				model.setRowCount(0);
				if(truck!=null)
					for(Parcel parcel : truck.getParcels()) {
						newData[0] = parcel.getParcelId();
						model.addRow(newData);
				}
				
			
				dialog.getBtnNewButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.getFailedLabel().setVisible(false);
						lblUnloadedSuccessfuly.setVisible(false);
						if(driver != null && truck != null) {
							if(SysData.getInstance().sendTruckToWareHouse(truck.getVin())) {
								dialog.dispose();
								lblUnloadedSuccessfuly.setVisible(true);
								jobLabel.setText("You have no job for today , ");
								label_1.setText("");
								if(driver.getWareHousesToGo().isEmpty()) {
									driver.setDriverInUse(false);
									truck.setInUse(false);
									truck.setDriver(null);
									panel_1.setVisible(false);
								}else {
								panel_1.setVisible(true);
								checked.setSelected(false);
								checked.setVisible(false);
								btnUnload.setVisible(false);
								}
							}
							else
							{
								dialog.getFailedLabel().setVisible(true);
							}
							
						}
					}
				});
				
			}
		});
		btnUnload.setBackground(Color.ORANGE);
		btnUnload.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		btnUnload.setBounds(434, 389, 178, 55);
		panel.add(btnUnload);
		btnUnload.setEnabled(false);
		
		checked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checked.isSelected())
					btnUnload.setEnabled(true);
				else
					btnUnload.setEnabled(false);
			}
		});
		
		btnUnload.setVisible(false);
		checked.setVisible(false);
		this.setLocation(getLocation());
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ArrayList<Parcel> newArray;
			lblUnloadedSuccessfuly.setVisible(false);
			WareHouse w1 = SysData.getInstance().getWareHouseById((int)table_1.getValueAt(table_1.getSelectedRow(), 0));
			WareHouse w2 = SysData.getInstance().getWareHouseById(driver.getWareHousesToGo().get(w1));
			newArray = (ArrayList<Parcel>) SysData.getInstance().addAllPossibleParcelsToTruck(truck.getVin(), w1.getWarehouseId(), w2.getWarehouseId());
			System.out.println("SIZE ARRAY " + newArray + newArray.isEmpty());
			if(newArray != null && !newArray.isEmpty()) {
				checked.setVisible(true);
				btnUnload.setVisible(true);
				panel_1.setVisible(false);
				driver.getWareHousesToGo().remove(w1, w2.getWarehouseId());
				System.out.println("SIZE " + driver.getWareHousesToGo().size());
				model_1.setRowCount(0);
				for(Map.Entry<WareHouse, Integer> temp : driver.getWareHousesToGo().entrySet()) {
					rowData[0] = temp.getKey().getWarehouseId();
					model_1.addRow(rowData);
				
				}
				label_1.setText("the truck load should be unloaded in Warehouse : " + truck.getDestinationWareHouse().getWarehouseId());
			}
			else if(newArray == null || newArray.isEmpty()){
				driver.getWareHousesToGo().remove(w1, w2.getWarehouseId());
				JOptionPane.showMessageDialog(null, "There are no parcels available to be shipped ! \n Receivers of those parcels are in the same city of the current warehouse , \n therefor get to the next warehouse .");
				model_1.setRowCount(0);
				truck.setDestinationWareHouse(null);
				for(Map.Entry<WareHouse, Integer> temp : driver.getWareHousesToGo().entrySet()) {
					rowData[0] = temp.getKey().getWarehouseId();
					model_1.addRow(rowData);
				
				}
				if(driver.getWareHousesToGo().isEmpty()) {
					label_1.setText("You have no jobs at the moment ");
					driver.setDriverInUse(false);
					truck.setInUse(false);
					truck.setDriver(null);
					panel_1.setVisible(false);
				}
			}
			}
		});
		
//		if(driver.getWareHousesToGo().isEmpty()) {
//			label_1.setText("You have no jobs at the moment ");
//			driver.setDriverInUse(false);
//			truck.setInUse(false);
//			truck.setDriver(null);
//			panel_1.setVisible(false);
//		}
		if(driver.isDriverInUse())
		if(!truck.getParcels().isEmpty()) {
			System.out.println("BRAAAAAAAAAAAAAAAAAa");
			panel_1.setVisible(false);
			checked.setVisible(true);
			btnUnload.setVisible(true);
			label_1.setText("the truck load should be unloaded in Warehouse : " + truck.getDestinationWareHouse().getWarehouseId());
			
		}
		JLabel background = new JLabel("");
		background.setBounds(79, 178, 56, 16);
		panel.add(background);
		background.setIcon(new ImageIcon(getClass().getResource("/blue.jpg")) );
		background.setBounds(0, 0, 1057, 648);
		
		
		
		
		
		
		
		
		
		setLocationRelativeTo(null);
		setResizable(false);
		
		this.setIconImage(new ImageIcon(getClass().getResource("/delivery2-512.png")).getImage());
		
	}
	public Driver getDriver() {
		return driver;
	}
	public Truck getTruck() {
		return truck;
	}
}
