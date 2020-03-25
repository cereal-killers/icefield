package item;

import player.Player;

/* Kötél osztály. Ennek használatával a szereplő megmentheti a mellette lévő vízbe esett társát.*/
public class Rope extends Item {

	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		
		if(player.GetEnergy() <= 0) return; /* A kötél használata egy energiát vesz igénybe. */
		Field current = player.GetCurrentField(); /* Ez a mező lesz az, ahol a szereplőnk van. */
		Field saveFrom = current; /* Erről a mezőről mentjük meg a vízbe esett szereplőt (a lyukas mező). Ha nem találunk megmentendő szereplőt, akkor a current marad, innen tudjuk majd, hogy nem kell csinálni semmit. */
		
		Direction dirToSafety; // ebbe az irányba kell majd a MEGMENTENDŐ szereplőt ÁTRAKNI.
		for() 	//itt majd végigmennék a current szomszédain és megnézném, hogy van-e olyan field szomszéd, ami egy lyuk, és van rajta szereplő.
				//ez lesz a saveFrom field
			  	// majd ha Csenge válaszol az üzenetemre
			
		if(saveFrom == current) return;
		Player[] playersToSave = saveFrom.GetPlayers();
		
		for(Player p: playersToSave) {
			saveFrom.PassPlayer(dirToSafety, p);
		}
		player.SetEnergy(player.GetEnergy() - 1);
		
	}

}
