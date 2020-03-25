package item;

import player.Player;

/* Ásó osztály, az Item leszármazottja. */
public class Shovel extends Item {
	
	public Shovel() {
		// igazából itt nem kell csinálni semmit, kell ez ide?
	}
	
	/* Átadjuk, hogy melyik szereplő használja. Ez a függvény egy munkával a szereplő mezőjéről 2 egység havat takarít el.*/
	@Override
	public void Use(Player player) {
		
		System.out.println("Use(Player player)");
		
		Field field = player.GetCurrentField(); /* Ezen a mezőn fogunk havat takarítani, ezt a player-től kérdezzük le. */
		int snowCount = field.GetSnow();
		if(snowCount > 1) {                 /* Ha több, mint egy egység hó van a mezőn, */
			snowCount -= 2;					/* akkor két egységet takarítunk el. */
		} else if (snowCount == 1){			/* Ha 1 egység van, */
			snowCount--;					/* akkor azt az egy egységet eltakarítjuk. */
		} else if (snowCount <= 0) {}		/* Ha nincs hó a jégtáblán, */
		field.SetSnow(snowCount);			/* akkor nem csinálunk semmit. */
		
		player.SetEnergy(player.GetEnergy() - 1); /* Az ásóval ásás a szereplőnek egy energiájába kerül. */
	}
}