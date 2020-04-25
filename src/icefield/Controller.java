package icefield;

import field.Field;
import player.Player;
import java.util.Random;
import java.util.Vector;

public class Controller implements EndOfGame {                          //Kontroller osztaly inicializalasa

    private Vector<Field> fields = new Vector<>();                      //A jatek mezoinek eltarolasa
    private Vector<Player> players = new Vector<>();
    private boolean ended = false;
    private Random random = new Random();


    //getterek
    public Vector<Field> getFields() {
        return fields;
    }
    public Vector<Player> getPlayers() {return players; }
    public Random getRandom() {return random; }
    public boolean getEnded() {
        return ended;
    }

    //setterek
    public void setFields(Vector<Field> thing) {
        fields = thing;
    }
    public void setPlayers(Vector<Player> thing) { players = thing; }
    public void setRandom(Random thing) { random = thing; }
    public void setEnded(boolean thing) { ended = thing; }

    public Controller(){}

    private void SnowStorm()                                            //A jatekban szereplo hoviharokat lebonyolito fuggveny
    {
        for (int i=1;i<(fields.size())/3;i++)
        {
            //mezo kivalasztasa
            int fieldIndex = random.nextInt(fields.size());

            //horeteg novelese
            fields.get(fieldIndex).IncrementSnow();

            //jatekosok testhojenek csokkentese
            for (Player player : fields.get(fieldIndex).getPlayers())
            {
                    player.decrementHealth();
            }
        }
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


    private boolean ArePlayersTogether()        //Azt vizsgalja egy mezon vannak-e a jatekosok
    {
        Field previous = players.get(0).getCurrentField();    //az elozo vizsgalt jatekos fieldje
        for (Player player : players)
        {
            if (player.getCurrentField() != previous)
                return false;
        }
        return true;
    }
    private boolean ArePartsTogether()
    {
        int partsOnField = 0;
        for (Field field : fields) {
            for (Player player : players) {
                if (player.getCurrentField() == field)
                    partsOnField +=  player.RocketParts();
            }
            if(partsOnField == 3)
            return true;
        }
        return false;
    }

    private void TryFire()
    {
        if(ArePartsTogether() && ArePlayersTogether())
            Finish();
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
                for (Field field : fields) {
                    if (field.getIsUpsideDown())
                        Finish();
                }
                TryFire();
                numOfTurns++;

                if (i == players.size()) //ha elér az utolsó játékosig, akkor vissza megy a players vektor elejére
                    i = 0;
            }

            int val = random.nextInt(10);
            if (val < 3) SnowStorm();
        }
        return numOfTurns;
    }
}