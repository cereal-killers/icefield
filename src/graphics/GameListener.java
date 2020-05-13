////
///    ITT MÉG MINDEN VÁLTOZHAT 
////

package graphics;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import icefield.Controller;
import menu.Menu;

public class GameListener implements ActionListener, KeyListener {

	private Controller controller;
	private Container container;
	private Menu menu;
	
	
	public GameListener(Controller _controller, Container _container, Menu _menu) {
		this.controller = _controller;
		this.container = _container;
		this.menu = _menu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // még csak a modellen végzett változtatások vannak, a viewen nem
		String cmd_in = e.getActionCommand();
		String cmd_out;
		InputStream stdin = System.in;
		System.setIn(stdin);
		
		switch(container.getCurrentPanel()) {
		case(menupanel):{ // nyilván itt a zárójelben lévő rész változik majd
			switch(cmd_in) {
			case "newgame":{
				//menu.NewGame();
				cmd_out = "1";
				container.navigate("choosemap");
			} break;
			case "options":{
				//menu.Settings();
				cmd_out = "2";
				container.navigate("options");
			} break;
			case "highscores":{
				//menu.ShowBestScores();
				cmd_out = "3";
				container.navigate("highscores");
			} break;
			case "exit":{
				//stream.println("exit");
				//menu.Exit();
				//System.exit(0);
				cmd_out = "4";
			} break;
			default:break;
			}
		} break;
		case(optionspanel):{
			switch(cmd_in) {
			case "submit":{
				string newName = optionsPanel.GetNameTextField().getText();
				//menu.getOptions().SetPlayerName(newName);
				cmd_out = "1\r\n" + newName;
				container.savePlayerName();
			} break;
			case "togglemusic":{
				//menu.getOptions().setMusic(!menu.getOptions().getMusic());
				boolean music = menu.getOptions().getMusic();
				if(music) {
					cmd_out = "2\r\noff";
				}else {
					cmd_out = "2\r\non";
				}
				container.toggleMusic();
			} break;
			default: break;
			}
		} break;
		case(bestscorespanel):{
			// innen nem jön semmi, csak gombnyomás, az a keypressedben van
		} break;
		case(choosemap_panel):{
			int chosenMap = Integer.parseInt(cmd_in);
			switch(chosenMap) {
			case 1: {
				cmd_out= "tesztpalya";
			} break;
			case 2: {
				cmd_out = "focilabda";
			} break;
			case 3: {
				cmd_out = "nagypalya";
			} break;
			default: break;
			}
			container.navigate("game");
		} break;
		case(gamepanel):{
			
			switch(cmd_in) {
			case "remove snow":{
				cmd_out = "remove snow";
			}break;
			case "build igloo":{
				cmd_out = "build igloo";
			}break;
			case "pick up item":{
				cmd_out = "pick up item";
			}break;
			case "menu":{
				cmd_out = "menu";
			}break;
			case "end turn": {
				cmd_out = "end turn";
			}break;
			default: {
				String[] cmd_out_split = cmd_out.split("\\s+");
				if(cmd_out_split[0] = "move") {
					
				} else if(cmd_out_split[0] = "inspect") {
					
				} else if(cmd_out_split[0] = "use") {
					
				} else if(cmd_out_split[0] = "test") {
					
				} 
			}
			}
		} break;
		case(you_won_panel):{
			
		} break;
		case(you_lose panel):{
			
		} break;
		default: break;
		}
		System.setIn(new ByteArrayInputStream(cmd_out.getBytes()));

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		String cmd_out;
		InputStream stdin = System.in;
		System.setIn(stdin);
		switch(container.getCurrentPanel()) {
		case(menupanel):{ // nyilván itt a zárójelben lévő rész változik majd

		} break;
		case(optionspanel):{
			if(key == KeyEvent.VK_ESCAPE) {
				cmd_out=
			}
		} break;
		case(bestscorespanel):{
		} break;
		case(choosemap_panel):{

		} break;
		case(gamepanel):{

		} break;
		case(you_won_panel):{
			
		} break;
		case(you_lose panel):{
			
		} break;
		default: break;
		}
		System.setIn(new ByteArrayInputStream(cmd_out.getBytes()));
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
