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
	
	public Container()
	{
		gamelistener = new GameListener(this);
		navigate("menu");
		
	}
	public void navigate(String where)
	{
		this.removeAll();
		switch(where)
		{
		case "game":
			GamePanel g = new GamePanel();
			g.setInventory(new Inventory(gamelistener.getController().getCurrentPlayer())) ;
			currentpanel = g;
			this.add(g);
			break;
		case "menu":
			MenuPanel m = new MenuPanel(this);
			for(JButton j : m.getMenuButtons())
			{
				j.addActionListener(gamelistener);
			}
			currentpanel = m;
			this.add(m);


			break;
		case "options":
			OptionsPanel o = new OptionsPanel(this);
			o.getMusic().addActionListener(gamelistener);
			currentpanel = o;
			this.add(o);
			break;
		case "highscores":
			Highscorespanel h = new HighscoresPanel(this);
			currentpanel = h;
			this.add(h);
			break;
		case "choosemap":
			MapPanel mp = new MapPanel();
			for( JButton b : mp.getMapButtons())
			{
				b.addActionListener(gamelistener);
			}
			currentpanel = mp;
			this.add(mp);
			break;
		}
	}
	
    @Override
    public void paint(Graphics g)
    {
        currentpanel.repaint();
    }
	
}
