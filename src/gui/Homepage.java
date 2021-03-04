package gui;
import InternalFrames.AddCoordinator;
import InternalFrames.AddDriver;
import InternalFrames.AddItem;
import InternalFrames.AddItemToParcel;
import InternalFrames.AddParcel;
import InternalFrames.AddReceiver;
import InternalFrames.AddRoute;
import InternalFrames.AddVehicle;
import InternalFrames.AddWarehouse;
import InternalFrames.AllCars;
import InternalFrames.AllCoordinators;
import InternalFrames.AllDrivers;
import InternalFrames.AllReceivers;
import InternalFrames.AllTrucks;
import InternalFrames.CoordinatorDV;
import InternalFrames.DD_CoordinationCar;
import InternalFrames.Route;
import InternalFrames.SmartBuy;
import InternalFrames.TwiceAtWareHouse;
import InternalFrames.WarehouseTransaction;
import Model.Driver;
import SerializationDemo.Delivery;
import SerializationDemo.Serialization;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conotroller.SysData;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Insets;

public class Homepage extends JFrame  {
	JPanel switchPanel = new JPanel();
	JPanel panelHome = new JPanel();
	JPanel panelAddTruck = new JPanel();
	JButton addButton = new JButton ("Add new truck");
	JButton backButton = new JButton ("Back");
	CardLayout cl = new CardLayout();
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
					Homepage frame = new Homepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Admin Homepage !
	 * can do all the methods in sysdata 
	 */
	public Homepage() {
		
//		Serialization demo = new Serialization() ;
//      SysData sys = SysData.getInstance() ;
////      Delivery dev = Delivery.getInstance() ;
//
//     ArrayList<Object> list = demo.deserialize("src/SerializationDemo/Del2.ser");
//      
//    if(!list.isEmpty() && list != null) {
//      	
//      sys.Setter(list);
//      }
		
	
		setTitle("Delivery");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		 
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
	        {
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
		JMenuItem logout = new JMenuItem("Log out");
		logout.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFile.add(logout);
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Serialization serial = new Serialization();
				serial.serialize("src//SerializationDemo//Del2.ser");
				login logpage= new login();
				logpage.getJframe().setVisible(true);
				
			}
		});
		DD_CoordinationCar frameCarRoute = new DD_CoordinationCar();
		frameCarRoute.setBounds(410, 0, 1506, 994);
		getContentPane().add(frameCarRoute);
		SmartBuy frameSmartBuy = new SmartBuy();
		frameSmartBuy.setBounds(416, 0, 1506, 994);
		getContentPane().add(frameSmartBuy);
		TwiceAtWareHouse frameTwice = new TwiceAtWareHouse();
		frameTwice.setBounds(416, 0, 1506, 994);
		getContentPane().add(frameTwice);
		Route frameRoute = new Route();
		getContentPane().add(frameRoute);
		frameRoute.setBounds(416, 0, 1506, 994);
		mnFile.add(mntmExit);
		AllCars frameCars = new AllCars();
		frameCars.setBounds(416, 0, 1506, 994);
		getContentPane().add(frameCars);
		ClockPane clock = new ClockPane();
		clock.setBounds(500, 400, 900, 100);
		getContentPane().add(clock);
		JMenu mnActions = new JMenu("Actions");
		mnActions.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnActions);
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 417, 994);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/welcomeAdmin.png")));
		label.setVisible(true);
		JLabel label2 = new JLabel();
		getContentPane().add(label2);
		label2.setBounds(0, 0, 417, 994);
		label2.setIcon(new ImageIcon(getClass().getResource("/whatup.png")));
		label2.setVisible(false);
		JMenuItem vehicleButtonMenu = new JMenuItem("Add Vehicle");
		vehicleButtonMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(vehicleButtonMenu);
		
		JMenuItem menuAddItem = new JMenuItem("Add Item");
		menuAddItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(menuAddItem);
		
		CoordinatorDV frameDV = new CoordinatorDV();
		frameDV.setBounds(416, 0, 1506, 994);
		getContentPane().add(frameDV);
		
		JMenuItem menuAddParcel = new JMenuItem("Add Parcel");
		JMenuItem mntmAllReceivers = new JMenuItem("All Receivers");
		AllTrucks frameTrucks = new AllTrucks();
		frameTrucks.setBounds(416, 0, 1506, 994);
		this.getContentPane().add(frameTrucks);
		AllCoordinators frameCoordinators = new AllCoordinators();
		getContentPane().add(frameCoordinators);
		frameCoordinators.setBounds(416, 0, 1506, 994);
		menuAddParcel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(menuAddParcel);
			AllReceivers frameReceivers = new AllReceivers();
			getContentPane().add(frameReceivers);
			frameReceivers.setBounds(416,0,1506,994);
		AddItemToParcel frame8 = new AddItemToParcel();
		frame8.setBounds(416, 0, 1506, 994);
		getContentPane().add(frame8);
			JMenuItem mntmAddWarehouse = new JMenuItem("Add Warehouse");
			mntmAddWarehouse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			mnActions.add(mntmAddWarehouse);
			AllDrivers frameDrivers = new AllDrivers();
			this.getContentPane().add(frameDrivers);
			frameDrivers.setBounds(416, 0, 1506, 994);
			AddVehicle frame1 = new AddVehicle();
			this.getContentPane().add(frame1);
		    frame1.setBounds(416, 0, 1506, 994);	
		    AddReceiver frame2 = new AddReceiver();
			getContentPane().add(frame2);
		    frame2.setBounds(416, 0, 1506, 994);
		    AddItem frame3 = new AddItem();
		    getContentPane().add(frame3);
		    frame3.setBounds(416, 0, 1506, 994);
		    AddParcel frame4 = new AddParcel();
		    getContentPane().add(frame4);
		    frame4.setBounds(416,0,1506,994);
		    AddDriver frame5 = new AddDriver();
		    getContentPane().add(frame5);
		    frame5.setBounds(416, 0, 1506, 994);
		    AddCoordinator frame6 = new AddCoordinator();
		    getContentPane().add(frame6);
		    AddWarehouse frame7 = new AddWarehouse();
		    getContentPane().add(frame7);
		    frame7.setBounds(416, 0, 1506, 994);
		    frame6.setBounds(416,0,1506,994);
		    WarehouseTransaction frameTrans = new WarehouseTransaction();
		    getContentPane().add(frameTrans);
		    frameTrans.setBounds(416, 0, 1506, 994);
			JMenuItem mntmAddDriver = new JMenuItem("Add driver");
			mntmAddDriver.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			mnActions.add(mntmAddDriver);
			mntmAddDriver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame5.setVisible(true);
					frame1.dispose();
					frame2.dispose();
					frameTrans.dispose();
					frame3.dispose();		
					frameCars.dispose();
					frameTwice.dispose();
					frame4.dispose();
					frame6.dispose();
					frameReceivers.dispose();
					frame7.dispose();
					frame8.dispose();
					label2.setVisible(true);
					label.setVisible(false);
					clock.setVisible(false);
					frameDrivers.dispose();
					frameDV.dispose();
					frameCoordinators.dispose();
					frameTrucks.dispose();
					frameSmartBuy.dispose();
					frameRoute.dispose();
					frameCarRoute.dispose();
				}
			});
		
			JMenuItem menuReceiver = new JMenuItem("Add Receiver");
			menuReceiver.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			mnActions.add(menuReceiver);
			
			menuReceiver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame2.setVisible(true);
					frame1.dispose();
					frameTrans.dispose();
					frame3.dispose();
					frameSmartBuy.dispose();
					frame4.dispose();
					frameCars.dispose();
					frame5.dispose();
					frameTwice.dispose();
					frameReceivers.dispose();
					frame6.dispose();
					frame7.dispose();
					frame8.dispose();
					frameCoordinators.dispose();
					clock.setVisible(false);
					label2.setVisible(true);
					label.setVisible(false);
					frameDrivers.dispose();
					frameTrucks.dispose();
					frameDV.dispose();
					frameRoute.dispose();
					frameCarRoute.dispose();

				}
			});
		
		JMenuItem mntmAddCoordinator = new JMenuItem("Add Coordinator");
		mntmAddCoordinator.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(mntmAddCoordinator);
		
		JMenuItem addItemtoParcelMenu = new JMenuItem("Add Item to Parcel");
		addItemtoParcelMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(addItemtoParcelMenu);
		
		JMenuItem mntmAddDriverTo = new JMenuItem("Add driver to Vehicle");
		mntmAddDriverTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameDV.setVisible(true);
				frame2.dispose();
				frame1.dispose();
				frameTrans.dispose();
				frame3.dispose();
				frameSmartBuy.dispose();
				frame4.dispose();
				frameCars.dispose();
				frame5.dispose();
				frameTwice.dispose();
				frameReceivers.dispose();
				frame6.dispose();
				frame7.dispose();
				frame8.dispose();
				frameCarRoute.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				label2.setVisible(true);
				label.setVisible(false);
				frameDrivers.dispose();
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		mntmAddDriverTo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(mntmAddDriverTo);
		
		JMenuItem mntmAddRoute = new JMenuItem("Add Route to truck");
		mntmAddRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameRoute.setVisible(true);
				frameDV.dispose();
				frame2.dispose();
				frame1.dispose();
				frameRoute.getRefresh().doClick();
				frame3.dispose();
				frameSmartBuy.dispose();
				frame4.dispose();
				frameCars.dispose();
				frame5.dispose();
				frameTrans.dispose();
				frameTwice.dispose();
				frameReceivers.dispose();
				frame6.dispose();
				frame7.dispose();
				frame8.dispose();
				frameCarRoute.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				label2.setVisible(true);
				label.setVisible(false);
				frameDrivers.dispose();
				frameTrucks.dispose();
				
			}
		});
		mntmAddRoute.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(mntmAddRoute);
		
		JMenuItem mntmAddRouteTo = new JMenuItem("Add Route to Car");
		mntmAddRouteTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameCoordinators.dispose();
				frame1.dispose();
				frame2.dispose();
				frame3.dispose();
				frame4.dispose();
				frameCars.dispose();
				frameSmartBuy.dispose();
				frame5.dispose();
				frameTrans.dispose();
				frameDV.dispose();
				frame7.dispose();
				frameTrucks.dispose();
				frame8.dispose();
				frame6.dispose();
				frameCarRoute.setVisible(true);
				clock.setVisible(false);
				frameReceivers.dispose();
				frameRoute.dispose();
				frameTwice.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameDrivers.dispose();
			}
		});
		mntmAddRouteTo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnActions.add(mntmAddRouteTo);
		
		JMenu mnShow = new JMenu("Show");
		mnShow.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnShow);
		
	
		mntmAllReceivers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnShow.add(mntmAllReceivers);
		
		JMenuItem mntmAllCoordinators = new JMenuItem("All Coordinators");
		mntmAllCoordinators.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCoordinators.setVisible(true);
				frame1.dispose();
				frame2.dispose();
				frame3.dispose();
				frame4.dispose();
				frameCars.dispose();
				frameSmartBuy.dispose();
				frame5.dispose();
				frameDV.dispose();
				frame7.dispose();
				frameTrans.dispose();
				frameTrucks.dispose();
				frame8.dispose();
				frame6.dispose();
				frameCarRoute.dispose();
				clock.setVisible(false);
				frameReceivers.dispose();
				frameRoute.dispose();
				frameTwice.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameDrivers.dispose();
			}
		});
		mntmAllCoordinators.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnShow.add(mntmAllCoordinators);
		
		JMenuItem mntmAllDrivers = new JMenuItem("All Drivers");
		mntmAllDrivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameDrivers.setVisible(true);
				frameCoordinators.dispose();
				frame1.dispose();
				frameTwice.dispose();
				frame2.dispose();
				frameSmartBuy.dispose();
				frame3.dispose();
				frame4.dispose();
				frameDV.dispose();
				frame5.dispose();
				frameCars.dispose();
				frame7.dispose();
				frameTrans.dispose();
				frame8.dispose();
				frameCarRoute.dispose();
				frame6.dispose();
				frameTrucks.dispose();
				clock.setVisible(false);
				frameReceivers.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameRoute.dispose();
			}
		});
		mntmAllDrivers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnShow.add(mntmAllDrivers);
		
		JMenuItem mntmAllVehicles = new JMenuItem("All Trucks");
		mntmAllVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame6.dispose();
				frame1.dispose();
				frame2.dispose();
				frame3.dispose();
				frameTwice.dispose();
				frameDrivers.dispose();
				frameTrans.dispose();
				frame4.dispose();
				frame5.dispose();
				frameSmartBuy.dispose();
				frame7.dispose();
				frameCars.dispose();
				frameDV.dispose();
				frame8.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frameCarRoute.dispose();
				frameReceivers.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameTrucks.setVisible(true);
				frameRoute.dispose();
			}
		});
		mntmAllVehicles.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnShow.add(mntmAllVehicles);
		
		JMenuItem mntmAllCars = new JMenuItem("All Cars");
		mntmAllCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCars.setVisible(true);
				frame6.dispose();
				frame1.dispose();
				frame2.dispose();
				frame3.dispose();
				frameTrans.dispose();
				frameDV.dispose();
				frameDrivers.dispose();
				frameSmartBuy.dispose();
				frame4.dispose();
				frame5.dispose();
				frameCarRoute.dispose();
				frame7.dispose();
				frame8.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frameReceivers.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameTrucks.dispose();
				frameRoute.dispose();
				frameTwice.dispose();
			}
		});
		mntmAllCars.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnShow.add(mntmAllCars);
		
		JMenu mnMore = new JMenu("More");
		mnMore.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnMore);
		
		JMenuItem mntmSmartbuy = new JMenuItem("Smartbuy");
		mntmSmartbuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameSmartBuy.setVisible(true);
				frame6.dispose();
				frame1.dispose();
				frameCars.dispose();
				frame2.dispose();
				frameDV.dispose();
				frame3.dispose();
				frameCarRoute.dispose();
				frameDrivers.dispose();
				frameTrans.dispose();
				frame4.dispose();
				frame5.dispose();
				frame7.dispose();
				frame8.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frameReceivers.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameTrucks.dispose();
				frameTwice.dispose();
				frameRoute.dispose();

			}
		});
		mntmSmartbuy.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMore.add(mntmSmartbuy);
		
		JMenuItem mntmTwiceAtWarehouse = new JMenuItem("Twice at Warehouse");
		mntmTwiceAtWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frameTwice.setVisible(true);
			frame6.dispose();
			frame1.dispose();
			frameCarRoute.dispose();
			frameCars.dispose();
			frame2.dispose();
			frame3.dispose();
			frameSmartBuy.dispose();
			frameDrivers.dispose();
			frameTrans.dispose();
			frame4.dispose();
			frameDV.dispose();
			frame5.dispose();
			frame7.dispose();
			frame8.dispose();
			frameCoordinators.dispose();
			clock.setVisible(false);
			frameReceivers.dispose();
			label2.setVisible(true);
			label.setVisible(false);
			frameTrucks.dispose();
			frameRoute.dispose();
			}
		});
		mntmTwiceAtWarehouse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMore.add(mntmTwiceAtWarehouse);
		
		JMenuItem mntmTransactions = new JMenuItem("Transactions");
		mntmTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameTrans.setVisible(true);
				label2.setVisible(true);
				label.setVisible(false);
				frame1.dispose();
				frame2.dispose();
				frame3.dispose();
				frame5.dispose();
				frameSmartBuy.dispose();
				frameDV.dispose();
				frameCars.dispose();
				frameReceivers.dispose();
				frameTwice.dispose();
				frame6.dispose();
				frameDrivers.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frame4.dispose();
				frame7.dispose();
				frameCarRoute.dispose();
				frame8.dispose();
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		mntmTransactions.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMore.add(mntmTransactions);
	
		
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/delivery2-512.png"));
		this.setIconImage(logo.getImage());
		getContentPane().setLayout(null);
		
		
		mntmAddCoordinator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame6.setVisible(true);
				frame1.dispose();
				frameCarRoute.dispose();
				frameCars.dispose();
				frame2.dispose();
				frame3.dispose();
				frameSmartBuy.dispose();
				frameDrivers.dispose();
				frameTrans.dispose();
				frame4.dispose();
				frame5.dispose();
				frame7.dispose();
				frame8.dispose();
				frameCoordinators.dispose();
				frameTwice.dispose();
				frameDV.dispose();
				clock.setVisible(false);
				frameReceivers.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		vehicleButtonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(true);
				frame2.dispose();
				frameTwice.dispose();
				frame3.dispose();
				frameTrans.dispose();
				frameCars.dispose();
				frame4.dispose();
				frame5.dispose();
				frameCarRoute.dispose();
				clock.setVisible(false);
				frame6.dispose();
				frameReceivers.dispose();
				frame7.dispose();
				frame8.dispose();
				frameDV.dispose();
				frameSmartBuy.dispose();
				frameCoordinators.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameDrivers.dispose();
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		
		menuAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3.setVisible(true);
				frame1.dispose();
				frame2.dispose();		
				frameCars.dispose();
				frameSmartBuy.dispose();
				frameDV.dispose();
				frame4.dispose();
				frameTwice.dispose();
				frame5.dispose();
				frame6.dispose();
				frameCarRoute.dispose();
				frameDrivers.dispose();
				frameTrans.dispose();
				frameCoordinators.dispose();
				frame7.dispose();
				clock.setVisible(false);
				frame8.dispose();
				frameReceivers.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		menuAddParcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame4.setVisible(true);
				frame3.dispose();
				frame2.dispose();
				frame1.dispose();
				frame5.dispose();
				frameCars.dispose();
				frameDrivers.dispose();
				frame6.dispose();
				frameDV.dispose();
				frameTrans.dispose();
				frame7.dispose();
				frameCarRoute.dispose();
				frame8.dispose();
				frameTwice.dispose();
				frameSmartBuy.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frameReceivers.dispose();
				frameTrucks.dispose();
				label2.setVisible(true);
				label.setVisible(false);
				frameRoute.dispose();
			}
		});
		mntmAddWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame7.setVisible(true);
				frame3.dispose();
				frame2.dispose();
				frameDV.dispose();
				frameTrans.dispose();
				frame1.dispose();
				frameDrivers.dispose();
				frame5.dispose();
				frameCars.dispose();
				frame6.dispose();
				frame4.dispose();
				frameCoordinators.dispose();
				frameReceivers.dispose();
				frame8.dispose();
				frameCarRoute.dispose();
				frameSmartBuy.dispose();
				clock.setVisible(false);
				label2.setVisible(true);
				label.setVisible(false);
				frameTwice.dispose();
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		addItemtoParcelMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame8.setVisible(true);
				frame3.dispose();
				frame2.dispose();	
				frameSmartBuy.dispose();
				frame8.refresh().doClick();
				frameDrivers.dispose();
				frame1.dispose();
				frameCars.dispose();
				frame5.dispose();
				frameTrans.dispose();
				frameCarRoute.dispose();
				frameDV.dispose();
				frame6.dispose();
				frame4.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frame7.dispose();
				label2.setVisible(true);
				frameTwice.dispose();
				label.setVisible(false);
				frameReceivers.dispose();
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		mntmAllReceivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameReceivers.setVisible(true);
				label2.setVisible(true);
				label.setVisible(false);
				frame1.dispose();
				frame2.dispose();
				frame3.dispose();
				frame5.dispose();
				frameSmartBuy.dispose();
				frameDV.dispose();
				frameCars.dispose();
				frameTrans.dispose();
				frameTwice.dispose();
				frame6.dispose();
				frameDrivers.dispose();
				frameCoordinators.dispose();
				clock.setVisible(false);
				frame4.dispose();
				frame7.dispose();
				frameCarRoute.dispose();
				frame8.dispose();
				frameTrucks.dispose();
				frameRoute.dispose();
			}
		});
		JLabel background = new JLabel();
		getContentPane().add(background);
		background.setVisible(true);
		background.setIcon(new ImageIcon(getClass().getResource("/theBackground.png")));
		background.setBounds(0, 0, 1920, 980);
		
//		this.addWindowListener(new WindowAdapter() {
//			
//		    public void windowOpened(WindowEvent we) {
//		        System.out.println("this window was opened for the first time");
//				
//		  //      File tempFile2 = new File("src/SerializationDemo/Del4.ser");
//		 //   	boolean exists2 = tempFile2.exists();
//		        
//		        
//		        list = demo.deserialize("src/SerializationDemo/Del2.ser");
////		        System.out.println("the list De serialized is : "+list);
//		  
//		        sys.Setter(list);
//		 
//		        
//		    }
//
//		  
//		});
		
		
		
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
//            setOpaque( false ) ;
            clock.setHorizontalAlignment(JLabel.CENTER);
            clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 72f));
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
