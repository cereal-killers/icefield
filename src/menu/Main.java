package menu;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Menu m = new Menu();
		while (true)
		{
			m.ShowMenuItems();
			Scanner in = new Scanner(System.in); 
			MenuItem n=	MenuItem.values()[in.nextInt()];
			m.ChooseMenuItem(n);
		}
	}
}
