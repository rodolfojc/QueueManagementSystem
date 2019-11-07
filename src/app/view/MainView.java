package app.view;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import app.controller.MainViewController;

public class MainView extends GuiView {
	
	private ListSelectionModel myTableModel;
	private MainViewController controller;
	private JTextField passport;
	private JTextField nameS;
	private JTextField surnameS;
	private JDateChooser dateOfArrival;
	private JComboBox priority;
	private String[] priorities = {"High", "Medium", "Low"};
	private String[][] data;
	private int selectedRow;

	public MainView(String name, int width, int height, boolean Resizable) {
		super(name, width, height, Resizable);
		this.controller = new MainViewController(this);
		this.data = new String[100][100];
		this.setView();
	}
	
	
			
	public String getData(int a, int b) {
		return data[a][b];
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public JTextField getPassport() {
		return passport;
	}

	public JTextField getNameS() {
		return nameS;
	}

	public JTextField getSurnameS() {
		return surnameS;
	}

	public JDateChooser getDateOfArrival() {
		this.dateOfArrival.setDateFormatString("MM/yy/DD");
		return dateOfArrival;
	}

	public JComboBox getPriority() {
		return priority;
	}
		
	public String[] getPriorities() {
		return priorities;
	}
	
	public void setData(String[][] data) {
		this.data = Arrays.copyOf(data, data.length);
	}
	
	public ListSelectionModel getMyTableModel() {
		return myTableModel;
	}

	public void setMyTableModel(ListSelectionModel myTableModel) {
		this.myTableModel = myTableModel;
	}


	public void setView() {
				
		//this.setGrid(1, 3, this.panel);
		this.setBox(this.panel, 2);
		
		//HEADER
		//JPanel title = new JPanel();
		//this.addLabel("Immigration - Queue Management System", title);
				
		
		// PERSONAL DETAILS PANEL
		JPanel personalInfo = new JPanel();
		this.setGrid(14, 1, personalInfo);
		this.addLabel("Personal Details", personalInfo);
		this.addLabel(" ", personalInfo);
		this.addLabel("Passport Number", personalInfo);
		this.passport = this.addTextField(20, personalInfo);
		this.addLabel("Name(s) - As on the passport", personalInfo);
		this.nameS = this.addTextField(20, personalInfo);
		this.addLabel("Surname(s) - As on the passport", personalInfo);
		this.surnameS = this.addTextField(20, personalInfo);
		this.addLabel("Date of Arrival", personalInfo);
		this.dateOfArrival = this.addCalen(personalInfo);
		this.addLabel("Priority level", personalInfo);
		this.priority = this.addComboB(this.priorities, personalInfo);
		this.addLabel(" ", personalInfo);
		this.addButtonAll("Submit", "Add", personalInfo, this.controller);		
		personalInfo.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
		
		// QUEUE TABLE
		JPanel queueTable = new JPanel();
		//String[][] myTable = new String[100][100];
		String[] columns = {"ID", "Name", "Surname", "Priority"};
		this.setGrid(1, 1, queueTable);
		JScrollPane myScrool = this.addTableS(0, this.data, columns, queueTable, "Queue First to Last");
		this.myTableModel = this.myTable[0].getSelectionModel();
		this.myTableModel.addListSelectionListener(this.controller);
		//myScrool.setPreferredSize(new Dimension(800, 50));
		
		// ACTIONS BUTTONS
		JPanel actionBtns = new JPanel();
		//this.setBox(actionBtns, 1);
		this.setGrid(3, 1, actionBtns);
		this.addButtonAll("Delete", "Delete", actionBtns, this.controller);
		this.addButtonAll("Update Inf", "Update", actionBtns, this.controller);
		this.addButtonAll("Cut Queue", "Cut", actionBtns, this.controller);
		actionBtns.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
		
		//this.panel.add(title);
		this.panel.add(personalInfo);
		this.panel.add(queueTable);
		this.panel.add(actionBtns);
		
		this.repaint();
		this.validate();
	}
	
	public void updateView() {
		
		this.panel.removeAll();
		this.setView();
	}
		
}
