package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Driver;

import javax.swing.JScrollPane;

public class AllDrivers extends JInternalFrame {
	private JTable table;
	private static DateFormat df;
	private static DateFormat dtf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllDrivers frame = new AllDrivers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame is built to show all the drivers in the system, whether the driver has a valid license or not ,
	 * it shows them in a table with a refresh button ,
	 */
	public AllDrivers() {
		setBounds(100, 100, 968, 795);
		getContentPane().setLayout(null);
		
		JLabel lblListOfAll = new JLabel("List of all Drivers in the System :");
		lblListOfAll.setForeground(Color.BLUE);
		lblListOfAll.setFont(new Font("Arial", Font.BOLD, 25));
		lblListOfAll.setBounds(65, 54, 439, 39);
		getContentPane().add(lblListOfAll);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(457, 59, 104, 31);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 127, 861, 379);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Driver ID", "Firstname", "Lastname", "Birthdate", "Address", "License", "InUse"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		df = new SimpleDateFormat("dd/MM/yyyy");
		dtf = new SimpleDateFormat("dd/MM/yyyy");
		Object newData[] = new Object[7];
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnGetPassword = new JButton("Get Password");
		btnGetPassword.setForeground(Color.WHITE);
		btnGetPassword.setBackground(Color.RED);
		
		btnGetPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGetPassword.setBounds(75, 523, 169, 39);
		getContentPane().add(btnGetPassword);
		btnGetPassword.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				for(Map.Entry<Long, Driver > driver : SysData.getInstance().getAllDriversMap().entrySet()) {
					newData[0] = driver.getValue().getId();
					newData[1] = driver.getValue().getFirstName();				
					newData[2] = driver.getValue().getSurname();		
					String strDate = df.format(driver.getValue().getBirthDate());
					newData[3] = strDate;
					newData[4]=driver.getValue().getAddress().getCity() +","+driver.getValue().getAddress().getStreet()+","+driver.getValue().getAddress().getHouseNumber()+","+driver.getValue().getAddress().getZipCode();
					if(driver.getValue().getHasValidLicense())
						newData[5] = "Valid";
					else
						newData[5] = "Not Valid";
					if(driver.getValue().isDriverInUse())
						newData[6] = "Yes";
					else
						newData[6] = "No";
					((DefaultTableModel)table.getModel()).addRow(newData);
				}
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent event) {
				 if(table.getSelectedRow()>=0)
				 SwingUtilities.invokeLater( 
					        new Runnable() {
					            public void run() {
					                btnGetPassword.setEnabled(true);
					            }
					        }
					    );
				 }
			});
		btnGetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String showPassword = null;
				Driver driver = null;
				Long wantedID = (Long)table.getValueAt(table.getSelectedRow(), 0);
				for(Map.Entry<Long, Driver> temp : SysData.getInstance().getAllDriversMap().entrySet()) {
					if(temp.getValue().getId() == wantedID) {
						showPassword = temp.getValue().getpassword();
						driver = temp.getValue();
					}
				}
				JOptionPane.showMessageDialog(null, driver.getFirstName() + " " + driver.getSurname() + "\n " + "Password : " + showPassword,"Password" , 0,new ImageIcon(getClass().getResource("/lock.png")));

				
			}
		});
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 899);
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
	}
}
