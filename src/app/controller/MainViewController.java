package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import app.candidate.Candidate;
import app.datastructure.DoublyLinkedList;
import app.node.Node;
import app.node.Priority;
import app.view.MainView;
import app.view.UpdateInfo;

public class MainViewController implements ActionListener, ListSelectionListener {
	
	private MainView mainView;
	private DoublyLinkedList myList;
	private int id = 0;
	
	//REGEXS
	//MUST BE LETTER A-Z, NO EMPTY FIELD AND UP TO 24 CHARACTERS
	private String regexGeneral = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
	//CVV 3 - 4 DIGITS 5 - 10
	private String regexPassport = "^([0-9]{5,10})$";
	
	public MainViewController(MainView mainView) {
		this.mainView = mainView;
		this.myList = new DoublyLinkedList();
				
	}
	
	public DoublyLinkedList getMyList() {
		return myList;
	}

	public void populateQueueBy(int number) {
		
		for (int i=0; i < number; i++) {
			this.populateQueu();
		}
	}
	
	public void updateView() {
		String[][] data = this.myList.convertToArrayLinkedList();
		this.mainView.setData(data);
		this.mainView.updateView();
	}
	
	public void populateQueu() {
		
		String[] names = {"Rodolfo", "Carlos", "Juan", "Cesar", "Ana", "Maria", "Karla", "Chriss", "Roberto", "Kamil", "Fernando"};
		String[] surnames = {"Carvajal", "Marquez", "Padron", "Velazques", "Diaz", "Molina", "Avendano", "Belandria", "Mora", "Mendez", "Carrillo"};
		String[] dates = {"15/10/2019", "14/10/2019", "17/10/2019", "04/11/2019", "25/10/2019", "01/11/2019" };
		String[] passaports = {"49284746", "84729292", "484943902", "84749202", "3749302", "8182012", "127129411", "3283920"};
		String[] priorities = {"HIGH", "MEDIUM", "LOW"};
		
		Random r = new Random();
		String nameSelected = names[r.nextInt(names.length)];
		String surnameSelected = surnames[r.nextInt(surnames.length)];
		String dateSelected = dates[r.nextInt(dates.length)];
		int passportSelected = Integer.valueOf(passaports[r.nextInt(passaports.length)]);
		Priority prioritiesSelected = Priority.valueOf(priorities[r.nextInt(priorities.length)]);
		this.id ++;
				
		Candidate tempCandidate = new Candidate(nameSelected, surnameSelected, dateSelected, passportSelected,id);
		Node tempNode = new Node(tempCandidate, prioritiesSelected);
		this.myList.insertNode(tempNode);
		String[][] data = this.myList.convertToArrayLinkedList();
		this.mainView.setData(data);
		this.myList.displayForward();
	
	}
	
	public boolean registerValidation(){
		
		//VALIDATION FLAG
		boolean valFlag = true;
		
		//PASSPORT
		if(!this.mainView.getPassport().getText().matches(this.regexPassport)) {
					
			JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("The PASSPORT NUMBER is not correct or EMPTY! (5 - 10 digits)"),
						"Passport Number - Error", JOptionPane.ERROR_MESSAGE);
			// IT DOES NOT MATCH, FLAG IS SET FALSE
					valFlag = false;
					
		}
		//NAME
		else if(!this.mainView.getNameS().getText().matches(this.regexGeneral)) {
			
			JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("The NAME(s) field is not correct or EMPTY!"),
					"Name(s) - Error", JOptionPane.ERROR_MESSAGE);
			// IT DOES NOT MATCH, FLAG IS SET FALSE
			valFlag = false;
		}
		
		// SURNAME 
		else if(!this.mainView.getSurnameS().getText().matches(this.regexGeneral)) {
			
			JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("The SURNAME(s) field is not correct or EMPTY!"),
					"Surname(s) - Error", JOptionPane.ERROR_MESSAGE);
			// IT DOES NOT MATCH, FLAG IS SET FALSE
			valFlag = false;
			
		}		
		else {
			
			return valFlag;
			
		}
		
		return valFlag;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Next")) {
			this.myList.deleteNode(this.myList.getFirst());
			String[][] data = this.myList.convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();
			
		}
		
		
		if(e.getActionCommand().equals("Add")) {
			
			if (this.registerValidation()) {
				
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
		
		if (e.getActionCommand().equals("Update")) {
			
			String priority = this.mainView.getData(this.mainView.getSelectedRow(), 5);
			String date = this.mainView.getData(this.mainView.getSelectedRow(), 4);
			
			Date myDate = null;
			try {
				myDate= new SimpleDateFormat("dd/MM/yyyy").parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			int myPriority = 0;
			
			if (priority.equals("MEDIUM")) {
				myPriority = 1;
			} else if (priority.equals("LOW")) {
				myPriority = 2;
			}
			
			
			UpdateInfo update = new UpdateInfo("UPDATE", 500, 800, true, this, this.mainView);
			update.getId().setText(this.mainView.getData(this.mainView.getSelectedRow(), 0));
			update.getId().setEditable(false);
			update.getPassport().setText(this.mainView.getData(this.mainView.getSelectedRow(), 1));
			update.getNameS().setText(this.mainView.getData(this.mainView.getSelectedRow(), 2));
			update.getSurnameS().setText(this.mainView.getData(this.mainView.getSelectedRow(), 3));
			update.getDateOfArrival().setDate(myDate);
			update.getPriority().setSelectedIndex(myPriority);
						
		}
		
		if (e.getActionCommand().equals("Delete")) {
			
			Node target = this.myList.getFirst();
			
			while (target.getCandidate().getID() != Integer.valueOf(this.mainView.getData(this.mainView.getSelectedRow(), 0))) {
				target = target.getNext();
			}
			
			this.myList.deleteNode(target);
			String[][] data = this.myList.convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();
			
		}
		
		if (e.getActionCommand().equals("Cut")) {
			
			int numberCandidate = Integer.valueOf(this.mainView.getCut().getText());
			this.myList.cutQueu(numberCandidate);
			String[][] data = this.myList.convertToArrayLinkedList();
			this.mainView.setData(data);
			this.mainView.updateView();
			
		}
		
		if (e.getActionCommand().equals("Search")) {
			
			int candidateId = Integer.valueOf(this.mainView.getSearch().getText());
			System.out.println(candidateId);
			
			int pointer = 0;
			Node start = this.myList.getFirst();
			
			try {
				while (start.getCandidate().getID() != candidateId && start.getNext() != null) {
				start = start.getNext();
				pointer++;
				}
			}
			catch (Exception error) {
				JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("ID did not found"));
			}
			
			if (start == this.myList.getLast() && start.getCandidate().getID() != candidateId) {
				JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt("ID did not found"));
			}
			else {
				this.mainView.getMyTableModel().setSelectionInterval(pointer, pointer);				
			}
			
			
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
						
			
			if (!this.mainView.getMyTableModel().isSelectionEmpty()) {
				this.mainView.setSelectedRow(this.mainView.getMyTableModel().getMinSelectionIndex());
				int position = Integer.valueOf(this.mainView.getSelectedRow()) + 1;
				
				JOptionPane.showMessageDialog(this.mainView, this.mainView.addLabelOpt(" POSITION = "+position+ 
						",  Candidate ID: "+ this.mainView.getData(this.mainView.getSelectedRow(), 0)
						+ ", " + ""
						+ this.mainView.getData(this.mainView.getSelectedRow(), 2)
						+ " " + ""
						+ this.mainView.getData(this.mainView.getSelectedRow(), 3)
						+ " " ));
												
			}
	}

}
