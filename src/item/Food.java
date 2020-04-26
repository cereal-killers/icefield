package item;

import player.Player;

public class Food extends Item implements java.io.Serializable {

	/* Ez a függvény a szereplő életerejét növeli 1-el, ha az még nincs a maximumon. */
	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		player.incrementHealth();
		player.RemoveItem(this); //Ez ide kell, vagy a Player UseItem() függvényébe?
	}

	@Override
	public String getName() {
		return "food";
	}
}
