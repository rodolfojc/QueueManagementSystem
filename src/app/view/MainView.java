package app.view;

import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import app.controller.MainViewController;

public class MainView extends GuiView {
	
	private MainViewController controller;
	private JTextField passport;
	private JTextField nameS;
	private JTextField surnameS;
	private JDateChooser dateOfArrival;
	private JComboBox priority;

	public MainView(String name, int width, int height, boolean Resizable, MainViewController controller) {
		super(name, width, height, Resizable);
		this.controller = controller;
		this.setView();
	}
	
	public void setView() {
		
		String[] priorities = {"High", "Medium", "Low"};
		
		this.setGrid(1, 2, this.panel);
		
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
		this.priority = this.addComboB(priorities, personalInfo);
		this.addLabel(" ", personalInfo);
		this.addButtonAll("Submit", "Add", personalInfo, this.controller);		
		personalInfo.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
		
		// QUEUE TABLE
		JPanel queueTable = new JPanel();
		this.setGrid(1, 1, queueTable);
		this.addLabel("Queue - Ordered", queueTable);
		
		//this.panel.add(title);
		this.panel.add(personalInfo);
		this.panel.add(queueTable);
		
		this.repaint();
		this.validate();
	}
		
}
