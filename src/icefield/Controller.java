package icefield;

import field.Field;
import item.*;
import player.Eskimo;
import player.Player;
import player.Scientist;

import java.util.Random;
import java.util.Vector;

public class Controller implements EndOfGame{                           //Kontroller osztaly inicializalasa

    private Vector<Field> fields = new Vector<>();          //A jatek mezoinek eltarolasa
    private Vector<Player> players = new Vector<>();
    private boolean ended = false;
    private Random random = new Random();



    public Vector<Field> GetFields()
    {
        return fields;
    }

    public boolean GetEnded()
    {
        return ended;
    }

    public void SnowStorm()                         //A jatekban szereplo hoviharokat lebonyolito fuggveny
    {
        System.out.println("Az erintett mezokon:");
        //Erintett mezokon a jatekosok testhojenek csokkentese
        for (Player player : players)
        {
            if ((player.GetCurrentField() == fields.get(5)) || (player.GetCurrentField() == fields.get(8)) ||
                    (player.GetCurrentField() == fields.get(10)) || (player.GetCurrentField() == fields.get(11)
                    || (player.GetCurrentField() == fields.get(16)) || (player.GetCurrentField() == fields.get(2))))
            player.decrementHealth();
        }
        System.out.println("decrementHealth();");


        //Erintett mezokon horeteg novelese
        fields.get(5).IncrementSnow();
        fields.get(8).IncrementSnow();
        fields.get(10).IncrementSnow();
        fields.get(11).IncrementSnow();
        fields.get(16).IncrementSnow();
        fields.get(2).IncrementSnow();
        System.out.println("IncrementSnow();");



    }

    public void Start()
    {
        //Itemek letrehozasa
        Charge charge= new Charge();
        DivingSuit divingSuit = new DivingSuit();
        Flare flare = new Flare();
        Food food = new Food();
        Food food2 = new Food();
        Gun gun= new Gun();
        Rope rope = new Rope();
        Shovel shovel = new Shovel();
        System.out.println("itemek letrehozva");

        //raketa letrehozasa1
        Rocket rocket = new Rocket(this, gun, flare, charge);           //a kontroller atadja magat a rocketnak parameterkent hogy a rocket Finish()-t tudjon hívni.
        System.out.println("raketa letrehozva");

        //Map letrehozasa
        Field field1 = new Field(5, 1, flare);
        Field field2 = new Field(1, 2, rope);
        Field field3 = new Field(5, 0, null);
        Field field4 = new Field(3, 1, charge);
        Field field5 = new Field(5, 0, divingSuit);
        Field field6 = new Field(5, 2, null);
        Field field7 = new Field(4, 0, null);
        Field field8 = new Field(0, 1, null);
        Field field9 = new Field(2, 1, food);
        Field field10 = new Field(0, 1, null);
        Field field11 = new Field(3, 1, shovel);
        Field field12 = new Field(5, 0, null);
        Field field13 = new Field(5, 3, food2);
        Field field14 = new Field(5, 3, null);
        Field field15 = new Field(5, 3, gun);
        Field field16 = new Field(3, 0, null);
        System.out.println("map letrehozva");


        //szomszedok beallitasa
        field1.SetNeighbors(null, field2, field5, null);
        field2.SetNeighbors(null, field3, field6, field1);
        field3.SetNeighbors(null, field4, field7, field2);
        field4.SetNeighbors(null, null, field8, field3);
        field5.SetNeighbors(field1, field6, field9, null);
        field6.SetNeighbors(field2, field7, field10, field5);
        field7.SetNeighbors(field3, field8, field11, field6);
        field8.SetNeighbors(field4, null, field12, field7);
        field9.SetNeighbors(field5, field10, field12, null);
        field10.SetNeighbors(field6, field11, field12, field9);
        field11.SetNeighbors(field7, field12, field12, field10);
        field12.SetNeighbors(field8, null, field12, field11);
        field13.SetNeighbors(field9, field14, null, null);
        field14.SetNeighbors(field10, field15, null, field13);
        field15.SetNeighbors(field11, field16, null, field14);
        field16.SetNeighbors(field12, null, null, field15);
        System.out.println("Szomszedok beallitva");




        //fieldek maphoz valo hozzaadasa
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);
        fields.add(field9);
        fields.add(field10);
        fields.add(field11);
        fields.add(field12);
        fields.add(field13);
        fields.add(field14);
        fields.add(field15);
        fields.add(field16);




        //Jatekosok letrehozasa
        Eskimo eskimo1 = new Eskimo(this, field6);            //a kontroller atadja magat a playereknek parameterkent hogy tudjanak finisht hivni
        Eskimo eskimo2 = new Eskimo(this, field6);
        Scientist scientist1 = new Scientist(this, field6);
        Scientist scientist2 = new Scientist(this, field6);
        players.add(eskimo1);
        players.add(scientist1);
        players.add(eskimo2);
        players.add(scientist2);
        System.out.println("Jatekosok letrehozva, lehelyezve a palyara");
        eskimo1.AddItem(food);
        eskimo1.AddItem(gun);
        GameLoop();
        
    }

    public boolean ArePlayersTogether()        //Azt vizsgalja egy mezon vannak-e a jatekosok
    {
        Field previous = players.get(0).GetCurrentField();    //az elozo vizsgalt jatekos fieldje
        for (Player player : players)
        {
            if (player.GetCurrentField() != previous)
                return false;
        }
        return true;
    }

    public void Finish()
    {
        System.out.println("You Won!");
        ended =true;
    }
    
    public void GameLoop() { //a játék ciklusa, mindaddig fut ameddig nem nyer a csapat, vagy meg nem hal valaki.
    	int i;
    	while (ended == false) { 
    		for (i = 0; i < players.size(); i++) { //egymás után jönnek a játékosok
    			System.out.println("Player " + (i + 1) + "'s turn");
    			players.get(i).Turn();
    		}
    		if (i == players.size()) //ha elér az utolsó játékosig, akkor vissza megy a players vektor elejére
    			i = 0;
    		
            int val = random.nextInt(10);
            if (val < 3) SnowStorm();
    	}
    }


}
