package player;

import java.util.Scanner;

import field.Field;
import icefield.Controller;

public class Scientist extends Player{
	
	public Scientist(Controller c, Field startField) {
		super(c, startField);
		this.maxHealth = 4;
		this.health = this.maxHealth;
		name = "Scientist";
	}

	public Scientist(){

	}
	//megnézi, hogy mennyi játékost bír el egy paraméterben megadott szomszédos Field, majd kiírja
	public void InspectField(Field field) {
		System.out.println("InspectField(field)");
		if (this.energy > 0) {
			System.out.println("Field can hold: " + field.getMaxWeight() + "players");
			this.decrementEnergy();
		}else {
			System.out.println("Not enough energy");
		}
		
		
	}

	public void Turn() { //A Player egy körének a függvénye
		String input;
		Scanner scanner = new Scanner(System.in); //olvassa a standard inputot
		boolean endturn = false;
		while(!endturn) { //addig van ciklusban, ameddig a játékos "end turn"-t nem ír, tehát a kör végéig
			input = scanner.nextLine();
			switch (input) {
				case "end turn": EndTurn(); endturn = true;//kör vége
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
						}else if(input.matches("^inspect\\s\\w*")){
							String[] temp = input.split(" ");
							InspectField(currentField.GetNeighbor(Integer.parseInt(temp[1])));
							break;
						}else{
							break;
						}
			}
		}
	}
}
