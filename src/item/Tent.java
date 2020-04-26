package item;

import field.Field;
import player.Player;

public class Tent extends Item implements java.io.Serializable {
	
	@Override
	public void Use(Player player) {
		
		if(!player.getCurrentField().getHasTent()) {
			player.getCurrentField().setHasTent(true);
			player.RemoveItem(this);
			System.out.println("Tent set");
			player.decrementEnergy();
		} else {
			System.out.println("Field already has tent");
		}
		
	}
	
	@Override
	public String getName() {
		return "tent";
	}
}
