import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private Object flightData;
	//Entire pane to hold loginPanel and other windows
	private Container pane = getContentPane();
	//GraphicPanel = new JLabel(new ImageIcon("C:\\Users\\bowesj\\Documents\\Java Workspace\\Book of Life\\src\\GFX\\dancing-banana.gif"));

	//Layouts used
	private BorderLayout paneLayout = new BorderLayout();
	private GridLayout loginLayout = new GridLayout(2,3);
	private GridLayout buttonLayout = new GridLayout(2,3);
	//Panels used to hold content
	private JPanel loginPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	//Content for loginPanel
	private JButton login = new JButton("Login");
	private JTextField username = new JTextField(20);
	private JTextField password = new JTextField(20);
	//Content for buttonPanel
	private JTextField search = new JTextField(20);
	private JButton accountButton = new JButton("Account Information");
	private JButton flightButton = new JButton("Find Flights");
	private JButton searchButton = new JButton("Search");
	//Content for Account Information
	private String[] columnNames = {"Flight Number", "Departure Date", "Arrival Date", "Origin", "Destination" };
	private Object[][] data = WentworthAirDatabase.getPlaneData();
	private JTextArea accountArea = new JTextArea(100,300);
	//Content for flights
	private MyModel flightTableModel = new MyModel(data, columnNames);
	private JTable flightTable = new JTable(flightTableModel);
	private JScrollPane scrollPane = new JScrollPane(flightTable);
	//Event handler
	private EventHandler event = new EventHandler();	
	//Labels used
	private Label userLabel = new Label("Username:");
	private Label passLabel = new Label("Password:");
	private Label nullLabel = new Label(" ");
	
	public MainWindow(){
		//MainWindow formatting
		setSize(WIDTH,HEIGHT);
		addWindowListener(new WindowDestroyer());
		setTitle("Wentworth Air Access");	
		setResizable(false);
		//pane formatting
		pane.setLayout(paneLayout);//
		pane.add(new JLabel(new ImageIcon("C:\\Users\\bowesj\\Documents\\Java Workspace\\WentworthAir\\GFX\\WentworthAir.png")));
		//flightTable formatting
		//Account area formatting
		accountArea.setEditable(false);
		accountArea.setWrapStyleWord(true);
		accountArea.setText("   Test   |   Column 1   |   Column 2   |" + '\n' + 
				            "   Test2  |   Column 1   |   Column 2   |");
		//loginPanel formatting
		loginPanel.setBackground(Color.LIGHT_GRAY);
		loginPanel.setLayout(loginLayout);
		//login button action listener
		login.addActionListener(event);
		//Add items to loginPanel
		loginPanel.add(userLabel);
		loginPanel.add(username);
		loginPanel.add(login);
		loginPanel.add(passLabel);
		loginPanel.add(password);
		loginPanel.add(nullLabel);
		//buttonPanel formatting
		buttonPanel.setLayout(buttonLayout);
		//buttonPanel action listeners
		accountButton.addActionListener(event);
		flightButton.addActionListener(event);
		//Add items to buttonPanel
		buttonPanel.add(accountButton);
		buttonPanel.add(flightButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(search);
		//Add items to tablePanel
		
		//adding components to pane
		pane.add(loginPanel, BorderLayout.SOUTH);
		
	}
	
	private class EventHandler implements ActionListener{
		private int previous = 0;
		public void actionPerformed(ActionEvent Event){
			String optionMessage = "";
			HashMap<String,String> emailList = new HashMap<String,String>();
			emailList = WentworthAirDatabase.UsersAndPassword;
			if(Event.getSource()==login){
				//if(emailList.containsKey(username.getText())){
					//if(emailList.get(username.getText()).equals(password.getText())){
						previous = 0;
						optionMessage = "Login";
						pane.remove(loginPanel);
						
						pane.add(buttonPanel,BorderLayout.SOUTH);
						pane.setVisible(false);
						pane.setVisible(true);
						JOptionPane.showMessageDialog(null, optionMessage);
					//}
				//}
			}
			if(Event.getSource() == accountButton){
				optionMessage = "Getting account information...";
				if(previous == 2){
					pane.remove(scrollPane);
					pane.setVisible(false);
					pane.setVisible(true);
				}
				pane.add(accountArea, BorderLayout.PAGE_START);
				JOptionPane.showMessageDialog(null, optionMessage);
				pane.setVisible(false);
				pane.setVisible(true);
				previous = 1;
			}
			if(Event.getSource() == flightButton){
				optionMessage = "Getting available flights...";
				if(previous == 1){
					pane.remove(accountArea);
					pane.setVisible(false);
					pane.setVisible(true);
				}
				pane.add(scrollPane, BorderLayout.PAGE_START);
				JOptionPane.showMessageDialog(null, optionMessage);
				pane.setVisible(false);
				pane.setVisible(true);
				previous = 2;
			}
			if(Event.getSource() == searchButton){
				
			}
			return;
		}
	}
}