package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class MenuPanel extends JPanel
{
    private BufferedImage background;
    private ArrayList<JButton> menuButtons = new ArrayList<JButton>();
    //private Container container;      //specko beimportalas mert a java.awt-ben is van container
    private HighschoresPanel highscores;
    private OptionsPanel options;

    @Override
    public void paint(Graphics g)
    {

    }

    public void newGame()
    {
    //    container = new Container();
    }

    public void options()
    {
        options = new OptionsPanel(this);
    }

    public void showHighschores()
    {
        highscores = new HighschoresPanel(this);
    }

    public void exit()
    {

    }
}
