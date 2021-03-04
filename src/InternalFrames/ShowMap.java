package InternalFrames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.E_Cities;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ShowMap extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel ;
	E_Cities[] array = E_Cities.values();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowMap dialog = new ShowMap();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * this frame has to detect the city of the parcels to show them as a live location ,
	 */
	public ShowMap() {
		setBounds(100, 100, 520, 750);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("Live Location of Parcel");
		
		 lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 500, 656);
		contentPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(193, 660, 119, 34);
		contentPanel.add(btnNewButton);
	}
	
	public JLabel getLabel() {
		return lblNewLabel;
	}
	
	public void getLocation(E_Cities city) {
		if(city.equals(array[0]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Acre.png")));
		else if(city.equals(array[1]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Afula.png")));
		else if(city.equals(array[44]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Arad.png")));
		else if(city.equals(array[2]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Ariel.png")));
		else if(city.equals(array[3]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Ashdod.png")));
		else if(city.equals(array[4]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Ashkelon.png")));
		else if(city.equals(array[5]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Baqa-Jatt.png")));
		else if(city.equals(array[6]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Beersheba.png")));
		else if(city.equals(array[7]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Beit_Shemesh.png")));
		else if(city.equals(array[8]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Tiberias.png")));
		else if(city.equals(array[9]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Bnei-Brak.png")));
		else if(city.equals(array[10]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Dimona.png")));
		else if(city.equals(array[11]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Eilat.png")));
		else if(city.equals(array[12]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Yavne.png")));
		else if(city.equals(array[13]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Hadera.png")));
		else if(city.equals(array[14]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Haifa.png")));
		else if(city.equals(array[15]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Herzliya.png")));
		else if(city.equals(array[16]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Hod-Hasharon.png")));
		else if(city.equals(array[17]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Holon.png")));
		else if(city.equals(array[18]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Jerusalem.png")));
		else if(city.equals(array[19]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Karmiel.png")));
		else if(city.equals(array[20]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Kfar-Kasem.png")));
		else if(city.equals(array[21]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Kfar-Sava.png")));
		else if(city.equals(array[22]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Kiryat-Ata.png")));
		else if(city.equals(array[23]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Sderot.png")));
		else if(city.equals(array[24]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Tel-Aviv.png")));
		else if(city.equals(array[25]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/UmmAl-Fahm.png")));
		else if(city.equals(array[26]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Qiryat-Shemona.png")));
		else if(city.equals(array[27]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Maale-Adumim.png")));
		else if(city.equals(array[28]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Migdal-Haemek.png")));
		else if(city.equals(array[29]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Tirat-Carmel.png")));
		else if(city.equals(array[30]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Naheriya.png")));
		else if(city.equals(array[31]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Nazareth.png")));
		else if(city.equals(array[32]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Nazareth_Illit.png")));
		else if(city.equals(array[33]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Nesher.png")));
		else if(city.equals(array[34]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Yokeneam.png")));
		else if(city.equals(array[35]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Petah-Tikva.png")));
		else if(city.equals(array[36]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Qalansuwa.png")));
		else if(city.equals(array[37]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Raanana.png")));
		else if(city.equals(array[38]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Rahat.png")));
		else if(city.equals(array[39]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Ramat-Gan.png")));
		else if(city.equals(array[40]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Ramat-Hasharon.png")));
		else if(city.equals(array[41]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Rehovot.png")));
		else if(city.equals(array[42]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Safed.png")));
		else if(city.equals(array[43]))
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/MAPS/Taibe.png")));
		
	}
}
