package graphics;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import icefield.Controller;
import menu.Menu;

public class Container extends JFrame {

	private JPanel currentpanel;
	
	private GameListener gamelistener;
	
	Container()
	{
		gamelistener = new GameListener(new Controller(), this, new Menu());
		navigate("menu");
		
	}
	public void navigate(String where)
	{
		switch(where)
		{
		case "game":
			GamePanel g = new GamePanel();
			currentpanel = g;
			break;
		case "menu":
			MenuPanel m = new MenuPanel(this);
			for(JButton j : m.getMenuButtons())
			{
				j.addActionListener(gamelistener);
			}
			currentpanel = m;

			break;
		case "options":
			OptionsPanel o = new OptionsPanel(this);
			o.getMusic().addActionListener(gamelistener);
			currentpanel = o;

			break;
		case "highscores":
			currentpanel = new HighschoresPanel(this);
			break;
		case "choosemap":
			MapPanel mp = new MapPanel();
			for( JButton b : mp.getMapButtons())
			{
				b.addActionListener(gamelistener);
			}
			currentpanel = mp;
			break;
		}
	}
	
    @Override
    public void paint(Graphics g)
    {
        currentpanel.repaint();
    }
	
}
