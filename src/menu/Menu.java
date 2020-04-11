package menu;

import java.util.Scanner;
import java.util.Vector;

import icefield.Controller;

public class Menu {
    private Vector<ScoreData> highscores;
	private Options options;
	public Menu(){
		highscores = new Vector<>();
		options = new Options();
	}
	
	//Függvény a menüben található opciók kiírására
	public void ShowMenuItems() {
		System.out.println("ShowMenuItems() called.");		
		System.out.println("1. New Game");		
		System.out.println("2. Settings");
		System.out.println("3. Show Best Scores");
		System.out.println("4. Exit");
	}
	//Függvény egy menüopció végrehajtására
	public boolean ChooseMenuItem(MenuItem item) {
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
			return false;
		default:
			System.out.println("Incorrect input.");		
			break;
		}
		return true;
	}
	//Függvény a játék elindításához
	public void NewGame() {
		System.out.println("NewGame() called.");	
		Controller controller = new Controller();
		controller.Start();
	}
	//Függvény a beállításokhoz
	public void Settings() {
		options.ShowOptionItems();
		System.out.println("Settings() called.");
		Scanner in = new Scanner(System.in); 
		OptionsItem o=	OptionsItem.values()[in.nextInt()-1];
		options.ChooseOptionsItem(o);
	}
	//Függvény a toplista megjelenítésére
	public void ShowBestScores() {
		System.out.println("ShowBestScores() called.");
		if(highscores.isEmpty()) //  ha üres a tömb tudatjuk a felhasználóval
			System.out.println("There are no highscores yet.");
		else
		{
			for(int i=0; i< highscores.size(); i++)
				System.out.println((i+1)+". " + highscores.get(i).GetName() + " - " +highscores.get(i).GetScore() );
		}
	}
	//Függvény a játékból történő kilépéshez
	public void Exit() {
		System.out.println("Exit() called.");
	}
	//Új highscore felvitelére szolgáló függévény
	public void AddHighscore(int score)
	{		
		System.out.println("AddHighscore() called.");		
		highscores.add(new ScoreData(options.GetPlayerName(), score));
	}

}
