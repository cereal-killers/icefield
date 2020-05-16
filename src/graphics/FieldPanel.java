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
import sun.font.FontScaler;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
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
        field = _field;
        width = _width;
        height = _height;
        button = new JButton();
        button.setBounds(x,y,width, height);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setActionCommand("field "+num);
        playerImages = new ArrayList<BufferedImage>();
        itemImages = new Stack<BufferedImage>();
    }

    public void paint(Graphics g, JPanel p, Player currentPlayer)
    {
    	itemImages.clear();
    	playerImages.clear();
    	polarbearImage = snowImage = thingImage = null;
    	 try
         {
             for( Item i : field.getItems())
                 itemImages.add(ImageIO.read(new File("src\\images\\"+i.getName()+".png")));

             if(field.getPolarBear() != null)
                 polarbearImage = ImageIO.read(new File("src\\images\\polarbear.png"));
             if(field.getSnow()>0)
                 snowImage = ImageIO.read(new File("src\\images\\snow.png"));
             for( Player i :field.getPlayers())
             {
            	 if (i==currentPlayer)
            		 playerImages.add( ImageIO.read(new File("src\\images\\"+i.getName()+"_selected.png")) );
            	 else
                     playerImages.add( ImageIO.read(new File("src\\images\\"+i.getName()+".png")) );
             }
             if(field.getIsUpsideDown())
             {
                 thingImage = ImageIO.read(new File("src\\images\\hole.png"));
             	playerImages.clear();

                 for( Player i :field.getPlayers())
                 {
                	 if (i==currentPlayer)
                		 playerImages.add( ImageIO.read(new File("src\\images\\"+i.getName()+"_selected_drowned.png")) );
                	 else
                         playerImages.add( ImageIO.read(new File("src\\images\\"+i.getName()+"_drowned.png")) );

                 }
             }
             else if(field.getHasIgloo())
                 thingImage = ImageIO.read(new File("src\\images\\igloo.png"));
             else if(field.getHasTent())
                 thingImage = ImageIO.read(new File("src\\images\\tent_big.png"));
         }catch(IOException e)
         {
             e.printStackTrace();
         }
         //sator/iglu
         if (thingImage!=null && field.getIsUpsideDown()==false)
        	 g.drawImage(thingImage, posX + width - 72, posY+height/2 -36, p);
         
         else if(thingImage!=null && field.getIsUpsideDown()==true)
             g.drawImage(thingImage, posX + width/2 - 72, posY + height/2 -33, p);

         //ho
         if (snowImage!=null && field.getIsUpsideDown()==false)
         g.drawImage(snowImage, posX + width/2 -63, posY + height/2 -38, p);


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
                    (int)(posX + width/2 - 26 + Math.cos( (-i*2*Math.PI) / playerImages.size() ) *30),
                    (int)(posY + height/2 - 112 + Math.sin ( (-i*2*Math.PI) / playerImages.size() ) *30), 
                    p);
        }
        //polarbear
        if (polarbearImage!=null)
        	g.drawImage(polarbearImage, posX+width/2-54, posY+height/2-112, p);
        
        //eszkoz
    	 if (itemImages.size()>0 && snowImage ==null && field.getIsUpsideDown()==false && field.GetIsInspected()==false)
        g.drawImage(itemImages.peek(), posX, posY, p);
    	 else if (itemImages.size()>0 &&  snowImage ==null &&field.GetIsInspected()==true)
    	 {            
    		 for(int i=0;i<itemImages.size();i++)
    			 g.drawImage(itemImages.get(i) , posX +80*i , posY, p);
    	       
    	 }
    	 //teherbiras
    	 if (field.GetIsInspected()==true)
    	 {
    		 try {
				//g.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir")+"\\src\\images\\amaticsc.ttf")).deriveFont(72f));
 				g.setFont(new Font("Arial", Font.BOLD, 72));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 g.setColor(Color.white);
    		 String w = String.valueOf(field.getMaxWeight());
    		 if (field.getMaxWeight()==10)
    			 w = String.valueOf('∞');
    			 
    		 g.drawString(w, posX + width /2,posY +height/2);
    	 }

    }


}