package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Conotroller.SysData;
import InternalFrames.AddWarehouse.JTextFieldLimit;
import Model.LargeParcel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SmartBuy extends JInternalFrame {
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SmartBuy frame = new SmartBuy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * sysdata method that has been used in this frame , it shows the table once the admin enters a minimum price , otherwise it gives error
	 */
	public SmartBuy() {
		setBounds(100, 100, 917, 795);
		getContentPane().setLayout(null);
		
		JLabel lblSmartbuy = new JLabel("SmartBuy :");
		lblSmartbuy.setForeground(Color.BLUE);
		lblSmartbuy.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 27));
		lblSmartbuy.setBounds(68, 58, 206, 36);
		getContentPane().add(lblSmartbuy);
		
		JLabel lblMinimumPrice = new JLabel("Minimum Price :");
		lblMinimumPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMinimumPrice.setBounds(68, 139, 177, 36);
		getContentPane().add(lblMinimumPrice);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(257, 145, 103, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setDocument(new JTextFieldLimit(6));
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Rockwell Nova Light", Font.BOLD, 16));
		btnNewButton.setBounds(388, 142, 117, 36);
		getContentPane().add(btnNewButton);
		
		JLabel onlyNumbers = new JLabel("Only Numbers");
		onlyNumbers.setForeground(Color.RED);
		onlyNumbers.setBounds(267, 177, 91, 21);
		getContentPane().add(onlyNumbers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 238, 780, 372);
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
		
		JLabel noneLabel = new JLabel("There are no large parcels that match your request.");
		noneLabel.setForeground(Color.RED);
		noneLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		noneLabel.setBounds(68, 211, 384, 21);
		getContentPane().add(noneLabel);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		onlyNumbers.setVisible(false);
		scrollPane.setVisible(false);
		noneLabel.setVisible(false);
		Object newData[] = new Object[4];
		Border border = BorderFactory.createLineBorder(Color.RED ,1 );
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onlyNumbers.setVisible(false);
				noneLabel.setVisible(false);
				scrollPane.setVisible(false);
				textField.setBorder(new JTextField().getBorder());
				if(textField.getText().isEmpty()) {
					textField.setBorder(border);
				}
				else if(!textField.getText().matches("[0-9.]*")) {
					onlyNumbers.setVisible(true);
					textField.setBorder(border);
				}
				else if(SysData.getInstance().smartBuy(Double.parseDouble(textField.getText())) == null) {
					noneLabel.setVisible(true);
				}
				else {
					ArrayList<LargeParcel> smart = SysData.getInstance().smartBuy(Double.parseDouble(textField.getText()));
					model.setRowCount(0);
					for(LargeParcel large : smart ) {
						newData[0] = large.getParcelId();
						newData[1] = large.getWeight();
						newData[2] = large.getCurrentCost();
						newData[3] = large.getReceiver().getFirstName() + " " + large.getReceiver().getSurname();
						((DefaultTableModel)table.getModel()).addRow(newData);
					}
					scrollPane.setVisible(true);
				}
				
			}
		});
		this.getRootPane().setDefaultButton(btnNewButton);
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 899);
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
		
	}
	public class JTextFieldLimit extends PlainDocument {
		  private int limit;

		  JTextFieldLimit(int limit) {
		   super();
		   this.limit = limit;
		   }

		  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		  }
}
