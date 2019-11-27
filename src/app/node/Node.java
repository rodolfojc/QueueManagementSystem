package app.node;

import app.candidate.Candidate;

public class Node {

	// ATTRIBUTES
	private Node next;
	private Node prev;
	private Candidate candidate;
	private Priority priority;
	
	// DEFAULT CONTRUCTOR
	public Node () {
		this.next = null;
		this.prev = null;
		this.candidate = null;
		this.priority = null;
	}
	
	// CUSTOM CONSTRUCTOR PASSING IN CADIDATE AND PRIORITY
	public Node (Candidate candidate, Priority priority) {
		this.next = null;
		this.prev = null;
		this.candidate = candidate;
		this.priority = priority;
	}
	
	// CUSTOM CONSTRUCTOR PASSING IN CADIDATE, PRIORITY, AND THE NODES THAT ARE PREV AND NEXT
	public Node (Node next, Node prev, Candidate candidate, Priority priority) {
		this.next = next;
		this.prev = prev;
		this.candidate = candidate;
		this.priority = priority;
		
	}
	
	// METHOD TO DISPLAY THE NODE INFOMATION - CANDIDATE, AND PRIORITY
	public void displayNode() {
		System.out.print("{"+this.candidate+"} "+this.priority+"\n");
	}
	
	// GETTER AND SETTERS
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Node [next=" + next + ", prev=" + prev + ", candidate=" + candidate + ", priority=" + priority + "]";
	}
	
}
