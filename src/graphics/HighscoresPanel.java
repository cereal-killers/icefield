package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HighscoresPanel extends JPanel
{
    private BufferedImage backGround;
    private ArrayList<JLabel> namelabels = new ArrayList<JLabel>();
    private Font AmaticSc;
    private JPanel valami;

    public HighscoresPanel()
    {
    	this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBounds(0,0, 1200, 720);
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
        for (int i= 0; i<5;i++){
        	{
        		String s ="10karakter "  + readFile(i) + " kört bírt";
            g.drawString(s, (int)(410+133-(s.length()*(2.3f+(s.length()-10)*0.17f))) , 290+i*68);
        	}
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

    public ArrayList<JLabel> getNamelabels() {
        return namelabels;
    }

    public void setNamelabels(ArrayList<JLabel> namelabels) {
        this.namelabels = namelabels;
    }

    public Font getAmaticSc() {
        return AmaticSc;
    }

    public void setAmaticSc(Font amaticSc) {
        AmaticSc = amaticSc;
    }

}
