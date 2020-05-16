package graphics;

import java.awt.Graphics;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import icefield.Controller;
import menu.Menu;

public class Container extends JFrame {

	private JPanel currentpanel = null;
	private GameListener gamelistener;
	private Menu menu;
	
	public Container(Menu m)
	{
		menu = m;
		try {
			gamelistener = new GameListener(this, m);
		} catch (UnsupportedEncodingException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTitle("Ice Field");
		this.setBounds(0,0,1217,767);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(gamelistener);
	    this.setVisible(true);
	    this.setFocusable(true);
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
			game = new GamePanel("foci", menu.getController());
			break;
		case "nagy":
			game = new GamePanel("nagy", menu.getController());
			break;
		case "teszt":
			game = new GamePanel("teszt", menu.getController());
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
			HighscoresPanel h = new HighscoresPanel(menu.getHighscores());
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
		case "win":
			EndPanel wp = new EndPanel(true);
			wp.addKeyListener(gamelistener);
			currentpanel = wp;
			this.add(wp);
			break;
		case "lose":
			EndPanel lp = new EndPanel(false);
			lp.addKeyListener(gamelistener);
			currentpanel = lp;
			this.add(lp);
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
    	if (currentpanel!=null)
    	currentpanel.repaint();
    }
	
}
