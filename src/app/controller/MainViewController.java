package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.view.MainView;

public class MainViewController implements ActionListener {
	
	private MainView mainView;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Add")) {
			System.out.println("Submit Button Working!!");
		}
	}

}
