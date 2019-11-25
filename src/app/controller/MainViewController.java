package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.candidate.Candidate;
import app.datastructure.DoublyLinkedList;
import app.node.Node;
import app.node.Priority;
import app.view.GuiView;
import app.view.MainView;
import app.view.UpdateInfo;

public class MainViewController implements ActionListener, ListSelectionListener {

	private MainView mainView;
	private DoublyLinkedList myList;
	private int id = 0;

	// REGEX(s)
	// MUST BE LETTER A-Z, NO EMPTY FIELD AND UP TO 24 CHARACTERS
	private String regexGeneral = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
	// CVV 3 - 4 DIGITS 5 - 10
	private String regexPassport = "^([0-9]{5,10})$";

	// DEFAULT CONSTRUCTOR
	public MainViewController(MainView mainView) {
		this.mainView = mainView;
		this.myList = new DoublyLinkedList();

	}

	// GETTER AND SETTERS
	public String getRegexGeneral() {
		return regexGeneral;
	}

	public String getRegexPassport() {
		return regexPassport;
	}

	public DoublyLinkedList getMyList() {
		return myList;
	}

	// METHOD TO POPULATE QUEU BY PASSING IN NUMBER OF CANDIDATES
	public void populateQueueBy(int number) {

		for (int i = 0; i < number; i++) {
			this.populateQueu();
		}
	}

	// METHOD TO CALL THE LIST LIST CONVERTION TO ARRAY AND JFRAME UPDATE
	public void updateView() {
		String[][] data = this.myList.convertToArrayLinkedList();
		this.mainView.setData(data);
		this.mainView.updateView();
	}

	// METHOD TO CREATE DYNAMICALLY TESTING DATA USING RANDOM VALUES
	public void populateQueu() {

		// RANDOM DATA
		String[] names = { "Rodolfo", "Carlos", "Juan", "Cesar", "Ana", "Maria", "Karla", "Chriss", "Roberto", "Kamil",
				"Fernando" };
		String[] surnames = { "Carvajal", "Marquez", "Padron", "Velazques", "Diaz", "Molina", "Avendano", "Belandria",
				"Mora", "Mendez", "Carrillo" };
		String[] dates = { "15/10/2019", "14/10/2019", "17/10/2019", "04/11/2019", "25/10/2019", "01/11/2019" };
		String[] passaports = { "49284746", "84729292", "484943902", "84749202", "3749302", "8182012", "127129411",
				"3283920" };
		String[] priorities = { "HIGH", "MEDIUM", "LOW" };

		// GETTING RANDOM VALUES
		Random r = new Random();
		String nameSelected = names[r.nextInt(names.length)];
		String surnameSelected = surnames[r.nextInt(surnames.length)];
		String dateSelected = dates[r.nextInt(dates.length)];
		int passportSelected = Integer.valueOf(passaports[r.nextInt(passaports.length)]);
		Priority prioritiesSelected = Priority.valueOf(priorities[r.nextInt(priorities.length)]);
		this.id++;

		// CREATING A NEW CANDIDATE, ADDING TO THE QUEU
		Candidate tempCandidate = new Candidate(nameSelected, surnameSelected, dateSelected, passportSelected, id);
		Node tempNode = new Node(tempCandidate, prioritiesSelected);
		this.myList.insertNode(tempNode);
		String[][] data = this.myList.convertToArrayLinkedList();
		this.mainView.setData(data);
		this.myList.displayForward();

	}

	// METHOD TO VALIDATE PASSPORT, NAME AND SURNAME VALUE FROM USER INPUT
	public boolean registerValidation(GuiView myView, String passport, String passportRegex, String name,
			String generalRegex, String surname) {

		// VALIDATION FLAG
		boolean valFlag = true;

		// PASSPORT
		if (!passport.matches(passportRegex)) {

			JOptionPane.showMessageDialog(myView,
					myView.addLabelOpt("The PASSPORT NUMBER is not correct or EMPTY! (5 - 10 digits)"),
					"Passport Number - Error", JOptionPane.ERROR_MESSAGE);
			// IT DOES NOT MATCH, FLAG IS SET FALSE
			valFlag = false;

		}
		// NAME
		else if (!name.matches(generalRegex)) {

			JOptionPane.showMessageDialog(myView, myView.addLabelOpt("The NAME(s) field is not correct or EMPTY!"),
					"Name(s) - Error", JOptionPane.ERROR_MESSAGE);
			// IT DOES NOT MATCH, FLAG IS SET FALSE
			valFlag = false;
		}

		// SURNAME
		else if (!surname.matches(generalRegex)) {

			JOptionPane.showMessageDialog(myView, myView.addLabelOpt("The SURNAME(s) field is not correct or EMPTY!"),
					"Surname(s) - Error", JOptionPane.ERROR_MESSAGE);
			// IT DOES NOT MATCH, FLAG IS SET FALSE
			valFlag = false;

		} else {

			return valFlag;

		}

		return valFlag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ACTION LISTENER FOR NEXT IN QUEUE
		if (e.getActionCommand().equals("Next")) {
			this.myList.deleteNode(this.myList.getFirst());
			String[][] data = this.myList.convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();

		}

		// ACTION LISTENER FOR ADDING IN QUEUE
		if (e.getActionCommand().equals("Add")) {

			// VALIDATION
			if (this.registerValidation(this.mainView, this.mainView.getPassport().getText(), this.regexPassport,
					this.mainView.getNameS().getText(), this.regexGeneral, this.mainView.getSurnameS().getText())) {

				// GETTING DATA
				String tempName = this.mainView.getNameS().getText();
				String tempSurname = this.mainView.getSurnameS().getText();
				Date tempDate = this.mainView.getDateOfArrival().getDate();
				SimpleDateFormat myDateSimp = new SimpleDateFormat("dd/MM/yyyy");
				String tempDateFormat = myDateSimp.format(tempDate);
				String tempPassport = this.mainView.getPassport().getText();
				int tempPassportInt = Integer.valueOf(tempPassport);
				this.id++;

				// CREATING CANDIDATE
				Candidate tempCandidate = new Candidate(tempName, tempSurname, tempDateFormat, tempPassportInt, id);

				// GETTING DATA FOR PRIORITY
				int tempPriorityInt = this.mainView.getPriority().getSelectedIndex();
				String[] tempPriorityArrayStr = this.mainView.getPriorities();
				String tempPriorityStr = tempPriorityArrayStr[tempPriorityInt].toUpperCase();
				System.out.println(tempPriorityStr);
				Priority tempPriority = Priority.valueOf(tempPriorityStr);

				// CREATING NODE WITH CANDIDATE AND PRIORITY
				Node tempNode = new Node(tempCandidate, tempPriority);
				System.out.println(tempNode);

				// ADDING CANDIDATE TO THE QUEU
				this.myList.insertNode(tempNode);
				JOptionPane.showMessageDialog(this.mainView,
						this.mainView.addLabelOpt("The Candidate has been ADDED!"));
				String[][] data = this.myList.convertToArrayLinkedList();
				this.mainView.setData(data);
				this.mainView.updateView();
				this.myList.displayForward();
			}

		}

		// ACTION LISTENER FOR UPDATING INFORMATION WHEN SELECTING A CANDIDATE
		if (e.getActionCommand().equals("Update")) {

			// GEETING DATA TO BE PASSING IN UPDATEVIEW FRAME
			String priority = this.mainView.getData(this.mainView.getSelectedRow(), 5);
			String date = this.mainView.getData(this.mainView.getSelectedRow(), 4);

			// PARSING DATE VALUE FROM STRING TO DATE
			Date myDate = null;
			try {
				myDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			int myPriority = 0;

			if (priority.equals("MEDIUM")) {
				myPriority = 1;
			} else if (priority.equals("LOW")) {
				myPriority = 2;
			}

			// CREATING A NEW INSTANCE OF UPDATEINFO
			UpdateInfo update = new UpdateInfo("UPDATE", 500, 800, true, this, this.mainView);

			// ADDING DATA OF THE CANDIDATE TO THE UPDATEINFO VIEW
			update.getId().setText(this.mainView.getData(this.mainView.getSelectedRow(), 0));
			update.getId().setEditable(false);
			update.getPassport().setText(this.mainView.getData(this.mainView.getSelectedRow(), 1));
			update.getNameS().setText(this.mainView.getData(this.mainView.getSelectedRow(), 2));
			update.getSurnameS().setText(this.mainView.getData(this.mainView.getSelectedRow(), 3));
			update.getDateOfArrival().setDate(myDate);
			update.getPriority().setSelectedIndex(myPriority);

		}

		// ACTION LISTENER FOR DELETING CANDIDATE
		if (e.getActionCommand().equals("Delete")) {

			// NODE TO STAR LOOKING FOR A TARGET
			Node target = this.myList.getFirst();

			// LOOKING FOR THE TARGET
			while (target.getCandidate().getID() != Integer
					.valueOf(this.mainView.getData(this.mainView.getSelectedRow(), 0))) {
				target = target.getNext();
			}

			// DELETING THE TARGET
			this.myList.deleteNode(target);
			JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("The Candidate has been DELETED!"));
			String[][] data = this.myList.convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();

		}

		// ACTION LISTENER FOR CUTTING/LIMITING THE NUMBER OF CANDIDATE IN QUEU
		if (e.getActionCommand().equals("Cut")) {

			// GETTING THE VALUE ENTER FOR CUTTING THE QUEU - INTEGER
			int numberCandidate = Integer.valueOf((String) this.mainView.getCutBox().getSelectedItem());

			// CUTTING QUEUE PASSING THE NUMBER
			if (this.myList.cutQueu(numberCandidate)) {
				JOptionPane.showMessageDialog(this.mainView,
						this.mainView.addLabelOpt("Last " + numberCandidate + " Candiates, have been REMOVED!"));
				String[][] data = this.myList.convertToArrayLinkedList();
				this.mainView.setData(data);
				this.mainView.updateView();
			} else {
				JOptionPane.showMessageDialog(this.mainView,
						this.mainView.addLabelOpt("The value is too HIGH for current queue"));
			}

		}

		// ACTION LISTENER FOR SEARCHING A CANDIDATE BY ID
		if (e.getActionCommand().equals("Search")) {

			// VALIDATING INPUT - REGEX FOR PASSPORT AS IT IS JUST ALLOW NUMBERS
			if (this.mainView.getSearch().getText().matches(regexPassport)) {

				int candidateId = Integer.valueOf(this.mainView.getSearch().getText());

				int pointer = 0;
				Node start = this.myList.getFirst();

				try {
					while (start.getCandidate().getID() != candidateId && start.getNext() != null) {
						start = start.getNext();
						pointer++;
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("ID did not found"));
				}

				if (start == this.myList.getLast() && start.getCandidate().getID() != candidateId) {
					JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("ID did not found"));
				} else {
					this.mainView.getMyTableModel().setSelectionInterval(pointer, pointer);
				}

			} else {
				JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("ID not valid, must be numbers"),
						"Surname(s) - Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		// ACTION LISTENER FOR EXIT THE PROGRAM
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}

	}

	// EVENT LIST SELECTION LISTENER FOR MAIN TABLE - QUEU
	@Override
	public void valueChanged(ListSelectionEvent e) {

		// WHEN SELECTING A ROW, SHOWS UP INFORMATION ABOUT THE CANDIDATE
		if (!this.mainView.getMyTableModel().isSelectionEmpty()) {
			this.mainView.setSelectedRow(this.mainView.getMyTableModel().getMinSelectionIndex());
			int position = Integer.valueOf(this.mainView.getSelectedRow()) + 1;

			JOptionPane.showMessageDialog(this.mainView,
					this.mainView.addLabelOpt(" POSITION = " + position + ",  Candidate ID: "
							+ this.mainView.getData(this.mainView.getSelectedRow(), 0) + ", " + ""
							+ this.mainView.getData(this.mainView.getSelectedRow(), 2) + " " + ""
							+ this.mainView.getData(this.mainView.getSelectedRow(), 3) + " "));

		}
	}

}
