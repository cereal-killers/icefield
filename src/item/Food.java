package item;

import player.Player;

public class Food extends Item implements java.io.Serializable {

	/* Ez a függvény a szereplő életerejét növeli 1-el, ha az még nincs a maximumon. */
	@Override
	public void Use(Player player) {
		player.incrementHealth();
		player.RemoveItem(this); 
	}

	@Override
	public String getName() {
		return "food";
	}
}
