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

    public OptionsPanel()
    {
        Font AmaticSc = new Font("Amatic sc", Font.PLAIN, 48);
        this.setLayout(null);
		this.setBounds(0,0, 1200, 720);
        try
        {
            backGround = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\images\\options.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        //Nametextfield beallitasa
        nameTextField = new JTextField();
        nameTextField.setBounds(255,365,265,60);
        nameTextField.setVisible(true);
      //  nameTextField.setFont(AmaticSc);
        this.add(nameTextField);


        //SubmitButton felparameterezese
        submitButton = new JButton();
        submitButton.setBounds(599, 328, 300, 120);
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setActionCommand("submit Player");
        this.add(submitButton);

        //Music CheckBox felparameterezese
        music = new JCheckBox();
        music.setBounds(701, 504, 100, 100);
        music.setIcon(new ImageIcon(System.getProperty("user.dir")+"src\\images\\buttonon.png"));
        music.setSelectedIcon(new ImageIcon(System.getProperty("user.dir")+"src\\images\\buttonoff.png"));
        music.setActionCommand("togglemusic");
        music.setVisible(true);
        this.add(music);
	    this.setVisible(true);
	    this.setFocusable(true);

    }

    @Override
    public void paint(Graphics g)
    {
    	super.paint(g);
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
}
