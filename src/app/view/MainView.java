package app.view;

import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.controller.MainViewController;

public class MainView extends GuiView {
	
	private MainViewController controller;

	public MainView(String name, int width, int height, boolean Resizable, MainViewController controller) {
		super(name, width, height, Resizable);
		this.controller = controller;
		this.setView();
	}
	
	public void setView() {
		
		String[] priorities = {"High", "Medium", "Low"};
		
		this.setGrid(1, 2, this.panel);
		
		// PERSONAL DETAILS PANEL
		JPanel personalInfo = new JPanel();
		this.setGrid(14, 1, personalInfo);
		this.addLabel("Personal Details", personalInfo);
		this.addLabel(" ", personalInfo);
		this.addLabel("Passport Number", personalInfo);
		this.addTextField(20, personalInfo);
		this.addLabel("Name(s) - As on the passport", personalInfo);
		this.addTextField(20, personalInfo);
		this.addLabel("Surname(s) - As on the passport", personalInfo);
		this.addTextField(20, personalInfo);
		this.addLabel("Date of Arrival", personalInfo);
		this.addCalen(personalInfo);
		this.addLabel("Priority level", personalInfo);
		this.addComboB(priorities, personalInfo);
		this.addLabel(" ", personalInfo);
		this.addButtonAll("Submit", "Add", personalInfo, this.controller);		
		personalInfo.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
		
		// QUEUE TABLE
		JPanel queueTable = new JPanel();
		this.setGrid(1, 1, queueTable);
		this.addLabel("Queue - Ordered", queueTable);
				
		this.panel.add(personalInfo);
		this.panel.add(queueTable);
		
		this.repaint();
		this.validate();
	}
		
}