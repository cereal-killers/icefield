package player;

import field.Field;
import icefield.Controller;

public class Eskimo extends Player {

	public Eskimo(Controller c){ 
		super(c);
		this.maxHealth = 5;
		this.health = this.maxHealth;
	}

    //az Eskimo képes igloo-t építeni egy Fieldre, ilyenkor átállítja a Field hasIgloo változóját true-ra
	public void BuildIgloo() {
		System.out.println("BuildIgloo()");
		currentField.SetHasIgloo(true);
	}
	
	
}
