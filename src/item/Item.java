package item;

import player.Player;

/* Eszköz osztály, ami egy játékbeli eszközt (pl. ásó, kötél, étel) reprezentál, ezeknek absztrakt ősosztálya. */
public abstract class Item implements  {
	
	/* A leszármazottak fogják implementálni, hogy náluk a használat hogyan nyilvánul meg. */
	public abstract void Use(Player player);
	
	public abstract String getName();
}
