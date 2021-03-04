package InternalFrames;
import Utils.E_Cities;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Conotroller.SysData;
import Model.Address;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AddWarehouse extends JInternalFrame {
	private JTextField textField;
	private E_Cities[] list;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWarehouse frame = new AddWarehouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame is built to add a warehouse to the system , considering all the errors that can occur
	 */
	public AddWarehouse() {
		setBounds(100, 100, 1000, 1000);
		getContentPane().setLayout(null);
		list = Utils.E_Cities.values();
		JLabel lblAddWarehouseTo = new JLabel("Add Warehouse  to the system :");
		lblAddWarehouseTo.setForeground(Color.BLUE);
		lblAddWarehouseTo.setFont(new Font("Verdana Pro Cond SemiBold", Font.BOLD, 25));
		lblAddWarehouseTo.setBounds(59, 87, 368, 32);
		getContentPane().add(lblAddWarehouseTo);
		
		JLabel lblWarehouseId = new JLabel("Warehouse ID :");
		lblWarehouseId.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblWarehouseId.setBounds(59, 225, 189, 27);
		getContentPane().add(lblWarehouseId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(292, 220, 211, 40);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setDocument(new JTextFieldLimit(6));
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCity.setBounds(59, 356, 126, 27);
		getContentPane().add(lblCity);
		
		JComboBox comboBox = new JComboBox(list);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setBounds(197, 357, 126, 32);
		getContentPane().add(comboBox);
		
		JLabel lblStreet = new JLabel("Street :");
		lblStreet.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblStreet.setBounds(59, 455, 119, 32);
		getContentPane().add(lblStreet);
		
		JLabel lblHouseNumber = new JLabel("House number :");
		lblHouseNumber.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHouseNumber.setBounds(59, 557, 189, 27);
		getContentPane().add(lblHouseNumber);
		
		JLabel lblZipCode = new JLabel("ZIP Code :");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblZipCode.setBounds(59, 658, 119, 32);
		getContentPane().add(lblZipCode);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(292, 453, 211, 40);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setDocument(new JTextFieldLimit(20));
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setBounds(292, 554, 211, 40);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setDocument(new JTextFieldLimit(4));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setBounds(292, 658, 211, 40);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setDocument(new JTextFieldLimit(7));
		JLabel numbersOnlyLabel = new JLabel("Numbers Only !");
		numbersOnlyLabel.setForeground(Color.RED);
		numbersOnlyLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numbersOnlyLabel.setBounds(515, 229, 102, 25);
		getContentPane().add(numbersOnlyLabel);
		
		JLabel numbersOnlyLabel1 = new JLabel("Numbers Only !");
		numbersOnlyLabel1.setForeground(Color.RED);
		numbersOnlyLabel1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numbersOnlyLabel1.setBounds(515, 559, 102, 28);
		getContentPane().add(numbersOnlyLabel1);
		
		JLabel numbersOnlyLabel2 = new JLabel("Numbers Only !");
		numbersOnlyLabel2.setForeground(Color.RED);
		numbersOnlyLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numbersOnlyLabel2.setBounds(515, 667, 110, 20);
		getContentPane().add(numbersOnlyLabel2);
		
		JButton btnAddWarehouse = new JButton("Add Warehouse");
		btnAddWarehouse.setBackground(Color.BLUE);
		btnAddWarehouse.setForeground(Color.WHITE);
		btnAddWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAddWarehouse.setBounds(197, 784, 195, 61);
		getContentPane().add(btnAddWarehouse);
		
		JLabel zipLength = new JLabel("ZIP Code must be 7 digits !");
		zipLength.setForeground(Color.RED);
		zipLength.setFont(new Font("Tahoma", Font.PLAIN, 13));
		zipLength.setBounds(515, 668, 202, 20);
		getContentPane().add(zipLength);
		JLabel existLabel = new JLabel("Warehouse with the given ID does exist !");
		existLabel.setForeground(Color.RED);
		existLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		existLabel.setBounds(138, 858, 327, 27);
		getContentPane().add(existLabel);
		existLabel.setVisible(false);
		JLabel emptyLabel = new JLabel("There is an empty field !");
		emptyLabel.setForeground(Color.RED);
		emptyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emptyLabel.setBounds(197, 858, 217, 27);
		getContentPane().add(emptyLabel);
		emptyLabel.setVisible(false);
		zipLength.setVisible(false);
		numbersOnlyLabel2.setVisible(false);
		numbersOnlyLabel1.setVisible(false);
		numbersOnlyLabel.setVisible(false);
		existLabel.setVisible(false);
		Border border = BorderFactory.createLineBorder(Color.RED,1);
		btnAddWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setBorder(new JTextField().getBorder());
				textField_1.setBorder(new JTextField().getBorder());
				textField_2.setBorder(new JTextField().getBorder());
				textField_3.setBorder(new JTextField().getBorder());
				zipLength.setVisible(false);
				numbersOnlyLabel2.setVisible(false);
				numbersOnlyLabel1.setVisible(false);
				numbersOnlyLabel.setVisible(false);
				emptyLabel.setVisible(false);
				existLabel.setVisible(false);

				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || textField_3.getText().isEmpty()) {
					emptyLabel.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					if(textField.getText().isEmpty()) 
						textField.setBorder(border);
					
					if(textField_1.getText().isEmpty()) 
						textField_1.setBorder(border);
					
					if(textField_2.getText().isEmpty()) 
						textField_2.setBorder(border);
					
					if(textField_3.getText().isEmpty())
						textField_3.setBorder(border);
					
				}
				else if(!textField.getText().matches("[0-9]+")) {
					textField.setBorder(border);
					numbersOnlyLabel.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");

				}
				else if(!textField_2.getText().matches("[0-9]+")) {
					textField_2.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					numbersOnlyLabel1.setVisible(true);
				}
				else if(!textField_3.getText().matches("[0-9]+")) {
					textField_3.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					numbersOnlyLabel2.setVisible(true);
				}
				else if(textField_3.getText().length() !=7) {
					textField_3.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					zipLength.setVisible(true);
				}
				else {
					if(SysData.getInstance().addWarehouse(Integer.parseInt(textField.getText()),new Address((E_Cities)comboBox.getSelectedItem(),textField_1.getText(),Integer.parseInt(textField_2.getText()),textField_3.getText()))) {
						PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
						JOptionPane.showMessageDialog(null, "Warehouse was added successfuly !","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");

					}
					else {
						existLabel.setVisible(true);
					}
				}
			}
		});
		
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		this.getRootPane().setDefaultButton(btnAddWarehouse);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1506, 994);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/internalBack.png")));

		

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
	
	public void PlayMusic(String filepath) {
		InputStream music;
		try {
			music = new FileInputStream(new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR with file path ");
		}
		
	}
}
