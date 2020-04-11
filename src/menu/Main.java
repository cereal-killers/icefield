package menu;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Menu m = new Menu();
		boolean game = true;
		while (game)
		{
			m.ShowMenuItems();
			Scanner in = new Scanner(System.in); 
			MenuItem n=	MenuItem.values()[in.nextInt()-1];
			game = m.ChooseMenuItem(n);
		}
	}
}
