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
		String[] cmd_in = e.getActionCommand().split("\\s+");
		String cmd_out = "";
		InputStream stdin = System.in;
		System.setIn(stdin);
		

		switch(cmd_in[0]) {
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
				/*String newName = optionsPanel.GetNameTextField().getText();
				cmd_out = "1\r\n" + newName;
				container.savePlayerName();*/
			} break;
			case "togglemusic":{
				/*boolean music = menu.getOptions().getMusic();
				if(music) {
					cmd_out = "2\r\noff";
				}else {
					cmd_out = "2\r\non";
				}
				container.toggleMusic();*/
			} break;
			case "remove_snow":{
				cmd_out = "remove snow";
			}break;
			case "build_igloo":{
				cmd_out = "build igloo";
			}break;
			case "pick_up_item":{
				cmd_out = "pick up item";
			}break;
			case "menu":{
				cmd_out = "menu";
			}break;
			case "end_turn": {
				cmd_out = "end turn";
			}break;
			case "nagy": {
				cmd_out = "nagypalya";
				container.navigate("nagy");
			}break;
			case "foci": {
				cmd_out = "focipalya";
				container.navigate("foci");
			}break;
			case "teszt": {
				cmd_out = "tesztpalya";
				container.navigate("teszt");
			}break;
			case "move": {
				cmd_out = "tesztpalya";
				container.navigate("teszt");
			}break;
			case "inspect": {
				cmd_out = "tesztpalya";
				container.navigate("teszt");
			}break;
			case "use": {
				cmd_out = "tesztpalya";
				container.navigate("teszt");
			}break;
			default: {
				
			} break;
		}
		System.setIn(new ByteArrayInputStream(cmd_out.getBytes()));
		container.repaint();
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
		container.repaint();
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
