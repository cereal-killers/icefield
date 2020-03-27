package item;

import player.Player;

public class Charge extends Item {

	@Override
	public void Use(Player player) {
		System.out.println("charge cannot be used individually");
	}
	
	@Override
	public String GetName() {
		return "charge";
	}

}
