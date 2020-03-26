package src.field;
import java.util.ArrayList;
import java.util.EnumMap;

import src.item.Item;
import src.player.Player;

public class Field {
	
	private int maxWeight;
	private int snowCount;
	private boolean hasIgloo;
	private boolean isUpsideDown=false;
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private EnumMap<Direction,Field> neighbor = new EnumMap<Direction,Field>(Direction.class);
	
	public Field(int maxWeight, int snowCount, boolean hasIgloo)
	{
		this.maxWeight = maxWeight;
		this.snowCount = snowCount;
		this.hasIgloo = hasIgloo;
	}
	
	public Field(Field Up,Field Right,Field Down, Field Left)
	{
		neighbor.put(Direction.Up, Up);
		neighbor.put(Direction.Right, Right);
		neighbor.put(Direction.Down, Down);
		neighbor.put(Direction.Left, Left);
	}
	
	public void SetNeighbors(Field Up,Field Right,Field Down, Field Left)
	{
		System.out.println("SetNeighbors()");
		neighbor.put(Direction.Up, Up);
		neighbor.put(Direction.Right, Right);
		neighbor.put(Direction.Down, Down);
		neighbor.put(Direction.Left, Left);
	}
	public Field GetNeighbor(Direction dir) {
		System.out.println("GetNeighbor()");
		return neighbor.get(dir);
		
	}
	
	public boolean PassPlayer(Direction dir, Player player)
	{
		System.out.println("PassPlayer()");
		if(neighbor.get(dir).Accept(player))
		{
			player.GetCurrentField().Remove(player);
			return true;
		}
		return false;
	}
	
	public boolean Accept(Player player)
	{
		System.out.println("Accept()");
		//TODO
		if()
		return true;
	}
	
	public void Remove(Player player)
	{
		System.out.println("Remove()");
		players.remove(player);
	}
	
	public void PushItem(Item item)
	{
		System.out.println("PushItem()");
		items.add(item);
	}
	
	public Item PopItem()
	{
		System.out.println("PopItem()");
		Item last = items.get(items.size()-1);
		items.remove(items.size()-1);
		return last;
	}
	
	public Item GetItem()
	{
		System.out.println("GetItem()");
		return items.get(items.size()-1);	
	}
	
	public boolean IsOverWeight()
	{
		System.out.println("IsOverWeight()");
		if(players.size()>maxWeight)
		{
			this.isUpsideDown = true;
			return true;	
		}
		return false;
	}

	public int GetMaxWeight() {
		System.out.println("GetMaxWeight()");
		return maxWeight;
	}
	
}