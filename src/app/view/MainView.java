package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import app.controller.MainViewController;

public class MainView extends GuiView {

	// GLOBAL VARIABLES
	private ListSelectionModel myTableModel;
	private MainViewController controller;
	private JTextField passport;
	private JTextField nameS;
	private JTextField surnameS;
	private JDateChooser dateOfArrival;
	private JComboBox priority;
	private String[] priorities = { "High", "Medium", "Low" };
	private String[][] data;
	private int selectedRow;
	private JTextField cut;
	private JTextField search;
	private JTextField boardTextFiled;
	private JLabel personalLabel;
	private JComboBox cutBox;

	// DEFAULT CONTRUCTOR
	public MainView(String name, int width, int height, boolean Resizable) {
		super(name, width, height, Resizable);
		this.controller = new MainViewController(this);
		this.data = new String[100][100];

		// CALL METHOD TO POPULATE QUEUE DYNAMICALY
		// PASSING IN THE NUMBER OF CANDIDATE TO ADD IN THE QUEU
		this.controller.populateQueueBy(25);
		this.setView();
	}

	// GETTERS ANS SETTERS
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

	public void setView() {

		// MAIN LAYOUT
		this.setBorder(this.panel);

		// PERSONAL INFORMATION SECTION
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
		this.setBorder(queueTable);

		// DISPLAY / MAIN BOARD
		JPanel boardPanel = new JPanel();
		this.setGrid(2, 1, boardPanel);
		this.boardTextFiled = this.addTextField(20, boardPanel);
		this.boardTextFiled.setText("NOW SERVING: ID-" + this.data[0][0] + ", " + this.data[0][2] + " " + this.data[0][3]);
		this.boardTextFiled.setHorizontalAlignment(SwingConstants.CENTER);
		this.boardTextFiled.setFont(new Font("Tahoma", Font.BOLD, 35));
		this.boardTextFiled.setBackground(new Color(59, 89, 182));
		this.boardTextFiled.setForeground(Color.WHITE);

		// SEARCHING AND CUTTING QUE SECTION
		JPanel searchPanel = new JPanel();
		String[] cutQueuNumber = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
		this.addLabel("Search by ID: ", searchPanel);
		this.setSearch(this.addTextField(10, searchPanel));
		this.addButtonAll("Search", "Search", searchPanel, this.controller);
		this.addLabel("Cut Queu by: ", searchPanel);
		this.setCutBox(this.addComboB(cutQueuNumber, searchPanel));
		this.addButtonAll("Cut!", "Cut", searchPanel, this.controller);
		this.addLabel("Total [" + this.data.length + "]", searchPanel);
		searchPanel.setBorder(new EmptyBorder(new Insets(50, 10, 50, 200)));
		boardPanel.add(searchPanel);
		queueTable.add(boardPanel, BorderLayout.NORTH);

		// FORMATING TABLE
		String[] columns = { "ID", "Passport", "Name", "Surname", "Date of Arribal", "Priority" };
		this.addTableS(0, this.data, columns, queueTable, "Queue First to Last");
		this.myTableModel = this.myTable[0].getSelectionModel();
		this.myTableModel.addListSelectionListener(this.controller);

		// ACTIONS BUTTONS SECTION
		JPanel actionBtns = new JPanel();
		this.setGrid(4, 1, actionBtns);
		this.addButtonAll("Next", "Next", actionBtns, this.controller);
		this.addButtonAll("Delete", "Delete", actionBtns, this.controller);
		this.addButtonAll("Update Inf", "Update", actionBtns, this.controller);
		this.addButtonAll("Exit", "Exit", actionBtns, this.controller);
		actionBtns.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));

		// BUTTON SECTION
		JPanel bottom = new JPanel();
		bottom.setBorder(new EmptyBorder(new Insets(30, 0, 0, 0)));
		
		// ADDING PANELS TO MAIN PANEL 
		this.panel.add(personalInfo, BorderLayout.WEST);
		this.panel.add(queueTable, BorderLayout.CENTER);
		this.panel.add(actionBtns, BorderLayout.EAST);
		this.panel.add(bottom, BorderLayout.SOUTH);
		
		this.repaint();
		this.validate();

		// SET FIRST ROW AS A DEFAULT SELECTION
		this.selectedRow = 0;
		this.getMyTableModel().setSelectionInterval(this.selectedRow, this.selectedRow);

	}

	// METHOD TO UPDATE VIEW
	public void updateView() {

		this.panel.removeAll();
		this.setView();
	}

}
