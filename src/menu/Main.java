package menu;

import graphics.Container;
import graphics.MenuPanel;

import javax.swing.*;
import java.util.Scanner;
/**
 * A fő osztály
 *
 */
public class Main {
/**
 * A program main függvénye
 * @param args
 */
	public static void main(String[] args) {
		Container c= new Container();
		c.repaint();
		Menu m = new Menu();
		c.setController(m.getController());
		boolean game = true;
		while (game)
		{
			m.ShowMenuItems();
			Scanner in = new Scanner(System.in); 
			try {
				MenuItem n=	MenuItem.values()[in.nextInt()-1];
				game = m.ChooseMenuItem(n);
			}
			catch(Exception ex){
				System.out.println("Invalid input.");
			}
		}
	}
}
