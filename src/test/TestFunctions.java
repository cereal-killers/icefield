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
			if (command[0] == "endtest") {
				endtest = true;
			} else if (command[0] == "spawn") {
				if( command[1] == "eskimo" || command[1] == "scientist") {
					Item item = null;
					if(command.length == 4) {
						if(command[3] == "shovel") {
							item = new Shovel();
						}else if (command[3] == "spade") {
							item = new Spade();
						} else if(command[3]== "food") {
							item = new Food();
						}else if(command[3] == "divingsuit") {
							item = new DivingSuit();
						}else if(command[3] == "rope") {
							item = new Rope();
						} else if(command[3] == "tent") {
							item = new Tent();
						} else if(command[3] == "charge") {
							item = new Charge();
						} else if(command[3] == "flare") {
							item = new Flare();
						} else if(command[3] == "gun") {
							item = new Gun();
						}
					}
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					if(command[1] == "eskimo") {
						SpawnEskimo(f,item);
					} else if(command[1] == "scientist") {
						SpawnScientist(f, item);
					}
					
				} else if(command[1] == "polarbear") {
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SpawnPolarBear(f);
				} else if(command[1] == "item") {
					int field = Integer.parseInt(command[3]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					if(command[2] == "shovel") {
						SpawnItem(f, new Shovel());
					}else if (command[2] == "spade") {
						SpawnItem(f, new Spade());
					} else if(command[2]== "food") {
						SpawnItem(f, new Food());
					}else if(command[2] == "divingsuit") {
						SpawnItem(f, new DivingSuit());
					}else if(command[2] == "rope") {
						SpawnItem(f, new Rope());
					} else if(command[2] == "tent") {
						SpawnItem(f, new Tent());
					} else if(command[2] == "charge") {
						SpawnItem(f, new Charge());
					} else if(command[2] == "flare") {
						SpawnItem(f, new Flare());
					} else if(command[2] == "gun") {
						SpawnItem(f, new Gun());
					} else {
						System.out.println("No such item!");
					}
				} else {
					System.out.println("This cannot be spawned");
				}
				
			} else if(command[0] == "polarbear") {
				
			} else if(command[0] == "set"){
				if(command[1] == "igloo") {
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SetIgloo(f);
				} else if(command[1] == "tent") {
					int field = Integer.parseInt(command[2]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SetTent(f);
				} else {
					System.out.println("No such thing can be set");
				}
				
			} else if(command[0] == "snowstorm") {
				int howmany = command.length - 1;
				for(int i = 0; i< howmany ; ++i) {
					int field = Integer.parseInt(command[i+1]);
					Field f = controller.getFields().get(field); // egyelőre nincs hibakezelés
					SpawnSnowStorm(f);
				}
			} else {
				System.out.println("Invalid command!");
			}
		}
	}
	
    public void SpawnEskimo(Field field, Item item)     //Eszkimo lehelyezese adott mezore
    {
        Eskimo eskimo = new Eskimo(controller, field);
        eskimo.AddItem(item);
    }

    public void SpawnScientist(Field field, Item item)  //Kutato lehelyezese adott mezore
    {
        Scientist scientist = new Scientist(controller, field);
        scientist.AddItem(item);
    }

    public void SpawnPolarBear(Field field)             //Jegesmaci lehelyezese adott mezore
    {
        PolarBear polarbear = new PolarBear(field, controller);
    }

    public void SetTent(Field field)                  //Sator lehelyezese adott mezore
    {
        field.setHasTent(true);
    }

    public void SetIgloo(Field field)                  //Iglu lehelyezese adott mezore
    {
        field.setHasIgloo(true);
    }

    public void SpawnItem(Field field, Item item)        //Sator lehelyezese adott mezore
    {
        field.PushItem(item);
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
