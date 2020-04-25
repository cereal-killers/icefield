package test;

import field.Field;
import icefield.Controller;
import item.Item;
import player.Eskimo;
import player.Player;
import player.PolarBear;
import player.Scientist;

import java.util.Vector;

public class TestFunctions
{
    private Controller controller;

    public TestFunctions(Controller c)
    {
        controller = c;
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

    public void SpawnTent(Field field)                  //Sator lehelyezese adott mezore
    {
        field.setHasTent(true);
    }

    public void SpawnIgloo(Field field)                  //Iglu lehelyezese adott mezore
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
