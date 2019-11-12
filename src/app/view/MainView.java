package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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
	private JTextField cut;
	private JTextField search;
	private JTextField boardTextFiled;
	private JLabel personalLabel;
	private JComboBox cutBox;

	public MainView(String name, int width, int height, boolean Resizable) {
		super(name, width, height, Resizable);
		this.controller = new MainViewController(this);
		this.data = new String[100][100];
		this.controller.populateQueueBy(25);
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
		//this.setBox(this.panel, 2);
		this.setBorder(this.panel);
		
		//HEADER
		//JPanel title = new JPanel();
		//this.addLabel("Immigration - Queue Management System", title);
				
		
		// PERSONAL DETAILS PANEL
		JPanel personalInfo = new JPanel();
		this.setGrid(14, 1, personalInfo);
		this.personalLabel = this.addLabel("Personal Details", personalInfo);
		this.personalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.personalLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		this.addLabel(" ", personalInfo);
		this.addLabel("Passport Number", personalInfo);
		this.passport = this.addTextField(20, personalInfo);
		this.addLabel("Name(s) - As on the passport", personalInfo);
		this.nameS = this.addTextField(20, personalInfo);
		this.addLabel("Surname(s) - As on the passport", personalInfo);
		this.surnameS = this.addTextField(20, personalInfo);
		this.addLabel("Date of Arrival", personalInfo);
		this.dateOfArrival = this.addCalen(personalInfo);
		Date calToday = new Date();
		this.dateOfArrival.setDate(calToday);
		Calendar calMinBirth = Calendar.getInstance();
		calMinBirth.add(Calendar.MONTH, -12);
		this.dateOfArrival.setMinSelectableDate(calMinBirth.getTime());
		this.dateOfArrival.setMaxSelectableDate(calToday);
		this.addLabel("Priority level", personalInfo);
		this.priority = this.addComboB(this.priorities, personalInfo);
		this.addLabel(" ", personalInfo);
		this.addButtonAll("Submit", "Add", personalInfo, this.controller);		
		personalInfo.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
		
		// QUEUE TABLE
		JPanel queueTable = new JPanel();
		//String[][] myTable = new String[100][100];
		this.setBorder(queueTable);
		
		
	//	this.setGrid(1, 1, boardPanel);
	//	this.boardTextFiled = this.addTextField(20, boardPanel);
				
		JPanel boardPanel = new JPanel();
		this.setGrid(2, 1, boardPanel);
		this.boardTextFiled = this.addTextField(20, boardPanel);
		this.boardTextFiled.setText("NOW SERVING: ID-"+this.data[0][0]+", "+this.data[0][2]+" "+this.data[0][3]);
		this.boardTextFiled.setHorizontalAlignment(SwingConstants.CENTER);
		this.boardTextFiled.setFont(new Font("Tahoma", Font.BOLD, 35));
		this.boardTextFiled.setBackground(new Color(59, 89, 182));
		this.boardTextFiled.setForeground(Color.WHITE);
		
		JPanel searchPanel = new JPanel();
		String[] numbers = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"}; 
		this.addLabel("Search by ID: ", searchPanel);
		this.setSearch(this.addTextField(10, searchPanel));
		this.addButtonAll("Search", "Search", searchPanel, this.controller);
		this.addLabel("Cut Queu by: ", searchPanel);
		this.setCutBox(this.addComboB(numbers, searchPanel));
		this.addButtonAll("Cut!", "Cut", searchPanel, this.controller);
		this.addLabel("Total ["+this.data.length+"]", searchPanel);
		searchPanel.setBorder(new EmptyBorder(new Insets(50, 10, 50, 200)));
		boardPanel.add(searchPanel);
		queueTable.add(boardPanel, BorderLayout.NORTH);
		//queueTable.add(searchPanel, BorderLayout.NORTH);
		
		String[] columns = {"ID", "Passport", "Name", "Surname", "Date of Arribal", "Priority"};
		JScrollPane myScrool = this.addTableS(0, this.data, columns, queueTable, "Queue First to Last");
		this.myTableModel = this.myTable[0].getSelectionModel();
		this.myTableModel.addListSelectionListener(this.controller);
		//myScrool.setPreferredSize(new Dimension(800, 50));
		
		
		// ACTIONS BUTTONS
		JPanel actionBtns = new JPanel();
		//this.setBox(actionBtns, 1);
		this.setGrid(4, 1, actionBtns);
		this.addButtonAll("Next", "Next", actionBtns, this.controller);
		this.addButtonAll("Delete", "Delete", actionBtns, this.controller);
		this.addButtonAll("Update Inf", "Update", actionBtns, this.controller);
		this.addButtonAll("Exit", "Exit", actionBtns, this.controller);
		
//		JPanel cutPanel = new JPanel();
//		this.setGrid(2, 1, cutPanel);
//		this.setCut(this.addTextField(5, cutPanel));
//		this.addButtonAll("Cut Queue", "Cut", cutPanel, this.controller);
//		actionBtns.add(cutPanel);
		actionBtns.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
		
		JPanel bottom = new JPanel();
		bottom.setBorder(new EmptyBorder(new Insets(30, 0, 0, 0)));
		
		this.panel.add(personalInfo, BorderLayout.WEST);
		this.panel.add(queueTable, BorderLayout.CENTER);
		this.panel.add(actionBtns, BorderLayout.EAST);
		this.panel.add(bottom, BorderLayout.SOUTH);
				
		this.repaint();
		this.validate();
		
		this.selectedRow = 0;
		this.getMyTableModel().setSelectionInterval(this.selectedRow, this.selectedRow);	
		
//		JOptionPane.showMessageDialog(this, this.addLabelOpt(" POSITION = "+this.selectedRow+ 
//				",  Candidate ID: "+ this.getData(this.getSelectedRow(), 0)
//				+ ", " + ""
//				+ this.getData(this.getSelectedRow(), 2)
//				+ " " + ""
//				+ this.getData(this.getSelectedRow(), 3)
//				+ " " ));
		
		
	}
	
	public void updateView() {
		
		this.panel.removeAll();
		this.setView();
	}

	public JTextField getCut() {
		return cut;
	}

	public void setCut(JTextField cut) {
		this.cut = cut;
	}

	public JTextField getSearch() {
		return search;
	}

	public void setSearch(JTextField search) {
		this.search = search;
	}

	public JComboBox getCutBox() {
		return cutBox;
	}

	public void setCutBox(JComboBox cutBox) {
		this.cutBox = cutBox;
	}
		
}
