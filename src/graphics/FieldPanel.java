package graphics;

import java.util.ArrayList;
import java.util.Stack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math; 

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import field.Field;
import item.Item;
import player.Player;

import java.awt.Graphics;

public class FieldPanel{
    private ArrayList<BufferedImage> playerImages;
    private Stack<BufferedImage> itemImages;
    private BufferedImage polarbearImage;
    private BufferedImage thingImage;
    private BufferedImage snowImage;
    private JButton button;
    private Field field;
    private int posX;
    private int posY;
    private int width;
    private int height;

    public ArrayList<BufferedImage> getPlayerImages(){ return playerImages; }
    public void setPlayerImages(ArrayList<BufferedImage> value) { playerImages=value;}   
    
    public Stack<BufferedImage> getItemImages() { return itemImages;}
    public void setItemImages(Stack<BufferedImage> value) { itemImages = value;}

    public BufferedImage getPolarbearImage () { return polarbearImage;}
    public void setPolarbearImage( BufferedImage value) { polarbearImage = value;}

    public BufferedImage getThingImage() { return thingImage;}
    public void setThingImage( BufferedImage value) { thingImage=value; }

    public BufferedImage getSnowImage() {return snowImage;}
    public void setSnowImage( BufferedImage value ) {snowImage = value;} 

    public JButton getButton() {return button; }
    public void setButton( JButton value) { button = value;}

    public Field getField () {return field;}
    public void setField (Field value) { field = value; }

    public FieldPanel (Field _field, int x, int y, int _width, int _height, int num)
    {
        posX=x;
        posY=y;
        width = _width;
        height = _height;
        button.setBounds(x,y,width, height);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setActionCommand("field"+num);
    }

    public void paint(Graphics g, JPanel p)
    {
    	itemImages.clear();
    	playerImages.clear();
    	polarbearImage = snowImage = thingImage = null;
    	 try
         {
             for( Item i : field.getItems())
                 itemImages.add(ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\"+i.getName()+".png")));
             for( Player i :field.getPlayers())
                 playerImages.add( ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\"+i.getName()+".png ")) );
             if(field.getPolarBear() != null)
                 polarbearImage = ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\polarbear.png"));
             if(field.getSnow()>0)
                 snowImage = ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\snow.png"));
             if(field.getHasTent())
                 thingImage = ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\tent.png"));
             else if(field.getHasIgloo())
                 thingImage = ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\igloo.png"));
             else if(field.getIsUpsideDown())
                 thingImage = ImageIO.read(new File(System.getProperty("user.dir")+"src\\images\\hole.png"));
         }catch(IOException e)
         {
             e.printStackTrace();
         }

         //sator/iglu
         if (thingImage!=null)
         g.drawImage(thingImage, posX + width - 72, posY + height -36, p);
         
         //ho
         if (snowImage!=null)
         g.drawImage(snowImage, posX + width/2 -63, posY + height/2 -38, p);

        //eszkoz
    	 if (itemImages.size()>0 && snowImage ==null)
        g.drawImage(itemImages.peek(), posX, posY, p);

        //playerek
        if(playerImages.size() == 1)
        {
            g.drawImage(playerImages.get(0), posX + width/2 - 26, posY + height/2 - 112, p);  
        }
        else if(playerImages.size() > 1)
        {
            for(int i=0;i<playerImages.size();i++)
                 g.drawImage(
                    playerImages.get(i),
                    (int)(posX + width/2 - 26 + Math.cos( (i*2*Math.PI) / playerImages.size() ) *30),
                    (int)(posY + height/2 - 112 + Math.sin ( (i*2*Math.PI) / playerImages.size() ) *30), 
                    p);
        }
        //polarbear
        if (polarbearImage!=null)
        	g.drawImage(polarbearImage, posX+width/2-54, posY+height/2-112, p);

    }


}