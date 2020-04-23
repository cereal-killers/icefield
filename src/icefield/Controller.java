package icefield;

import field.Field;
import item.*;
import player.Eskimo;
import player.Player;
import player.PolarBear;
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

    private void SnowStorm()                         //A jatekban szereplo hoviharokat lebonyolito fuggveny
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

    public int Start()
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
        Spade spade = new Spade();
        Tent tent = new Tent();
        System.out.println("itemek letrehozva");

        //raketa letrehozasa1
        Rocket rocket = new Rocket(this, gun, flare, charge);           //a kontroller atadja magat a rocketnak parameterkent hogy a rocket Finish()-t tudjon hívni.
        System.out.println("raketa letrehozva");

        //Map letrehozasa
        Field field1 = new Field(6, 1, rope);
        Field field2 = new Field(0, 1, null);
        Field field3 = new Field(2, 1, null);
        Field field4 = new Field(6, 1, charge);
        Field field5 = new Field(6, 0, null);
        Field field6 = new Field(3, 2, null);
        Field field7 = new Field(6, 1, divingSuit);
        Field field8 = new Field(6, 1, shovel);
        Field field9 = new Field(6, 1, tent);
        Field field10 = new Field(6, 1, null);
        Field field11 = new Field(3, 1, spade);
        Field field12 = new Field(6, 0, null);
        Field field13 = new Field(2, 3, gun);
        Field field14 = new Field(6, 3, null);
        Field field15 = new Field(6, 2, null);
        Field field16 = new Field(0, 1, null);
        Field field17 = new Field(6, 0, null);
        Field field18 = new Field(2, 3, food);
        Field field19 = new Field(6, 1, null);
        Field field20 = new Field(6, 3, flare);
        Field field21 = new Field(6, 2, null);
        Field field22 = new Field(0, 1, null);
        Field field23 = new Field(0, 1, null);



        //szomszedok beallitasa
        field1.AddNeighbor(field2);
        field1.AddNeighbor(field5);
        field1.AddNeighbor(field8);
        field2.AddNeighbor(field1);
        field2.AddNeighbor(field6);
        field2.AddNeighbor(field5);
        field2.AddNeighbor(field3);
        field3.AddNeighbor(field2);
        field3.AddNeighbor(field7);
        field3.AddNeighbor(field6);
        field3.AddNeighbor(field4);
        field4.AddNeighbor(field3);
        field4.AddNeighbor(field7);
        field5.AddNeighbor(field1);
        field5.AddNeighbor(field2);
        field5.AddNeighbor(field6);
        field5.AddNeighbor(field10);
        field5.AddNeighbor(field9);
        field5.AddNeighbor(field8);
        field6.AddNeighbor(field2);
        field6.AddNeighbor(field3);
        field6.AddNeighbor(field7);
        field6.AddNeighbor(field10);
        field6.AddNeighbor(field5);
        field7.AddNeighbor(field6);
        field7.AddNeighbor(field3);
        field7.AddNeighbor(field4);
        field7.AddNeighbor(field12);
        field7.AddNeighbor(field11);
        field8.AddNeighbor(field1);
        field8.AddNeighbor(field5);
        field8.AddNeighbor(field9);
        field8.AddNeighbor(field23);
        field9.AddNeighbor(field8);
        field9.AddNeighbor(field5);
        field9.AddNeighbor(field10);
        field9.AddNeighbor(field23);
        field10.AddNeighbor(field9);
        field10.AddNeighbor(field5);
        field10.AddNeighbor(field6);
        field10.AddNeighbor(field14);
        field10.AddNeighbor(field18);
        field10.AddNeighbor(field17);
        field11.AddNeighbor(field7);
        field11.AddNeighbor(field14);
        field11.AddNeighbor(field12);
        field12.AddNeighbor(field11);
        field12.AddNeighbor(field7);
        field12.AddNeighbor(field15);
        field13.AddNeighbor(field8);
        field13.AddNeighbor(field16);
        field14.AddNeighbor(field10);
        field14.AddNeighbor(field11);
        field14.AddNeighbor(field18);
        field14.AddNeighbor(field19);
        field15.AddNeighbor(field19);
        field15.AddNeighbor(field22);
        field15.AddNeighbor(field20);
        field15.AddNeighbor(field12);
        field16.AddNeighbor(field13);
        field16.AddNeighbor(field23);
        field16.AddNeighbor(field17);
        field17.AddNeighbor(field16);
        field17.AddNeighbor(field18);
        field17.AddNeighbor(field10);
        field18.AddNeighbor(field17);
        field18.AddNeighbor(field10);
        field18.AddNeighbor(field14);
        field18.AddNeighbor(field19);
        field18.AddNeighbor(field21);
        field19.AddNeighbor(field18);
        field19.AddNeighbor(field14);
        field19.AddNeighbor(field15);
        field19.AddNeighbor(field22);
        field19.AddNeighbor(field21);
        field20.AddNeighbor(field15);
        field20.AddNeighbor(field22);
        field21.AddNeighbor(field18);
        field21.AddNeighbor(field19);
        field21.AddNeighbor(field22);
        field22.AddNeighbor(field21);
        field22.AddNeighbor(field19);
        field22.AddNeighbor(field15);
        field22.AddNeighbor(field20);
        field23.AddNeighbor(field9);
        field23.AddNeighbor(field16);
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
        fields.add(field17);
        fields.add(field18);
        fields.add(field19);
        fields.add(field20);
        fields.add(field21);
        fields.add(field22);
        fields.add(field23);
        System.out.println("map letrehozva");



        //Jatekosok letrehozasa
        Eskimo eskimo1 = new Eskimo(this, field6);            //a kontroller atadja magat a playereknek parameterkent hogy tudjanak finisht hivni
        Eskimo eskimo2 = new Eskimo(this, field17);
        Scientist scientist1 = new Scientist(this, field5);
        Scientist scientist2 = new Scientist(this, field19);
        PolarBear polarbear = new PolarBear(field12, this);
        players.add(eskimo1);
        players.add(eskimo2);
        players.add(scientist1);
        players.add(scientist2);
        System.out.println("Jatekosok es Maci letrehozva, lehelyezve a palyara");

        return GameLoop();

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

    private int GameLoop() //a játék ciklusa, mindaddig fut ameddig nem nyer a csapat, vagy meg nem hal valaki.
    {
        int numOfTurns = 0;

        int i;
        while (!ended) {
            for (i = 0; i < players.size(); i++) { //egymás után jönnek a játékosok
                System.out.println("Player " + (i + 1) + "'s turn");
                players.get(i).Turn();
                numOfTurns++;
            }
            if (i == players.size()) //ha elér az utolsó játékosig, akkor vissza megy a players vektor elejére
                i = 0;

            int val = random.nextInt(10);
            if (val < 3) SnowStorm();
        }
        return numOfTurns;
    }


}