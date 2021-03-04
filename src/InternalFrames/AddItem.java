package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Conotroller.*;
import InternalFrames.AddWarehouse.JTextFieldLimit;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
public class AddItem extends JInternalFrame {
	private JTextField textField;
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
					AddItem frame = new AddItem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * this frame is built to add an item to the system using the method from sysdata , with considering to all errors that can occur.
	 */
	public AddItem() {
		
		setBounds(100, 100, 917, 795);
		getContentPane().setLayout(null);
		
		JLabel lblAddItemTo = new JLabel("Add Item to the System :");
		lblAddItemTo.setForeground(Color.BLUE);
		lblAddItemTo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAddItemTo.setBounds(58, 67, 285, 25);
		getContentPane().add(lblAddItemTo);
		
		JLabel catalogIDLabel = new JLabel("Catalog ID :");
		catalogIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		catalogIDLabel.setBounds(26, 185, 128, 25);
		getContentPane().add(catalogIDLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(185, 185, 172, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setDocument(new JTextFieldLimit(6));
		
		JLabel ItemNameLabel = new JLabel("Item Name :");
		ItemNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ItemNameLabel.setBounds(26, 278, 128, 25);
		getContentPane().add(ItemNameLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setBounds(185, 278, 172, 30);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setDocument(new JTextFieldLimit(30));
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPrice.setBounds(26, 373, 89, 25);
		getContentPane().add(lblPrice);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setBounds(185, 373, 116, 30);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setDocument(new JTextFieldLimit(7));
		
		JLabel lblWeight = new JLabel("Weight :");
		lblWeight.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWeight.setBounds(26, 460, 89, 27);
		getContentPane().add(lblWeight);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_3.setBounds(185, 461, 116, 30);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setDocument(new JTextFieldLimit(4));
		
		JToggleButton toggleButton = new JToggleButton("No Free Shipping");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(toggleButton.isSelected()) {
					toggleButton.setText("Free Shipping");
				}
				else {
					toggleButton.setText("No Free Shipping");
				}
			}
		});
		toggleButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toggleButton.setBounds(101, 546, 159, 39);
		getContentPane().add(toggleButton);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddItem.setBackground(Color.BLUE);
		btnAddItem.setForeground(Color.WHITE);
		btnAddItem.setBounds(114, 667, 137, 39);
		getContentPane().add(btnAddItem);
		
		JLabel lblEmpty1 = new JLabel("Empty!");
		lblEmpty1.setForeground(Color.RED);
		lblEmpty1.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblEmpty1.setBounds(313, 468, 60, 23);
		getContentPane().add(lblEmpty1);
		lblEmpty1.setVisible(false);
		
		JLabel lblEmpty = new JLabel("Empty!");
		lblEmpty.setForeground(Color.RED);
		lblEmpty.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblEmpty.setBounds(313, 376, 60, 25);
		getContentPane().add(lblEmpty);
		lblEmpty.setVisible(false);
		
		JLabel lblEmpty2 = new JLabel("Empty!");
		lblEmpty2.setForeground(Color.RED);
		lblEmpty2.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblEmpty2.setBounds(369, 282, 60, 25);
		getContentPane().add(lblEmpty2);
		lblEmpty2.setVisible(false);
		
		JLabel lblEmpty3 = new JLabel("Empty!");
		lblEmpty3.setForeground(Color.RED);
		lblEmpty3.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblEmpty3.setBounds(369, 189, 60, 25);
		getContentPane().add(lblEmpty3);
		
		JLabel mainEmptyLabel = new JLabel("There is an Empty Field !");
		mainEmptyLabel.setForeground(Color.RED);
		mainEmptyLabel.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		mainEmptyLabel.setBounds(89, 715, 200, 16);
		getContentPane().add(mainEmptyLabel);
		
		JLabel numbersOnlyLabel = new JLabel("It must contain positive numbers only !");
		numbersOnlyLabel.setForeground(Color.RED);
		numbersOnlyLabel.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		numbersOnlyLabel.setBounds(369, 188, 255, 25);
		getContentPane().add(numbersOnlyLabel);
		
		JLabel numbersOnlylblPrice = new JLabel("It must contain positive numbers only !");
		numbersOnlylblPrice.setForeground(Color.RED);
		numbersOnlylblPrice.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		numbersOnlylblPrice.setBounds(313, 378, 264, 23);
		getContentPane().add(numbersOnlylblPrice);
		
		JLabel lblItMustContain = new JLabel("It must contain positive numbers only !");
		lblItMustContain.setForeground(Color.RED);
		lblItMustContain.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblItMustContain.setBounds(313, 468, 264, 19);
		getContentPane().add(lblItMustContain);
		lblItMustContain.setVisible(false);
		numbersOnlylblPrice.setVisible(false);
		numbersOnlyLabel.setVisible(false);
		mainEmptyLabel.setVisible(false);
		lblEmpty3.setVisible(false);
		JLabel lblItemIsAlready = new JLabel("Item is already available !");
		lblItemIsAlready.setForeground(Color.RED);
		lblItemIsAlready.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblItemIsAlready.setBounds(89, 715, 183, 16);
		getContentPane().add(lblItemIsAlready);
		lblItemIsAlready.setVisible(false);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setBorder(new JTextField().getBorder());
				textField_2.setBorder(new JTextField().getBorder());
				textField_1.setBorder(new JTextField().getBorder());
				textField_3.setBorder(new JTextField().getBorder());
				lblEmpty.setVisible(false);
				lblEmpty1.setVisible(false);
				lblEmpty2.setVisible(false);
				lblEmpty3.setVisible(false);
				lblItemIsAlready.setVisible(false);
				lblItMustContain.setVisible(false);
				numbersOnlylblPrice.setVisible(false);
				numbersOnlyLabel.setVisible(false);
				mainEmptyLabel.setVisible(false);
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || textField_3.getText().isEmpty() ) {
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					if(textField.getText().isEmpty()) {
						Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
						lblEmpty3.setVisible(true);
						mainEmptyLabel.setVisible(true);
						textField.setBorder(emptyBorder);
					}
					if(textField_1.getText().isEmpty()) {
						Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
						lblEmpty2.setVisible(true);
						mainEmptyLabel.setVisible(true);
						textField_1.setBorder(emptyBorder);
					}
					if(textField_2.getText().isEmpty()) {
						Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
						lblEmpty.setVisible(true);
						mainEmptyLabel.setVisible(true);
						textField_2.setBorder(emptyBorder);
					}
					if(textField_3.getText().isEmpty()) {
						Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
						lblEmpty1.setVisible(true);
						mainEmptyLabel.setVisible(true);
						textField_3.setBorder(emptyBorder);
					}
				}
				else if (!textField.getText().matches("[0-9]+")) {
					Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
					numbersOnlyLabel.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					textField.setBorder(emptyBorder);
				}
				else if (!textField_2.getText().matches("[0-9.]*")) {
					numbersOnlylblPrice.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
					textField_2.setBorder(emptyBorder);

				}else if(!textField_3.getText().matches("[0-9.]*")) {
					lblItMustContain.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					Border emptyBorder = BorderFactory.createLineBorder(Color.RED, 1);
					textField_3.setBorder(emptyBorder);
				}
				else {
					if(SysData.getInstance().addItem(Long.parseLong(textField.getText()), textField_1.getText(), Double.parseDouble(textField_2.getText()), toggleButton.getText().equals("Free Shipping"),Double.parseDouble(textField_3.getText()))) {
					PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
					JOptionPane.showMessageDialog(null, "Item has been added successfully !","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
	    			textField.setText("");
	    			textField_1.setText("");
	    			textField_2.setText("");
	    			textField_3.setText("");
					}else {
						lblItemIsAlready.setVisible(true);
					}

				}
			}
		});
		this.getRootPane().setDefaultButton(btnAddItem);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1506, 994);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/internal2.png")));
		
		
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
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
