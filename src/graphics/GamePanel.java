package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    private BufferedImage backGround;
    private Inventory inventory;
    private ArrayList<FieldPanel> fields;


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
    public void setInventory(Inventory inv)
    {
    	inventory = inv;
    	this.add(inv);
    }
    
    public void createFields(String palya)
    {
    	
    }
    
    public ArrayList<FieldPanel> getFields(){return fields;}
}
