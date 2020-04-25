package player;


import java.util.ArrayList;
import java.util.Scanner;

import item.Item;
import field.Field;
import icefield.Controller;

//játékos osztály
public class Player extends Moveable implements java.io.Serializable{
	protected int health; //játékos aktuális életpontjai
	protected int maxHealth; //játékos maximális életpontjai
	protected int energy; //játékos energiapontjai
	protected boolean wears_suit; //a játékoson van-e búvárruha vagy sem
	protected ArrayList<Item> items; //eszköztár
	protected Controller controller;
	protected String name;
    
	public Player(Controller c, Field startField) {
		this.energy = 4;
		this.wears_suit = false;
		this.items = new ArrayList<Item>();
		this.controller = c;
		this.currentField = startField;
		currentField.Accept(this);
	}

	public Player(){

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
			if (items.get(i).getName().equals(item)) {	//ha talál akkor a found változót átállítja true-ra
				found = true;
				return i;
			}
		}
		return -1;
	}
	//Kör vége, ilyenkor ha a játékos vízben maradt, akkor az életpontjai csökkennek egyel
	//Ha pedig az életpontjai elfogynak, vagyis 0, akkor meghívja a controller Finish() függvényét
	public void EndTurn() {
		energy = 4;
	}
	
	//A játékos lép egyet a paraméternek megadott dir irányba.
	public void Move(int dir) {
		if (this.energy > 0)
		{
			try{
				currentField.Pass(dir-1, this);
				if (currentField.getPolarBear() != null)
					controller.Finish();
			}catch(Exception e){
				System.out.println("No such neighbor!");
			}
		}else
			System.out.println("Not enough energy");
		
	}
	
	public void RemoveItem(Item item){
		items.remove(item);
	}

	//Használ egy Item-et a Player
	public void UseItem(Item item) {
		if (this.energy > 0)
		{
			item.Use(this);	
		}else{
			System.out.println("Not enough energy");
		}
		
	}

	public int RocketParts(){
		int parts = 0;
		for (int i = 0; i < items.size(); i++){
			if (items.get(i).getName().equals("flare") || items.get(i).getName().equals("charge")
			 || items.get(i).getName().equals("gun") )
			 	++parts;
		}
		return parts;

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
		try {
			items.add(item); //az ArrayList add függvényével belerakja az inventory-ba az item-et					  
			//kivételt dob, ha nincs elég hely
			if (item.getName() == "divingsuit"){
				item.Use(this);
			}
		}catch(Exception e) {	//elkapja a kivételt és kiír egy hibaüzenetet
			System.out.println("Player's inventory is full");
		}
		
	}

	public void RemoveSnow() { //eltávolít egy egység havat a currentFieldről
		currentField.DecrementSnow();
		System.out.println("1 snow removed");
		
	}
	
	public void Turn(){

	}

	public boolean getWears_suit() {
		System.out.println("GetWearsSuit()");
		return wears_suit;
	}
	
	public void setWears_suit(boolean value) {
		System.out.println("SetWearsSuit(value)");
		wears_suit = value;
	}
	
	public Field getCurrentField() {
		System.out.println("GetCurrentField()");
		return currentField;
	}
	
	public void setCurrentField(Field nextField) {
		System.out.println("SetCurrentField(value)");
		currentField = nextField;
	}
	
	public int getHealth() {
		System.out.println("GetHealth()");
		return health;
	}
	
	public void setHealth(int value) {
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
	
	public int getMaxHealth() {
		System.out.println("GetMaxHealth()");
		return maxHealth;
	}
	public void setMaxHealth(int value){
		maxHealth = value;
	}

	public int getEnergy() {
		System.out.println("GetEnergy()");
		return energy;
	}
	
	public void setEnergy(int value) { //az energia nem lehet kisebb, mint 0 és nem lehet nagyobb, mint 4
		System.out.println("SetEnergy(value)");
		if (value > 4) {
			System.out.println("Energy level can't be higher than 4");
		}else if (value < 0) {
			System.out.println("Energy level can't be lower than 0");
		}else
			this.energy = value;
	}
	
	public void decrementEnergy(){
		this.energy--;
		System.out.println("energy level: " + energy);
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public void setItems(ArrayList<Item> array){
		items = array;
	}

	public Controller getController(){
		return controller;
	}
	
	public void setController(Controller _controller){
		controller = _controller;
	}

	public void ListItems() {
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1)+ ": " + items.get(i).getName());
		}
		
	}

	public String getName(){
		return name;
	}
	
	public void setName(String value){
		name = value;
	}
	
}
