package app.datastructure;

import app.candidate.Candidate;
import app.node.Node;

public class DoublyLinkedList {
	
	private Node first;
	private Node last;
	
	public DoublyLinkedList () {
		this.first = null;
		this.last = null;
	}
	
	// TO CHECK IF THE DOUBLYLINKEDLIST IS EMPTY
	public boolean isEmpty() {
		return this.first == null;
	}
	
	// INSERT - FIRST
	public void insertFirst (Candidate myCandidate) {
		
		// WRAPING THE CANDIDATE DATA OBJECT INTO A NODE
		Node tempNode = new Node();
		tempNode.setCandidate(myCandidate);
		
		if (isEmpty()) {
			this.last = tempNode;
		} else {
			this.first.setPrev(tempNode);
		}
		
		tempNode.setNext(this.first);
		this.first = tempNode;
		
	}
	
	//INSERT - LAST
	public void insertLast (Candidate myCandidate) {
		
		// WRAPING THE CANDIDATE DATA OBJECT INTO A NODE
		Node tempNode = new Node();
		tempNode.setCandidate(myCandidate);
		
		if(isEmpty()) {
			this.first = tempNode;
		} else {
			this.last.setNext(tempNode);
		}
		
		tempNode.setPrev(this.last);
		this.last = tempNode;		
		
	}
	
	//
	// IF THE LINKEDLIST IS NOT EMPTY 
	//
	
	// DELETE FIRST
	public Node deleteFirst () {
		
		// FIRST NODE - IF THE LINKEDLIST IS NOT EMPTY
		Node tempNode = this.first;
		
		// IF THERE IS JUST A NODE IN THE LINKED LIST
		if(this.first.getNext() == null) {
			this.last = null;
		// THE NEW FIRST NODE HAS TO POINT TO NULL
		} else {
			this.first.getNext().setPrev(null);
		}		
		// ASSIGNING NEW FIRST NODE THE REFERENCE OF THE NEXT NODE ON THE OLD FIRST NODE
		this.first = this.first.getNext();
		
		// RETURN NODE
		return tempNode;
		
	}
	
	// DELETE LAST 
	public Node deleteLast() {
		
		// LAST NODE - IF THE LINKEDLIST IS NOT EMPTY
		Node tempNode = this.last;
		
		// IF THERE IS JUST A NODE IN THE LINKED LIST
		if (this.first.getNext() == null) {
			this.first = null;
		// THE NEW LAST, IT IS THE PREVIUS OLD LAST, AND IT HAS TO POINT TO NULL
		} else {
			this.last.getPrev().setNext(null);
		}
		
		// ASSIGNING NEW LAST NODE THE REFERENCE OF THE PREVIOUS NODE ON THE OLD LAST NODE
		this.last = this.last.getPrev();
		
		// RETURN NODE
		return tempNode;
		
	}
	
	// INSERT - AFTER A TARGET NODE
	public boolean insertAfter(Candidate target, Candidate myCandidate) {
		
		// NODE TO POINT THE FIRST NODE
		Node current = this.first;
		
		// WHILE LOOP TO FIND THE TARGET IF EXIST
		while (current.getCandidate() != target) {
			current = current.getNext();
			if(current.getNext() == null) {
				return false;
			}
		}
		
		Node tempNode = new Node();
		tempNode.setCandidate(myCandidate);
		
		if (current == this.last) {
			current.setNext(null);
			this.last = tempNode;
		} else {
			// THE NEW NODE HAS TO POINT THE NEXT NOTE IN CURRENT NODE
			tempNode.setNext(current.getNext());
			// THE NEXT NODE IN CURRENT NODE HAS TO POINT TO THE NEW NODE
			current.getNext().setPrev(tempNode);
		}
		
		tempNode.setPrev(current);
		current.setNext(tempNode);
		
		return true;		
		
	}
	
	// DELETE CANDIDATE
	public Node deleteCandidate (Candidate target) {
		
		// NODE TO POINT THE FIRST NODE
		Node current = this.first;
		
		// WHILE LOOP TO FIND THE TARGET IF EXIST
		while (current.getCandidate() != target) {
			current = current.getNext();
				if (current.getNext() == null) {
				return null;
			}
		} 
		
		Node targetReturn = new Node();
		targetReturn.setCandidate(target);
		
		if (current == this.first) {
			this.first = current.getNext();
		} else {
			// THE CURRENT PREV NODE HAS TO POINT THE NEXT NODE IN THE CURRENT NODE
			current.getPrev().setNext(current.getNext());
		}
		
		if (current == this.last) {
			this.last = current.getPrev();
			//this.last.getPrev().setNext(null);						
		} else {
			// THE CURRENT NEXT NODE HAS TO POINT THE PREV NODE IN THE CURRENT NODE
			current.getNext().setPrev(current.getPrev());
		}
		
		return targetReturn;
		
	}
	
	public void displayForward() {
		
		System.out.print("List First-->Last");
		Node current = this.first;
		while (current.getNext() != null) {
			current.displayNode();
			current = current.getNext();
		}
		System.out.println();
		
		
	}
	
	public void displayBackwards() {
		
		System.out.print("List First-->Last");
		Node current = this.last;
		while (current.getNext() != null) {
			current.displayNode();
			current = current.getNext();
		}
		System.out.println();
		
		
	}
	
	
	
	
	
	
	
	

}
