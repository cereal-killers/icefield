package menu;

import java.util.Scanner;
import java.util.Vector;

import field.Field;

public class Options {
	
	private String playerName;
	private boolean music;

	public Options()
	{
		// default értékek
		playerName = "Player";
		music = true;
	}

	//Függvény a menüben található opciók kiírására
	public void ShowOptionItems() {
		System.out.println("1. Player name");		
		System.out.println("2. Music");
		System.out.println();
		System.out.println("Current state:");
		System.out.println();
		System.out.println("Player name = "+ GetPlayerName());
		System.out.print("Music = ");
		if(GetMusic())
			System.out.println("on");
		else
			System.out.println("off");
	}
	
	//Függvény egy beállítási opció végrehajtására
	public void ChooseOptionsItem(OptionsItem item) {
		switch(item)
		{
		case playername:
			PlayerNameOption();
			break;
		case music:
			MusicOption();
			break;
		default:
			System.out.println("Invalid input.");
			}
	}
	
	// Játékos nevének beállítására szolgáló függvények
	public void PlayerNameOption()
	{
		Scanner in = new Scanner(System.in); 
		System.out.println("Set the name of player.");	
		SetPlayerName(in.nextLine());
		System.out.println("PlayerNumber set to "+ GetPlayerName());	

	}
	public void SetPlayerName(String newname)
	{
		System.out.println("SetPlayerName(String newname) called.");		
		playerName = newname;
	}
	public String GetPlayerName()
	{
		System.out.println("GetPlayerName() called.");		
		return playerName;
	}
	
	// Játék zenéjének beállítására szolgáló függvények
	public void MusicOption()
	{
		Scanner in = new Scanner(System.in); 
		System.out.println("Set the music of game. (on/off)");
		try {
		SetMusic(in.nextLine().equals("on")? true : false);
		System.out.print("Music set to ");
		if(GetMusic())
			System.out.println("on");
		else
			System.out.println("off");

		}	
		catch(Exception ex)
		{
			System.out.println("Incorrect input.");	
		}		
	}
	public void SetMusic(boolean newvalue)
	{		
		music = newvalue;
	}
	public boolean GetMusic()
	{		
		return music;
	}
	
}
