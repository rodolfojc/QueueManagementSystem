package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import app.candidate.Candidate;
import app.node.Node;
import app.node.Priority;
import app.view.MainView;
import app.view.UpdateInfo;

public class UpdateInfoController implements ActionListener {

	private UpdateInfo updateInfo;
	private MainViewController mainViewController;
	private MainView mainView;

	// DEFAULT CONSTRUCTOR
	public UpdateInfoController(UpdateInfo updateInfo, MainViewController mainViewController, MainView mainView) {
		this.updateInfo = updateInfo;
		this.mainViewController = mainViewController;
		this.mainView = mainView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ACTION LISTENER FOR CANCELLING UPDATE
		if (e.getActionCommand().equals("Cancel")) {
			this.updateInfo.dispose();
		}

		// ACTION LISTENER FOR UPDATING CANDIDATE INFORMATION
		if (e.getActionCommand().equals("Update")) {

			// VALIDATION FOR PASSPORT, NAME AND SURNAME - NO NEED FOR PRIORITY AND CALENDAR
			if (this.mainViewController.registerValidation(this.updateInfo, this.updateInfo.getPassport().getText(),
					this.mainViewController.getRegexPassport(), this.updateInfo.getNameS().getText(),
					this.mainViewController.getRegexGeneral(), this.updateInfo.getSurnameS().getText())) {

				// GETTING HEAD FOR SEARCHING TARGET
				Node tempNode = this.mainViewController.getMyList().getFirst();
				int id = Integer.valueOf(this.updateInfo.getId().getText());

				// GETTING DATA FROM THE UPDATEINFO VIEW

				// PASSPORT, NAME, SURNAME
				int newPassport = Integer.valueOf(this.updateInfo.getPassport().getText());
				String newName = this.updateInfo.getNameS().getText();
				String newSurname = this.updateInfo.getSurnameS().getText();

				// DATE
				Date tempDate = this.updateInfo.getDateOfArrival().getDate();
				SimpleDateFormat myDateSimp = new SimpleDateFormat("dd/MM/yyyy");
				String newDate = myDateSimp.format(tempDate);

				// PRIORITY
				int tempPriorityInt = this.updateInfo.getPriority().getSelectedIndex();
				String[] tempPriorityArrayStr = this.mainView.getPriorities();
				String tempPriorityStr = tempPriorityArrayStr[tempPriorityInt].toUpperCase();
				Priority tempPriority = Priority.valueOf(tempPriorityStr);

				JOptionPane.showMessageDialog(this.mainView,
						this.mainView.addLabelOpt("The Candidate's information has been UPDATED!"));

				// SEARCH TARGET STARTING FROM THE HEAD
				while (tempNode.getCandidate().getID() != id) {
					tempNode = tempNode.getNext();
				}

				// IF THE PRIORITY IS UPDATE, THE NODE IS GOING TO BE DELETE
				if (tempNode.getPriority() != tempPriority) {

					this.mainViewController.getMyList().deleteNode(tempNode);
					Candidate tempCandidate = new Candidate(newName, newSurname, newDate, newPassport, id);
					// CREATING A NOW NODE WITH A NEW PRIORITY
					Node tempNodeTwo = new Node(tempCandidate, tempPriority);
					this.mainViewController.getMyList().insertNode(tempNodeTwo);
				} else {
					tempNode.getCandidate().setPassportNum(newPassport);
					tempNode.getCandidate().setName(newName);
					tempNode.getCandidate().setSurname(newSurname);
					tempNode.getCandidate().setDateOfArribal(newDate);
				}

				// UPDATING INFORMATION IN MAIN VIEW
				String[][] data = this.mainViewController.getMyList().convertToArrayLinkedList();
				this.mainView.setData(data);
				this.mainView.updateView();
				this.updateInfo.dispose();
			}

		}

	}

}
