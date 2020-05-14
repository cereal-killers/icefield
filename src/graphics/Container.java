package graphics;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import icefield.Controller;
import menu.Menu;

public class Container extends JFrame {

	private JPanel currentpanel;
	private GamePanel game;
	private GameListener gamelistener;
	private Controller controller;

	
	public Container()
	{
		gamelistener = new GameListener(this);
		navigate("menu");
		
	}
	public void navigate(String where)
	{
		this.remove(currentpanel);
		switch(where)
		{
		case "foci":
			GamePanel foci = new GamePanel("foci");
			game = foci;
			for(FieldPanel f: game.getFields())
			{
				f.getButton().addActionListener(gamelistener);
			}
			currentpanel = foci;
			this.add(foci);
			break;
		case "nagy":
			GamePanel nagy = new GamePanel("nagy");
			game = nagy;
			for(FieldPanel f: game.getFields())
			{
				f.getButton().addActionListener(gamelistener);
			}
			currentpanel = g;
			this.add(nagy);
			break;
		case "teszt":
			GamePanel teszt = new GamePanel("teszt");
			game = teszt;
			for(FieldPanel f: game.getFields())
			{
				f.getButton().addActionListener(gamelistener);
			}
			currentpanel = teszt;
			this.add(teszt);
			break;
		case "menu":
			MenuPanel m = new MenuPanel();
			for(JButton j : m.getMenuButtons())
			{
				j.addActionListener(gamelistener);
			}
			currentpanel = m;
			this.add(m);
			break;
		case "options":
			OptionsPanel o = new OptionsPanel();
			o.getMusic().addActionListener(gamelistener);
			currentpanel = o;
			this.add(o);
			break;
		case "highscores":
			Highscorespanel h = new HighscoresPanel();
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
		game.setInventory(new Inventory(controller.getCurrentPlayer())); // TODO NULL eset
        currentpanel.repaint();
    }
    
    public void setController(Controller c)
    {
    	controller = c;
    }
	
}
