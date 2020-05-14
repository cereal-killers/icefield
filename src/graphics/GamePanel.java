package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import icefield.Controller;

public class GamePanel extends JPanel{
    private BufferedImage backGround;
    private Inventory inventory = null;
    private ArrayList<FieldPanel> fields;
    private Controller controller;

    public GamePanel(String palya)
    {
	    try
	    {
	        backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\background.png"));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backGround, 0,0, this);
    }
    public Inventory getInventory()
    {
    	return inventory;
    }
    public void refreshInventory()
    {
    	if (inventory!=null)
    		this.remove(inventory);
    	inventory = new Inventory(controller.getCurrentPlayer());
    	this.add(inventory);
    }
    
    public void createFields(String palya)
    {
    	switch(palya)
    	{
    	case "foci":
    		fields.add(new FieldPanel(controller.getFields().get(0),246,61,182,119, 0));
    		fields.add(new FieldPanel(controller.getFields().get(0),514,135,150,70, 1));
    		fields.add(new FieldPanel(controller.getFields().get(0),793,162,158,152, 2));

    		break;
    	case "nagy":
    		break;
    	case "teszt":
    		break;
    	}
    }
    public void setController(Controller c){controller = c;}

    
    public ArrayList<FieldPanel> getFields(){return fields;}
}
