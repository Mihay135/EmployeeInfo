package employeeForm;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import employeeDatabase.EmployeeRecordsDatabase;
import jValidators.EmailAddressValidator;
import jValidators.MaxLengthTextDocument;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Rectangle;

public class EmployeeForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhoneNumber;
	private JTextField txtEmail;
	private JTextField txtAnnualSalary;
	private JTable table;
	private JTextField txtCity;
	private EmployeeRecordsDatabase database;
	private Connection conn;
	private PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm frame = new EmployeeForm();
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
	public EmployeeForm() {
		setResizable(false);
		database = new EmployeeRecordsDatabase();
		conn = database.connectToDB();
		database.createTable(conn, "records");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				database.closeConnection(conn);
			}
		});
		
		setTitle("Employee Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 475);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseReleased(e);
				contentPane.requestFocusInWindow();
				txtAnnualSalary.setEditable(true);
				txtFirstName.setEditable(true);
				txtLastName.setEditable(true);
				txtPhoneNumber.setEditable(true);
				txtEmail.setEditable(true);
				txtCity.setEditable(true);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {127, 39, 107, 39, 30, 86, 39, 120, 39, 129, 30, 30, 126};
		gbl_contentPane.rowHeights = new int[]{73, 298, 45, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridwidth = 12;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmployeeInformationSystem = new JLabel("Employee Information System");
		lblEmployeeInformationSystem.setToolTipText("Simple System to Store, Update and Delete Empolyee Information Into a Local Database (Using SQLite)");
		lblEmployeeInformationSystem.setForeground(new Color(79, 79, 255));
		lblEmployeeInformationSystem.setBounds(54, 0, 800, 73);
		lblEmployeeInformationSystem.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblEmployeeInformationSystem);
		lblEmployeeInformationSystem.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		panel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 5;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(null);
		
		MaxLengthTextDocument maxLengthFname = new MaxLengthTextDocument();
		maxLengthFname.setMaxChars(255);
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(0, 0, 255));
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblFirstName.setBounds(10, 27, 121, 28);
		panel.add(lblFirstName);
		txtFirstName = new JTextField();
		txtFirstName.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFirstName.setBounds(130, 37, 182, 18);
		txtFirstName.setColumns(10);
		txtFirstName.setDocument(maxLengthFname);
		txtFirstName.requestFocusInWindow();
		panel.add(txtFirstName);
		
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setForeground(Color.BLUE);
		lblLastName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblLastName.setBounds(10, 73, 121, 28);
		panel.add(lblLastName);
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtLastName.setColumns(10);
		txtLastName.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtLastName.setBounds(130, 83, 182, 18);
		MaxLengthTextDocument maxLengthLName = new MaxLengthTextDocument();
		maxLengthLName.setMaxChars(255);
		txtLastName.setDocument(maxLengthLName);;
		panel.add(txtLastName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumber.setForeground(Color.BLUE);
		lblPhoneNumber.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPhoneNumber.setBounds(10, 118, 121, 28);
		panel.add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		Color defaultColor = txtPhoneNumber.getBackground();
		txtPhoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if((key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9) ||
					(key >= KeyEvent.VK_NUMPAD0 && key <= KeyEvent.VK_NUMPAD9) || 
					(key == KeyEvent.VK_BACK_SPACE) ||
					(key == KeyEvent.VK_DELETE) || 
					(key == KeyEvent.VK_CANCEL) ||
					(key == KeyEvent.VK_LEFT) ||
					(key == KeyEvent.VK_RIGHT) ||
					(key == KeyEvent.VK_SHIFT)
				){
					txtPhoneNumber.setEditable(true);
					txtPhoneNumber.setBackground(defaultColor);
				}else {
					txtPhoneNumber.setEditable(false);
					txtPhoneNumber.setBackground(Color.RED);
				}
			}
		});
		MaxLengthTextDocument maxLengthPhone = new MaxLengthTextDocument();
		maxLengthPhone.setMaxChars(11);
		txtPhoneNumber.setDocument(maxLengthPhone);
		txtPhoneNumber.setToolTipText("Insert maxiumum of 11 digits for phone number");
		txtPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtPhoneNumber.setBounds(130, 128, 182, 18);
		panel.add(txtPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblEmail.setBounds(10, 166, 121, 28);
		panel.add(lblEmail);
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtEmail.setBounds(130, 175, 182, 18);
		MaxLengthTextDocument maxLengthEmail = new MaxLengthTextDocument();
		maxLengthEmail.setMaxChars(45);
		txtEmail.setDocument(maxLengthEmail);
		EmailAddressValidator validator = new EmailAddressValidator();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String emailAddress = txtEmail.getText();
				boolean isEmailValid = validator.isValidEmailAddress(emailAddress);
				if(isEmailValid 
						|| emailAddress.isEmpty()
						|| (e.getKeyChar() == '@' && !emailAddress.contains("@")) 
						|| (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && emailAddress.isEmpty())) {
					txtEmail.setBackground(defaultColor);
				}else {
					txtEmail.setBackground(Color.red);
				}
				
			}
		});
		panel.add(txtEmail);
		
		JLabel lblAnnualSalary = new JLabel("Annual Salary");
		lblAnnualSalary.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnnualSalary.setForeground(Color.BLUE);
		lblAnnualSalary.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblAnnualSalary.setBounds(10, 258, 121, 28);
		panel.add(lblAnnualSalary);
		txtAnnualSalary = new JTextField();
		txtAnnualSalary.setToolTipText("Insert Only The Amount without Currency");
		txtAnnualSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtAnnualSalary.setColumns(10);
		txtAnnualSalary.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtAnnualSalary.setBounds(130, 268, 182, 18);
		txtAnnualSalary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if((key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9) || 
						(key >= KeyEvent.VK_NUMPAD0 && key <= KeyEvent.VK_NUMPAD9) || 
						(key == KeyEvent.VK_BACK_SPACE) || 
						(key == KeyEvent.VK_PERIOD) || 
						(key == KeyEvent.VK_DELETE) || 
						(key == KeyEvent.VK_CANCEL) ||
						(key == KeyEvent.VK_LEFT) ||
						(key == KeyEvent.VK_RIGHT) ||
						(key == KeyEvent.VK_SHIFT)
				){
					txtAnnualSalary.setEditable(true);
					txtAnnualSalary.setBackground(defaultColor);
				}else {
					txtAnnualSalary.setEditable(false);
					txtAnnualSalary.setBackground(Color.RED);
				}
			}
		});
		
		MaxLengthTextDocument maxLengthSalary = new MaxLengthTextDocument();
		maxLengthSalary.setMaxChars(11);
		txtAnnualSalary.setDocument(maxLengthSalary);
		panel.add(txtAnnualSalary);
		
		MaxLengthTextDocument maxLengthCity = new MaxLengthTextDocument();
		maxLengthCity.setMaxChars(255);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setForeground(Color.BLUE);
		lblCity.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblCity.setBounds(10, 212, 121, 28);
		panel.add(lblCity);
		txtCity = new JTextField();
		txtCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCity.setColumns(10);
		txtCity.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtCity.setBounds(130, 222, 182, 18);
		txtCity.setDocument(maxLengthCity);
		panel.add(txtCity);
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				int id = Integer.parseInt((model.getValueAt(selectedIndex, 0).toString()));
				database.deleteFromRecordsTable(conn, id);
				
				txtAnnualSalary.setText("");
				txtCity.setText("");
				txtEmail.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtPhoneNumber.setText("");
				txtFirstName.requestFocus();
				
				panelTableUpdate();
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				int id = Integer.parseInt((model.getValueAt(selectedIndex, 0).toString()));
				String firstName, lastName, phone, email, city, salary;
				
				firstName = txtFirstName.getText();
				lastName = txtLastName.getText();
				phone = txtPhoneNumber.getText();
				email = txtEmail.getText();
				city = txtCity.getText();
				salary = txtAnnualSalary.getText();
	
				database.updateTableRecords(conn, firstName, lastName, phone, email, city, Double.valueOf(salary), id);
				database.displayDatabase(conn, "records");
				
				
				txtAnnualSalary.setText("");
				txtCity.setText("");
				txtEmail.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtPhoneNumber.setText("");
				txtFirstName.requestFocus();
				
				panelTableUpdate();
				}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(10,10,230,180),1,true));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(1, 1, 5, 1);
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.gridx = 5;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				txtFirstName.setText(model.getValueAt(selectedIndex, 1).toString());
				txtLastName.setText(model.getValueAt(selectedIndex, 2).toString());
				txtPhoneNumber.setText(model.getValueAt(selectedIndex, 3).toString());
				txtEmail.setText(model.getValueAt(selectedIndex, 4).toString());
				txtCity.setText(model.getValueAt(selectedIndex, 5).toString());
				txtAnnualSalary.setText(model.getValueAt(selectedIndex, 6).toString());
				
			}
		});
		table.setBounds(new Rectangle(2, 0, 0, 0));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(10,100,200,255));
		table.setBorder(new LineBorder(new Color(0, 0, 255,200), 1, true));
		JTableHeader tableHeader = table.getTableHeader();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "First Name", "Last Name", "Phone", "Email", "City", "Annual Salary \u20AC"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(66);
		table.getColumnModel().getColumn(2).setPreferredWidth(63);
		table.getColumnModel().getColumn(3).setPreferredWidth(66);
		table.getColumnModel().getColumn(4).setPreferredWidth(89);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(89);
		
		JButton btnSave = new JButton("Save");
		btnSave.setToolTipText("Save Current Information Into Database");
		btnSave.setBorderPainted(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName, lastName, phone, email, city, salary;
				
				firstName = txtFirstName.getText();
				lastName = txtLastName.getText();
				phone = txtPhoneNumber.getText();
				email = txtEmail.getText();
				city = txtCity.getText();
				salary = txtAnnualSalary.getText();
	
				if(!((firstName.equals("") && lastName.equals("") && phone.equals("") && email.equals("")) || salary.equals(""))) {
					database.insertIntoRecords(conn, firstName, lastName, phone, email, city, Double.valueOf(salary));
					database.displayDatabase(conn, "records");
					System.out.println(firstName);
					
				}else {
					System.out.println("Not enough fields filled");
				}
					
				
				
				
				txtAnnualSalary.setText("");
				txtCity.setText("");
				txtEmail.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtPhoneNumber.setText("");
				txtFirstName.requestFocus();
				
				panelTableUpdate();
				
			}
		});
		btnSave.setMargin(new Insets(4, 14, 4, 14));
		btnSave.setBorder(new LineBorder(new Color(79, 79, 255), 2, true));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnSave.setBackground(new Color(0, 0, 255));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 2;
		contentPane.add(btnSave, gbc_btnSave);
		btnEdit.setMargin(new Insets(4, 14, 4, 14));
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnEdit.setBorderPainted(false);
		btnEdit.setBorder(new LineBorder(new Color(79, 79, 255), 1, true));
		btnEdit.setBackground(Color.BLUE);
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 2;
		gbc_btnEdit.gridy = 2;
		contentPane.add(btnEdit, gbc_btnEdit);
		btnDelete.setMargin(new Insets(4, 14, 4, 14));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnDelete.setBorderPainted(false);
		btnDelete.setBorder(new LineBorder(new Color(79, 79, 255), 1, true));
		btnDelete.setBackground(Color.BLUE);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridwidth = 2;
		gbc_btnDelete.gridx = 4;
		gbc_btnDelete.gridy = 2;
		contentPane.add(btnDelete, gbc_btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				database.closeConnection(conn);
				System.exit(0);
			}
		});
		
		JButton btnPrintRecords = new JButton("Print");
		btnPrintRecords.setMargin(new Insets(4, 14, 4, 14));
		btnPrintRecords.setForeground(Color.WHITE);
		btnPrintRecords.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnPrintRecords.setBorderPainted(false);
		btnPrintRecords.setBorder(new LineBorder(new Color(79, 79, 255), 1, true));
		btnPrintRecords.setBackground(Color.BLUE);
		GridBagConstraints gbc_btnPrintRecords = new GridBagConstraints();
		gbc_btnPrintRecords.fill = GridBagConstraints.BOTH;
		gbc_btnPrintRecords.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrintRecords.gridx = 7;
		gbc_btnPrintRecords.gridy = 2;
		contentPane.add(btnPrintRecords, gbc_btnPrintRecords);
		btnExit.setMargin(new Insets(4, 14, 4, 14));
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnExit.setBorderPainted(false);
		btnExit.setBorder(new LineBorder(new Color(79, 79, 255), 1, true));
		btnExit.setBackground(Color.BLUE);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridwidth = 2;
		gbc_btnExit.fill = GridBagConstraints.BOTH;
		gbc_btnExit.gridx = 12;
		gbc_btnExit.gridy = 2;
		contentPane.add(btnExit, gbc_btnExit);
		
		tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tableHeader.setBackground(new Color(10,10,230,255));
		tableHeader.setBorder(new LineBorder(new Color(0,0,240,100), 1, true));
		
		panelTableUpdate();
	}

	private void panelTableUpdate() {
		int columnCount;
		try {
			String statement = "SELECT * FROM records ;";
			pst = conn.prepareStatement(statement);
			ResultSet rs = database.getResultSetFromQuery(pst);
			
			ResultSetMetaData RSMD = rs.getMetaData();
			columnCount = RSMD.getColumnCount();
			DefaultTableModel DFT = (DefaultTableModel) table.getModel();
			DFT.setRowCount(0);
			
			while (rs.next()) {
				Vector<Object> v2 = new Vector<Object>();
				for(int i = 1; i <= columnCount; i++) {
					v2.add(rs.getInt("id"));
					v2.add(rs.getString("first_Name"));
					v2.add(rs.getString("last_Name"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("email"));
					v2.add(rs.getString("city"));
					v2.add(rs.getDouble("annual_salary"));
				}
				DFT.addRow(v2);
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
}
