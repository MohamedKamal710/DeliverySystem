package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import Model.Coordinator;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class AllCoordinators extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllCoordinators frame = new AllCoordinators();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	  * this frame is built to show all the coordinators in the system,
	 * it shows them in a table with a refresh button ,
	 */
	public AllCoordinators() {
		setBounds(100, 100, 917, 795);
		getContentPane().setLayout(null);
		
		JLabel lblAllCoordinatorsIn = new JLabel("All Coordinators in the system :");
		lblAllCoordinatorsIn.setForeground(Color.BLUE);
		lblAllCoordinatorsIn.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		lblAllCoordinatorsIn.setBounds(33, 55, 480, 30);
		getContentPane().add(lblAllCoordinatorsIn);
		
		JButton btnRefresh = new JButton("Refresh");
	
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefresh.setBounds(397, 60, 97, 25);
		getContentPane().add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 133, 826, 337);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID number ", "Firstname", "Lastname"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnGetPassword = new JButton("Get Password");
		btnGetPassword.setForeground(Color.WHITE);
		btnGetPassword.setBackground(Color.RED);
	
		btnGetPassword.setFont(new Font("Sitka Text", Font.BOLD, 15));
		btnGetPassword.setBounds(33, 492, 156, 37);
		getContentPane().add(btnGetPassword);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Object newData[] = new Object[3];
        btnGetPassword.setEnabled(false);

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
                btnGetPassword.setEnabled(false);
				for(Map.Entry<Long, Coordinator> temp : SysData.getInstance().getAllCoordinators().entrySet()) {
					newData[0] = temp.getValue().getId();
					newData[1] = temp.getValue().getFirstName();
					newData[2] = temp.getValue().getSurname();
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
			public void actionPerformed(ActionEvent e) {
				String showPassword = null;
				Coordinator coordinator = new Coordinator((Long)table.getValueAt(table.getSelectedRow(), 0),(String)table.getValueAt(table.getSelectedRow(),1),(String)table.getValueAt(table.getSelectedRow(), 2));
				for(Map.Entry<Long, Coordinator> coor: SysData.getInstance().getAllCoordinators().entrySet()) {
					if(coordinator.getId() == coor.getValue().getId())
						showPassword = coor.getValue().getpassword();
				}
				JOptionPane.showMessageDialog(null, coordinator.getFirstName() + " " + coordinator.getSurname() + "\n " +"Password : " + showPassword,"Password" , 0,new ImageIcon(getClass().getResource("/lock.png")));

				
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
