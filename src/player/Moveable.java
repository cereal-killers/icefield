package player;

import field.Field;

public abstract class Moveable
{
	protected Field currentField; //a jégtábla, amin jelenleg a játékos áll
	
	//A játékos lép egyet a paraméternek megadott dir irányba.
	public abstract void Move(int direction);
	
	public Field getCurrentField() {
			return currentField;
	}
		
	public void setCurrentField(Field nextField) {
		currentField = nextField;
	}
}