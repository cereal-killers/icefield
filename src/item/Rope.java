package item;

import java.util.ArrayList;

import field.Field;
import player.Moveable;
import player.Player;

/* Kötél osztály. Ennek használatával a szereplő megmentheti a mellette lévő vízbe esett társát.*/
public class Rope extends Item {

	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		
		if(player.getEnergy() <= 0) { 	/* A kötél használata egy energiát vesz igénybe. */
			System.out.println("no energy");
			return;
		} 		
		Field current = player.getCurrentField(); /* Ez a mező lesz az, ahol a szereplőnk van. */
		Field saveFrom = current; /* Erről a mezőről mentjük meg a vízbe esett szereplőt (a lyukas mező). Ha nem találunk megmentendő szereplőt, akkor a current marad, innen tudjuk majd, hogy nem kell csinálni semmit. */
		
		ArrayList<Player> playersToSave = new ArrayList<Player>();
		//int dirToSafety = 0; // ebbe az irányba kell majd a MEGMENTENDŐ szereplőt ÁTRAKNI.
		for(Field f : current.getNeighbors()) {
			f.setIsUpsideDown(false);
			if(f.IsOverWeight()) {
				//dirToSafety = ;
				saveFrom = f;
				playersToSave = saveFrom.getPlayers();
				for(Player p: playersToSave) {
					int dirToSafety = saveFrom.getNeighbors().indexOf(current);
					saveFrom.Pass(dirToSafety,p);
				}
			}
			//++dirToSafety;
		}
				
		/*if(saveFrom == current) {
			System.out.println("no players to save");
			return;
		}
		*/
		//ArrayList<Player> playersToSave = saveFrom.GetPlayers();
		
		
		
		//////////////
		player.setEnergy(player.getEnergy() - 1);
		System.out.println(playersToSave.size() + "player(s) saved");
		System.out.println("energy level: " + player.getEnergy());
	}
	
	@Override
	public String getName() {
		return "rope";
	}

}
