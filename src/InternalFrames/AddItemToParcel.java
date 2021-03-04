package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Item;
import Model.LargeParcel;
import Model.Parcel;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class AddItemToParcel extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JButton btnRefresh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemToParcel frame = new AddItemToParcel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame is built to add an item to a parcel with the an option of removing it ,
	 */
	public AddItemToParcel() {
		setBounds(100, 100, 1400, 1080);
		getContentPane().setLayout(null);
		
		JLabel lblAddItemTo = new JLabel("Add Item to Parcel :");
		lblAddItemTo.setForeground(Color.BLUE);
		lblAddItemTo.setFont(new Font("Verdana Pro Light", Font.BOLD, 25));
		lblAddItemTo.setBounds(61, 72, 284, 37);
		getContentPane().add(lblAddItemTo);
		
		JLabel lblChooseAParcel = new JLabel("Choose  a parcel you would like to add an item to :");
		lblChooseAParcel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChooseAParcel.setBounds(61, 191, 430, 21);
		getContentPane().add(lblChooseAParcel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 225, 698, 166);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"parcelID", "Type", "Weight", "Price", "Receiver"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setVisible(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblChooseAnItem = new JLabel("Choose an item to add to the Parcel :");
		lblChooseAnItem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChooseAnItem.setBounds(61, 445, 315, 21);
		getContentPane().add(lblChooseAnItem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(61, 479, 698, 307);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CatalogID", "itemName", "Price", "FreeShipping", "Weight"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		DefaultTableModel model_1 = (DefaultTableModel)table_1.getModel();
		JButton btnNewButton = new JButton("Add item to parcel");
		Object rowData[] = new Object[5];
		
		 btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton.setEnabled(false);
				model.setRowCount(0);
				model_1.setRowCount(0);
				for(Parcel temp: SysData.getInstance().allParcels()) {
					if(!temp.isSentToReceiver()) {
					rowData[0] = temp.getParcelId();
					if(temp instanceof LargeParcel)
						rowData[1] = "Large";
					else
						rowData[1] = "Small";
					rowData[2] = temp.getWeight();
					rowData[3] = temp.getCurrentCost();
					rowData[4] = temp.getReceiver().getFirstName() + " "+ temp.getReceiver().getSurname();
					((DefaultTableModel)table.getModel()).addRow(rowData);
					}
				}
				
				for(Item item : SysData.getInstance().allItems()) {
					rowData[0] =item.getCatalogID();
					rowData[1] = item.getItemName();
					rowData[2] = item.getPrice();
					if(item.isHasFreeShipping())
						rowData[3] = "Yes";
					else
						rowData[3] = "No";
					rowData[4] = item.getItemWeight();
					((DefaultTableModel)table_1.getModel()).addRow(rowData);
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefresh.setBounds(455, 191, 97, 25);
		getContentPane().add(btnRefresh);
		
		
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(262, 846, 229, 46);
		getContentPane().add(btnNewButton);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(94);
		btnNewButton.setEnabled(false);
		
		JLabel alreadyInLabel = new JLabel("Item is already in the parcel Or not enough space !");
		alreadyInLabel.setForeground(Color.RED);
		alreadyInLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		alreadyInLabel.setBounds(163, 905, 430, 25);
		getContentPane().add(alreadyInLabel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(967, 225, 284, 336);
		getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setBackground(Color.WHITE);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane_2.setViewportView(table_2);
		scrollPane_2.setVisible(false);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items in parcel"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnDeleteItem = new JButton("Delete Item");
	
		btnDeleteItem.setBackground(Color.RED);
		btnDeleteItem.setForeground(Color.WHITE);
		btnDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteItem.setBounds(1038, 574, 151, 37);
		getContentPane().add(btnDeleteItem);
		btnDeleteItem.setVisible(false);
		JLabel doneLabel = new JLabel("Item has been successfuly added to the parcel .");
		alreadyInLabel.setVisible(false);
		DefaultTableModel model_2 = (DefaultTableModel)table_2.getModel();
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doneLabel.setVisible(false);
				 Object newData2[]= new Object[1];
				 
				Parcel p = SysData.getInstance().getParcelById((String) table.getValueAt(table.getSelectedRow(), 0));
				if(!p.getListOfItem().isEmpty())
				for(Item item : p.getListOfItem()) {
					if(table_2.getValueAt(table_2.getSelectedRow(), 0) == item.getItemName()) {
						p.removeItem(item);
						break;
				}
			}
				
				if(p.getListOfItem().isEmpty()) {
					btnDeleteItem.setVisible(false);
				 scrollPane_2.setVisible(false);
				}
				model_2.setRowCount(0);
				for(Item item : p.getListOfItem()) {
					newData2[0] = item.getItemName();
					((DefaultTableModel)table_2.getModel()).addRow(newData2);
				}
				if(p.getListOfItem().isEmpty())
					table.setValueAt(0, table.getSelectedRow(), 2);
				else
					table.setValueAt(p.getWeight(), table.getSelectedRow(), 2);
				table.setValueAt(p.getCurrentCost(),table.getSelectedRow(), 3);
					
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		 public void valueChanged(ListSelectionEvent event) {
			 scrollPane_2.setVisible(false);
				btnDeleteItem.setVisible(false);
			 if(table.getSelectedRow()>=0 &&table_1.getSelectedRow()>=0)
			 SwingUtilities.invokeLater( 
				        new Runnable() {
				            public void run() {
				                btnNewButton.setEnabled(true);
				            }
				        }
				    );
			 if(table.getSelectedRow()>=0) {
				 model_2.setRowCount(0);
				 Object rowData1[]= new Object[1];
				 for(Parcel parcel : SysData.getInstance().allParcels()) {
					 if(parcel.getParcelId().equals(table.getValueAt(table.getSelectedRow(), 0)))
						 if(!parcel.getListOfItem().isEmpty()) {
							scrollPane_2.setVisible(true);
							btnDeleteItem.setVisible(true);
							for(Item item : parcel.getListOfItem()) {
								rowData1[0] = item.getItemName();
								((DefaultTableModel)table_2.getModel()).addRow(rowData1);
							}

					 }
				 }
			 }
			 }
		});

		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		btnDeleteItem.setEnabled(false);
		
		doneLabel.setForeground(new Color(50, 205, 50));
		doneLabel.setFont(new Font("Verdana Pro Cond SemiBold", Font.PLAIN, 23));
		doneLabel.setBounds(131, 896, 532, 37);
		getContentPane().add(doneLabel);
		doneLabel.setVisible(false);
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 if(table_1.getSelectedRow()>=0 && table.getSelectedRow()>=0)
					 SwingUtilities.invokeLater( 
						        new Runnable() {
						            public void run() {
						                btnNewButton.setEnabled(true);
						            }
						        }
						    );
			
			 }
			 
			});
		table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 if(table_2.getSelectedRow()>=0)
					 SwingUtilities.invokeLater( 
						        new Runnable() {
						            public void run() {
						                btnDeleteItem.setEnabled(true);
						            }
						        }
						    );
			
			 }
			 
			});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alreadyInLabel.setVisible(false);
				doneLabel.setVisible(false);
				if(SysData.getInstance().addItemToParcel((String)table.getValueAt(table.getSelectedRow(), 0),(Long)table_1.getValueAt(table_1.getSelectedRow(), 0),(String) table_1.getValueAt(table_1.getSelectedRow(), 1))) { 
					doneLabel.setVisible(true);
					model_2.setRowCount(0);
					Object newData1[] = new Object[1];
 					scrollPane_2.setVisible(true);
 					btnDeleteItem.setVisible(true);
					 for(Parcel parcel : SysData.getInstance().allParcels()) {
						 if(parcel.getParcelId().equals((String)table.getValueAt(table.getSelectedRow(), 0)))
					for(Item item : parcel.getListOfItem()) {
						newData1[0] = item.getItemName();
						((DefaultTableModel)table_2.getModel()).addRow(newData1);
						table.setValueAt(parcel.getWeight(), table.getSelectedRow(), 2);
						table.setValueAt(parcel.getCurrentCost(), table.getSelectedRow(), 3);
					}
				}
				}
				else {
					alreadyInLabel.setVisible(true);				
					}
				}
		});
		scrollPane_2.getViewport().setBackground(Color.WHITE);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 899);
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
	}
	
	public JButton refresh() {
		return btnRefresh;
	}
}
