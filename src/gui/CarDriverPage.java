package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conotroller.SysData;
import InternalFrames.CarDriverView;
import Model.Car;
import Model.Driver;
import Model.Truck;
import Model.Vehicle;
import SerializationDemo.Delivery;
import SerializationDemo.Serialization;

public class CarDriverPage extends JFrame {

	private JPanel contentPane;
	private Driver driver = null;
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
					CarDriverPage frame = new CarDriverPage();
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
	public CarDriverPage() {
		setTitle("Driver page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 695);
		
	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login loginPage = new login();
				loginPage.getJframe().setVisible(true);
				dispose();
			}
		});
		
		
		mntmLogOut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnNewMenu.add(mntmLogOut);
		
		JLabel label_1 = new JLabel("");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Sitka Small", Font.PLAIN, 35));
		label_1.setBounds(32, 182, 970, 61);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem.addActionListener(new ActionListener() {
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
				mntmNewMenuItem.doClick();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Truck truck = null;
		Car car = null;
		if(login.idUser  != null)
			 driver = SysData.getInstance().getAllDriversMap().get(login.idUser);
		if(driver != null) {
		for(Map.Entry<String, Vehicle> ridenV : SysData.getInstance().getVehclesMap().entrySet()) {
			if(ridenV.getValue().isInUse())	
				if(ridenV.getValue() instanceof Car) {
					
					if(ridenV.getValue().getDriver().equals(driver)) {
						car = (Car)ridenV.getValue(); 
					}
			}
			else if(ridenV.getValue() instanceof Truck) {
				if(ridenV.getValue().getDriver().equals(driver)) {
					truck = (Truck)ridenV.getValue();
				}
			}
		}
		}
			JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_1.setBounds(401, 55, 599, 65);
		contentPane.add(lblNewLabel_1);
		
		if(driver!=null) {
			lblNewLabel_1.setText(driver.getFirstName() + " " + driver.getSurname());
		}
//		
//		ArrayList<Vehicle> vv = new ArrayList<>(SysData.getInstance().getVehclesMap().values());
//		for(Vehicle v : vv) {
//			if(v.isInUse()) {
//			if(v instanceof Car) {
//				Car car =(Car) v ;
//			//	System.out.println(car.getDriver().getId()+"---"+driver.getId());
//				if(car.getDriver().getId()==driver.getId() ) {
//					tempCar2 = car ;
//					break ;
//				}
//			}
//			}
//		}
		
		JLabel label = new JLabel("");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 41));
		label.setBounds(36, 213, 742, 76);
		contentPane.add(label);
		
		CarDriverView view = new CarDriverView(driver);
		this.add(view);
		view.setBounds(0, 0, 800, 600);
		if(car == null || car.getParcels().isEmpty()) {
			view.setVisible(false);
			label.setText("You have no jobs for today ,");
		}else if(car != null && !car.getParcels().isEmpty()) {
			view.setVisible(true);
		}
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		lblNewLabel.setBounds(36, 44, 333, 76);
		contentPane.add(lblNewLabel);
		
		JLabel background = new JLabel("");
		background.setBounds(79, 178, 56, 16);
		getContentPane().add(background);
		background.setIcon(new ImageIcon(getClass().getResource("/blue.jpg")) );
		background.setBounds(0, 0, 1057, 648);
		
		
		
	
		
		
		
		
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
//		
		
//		
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
//		
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
}
