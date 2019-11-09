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
