package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Conotroller.SysData;
import Model.Driver;
import Model.Receiver;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class AllReceivers extends JInternalFrame {
	private JTable table;
	public static DateFormat df;
	public static DateFormat dtf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllReceivers frame = new AllReceivers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	  * this frame is built to show all the receivers in the system, with the option of gettin the password and the receiver with most received items
	 * it shows them in a table with a refresh button ,
	 */
	public AllReceivers() {
		setBounds(100, 100, 1027, 712);
		getContentPane().setLayout(null);
		
		JLabel lblShowAllReceivers = new JLabel("Show all Receivers that are registered in the System :");
		lblShowAllReceivers.setBackground(Color.WHITE);
		lblShowAllReceivers.setForeground(Color.BLUE);
		lblShowAllReceivers.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblShowAllReceivers.setBounds(90, 65, 643, 41);
		getContentPane().add(lblShowAllReceivers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 153, 856, 383);
		getContentPane().add(scrollPane);
		df = new SimpleDateFormat("dd/MM/yyyy");
		dtf = new SimpleDateFormat("dd/MM/yyyy");
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"receiverID", "Firstname", "Lastname", "Email", "Birthdate", "Address"
			}
		)
		{public boolean isCellEditable(int row, int column){return false;}}
		);
		
		JButton btnRefresh = new JButton("Refresh");

		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefresh.setBounds(708, 78, 97, 25);
		
		getContentPane().add(btnRefresh);
		JLabel emptyListLabel = new JLabel("There are no receiver in the system !");
		emptyListLabel.setForeground(Color.RED);
		emptyListLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emptyListLabel.setBounds(90, 599, 270, 25);
		getContentPane().add(emptyListLabel);
		emptyListLabel.setVisible(false);
		JButton btnGetReceiverWith = new JButton("Get Receiver with max Items ");
		btnGetReceiverWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyListLabel.setVisible(false);
				Receiver receiver = SysData.getInstance().getPersonWithMaxReceivedItems();
				if(SysData.getInstance().getReceiversMap().isEmpty()) {
					emptyListLabel.setVisible(true);
					emptyListLabel.setText("There are no receivers in the system !");
				}
				else if(receiver == null) {
					emptyListLabel.setVisible(true);
					emptyListLabel.setText("All Receivers have 0 items !");
				}
				else {
					JOptionPane.showMessageDialog(null, "Receiver ID : "+receiver.getId() +"\n Receiver name : " + receiver.getFirstName() + " " + receiver.getSurname()  ,"Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
				}
				
			}
		});
		btnGetReceiverWith.setBackground(Color.BLUE);
		btnGetReceiverWith.setForeground(Color.WHITE);
		btnGetReceiverWith.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGetReceiverWith.setBounds(90, 549, 243, 41);
		getContentPane().add(btnGetReceiverWith);
		
		JButton btnGetPassword = new JButton("Get Password");
		btnGetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String showPassword = null;
				Receiver receiver = null;
				Long wantedID = (Long)table.getValueAt(table.getSelectedRow(), 0);
				for(Map.Entry<Long, Receiver> temp : SysData.getInstance().getReceiversMap().entrySet()) {
					if(temp.getValue().getId() == wantedID) {
						showPassword = temp.getValue().getpassword();
						receiver = temp.getValue();
					}
				}
				JOptionPane.showMessageDialog(null, receiver.getFirstName() + " " + receiver.getSurname() + " : " + showPassword,"Password" , 0,new ImageIcon(getClass().getResource("/lock.png")));

			}
		});
		btnGetPassword.setEnabled(false);
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
		btnGetPassword.setBackground(Color.RED);
		btnGetPassword.setForeground(Color.WHITE);
		btnGetPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGetPassword.setBounds(361, 549, 165, 41);
		getContentPane().add(btnGetPassword);
		
	
		Object rowData[]=new Object[6];
		for(Map.Entry<Long, Receiver> rec : SysData.getInstance().getReceiversMap().entrySet()) {
			String strDate = df.format(rec.getValue().getBirthDate());
			rowData[0] = rec.getValue().getId();
			rowData[1] = rec.getValue().getFirstName();
			rowData[2] = rec.getValue().getSurname();
			rowData[3] = rec.getValue().getEmail();
			rowData[4] = strDate;
			rowData[5]=rec.getValue().getAddress().getCity() +","+rec.getValue().getAddress().getStreet()+","+rec.getValue().getAddress().getHouseNumber()+","+rec.getValue().getAddress().getZipCode();
			((DefaultTableModel)table.getModel()).addRow(rowData);
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				emptyListLabel.setVisible(false);
				btnGetPassword.setEnabled(false);
				for(Map.Entry<Long, Receiver> rec : SysData.getInstance().getReceiversMap().entrySet()) {
					String strDate = df.format(rec.getValue().getBirthDate());
					rowData[0] = rec.getValue().getId();
					rowData[1] = rec.getValue().getFirstName();
					rowData[2] = rec.getValue().getSurname();
					rowData[3] = rec.getValue().getEmail();
					rowData[4] = strDate;
					rowData[5]=rec.getValue().getAddress().getCity() +","+rec.getValue().getAddress().getStreet()+","+rec.getValue().getAddress().getHouseNumber()+","+rec.getValue().getAddress().getZipCode();
					((DefaultTableModel)table.getModel()).addRow(rowData);
			}
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
