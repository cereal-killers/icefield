package menu;

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
		JFrame f=new JFrame("fela dat");

		MenuPanel mp = new MenuPanel(f);

		Menu m = new Menu();
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
