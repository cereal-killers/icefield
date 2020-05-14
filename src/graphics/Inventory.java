package graphics;

import player.Player;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Inventory extends JPanel {
    private Player currentPlayer;
    private BufferedImage background;
    private ArrayList<BufferedImage> itemImages;
    private ArrayList<JButton> itemButtons;
    private JButton endTurnButton;

    public Inventory(){

    }
    public Inventory(Player player){
        currentPlayer = player;
        try {
            background = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\inventory.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        itemImages = new ArrayList<BufferedImage>();
        itemButtons = new ArrayList<JButton>();
        endTurnButton = new JButton("End turn");
        endTurnButton.setActionCommand("endturn");
        endTurnButton.setOpaque(false);
        endTurnButton.setContentAreaFilled(false);
        endTurnButton.setBorderPainted(false);
        endTurnButton.setBounds(505, 585, 183, 53);
        this.add(endTurnButton);
        
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(189, 525 + 
        (currentPlayer.getMaxHealth()-currentPlayer.getHealth())*(195/currentPlayer.getMaxHealth()), 185,
        currentPlayer.getHealth()*(195/currentPlayer.getMaxHealth()));
        g.setColor(Color.blue);
        g.fillRect(832, 525 + (4 - currentPlayer.getEnergy())*(195/4), 197, currentPlayer.getEnergy()*(195/4));
        itemImages.clear();
        for (int i = 0; i < currentPlayer.getItems().size(); ++i){
            try {
                BufferedImage temp = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\"+currentPlayer.getItems().get(i).getName()+".png"));
                itemImages.add(temp);
                JButton item = new JButton();
                item.setActionCommand(currentPlayer.getItems().get(i).getName());
                item.setOpaque(false);
                item.setContentAreaFilled(false);
                item.setBorderPainted(false);
                item.setBounds(417 + i*80, 651, 60, 58);
                this.add(item);
                g.drawImage(temp, 417 + i*80, 651, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        g.drawImage(background, 0, 0, this);
    }

    public Player getCurrentPlayer(){ return currentPlayer;}
    public void setCurrentPlayer(Player player){currentPlayer = player;}

    public BufferedImage getBackground(){ return background;}
    public void setBackground(BufferedImage image){ background = image; }

    public ArrayList<JButton> getItemButtons(){return itemButtons; }
    public void setItemButtons(ArrayList<JButton> buttons){ itemButtons = buttons;}

    public ArrayList<BufferedImage> getItemImages(){return itemImages; }
    public void setItemImages(ArrayList<BufferedImage> images){itemImages = images;}

    public JButton getEndTurnButton(){return endTurnButton;}
    public void setEndTurnButton(JButton button){endTurnButton = button;}


}