package field;
import java.util.ArrayList;
import java.util.EnumMap;

import item.Item;
import player.Player;

//Mező osztály
public class Field {
	private int maxWeight; //Maximális súly amit a mező elbír
	private int snowCount; //A mezőn levő hóegységek száma
	private boolean hasIgloo; //Értéke true ha van iglu a mezőn, false ellenkező esetben
	private boolean isUpsideDown=false; //Értéke true ha a mező átfordult, false ha nem
	private ArrayList<Item> items = new ArrayList<Item>(); //A mezőn található eszközök (pl ásó) listája
	private ArrayList<Player> players = new ArrayList<Player>(); //A mezőn álló játékosok listája
	//Szomszédos mezőket tartalmazza, a Direction kulcs alapján
	//tehát például az Up (fel) irányhoz tartozó mezőt a megfelelő irány megadásával kapjuk meg
	private EnumMap<Direction,Field> neighbor = new EnumMap<Direction,Field>(Direction.class);
	
	//Konstruktor
	//Beállíthatjuk a létrehozandó mező tulajdonságait
	public Field(int maxWeight, int snowCount,Item item)
	{
		this.maxWeight = maxWeight;
		this.snowCount = snowCount;
		if(item != null)
			items.add(item);
	}
	
	public Field(Field Up,Field Right,Field Down, Field Left)
	{
		neighbor.put(Direction.Up, Up);
		neighbor.put(Direction.Right, Right);
		neighbor.put(Direction.Down, Down);
		neighbor.put(Direction.Left, Left);
	}
	
	//Beállíthatjuk a mező szomszédait irány szerint
	//Ha az adott irányba nincs szomszéd, akkor értéke null
	public void SetNeighbors(Field Up,Field Right,Field Down, Field Left)
	{
		System.out.println("SetNeighbors()");
		neighbor.put(Direction.Up, Up);
		neighbor.put(Direction.Right, Right);
		neighbor.put(Direction.Down, Down);
		neighbor.put(Direction.Left, Left);
	}
	
	//Visszaadja a paraméterként megadott irányban levő szomszéd Field objektumot
	public Field GetNeighbor(Direction dir) {
		System.out.println("GetNeighbor()");
		return neighbor.get(dir);
		
	}
	
	//Átrakja a paraméterként kapott játékost (player) a megadott irányban levő szomszéd mezőre (dir)
	//Ha a művelet sikeres visszatérési értéke true
	//Ellenkező esetben értéke false (például ha az adott irányban nincs mező)
	public boolean PassPlayer(Direction dir, Player player)
	{
		System.out.println("PassPlayer()");
		if(neighbor.get(dir) != null && neighbor.get(dir).Accept(player))
		{
			//Csak akkor töröljük a játékost a jelenlegi mezőről, ha a szomszéd mezőre sikerült áthelyezni
			this.Remove(player);
			//Frissítjük a játékos jelenlegi helyét
			player.SetCurrentField(neighbor.get(dir));
			return true;
		}
		return false;
	}
	
	//Ha a játékost létezik értéke true ellenkező esetben false
	public boolean Accept(Player player)
	{
		System.out.println("Accept()");
		if(player != null)
			return true;
		return false;
	}
	
	//Eltávolítja a paraméterként kapott játékost a mezőről
	public void Remove(Player player)
	{
		System.out.println("Remove()");
		players.remove(player);
	}
	
	//A mezőre kerül a paraméterként kapott eszköz: az items lista végére rakjuk az item-et
	public void PushItem(Item item)
	{
		System.out.println("PushItem()");
		items.add(item);
	}
	
	//Visszaadja a legfelső eszközt és törli a listából
	public Item PopItem()
	{
		System.out.println("PopItem()");
		Item last = items.get(items.size()-1);
		items.remove(items.size()-1);
		return last;
	}
	
	//Visszaadja a legfelső eszközt
	public Item GetItem()
	{
		System.out.println("GetItem()");
		return items.get(items.size()-1);	
	}
	
	//Visszatérési értéke true és a mező átfordul ha a mezőre helyezett súly nagyobb mint a maximális súly
	//ellenkező esetben false
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
	
	//Visszaadja a mezőn tartózkodó játékosok listáját
	public ArrayList<Player> GetPlayers()
	{
		System.out.println("GetPlayers()");	
		return players;
	}

	//Visszaadja a mező maximális teherbírását
	public int GetMaxWeight() {
		System.out.println("GetMaxWeight()");
		return maxWeight;
	}
	
	//Visszaadja a jelenleg a mezőn levő hóegységek számát
	public int GetSnow()
	{
		System.out.println("GetSnow()");
		return snowCount;
	}
	
	//Beállítja a mezőn levő hóegységek számát a value paraméter értékére
	public void SetSnow(int value)
	{
		System.out.println("SetSnow()");
		snowCount = value;	
	}
	
	//A mezőn levő hóegységek számát egyel növeli
	public void IncrementSnow()
	{
		System.out.println("IncrementSnow()");
		snowCount++;
	}
	
}