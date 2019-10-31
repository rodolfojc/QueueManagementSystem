package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class GuiView extends JFrame {

	//ATTRIBUTES
	JPanel panel;
	JMenuBar menu;
	JTable[] myTable;

	// CONSTRUCTOR
	public GuiView(String name, int width, int height, boolean Resizable) {

		// SETTING UP THE FRAME
		this.setVisible(true); // VISIBLE
		this.setSize(width, height); // SIZE
		this.setTitle(name); // TITLE
		this.panel = new JPanel(); // PANEL INSTANTIATION
		this.myTable = new JTable[7];
		this.add(panel); // ADDING GLOBAL MAIN PANEL TO THE FRAME
		this.menu = new JMenuBar(); // MENU INSTANTIATION
		this.setJMenuBar(menu); // ADDING MENU
		this.setResizable(Resizable); // RESIZABLE - WINDOW
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // SETTING CLOSE PROGRAM WHEN THE FRAME IS CLOSED
		this.setLocationRelativeTo(null); // SETTING FRAME IN CENTER LOCATION ON THE SCREEN

	}

	// THESE METHODS HAVE BEEN CREATED TO ADD COMPONENTS TO PANELS DIRECTLY
	// TO SET LAYOUTS TO PANELS, AND TO RETURN OBJECTS THAT ARE CREATED

	// SET GRIDLAYOUT TO A PANEL
	public void setGrid(int a, int b, JPanel panel) {

		GridLayout grid = new GridLayout(a, b);
		panel.setLayout(grid);
	}

	// SET BORDERLAYOUT TO A PANEL
	public void setBorder(JPanel panel) {

		BorderLayout border = new BorderLayout();
		panel.setLayout(border);
	}

	// SET BOXLAYOUT TO A PANEL
	public void setBox(JPanel panel, int a) {

		BoxLayout box = new BoxLayout(panel, a);
		panel.setLayout(box);

	}

	// CREATE JMENU, ADD TO MAIN MENU
	public JMenu addMenu(String name) {

		JMenu myMenu = new JMenu(name);
		this.menu.add(myMenu);
		return myMenu;

	}

	// CREATE JMENUITEM, ADD TO JMENU
	public JMenuItem addMenuItem(JMenu myMenu, String name) {

		JMenuItem myMenuItem = new JMenuItem(name);
		myMenu.add(myMenuItem);
		return myMenuItem;
	}

	// CREATE A JTEXTFIELD WITH SIZE AND ADD TO A PANEL
	public JTextField addTextField(int a, JPanel panel) {

		JTextField myText = new JTextField(a);
		myText.setFont(new Font("Tahoma", Font.BOLD, 20));
		//myText.setBackground(new Color(59, 89, 182));
		myText.setForeground(Color.BLACK);
		panel.add(myText);
		return myText;

	}

	// CREATE A JPASSWORDFIELD WITH SIZE AND ADD TO A PANEL
	public JPasswordField addPassField(int a, JPanel panel) {

		JPasswordField myPass = new JPasswordField(a);
		panel.add(myPass);
		return myPass;
	}

	// CREATE A JBUTTON WITH NAME, SET COLOR, AND ADD TO A PANEL
	public JButton addButton(String name, JPanel panel) {

		JButton myButton = new JButton(name);
		panel.add(myButton);
		return myButton;
	}

	// CREATE A BUTTON WITN NAME, PANEL, ACTION COMMAND Y CONTROLLER
	public JButton addButtonAll(String name, String command, JPanel panel, ActionListener controller) {

		JButton myButton = new JButton(name);
		myButton.setBackground(new Color(59, 89, 182));
		myButton.setForeground(Color.WHITE);
		myButton.setFocusPainted(false);
		myButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		myButton.setActionCommand(command);
		myButton.addActionListener(controller);
		panel.add(myButton);
		return myButton;

	}

	// CREATE A JLABEL WITH TEXT AND ADD TO A PANEL
	public JLabel addLabel(String text, JPanel panel) {

		JLabel myLabel = new JLabel(text);
		myLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		myLabel.setForeground(Color.BLACK);
		panel.add(myLabel);
		return myLabel;
	}

	// CREATE A JCOMBOBOX WITH DATA OPTIONS AND ADD TO A PANEL
	public JComboBox addComboB(String[] options, JPanel panel) {

		JComboBox myComboBox = new JComboBox(options);
		myComboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		myComboBox.setBackground(Color.WHITE);
		//myComboBox.setBackground(new Color(59, 89, 182));
		panel.add(myComboBox);
		return myComboBox;
	}

	// CREATE A JCALENDAR - JDATECHOOSER WITH SIZE AND ADD TO A PANEL
	public JDateChooser addCalen(JPanel panel) {
		JDateChooser myDateChooser = new JDateChooser();
		myDateChooser.setDateFormatString("dd/MM/yyyy");
		myDateChooser.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(myDateChooser);
		return myDateChooser;
	}

	// CREATE A JSCROOLPANE WITH INDEX FOR MAIN TABLES, DATA (GETTING FROM
	// DATABASE), COLUMNS NAMES, ADD TO A PANEL, AND TITLE FOR BORDER.
	// ALSO IT CREATES A BORDER WITH A TITLE.
	public JScrollPane addTableS(int tableNum, String[][] data, String[] columnsName, JPanel panel, String title) {

		myTable[tableNum] = new JTable(data, columnsName);
		myTable[tableNum].setSize(100, 200);
		myTable[tableNum].setFont(new Font("Tahoma", Font.BOLD, 20));
		myTable[tableNum].setRowHeight(30);
		myTable[tableNum].getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		JScrollPane myScroll = new JScrollPane(myTable[tableNum]);
		myScroll.setFont(new Font("Tahoma", Font.BOLD, 20));

		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title, TitledBorder.CENTER,
				TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 20), Color.BLACK));

		panel.add(myScroll);
		return myScroll;

	}
	
//	public void showJOptPane(Component view, String message, String title, String type) {
//					
//		JOptionPane jopt = new JOptionPane();
//	    JLabel labels = new JLabel(message);
//	    labels.setFont(new Font("SERIF", Font.PLAIN, 20));
//	    jopt.showMessageDialog(view, labels, "Results", JOptionPane.PLAIN_MESSAGE );
//		
//		
//	}
	
	public JLabel addLabelOpt(String label) {
		
		JLabel myLabel = new JLabel(label);
		myLabel.setFont(new Font("SERIF", Font.BOLD, 20));
		return myLabel;
	
	}

}