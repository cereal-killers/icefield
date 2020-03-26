package player;

import field.Field;

public class Eskimo extends Player {

	public Eskimo(Field current){ 
		super(current);
		this.maxHealth = 5;
		this.health = this.maxHealth;
	}
	//az Eskimo képes igloo-t építeni egy Fieldre, ilyenkor átállítja a Field hasIgloo változóját true-ra
	public void BuildIgloo() {
		System.out.println("BuildIgloo()");
		currentField.SetHasIgloo(true);
	}
	
	
}
