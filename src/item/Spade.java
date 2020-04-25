package item;

import player.Player;

public class Spade extends Shovel implements java.io.Serializable {

	public Spade() {}
	private int health = 3;
	@Override
	public void Use(Player player) {
		super.Use(player);
		health--;
		if(health == 0) {
			player.RemoveItem(this);
		}
	}
	
	@Override
	public String getName() {
		return "spade";
	}
}
