package field;
import java.util.EnumMap;

import item.Item;
import player.Player;

public class Field {
	
	private int maxWeight;
	private int snowCount;
	private boolean hasIgloo;
	private boolean isUpsideDown;
	private EnumMap<Direction,Field> neighbor = new EnumMap<Direction,Field>(Direction.class);
	
	public Field(Field Up,Field Right,Field Down, Field Left)
	{
		neighbor.put(Direction.Up, Up);
		neighbor.put(Direction.Right, Right);
		neighbor.put(Direction.Down, Down);
		neighbor.put(Direction.Left, Left);
	}
	
	public Field GetNeighbor(Direction dir) {
		return neighbor.get(dir);
		
	}
	
	public boolean PassPlayer(Direction dir,Player player)
	{
		if(neighbor.get(dir).Accept(player))
		{
			player.GetCurrentField().Remove(player);
			return true;
		}
	}
	
	public boolean Accept(Player player)
	{
		//TODO
		return true;
	}
	
	public void Remove(Player player)
	{
		//TODO
	}
	
	public void PushItem(Item item)
	{
		//TODO	
	}
	
	public Item PopItem()
	{
		//TODO
		return null;
	}
	
	public Item GetItem()
	{
		//TODO
		return null;	
	}
	
	public boolean IsOverWeight()
	{
		//TODO
		return true;	
	}

	public int GetMaxWeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}