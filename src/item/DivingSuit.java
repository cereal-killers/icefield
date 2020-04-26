package item;

import player.Player;

/* Búvárruha osztály. Ha felveszi a játékos, akkor nem hal meg vízbeesés esetén. */
public class DivingSuit extends Item implements java.io.Serializable {
	
	/* Ha használja a Player, akkor felkerül rá a búvárruha, vagy ha rajta volt, akkor lekerül róla. */
	@Override
	public void Use(Player player) {
		player.setWears_suit(true);
		System.out.println("player now wears divingsuit");
		
	}
	
	@Override
	public String getName() {
		return "divingsuit";
	}

}
