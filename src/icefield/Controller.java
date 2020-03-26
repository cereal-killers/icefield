package icefield;

import field.Field;
import item.Rocket;
import player.Eskimo;
import player.Player;
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
        //ToDo

        //raketa letrehozasa
        Rocket rocket = new Rocket(this);           //a kontroller atadja magat a rocketnak parameterkent hogy a rocket Finish()-t tudjon hívni.

        //Jatekosok letrehozasa
        Eskimo eskimo1 = new Eskimo(this);            //a kontroller atadja magat a playereknek parameterkent hogy tudjanak finisht hivni
        Eskimo eskimo2 = new Eskimo(this);
        Eskimo scientist1 = new Scientist(this);
        Eskimo scientist2 = new Scientist(this);
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
