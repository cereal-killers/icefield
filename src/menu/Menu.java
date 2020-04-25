package menu;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

import icefield.Controller;

public class Menu {
    private Vector<ScoreData> highscores;
	private Options options;
	public Menu(){
		highscores = new Vector<>();
		options = new Options();
		ReadHighscores();
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
		Controller controller = new Controller();
		int turns = controller.Start();
		AddHighscore(turns);
	}
	//Függvény a beállításokhoz
	public void Settings() {
		options.ShowOptionItems();
		Scanner in = new Scanner(System.in); 
		OptionsItem o=	OptionsItem.values()[in.nextInt()-1];
		options.ChooseOptionsItem(o);
	}
	//Függvény a toplista megjelenítésére
	public void ShowBestScores() {
		// rendezés
		Collections.sort(highscores, new Comparator<ScoreData>() {
		    @Override
		    public int compare(ScoreData o1, ScoreData o2) {
		        return Integer.compare(o1.getScore(), o2.getScore());
		    }
		});
		if(highscores.isEmpty()) //  ha üres a tömb tudatjuk a felhasználóval
			System.out.println("There are no highscores yet.");
		else
		{
			for(int i=0; i< highscores.size(); i++)
				System.out.println((i+1)+". " + highscores.get(i).getName() + " - " +highscores.get(i).getScore() );
		}
	}
	//Függvény a játékból történő kilépéshez
	public void Exit() {
		WriteHighscores();
	}
	//Új highscore felvitelére szolgáló függévény
	public void AddHighscore(int score)
	{		
		System.out.println("New highscore added.");		
		highscores.add(new ScoreData(options.GetPlayerName(), score));
	}
	//Függvény a toplista kiírására
	public void WriteHighscores()
	{
	  	FileOutputStream fos;
		try {
			fos = new FileOutputStream(System.getProperty("user.dir")+"/highscores.xml");
		 	XMLEncoder encoder = new XMLEncoder(fos);
		  	encoder.writeObject(highscores);
		  	encoder.close();
		  	fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//Függvény a toplista beolvasására
	public void ReadHighscores()
	{
	  	if (new File(System.getProperty("user.dir")+"/highscores.xml").exists()==false) // ha nem létezik még a fájl
	  		return;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/highscores.xml");
		  	XMLDecoder decoder = new XMLDecoder(fis);
		  	highscores = (Vector<ScoreData>) decoder.readObject();
		  	decoder.close();
		  	fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
