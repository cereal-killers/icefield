package field;


import java.util.ArrayList;
import java.util.Stack;

import item.Item;
import player.Player;
import player.PolarBear;
import player.Moveable;

//Mezo osztaly
public class Field {
	private int maxWeight; //Maximalis teherbiras
	private int snow; //A mezon levo hoegysegek szama
	private boolean hasIgloo=false; //Erteke true ha a mezon epult iglu, false ellenkezo esetben
	private boolean isUpsideDown=false; //Erteke true ha a mezo atfordult, false ellenkezo esetben
	private Stack<Item> items = new Stack<Item>(); //A mezon talalhato eszkozok (pl aso) listaja
	private ArrayList<Player> players = new ArrayList<Player>(); //A mezon levo szereplok listaja
	private PolarBear polarBear=null;//A mezon allo jegesmedvere mutato pointer
	private ArrayList<Field> neighbors = new ArrayList<Field>();//A mezo szomszedait tarolo lista
	
	//Konstruktor
	public Field()
	{
	}
	
	//Konstruktor
	public Field(int maxWeight, int snow, Item item)
	{
		this.maxWeight = maxWeight;
		this.snow = snow; 
		if(item != null)
			items.push(item);
	}
	
	public int getMaxWeight()
	{
		return maxWeight;
	}

	public void setMaxWeight( int value )
	{
		maxWeight = value;
	}
	
	public int getSnow()
	{
		return snow;
	}
	
	public void setSnow(int value)
	{
		snow = value;	
	}
	
	public void setHasIgloo(boolean value)
	{
		hasIgloo = value;
	}
	
	public boolean getHasIgloo()
	{
		return hasIgloo;
	}
	
	public boolean getIsUpsideDown()
    {
		return isUpsideDown;
	}
	 
	public void setIsUpsideDown(boolean value)
	{
		isUpsideDown = value;
	}
	
	public Stack<Item> getItems()
	{
		return items;
	}
	
	public void setItems(Stack<Item> value)
	{
		items=value;
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public void setPlayers(ArrayList<Player> value)
	{
		players=value;
	}
	
	public PolarBear getPolarBear()
	{
		return polarBear;
	}
	
	public void setPolarBear(PolarBear pb)
	{
		polarBear = pb;
	}
	
	public ArrayList<Field> getNeighbors()
	{
		return neighbors;
	}

	public void setNeighbors(ArrayList<Field> value)
	{
		neighbors = value;
	}
	
	//Szomszed hozzaadasa
	public void AddNeighbor(Field neighbor)
	{
		System.out.println("AddNeighbor()");
		neighbors.add(neighbor);
		
	}
	
	//Visszaadja a paramerterkent megadott iranyban levo szomszed Field objektumot
	public Field GetNeighbor(int neighborIndex) {
		System.out.println("GetNeighbor()");
		return neighbors.get(neighborIndex);
		
	}

	public boolean Pass(int directionIndex, Polarbear pb)
	{
		System.out.println("Pass()");
		if(neighbors.get(directionIndex) != null && neighbors.get(directionIndex).Accept(pb))
		{
			this.Remove(pb);
			pb.setCurrentField(neighbors.get(directionIndex));
			return true;
		}
		return false;
	}
	
	public boolean Pass(int directionIndex, Player player)
	{
		System.out.println("Pass()");
		if(neighbors.get(directionIndex) != null && neighbors.get(directionIndex).Accept(player))
		{
			player.decrementEnergy();
			this.Remove(player);
			player.setCurrentField(neighbors.get(directionIndex));
			return true;
		}
		return false;
	}
	
	public boolean Accept(Player player)
	{
		System.out.println("Accept()");
		if(player != null)
			return true;
		return false;
	}
	
	public void Remove(Player player)
	{
		System.out.println("Remove()");
		players.remove(player);
	}
	
	public void PushItem(Item item)
	{
		System.out.println("PushItem()");
		items.push(item);
	}
	
	public Item PopItem()
	{
		System.out.println("PopItem()");
		return items.pop();
	}
	
	public Item GetItem()
	{
		System.out.println("GetItem()");
		return items.peek();	
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
	
	public void DecrementSnow()
	{
		System.out.println("DecrementSnow()");
		snow--;
	}
	
	public void IncrementSnow()
	{
		System.out.println("IncrementSnow()");
		snow++;
	}
	 
	 public void ListItems() {
			if(items.empty() == false)
				System.out.println(items);
	 }
}