////
///    ITT MÉG MINDEN VÁLTOZHAT 
////

package graphics;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import graphics.Container;
import icefield.Controller;
import menu.Menu;

public class GameListener implements ActionListener, KeyListener {

	private Container container;
	
	public GameListener(Container _container) {
		this.container = _container;
	}
	@Override
	public void actionPerformed(ActionEvent e) { // még csak a modellen végzett változtatások vannak, a viewen nem
		String cmd_in = e.getActionCommand();
		String cmd_out = "";
		InputStream stdin = System.in;
		System.setIn(stdin);
		

		switch(cmd_in) {
			case "newgame":{
				cmd_out = "1";
				container.navigate("choosemap");
			} break;
			case "options":{
				cmd_out = "2";
				container.navigate("options");
			} break;
			case "highscores":{
				cmd_out = "3";
				container.navigate("highscores");
			} break;
			case "exit":{
				cmd_out = "4";
			} break;
			case "submit":{
				String newName = optionsPanel.GetNameTextField().getText();
				cmd_out = "1\r\n" + newName;
				container.savePlayerName();
			} break;
			case "togglemusic":{
				boolean music = menu.getOptions().getMusic();
				if(music) {
					cmd_out = "2\r\noff";
				}else {
					cmd_out = "2\r\non";
				}
				container.toggleMusic();
			} break;
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
				if(cmd_out_split[0] == "move") {
						
				} else if(cmd_out_split[0].contentEquals("inspect")) {
						
				} else if(cmd_out_split[0].contentEquals("use")) {
						
				} else if(cmd_out_split[0].contentEquals("test")) {
						
				} 
			} break;
		}

		/*int chosenMap = Integer.parseInt(cmd_in);
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
		*/
		System.setIn(new ByteArrayInputStream(cmd_out.getBytes()));
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		String cmd_out = "";
		InputStream stdin = System.in;
		System.setIn(stdin);
		if(key == KeyEvent.VK_ESCAPE) {
			cmd_out = "menu";
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
