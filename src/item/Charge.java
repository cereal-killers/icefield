package item;

import player.Player;

public class Charge extends Item implements java.io.Serializable {

	@Override
	public void Use(Player player) {
		System.out.println("charge cannot be used individually");
	}
	
	@Override
	public String getName() {
		return "charge";
	}

}
