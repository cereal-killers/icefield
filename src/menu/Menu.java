package menu;

import icefield.Controller;

public class Menu {
	//F�ggv�ny a men�ben tal�lhat� opci�k ki�r�s�ra
	public void ShowMenuItems() {
		System.out.println("ShowMenuItems() called.");		
		System.out.println("1. New Game");		
		System.out.println("2. Settings");
		System.out.println("3. Show Best Scores");
		System.out.println("4. Exit");
	}
	//F�ggv�ny egy men�opci� v�grehajt�s�ra
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
	//F�ggv�ny a j�t�k elind�t�s�hoz
	public void NewGame() {
		System.out.println("NewGame() called.");	
		Controller controller = new Controller();
		controller.Start();
	}
	//F�ggv�ny a be�ll�t�ssok el�r�s�hez
	public void Settings() {
		System.out.println("Settings() called.");		
	}
	//F�ggv�ny a toplista megmutat�s�hoz
	public void ShowBestScores() {
		System.out.println("ShowBestScores() called.");		
	}
	//F�ggv�ny a j�t�kb�l val� kil�p�shez
	public void Exit() {
		System.out.println("Exit() called.");		
	}

}
