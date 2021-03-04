package InternalFrames;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Car;
import Model.Item;
import Model.Truck;
import Model.Vehicle;
import gui.login;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Receiver_ItemList extends JInternalFrame {
	private JTable table;
	
	/***
	 * this frame is built to show all items that the receiver has bought and delivered ,
	 * this frame can only be reachable from the receiver homepage!
	 */
	public Receiver_ItemList() {
		getContentPane().setLayout(null);
		
		JLabel itemsList = new JLabel("Items Bought");
		itemsList.setForeground(new Color(153, 0, 204));
		itemsList.setFont(new Font("Yu Gothic Medium", Font.BOLD, 34));
		itemsList.setBounds(101, 38, 234, 56);
		getContentPane().add(itemsList);
		JLabel emptyListLabel = new JLabel("Your list is empty ! ");
		emptyListLabel.setForeground(Color.RED);
		emptyListLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		emptyListLabel.setBounds(54, 430, 222, 26);
		getContentPane().add(emptyListLabel);
		emptyListLabel.setVisible(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 118, 687, 299);
		getContentPane().add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false;   
			}
			};
		table.setBorder(new LineBorder(new Color(128, 128, 128), 3, true));
		table.setFont(new Font("Sylfaen", Font.BOLD, 12));
		table.setForeground(new Color(0, 0, 102));
		table.setRowHeight(30);	
		table.setBackground(new Color(153, 204, 204));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CatalogID", "Item Name", "Price", "Free Shipped", "itemWeight"
			}
		));
		
		if(login.idUser != null)
		if(!SysData.getInstance().getReceiverItems(SysData.getInstance().getReceiversMap().get(login.idUser).getId()).isEmpty()) {
			((DefaultTableModel) table.getModel()).setRowCount(0);
			Car car = null ;
			Truck tt = null ; 
		for(Item i :SysData.getInstance().getReceiverItems( SysData.getInstance().getReceiversMap().get(login.idUser).getId())) {
			Object[] Row = new Object[5];
			
	     	Row[0] = i.getCatalogID();
	     	Row[1]= i.getItemName() ;
			Row[2] = i.getPrice() ;
			Row[3]=i.isHasFreeShipping() ;
	  		Row[4]=i.getItemWeight() ;
		/**
			Row[0] = "123245" ;
			Row[1]= "red";
			Row[2]=Utils.E_ModelType.BMW ;
			Row[3]="11111111" ;
			Row[4]=true ;
			*/	
	  		
			((DefaultTableModel) table.getModel()).addRow(Row);
		
		}
			
		
		}else{
			emptyListLabel.setVisible(true);
			//dispose();
			}
		
		
		scrollPane.setViewportView(table);
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		JLabel label = new JLabel("");
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
	
	}
}