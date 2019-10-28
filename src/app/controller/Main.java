package app.controller;

import com.toedter.calendar.JDateChooser;

import app.candidate.Candidate;
import app.node.Node;
import app.node.Priority;
import app.view.MainView;

public class Main {

	public static void main(String[] args) {
		
		MainViewController controller = new MainViewController();
		new MainView("Queu Management System", 1200, 800, true, controller);
		
		JDateChooser mydate = new JDateChooser();
		
		// CREATE A CANDIADTE
		Candidate myCandidate = new Candidate("Rodolfo", "Carvajal", mydate, 1987, 1);
		System.out.println(myCandidate);
		
		// CREATE A PRIORITY
		//Priority myPriority = new Priority();
		
		// CREATE A NODE
		//Node myNode = new Node(myCandidate, myPriority);
		
		// TRY LINKED LIST
		
		

	}

}
