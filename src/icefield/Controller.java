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
        //Map letrehozasa
        Field field1 = new Field(2, 1, null);
        Field field2 = new Field(5, 2, null);
        Field field3 = new Field(0, 1, null);
        Field field4 = new Field(0, 1, null);
        Field field5 = new Field(5, 0, null);
        Field field6 = new Field(1, 2, null);
        Field field7 = new Field(0, 1, null);
        Field field8 = new Field(5, 1, null);
        Field field9 = new Field(5, 1, null);
        Field field10 = new Field(0, 1, null);
        Field field11 = new Field(0, 1, null);
        Field field12 = new Field(2, 0, null);
        Field field13 = new Field(5, 3, null);



        //szomszedok beallitasa
        field1.AddNeighbor(field2);
        field1.AddNeighbor(field4);
        field1.AddNeighbor(field8);
        field2.AddNeighbor(field1);
        field2.AddNeighbor(field4);
        field2.AddNeighbor(field5);
        field2.AddNeighbor(field3);
        field3.AddNeighbor(field2);
        field3.AddNeighbor(field5);
        field3.AddNeighbor(field6);
        field4.AddNeighbor(field1);
        field4.AddNeighbor(field2);
        field4.AddNeighbor(field5);
        field4.AddNeighbor(field9);
        field4.AddNeighbor(field8);
        field5.AddNeighbor(field2);
        field5.AddNeighbor(field3);
        field5.AddNeighbor(field4);
        field5.AddNeighbor(field6);
        field5.AddNeighbor(field9);
        field5.AddNeighbor(field10);
        field6.AddNeighbor(field3);
        field6.AddNeighbor(field5);
        field6.AddNeighbor(field7);
        field6.AddNeighbor(field10);
        field6.AddNeighbor(field13);
        field7.AddNeighbor(field6);
        field7.AddNeighbor(field13);
        field8.AddNeighbor(field1);
        field8.AddNeighbor(field4);
        field8.AddNeighbor(field9);
        field8.AddNeighbor(field11);
        field9.AddNeighbor(field4);
        field9.AddNeighbor(field5);
        field9.AddNeighbor(field10);
        field9.AddNeighbor(field12);
        field9.AddNeighbor(field11);
        field9.AddNeighbor(field8);
        field10.AddNeighbor(field5);
        field10.AddNeighbor(field6);
        field10.AddNeighbor(field13);
        field10.AddNeighbor(field12);
        field10.AddNeighbor(field9);
        field11.AddNeighbor(field8);
        field11.AddNeighbor(field9);
        field11.AddNeighbor(field12);
        field12.AddNeighbor(field11);
        field12.AddNeighbor(field9);
        field12.AddNeighbor(field10);
        field12.AddNeighbor(field13);
        field13.AddNeighbor(field12);
        field13.AddNeighbor(field10);
        field13.AddNeighbor(field6);
        field13.AddNeighbor(field7);
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
        System.out.println("map letrehozva");

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
                for (Field field : fields)
                {
                    if (getIsUpsideDown)
                        Finish();
                }
                TryFire(ArePlayersTogether());
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