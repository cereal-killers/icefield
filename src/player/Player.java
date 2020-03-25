package player;

import java.util.ArrayList;

public class Player {
	protected int health;
	protected int maxHealth;
	protected int energy;
	protected boolean wears_suit;
	protected ArrayList<Item> items;
	protected Field currentField;
	
	public Player() {
		this.energy = 4;
		ArrayList<Item> items = new ArrayList<Item>(5);
		
	}
	
	public void PickItemUp() {
		Item item = currentField.GetItem();
		try {
			currentField.PopItem();
			this.AddItem(item);
		}catch(Exception e) {
			System.out.println("Nincs több hely az eszköztárban");
		}
		
	}
	
	public void DropItem(Item item) {
		try {
			items.remove(item);
		}
	}
	
	public void EndTurn() {
		
	}
	
	public void Move(Direction dir) {
		
	}
	
	public void UseItem(Item item) {
		
	}
	
	public void AddItem(Item item) {
		
	}
	
	public void RemoveItem(Item item) {
		
	}
}
