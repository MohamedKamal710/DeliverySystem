package InternalFrames;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import Conotroller.SysData;
import Model.Parcel;
import Model.Receiver;
import Model.WareHouse;
import gui.login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TrackParcel extends JInternalFrame {
	
	/***
	 * this frame is built to track the parcel , 
	 * this frame is reachable only from the receiver homepage ,
	 */
	public TrackParcel() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 475, 786, 315);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel track1 = new JLabel("");
		track1.setIcon(new ImageIcon(TrackParcel.class.getResource("/track1.png")));
		track1.setBounds(12, 11, 772, 176);
		panel.add(track1);
		track1.setVisible(false);
		
		JLabel track2 = new JLabel("");
		track2.setIcon(new ImageIcon(TrackParcel.class.getResource("/track2.png")));
		track2.setBounds(12	, 11, 772, 176);
		panel.add(track2);
		track2.setVisible(false);
		
		JLabel track3 = new JLabel("");
		track3.setIcon(new ImageIcon(TrackParcel.class.getResource("/track3.png")));
		track3.setBounds(12	, 11, 772, 176);
		panel.add(track3);
		track3.setVisible(false);
		
		JLabel track4 = new JLabel("");
		track4.setIcon(new ImageIcon(TrackParcel.class.getResource("/track4.png")));
		track4.setBounds(12	, 11, 772, 176);
		panel.add(track4);
		track4.setVisible(false);
		
		JLabel MWlabel = new JLabel("Main WareHouse");
		MWlabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		MWlabel.setHorizontalAlignment(SwingConstants.CENTER);
		MWlabel.setBounds(12, 197, 125, 27);
		panel.add(MWlabel);
		MWlabel.setVisible(false);
		
		JLabel Shippedlabel = new JLabel("Shipped");
		Shippedlabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		Shippedlabel.setHorizontalAlignment(SwingConstants.CENTER);
		Shippedlabel.setBounds(259, 200, 69, 21);
		panel.add(Shippedlabel);
		Shippedlabel.setVisible(false);
		
		JLabel NWlabel = new JLabel("Nearest WareHouse");
		NWlabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16));
		NWlabel.setBounds(439, 192, 163, 37);
		panel.add(NWlabel);
		NWlabel.setVisible(false);
		
		JLabel lblDelivered = new JLabel("Delivered");
		lblDelivered.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblDelivered.setBounds(692, 200, 82, 14);
		panel.add(lblDelivered);
		lblDelivered.setVisible(false);
		
		JComboBox comboBox = new JComboBox() ;
		comboBox.setBounds(249, 197, 173, 26);
		getContentPane().add(comboBox);
		JLabel ChooseParcel = new JLabel("Select Parcel :");
		ChooseParcel.setFont(new Font("Traditional Arabic", Font.BOLD, 21));
		ChooseParcel.setHorizontalAlignment(SwingConstants.CENTER);
		ChooseParcel.setBounds(54, 187, 155, 44);
		getContentPane().add(ChooseParcel);
		JLabel lblStatus = new JLabel("Status  :");
		lblStatus.setForeground(new Color(51, 0, 153));
		lblStatus.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(39, 284, 129, 30);
		getContentPane().add(lblStatus);
		lblStatus.setVisible(false);
		
		JLabel lblstatus1 = new JLabel("Main WareHouse ");
		lblstatus1.setForeground(Color.RED);
		lblstatus1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 16));
		lblstatus1.setVerticalAlignment(SwingConstants.TOP);
		lblstatus1.setBounds(158, 289, 282, 55);
		getContentPane().add(lblstatus1);
		lblstatus1.setVisible(false);
		
		JLabel lblstatus2 = new JLabel("Shipped - on the Way to Your Closest WareHouse");
		lblstatus2.setForeground(new Color(139, 69, 19));
		lblstatus2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblstatus2.setBounds(158, 270, 376, 61);
		getContentPane().add(lblstatus2);
		lblstatus2.setVisible(false);
		
		JLabel lblstatus3 = new JLabel("Your Town's WareHouse - Wating to Be Delivered");
		lblstatus3.setForeground(Color.BLUE);
		lblstatus3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblstatus3.setBounds(158, 270, 396, 61);
		getContentPane().add(lblstatus3);
		lblstatus3.setVisible(false);
		
		JLabel lblstatus4 = new JLabel("Delivered");
		lblstatus4.setForeground(new Color(50, 205, 50));
		lblstatus4.setFont(new Font("Verdana Pro", Font.PLAIN, 17));
		lblstatus4.setBounds(158, 273, 282, 55);
		getContentPane().add(lblstatus4);
		lblstatus4.setVisible(false);
		
		JLabel lbltrackHead = new JLabel("Tracking your Parcel");
		lbltrackHead.setForeground(new Color(255, 0, 0));
		lbltrackHead.setHorizontalAlignment(SwingConstants.CENTER);
		lbltrackHead.setFont(new Font("Vivaldi", Font.BOLD, 32));
		lbltrackHead.setBounds(138, 45, 371, 55);
		getContentPane().add(lbltrackHead);
		
		JLabel lblNewLabel = new JLabel("Parcel's  Current  Location : ");
		lblNewLabel.setFont(new Font("Urdu Typesetting", Font.BOLD, 23));
		lblNewLabel.setBounds(67, 352, 259, 30);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JLabel lblCurrentLocation = new JLabel("XXXXXXXXXXX");
		lblCurrentLocation.setForeground(Color.BLUE);
		lblCurrentLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentLocation.setBounds(338, 357, 336, 30);
		getContentPane().add(lblCurrentLocation);
		
		JButton locationButton = new JButton("Show Parcel Location");
		locationButton.setBounds(362, 437, 192, 25);
		getContentPane().add(locationButton);
		lblCurrentLocation.setVisible(false);
		locationButton.setVisible(false);
		
		Receiver receiver = null;
		HashMap<String,Parcel> recParcels = new HashMap<String,Parcel>();
		TreeSet<String> parcelId= new TreeSet<String>();
		if(login.idUser != null) {
			receiver = SysData.getInstance().getReceiversMap().get(login.idUser);
			for(Parcel temp : SysData.getInstance().allParcels()) {
				if(temp.getReceiver().equals(receiver)) {
					recParcels.put(temp.getParcelId(), temp);
					parcelId.add(temp.getParcelId());
				}
			}
			comboBox.setModel(new DefaultComboBoxModel<>(parcelId.toArray(new String[parcelId.size()])));
			comboBox.insertItemAt("", 0);
			comboBox.setSelectedIndex(0);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblStatus.setVisible(false);
					lblstatus1.setVisible(false);
					lblstatus2.setVisible(false);
					lblstatus3.setVisible(false);
					locationButton.setVisible(false);
					lblstatus4.setVisible(false);
					lblNewLabel.setVisible(false);
					lblCurrentLocation.setVisible(false);
					track1.setVisible(false);
					track2.setVisible(false);
					track3.setVisible(false);
					track4.setVisible(false);
					Shippedlabel.setVisible(false);
					MWlabel.setVisible(false);
					lblDelivered.setVisible(false);
					NWlabel.setVisible(false);
					if(!comboBox.getSelectedItem().equals("")) {
					Parcel par = SysData.getInstance().getParcelById((String)comboBox.getSelectedItem());
					WareHouse mainW = SysData.getInstance().getWareHouesMap().get(1);
					if(par.getLocations().get(par.getLocations().size()-1).equals(mainW) && par.getReceiver().getAddress().getCity().equals(mainW.getAddress().getCity()) && !par.isSentToReceiver()) {
						lblStatus.setVisible(true);
						lblstatus3.setVisible(true);
						lblNewLabel.setVisible(true);
						lblCurrentLocation.setVisible(true);
						locationButton.setVisible(true);
						lblCurrentLocation.setText("Main Warehouse in " + mainW.getAddress().getCity());
						track3.setVisible(true);
						Shippedlabel.setVisible(true);
						MWlabel.setVisible(true);
						lblDelivered.setVisible(true);
						NWlabel.setVisible(true);
					}
					else if(par.getLocations().get(par.getLocations().size()-1).equals(mainW) && !par.getReceiver().getAddress().getCity().equals(mainW.getAddress().getCity())&& mainW.getParcels().contains(par) && !par.isSentToReceiver()) {
						lblStatus.setVisible(true);
						lblstatus1.setVisible(true);
						lblNewLabel.setVisible(true);
						lblCurrentLocation.setVisible(true);
						lblCurrentLocation.setText("Main Warehouse in " + mainW.getAddress().getCity());
						track1.setVisible(true);
						locationButton.setVisible(false);
						Shippedlabel.setVisible(true);
						MWlabel.setVisible(true);
						lblDelivered.setVisible(true);
						NWlabel.setVisible(true);
						locationButton.setVisible(true);

					}
					else if(par.getLocations().get(par.getLocations().size()-1).equals(mainW) && !mainW.getParcels().contains(par) && !par.isSentToReceiver()) {
						lblStatus.setVisible(true);
						lblstatus2.setVisible(true);
						lblNewLabel.setVisible(true);
						lblCurrentLocation.setVisible(true);
						lblCurrentLocation.setText("On the way !");
						track2.setVisible(true);
						Shippedlabel.setVisible(true);
						MWlabel.setVisible(true);
						locationButton.setVisible(false);
						lblDelivered.setVisible(true);
						NWlabel.setVisible(true);
					}
					else if(par.getLocations().get(par.getLocations().size()-1).getParcels().contains(par) && !par.getLocations().get(par.getLocations().size()-1).getAddress().getCity().equals(par.getReceiver().getAddress().getCity()) && !par.isSentToReceiver()) {
						lblStatus.setVisible(true);
						lblstatus2.setVisible(true);
						lblNewLabel.setVisible(true);
						lblCurrentLocation.setVisible(true);
						lblCurrentLocation.setText("On the way !");
						track2.setVisible(true);Shippedlabel.setVisible(true);
						MWlabel.setVisible(true);
						locationButton.setVisible(false);
						lblDelivered.setVisible(true);
						NWlabel.setVisible(true);
						
					}
					else if(par.getLocations().get(par.getLocations().size()-1).getParcels().contains(par) && par.getLocations().get(par.getLocations().size()-1).getAddress().getCity().equals(par.getReceiver().getAddress().getCity()) && !par.isSentToReceiver()) {
						lblStatus.setVisible(true);
						lblstatus3.setVisible(true);
						lblNewLabel.setVisible(true);
						lblCurrentLocation.setVisible(true);
						lblCurrentLocation.setText("Warehouse number : " + par.getLocations().get(par.getLocations().size()-1).getWarehouseId() + " in " + par.getLocations().get(par.getLocations().size()-1).getAddress().getCity() );
						track3.setVisible(true);
						Shippedlabel.setVisible(true);
						MWlabel.setVisible(true);
						lblDelivered.setVisible(true);
						NWlabel.setVisible(true);
						locationButton.setVisible(true);
					}
					else if(par.isSentToReceiver()) {
						lblStatus.setVisible(true);
						lblstatus4.setVisible(true);
						lblNewLabel.setVisible(true);
						lblCurrentLocation.setVisible(true);
						lblCurrentLocation.setText("Delivered to receiver  in : " +  par.getReceiver().getAddress().getCity());
						track4.setVisible(true);
						Shippedlabel.setVisible(true);
						MWlabel.setVisible(true);
						locationButton.setVisible(false);
						lblDelivered.setVisible(true);
						NWlabel.setVisible(true);
					}
				}
				}
			});
		}
		locationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox.getSelectedItem().equals("")) {
					Parcel par = SysData.getInstance().getParcelById((String)comboBox.getSelectedItem());

				ShowMap show = new ShowMap();
				show.setVisible(true);
				show.setLocationRelativeTo(null);
				show.getLocation(par.getLocations().get(par.getLocations().size()-1).getAddress().getCity());
				
				}
			}
		});
		for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
			((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
			}
		JLabel label = new JLabel("");
		getContentPane().add(label);
		label.setBounds(0, 0, 1506, 994);
		label.setIcon(new ImageIcon(getClass().getResource("/internalframeBackground.png")));
	}
}