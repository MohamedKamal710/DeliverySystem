package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Parcel;
import javafx.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TwiceAtWareHouse extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwiceAtWareHouse frame = new TwiceAtWareHouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame calls the method twiceatwarehouse that is in sysdata , and shows the result in a table ,
	 */
	public TwiceAtWareHouse() {
		setBounds(100, 100, 999, 710);
		getContentPane().setLayout(null);
		
		JLabel lblTwiceAtWarehouse = new JLabel("Twice at Warehouse :");
		lblTwiceAtWarehouse.setFont(new Font("Georgia Pro Cond Light", Font.BOLD, 28));
		lblTwiceAtWarehouse.setForeground(Color.BLUE);
		lblTwiceAtWarehouse.setBounds(55, 51, 445, 63);
		getContentPane().add(lblTwiceAtWarehouse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 149, 799, 380);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"parcelID", "Weight", "Price", "Receiver"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel noneLabel = new JLabel("There are no parcels that have been twice or more in a Warehouse !");
		noneLabel.setForeground(Color.RED);
		noneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		noneLabel.setBounds(55, 121, 595, 27);
		getContentPane().add(noneLabel);
		
		Object newData[] = new Object[4];
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				noneLabel.setVisible(false);
				model.setRowCount(0);
				HashMap<Parcel,Integer> twiceParcels = SysData.getInstance().TwiceAtWarehouse();
				if(twiceParcels == null)
					noneLabel.setVisible(true);
				else
				{
					for(Map.Entry<Parcel, Integer> temp : twiceParcels.entrySet()) {
						newData[0] = temp.getKey().getParcelId();
						newData[1] = temp.getKey().getWeight();
						newData[2] = temp.getKey().getCurrentCost();
						newData[3] = temp.getKey().getReceiver().getFirstName() + " " + temp.getKey().getReceiver().getSurname();
						((DefaultTableModel)table.getModel()).addRow(newData);
					}
				}
			}
		});
		btnShow.setBackground(Color.BLUE);
		btnShow.setForeground(Color.WHITE);
		btnShow.setFont(new Font("Verdana", Font.BOLD, 17));
		btnShow.setBounds(344, 73, 103, 27);
		getContentPane().add(btnShow);
		noneLabel.setVisible(false);
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 899);
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
	}
}
