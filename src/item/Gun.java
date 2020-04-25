package item;

import player.Player;

public class Gun extends Item implements java.io.Serializable {
	
	@Override
	public void Use(Player player) {
		System.out.println("gun cannot be used individually");
	}

	@Override
	public String getName() {
		return "gun";
	}
}
