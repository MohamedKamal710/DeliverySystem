package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Conotroller.SysData;
import InternalFrames.CoordinatorDV;
import InternalFrames.DD_CoordinationCar;
import InternalFrames.Route;
import Model.Driver;
import SerializationDemo.Delivery;
import SerializationDemo.Serialization;
//import InternalFrames.CoordinatorDV;
import gui.Homepage.ClockPane;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Label;

public class CoordinatorPage extends JFrame {

	private JPanel contentPane;
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
					CoordinatorPage frame = new CoordinatorPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CoordinatorPage() {
		setTitle("Delivery - Coordinator Homepage");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/delivery2-512.png"));
		this.setIconImage(logo.getImage());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Arial", Font.BOLD, 20));
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			login loginPage = new login();
			loginPage.getJframe().setVisible(true);
			dispose();
			}
		});
		mntmLogOut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFile.add(mntmLogOut);
		
		JMenuItem mntmExitSystem = new JMenuItem("Exit system");
		mntmExitSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				mntmExitSystem.doClick();
			}
		});
		mntmExitSystem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFile.add(mntmExitSystem);
		
		JMenu mnActions = new JMenu("Actions");
		mnActions.setFont(new Font("Arial", Font.BOLD, 20));
		menuBar.add(mnActions);
		
		JMenuItem mntmAddDriverTo = new JMenuItem("Add Driver to Truck");
		mntmAddDriverTo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnActions.add(mntmAddDriverTo);
		
		JMenuItem mntmAddRouteTo = new JMenuItem("Add Route to Truck");
		mntmAddRouteTo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnActions.add(mntmAddRouteTo);
		
		JMenuItem mntmAddRouteTo_1 = new JMenuItem("Add Route to Car");
		mntmAddRouteTo_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnActions.add(mntmAddRouteTo_1);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton homepageButton = new JButton("Homepage");
		homepageButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		homepageButton.setBounds(50, 50, 241, 57);
		contentPane.add(homepageButton);
		
		JButton drivertotruckButton = new JButton("Add Driver To Truck");
		drivertotruckButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		drivertotruckButton.setBounds(50, 150, 241, 57);
		contentPane.add(drivertotruckButton);
		
		JButton addRouteButton = new JButton("Add Route to Truck");
		addRouteButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		addRouteButton.setBounds(50, 250, 241, 57);
		contentPane.add(addRouteButton);
		
		JLabel lblNewLabel = new JLabel("Welcome :");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 80));
		lblNewLabel.setBounds(370, 8, 531, 118);
		contentPane.add(lblNewLabel);
		
		ClockPane clock = new ClockPane();
		clock.setBounds(500, 520, 900, 100);
		getContentPane().add(clock);
		
		Label NameLabel = new Label("");
		NameLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 76));
		NameLabel.setBounds(566, 109, 1156, 118);
		contentPane.add(NameLabel);
		
		JButton btnNewButton = new JButton("Add Route to Car");
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 19));
		btnNewButton.setBounds(50, 349, 241, 57);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 354, 994);
		contentPane.add(panel);
		if(login.idUser != null)
			NameLabel.setText(SysData.getInstance().getAllCoordinators().get(login.idUser).getFirstName() + " " + SysData.getInstance().getAllCoordinators().get(login.idUser).getSurname());
		
		 CoordinatorDV page1 = new CoordinatorDV();
		 Route page2 = new Route();
		 DD_CoordinationCar page3 = new DD_CoordinationCar();
		drivertotruckButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 page1.setVisible(true);
				 clock.setVisible(false);
				 page2.dispose();
				 page3.dispose();
				page1.setBounds(416, 00, 1506	, 994);
				getContentPane().add(page1);
				lblNewLabel.setVisible(false);
				NameLabel.setVisible(false);
			}
		});
		homepageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				page1.dispose();
				page2.dispose();
				page3.dispose();
				clock.setVisible(true);
				lblNewLabel.setVisible(true);
				NameLabel.setVisible(true);
			}
		});
		addRouteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page2.setVisible(true);
				 clock.setVisible(false);
				 page1.dispose();
					page2.getRefresh().doClick();
				 page3.dispose();
				page2.setBounds(416, 00, 1506	, 994);
				getContentPane().add(page2);
				lblNewLabel.setVisible(false);
				NameLabel.setVisible(false);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page3.setVisible(true);
				 clock.setVisible(false);
				 page1.dispose();
				 page2.dispose();
				page3.setBounds(416, 00, 1506	, 994);
				getContentPane().add(page3);
				lblNewLabel.setVisible(false);
				NameLabel.setVisible(false);
			}
		});
		
		mntmAddDriverTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page1.setVisible(true);
				 clock.setVisible(false);
				 page2.dispose();
				 page3.dispose();
				page1.setBounds(416, 00, 1506	, 994);
				getContentPane().add(page1);
				lblNewLabel.setVisible(false);
				NameLabel.setVisible(false);
			}
		});
		mntmAddRouteTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page2.setVisible(true);
				 clock.setVisible(false);
				 page1.dispose();
				 page3.dispose();
				page2.setBounds(416, 00, 1506	, 994);
				getContentPane().add(page2);
				lblNewLabel.setVisible(false);
				NameLabel.setVisible(false);
			}
		});
		mntmAddRouteTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page3.setVisible(true);
				 clock.setVisible(false);
				 page1.dispose();
				 page2.dispose();
				page3.setBounds(416, 00, 1506	, 994);
				getContentPane().add(page3);
				lblNewLabel.setVisible(false);
				NameLabel.setVisible(false);
			}
		});
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
//		JLabel background = new JLabel();
//		getContentPane().add(background);
//		background.setVisible(true);
//		background.setIcon(new ImageIcon(getClass().getResource("/CoorBack.png")));
//		background.setBounds(0, 0, 1920, 1020);
	}
	
	 public class ClockPane extends JPanel {

	        private JLabel clock;

	        public ClockPane() {
	            setLayout(new BorderLayout());
	            clock = new JLabel();
	            setOpaque( false ) ;
	            clock.setHorizontalAlignment(JLabel.CENTER);
	            clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 72f));
	            tickTock();
	            clock.setBackground(Color.WHITE);
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
