package InternalFrames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class UNLOAD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblFailedToUnload;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UNLOAD dialog = new UNLOAD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UNLOAD() {
		setBounds(100, 100, 400, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("Unload ");
		 btnNewButton = new JButton("UNLOAD ALL");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		btnNewButton.setBounds(73, 442, 241, 48);
		contentPanel.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);;
		scrollPane.setBounds(73, 40, 240, 389);
		contentPanel.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ParcelID"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		lblFailedToUnload = new JLabel("Failed to unload !");
        lblFailedToUnload.setForeground(Color.RED);
        lblFailedToUnload.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFailedToUnload.setBounds(73, 13, 271, 26);
        contentPanel.add(lblFailedToUnload);
        lblFailedToUnload.setVisible(false);
		setLocationRelativeTo(null);
		JLabel texture = new JLabel();
		texture.setIcon(new ImageIcon(getClass().getResource("/blue.jpg")));
		contentPanel.add(texture);
		texture.setSize(400,550);
        table.setCellSelectionEnabled(false);
        
        
        this.setIconImage(new ImageIcon(getClass().getResource("/delivery2-512.png")).getImage());
		
		texture.setVisible(true);
	}
	
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JLabel getFailedLabel() {
		return lblFailedToUnload;
	}
}
