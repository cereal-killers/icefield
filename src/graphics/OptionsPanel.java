package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OptionsPanel extends JPanel
{
    private BufferedImage backGround;
    private JTextField nameTextField;
    private JCheckBox music;
    private JButton submitButton;
    private JFrame frame;
    private JPanel panel;

    public OptionsPanel(JFrame _frame)
    {
        frame = _frame;
        panel = (JPanel) frame.getContentPane();
        Font AmaticSc = new Font("Amatic sc", Font.PLAIN, 48);

        try
        {
            backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\options.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        frame.invalidate();

        //Nametextfield beallitasa
        nameTextField.setBounds(255,365,265,60);
        nameTextField.setFont(AmaticSc);
        nameTextField.setOpaque(false);
        panel.add(nameTextField);


        //SubmitButton felparameterezese
        submitButton.setBounds(599, 328, 300, 120);
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setActionCommand("submit");
        panel.add(submitButton);

        //Music CheckBox felparameterezese
        music.setBounds(599, 328, 300, 120);
        music.setIcon(new ImageIcon("src\\images\\musicon.png"));
        music.setSelectedIcon(new ImageIcon("src\\images\\musicoff.png"));
        music.setActionCommand("togglemusic");
        panel.add(music);
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

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JCheckBox getMusic() {
        return music;
    }

    public void setMusic(JCheckBox music) {
        this.music = music;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
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
