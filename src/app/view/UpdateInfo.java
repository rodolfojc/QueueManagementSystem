package app.view;

import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import app.controller.MainViewController;
import app.controller.UpdateInfoController;

public class UpdateInfo extends GuiView {

	// GLOBAL VARIABLES
	private JTextField passport;
	private JTextField nameS;
	private JTextField surnameS;
	private JDateChooser dateOfArrival;
	private JComboBox priority;
	private JTextField id;
	private String[] priorities = { "High", "Medium", "Low" };
	private UpdateInfoController controller;

	// DEFAULT CONTRUCTOR
	public UpdateInfo(String name, int width, int height, boolean Resizable, MainViewController mainViewController,
			MainView mainView) {
		super(name, width, height, Resizable);
		this.controller = new UpdateInfoController(this, mainViewController, mainView);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setView();
	}

	// GETTER AND SETTER
	public JTextField getPassport() {
		return passport;
	}

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
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

		// LAYOUT MAIN PANEL
		this.setBox(this.panel, 2);
		this.setGrid(17, 1, this.panel);

		// UPDATE INFORMATION SECTION
		this.addLabel("UPDATE INFORMATION", this.panel);
		this.addLabel("ID (Cannot be UPDATED)", this.panel);
		this.id = this.addTextField(20, this.panel);
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
		this.addButtonAll("UPDATE", "Update", this.panel, this.controller);
		this.addLabel(" ", this.panel);
		this.addButtonAll("Cancel", "Cancel", this.panel, this.controller);
		this.panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));

		this.repaint();
		this.validate();

	}

}
