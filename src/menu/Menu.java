package menu;

import icefield.Controller;

public class Menu {
	//Függvény a menüben található opciók kiírására
	public void ShowMenuItems() {
		System.out.println("ShowMenuItems() called.");		
		System.out.println("1. New Game");		
		System.out.println("2. Settings");
		System.out.println("3. Show Best Scores");
		System.out.println("4. Exit");
	}
	//Függvény egy menüopció végrehajtására
	public void ChooseMenuItem(MenuItem item) {
		System.out.println("ChooseMenuItem(MenuItem item) called.");		
		switch(item)
		{
		case newgame:
			NewGame();
			break;
		case settings:
			Settings();
			break;
		case showbestscores:
			ShowBestScores();
			break;
		case exit:
			Exit();
			break;
		}
	}
	//Függvény a játák elindításához
	public void NewGame() {
		System.out.println("NewGame() called.");	
		Controller controller = new Controller();
		controller.Start();
	}
	//Függvény a beállítássok eléréséhez
	public void Settings() {
		System.out.println("Settings() called.");		
	}
	//Függvény a toplista megmutatásához
	public void ShowBestScores() {
		System.out.println("ShowBestScores() called.");		
	}
	//Függvény a játékból való kilépéshez
	public void Exit() {
		System.out.println("Exit() called.");		
	}

}
