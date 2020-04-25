package item;

import field.Field;
import player.Player;

public class Tent extends Item {
	
	@Override
	public void Use(Player player) {
		
		System.out.println("Use(Player player)");
		player.getCurrentField()player.
	}
	
	@Override
	public String getName() {
		return "tent";
	}
}
