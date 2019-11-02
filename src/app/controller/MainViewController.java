package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
						
			String tempName = this.mainView.getNameS().getText();
			String tempSurname = this.mainView.getSurnameS().getText();
			
			Date tempDate = this.mainView.getDateOfArrival().getDate();
			SimpleDateFormat myDateSimp = new SimpleDateFormat("dd/MM/yyyy");
			String tempDateFormat = myDateSimp.format(tempDate);
			
			String tempPassport = this.mainView.getPassport().getText();
			int tempPassportInt = Integer.valueOf(tempPassport);
			this.id ++;
					
			Candidate tempCandidate = new Candidate(tempName, tempSurname, tempDateFormat, tempPassportInt,id);
			
			int tempPriorityInt = this.mainView.getPriority().getSelectedIndex();
			System.out.println(tempPriorityInt);
			String[] tempPriorityArrayStr = this.mainView.getPriorities();
			String tempPriorityStr = tempPriorityArrayStr[tempPriorityInt].toUpperCase();
			System.out.println(tempPriorityStr);
			Priority tempPriority = Priority.valueOf(tempPriorityStr);
			System.out.println(tempPriority);
			
			
			Node tempNode = new Node(tempCandidate, tempPriority);
			System.out.println(tempNode);
						
			this.myList.insertNode(tempNode);
			String[][] data = this.myList.convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();
			
			
			this.myList.displayForward();
			
		}
	}

}
