package player;

public class PolarBear extends Moveable()
{
	public void Move(int direction) {
		System.out.println("Move(dir)");
		currentField.PassPlayer(dir, this);		
	}
}