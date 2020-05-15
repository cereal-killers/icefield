package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EndPanel extends JPanel {

	private BufferedImage backGround;
	public EndPanel(boolean win)
	{
		this.setLayout(null);
		this.setBounds(0,0, 1200, 720);
		 try
	     {
			 if( win)
				 backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\win.png"));
			 else
				 backGround= ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\lose.png"));
	     }
	     catch (IOException e)
	     {
	         e.printStackTrace();
	     }
		 this.setVisible(true);
		 this.setFocusable(true);
	}
	
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backGround, 0,0, this);
    }

}
