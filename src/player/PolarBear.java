package player;

import field.Field;
import icefield.Controller;

public class PolarBear extends Moveable implements java.io.Serializable
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

	public Controller getController(){
		return controller;
	}
	
	public void setController(Controller _controller){
		controller = _controller;
	}

}