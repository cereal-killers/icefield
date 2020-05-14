package graphics;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import icefield.Controller;
import menu.Menu;

public class Container extends JFrame {

	private JPanel currentpanel = null;
	private GameListener gamelistener;
	private Controller controller;
	
	public Container(Controller c)
	{
		controller = c;
		gamelistener = new GameListener(this);
		this.setBounds(0,0,1200,720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(gamelistener);
		navigate("menu");
	}
	public void navigate(String where)
	{
		if (currentpanel!=null)
			this.remove(currentpanel);
		GamePanel game = null;
		switch(where)
		{
		case "foci":
			game = new GamePanel("foci", controller);
			break;
		case "nagy":
			game = new GamePanel("nagy", controller);
			break;
		case "teszt":
			game = new GamePanel("teszt", controller);
			break;
		case "menu":
			MenuPanel m = new MenuPanel();
			m.addKeyListener(gamelistener);
			for(JButton b : m.getMenuButtons())
			{
				b.addActionListener(gamelistener);
				b.addKeyListener(gamelistener);
			}
			currentpanel = m;
			this.add(m);
			break;
		case "options":
			OptionsPanel o = new OptionsPanel();
			o.addKeyListener(gamelistener);
			o.getMusic().addActionListener(gamelistener);
			o.getMusic().addKeyListener(gamelistener);
			currentpanel = o;
			this.add(o);
			break;
		case "highscores":
			HighscoresPanel h = new HighscoresPanel();
			h.addKeyListener(gamelistener);
			currentpanel = h;
			this.add(h);
			break;
		case "choosemap":
			MapPanel mp = new MapPanel();
			mp.addKeyListener(gamelistener);
			for( JButton b : mp.getMapButtons())
			{
				b.addActionListener(gamelistener);
				b.addKeyListener(gamelistener);
			}
			currentpanel = mp;
			this.add(mp);
			break;
		}
		// ha gamepanelre megyunk
		if (game!=null)
		{
			game.addKeyListener(gamelistener);
			game.getInventory().getEndTurnButton().addActionListener(gamelistener);
			game.getInventory().getEndTurnButton().addKeyListener(gamelistener);
			for(JButton b: game.getInventory().getItemButtons())
			{
				b.addActionListener(gamelistener);
				b.addKeyListener(gamelistener);
			}
			for(FieldPanel f: game.getFields())
			{
				f.getButton().addActionListener(gamelistener);
				f.getButton().addKeyListener(gamelistener);
				f.getButton().addMouseListener(gamelistener);
			}
			currentpanel = game;
			this.add(game);
		}
		//refresh
		repaint();
	}
	
    @Override
    public void paint(Graphics g)
    {
    	currentpanel.repaint();
    }
	
}
