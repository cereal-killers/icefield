package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HighschoresPanel extends JPanel
{
    private BufferedImage backGround;
    private ArrayList<JLabel> namelabels;
    private JFrame frame;
    private Font AmaticSc = new Font("Amatic sc", Font.PLAIN, 48);
    private JPanel panel;

    public HighschoresPanel(JFrame _frame)
    {
        frame = _frame;
        panel = (JPanel) frame.getContentPane();

        try
        {
            backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\HighscoresPanel.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        frame.invalidate();

        //namelabelek hozzaadasa
        for (int i= 0; i<5;i++)     //később: File-on végigmenni
        {
            JLabel player = new JLabel();
            player.setFont(AmaticSc);
            player.setForeground(Color.WHITE);      //ToDo: szin = (198, 205, 229)
            player.setText("János " + readFile(i) + " kört bírt");
            namelabels.add(player);
            panel.setBounds(460, 250+i*54, 375, 50);
            panel.add(player);
        }

    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backGround, 0,0, this);
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

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Font getAmaticSc() {
        return AmaticSc;
    }

    public void setAmaticSc(Font amaticSc) {
        AmaticSc = amaticSc;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
