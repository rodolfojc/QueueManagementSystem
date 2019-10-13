package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Add")) {
			System.out.println("Submit Button Working!!");
		}
	}

}
