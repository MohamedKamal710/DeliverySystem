package InternalFrames;
import Utils.Constants;
import Utils.E_ModelType;
import java.awt.EventQueue;
import Utils.E_TypeTruck;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Conotroller.SysData;
import InternalFrames.AddWarehouse.JTextFieldLimit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;

public class AddVehicle extends JInternalFrame {
	private JTextField textField;
	E_ModelType[] types;
	E_TypeTruck[] trucktypes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVehicle frame = new AddVehicle();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame is built to add a vehicle , it can add a truck or a car , considering duplicates !
	 */
	public AddVehicle() {
		setBounds(100, 100, 915, 850);
		getContentPane().setLayout(null);
		
		JLabel vinLabel = new JLabel("Vehicle ID number :");
		vinLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		vinLabel.setBounds(137, 292, 194, 28);
		getContentPane().add(vinLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(343, 289, 175, 35);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setDocument(new JTextFieldLimit(8));
		JLabel typeLabel = new JLabel("Type of vehicle :");
		typeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		typeLabel.setBounds(137, 218, 157, 28);
		getContentPane().add(typeLabel);
		
		JRadioButton rdbtnCar = new JRadioButton("Car");
		rdbtnCar.setSelected(true);
		rdbtnCar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnCar.setBounds(343, 221, 66, 25);
		getContentPane().add(rdbtnCar);
		
		JRadioButton truckRadio = new JRadioButton("Truck");
		truckRadio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		truckRadio.setBounds(436, 221, 82, 25);
		getContentPane().add(truckRadio);
		setVisible(false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(truckRadio);
		group.add(rdbtnCar);
		
		JLabel colorLabel = new JLabel("Color :");
		colorLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		colorLabel.setBounds(138, 369, 82, 28);
		getContentPane().add(colorLabel);
		
		 String[] colors1 = { "Red" , "Blue" , "Black" , "White" , "Gray" , "Orange" , "Metalic" , "Yellow" ,"Pink" , "Purple" ,"Green"};
		JComboBox comboBox = new JComboBox(colors1);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(246, 367, 106, 33);
		getContentPane().add(comboBox);
		
		JLabel modelLabel = new JLabel("Type :");
		modelLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		modelLabel.setBounds(138, 435, 82, 28);
		getContentPane().add(modelLabel);
		types=  Utils.E_ModelType.values();
		JComboBox comboBox_1 = new JComboBox(types);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(257, 435, 106, 28);
		getContentPane().add(comboBox_1);
		
		JLabel hybridLabel = new JLabel("Hybrid :");
		hybridLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		hybridLabel.setBounds(138, 493, 82, 28);
		getContentPane().add(hybridLabel);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnYes.setBounds(260, 495, 59, 25);
		getContentPane().add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNo.setBounds(344, 495, 53, 25);
		getContentPane().add(rdbtnNo);
		
		ButtonGroup group2=  new ButtonGroup();
		group2.add(rdbtnNo);
		group2.add(rdbtnYes);
		
		JLabel lblTypeOfTruck = new JLabel("Type of Truck :");
		lblTypeOfTruck.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTypeOfTruck.setBounds(138, 556, 143, 25);
		getContentPane().add(lblTypeOfTruck);
		
		trucktypes = Utils.E_TypeTruck.values();
		JComboBox comboBox_2 = new JComboBox(trucktypes);
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_2.setBounds(293, 551, 118, 35);
		getContentPane().add(comboBox_2);
		comboBox_2.setEnabled(false);
		
		JButton AddButton = new JButton("Add Vehicle");
		AddButton.setForeground(Color.WHITE);
		AddButton.setBackground(Color.BLUE);
		AddButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		AddButton.setBounds(162, 651, 157, 55);
		getContentPane().add(AddButton);
		
		JLabel lblAddVehicleTo = new JLabel("Add Vehicle to the system : ");
		lblAddVehicleTo.setForeground(Color.BLUE);
		lblAddVehicleTo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAddVehicleTo.setBounds(137, 79, 343, 28);
		getContentPane().add(lblAddVehicleTo);
		rdbtnCar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnYes.setEnabled(true);
				rdbtnNo.setEnabled(true);
				comboBox_2.setEnabled(false);
			}
		});
		truckRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnYes.setEnabled(false);
				rdbtnNo.setSelected(false);
				rdbtnNo.setEnabled(false);
				comboBox_2.setEnabled(true);
			}
		});
		JLabel duplicate = new JLabel("Vehicle is already available !");
		duplicate.setForeground(Color.RED);
		duplicate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		duplicate.setBounds(150, 712, 198, 25);
		getContentPane().add(duplicate);
		duplicate.setVisible(false);
	    JLabel wrongLabel = new JLabel("");
	    wrongLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    wrongLabel.setForeground(Color.RED);
	    wrongLabel.setBounds(530, 299, 157, 16);
	    getContentPane().add(wrongLabel);
	    wrongLabel.setVisible(false);
	    AddButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
				textField.setBorder(new JTextField().getBorder());
			    wrongLabel.setVisible(false);
			    duplicate.setVisible(false);
	    		if(textField.getText().isEmpty()) {
					PlayMusic("Sound\\\\windows_xp_ding.wav");
	    			wrongLabel.setText("Field is empty!");
	    			wrongLabel.setVisible(true);
	    			Border border = BorderFactory.createLineBorder(Color.RED,1);
					textField.setBorder(border);
	    		}
	    		else if (!setVin(textField.getText())) {
	    			wrongLabel.setText("Wrong Format VIN");
	    			wrongLabel.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
	    		}
	    		else
	    		{
	    			if(rdbtnYes.isEnabled() == false && rdbtnNo.isEnabled() == false && comboBox_2.isEnabled()==true)	{
	    				if(SysData.getInstance().addTruck(textField.getText(),(String) comboBox.getSelectedItem(), (E_ModelType)comboBox_1.getSelectedItem(), 0, (E_TypeTruck)comboBox_2.getSelectedItem())) {
	    					PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
	    					JOptionPane.showMessageDialog(null, "Truck has been added successfully! ","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
	    			}
	    				else
	    					duplicate.setVisible(true);
	    			}
	    			else {
	    				if(SysData.getInstance().addCar(textField.getText(), (String)comboBox.getSelectedItem(), (E_ModelType)comboBox_1.getSelectedItem(), 0, rdbtnYes.isSelected())) {
	    					PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
	    					JOptionPane.showMessageDialog(null, "Car has been added successfully! ","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
	    				}
	    				else
	    					duplicate.setVisible(true);
	    			}
    				textField.setText("");
	    		}
	    	}
	    });
	    
		this.getRootPane().setDefaultButton(AddButton);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1506, 994);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/internalBack.png")));


		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		
		
	}
	
	public boolean setVin(String vin) {
		//TODO
		char ch;
		char[] charArray = vin.toCharArray();/*Converting String to “Character” array*/
		if (vin.length()!=Constants.NUM_OF_VIN_CHARACTERS)/*the lenght of the VIN MUST be a 17 characters only!!*/
			{
			return false;
			}
		for (int i=0; i<Constants.FORBIDDEN_VIN_CHARACTERS.length;i++)
		{
			ch=Constants.FORBIDDEN_VIN_CHARACTERS[i];
			for (int j=0;j<Constants.NUM_OF_VIN_CHARACTERS;j++)/*check each char from the string if it contain one of the frobbiden letters*/
				if (ch==charArray[j])
				{
					return false;
				}
		}
		return true;	
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
