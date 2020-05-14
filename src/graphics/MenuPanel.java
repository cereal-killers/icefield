package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MenuPanel extends JPanel {
    private BufferedImage backGround = new BufferedImage(1200, 720, BufferedImage.TYPE_INT_RGB);
    private ArrayList<JButton> menuButtons = new ArrayList<JButton>();
    private JFrame frame;
    private JPanel panel;

    public MenuPanel(JFrame _frame) {
        super();
        frame = _frame;

        try
        {
            backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\menu.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        frame.invalidate();

        //gombok letrehozasa
        JButton newGame = new JButton();
        JButton settings = new JButton();
        JButton bestScores = new JButton();
        JButton exit = new JButton();

        /*
        final JTextField textbox = new JTextField();
        textbox.setBounds(50, 50, 150, 20);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textbox.setText("NewGame"); // debug

                newGame();
            }
        });
        */

        //gombok felparameterezese
        newGame.setBounds(370, 267, 573, 75);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);
        newGame.setActionCommand("newgame");

        settings.setBounds(370, 363, 573, 75);
        settings.setOpaque(false);
        settings.setContentAreaFilled(false);
        settings.setBorderPainted(false);
        settings.setActionCommand("settings");

        bestScores.setBounds(370, 467, 573, 75);
        bestScores.setOpaque(false);
        bestScores.setContentAreaFilled(false);
        bestScores.setBorderPainted(false);
        bestScores.setActionCommand("bestscores");

        exit.setBounds(370, 567, 573, 75);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setActionCommand("exit");

        //gombok listahoz valo hozzaadasa
        menuButtons.add(newGame);
        menuButtons.add(settings);
        menuButtons.add(bestScores);
        menuButtons.add(exit);

        panel = (JPanel) frame.getContentPane();

        panel.add(newGame);
        panel.add(settings);
        panel.add(bestScores);
        panel.add(exit);

        //panel.add(textbox);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(backGround, 0,0, this);
    }

    //getterek & setterek
    public BufferedImage getBackGround() {
        return backGround;
    }

    public void setBackGround(BufferedImage backGround) {
        this.backGround = backGround;
    }

    public ArrayList<JButton> getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(ArrayList<JButton> menuButtons) {
        this.menuButtons = menuButtons;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}