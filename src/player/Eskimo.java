package player;

import field.Field;
import icefield.Controller;

public class Eskimo extends Player {

	public Eskimo(Controller c, Field startField){ 
		super(c, startField);
		this.maxHealth = 5;
		this.health = this.maxHealth;
	}

	public Eskimo(){

	}
    //az Eskimo képes igloo-t építeni egy Fieldre, ilyenkor átállítja a Field hasIgloo változóját true-ra
	public void BuildIgloo() {
		if(this.energy > 0) {
			this.decrementEnergy();
			currentField.setHasIgloo(true);
		}else
			System.out.println("Not enough energy");
	}
	
	
}
