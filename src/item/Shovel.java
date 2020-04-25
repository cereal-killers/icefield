package item;

import field.Field;
import player.Player;

/* Ásó osztály, az Item leszármazottja. */
public class Shovel extends Item implements java.io.Serializable {
	
	/*public Shovel() {
		// igazából itt nem kell csinálni semmit, kell ez ide?
		// nem hiszem - Levi
	}*/
	
	/* Átadjuk, hogy melyik szereplő használja. Ez a függvény egy munkával a szereplő mezőjéről 2 egység havat takarít el.*/
	@Override
	public void Use(Player player) {
		
		System.out.println("Use(Player player)");
		
		Field field = player.getCurrentField(); /* Ezen a mezőn fogunk havat takarítani, ezt a player-től kérdezzük le. */
		int snowCount = field.getSnow();
		if(snowCount > 1) {                 /* Ha több, mint egy egység hó van a mezőn, */
			snowCount -= 2;					/* akkor két egységet takarítunk el. */
			System.out.println("2 snow removed");
		} else if (snowCount == 1){			/* Ha 1 egység van, */
			snowCount--;					/* akkor azt az egy egységet eltakarítjuk. */
			System.out.println("1 snow removed");
		} else if (snowCount <= 0) { 		/* Ha nincs hó a jégtáblán, */
			System.out.println("0 snow removed"); /* akkor nem csinálunk semmit. */
		}		
		field.setSnow(snowCount);			
		
		player.decrementEnergy(); /* Az ásóval ásás a szereplőnek egy energiájába kerül. */
	}
	
	@Override
	public String getName() {
		return "shovel";
	}
}
