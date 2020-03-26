package player;

import java.util.ArrayList;
import item.Item;
import field.Field;
import icefield.Controller;
import field.Direction;

//játékos osztály
public class Player {
	protected int health; //játékos aktuális életpontjai
	protected int maxHealth; //játékos maximális életpontjai
	protected int energy; //játékos energiapontjai
	protected boolean wears_suit; //a játékoson van-e búvárruha vagy sem
	protected ArrayList<Item> inventory; //eszköztár
	protected Field currentField; //a jégtábla, amin jelenleg a játékos áll
	protected Controller controller;
	
	public Player(Controller c, Field startField) {
		this.energy = 4;
		this.wears_suit = false;
		this.inventory = new ArrayList<Item>(5);
		this.controller = c;
		this.currentField = startField;
	}
	
	//A játékos felvesz egy eszközt az eszköztárába arról a jégtábláról amin éppen áll.
	public void PickItemUp() {
		System.out.println("PickItemUp()");
		Item item = currentField.GetItem();
		currentField.PopItem();
		this.AddItem(item);
		
	}
	//A játékos lerak egy eszközt az eszköztárából a currentFieldre.
	public void DropItem(Item item) {
		System.out.println("DropItem(item)");
		currentField.PushItem(item);
		Item topitem = currentField.GetItem();
		if(topitem == item) { //megnézi, hogy a lerakott Item megegyezik-e a currentField-en található legfelső item-el
			RemoveItem(item); //ha igen, akkor kiveszi az eszköztárból is
		}
	}
	
	public void EndTurn() {
		System.out.println("EndTurn()");
		if (currentField.GetUpsideDown()) {
			this.SetHealth(health-1);
		}
		if (health == 0) {
			controller.Finish(); 
		}
	}
	
	//A játékos lép egyet a paraméternek megadott dir irányba.
	public void Move(Direction dir) {
		System.out.println("Move(dir)");
		//System.out.println("PassPlayer(dir,player)");
		currentField.PassPlayer(dir, this);
	}
	
	//Használ egy Item-et a Player
	public void UseItem(Item item) {
		System.out.println("UseItem(item)");
		item.Use(this);
	}
	
	//hozzáadja a paraméterben átadott Item-et az inventory tömbbe (eszköztár)
	public void AddItem(Item item) {
		System.out.println("AddItem(item)");
		try {
			this.inventory.add(item); //az ArrayList add függvényével belerakja az inventory-ba az item-et
									  //kivételt dob, ha nincs elég hely
		}catch(Exception e) {	//elkapja a kivételt és kiír egy hibaüzenetet
			System.out.println("No more space in inventory"); 
		}
		
	}
	
	//Eltávolít egy paraméterben megadott Item-et az eszköztárból
	public void RemoveItem(Item item) {
		System.out.println("RemoveItem(item)");
		if (this.inventory.remove(item)) { //az ArrayList remove függvénye true-t térít vissza, ha paraméterben megadott
										   //objektum benne van a listában és ezt eltávolította
			System.out.println("Item removed from inventory");
		}else { //ellenkező esetben false-t
			System.out.println("Item can't be removed! Not in inventory.");
		}
	}
	
	public boolean GetWearsSuit() {
		System.out.println("GetWearsSuit()");
		return wears_suit;
	}
	
	public void SetWearsSuit(boolean value) {
		System.out.println("SetWearsSuit(value)");
		wears_suit = value;
	}
	
	public Field GetCurrentField() {
		System.out.println("GetCurrentField()");
		return currentField;
	}
	
	public void SetCurrentField(Field nextField) {
		System.out.println("SetCurrentField(value)");
		this.currentField = nextField;
	}
	
	public int GetHealth() {
		System.out.println("GetHealth()");
		return health;
	}
	
	public void SetHealth(int value) {
		System.out.println("SetHealth(value)");
		this.health = value;
	}
	
	public int GetMaxHealth() {
		System.out.println("GetMaxHealth()");
		return maxHealth;
	}
	
	public int GetEnergy() {
		System.out.println("GetEnergy()");
		return energy;
	}
	
	public void SetEnergy(int value) {
		System.out.println("SetEnergy(value)");
		this.energy = value;
	}
}
