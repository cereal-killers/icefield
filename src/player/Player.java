package player;

import java.util.ArrayList;
import java.util.Scanner;

import item.Item;
import field.Field;
import icefield.Controller;

//játékos osztály
public class Player extends Moveable{
	protected int health; //játékos aktuális életpontjai
	protected int maxHealth; //játékos maximális életpontjai
	protected int energy; //játékos energiapontjai
	protected boolean wears_suit; //a játékoson van-e búvárruha vagy sem
	protected ArrayList<Item> items; //eszköztár
	protected Controller controller;  
    
	public Player(Controller c, Field startField) {
		this.energy = 4;
		this.wears_suit = false;
		this.items = new ArrayList<Item>();
		this.controller = c;
		this.currentField = startField;
	}
	
	//A játékos felvesz egy eszközt az eszköztárába arról a jégtábláról amin éppen áll.
	public void PickItemUp() {
		System.out.println("PickItemUp()");
		if (this.energy > 0) {
			
			Item item = currentField.GetItem();
			currentField.PopItem();
			this.AddItem(item); //berakja az eszköztárba
			this.decrementEnergy(); //1munkába került, ezért csökkenti az energiapontjait
		}else
			System.out.println("Not enough energy");
		
		
	}
	//A játékos lerak egy eszközt az eszköztárából a currentFieldre.
	public int SearchItem(String input) {
		String[] temp = input.split(" "); //felbontsa 2 stringre, space mentén
		String item = temp[1].toString(); //ez változóba bele rakja az Item nevét
		boolean found = false;
		for (int i = 0; i < items.size() && found == false ; i++) { //addig megy az Player items tömbjében ameddig nem talál
															//azonos nevű Itemet, vagy ameddig a végére nem ér
			if (items.get(i).GetName().equals(item)) {	//ha talál akkor a found változót átállítja true-ra
				found = true;
				return i;
			}
		}
		return -1;
	}
	//Kör vége, ilyenkor ha a játékos vízben maradt, akkor az életpontjai csökkennek egyel
	//Ha pedig az életpontjai elfogynak, vagyis 0, akkor meghívja a controller Finish() függvényét
	public void EndTurn() {
		System.out.println("EndTurn()");
		if (currentField.GetUpsideDown()) {
			this.decrementHealth();
		}
	}
	
	//A játékos lép egyet a paraméternek megadott dir irányba.
	public void Move(int dir) {
		System.out.println("Move(dir)");
		//System.out.println("PassPlayer(dir,player)");
		if (this.energy > 0)
		{
			currentField.PassPlayer(dir, this);	
		}else
			System.out.println("Not enough energy");
		
	}
	
	public void RemoveItem(Item item){
		items.remove(item);
	}

	//Használ egy Item-et a Player
	public void UseItem(Item item) {
		System.out.println("UseItem(item)");
		if (this.energy > 0)
		{
			this.decrementEnergy();
			item.Use(this);	
		}else{
			System.out.println("Not enough energy");
		}
		
	}

	public void UseItem(String input) { //paraméterben kap egy Stringet, amiben egy "use item", ahol az item egy Item neve 
		//System.out.println("UseItem(String item)");
		int i = SearchItem(input);
		if (i == -1) {
			System.out.println("Player doesn't have this item"); //ha nem talált ilyen Item-et, akkor jelzi hogy nem talált
		}else {
			UseItem(items.get(i));
		}
	}
	
	//hozzáadja a paraméterben átadott Item-et az inventory tömbbe (eszköztár)
	public void AddItem(Item item){
		System.out.println("AddItem(item)");
		try {
			items.add(item); //az ArrayList add függvényével belerakja az inventory-ba az item-et					  //kivételt dob, ha nincs elég hely
			decrementEnergy(); //1 munkába került, ezért csökkenti az energiapontjait
		}catch(Exception e) {	//elkapja a kivételt és kiír egy hibaüzenetet
			System.out.println("Player's inventory is full");
		}
		
	}

	public void RemoveSnow() { //eltávolít egy egység havat a currentFieldről
		System.out.println("RemoveSnow()");
		currentField.DecrementSnow();
		System.out.println("1 snow removed");
		
	}
	
	public void Turn() { //A Player egy körének a függvénye
		System.out.println("Turn()");
		String input;
		Scanner scanner = new Scanner(System.in); //olvassa a standard inputot
		while(true) { //addig van ciklusban, ameddig a játékos "end turn"-t nem ír, tehát a kör végéig

			input = scanner.nextLine();
			switch (input) {
				case "end turn": EndTurn(); //kör vége
					break;
				case "list inventory": ListItems(); //a felhasználó kilistázza a játékos eszköztárában 
					break;							//található item-eket
				case "remove snow": RemoveSnow(); //eltávolít a mezőről a játékos 1 egységnyi havat
					break;
				case "pick up item": PickItemUp(); //felveszi a legelső tárgyat a mezőről
				 	break;
				default: if(input.matches("^use\\s\\w*")) { //reguláris kifejezés egy tárgy használatához
							UseItem(input); break;
						}else if(input.matches("^move\\s\\w*")){
							String[] temp = input.split(" ");
							Move(Integer.parseInt(temp[1])); break;
						}else {
							break;
						}
			}
			if (input.equals("end turn")) { //kilép a ciklusból "end turn" utasításra
				break;
			}
		}
		scanner.close();
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
		if (value > maxHealth) {
			System.out.println("Health level can't be higher than " + maxHealth);
		}else if (value < 0) {
			System.out.println("Health level can't be lower than 0");
		}else
			this.health = value;
	}
	
	public void decrementHealth() {//1-el csökkenti az életpontokat
		System.out.println("decrementHealth()");
		if (this.health > 0)
			this.health--;
		if (health == 0) {
			controller.Finish(); 
		}
	}
	public void incrementHealth() {//1-el növeli az életpontokat
		System.out.println("incrementHealth()");
		if (this.health < maxHealth)
			this.health++;
		System.out.println("Health = " + this.health);
	}
	
	public int GetMaxHealth() {
		System.out.println("GetMaxHealth()");
		return maxHealth;
	}
	
	public int GetEnergy() {
		System.out.println("GetEnergy()");
		return energy;
	}
	
	public void SetEnergy(int value) { //az energia nem lehet kisebb, mint 0 és nem lehet nagyobb, mint 4
		System.out.println("SetEnergy(value)");
		if (value > 4) {
			System.out.println("Energy level can't be higher than 4");
		}else if (value < 0) {
			System.out.println("Energy level can't be lower than 0");
		}else
			this.energy = value;
	}
	
	public void decrementEnergy(){
		System.out.println("decrementEnergy()");
		this.energy--;
	}
	
	public ArrayList<Item> GetItems(){
		return items;
	} 
	
	public void ListItems() {
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1)+ ": " + items.get(i).GetName());
		}
		
	}
	
}
