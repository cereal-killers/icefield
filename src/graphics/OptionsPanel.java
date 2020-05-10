package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OptionsPanel extends JPanel
{
    private BufferedImage background;
    private JButton ackButton;
    private JTextField nameTextField;
    private JCheckBox music;
    private MenuPanel menuPanel;

    public OptionsPanel(MenuPanel _menuPanel)
    {
        menuPanel = _menuPanel;
    }

    @Override
    public void paint(Graphics g)
    {

    }
}
