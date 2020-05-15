////
///    ITT MÉG MINDEN VÁLTOZHAT 
////

package graphics;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;

import field.Field;
import graphics.Container;
import icefield.Controller;
import menu.Menu;

public class GameListener implements ActionListener, KeyListener, MouseListener {

	private Container container;
	private Controller controller;
	private PrintStream stdout;
	private InputStream stdin;
	
	public GameListener(Container _container, Controller _controller) {
		this.container = _container;
		stdout = System.out;
		stdin = System.in;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		String[] cmd_from_view = e.getActionCommand().split("\\s+");
		String cmd_to_model = "";
		
		switch(cmd_from_view[0]) {
			case "newgame":{
				cmd_to_model = "1";
				container.navigate("choosemap");
			} break;
			case "options":{
				cmd_to_model = "2";
				container.navigate("options");
			} break;
			case "highscores":{
				cmd_to_model = "3";
				container.navigate("highscores");
			} break;
			case "exit":{
				cmd_to_model = "4"; //talán az ablakkal is külön kell majd valamit kezdeni?
			} break;
			case "submit":{
				String newName = cmd_from_view[1];
				cmd_to_model = "1" + System.lineSeparator() + newName;
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
				cmd_to_model = "remove snow";
			}break;
			case "build_igloo":{
				cmd_to_model = "build igloo";
			}break;
			case "pick_up_item":{
				cmd_to_model = "pick up item";
			}break;
			case "menu":{
				cmd_to_model = "menu";
			}break;
			case "end_turn": {
				cmd_to_model = "end turn";
			}break;
			case "nagy": {
				cmd_to_model = "nagypalya";
				container.navigate("nagy");
			}break;
			case "foci": {
				cmd_to_model = "focipalya";
				container.navigate("foci");
			}break;
			case "teszt": {
				cmd_to_model = "tesztpalya";
				container.navigate("teszt");
			}break;
			case "move": {
				int globalIndex = Integer.parseInt(cmd_from_view[1]); // 0-tól indul
				Field moveTo = controller.getFields().get(globalIndex); //0-tól indul
				int direction = controller.getCurrentPlayer().getCurrentField().getNeighbors().indexOf(moveTo); // 0-tól indul
				int dirToModel = direction + 1; // a move parancsot 1-től kell indexelni
				cmd_to_model = "move " + dirToModel; 
			}break;
			case "inspect": {
				cmd_to_model = "tesztpalya";
				container.navigate("teszt");
			}break;
			case "use": {
				String item = cmd_from_view[1];
				cmd_to_model = "use "+ item;
			}break;
			default: {
				
			} break;
		}
		sendCommandToModel(cmd_to_model);
		container.repaint(); //biztos ez kell? nem implementáltad, magától meg nem kérdezi le a modellt
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		String cmd_to_model = "";
		if(key == KeyEvent.VK_ESCAPE) {
			cmd_to_model = "menu";
		}
		sendCommandToModel(cmd_to_model);
		container.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		JButton j = new JButton();
		try {
			j = (JButton) e.getComponent();
		} catch(Exception ex) {}
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			String[] cmd_from_view = j.getActionCommand().split("\\s+"); // az meg lesz oldva hogy ha rossz helyre kattintasz akkor invalid command jön? mondjuk ""
			String cmd_to_model = "";
			
			switch(controller.getCurrentPlayer().getName()) {
				case "Eskimo":{
					int globalIndex = Integer.parseInt(cmd_from_view[1]);
					Field toInspect = controller.getFields().get(globalIndex);
					int direction = controller.getCurrentPlayer().getCurrentField().getNeighbors().indexOf(toInspect);
					int dirToModel = direction + 1;
					cmd_to_model = "inspect " + dirToModel;
				} break;
				case "Scientist":{
					cmd_to_model = "igloo";
				} break;
				default: break;
			}
			sendCommandToModel(cmd_to_model);
			container.repaint();
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void sendCommandToModel(String cmd) {
		cmd = cmd + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(cmd.getBytes()));
		//Scanner scanner = new Scanner(System.in);
		//System.out.println(scanner.nextLine());
		//scanner.close();
		
		stdout.println(cmd); //debug célból
	}
	
	/*private String getOutputFromModel() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //olvas
		PrintStream ps = new PrintStream(baos); //olvas
		PrintStream stdout = System.out; //olvas
		System.setOut(ps); //olvas
		re
	}*/

}
