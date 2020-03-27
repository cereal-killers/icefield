package item;

import field.Field;
import player.Player;
import java.util.ArrayList;
import field.Direction;

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
		
		Direction dirToSafety = Direction.Down; // ebbe az irányba kell majd a MEGMENTENDŐ szereplőt ÁTRAKNI.
		for(Direction dir : Direction.values()) {
			if(current.GetNeighbor(dir).IsOverWeight()) {
				dirToSafety = dir;
				saveFrom = current.GetNeighbor(dirToSafety);
			}
		}
				
		if(saveFrom == current) {
			System.out.println("no players to save");
			return;
		}
		
		ArrayList<Player> playersToSave = saveFrom.GetPlayers();
		
		for(Player p: playersToSave) {
			saveFrom.PassPlayer(dirToSafety, p);
		}
		
		player.SetEnergy(player.GetEnergy() - 1);
		System.out.println(playersToSave.size() + "player(s) saved");
		System.out.println("energy level: " + player.GetEnergy());
	}
	
	@Override
	public String GetName() {
		return "rope";
	}

}
