package player;

import field.Field;
import icefield.Controller;

public class Scientist extends Player{
	
	public Scientist(Controller c, Field startField) {
		super(c, startField);
		this.maxHealth = 4;
		this.health = this.maxHealth;
	}
	//megnézi, hogy mennyi játékost bír el egy paraméterben megadott szomszédos Field, majd kiírja
	public void InspectField(Field field) {
		System.out.println("InspectField(field)");
		System.out.println("Field can hold: " + field.GetMaxWeight() + "players");
		this.decrementEnergy();
	}
}
