package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Conotroller.SysData;
import Exceptions.OnlyLettersException;
import InternalFrames.AddWarehouse.JTextFieldLimit;
import Model.Address;

import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import Utils.E_Cities;
import gui.Datei;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.util.Date;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
public class AddDriver extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JPasswordField passwordField;
	private E_Cities [] array;
	private JTextField textField_4;
	private JTextField textField_6;
	public static DateFormat df;
	public static DateFormat dtf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDriver frame = new AddDriver();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this page has been build to add driver  considering all the errors that can occur.
	 */
	public AddDriver() {
		setBounds(100, 100, 858, 935);
		getContentPane().setLayout(null);
		
		JLabel lblDriverId = new JLabel("Driver ID :");
		lblDriverId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDriverId.setBounds(51, 147, 102, 22);
		getContentPane().add(lblDriverId);
		
		JLabel lblNewLabel = new JLabel("Firstname :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(51, 206, 102, 22);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lastname :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(51, 271, 102, 22);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblBirthdate = new JLabel("Birthdate :");
		lblBirthdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBirthdate.setBounds(51, 394, 102, 22);
		getContentPane().add(lblBirthdate);
		
		JLabel lblNewLabel_2 = new JLabel("City :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(51, 454, 102, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblStreet = new JLabel("Street :");
		lblStreet.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStreet.setBounds(51, 510, 93, 22);
		getContentPane().add(lblStreet);
		
		JLabel lblHouseNumber = new JLabel("House number :");
		lblHouseNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHouseNumber.setBounds(51, 573, 150, 22);
		getContentPane().add(lblHouseNumber);
		
		JLabel lblZipCode = new JLabel("ZIP Code :");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblZipCode.setBounds(51, 630, 150, 22);
		getContentPane().add(lblZipCode);
		
		JCheckBox ValidLicense = new JCheckBox("Valid-License");
		ValidLicense.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ValidLicense.setBounds(206, 693, 150, 22);
		getContentPane().add(ValidLicense);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(206, 145, 169, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setDocument(new JTextFieldLimit(9));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(206, 204, 169, 30);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setDocument(new JTextFieldLimit(20));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setBounds(206, 269, 169, 30);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setDocument(new JTextFieldLimit(20));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setBounds(206, 392, 169, 30);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setDocument(new JTextFieldLimit(10));
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_5.setBounds(206, 508, 169, 30);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.setDocument(new JTextFieldLimit(20));
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(51, 336, 102, 22);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(206, 334, 169, 30);
		getContentPane().add(passwordField);
		array = E_Cities.values();
		JComboBox comboBox = new JComboBox(array);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(206, 449, 169, 30);
		getContentPane().add(comboBox);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_4.setBounds(206, 571, 169, 30);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setDocument(new JTextFieldLimit(5));
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_6.setBounds(206, 628, 169, 30);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		textField_6.setDocument(new JTextFieldLimit(7));
		
		JLabel lblAddDriverTo = new JLabel("Add driver to the System :");
		lblAddDriverTo.setForeground(Color.BLUE);
		lblAddDriverTo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblAddDriverTo.setBounds(51, 58, 324, 22);
		getContentPane().add(lblAddDriverTo);
		
		JButton btnNewButton = new JButton("Add driver");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(123, 791, 177, 53);
		getContentPane().add(btnNewButton);
		
		JLabel numbersOnly = new JLabel("");
		numbersOnly.setForeground(Color.RED);
		numbersOnly.setBounds(387, 152, 205, 17);
		getContentPane().add(numbersOnly);
		numbersOnly.setVisible(false);
		
		JLabel lettersOnly = new JLabel("");
		lettersOnly.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lettersOnly.setForeground(Color.RED);
		lettersOnly.setBounds(38, 847, 362, 22);
		getContentPane().add(lettersOnly);
		
		JLabel emptyField = new JLabel("There is an empty field !");
		emptyField.setForeground(Color.RED);
		emptyField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emptyField.setBounds(133, 847, 169, 22);
		getContentPane().add(emptyField);
		emptyField.setVisible(false);
		lettersOnly.setVisible(false);
		JLabel invalidDate = new JLabel("Invalid birthdate format");
		invalidDate.setForeground(Color.RED);
		invalidDate.setBounds(387, 399, 157, 16);
		getContentPane().add(invalidDate);
		
		JLabel numbers1 = new JLabel("Numbers only !");
		numbers1.setForeground(Color.RED);
		numbers1.setFont(new Font("Dialog", Font.PLAIN, 13));
		numbers1.setBounds(387, 578, 93, 16);
		getContentPane().add(numbers1);
		
		JLabel zipNumbersError = new JLabel("Numbers only !");
		zipNumbersError.setForeground(Color.RED);
		zipNumbersError.setBounds(387, 635, 93, 16);
		getContentPane().add(zipNumbersError);
		JLabel passwordLength = new JLabel("Password must be minimum 8 letters");
		passwordLength.setForeground(Color.RED);
		passwordLength.setBounds(387, 341, 213, 16);
		getContentPane().add(passwordLength);
		passwordLength.setVisible(false);
		JLabel zipcodeLength = new JLabel("ZIP code must consist of 7 digits !");
		zipcodeLength.setFont(new Font("Tahoma", Font.PLAIN, 13));
		zipcodeLength.setForeground(Color.RED);
		zipcodeLength.setBounds(387, 635, 205, 16);
		getContentPane().add(zipcodeLength);
		numbers1.setVisible(false);
		zipNumbersError.setVisible(false);
		zipcodeLength.setVisible(false);
		invalidDate.setVisible(false);
		JLabel minimumageDriver = new JLabel("Driver should be above 18 !");
		minimumageDriver.setForeground(Color.RED);
		minimumageDriver.setBounds(387, 399, 163, 16);
		getContentPane().add(minimumageDriver);
		minimumageDriver.setVisible(false);
		JLabel driverExisted = new JLabel("Driver with the given ID does exist !");
		driverExisted.setFont(new Font("Tahoma", Font.PLAIN, 16));
		driverExisted.setForeground(Color.RED);
		driverExisted.setBounds(82, 847, 287, 22);
		getContentPane().add(driverExisted);
		driverExisted.setVisible(false);
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lettersOnly.setVisible(false);
				numbersOnly.setVisible(false);
				driverExisted.setVisible(false);
				minimumageDriver.setVisible(false);
				emptyField.setVisible(false);
				numbers1.setVisible(false);
				invalidDate.setVisible(false);
				zipNumbersError.setVisible(false);
				zipcodeLength.setVisible(false);
				passwordLength.setVisible(false);
				textField.setBorder(new JTextField().getBorder());
				textField_1.setBorder(new JTextField().getBorder());
				textField_2.setBorder(new JTextField().getBorder());
				textField_3.setBorder(new JTextField().getBorder());
				textField_4.setBorder(new JTextField().getBorder());
				textField_5.setBorder(new JTextField().getBorder());
				textField_6.setBorder(new JTextField().getBorder());
				passwordField.setBorder(new JPasswordField().getBorder());

				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || textField_3.getText().isEmpty()||textField_5.getText().isEmpty()
						|| textField_6.getText().isEmpty() || textField_4.getText().isEmpty() || passwordField.getPassword().length==0) {
					emptyField.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");

					if(textField.getText().isEmpty()) {
						textField.setBorder(border);
					}
					if(textField_1.getText().isEmpty()) {
						textField_1.setBorder(border);
					}
					if(textField_2.getText().isEmpty()) {
						textField_2.setBorder(border);
					}
					if(passwordField.getPassword().length==0) {
						passwordField.setBorder(border);
					}
					if(textField_3.getText().isEmpty()) {
						textField_3.setBorder(border);
					}
					if(textField_5.getText().isEmpty()) {
						textField_5.setBorder(border);
					}
					if(textField_4.getText().isEmpty()) {
						textField_4.setBorder(border);
					}
					if(textField_6.getText().isEmpty()) {
						textField_6.setBorder(border);
					}
				}
				else if (!textField.getText().matches("[0-9]+")) {
					textField.setBorder(border);
					numbersOnly.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					numbersOnly.setText("Must contain numbers only!");
				}
				else if(textField.getText().length() != 9) {
					textField.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					numbersOnly.setText("Must be 9 digits!");
					numbersOnly.setVisible(true);
					
				}
				else if(!textField_1.getText().matches("^[a-zA-Z]*$") || !textField_2.getText().matches("^[a-zA-Z]*$")) {
					try {
						throw new OnlyLettersException ( "Firstname or Lastname should be written only in letters ");
						}catch(OnlyLettersException ex) {
							PlayMusic("Sound\\\\windows_xp_ding.wav");
							lettersOnly.setText(ex.getMessage());
							lettersOnly.setVisible(true);
						}
					if(!textField_1.getText().matches("^[a-zA-Z]*$")) {
						textField_1.setBorder(border);
					}
					if(!textField_2.getText().matches("^[a-zA-Z]*$")) {
						textField_2.setBorder(border);
					}
				}
				else if ( passwordField.getPassword().length < 8 || passwordField.getPassword().length > 16) {
					passwordLength.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					passwordField.setBorder(border);

				}
				else if(textField_3.getText().length()!=10 ||  !checkValidDate(textField_3.getText())|| !textField_3.getText().matches("[0-3]\\d/[01]\\d/\\d{4}" )) {
					invalidDate.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					textField_3.setBorder(border);
				}
				else if (textField_3.getText().length() == 10 && Integer.parseInt(textField_3.getText().substring(6, 10)) >= Datei.MAX_VALID_YR - 18 ) {
					minimumageDriver.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					textField_3.setBorder(border);

				}
				else if (!textField_4.getText().matches("[0-9]+")) {
					textField_4.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					numbers1.setVisible(true);
				}
				else if(!textField_6.getText().matches("[0-9]+")||textField_6.getText().length()!=7) {
						textField_6.setBorder(border);
						PlayMusic("Sound\\\\windows_xp_ding.wav");
						if(!textField_6.getText().matches("[0-9]+"))
							zipNumbersError.setVisible(true);
						else
							zipcodeLength.setVisible(true);
				}
				else {
					df = new SimpleDateFormat("dd/MM/yyyy");
					dtf = new SimpleDateFormat("dd/MM/yyyy");
					Date dd = null;
					
			      try {
				 dd =	dtf.parse(textField_3.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
			      String strDate = df.format(dd);
			      String password = String.valueOf(passwordField.getPassword());
					if(SysData.getInstance().addDriver(Long.parseLong(textField.getText()), textField_1.getText(), textField_2.getText(), dd, new Address((E_Cities)comboBox.getSelectedItem(),textField_5.getText(),Integer.parseInt(textField_4.getText()),textField_6.getText()), ValidLicense.isSelected(),password)){
						PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
						JOptionPane.showMessageDialog(null, "Driver has been added successfully !","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_5.setText("");
						textField_6.setText("");
						passwordField.setText("");
						ValidLicense.setSelected(false);
						System.out.println(SysData.getInstance().allDrivers());
					}
					else
						driverExisted.setVisible(true);
				}
			}
		});
		this.getRootPane().setDefaultButton(btnNewButton);
		
	
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1506, 994);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/internal2.png")));
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		}
	
	public boolean checkValidDate(String date) {
		try {
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		return Datei.isValidDate(day, month, year);
		}catch(Exception e) {
			return false;
		}
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
