package player;

import field.Field;

public class Scientist extends Player{
	
	public Scientist(Field current) {
		super(current);
		this.maxHealth = 4;
		this.health = this.maxHealth;
	}
	//megnézi, hogy mennyi játékost bír el egy paraméterben megadott szomszédos Field, majd kiírja
	public void InspectField(Field field) {
		System.out.println("InspectField(field)");
		System.out.println("Field can hold: " + field.GetMaxWeight() + "players");
	}
}
