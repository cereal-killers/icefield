package player;

import field.Field;
import icefield.Controller;

public class Scientist extends Player{
	
	public Scientist(Controller c, Field startField) {
		super(c, startField);
		this.maxHealth = 4;
		this.health = this.maxHealth;
	}

	public Scientist(){

	}
	//megnézi, hogy mennyi játékost bír el egy paraméterben megadott szomszédos Field, majd kiírja
	public void InspectField(Field field) {
		System.out.println("InspectField(field)");
		if (this.energy > 0) {
			System.out.println("Field can hold: " + field.getMaxWeight() + "players");
			this.decrementEnergy();
		}else {
			System.out.println("Not enough energy");
		}
		
		
	}
}
