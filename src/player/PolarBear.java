package player;

import field.Field;

public class PolarBear extends Moveable
{
	private Field currentField;

	PolarBear(Field _currentField){
		currentField = _currentField;
	}

	public void Move(int dir) {
		System.out.println("Move(dir)");
		currentField.PassPolarBear(dir, this);		
	}

	public Field GetCurrentField(){
		return currentField;
	}
}