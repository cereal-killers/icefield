package icefield;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import field.Field;
import item.Item;
import player.Player;
import player.PolarBear;
import test.TestFunctions;

public class Controller implements EndOfGame, java.io.Serializable{                          //Kontroller osztaly inicializalasa

    private Vector<Field> fields = new Vector<>();                      //A jatek mezoinek eltarolasa
    private Vector<Player> players = new Vector<>();
    private PolarBear polarBear = new PolarBear();
    private boolean ended = false;
    private transient Random random = new Random();
    private boolean testMode = false;
    private TestFunctions test = new TestFunctions(this);

    //getterek
    public Vector<Field> getFields() { return fields;}
    public Vector<Player> getPlayers() {return players; }
    public PolarBear getPolarBear() {return polarBear;}
    public Random getRandom() {return random; }
    public boolean getEnded() {return ended;}
    public boolean getTestMode() {return testMode;}

    //setterek
    public void setFields(Vector<Field> thing) {fields = thing; }
    public void setPlayers(Vector<Player> thing) { players = thing; }
    public void setPolarBear(PolarBear thing) { polarBear = thing;}
    public void setRandom(Random thing) { random = thing; }
    public void setEnded(boolean thing) { ended = thing; }
    public void setTestMode(boolean value) {
    	testMode = value;
    	System.out.println("Testmode: " + testMode);
    }

    public Controller(){}

    public void viewMap() {
    	for(Field f: fields) {
    		
    		System.out.println("FIELD #" + (fields.indexOf(f)+1) +": mW = " + f.getMaxWeight() + " snow=" + f.getSnow() + " tent=" +f.getHasTent()+ " igloo=" + f.getHasIgloo()+ " iUD="+f.getIsUpsideDown());
    		for(Item i: f.getItems()){
    			System.out.print(i.getName()+ " ");
    		}
    		if(f.getItems().size() == 0) {
    			System.out.print("-");
    		}
    		System.out.print("(" + f.getPlayers().size() + ")");
    		for(Player p: f.getPlayers()){
    			System.out.print(p.getName()+ " ");
    		}
    		System.out.println();
    		String polarbear;
       		if(f.getPolarBear() != null) {
    			polarbear = "true";
    		} else polarbear = "false";
    		System.out.print("PB: " + polarbear);
    		System.out.print("\t");
    		for(Field n: f.getNeighbors()) {
    			System.out.print((fields.indexOf(n)+1)+ " ");
    		}
    		System.out.println();
    		System.out.println();
    	}
    }
    
    private void SnowStorm()                                            //A jatekban szereplo hoviharokat lebonyolito fuggveny
    {
    	if(!testMode) {
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
    	} else {
    		test.testCommand();
    	}
        
    }

    public int Start()
    { 
    	/*//Map letrehozasa
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
        System.out.println("map letrehozva");*/

    	String filename = "nagypalya";
    	ReadController(filename);// beolvassuk az xml filebol a mappat
        System.out.println(filename+" beolvasva.");
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

    private boolean TryFire()
    {
        if(ArePartsTogether() && ArePlayersTogether()) {
            System.out.println("You Won!");
            ended = true;
            return true;
        }
        return false;
    }

    public void Finish()
    {
        System.out.println("You Lose!");
        ended =true;
    }

    private int GameLoop() //a játék ciklusa, mindaddig fut ameddig nem nyer a csapat, vagy meg nem hal valaki.
    {
        int numOfTurns = 0;

        int i;
        while (!ended) {
            for (i = 0; i < players.size() && !ended; i++) { //egymás után jönnek a játékosok
                System.out.println("Player " + (i + 1) + "'s turn");
                players.get(i).Turn();
                
                numOfTurns++;
                if(TryFire()) return numOfTurns;

                if (i == players.size()) //ha elér az utolsó játékosig, akkor vissza megy a players vektor elejére
                    i = 0;
            }
            for (Field field : fields) {
                if (field.getIsUpsideDown()) {
                    ArrayList<Player> playersFell = field.getPlayers(); 
                	for(int j = 0; j < playersFell.size() && !ended; ++j) {
                		if(!playersFell.get(j).getWears_suit()) {
                			Finish();
                		}
                	}
                }
                if(field.getHasTent()) {
                	field.setHasTent(false);

                }
                   
            }
            SnowStorm();
        }
        return -1;
    }
    //Függvény a controller kiírására
    // a palya stringben megadjuk az xml fajl nevet (.xml nelkul)
  	public void WriteController(String palya)
  	{
  	  	FileOutputStream fos;
  		try {
  			fos = new FileOutputStream(System.getProperty("user.dir")+"\\src\\"+palya+".xml");
  		 	XMLEncoder encoder = new XMLEncoder(fos);
  		  	encoder.writeObject(this);
  		  	encoder.close();
  		  	fos.close();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  	}
  	//Függvény a controller beolvasására
    // a palya stringben megadjuk az xml fajl nevet (.xml nelkul)
  	public void ReadController(String palya )
  	{
  	  	if (new File(System.getProperty("user.dir")+"\\src\\"+palya+".xml").exists()==false) // ha nem létezik még a fájl
  	  	{
  			System.out.println(System.getProperty("user.dir")+"\\src\\"+palya+".xml file does not exists.");
  			return;
  	  	}
  		try {
  			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\"+palya+".xml");
  		  	XMLDecoder decoder = new XMLDecoder(fis);
  		  	Controller copy = (Controller) decoder.readObject();
  		  	this.ended = copy.getEnded();
  		  	this.players = copy.getPlayers();
  		  	this.fields = copy.getFields();
  		  	for(Player p : players)
  		  		p.setController(this);
  		  	this.polarBear = copy.getPolarBear();
  		  	this.polarBear.setController(this);
  		  	decoder.close();
  		  	fis.close();
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
}