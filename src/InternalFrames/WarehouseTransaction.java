package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import Conotroller.SysData;
import Model.Parcel;
import Model.Truck;
import Model.WareHouse;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class WarehouseTransaction extends JInternalFrame {
	private JTable table;
	WareHouse wh = null;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseTransaction frame = new WarehouseTransaction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame is built to show the transaction number and the truck that was in this warehouse and has unloaded ,
	 * for every warehouse that is not empty , has a table to show the transaction!
	 */
	public WarehouseTransaction() {
		setBounds(100, 100, 912, 711);
		getContentPane().setLayout(null);
		
		JLabel lblWarehouse = new JLabel("Warehouse :");
		lblWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWarehouse.setBounds(45, 99, 129, 33);
		getContentPane().add(lblWarehouse);
		
		JLabel lblShowTransactionFor = new JLabel("Show Transaction for each Warehouse :");
		lblShowTransactionFor.setForeground(Color.BLUE);
		lblShowTransactionFor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblShowTransactionFor.setBounds(45, 23, 581, 38);
		getContentPane().add(lblShowTransactionFor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(186, 103, 121, 30);
		getContentPane().add(comboBox);
		TreeSet<Integer> parcelId= new TreeSet<Integer>();
		for(WareHouse temp : SysData.getInstance().WareHouses()) {
			if(!temp.getTransactionsHashMap().isEmpty() && temp.getWarehouseId()!=1)
				parcelId.add(temp.getWarehouseId());
		}
		comboBox.setModel(new DefaultComboBoxModel<>(parcelId.toArray(new Integer[parcelId.size()])));
		comboBox.insertItemAt("", 0);
		comboBox.setSelectedIndex(0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 237, 323, 425);
		getContentPane().add(scrollPane);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setVisible(false);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TransactionNumber", "Truck VIN"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(Color.white);
		scrollPane_1.setBounds(547, 237, 204, 425);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		scrollPane_1.setVisible(false);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"parcelID"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DefaultTableModel model1 = (DefaultTableModel)table_1.getModel();

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(false);
				if(comboBox.getSelectedIndex() != 0) {
					 wh = SysData.getInstance().getWareHouseById((int)comboBox.getSelectedItem());
					 Object row[] = new Object[2];
					 ((DefaultTableModel)table.getModel()).setRowCount(0);
					 for(Map.Entry<Integer, HashMap<Truck,ArrayList<Parcel>>> tran : wh.getTransactionsHashMap().entrySet()) {
						 HashMap<Truck,ArrayList<Parcel>> sample = (HashMap<Truck,ArrayList<Parcel>>)tran.getValue();
						 row[0] = tran.getKey();
						 for(Map.Entry<Truck, ArrayList<Parcel>> loadedTruck : sample.entrySet()) {
							 row[1] = loadedTruck.getKey().getVin();
						 }
						 
							((DefaultTableModel)table.getModel()).addRow(row);
					 }
					 scrollPane.setVisible(true);
				}
			}
			});
		
//		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//			 public void valueChanged(ListSelectionEvent event) {
//				 scrollPane_1.setVisible(false);
//				 if(table.getSelectedRow() >=0) {
//					 Object rowData[] = new Object[1];
//					 Truck truck = SysData.getInstance().getTruckByVin((String)table.getValueAt(table.getSelectedRow(), 1));
//					 Integer transactionID =(int) table.getValueAt(table.getSelectedRow(), 0);
//					 if(truck != null && wh != null) {
//						 ArrayList<Parcel> showParcels = new ArrayList<Parcel>();
//						 HashMap<Truck,ArrayList<Parcel>> toCheck = new HashMap<Truck,ArrayList<Parcel>>();
//						 toCheck = wh.getTransactionsHashMap().get(transactionID);
//						 showParcels = toCheck.get(truck);
//						 					System.out.println(showParcels);
////						 model1.setRowCount(0);
//						 ((DefaultTableModel)table_1.getModel()).setRowCount(0);
//						 for(Parcel temp1 : showParcels) {
//							 rowData[0] = temp1.getParcelId();
//							((DefaultTableModel)table_1.getModel()).addRow(rowData);
//							 
//						 }
//						 scrollPane_1.setVisible(true);
//
//					 }
//				 }
//			 }
//		});
		JLabel label1 = new JLabel("");
		label1.setBounds(0, 0, 842, 899);
		getContentPane().add(label1);
		label1.setBounds(0, 0, 1506, 994);
		label1.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
	}
}
