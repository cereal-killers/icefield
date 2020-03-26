package icefield;

import field.Field;
import item.Rocket;
import player.Eskimo;
import player.Scientist;

import java.util.Vector;

public class Controller {                           //Kontroller osztaly inicializalasa

    Vector<Field> Fields = new Vector<>();          //A jatek mezoinek eltarolasa



    public void SnowStorm()                         //A jatekban szereplo hoviharokat lebonyolito fuggveny
    {
        //ToDo
    }

    public void Start()
    {
        //Map letrehozasa
        Field field1 = new Field();
        Field field2 = new Field();
        Field field3 = new Field();
        Field field4 = new Field();
        Field field5 = new Field();
        Field field6 = new Field();
        Field field7 = new Field();
        Field field8 = new Field();
        Field field9 = new Field();
        Field field10 = new Field();
        Field field11 = new Field();
        Field field12 = new Field();
        Field field13 = new Field();
        Field field14 = new Field();
        Field field15 = new Field();
        Field field16 = new Field();

        //fieldek maphoz valo hozzaadasa
        Fields.add(field1);
        Fields.add(field2);
        Fields.add(field3);
        Fields.add(field4);
        Fields.add(field5);
        Fields.add(field6);
        Fields.add(field7);
        Fields.add(field8);
        Fields.add(field9);
        Fields.add(field10);
        Fields.add(field11);
        Fields.add(field12);
        Fields.add(field13);
        Fields.add(field14);
        Fields.add(field15);
        Fields.add(field16);



        //raketa letrehozasa
        Rocket rocket = new Rocket(this);           //a kontroller atadja magat a rocketnak parameterkent hogy a rocket Finish()-t tudjon hívni.

        //Jatekosok letrehozasa
        Eskimo eskimo1 = new Eskimo(this);            //a kontroller atadja magat a playereknek parameterkent hogy tudjanak finisht hivni
        Eskimo eskimo2 = new Eskimo(this);
        Scientist scientist1 = new Scientist(this);
        Scientist scientist2 = new Scientist(this);
    }

    public boolean ArePlayersTogether()        //talan  ugy lehetne megoldani ha a fieldek jeleznek ha a jatekosok szamaval egyenlo suly lenne rajtuk
    {
        //ToDo
        return true;
    }

    public void Finish()
    {
        //ToDo
    }


}
