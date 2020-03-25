package item;

import player.Player;

public class Food extends Item {

	/* Ez a függvény a szereplő életerejét növeli 1-el, ha az még nincs a maximumon. */
	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		player.SetHealth(player.GetHealth() + 1); /* A maximum-ellenőrzés a SetHealth() függvényben van megvalósítva. */
		player.RemoveItem(this); //Ez ide kell, vagy a Player UseItem() függvényébe?
		System.out.println("health = " + player.GetHealth());
	}

}
