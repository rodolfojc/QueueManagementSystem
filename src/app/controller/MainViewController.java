package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import app.candidate.Candidate;
import app.datastructure.DoublyLinkedList;
import app.node.Node;
import app.node.Priority;
import app.view.MainView;

public class MainViewController implements ActionListener {
	
	private MainView mainView;
	private DoublyLinkedList myList;
	private int id = 0;
	
	public MainViewController(MainView mainView) {
		this.mainView = mainView;
		this.myList = new DoublyLinkedList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Add")) {
			//System.out.println("Submit Button Working!!");
			
			String tempName = this.mainView.getNameS().getText();
			String tempSurname = this.mainView.getSurnameS().getText();
			Date tempDate = this.mainView.getDateOfArrival().getDate();
			String tempPassport = this.mainView.getPassport().getText();
			int tempPassportInt = Integer.valueOf(tempPassport);
			this.id ++;
			
			
			
			Candidate tempCandidate = new Candidate(tempName, tempSurname, tempDate, tempPassportInt,id);
			
			int tempPriorityInt = this.mainView.getPriority().getSelectedIndex();
			String[] tempPriorityArrayStr = this.mainView.getPriorities();
			String tempPriorityStr = tempPriorityArrayStr[tempPriorityInt].toUpperCase();
			Priority tempPriority = Priority.valueOf(tempPriorityStr);
			
			Node tempNode = new Node(tempCandidate, tempPriority);
			
			this.myList.insertNode(tempNode);
			this.myList.displayForward();
			
		}
	}

}
