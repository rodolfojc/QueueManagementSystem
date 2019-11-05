package app.view;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import app.controller.UpdateInfoController;

public class UpdateInfo extends GuiView {

	private JTextField passport;
	private JTextField nameS;
	private JTextField surnameS;
	private JDateChooser dateOfArrival;
	private Object priority;
	private String[] priorities;
	private UpdateInfoController controller;

	public UpdateInfo(String name, int width, int height, boolean Resizable) {
		super(name, width, height, Resizable);
		this.controller = new UpdateInfoController(this);
		setView();
	}

	public void setView() {
		
		this.setBox(this.panel, 2);
		
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
		
	}

}
