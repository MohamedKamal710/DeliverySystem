package gui;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.border.SoftBevelBorder;

import Conotroller.SysData;
import InternalFrames.Receiver_ItemList;
import InternalFrames.TrackParcel;
import Model.Driver;
import SerializationDemo.Delivery;
import SerializationDemo.Serialization;
import gui.Homepage;
import gui.Homepage.ClockPane;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ReceiverFrame extends JFrame {
	private JButton ListButton;
//	Serialization demo = new Serialization() ;
//    SysData sys = SysData.getInstance() ;
//    Delivery dev = Delivery.getInstance() ;
//    ArrayList<Object> list = new ArrayList<>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiverFrame frame = new ReceiverFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public ReceiverFrame() {
		

//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 1000);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
	 
	
	    Receiver_ItemList itemList = new Receiver_ItemList();
	    itemList.setVisible(false);
	    itemList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 153, 0), null, null, new Color(255, 153, 0)));
	    itemList.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	  //  itemList.setVisible(false);
	    itemList.setBounds(250,0,1050,1000);
		getContentPane().add(itemList);
	 
		
		
		JPanel Centerpanel = new JPanel();
		Centerpanel.setBounds(263, 94, 701, 161);
		getContentPane().add(Centerpanel);
		Centerpanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		Centerpanel.setOpaque(false);

			    TrackParcel trackparcel = new TrackParcel();
			    trackparcel.setBounds(250,0,1050,1000);
			    trackparcel.setVisible(false);
			    getContentPane().add(trackparcel);
			     trackparcel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 153, 0), null, null, new Color(255, 153, 0)));
			     trackparcel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
			     ClockPane clock = new ClockPane();
					clock.setBounds(150, 0, 800, 100);
					getContentPane().add(clock);
					clock.setOpaque(false);
					
		JLabel lblWelcome = new JLabel("Welcome , ");
		lblWelcome.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblWelcome.setForeground(new Color(47, 79, 79));
		lblWelcome.setBackground(new Color(244, 164, 96));
		lblWelcome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 0, 167, 52);
		getContentPane().add(lblWelcome);
		JLabel lblName ;

		if(login.idUser != null) {
		lblName = new JLabel(SysData.getInstance().getReceiversMap().get(login.idUser).getFirstName() + " " + SysData.getInstance().getReceiversMap().get(login.idUser).getSurname());
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 54, 243, 26);
		getContentPane().add(lblName);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 153, 0), new Color(255, 153, 0), new Color(255, 153, 0), new Color(255, 153, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 109, 211, 283);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		
		
		ListButton = new JButton("Item History");
		ListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemList.setVisible(true);
				Centerpanel.setVisible(false);
				trackparcel.dispose();
			}
		});
		
		
		
		
		panel_1.add(ListButton);
		ListButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ListButton.setBackground(Color.ORANGE);
		ListButton.setBounds(10, 23, 189, 53);
		
		JButton btnNewButton = new JButton("Track Parcel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Centerpanel.setVisible(false);
				trackparcel.setVisible(true);
				itemList.dispose();
			}
		});
		
		
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 99, 189, 53);
		panel_1.add(btnNewButton);
		
		JButton Homebtn = new JButton("Home");
		Homebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Centerpanel.setVisible(true);
				//Centerpanel.setBounds(70, 70, 1200, 850);
				trackparcel.dispose();
				itemList.dispose();
				
				
			}
		});
		
		this.setTitle("Delivery - Receiver Page");
		this.setIconImage(new ImageIcon(getClass().getResource("/delivery2-512.png")).getImage());
		Homebtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		Homebtn.setBackground(Color.WHITE);
		Homebtn.setBounds(10, 181, 189, 53);
		panel_1.add(Homebtn);
		
		JInternalFrame inFrame = new JInternalFrame("New JInternalFrame");
		inFrame.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 153, 0), null, null, new Color(255, 153, 0)));
		inFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		inFrame.setVisible(false);
		inFrame.setBounds(155, 78, 583, 515);
		getContentPane().add(inFrame);
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu FileMenu = new JMenu("File");
		FileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(FileMenu);
		
		JMenuItem mntmLogout = new JMenuItem("Log out");
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login logPage = new login();
				logPage.getJframe().setVisible(true);
				dispose();
				
			}
		});
		mntmLogout.setHorizontalAlignment(SwingConstants.CENTER);
		FileMenu.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null,"Do you want to save changes?", "Save Data",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	Serialization serial = new Serialization();
							
							serial.serialize("src//SerializationDemo//Del2.ser");
				            System.exit(0);
			            }else if (result == JOptionPane.NO_OPTION){
				            System.exit(0);
			            }else {
			            }
				System.exit(0);
			}
			
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mntmExit.doClick();
			}
		});
		FileMenu.add(mntmExit);
		
		JMenu mnNavigate = new JMenu("Navigate");
		mnNavigate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNavigate);
		
		JMenuItem mntmDisplayItemHistory = new JMenuItem("Display Item History");
		mntmDisplayItemHistory.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmDisplayItemHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemList.setVisible(true);
				Centerpanel.setVisible(false);
				trackparcel.dispose();
			}
		});
		
		
		
		mnNavigate.add(mntmDisplayItemHistory);
		
		JMenuItem mntmParcelTracking = new JMenuItem("Parcel Tracking");
		mntmParcelTracking.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmParcelTracking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Centerpanel.setVisible(false);
				trackparcel.setVisible(true);
				itemList.dispose();
			}
		});
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1300, 936);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/receiverPage.png")));
		mnNavigate.add(mntmParcelTracking);
		
//		this.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				
//
//	    	Delivery D1 =Delivery.getInstance() ;
//	
//	
//	
//			 ArrayList<Object> list1 = D1.getData() ;
//			
//			
//			 System.out.println("the list to be serialized : "+list1);
//System.out.println( "thr list of drivers : "+(ArrayList<Driver>) list1.get(1));
//			System.out.println("the size of array drivese :  : "+list1.isEmpty());
//				System.out.println(" just created the delivery Instance ! ");
//				Serialization demo = new Serialization() ;
//	
//					try {
//						
//					demo.serialize(list1, "src/SerializationDemo/Del2.ser");
//					
//					
//				} catch (Exception eoi) {
//					// TODO Auto-generated catch block
//				
//					eoi.printStackTrace();
//					return ;
//				}
//
//	    }
//			
//	//	}
//			
//				
//				
//			
//	});
	}
	
	 public class ClockPane extends JPanel {

	        private JLabel clock;

	        public ClockPane() {
	            setLayout(new BorderLayout());
	            clock = new JLabel();
//	            setOpaque( false ) ;
	            clock.setHorizontalAlignment(JLabel.CENTER);
	            clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 50f));
	            tickTock();
	            add(clock);

	            Timer timer = new Timer(500, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    tickTock();
	                }
	            });
	            timer.setRepeats(true);
	            timer.setCoalesce(true);
	            timer.setInitialDelay(0);
	            timer.start();
	        }

	        public void tickTock() {
	            clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
	        }
	    }
}