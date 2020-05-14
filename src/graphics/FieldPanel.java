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
    private GamePanel currentPanel;
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

    public GamePanel getCurrentPanel () { return currentPanel;}
    public void setCurrentPanel( GamePanel value) { currentPanel = value; }

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
        currentPanel.add(button);
        button.setBounds(x,y,width, height);
        
        button.setActionCommand("field"+num);
        try
        {
            for( Item i : _field.getItems())
                itemImages.add(ImageIO.read(new File("src\\images\\"+i.getName()+".png")));
            for( Player i : _field.getPlayers())
                playerImages.add( ImageIO.read(new File("src\\images\\"+i.getName()+".png ")) );
            if(_field.getPolarBear() != null)
                polarbearImage = ImageIO.read(new File("src\\images\\polarbear.png"));
            if(field.getSnow()>0)
                snowImage = ImageIO.read(new File("src\\images\\snow.png"));
            if(field.getHasTent())
                thingImage = ImageIO.read(new File("src\\images\\tent.png"));
            else if(field.getHasIgloo())
                 thingImage = ImageIO.read(new File("src\\images\\igloo.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g)
    {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        //eszkoz
        g.drawImage(itemImages.peek(), posX, posY, currentPanel);

        //playerek
        if(playerImages.size() == 1)
        {
            g.drawImage(playerImages.get(0), posX + width/2 - 26, posY + height/2 - 112, currentPanel);
        }else
        {
            for(int i=0;i<playerImages.size();i++)
                 g.drawImage(
                    playerImages.get(i),
                    posX + width/2 - 26 + Math.cos( (i*2*Math.PI) / playerImages.size() ) *30,
                    posY + height/2 - 112 + Math.sin ( (i*2*Math.PI) / playerImages.size() ) *30, 
                    currentPanel);
        }

        //polarbear
        g.drawImage(polarbearImage, posX+width/2-54, posY+height/2-112, currentPanel);

        //ho
        g.drawImage(snowImage, posX + width/2 -63, posY + height/2 -38, currentPanel);

        //sator/iglu
        g.drawImage(thingImage, posX + width - 72, posY + height -36, currentPanel);
    }


}