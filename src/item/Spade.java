package item;

import player.Player;

/** 
 * Torekeny aso megvalositasa, ugyanazt tudja mint a lapat, de harom hasznalat utan eltorik, es megszunik letezni.
 */
public class Spade extends Shovel implements java.io.Serializable {

	public Spade() {}
	private int health = 3;
	
	/** 
	 * A torekeny hasznalatanak megvalositasa. A szereplo ketto havat eltakarit, vagy 1/0-at, ha a mezon csak annyi van. Ha harmadjara hasznaljuk, eltorik, errol visszajelzest is kapunk. Egy energiaba kerul.
	 * @param player A szereplo, aki hasznalja
	 */
	@Override
	public void Use(Player player) {
		super.Use(player);
		health--;
		if(health == 0) {
			System.out.println("Spade broke");
			player.RemoveItem(this);
		}
	}
	
	/** 
	 * Az aso nevevel ter vissza(spade), azonositashoz kell
	 * @return spade (string)
	 */
	@Override
	public String getName() {
		return "spade";
	}
}
