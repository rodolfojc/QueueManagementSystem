package app.controller;

import java.util.Date;

import com.toedter.calendar.JDateChooser;

import app.candidate.Candidate;
import app.datastructure.DoublyLinkedList;
import app.node.Node;
import app.node.Priority;
import app.view.MainView;

public class Main {

	public static void main(String[] args) {
		
		//MainViewController controller = new MainViewController();
		//new MainView("Queu Management System", 1200, 800, true, controller);
		
		//JDateChooser mydate = new JDateChooser();
		//mydate.getDate();
		Date mydate = new Date();
				
		// CREATE A CANDIDATES
		Candidate myCandidate1 = new Candidate("Rodolfo", "Carvajal", mydate, 1987, 1);
		Candidate myCandidate2 = new Candidate("Juan", "Velansquez", mydate, 1985, 2);
		Candidate myCandidate3 = new Candidate("Cesar", "Padron", mydate, 1981, 3);
		Candidate myCandidate4 = new Candidate("Lucy", "Blue", mydate, 1987, 4);
		Candidate myCandidate5 = new Candidate("Luis", "Altube", mydate, 1978, 5);
		Candidate myCandidate6 = new Candidate("Maria", "Montoya", mydate, 1982, 6);
		Candidate myCandidate7 = new Candidate("Laura", "Churion", mydate, 1990, 7);
					
		// CREATE A PRIORITY
		Priority myPriority1 = Priority.HIGH ;
		Priority myPriority2 = Priority.HIGH ;
		Priority myPriority3 = Priority.MEDIUM;
		Priority myPriority4 = Priority.LOW ;
		Priority myPriority5 = Priority.HIGH ;
		Priority myPriority6 = Priority.LOW ;
		Priority myPriority7 = Priority.MEDIUM ;
		
		
		// CREATE NODES
		Node myNode1 = new Node(myCandidate1, myPriority1);
		Node myNode2 = new Node(myCandidate2, myPriority2);
		Node myNode3 = new Node(myCandidate3, myPriority3);
		Node myNode4 = new Node(myCandidate4, myPriority4);
		Node myNode5 = new Node(myCandidate5, myPriority5);
		Node myNode6 = new Node(myCandidate6, myPriority6);
		Node myNode7 = new Node(myCandidate7, myPriority7);
		
		// TRY LINKED LIST
		DoublyLinkedList myList = new DoublyLinkedList();
		
//		myList.insertFirst(myCandidate1);
//		myList.insertFirst(myCandidate2);
//		myList.insertFirst(myCandidate3);
//		myList.insertFirst(myCandidate4);
//		myList.insertFirst(myCandidate5);
//		myList.insertFirst(myCandidate6);
//		myList.insertFirst(myCandidate7);
		
		myList.insertNode(myNode1);
		myList.insertNode(myNode2);
		myList.insertNode(myNode3);
		myList.insertNode(myNode4);
		myList.insertNode(myNode5);
		myList.insertNode(myNode6);
		myList.insertNode(myNode7);
		
		
		//System.out.println(myNode1.getPriority());
				
		myList.displayForward();
				

	}

}
