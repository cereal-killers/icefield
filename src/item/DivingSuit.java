package item;

import player.Player;

/* B�v�rruha oszt�ly. Ha felveszi a j�t�kos, akkor nem hal meg v�zbees�s eset�n. */
public class DivingSuit extends Item {
	
	/* Ha haszn�lja a Player, akkor felker�l r� a b�v�rruha, vagy ha rajta volt, akkor leker�l r�la. */
	@Override
	public void Use(Player player) {
		System.out.println("Use(Player player)");
		
		if(player.GetWearsSuit()) {
			player.SetWearsSuit(false);
			System.out.println("player took off divingsuit")
		} else {
			player.SetWearsSuit(true);
			System.out.println("player now wears divingsuit");
		}
	}

}
