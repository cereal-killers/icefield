package field;
import java.util.ArrayList;
import item.Item;
import player.Player;

//MezÅ‘ osztÃ¡ly
public class Field {
	private int maxWeight; //MaximÃ¡lis sÃºly amit a mezÅ‘ elbÃ­r
	private int snowCount; //A mezÅ‘n levÅ‘ hÃ³egysÃ©gek szÃ¡ma
	private boolean hasIgloo; //Ã‰rtÃ©ke true ha van iglu a mezÅ‘n, false ellenkezÅ‘ esetben
	private boolean isUpsideDown=false; //Ã‰rtÃ©ke true ha a mezÅ‘ Ã¡tfordult, false ha nem
	private Stack<Item> items = new ArrayList<Item>(); //A mezÅ‘n talÃ¡lhatÃ³ eszkÃ¶zÃ¶k (pl Ã¡sÃ³) listÃ¡ja
	private ArrayList<Player> players = new ArrayList<Player>(); //A mezÅ‘n Ã¡llÃ³ jÃ¡tÃ©kosok listÃ¡ja
	private PolarBear polarBear=null;//A mezőn álló jegesmedvére mutató pointer
	//SzomszÃ©dos mezÅ‘ket tartalmazza, a Direction kulcs alapjÃ¡n
	//tehÃ¡t pÃ©ldÃ¡ul az Up (fel) irÃ¡nyhoz tartozÃ³ mezÅ‘t a megfelelÅ‘ irÃ¡ny megadÃ¡sÃ¡val kapjuk meg
	private ArrayList<Field> neighbors = new ArrayList<Field>();//A mező szomszédeit tároló lista
	
	//Konstruktor
	//BeÃ¡llÃ­thatjuk a lÃ©trehozandÃ³ mezÅ‘ tulajdonsÃ¡gait
	public Field(int maxWeight, int snowCount, Item item)
	{
		this.maxWeight = maxWeight;
		this.snowCount = snowCount; 
		if(item != null)
			items.push(item);
	}
	
	//Szomszéd hozzáadása
	public void AddNeighbor(Field neighbor)
	{
		System.out.println("AddNeighbor()");
		neighbors.add(neighbor);
		
	}
	
	//Visszaadja a paramÃ©terkÃ©nt megadott irÃ¡nyban levÅ‘ szomszÃ©d Field objektumot
	public Field GetNeighbor(int neighborIndex) {
		System.out.println("GetNeighbor()");
		return neighbors.get(neighborIndex);
		
	}
	
	//Ã�trakja a paramÃ©terkÃ©nt kapott jÃ¡tÃ©kost (player) a megadott irÃ¡nyban levÅ‘ szomszÃ©d mezÅ‘re (directionIndex)
	//Ha a mÅ±velet sikeres visszatÃ©rÃ©si Ã©rtÃ©ke true
	//EllenkezÅ‘ esetben Ã©rtÃ©ke false (pÃ©ldÃ¡ul ha az adott irÃ¡nyban nincs mezÅ‘)
	public boolean PassPlayer(int directionIndex, Player player)
	{
		System.out.println("PassPlayer()");
		if(neighbor.get(directionIndex) != null && neighbors.get(directionIndex).Accept(player))
		{
			//Csak akkor tÃ¶rÃ¶ljÃ¼k a jÃ¡tÃ©kost a jelenlegi mezÅ‘rÅ‘l, ha a szomszÃ©d mezÅ‘re sikerÃ¼lt Ã¡thelyezni
			this.Remove(player);
			//FrissÃ­tjÃ¼k a jÃ¡tÃ©kos jelenlegi helyÃ©t
			player.SetCurrentField(neighbors.get(directionIndex));
			return true;
		}
		return false;
	}
	
	//Visszaadja hogy az adott mezőn van-e jegesmedve (ha értéke null nincs, ellenkező esetben a jegesmedvét)
	public Creature GetPolarBear()
	{
		System.out.println("GetPolarBear()");
		return polarBear;
	}
	
	//Visszaadja hogy az adott mezőn van-e jegesmedve (ha értéke null nincs, ellenkező esetben a jegesmedvét)
	public void SetPolarBear(PolarBear pb)
	{
		System.out.println("SetPolarBear()");
		polarBear = pb;
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
		items.push(item);
	}
	
	//Visszaadja a legfelsÅ‘ eszkÃ¶zt Ã©s tÃ¶rli a listÃ¡bÃ³l
	public Item PopItem()
	{
		System.out.println("PopItem()");
		return items.pop();
	}
	
	//Visszaadja a legfelsÅ‘ eszkÃ¶zt
	public Item GetItem()
	{
		System.out.println("GetItem()");
		return items.peek();	
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
	
	//Visszaadja a mező szomszédainak listáját
		public ArrayList<Field> GetNeighbors()
		{
			System.out.println("GetNeighbors()");	
			return neighbors;
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
			stack temp = items;
			while(temp.empty() == false)
				print(temp.pop());
	 }
}