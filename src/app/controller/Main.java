package app.controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;

import app.view.MainView;

public class Main {

	public static void main(String[] args) {
		
		try {
            // Set cross-platform Java L&F (also called "Metal")
		  //Plastic3DLookAndFeel.setPlasticTheme(new DarkStar());
		  Plastic3DLookAndFeel.setPlasticTheme(new ExperienceRoyale());	
		  //Plastic3DLookAndFeel.setPlasticTheme(new DesertBluer());	
		  UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
    } 
    catch (UnsupportedLookAndFeelException e) {
       System.out.println(e.getStackTrace());
    }
    catch (ClassNotFoundException e) {
    	System.out.println(e.getStackTrace());
    }
    catch (InstantiationException e) {
    	System.out.println(e.getStackTrace());
    }
    catch (IllegalAccessException e) {
    	System.out.println(e.getStackTrace());
    }
		
		new MainView("Queu Management System", 1600, 800, true);
		
			
	}

}
