package item;

import java.util.ArrayList;

import field.Field;
import player.Player;

/* Kötél osztály. Ennek használatával a szereplő megmentheti a mellette lévő vízbe esett társát.*/
public class Rope extends Item {

	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		
		if(player.GetEnergy() <= 0) { 	/* A kötél használata egy energiát vesz igénybe. */
			System.out.println("no energy");
			return;
		} 		
		Field current = player.GetCurrentField(); /* Ez a mező lesz az, ahol a szereplőnk van. */
		Field saveFrom = current; /* Erről a mezőről mentjük meg a vízbe esett szereplőt (a lyukas mező). Ha nem találunk megmentendő szereplőt, akkor a current marad, innen tudjuk majd, hogy nem kell csinálni semmit. */
		
		int dirToSafety = 0; // ebbe az irányba kell majd a MEGMENTENDŐ szereplőt ÁTRAKNI.
		for(Field f : current.GetNeighbors()) {
			if(f.IsOverWeight()) {
				//dirToSafety = ;
				saveFrom = current.GetNeighbor(dirToSafety);
				ArrayList<Player> playersToSave = saveFrom.GetPlayers();
				for(Player p: playersToSave) {
					//saveFrom.Pass();
				}
			}
			++dirToSafety;
		}
				
		/*if(saveFrom == current) {
			System.out.println("no players to save");
			return;
		}
		*/
		ArrayList<Player> playersToSave = saveFrom.GetPlayers();
		
		
		
		//////////////
		player.SetEnergy(player.GetEnergy() - 1);
		System.out.println(playersToSave.size() + "player(s) saved");
		System.out.println("energy level: " + player.GetEnergy());
	}
	
	@Override
	public String GetName() {
		return "rope";
	}

}
