package item;

import player.Player;

/* Kötél osztály. Ennek használatával a szereplő megmentheti a mellette lévő vízbe esett társát.*/
public class Rope extends Item {

	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		
		Field current = player.GetCurrentField();
		Field saveFrom = current; /* Ha nem találunk megmentendő szereplőt, akkor current marad, innen tudjuk majd, hogy nem kell csinálni semmit. */
		
		Direction dirToSafety; // ebbe az irányba kell majd a MEGMENTENDŐ szereplőt ÁTRAKNI.
		for() 	//itt majd végigmennék a current szomszédain és megnézném, hogy van-e olyan field szomszéd, ami egy lyuk, és van rajta szereplő.
				//ez lesz a saveFrom field
			  	// majd ha Csenge válaszol az üzenetemre
			
		if(saveFrom == current) return;
		Player[] playersToSave = saveFrom.GetPlayers();
		
		for(Player p: playersToSave) {
			saveFrom.PassPlayer(dirToSafety, p);
		}
	}

}
