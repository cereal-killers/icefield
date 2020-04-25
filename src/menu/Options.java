package menu;

import java.util.Scanner;
import java.util.Vector;

import field.Field;

public class Options {
	
	private String playerName;
	private int playerNum;
	private boolean music;

	public Options()
	{
		// default értékek
		playerName = "Player";
		music = true;
		playerNum = 3;
	}

	//Függvény a menüben található opciók kiírására
	public void ShowOptionItems() {
		System.out.println("ShowOptionItems() called.");		
		System.out.println("1. Player name");		
		System.out.println("2. Number of players");
		System.out.println("3. Music");
		System.out.println("4. Determinism");
		System.out.println();
		System.out.println("Current state:");
		System.out.println();
		System.out.println("Player name = "+ GetPlayerName());
		System.out.println("Number of players = "+ GetPlayerNumber());
		System.out.print("Music = ");
		if(GetMusic())
			System.out.println("on");
		else
			System.out.println("off");
		System.out.print("Determinism = ");
	}
	
	//Függvény egy beállítási opció végrehajtására
	public void ChooseOptionsItem(OptionsItem item) {
		System.out.println("ChooseOptionsItem(OptionsItem item) called.");	
		switch(item)
		{
		case playername:
			PlayerNameOption();
			break;
		case playernumber:
			PlayerNumberOption();
			break;
		case music:
			MusicOption();
			break;	
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
	

	// Játékosok számának beállítására szolgáló függvények
	public void PlayerNumberOption()
	{
		Scanner in = new Scanner(System.in); 
		System.out.println("Set the number of players to a value between 3 and 8.");	
		try {
			SetPlayerNumber(in.nextInt());
			System.out.println("PlayerNumber set to "+ GetPlayerNumber());	

		}
		catch(Exception ex)
		{
			System.out.println("Incorrect input.");	
		}		
	}
	public void SetPlayerNumber(int newvalue)  
	{
		if(newvalue<3 || newvalue >8)
			System.out.println("The number of players must be between 3 and 8.");
		playerNum = newvalue;
	}
	public int GetPlayerNumber()
	{
		return playerNum;
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
