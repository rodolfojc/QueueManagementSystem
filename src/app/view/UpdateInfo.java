package app.view;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private JComboBox priority;
	private String[] priorities = {"High", "Medium", "Low"};
	private UpdateInfoController controller;

	public UpdateInfo(String name, int width, int height, boolean Resizable) {
		super(name, width, height, Resizable);
		this.controller = new UpdateInfoController(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setView();
	}
	
	// GETTER AND SETTER
	public JTextField getPassport() {
		return passport;
	}

	public void setPassport(JTextField passport) {
		this.passport = passport;
	}

	public JTextField getNameS() {
		return nameS;
	}

	public void setNameS(JTextField nameS) {
		this.nameS = nameS;
	}

	public JTextField getSurnameS() {
		return surnameS;
	}

	public void setSurnameS(JTextField surnameS) {
		this.surnameS = surnameS;
	}

	public JDateChooser getDateOfArrival() {
		return dateOfArrival;
	}

	public void setDateOfArrival(JDateChooser dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}

	public JComboBox getPriority() {
		return priority;
	}

	public void setPriority(JComboBox priority) {
		this.priority = priority;
	}

	public String[] getPriorities() {
		return priorities;
	}

	public void setPriorities(String[] priorities) {
		this.priorities = priorities;
	}

	public void setView() {
		
		this.setBox(this.panel, 2);
				
		this.setGrid(14, 1, this.panel);
		this.addLabel("UPDATE INFORMATION", this.panel);
		this.addLabel(" ", this.panel);
		this.addLabel("Passport Number", this.panel);
		this.passport = this.addTextField(20, this.panel);
		this.addLabel("Name(s) - As on the passport", this.panel);
		this.nameS = this.addTextField(20, this.panel);
		this.addLabel("Surname(s) - As on the passport", this.panel);
		this.surnameS = this.addTextField(20, this.panel);
		this.addLabel("Date of Arrival", this.panel);
		this.dateOfArrival = this.addCalen(this.panel);
		this.addLabel("Priority level", this.panel);
		this.priority = this.addComboB(this.priorities, this.panel);
		this.addLabel(" ", this.panel);
		this.addButtonAll("Submit", "Add", this.panel, this.controller);		
		this.panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
				
		this.repaint();
		this.validate();
		
	}

}
