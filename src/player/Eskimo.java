package player;

import java.util.Scanner;

import field.Field;
import icefield.Controller;

public class Eskimo extends Player {

	public Eskimo(Controller c, Field startField){ 
		super(c, startField);
		this.maxHealth = 5;
		this.health = this.maxHealth;
		name = "Eskimo";
	}

	public Eskimo(){

	}
    //az Eskimo képes igloo-t építeni egy Fieldre, ilyenkor átállítja a Field hasIgloo változóját true-ra
	public void BuildIgloo() {
		if(this.energy > 0) {
			this.decrementEnergy();
			currentField.setHasIgloo(true);
		}else
			System.out.println("Not enough energy");
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
				case "igloo": BuildIgloo();
					break;
				case "view map": controller.viewMap();
					break;
				default: if(input.matches("^use\\s\\w*")) { //reguláris kifejezés egy tárgy használatához
							UseItem(input); break;
						}else if(input.matches("^move\\s\\w*")){
							String[] temp = input.split(" ");
							Move(Integer.parseInt(temp[1])); break;
						}else{
							break;
						}
			}
		}
	}

}
