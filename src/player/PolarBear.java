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

	public PolarBear(){

	}

	public void Move(int dir) {
		currentField.Pass(dir, this);		
		if (currentField.getPlayers().size() != 0)
			controller.Finish();
	}

	public Field getCurrentfield(){
		return currentField;
	}

	public void setCurrentfield(Field nextField){
		currentField = nextField;
	}
}