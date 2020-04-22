package player;

public abstract class Moveabe
{
	protected Field currentField; //a jégtábla, amin jelenleg a játékos áll
	
	//A játékos lép egyet a paraméternek megadott dir irányba.
		public abstract void Move(int direction);
}