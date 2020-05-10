package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HighschoresPanel extends JPanel
{
    private BufferedImage background;
    private ArrayList<JLabel> namelabels;
    private ArrayList<JLabel> scorelabels;
    private JButton backButton;
    private MenuPanel menuPanel;

    public HighschoresPanel(MenuPanel _menuPanel)
    {
        menuPanel = _menuPanel;
    }

    @Override
    public void paint(Graphics g)
    {

    }
}
