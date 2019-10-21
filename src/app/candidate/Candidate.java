package app.candidate;

import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class Candidate {

	private String name;
	private String surname;
	private JDateChooser dateOfArribal;
	private int passportNum;
	private String priority;
	private int ID;

	public Candidate (String name, String surname, JDateChooser dateOfArrival, int passportNumb, String priorityLevel, int ID) {
		this.name = name;
		this.surname = surname;
		this.dateOfArribal = dateOfArrival;
		this.passportNum = passportNumb;
		this.priority = priorityLevel;
		this.ID = ID;
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public JDateChooser getDateOfArribal() {
		return dateOfArribal;
	}

	public void setDateOfArribal(JDateChooser dateOfArribal) {
		this.dateOfArribal = dateOfArribal;
	}

	public int getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(int passportNum) {
		this.passportNum = passportNum;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "Candidate [name=" + name + ", surname=" + surname + ", dateOfArribal=" + dateOfArribal
				+ ", passportNum=" + passportNum + ", priority=" + priority + ", ID=" + ID + "]";
	}
		
}
