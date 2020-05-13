package graphics;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;

import field.Field;

import java.awt.Graphics;

public class FieldPanel{
    private ArrayList<BufferedImage> playerImages;
    private ArrayList<BufferedImage> itemImages;
    private BufferedImage polarbearImage;
    private JButton button;

    public FieldPanel (Field field, int x, int y, int width, int height, int num)
    {
        button.setBounds(x,y,width, height);
        button.setActionCommand("field"+num);
    }

    public void paint(Graphics g)
    {

    }


}