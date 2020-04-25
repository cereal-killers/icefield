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

	public Field getCurrentField() {
		return currentField;
	}
	
	public void setCurrentField(Field nextField) {
		currentField = nextField;
	}

	public Controller getController(){
		return controller;
	}
	
	public void setController(Controller _controller){
		controller = _controller;
	}

}