package player;

import field.Field;
import icefield.Controller;

public class PolarBear extends Moveable
{
	private Controller controller;

	public PolarBear(Field _currentField, Controller _controller){
		currentField = _currentField;
		controller = _controller;
	}

	public void Move(int dir) {
		System.out.println("Move(dir)");
		currentField.PassPolarBear(dir, this);		
		if (currentField.GetPlayers().size() != 0)
			controller.Finish();
	}

	public Field GetCurrentField(){
		return currentField;
	}

	public void SetCurrentField(Field nextField){
		currentField = nextField;
	}
}