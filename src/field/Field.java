package field;


import java.util.ArrayList;
import java.util.Stack;

import item.Item;
import player.Player;
import player.PolarBear;
import player.Moveable;

//Mezo osztaly
public class Field implements java.io.Serializable {
	private int maxWeight; //Maximalis teherbiras
	private int snow; //A mezon levo hoegysegek szama
	private boolean hasTent=false; //Erteke true ha a mezon epult sator, false ellenkezo esetben
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
	
	public void setHasTent(boolean value)
	{
		hasTent = value;
	}
	
	public boolean getHasTent()
	{
		return hasTent;
	}

	public void setHasIgloo(boolean value)
	{
		if (maxWeight == Integer.MAX_VALUE)
			hasIgloo = value;
		else
			System.out.println("Field is not stable");
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
	
	public void AddPlayer(Player p)
	{
		players.add(p);
		p.setCurrentField(this);
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
		neighbors.add(neighbor);
		
	}
	
	//Visszaadja a paramerterkent megadott iranyban levo szomszed Field objektumot
	public Field GetNeighbor(int neighborIndex) {
		return neighbors.get(neighborIndex);
		
	}

	//A parameterkent kapott directionIndex iranyba tovabb rakja a pb jegesmedvet
	public boolean Pass(int directionIndex, PolarBear pb)
	{
		if(neighbors.get(directionIndex) != null)
		{
			polarBear = null;
			pb.setCurrentField(neighbors.get(directionIndex));
			neighbors.get(directionIndex).setPolarBear(pb);
			return true;
		}
		return false;
	}
	
	//A parameterkent kapott directionIndex iranyba tovabb rakja a player szereplot
	public boolean Pass(int directionIndex, Player player)
	{
		if(neighbors.get(directionIndex) != null && neighbors.get(directionIndex).Accept(player))
		{
			if (player.getCurrentField().getIsUpsideDown()){ //ha lyukban van a játékos, akkor nem tud mozogni
				System.out.println("Player is in hole, can't move!");
				return false;
			}
			this.Remove(player);
			player.setCurrentField(neighbors.get(directionIndex));
			if(neighbors.get(directionIndex).IsOverWeight()){
				isUpsideDown = true;
				System.out.println("Player fell into hole!");
			}
			player.decrementEnergy();
			return true;
		}
		return false;
	}
	
	//Elhelyezi a szereplot a mezon
	public boolean Accept(Player player)
	{
		if(player != null){
			players.add(player);
			return true;
		}
		return false;
	}
	
	//Eltavolitja a mezorol a parameterkent kapott szereplot
	public void Remove(Moveable player)
	{
		players.remove(player);
	}
	
	//Hozzaad egy eszkozt a mezon levo eszkozokhoz
	public void PushItem(Item item)
	{
		items.push(item);
	}
	
	//Visszaadja az mezon levo eszkozok kozul az utolsot es torli
	public Item PopItem()
	{
		try{
			return items.pop(); //kiveszi a legfelsőbb elemet, ha üres kivételt dob
		}catch(Exception e){
			System.out.println("No item on field");
			return null;
		}
	}
	
	//Visszaadja az mezon levo eszkozok kozul az utolsot
	public Item GetItem()
	{
		try{
			return items.peek();
		}catch(Exception e){
			System.out.println("No item on field");
			return null;
		}
		
	}
	
	//Visszateresi erteke igaz, ha a mezon tul sok jatekos all (es ekkor atfordul), false ellenkezo esetben
	public boolean IsOverWeight()
	{
		if(players.size()>maxWeight)
		{
			this.isUpsideDown = true;
			return true;	
		}
		return false;
	}
	
	//A mezon levo hoegysegek erteket 1-el csokkenti
	public boolean DecrementSnow()
	{
		if (snow != 0){
			snow--;
			System.out.println("1 snow removed!");
			return true;
		}
		else
			System.out.println("Field already clear!");
		return false;
	}
	
	//A mezon levo hoegysegek erteket 1-el noveli
	public void IncrementSnow()
	{
		snow++;
	}
	
	//Mezon levo eszkozok kiirasa
	public void ListItems()
	{
		if(items.empty() == false)
			System.out.println(items);
	 }
}