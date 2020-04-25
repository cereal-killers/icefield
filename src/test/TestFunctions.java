package test;

import field.Field;
import icefield.Controller;
import item.Item;
import player.PolarBear;

import java.util.Vector;

public class TestFunctions
{
    private Controller controller;

    public TestFunctions(Vector<Field> fields)
    {

    }

    public void SpawnEskimo(Field field, Item item)     //Eszkimo lehelyezese adott mezore
    {

    }

    public void SpawnScientist(Field field, Item item)  //Kutato lehelyezese adott mezore
    {

    }

    public void SpawnPolarBear(Field field)             //Jegesmaci lehelyezese adott mezore
    {
        PolarBear polarbear = new PolarBear(field, controller);
    }

    public void SpawnTent(Field field)                  //Sator lehelyezese adott mezore
    {

    }

    public void SpawnIgloo(Field field)                  //Iglu lehelyezese adott mezore
    {

    }

    public void SpawnItem(Field field, Item item)        //Sator lehelyezese adott mezore
    {

    }

    public void SpawnSnowStorm(Field field)              //Iglu lehelyezese adott mezore
    {

    }
}
