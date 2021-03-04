package InternalFrames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.border.Border;

import Model.Receiver;
import Model.SmallParcel;
import javafx.scene.Group;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Conotroller.SysData;
import InternalFrames.AddWarehouse.JTextFieldLimit;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class AddParcel extends JInternalFrame {
	private JTextField textField;
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
					AddParcel frame = new AddParcel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *  this frame is built to add parcel to the system , there are 2 options , auto generate or with inserting parcelID.
	 */
	public AddParcel() {
		setBounds(100, 100, 949, 921);
		getContentPane().setLayout(null);
		
		JLabel lblParcel = new JLabel("Parcel  ID : ");
		lblParcel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblParcel.setBounds(79, 184, 164, 54);
		getContentPane().add(lblParcel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(290, 193, 264, 46);
		getContentPane().add(textField);
		textField.setColumns(9);
		textField.setDocument(new JTextFieldLimit(10));
		
		JRadioButton largeButton = new JRadioButton("Large Parcel");
		largeButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		largeButton.setBounds(290, 324, 177, 31);
		getContentPane().add(largeButton);
		
		JRadioButton smallButton = new JRadioButton("Small Parcel");
		smallButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		smallButton.setBounds(491, 324, 177, 31);
		getContentPane().add(smallButton);
		
		JLabel lblParceSize = new JLabel("Parce Size :");
		lblParceSize.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblParceSize.setBounds(79, 320, 164, 38);
		getContentPane().add(lblParceSize);
		
		JCheckBox autogenerate = new JCheckBox("Auto-generate ");
		autogenerate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		autogenerate.setBounds(575, 196, 193, 34);
		getContentPane().add(autogenerate);
		autogenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(autogenerate.isSelected())
					textField.setEnabled(false);
				else 
					textField.setEnabled(true);
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(largeButton);
		group.add(smallButton);
		group.setSelected(largeButton.getModel(), true);
		
		JLabel lblAddParcelTo = new JLabel("Add Parcel to a Receiver:");
		lblAddParcelTo.setForeground(Color.BLUE);
		lblAddParcelTo.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblAddParcelTo.setBounds(79, 40, 300, 46);
		getContentPane().add(lblAddParcelTo);
		
		JButton btnNewButton = new JButton("Add Parcel");
	
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.setBounds(322, 768, 187, 66);
		getContentPane().add(btnNewButton);
		
		JLabel errorLabel1 = new JLabel("Must contain numbers only !");
		errorLabel1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorLabel1.setForeground(Color.RED);
		errorLabel1.setBounds(322, 239, 242, 19);
		getContentPane().add(errorLabel1);
		
		JLabel emptyField = new JLabel("Empty Field !");
		emptyField.setForeground(Color.RED);
		emptyField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emptyField.setBounds(380, 240, 87, 16);
		getContentPane().add(emptyField);
		
		JLabel lengthError = new JLabel("Maximum length is 10 digits !");
		lengthError.setForeground(Color.RED);
		lengthError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lengthError.setBounds(323, 240, 203, 19);
		getContentPane().add(lengthError);
		lengthError.setVisible(false);
		errorLabel1.setVisible(false);
		emptyField.setVisible(false);
		
		
		this.getRootPane().setDefaultButton(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 448, 827, 307);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "FIRSTNAME", "LASTNAME", "EMAIL", "BIRTHDATE", "ADDRESS"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblChooseAReceiver = new JLabel("Choose a receiver to the parcel :");
		lblChooseAReceiver.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChooseAReceiver.setBounds(79, 420, 289, 22);
		getContentPane().add(lblChooseAReceiver);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefresh.setBounds(349, 421, 97, 25);
		getContentPane().add(btnRefresh);
		table.setVisible(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel receiverChoose = new JLabel("You must choose a receiver to attach to the parcel !");
		receiverChoose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		receiverChoose.setForeground(Color.RED);
		receiverChoose.setBounds(231, 843, 377, 29);
		getContentPane().add(receiverChoose);
		
		JLabel existLabel = new JLabel("Parcel with the givin ID does already exist !");
		existLabel.setForeground(Color.RED);
		existLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		existLabel.setBounds(254, 843, 320, 22);
		getContentPane().add(existLabel);
		existLabel.setVisible(false);
		receiverChoose.setVisible(false);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
//		Object [] columns= { "ID","FIRSTNAME","LASTNAME","EMAIL","BIRTHDATE","ADDRESS"};
//		model.setColumnIdentifiers(columns);
//		model.addColumn( columns);
		Object rowData[] = new Object[6];
//		model.setRowCount(0);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				df = new SimpleDateFormat("dd/MM/yyyy");
				dtf = new SimpleDateFormat("dd/MM/yyyy");
				Date dd = null;
				model.setRowCount(0);
				for(Map.Entry<Long, Receiver> temp : SysData.getInstance().getReceiversMap().entrySet()) {
					boolean flag = false;
					for(int i =0 ;i < table.getRowCount();i++){
						if(table.getValueAt(0, i).equals(temp.getValue().getId())) {
							flag = true;
						}
					}
					if(!flag) {
					String strDate = df.format(temp.getValue().getBirthDate());
					rowData[0]=temp.getValue().getId();
					rowData[1]=temp.getValue().getFirstName();
					rowData[2]=temp.getValue().getSurname();
					rowData[3]=temp.getValue().getEmail();
					rowData[4]=strDate;
					rowData[5]=temp.getValue().getAddress().getCity() +","+temp.getValue().getAddress().getStreet()+","+temp.getValue().getAddress().getHouseNumber()+","+temp.getValue().getAddress().getZipCode();
					((DefaultTableModel)table.getModel()).addRow(rowData);
					}
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel1.setVisible(false);
				emptyField.setVisible(false);
				lengthError.setVisible(false);
				existLabel.setVisible(false);
				receiverChoose.setVisible(false);
				textField.setBorder(new JTextField().getBorder());
				if(!autogenerate.isSelected() && textField.getText().isEmpty()) {
					Border border =BorderFactory.createLineBorder(Color.RED,1);
					textField.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					emptyField.setVisible(true);
				}
				else if (!autogenerate.isSelected() && !textField.getText().matches("[0-9]+")) {
					Border border =BorderFactory.createLineBorder(Color.RED,1);
					textField.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					errorLabel1.setVisible(true);
				}
				else if (!autogenerate.isSelected() && textField.getText().length()>10) {
					Border border = BorderFactory.createLineBorder(Color.RED, 1);
					textField.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					lengthError.setVisible(true);
				}
				else if(table.getSelectedRow()==-1) {
					receiverChoose.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
				}
				else {
					if(group.isSelected(smallButton.getModel())) {
						if(textField.isEnabled()) {
							if(SysData.getInstance().addSmallParcel(textField.getText(),(Long)table.getValueAt(table.getSelectedRow(), 0))!=null) {
								PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
								JOptionPane.showMessageDialog(null, "Small parcel has been successfuly generated! ","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
								textField.setText("");
							}
							else
								existLabel.setVisible(true);
						
						}
						else {
							if(SysData.getInstance().addSmallParcel(null,(Long)table.getValueAt(table.getSelectedRow(), 0))!=null) {
								PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
								JOptionPane.showMessageDialog(null, "Small parcel has been successfuly generated! ","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
								textField.setText("");
							}
							else
								existLabel.setVisible(true);

						}
					}
					else {
						if(textField.isEnabled()) {
							if(SysData.getInstance().addLargeParcel(textField.getText(),(Long)table.getValueAt(table.getSelectedRow(), 0))!=null) {
								PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
								JOptionPane.showMessageDialog(null, "Large parcel has been successfuly generated! ","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
								textField.setText("");

							}
							else
								existLabel.setVisible(true);
						
						}
						else {
							if(SysData.getInstance().addLargeParcel(null,(Long)table.getValueAt(table.getSelectedRow(), 0))!=null) {
								PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
								JOptionPane.showMessageDialog(null, "Large parcel has been successfuly generated! ","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
								textField.setText("");

							}
							else
								existLabel.setVisible(true);
					}
					}
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
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
