package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Parcel;
import Model.Truck;
import Model.Vehicle;
import Model.WareHouse;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AddRoute extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JButton refresh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoute frame = new AddRoute();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame built to add a route to a truck , 
	 * first table shows only trucks that are in use ,
	 * second table shows only warehouses that has parcels ,
	 * 3rd table shows  all the warehouses that a truck can unload in ,
	 */
	public AddRoute() {
		setBounds(100, 100, 1506, 994);
		getContentPane().setLayout(null);
		
		JLabel lblAddRoute = new JLabel("Add  Route to Truck :");
		lblAddRoute.setForeground(new Color(0, 0, 255));
		lblAddRoute.setFont(new Font("Verdana Pro SemiBold", Font.PLAIN, 33));
		lblAddRoute.setBounds(53, 56, 491, 77);
		getContentPane().add(lblAddRoute);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 188, 253, 392);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TruckID"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Object DataTo[] = new Object[1];
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		
		for(Vehicle temp : SysData.getInstance().getVehicles()) {
			if(temp instanceof Truck && temp.getParcels().isEmpty() && temp.getDriver() != null && temp.isInUse()) {
				DataTo[0] = temp.getVin();
				model.addRow(DataTo);
			}
		}
		JLabel lblChooseATruck = new JLabel("Choose a truck :");
		lblChooseATruck.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblChooseATruck.setBounds(53, 163, 185, 24);
		getContentPane().add(lblChooseATruck);
		
		JPanel panel = new JPanel();
		panel.setBounds(560, 141, 763, 511);
		getContentPane().add(panel);
		panel.setLayout(null);
        panel.setVisible(false);
		
        JButton addRoute = new JButton("Add Route");
		addRoute.setBackground(new Color(0, 0, 255));
		addRoute.setForeground(new Color(255, 255, 255));
		addRoute.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		addRoute.setBounds(856, 695, 204, 58);
		getContentPane().add(addRoute);
		addRoute.setVisible(false);
		addRoute.setEnabled(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 53, 266, 391);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Warehouse"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model_1 = (DefaultTableModel)table_1.getModel();
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 if(table_1.getSelectedRow()>=0)
				 SwingUtilities.invokeLater( 
					        new Runnable() {
					            public void run() {
					            	addRoute.setEnabled(false); 
					            	if(table_1.getSelectedRow() >=0 && table_2.getSelectedRow()>= 0)
					            		addRoute.setEnabled(true);
					            }
					        }
					    );
				 }
			});

		JLabel lblFrom = new JLabel("From :");
		lblFrom.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblFrom.setBounds(33, 23, 75, 27);
		panel.add(lblFrom);
		
		JLabel label = new JLabel("----------->");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(335, 225, 92, 36);
		panel.add(label);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(474, 53, 266, 391);
		panel.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Warehouse"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model_2 = (DefaultTableModel)table_2.getModel();
		
		table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 if(table_2.getSelectedRow()>=0)
				 SwingUtilities.invokeLater( 
					        new Runnable() {
					            public void run() {
					            	addRoute.setEnabled(false);
					            	if(table_2.getSelectedRow() >=0 && table_1.getSelectedRow()>= 0)
					            		addRoute.setEnabled(true);
					            }
					        }
					    );
				 }
			});
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTo.setBounds(474, 26, 64, 21);
		panel.add(lblTo);
		
		JLabel sameWarehouse = new JLabel("You can't choose same Warehouse !");
		sameWarehouse.setForeground(Color.RED);
		sameWarehouse.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		sameWarehouse.setBounds(797, 757, 330, 24);
		getContentPane().add(sameWarehouse);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(50, 205, 50));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(645, 753, 658, 29);
		getContentPane().add(lblNewLabel);
		
		refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object newData[] = new Object[2];
				model.setRowCount(0);
				for(Map.Entry<String, Vehicle > temp : SysData.getInstance().getVehclesMap().entrySet()) {
					if(temp.getValue() instanceof Truck && temp.getValue().isInUse() && temp.getValue().getDriver() != null ) {
						Truck tr = (Truck) temp.getValue();
						if(tr.getDestinationWareHouse() == null) {
						newData[0] = temp.getValue().getVin() ;
						newData[1] = "DriverID: " +temp.getValue().getDriver().getId();
						model.addRow(newData);
						}
					}
				}
				
				Object rowData_1[] = new Object[1];
				Object rowData_2[] = new Object[1];
				model_1.setRowCount(0);
				
				for(WareHouse wh: SysData.getInstance().WareHouses()) {
					if(!wh.getParcels().isEmpty()) {
						rowData_1[0] = wh.getWarehouseId();
						model_1.addRow(rowData_1);
					}
				}
				
				model_2.setRowCount(0);
				for(WareHouse wh: SysData.getInstance().WareHouses()) {
					rowData_2[0] = wh.getWarehouseId();
					model_2.addRow(rowData_2);
				}
			}
		});
		refresh.setBounds(430, 91, 97, 25);
		getContentPane().add(refresh);
		refresh.setVisible(false);
		lblNewLabel.setVisible(false);
		sameWarehouse.setVisible(false);
		
		
		
		

		Object newData[] = new Object[2];
		model.setRowCount(0);
		for(Map.Entry<String, Vehicle > temp : SysData.getInstance().getVehclesMap().entrySet()) {
			if(temp.getValue() instanceof Truck && temp.getValue().isInUse() && temp.getValue().getDriver() != null) {
				newData[0] = temp.getValue().getVin() ;
				newData[1] = "DriverID: " +temp.getValue().getDriver().getId();
				model.addRow(newData);
			}
		}
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 panel.setVisible(false);
	                addRoute.setVisible(false);
				 if(table.getSelectedRow()>=0)
				 SwingUtilities.invokeLater( 
					        new Runnable() {
					            public void run() {
					                panel.setVisible(true);
					                addRoute.setVisible(true);
					            }
					        }
					    );
				 }
			});
		
		Object rowData_1[] = new Object[1];
		Object rowData_2[] = new Object[1];
		model_1.setRowCount(0);
		
		for(WareHouse wh: SysData.getInstance().WareHouses()) {
			if(!wh.getParcels().isEmpty()) {
				rowData_1[0] = wh.getWarehouseId();
				model_1.addRow(rowData_1);
			}
		}
		
		model_2.setRowCount(0);
		for(WareHouse wh: SysData.getInstance().WareHouses()) {
			rowData_2[0] = wh.getWarehouseId();
			model_2.addRow(rowData_2);
		}
		
		addRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sameWarehouse.setVisible(false);
				lblNewLabel.setVisible(false);
				lblNewLabel.setText("");
				ArrayList<Parcel> array = new ArrayList<Parcel>();
				Truck tr1 = (Truck)SysData.getInstance().getVehclesMap().get(table.getValueAt(table.getSelectedRow(), 0));
				if(table_1.getSelectedRow() < 0 && table_2.getSelectedRow() < 0) {
					sameWarehouse.setVisible(true);
					sameWarehouse.setText("You should select a warehouse first");
				}
				else if(table_2.getValueAt(table_2.getSelectedRow(), 0) == table_1.getValueAt(table_1.getSelectedRow(), 0)) {
					sameWarehouse.setVisible(true);
					sameWarehouse.setText("You can't choose same Warehouse !");
				}
				else if(tr1.getDestinationWareHouse() ==null) {
					array = (ArrayList<Parcel>)(SysData.getInstance().addAllPossibleParcelsToTruck((String)tr1.getVin(), (int)table_1.getValueAt(table_1.getSelectedRow(), 0), (int)table_2.getValueAt(table_2.getSelectedRow(), 0)));
					lblNewLabel.setVisible(true);
					lblNewLabel.setText("Route for TruckID : " + table.getValueAt(table.getSelectedRow(), 0) + " has been added successfuly !");
					refresh.doClick();
				}
			}
		});
		
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane_1.getViewport().setBackground(Color.WHITE);
		scrollPane_2.getViewport().setBackground(Color.WHITE);
		JLabel label1 = new JLabel("");
		label1.setBounds(0, 0, 842, 899);
		getContentPane().add(label1);
		label1.setBounds(0, 0, 1506, 994);
		label1.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		
	}
	
	public JButton getRefresh() {
		return refresh;
	}
}
