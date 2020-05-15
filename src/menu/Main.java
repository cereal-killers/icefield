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

		Menu m = new Menu();
		Container c= new Container(m.getController());
		boolean game = true;
		while (game)
		{
			m.ShowMenuItems();
			Scanner in = new Scanner(System.in);
			System.out.println(in.nextLine());
			try {
				//if(in.hasNextInt()) {
				MenuItem n=	MenuItem.values()[in.nextInt()-1];
				game = m.ChooseMenuItem(n);
				//}
			}
			catch(Exception ex){
				System.out.println("Invalid input.");
				ex.printStackTrace();
			}
		}
	}
}
