package test;

import java.util.Scanner;

import field.Field;
import icefield.Controller;
import item.Charge;
import item.DivingSuit;
import item.Flare;
import item.Food;
import item.Gun;
import item.Item;
import item.Rope;
import item.Shovel;
import item.Spade;
import item.Tent;
import player.Eskimo;
import player.Player;
import player.PolarBear;
import player.Scientist;


public class TestFunctions
{
    private Controller controller;

    public TestFunctions(Controller c)
    {
        controller = c;
    }

	public void testCommand() { //A Player egy körének a függvénye
		System.out.println("TEST COMMAND: ");
		String input;
		Scanner scanner = new Scanner(System.in); //olvassa a standard inputot
		boolean endtest = false;
		while(!endtest) { 
			input = scanner.nextLine();
			String[] command = input.split("\\s+");
			if (command[0].contentEquals("endtest") ) {
				endtest = true;
			} else if (command[0].contentEquals("spawn")) {
				if( command[1].contentEquals("eskimo") || command[1].contentEquals("scientist")) {
					Item item = null;
					if(command.length == 4) {
						if(command[3].contentEquals("shovel") ) {
							item = new Shovel();
						}else if (command[3].contentEquals( "spade")) {
							item = new Spade();
						} else if(command[3].contentEquals("food")) {
							item = new Food();
						}else if(command[3].contentEquals("divingsuit") ) {
							item = new DivingSuit();
						}else if(command[3].contentEquals("rope") ) {
							item = new Rope();
						} else if(command[3].contentEquals("tent") ) {
							item = new Tent();
						} else if(command[3].contentEquals("charge") ) {
							item = new Charge();
						} else if(command[3].contentEquals("flare") ) {
							item = new Flare();
						} else if(command[3].contentEquals("gun") ) {
							item = new Gun();
						}
					}
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					if(command[1].contentEquals("eskimo") ) {
						SpawnEskimo(f,item);
					} else if(command[1].contentEquals("scientist") ) {
						SpawnScientist(f, item);
					}
					
				} else if(command[1].contentEquals("polarbear") ) {
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SpawnPolarBear(f);
					
				} else if(command[1].contentEquals("item") ) {
					int field = Integer.parseInt(command[3]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					if(command[2].contentEquals("shovel") ) {
						SpawnItem(f, new Shovel());
					}else if (command[2].contentEquals("spade") ) {
						SpawnItem(f, new Spade());
					} else if(command[2]== "food") {
						SpawnItem(f, new Food());
					}else if(command[2].contentEquals("divingsuit") ) {
						SpawnItem(f, new DivingSuit());
					}else if(command[2].contentEquals("rope") ) {
						SpawnItem(f, new Rope());
					} else if(command[2].contentEquals("tent") ) {
						SpawnItem(f, new Tent());
					} else if(command[2].contentEquals("charge") ) {
						SpawnItem(f, new Charge());
					} else if(command[2].contentEquals("flare") ) {
						SpawnItem(f, new Flare());
					} else if(command[2].contentEquals("gun") ) {
						SpawnItem(f, new Gun());
					} else {
						System.out.println("No such item!");
					}
				} else {
					System.out.println("This cannot be spawned");
				}
				
			} else if(command[0].contentEquals("polarbear") ) {
				int dir = Integer.parseInt(command[1]);
				controller.getPolarBear().Move(dir);
			} else if(command[0].contentEquals("set")){
				if(command[1].contentEquals("igloo") ) {
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SetIgloo(f);
				} else if(command[1].contentEquals("tent") ) {
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SetTent(f);
				} else {
					System.out.println("No such thing can be set");
				}
				
			} else if(command[0].contentEquals("snowstorm") ) {
				int howmany = command.length - 1;
				for(int i = 0; i < howmany ; ++i) {
					int field = Integer.parseInt(command[i+1]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SpawnSnowStorm(f);
				}
				System.out.print("Snowstorm affected fields: ");
				for(int i = 1; i < command.length; ++i) {
					System.out.print(command[i] + " ");
				}
				System.out.println();
			} else {
				System.out.println("Invalid command!");
			}
		}
	}
	
    public void SpawnEskimo(Field field, Item item)     //Eszkimo lehelyezese adott mezore
    {
        Eskimo eskimo = new Eskimo(controller, field);
        eskimo.AddItem(item);
        System.out.println("Eskimo spawned");
    }

    public void SpawnScientist(Field field, Item item)  //Kutato lehelyezese adott mezore
    {
        Scientist scientist = new Scientist(controller, field);
        scientist.AddItem(item);
        System.out.println("Scientist spawned");
    }

    public void SpawnPolarBear(Field field)             //Jegesmaci lehelyezese adott mezore
    {
    	if(controller.getPolarBear() != null) {
    		System.out.println("There's already a polarbear");
    	} else {
    		PolarBear polarbear = new PolarBear(field, controller);
    		System.out.println("Polarbear spawned");
    	}
    }

    public void SetTent(Field field)                  //Sator lehelyezese adott mezore
    {
        field.setHasTent(true);
        System.out.println("Tent set");
    }

    public void SetIgloo(Field field)                  //Iglu lehelyezese adott mezore
    {
        field.setHasIgloo(true);
        System.out.println("Igloo built");
    }

    public void SpawnItem(Field field, Item item)        //Sator lehelyezese adott mezore
    {
        field.PushItem(item);
        System.out.println("Item spawned");
    }

    public void SpawnSnowStorm(Field field)              //Iglu lehelyezese adott mezore
    {
        field.IncrementSnow();
        for (Player player : field.getPlayers())
        {
            player.decrementHealth();
        }
    }
}
