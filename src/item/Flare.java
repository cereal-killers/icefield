package item;

import player.Player;

public class Flare extends Item {

	@Override
	public void Use(Player player) {
		System.out.println("flare cannot be used individually");
	}

}
