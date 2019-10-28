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
		
		// NODE THAT IS FIRST - IF THE LINKEDLIST IS NOT EMPTY
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
		
	}
	
	
	
	
	
	
	

}
