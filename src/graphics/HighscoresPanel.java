package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;

import menu.ScoreData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class HighscoresPanel extends JPanel
{
    private BufferedImage backGround;
    private Font AmaticSc;
    private Vector<ScoreData> highscores;

    public HighscoresPanel(Vector<ScoreData> _highscores)
    {
    	this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBounds(0,0, 1200, 720);
        highscores = _highscores;
        try
        {
            backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\highscores.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //namelabelek hozzaadasa
        /*for (int i= 0; i<5;i++)     //később: File-on végigmenni
        {
            JLabel player = new JLabel();
            player.setFont(AmaticSc);
            player.setForeground(Color.WHITE);      //ToDo: szin = (198, 205, 229)
            player.setText("János" + readFile(i) + " kört bírt");
            player.setBounds(460, 250+i*54, 375, 50);
            //player.setSize(375,50);
            //player.setLocation(460, 250+i*54);
            player.setVisible(true);
            //player.setOpaque(false);
            namelabels.add(player);
            this.add(player);
        }*/
        try {
			AmaticSc = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir")+"\\src\\images\\amaticsc.ttf")).deriveFont(48f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    this.setVisible(true);
	    this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backGround, 0,0, this);
        g.setFont(AmaticSc);
        g.setColor(new Color(198, 205, 229));
        for (int i= 0; i< highscores.size() && i < 5;i++){
            int n = highscores.get(i).getName().length() + 5;
            g.drawString(highscores.get(i).getName() + " - " + highscores.get(i).getScore() + " turns", (int)(410+133-((n*(2.3f+(n-10)*0.17f)))) , 290+i*68);
        }
    }

    //filebeolvaso fuggveny
    public int readFile(int line)                       //ToDo
    {
        return line;
    }

    //getterek & setterek
    public BufferedImage getBackGround() {
        return backGround;
    }

    public void setBackGround(BufferedImage backGround) {
        this.backGround = backGround;
    }

    public Font getAmaticSc() {
        return AmaticSc;
    }

    public void setAmaticSc(Font amaticSc) {
        AmaticSc = amaticSc;
    }

}
