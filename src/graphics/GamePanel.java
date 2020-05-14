package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    private BufferedImage backGround;

    public GamePanel()
    {
	    try
	    {
	        backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\background.png"));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backGround, 0,0, this);
    }
}
