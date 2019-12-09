package app.datastructure;

import app.candidate.Candidate;
import app.node.Node;

public class DoublyLinkedList {

	// HEAD
	private Node first;
	// TAIL
	private Node last;
	private int size;

	// DEFAULT CONSTRUCTOR
	public DoublyLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	// GETTER AND SETTERS
	public Node getLast() {
		return last;
	}

	public Node getFirst() {
		return first;
	}

	public int getSize() {
		return size;
	}

	// TO CHECK IF THE DOUBLYLINKEDLIST IS EMPTY
	public boolean isEmpty() {
		return this.first == null;
	}

	// INSERT - FIRST
	public void insertFirst(Node myNode) {

		if (isEmpty()) {
			this.last = myNode;
		} else {
			this.first.setPrev(myNode);
		}

		myNode.setNext(this.first);
		this.first = myNode;

		System.out.println("Added - First" + myNode);
		this.size++;
		displayForward();

	}

	// INSERT - LAST
	public void insertLast(Node myNode) {

		if (isEmpty()) {
			this.first = myNode;
		} else {
			this.last.setNext(myNode);
		}

		myNode.setPrev(this.last);
		this.last = myNode;

	}

	// INSERT BETWEEN TWO DIFFERENT PRIORITIES
	public void insertInBetween(Node lower, Node highter) {

		// SET PREV ON START PREVE, NEXT NEW NODE
		lower.getPrev().setNext(highter);
		// SET NODE AT NEXT FOR NEW NODE
		highter.setNext(lower);
		// SET NODE AT PREVEUS FOR NEW NODE
		highter.setPrev(lower.getPrev());
		// SET CURRENT NODE TO THE NEW NODE
		lower.setPrev(highter);

	}

	// INSERTING CONDITIONS
	public void insertNode(Node myNode) {

		Node start;

		// IF LIST IS EMPTY
		if (isEmpty()) {
			insertFirst(myNode);

		} else {

			// 1.- LOOP OVER THE LIST STARTING IN FIRST NODE
			start = this.first;

			// NEW CANDIDATE PRIORITY GREATER THAN THE FIRST IN THE QUEU, NEW CANDIDATE GOES
			// FIRST
			if (myNode.getPriority().getLevelValue() > this.first.getPriority().getLevelValue()) {
				System.out.println("Go 1st " + myNode);
				this.first.setPrev(myNode);
				myNode.setNext(this.first);
				this.first = myNode;
				this.size++;
				displayForward();

				// NEW CANDIDATE PRIORITY LESS OR EQUAL TO TO THE FIRST IN THE QUEU
			} else if (myNode.getPriority().getLevelValue() <= this.first.getPriority().getLevelValue()) {
				System.out.println("Equal or less " + myNode);
				System.out.println("Equal or less " + this.first.getCandidate());

				// MOVING OVER THE LINKED LIST UNTIL PRIORITY CHANGE OR HIT THE TAIL
				while ((myNode.getPriority().getLevelValue() <= start.getPriority().getLevelValue())
						&& this.last != start) {
					System.out.println("Equal or less " + myNode.getCandidate());
					System.out.println("Equal or less " + start.getCandidate());
					// HIT THE TAIL
					if (start.getNext() == null) {
						System.out.println("Next is Null");
						this.last = start;

						// IF DO NOT HIT THE TAIL, CONTINUE TO NEXT NODE
					} else {
						start = start.getNext();
						System.out.println("Next Candidate: " + start.getCandidate());
					}
				}

				// CHECKING WERE IS THE POINTER AND WHO IS AT THE TAIL
				System.out.println("Where it stopped: " + start.getCandidate());
				System.out.println("Last in queu: " + this.last.getCandidate());

				// POINTER IS AT THE TAIL
				if (start == this.last) {
					// CHECKING PRIORITIES IN LAST IN THE QUEU, IF NEW CANDIDATE IS HIGTER GO FIRST
					// THAN THE LAST ONE
					if (myNode.getPriority().getLevelValue() > start.getPriority().getLevelValue()) {
						this.insertInBetween(start, myNode);
						this.last = start;
						this.size++;
						displayForward();
						// NEW CANDIDATE PRIORITY IS LOWER THAN THE LAST IN THE QUEUE, NEW CANDIDATE
						// GOES TO THE TAIL
					} else {
						this.last.setNext(myNode);
						myNode.setPrev(this.last);
						this.last = myNode;
						this.size++;
						displayForward();
					}
					// POINTER IS IN THE MIDDLE OF TWO DIFFERENT PRIORITIES WHERE NEW CANDIDATE
					// PRIORITY IS HIGTER THAN NEXT CANDIDATE
				} else {
					this.insertInBetween(start, myNode);
					this.size++;
					displayForward();
				}

			} else {
				System.out.println("Something went wrong!");

			}

		}
	}

	////////////////////////////////////////////////
	// IF THE LINKEDLIST IS NOT EMPTY
	////////////////////////////////////////////////

	// DELETE FIRST
	public Node deleteFirst() {

		// FIRST NODE - IF THE LINKEDLIST IS NOT EMPTY
		Node tempNode = this.first;

		// IF THERE IS JUST A NODE IN THE LINKED LIST
		if (this.first.getNext() == null) {
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

		// ASSIGNING NEW LAST NODE THE REFERENCE OF THE PREVIOUS NODE ON THE OLD LAST
		// NODE
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
			if (current.getNext() == null) {
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

	// FIND CANDIDATE BY GIVING OBJECT
	public int findCandidate(Candidate target) {

		// NODE TO POINT THE FIRST NODE
		Node tempNode = this.first;
		// POINTER TO TRACK THE POSSITION
		int pointer = 0;

		if (this.isEmpty()) {
			System.out.println("The queu is empty");
		} else {
			while (tempNode.getNext() != null) {
				pointer++;
				if (tempNode.getCandidate() == target) {
					return pointer;
				} else {
					tempNode = tempNode.getNext();
				}
			}
		}

		return -1;
	}

	// CUT QUEUE GIVING A NUMBER
	public boolean cutQueu(int number) {

		// NODE TO POINT THE FIRST NODE
		Node tempNode = this.last;
		// POINTER TO TRACK THE POSSITION
		int pointer = 0;

		while (tempNode.getPrev() != null && pointer != number) {
			tempNode = tempNode.getPrev();
			pointer++;
			System.out.println(tempNode.getCandidate());
		}

		if (tempNode.getPrev() == null) {
			return false;
		}

		if (pointer == number) {
			this.last = tempNode;
			this.last.setNext(null);
			this.size -= number;
		} else {
			System.out.println("The queue is shorter");
		}

		return true;

	}

	// DELETE CANDIDATE
	public Node deleteCandidate(Candidate target) {

		// NODE TO POINT THE FIRST NODE
		Node tempNode = this.first;

		// WHILE LOOP TO FIND THE TARGET IF EXIST
		while (tempNode.getCandidate() != target) {
			tempNode = tempNode.getNext();
			if (tempNode.getNext() == null) {
				return null;
			}
		}

		Node targetReturn = new Node();
		targetReturn.setCandidate(target);

		if (tempNode == this.first) {
			this.first = tempNode.getNext();
		} else {
			// THE CURRENT PREV NODE HAS TO POINT THE NEXT NODE IN THE CURRENT NODE
			tempNode.getPrev().setNext(tempNode.getNext());
		}

		if (tempNode == this.last) {
			this.last = tempNode.getPrev();
			// this.last.getPrev().setNext(null);
		} else {
			// THE CURRENT NEXT NODE HAS TO POINT THE PREV NODE IN THE CURRENT NODE
			tempNode.getNext().setPrev(tempNode.getPrev());
		}

		return targetReturn;

	}

	// DELETE NODE
	public void deleteNode(Node target) {

		if (target == this.first) {
			this.first = target.getNext();
		} else {
			// THE CURRENT PREV NODE HAS TO POINT THE NEXT NODE IN THE CURRENT NODE
			target.getPrev().setNext(target.getNext());
		}

		if (target == this.last) {
			this.last = target.getPrev();
			// this.last.getPrev().setNext(null);
		} else {
			// THE CURRENT NEXT NODE HAS TO POINT THE PREV NODE IN THE CURRENT NODE
			target.getNext().setPrev(target.getPrev());
		}

		this.size--;

	}

	// DISPLAY THE QUEUE FORWARD
	public void displayForward() {

		System.out.println("List First-->Last");
		Node current = this.first;
		while (current != null) {
			current.displayNode();
			current = current.getNext();
		}
		System.out.println();

	}

	// DISPLAY THE QUEUE BACKWARDS
	public void displayBackwards() {

		System.out.println("List Last-->First");
		Node current = this.last;
		while (current != null) {
			current.displayNode();
			current = current.getNext();
		}
		System.out.println();

	}

	// METHOD TO CONVERT FROM LINKED LIST TO STRING ARRAY LIST - FOR JTABLE
	public String[][] convertToArrayLinkedList() {

		String[][] data = new String[this.size][6];
		Node start = this.first;
		int i = 0;

		while (start != null) {
			data[i][0] = String.valueOf(start.getCandidate().getID());
			data[i][1] = String.valueOf(start.getCandidate().getPassportNum());
			data[i][2] = start.getCandidate().getName();
			data[i][3] = start.getCandidate().getSurname();
			data[i][4] = start.getCandidate().getDateOfArribal();
			data[i][5] = String.valueOf(start.getPriority());
			i++;
			start = start.getNext();
		}

		return data;

	}
}
