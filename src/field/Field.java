package field;
import java.util.ArrayList;
import java.util.EnumMap;

import item.Item;
import player.Player;

//MezÅ‘ osztÃ¡ly
public class Field {
	private int maxWeight; //MaximÃ¡lis sÃºly amit a mezÅ‘ elbÃ­r
	private int snowCount; //A mezÅ‘n levÅ‘ hÃ³egysÃ©gek szÃ¡ma
	private boolean hasIgloo; //Ã‰rtÃ©ke true ha van iglu a mezÅ‘n, false ellenkezÅ‘ esetben
	private boolean isUpsideDown=false; //Ã‰rtÃ©ke true ha a mezÅ‘ Ã¡tfordult, false ha nem
	private ArrayList<Item> items = new ArrayList<Item>(); //A mezÅ‘n talÃ¡lhatÃ³ eszkÃ¶zÃ¶k (pl Ã¡sÃ³) listÃ¡ja
	private ArrayList<Player> players = new ArrayList<Player>(); //A mezÅ‘n Ã¡llÃ³ jÃ¡tÃ©kosok listÃ¡ja
	private Creature* polarBear=null;//A mezőn álló jegesmedvére mutató pointer
	//SzomszÃ©dos mezÅ‘ket tartalmazza, a Direction kulcs alapjÃ¡n
	//tehÃ¡t pÃ©ldÃ¡ul az Up (fel) irÃ¡nyhoz tartozÃ³ mezÅ‘t a megfelelÅ‘ irÃ¡ny megadÃ¡sÃ¡val kapjuk meg
	private ArrayList<Field> neighbors = new ArrayList<Field>();//A mező szomszédeit tároló lista
	
	//Konstruktor
	//BeÃ¡llÃ­thatjuk a lÃ©trehozandÃ³ mezÅ‘ tulajdonsÃ¡gait
	public Field(int maxWeight, int snowCount,Item item)
	{
		this.maxWeight = maxWeight;
		this.snowCount = snowCount; 
		if(item != null)
			items.add(item);
	}
	
	//Szomszéd hozzáadása
	public void AddNeighbor(Field neighbor)
	{
		System.out.println("AddNeighbor()");
		neighbors.add(neighbor);
		
	}
	
	//Visszaadja a paramÃ©terkÃ©nt megadott irÃ¡nyban levÅ‘ szomszÃ©d Field objektumot
	public Field GetNeighbor(Direction dir) {
		System.out.println("GetNeighbor()");
		return neighbor.get(dir);
		
	}
	
	//Ã�trakja a paramÃ©terkÃ©nt kapott jÃ¡tÃ©kost (player) a megadott irÃ¡nyban levÅ‘ szomszÃ©d mezÅ‘re (dir)
	//Ha a mÅ±velet sikeres visszatÃ©rÃ©si Ã©rtÃ©ke true
	//EllenkezÅ‘ esetben Ã©rtÃ©ke false (pÃ©ldÃ¡ul ha az adott irÃ¡nyban nincs mezÅ‘)
	public boolean PassPlayer(Direction dir, Player player)
	{
		System.out.println("PassPlayer()");
		if(neighbor.get(dir) != null && neighbor.get(dir).Accept(player))
		{
			//Csak akkor tÃ¶rÃ¶ljÃ¼k a jÃ¡tÃ©kost a jelenlegi mezÅ‘rÅ‘l, ha a szomszÃ©d mezÅ‘re sikerÃ¼lt Ã¡thelyezni
			this.Remove(player);
			//FrissÃ­tjÃ¼k a jÃ¡tÃ©kos jelenlegi helyÃ©t
			player.SetCurrentField(neighbor.get(dir));
			return true;
		}
		return false;
	}
	
	//Ha a jÃ¡tÃ©kost lÃ©tezik Ã©rtÃ©ke true ellenkezÅ‘ esetben false
	public boolean Accept(Player player)
	{
		System.out.println("Accept()");
		if(player != null)
			return true;
		return false;
	}
	
	//EltÃ¡volÃ­tja a paramÃ©terkÃ©nt kapott jÃ¡tÃ©kost a mezÅ‘rÅ‘l
	public void Remove(Player player)
	{
		System.out.println("Remove()");
		players.remove(player);
	}
	
	//A mezÅ‘re kerÃ¼l a paramÃ©terkÃ©nt kapott eszkÃ¶z: az items lista vÃ©gÃ©re rakjuk az item-et
	public void PushItem(Item item)
	{
		System.out.println("PushItem()");
		items.add(item);
	}
	
	//Visszaadja a legfelsÅ‘ eszkÃ¶zt Ã©s tÃ¶rli a listÃ¡bÃ³l
	public Item PopItem()
	{
		System.out.println("PopItem()");
		Item last = items.get(items.size()-1);
		items.remove(items.size()-1);
		return last;
	}
	
	//Visszaadja a legfelsÅ‘ eszkÃ¶zt
	public Item GetItem()
	{
		System.out.println("GetItem()");
		return items.get(items.size()-1);	
	}
	
	//VisszatÃ©rÃ©si Ã©rtÃ©ke true Ã©s a mezÅ‘ Ã¡tfordul ha a mezÅ‘re helyezett sÃºly nagyobb mint a maximÃ¡lis sÃºly
	//ellenkezÅ‘ esetben false
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
	
	//Visszaadja a mezÅ‘n tartÃ³zkodÃ³ jÃ¡tÃ©kosok listÃ¡jÃ¡t
	public ArrayList<Player> GetPlayers()
	{
		System.out.println("GetPlayers()");	
		return players;
	}

	//Visszaadja a mezÅ‘ maximÃ¡lis teherbÃ­rÃ¡sÃ¡t
	public int GetMaxWeight() {
		System.out.println("GetMaxWeight()");
		return maxWeight;
	}
	
	//Visszaadja a jelenleg a mezÅ‘n levÅ‘ hÃ³egysÃ©gek szÃ¡mÃ¡t
	public int GetSnow()
	{
		System.out.println("GetSnow()");
		return snowCount;
	}
	
	//BeÃ¡llÃ­tja a mezÅ‘n levÅ‘ hÃ³egysÃ©gek szÃ¡mÃ¡t a value paramÃ©ter Ã©rtÃ©kÃ©re
	public void SetSnow(int value)
	{
		System.out.println("SetSnow()");
		snowCount = value;	
	}
	
	//A mezÅ‘n levÅ‘ hÃ³egysÃ©gek szÃ¡mÃ¡t egyel csÃ¶kkenti
		public void DecrementSnow()
		{
			System.out.println("DecrementSnow()");
			snowCount--;
		}
	
	//A mezÅ‘n levÅ‘ hÃ³egysÃ©gek szÃ¡mÃ¡t egyel nÃ¶veli
	public void IncrementSnow()
	{
		System.out.println("IncrementSnow()");
		snowCount++;
	}
	
	//BeÃ¡llÃ­tja hogy az adott mezÅ‘n van-e iglu
	public void SetHasIgloo(boolean value)
	{
		hasIgloo = value;
	}
	
	//Visszaadja hogy az adott mezÅ‘ Ã¡tfordult Ã¡llapotban van-e
	 public boolean GetUpsideDown()
	 {
		 return isUpsideDown;
	 }
	 
	 //ListÃ¡zza a mezÅ‘n levÅ‘ eszkÃ¶zÃ¶ket
	 public void ListItems() {
			for (int i = 0; i < items.size(); i++) {
				System.out.println((i + 1)+ ": " + items.get(i).GetName());
			}
	
}