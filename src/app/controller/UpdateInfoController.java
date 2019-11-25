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
	
	public UpdateInfoController(UpdateInfo updateInfo, MainViewController mainViewController, MainView mainView) {
		this.updateInfo = updateInfo;
		this.mainViewController = mainViewController;
		this.mainView = mainView;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Cancel")) {
			this.updateInfo.dispose();
		}
		
		if (e.getActionCommand().equals("Update")) {
									
			Node tempNode = this.mainViewController.getMyList().getFirst();
			int id = Integer.valueOf(this.updateInfo.getId().getText());
			
			int newPassport = Integer.valueOf(this.updateInfo.getPassport().getText());
			String newName = this.updateInfo.getNameS().getText();
			String newSurname = this.updateInfo.getSurnameS().getText();
			
			Date tempDate = this.updateInfo.getDateOfArrival().getDate();
			SimpleDateFormat myDateSimp = new SimpleDateFormat("dd/MM/yyyy");
			String newDate = myDateSimp.format(tempDate);
			
			int tempPriorityInt = this.updateInfo.getPriority().getSelectedIndex();
			String[] tempPriorityArrayStr = this.mainView.getPriorities();
			String tempPriorityStr = tempPriorityArrayStr[tempPriorityInt].toUpperCase();
			Priority tempPriority = Priority.valueOf(tempPriorityStr);
			
			JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("The Candidate's information has been UPDATED!"));
			
			while (tempNode.getCandidate().getID() != id ) {
				tempNode = tempNode.getNext();				
			}
						
			if (tempNode.getPriority() != tempPriority) {
				
				this.mainViewController.getMyList().deleteNode(tempNode);				
				Candidate tempCandidate = new Candidate(newName, newSurname, newDate, newPassport, id);
				Node tempNodeTwo = new Node(tempCandidate, tempPriority);
				this.mainViewController.getMyList().insertNode(tempNodeTwo);
			} else {
				tempNode.getCandidate().setPassportNum(newPassport);
				tempNode.getCandidate().setName(newName);
				tempNode.getCandidate().setSurname(newSurname);
				tempNode.getCandidate().setDateOfArribal(newDate);
			}
						
			String[][] data = this.mainViewController.getMyList().convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();
			this.updateInfo.dispose();
			
		}

	}

}
