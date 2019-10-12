package app.controller;

import app.view.MainView;

public class Main {

	public static void main(String[] args) {
		
		MainViewController controller = new MainViewController();
		new MainView("Queu Management System", 1200, 800, true, controller);

	}

}
