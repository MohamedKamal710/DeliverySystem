package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Truck;
import Model.Vehicle;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class AllTrucks extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllTrucks frame = new AllTrucks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	  * this frame is built to show all the trucks in the system, with n option of showing the heaviest truck (truck that weight the most),
	 * it shows them in a table with a refresh button ,
	 */
	public AllTrucks() {
		setBounds(100, 100, 1027, 712);
		getContentPane().setLayout(null);
		
		JLabel lblAllTrucksIn = new JLabel("All Trucks in the System :");
		lblAllTrucksIn.setForeground(Color.BLUE);
		lblAllTrucksIn.setFont(new Font("Verdana Pro Cond Light", Font.BOLD, 26));
		lblAllTrucksIn.setBounds(51, 62, 362, 33);
		getContentPane().add(lblAllTrucksIn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 126, 825, 355);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"VIN ", "Color", "ModelType", "TypeTruck", "Driver"
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Object newData[] = new Object[5];
		for(Vehicle truck : SysData.getInstance().getVehicles()) {
			if(truck instanceof Truck) {
				newData[0] = truck.getVin();
				newData[1] = truck.getColor();
				newData[2] = truck.getType();
				newData[3] = ((Truck)truck).getTypeTrunk();
				if(!truck.isInUse())
					newData[4] = "None";
				else
					newData[4] = truck.getDriver().getFirstName() + " " + truck.getDriver().getSurname();
				((DefaultTableModel)table.getModel()).addRow(newData);
			}
		}
		
		JLabel lblThereAreNone = new JLabel("There are none !");
		lblThereAreNone.setForeground(Color.RED);
		lblThereAreNone.setFont(new Font("Segoe UI Historic", Font.BOLD, 18));
		lblThereAreNone.setBounds(51, 579, 265, 23);
		getContentPane().add(lblThereAreNone);
		lblThereAreNone.setVisible(false);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblThereAreNone.setVisible(false);
				model.setRowCount(0);
				for(Vehicle truck : SysData.getInstance().getVehicles()) {
					if(truck instanceof Truck) {
						newData[0] = truck.getVin();
						newData[1] = truck.getColor();
						newData[2] = truck.getType();
						newData[3] = ((Truck)truck).getTypeTrunk();
						if(!truck.isInUse())
							newData[4] = "None";
						else
							newData[4] = truck.getDriver().getFirstName() + " " + truck.getDriver().getSurname();
						((DefaultTableModel)table.getModel()).addRow(newData);
					}
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRefresh.setBounds(361, 67, 110, 33);
		getContentPane().add(btnRefresh);
		
		JButton btnNewButton = new JButton("Get Truck with Max Weight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Truck truck;
				lblThereAreNone.setVisible(false);
			truck = SysData.getInstance().getTruckWithMaxWeight();
			if(truck == null || truck.getParcels().isEmpty()) {
				lblThereAreNone.setVisible(true);
			}
			else if(truck!= null) {
				JOptionPane.showMessageDialog(null, "Truck ID : "+ truck.getVin() +"\n Truck weight : " + truck.getWeight()  ,"Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));

			}
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(51, 507, 296, 59);
		getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 899);
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		
		
		
		
		
	}
}
