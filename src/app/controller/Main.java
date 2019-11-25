package app.controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;

import app.view.MainView;

public class Main {

	public static void main(String[] args) {
		
		// THEME FOR GUI INTERFACE USING JGOODIES LIBRARY
		try {
			
			Plastic3DLookAndFeel.setPlasticTheme(new ExperienceRoyale());			
			UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
			
		} catch (UnsupportedLookAndFeelException e) {
			System.out.println(e.getStackTrace());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getStackTrace());
		} catch (InstantiationException e) {
			System.out.println(e.getStackTrace());
		} catch (IllegalAccessException e) {
			System.out.println(e.getStackTrace());
		}
		
		// NEW INSTANCE OF THE MAIN VIEW
		new MainView("Queu Management System", 1800, 800, true);

	}

}
