package item;

import player.Player;

public class Flare extends Item implements java.io.Serializable {

	@Override
	public void Use(Player player) {
		System.out.println("flare cannot be used individually");
	}

	@Override
	public String getName() {
		return "flare";
	}
}
