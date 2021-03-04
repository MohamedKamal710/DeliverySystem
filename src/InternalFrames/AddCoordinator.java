package InternalFrames;

import java.awt.EventQueue;

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
import Exceptions.OnlyLettersException;
import InternalFrames.AddWarehouse.JTextFieldLimit;
import Model.Coordinator;
import Model.Driver;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.awt.event.ActionEvent;
import Model.Receiver;
public class AddCoordinator extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCoordinator frame = new AddCoordinator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * with this internal frame we can add a coordinator , considering all the errors that can occur !
	 */
	public AddCoordinator() {
		setBounds(100, 100, 858, 935);
		getContentPane().setLayout(null);
		
		JLabel lblAddCoordinatorTo = new JLabel("Add Coordinator to the system :");
		lblAddCoordinatorTo.setForeground(Color.BLUE);
		lblAddCoordinatorTo.setFont(new Font("Verdana Pro Cond Light", Font.BOLD, 25));
		lblAddCoordinatorTo.setBounds(33, 57, 377, 37);
		getContentPane().add(lblAddCoordinatorTo);
		
		JLabel lblCoordinatorId = new JLabel("Coordinator ID :");
		lblCoordinatorId.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblCoordinatorId.setBounds(33, 200, 212, 31);
		getContentPane().add(lblCoordinatorId);
		
		JLabel lblFirstname = new JLabel("Firstname :");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFirstname.setBounds(33, 295, 160, 31);
		getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname :");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLastname.setBounds(33, 393, 160, 31);
		getContentPane().add(lblLastname);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPassword.setBounds(33, 476, 146, 31);
		getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField.setBounds(272, 200, 219, 37);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setDocument(new JTextFieldLimit(9));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField_1.setBounds(272, 297, 219, 37);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setDocument(new JTextFieldLimit(20));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField_2.setBounds(272, 391, 219, 37);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setDocument(new JTextFieldLimit(20));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		passwordField.setBounds(272, 478, 219, 37);
		getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(503, 197, 279, 31);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		JLabel firstandlastError = new JLabel("");
		firstandlastError.setForeground(Color.RED);
		firstandlastError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstandlastError.setBounds(156, 678, 469, 20);
		getContentPane().add(firstandlastError);
		firstandlastError.setVisible(false);
		JLabel emptyField = new JLabel("There is an empty field !");
		emptyField.setForeground(Color.RED);
		emptyField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emptyField.setBounds(282, 678, 202, 31);
		getContentPane().add(emptyField);
		emptyField.setVisible(false);
		JLabel passwordLength = new JLabel("Password length must be  8 to 16 letters!");
		passwordLength.setForeground(Color.RED);
		passwordLength.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLength.setBounds(503, 488, 327, 19);
		getContentPane().add(passwordLength);
		passwordLength.setVisible(false);
		JLabel existCoor = new JLabel("Coordinator with the givin ID does exist !");
		existCoor.setForeground(Color.RED);
		existCoor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		existCoor.setBounds(219, 678, 332, 31);
		getContentPane().add(existCoor);
		existCoor.setVisible(false);
		Border border =BorderFactory.createLineBorder(Color.RED, 1);
		JButton btnNewButton = new JButton("Add Coordinator");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				emptyField.setVisible(false);
				passwordLength.setVisible(false);
				existCoor.setVisible(false);
				firstandlastError.setVisible(false);
				textField.setBorder(new JTextField().getBorder());;
				textField_1.setBorder(new JTextField().getBorder());
				textField_2.setBorder(new JTextField().getBorder());
				passwordField.setBorder(new JPasswordField().getBorder());
				firstandlastError.setText("");
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || passwordField.getPassword().length==0) {
					emptyField.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					if(textField.getText().isEmpty())
						textField.setBorder(border);
					if(textField_1.getText().isEmpty())
						textField_1.setBorder(border);
					if(textField_2.getText().isEmpty())
						textField_2.setBorder(border);
					if(passwordField.getPassword().length==0)
						passwordField.setBorder(border);
				}
				else if ( !textField.getText().matches("[0-9]+")) {
					textField.setBorder(border);
					lblNewLabel.setVisible(true);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					lblNewLabel.setText("Only numbers !");
				}
				else if (textField.getText().length() != 9) {
					textField.setBorder(border);
					PlayMusic("Sound\\\\windows_xp_ding.wav");
					lblNewLabel.setVisible(true);
					lblNewLabel.setText("Must be 9 digits!");
				}
			else if(!textField_1.getText().matches("^[a-zA-Z]*$") || !textField_2.getText().matches("^[a-zA-Z]*$")) {
				try {
					throw new OnlyLettersException ( "Firstname or Lastname should be written only in letters ");
					}catch(OnlyLettersException ex) {
						firstandlastError.setText(ex.getMessage());
						PlayMusic("Sound\\\\windows_xp_ding.wav");
						firstandlastError.setVisible(true);
					}
					if(!textField_1.getText().matches("^[a-zA-Z]*$"))
						textField_1.setBorder(border);
					else
						textField_2.setBorder(border);
			}
			else if(passwordField.getPassword().length < 8 || passwordField.getPassword().length > 16) {
				passwordLength.setVisible(true);
				PlayMusic("Sound\\\\windows_xp_ding.wav");
				passwordField.setBorder(border);
			}
			else {
				String password = String.valueOf(passwordField.getPassword());
				for(Map.Entry<Long, Coordinator> coor : SysData.getInstance().getAllCoordinators().entrySet()) {
					if(coor.getValue().getId() == Long.parseLong(textField.getText()))
						existCoor.setVisible(true);
				}
				for(Driver driver : SysData.getInstance().allDrivers())
					if(driver.getId()==Long.parseLong(textField.getText()))
						existCoor.setVisible(true);
				for(Map.Entry<Long,Receiver> rec :SysData.getInstance().getReceiversMap().entrySet()) {
					if(rec.getValue().getId()==Long.parseLong(textField.getText()))
						existCoor.setVisible(true);
				}
				if(!existCoor.isVisible()) {
					Coordinator coordinator = new Coordinator(Long.parseLong(textField.getText()),textField_1.getText(),textField_2.getText());
					SysData.getInstance().getAllCoordinators().put(Long.parseLong(textField.getText()),coordinator );
					coordinator.setPassword(password);
					PlayMusic("Sound\\\\Beep Ping-SoundBible.com-217088958.wav");
					JOptionPane.showMessageDialog(null, "Coordinator has been added succesfully !","Successful" , 0,new ImageIcon(getClass().getResource("/correct.png")));
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					passwordField.setText("");
					System.out.println(SysData.getInstance().getAllCoordinators());
				}
			}
			}
		});
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(272, 602, 219, 63);
		getContentPane().add(btnNewButton);
		this.getRootPane().setDefaultButton(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1506, 994);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/internalBack.png")));

	
		
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
