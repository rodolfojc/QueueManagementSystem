package app.node;

import app.candidate.Candidate;

public class Node {

	private Node next;
	private Node prev;
	private Candidate candidate;
	private Priority priority;
	// private String priority;
	
	public Node () {
		this.next = null;
		this.prev = null;
		this.candidate = null;
		this.priority = null;
	}
	
	public Node (Candidate candidate, Priority priority) {
		this.next = null;
		this.prev = null;
		this.candidate = candidate;
		this.priority = priority;
	}
		
	public Node (Node next, Node prev, Candidate candidate, Priority priority) {
		this.next = next;
		this.prev = prev;
		this.candidate = candidate;
		this.priority = priority;
		
	}
	
	public void displayNode() {
		System.out.print("{"+this.candidate+"} "+this.priority+"\n");
	}

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
