package item;

import player.Player;

/* Búvárruha osztály. Ha felveszi a játékos, akkor nem hal meg vízbeesés esetén. */
public class DivingSuit extends Item {
	
	/* Ha használja a Player, akkor felkerül rá a búvárruha, vagy ha rajta volt, akkor lekerül róla. */
	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		
		/*if(player.getWearssuit()) {
			player.setWearssuit(false);
			System.out.println("player took off divingsuit");
		} else {
			player.setWearssuit(true);
			System.out.println("player now wears divingsuit");
		}*/
	}
	
	@Override
	public String getName() {
		return "divingsuit";
	}

}
