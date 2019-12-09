package app.candidate;

public class Candidate {

	// ATTRIBUTESS
	private String name;
	private String surname;
	private String dateOfArribal;
	private int passportNum;
	private int ID;

	// DEFAULT CONSTRCUCTOR
	public Candidate(String name, String surname, String dateOfArrival, int passportNumb, int ID) {
		this.name = name;
		this.surname = surname;
		this.dateOfArribal = dateOfArrival;
		this.passportNum = passportNumb;
		this.ID = ID;

	}

	// GETTERS AND SETTERS
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

	public String getDateOfArribal() {
		return dateOfArribal;
	}

	public void setDateOfArribal(String dateOfArribal) {
		this.dateOfArribal = dateOfArribal;
	}

	public int getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(int passportNum) {
		this.passportNum = passportNum;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	// toString METHOD FOR TESTING
	@Override
	public String toString() {
		return "Candidate [name=" + name + ", surname=" + surname + ", dateOfArribal=" + dateOfArribal
				+ ", passportNum=" + passportNum + ", ID=" + ID + "]";
	}

}
