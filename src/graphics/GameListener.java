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
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;

import field.Field;
import icefield.Controller;
import menu.Main;
import menu.Menu;
import menu.Options;

public class GameListener implements ActionListener, KeyListener, MouseListener {

	private Container container;
	private Controller controller;
	private Menu menu;
	private PrintStream stdout;
	private InputStream stdin;
	private String currentPanel;
	private boolean sendCommand = true;
	
	public GameListener(Container _container, Menu _menu) throws UnsupportedEncodingException, InterruptedException {
		this.container = _container;
		this.menu = _menu;
		this.controller = menu.getController();
		stdout = System.out;
		stdin = System.in;
		String cmd = "";
		currentPanel = "menu";
		System.setIn(new ByteArrayInputStream(cmd.getBytes()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		String[] cmd_from_view = e.getActionCommand().split("\\s+");
		System.out.println("command from view: ["+e.getActionCommand()+"]"); //Leónak
		String cmd_to_model = "";

		switch(cmd_from_view[0]) {
			case "newgame":{
				cmd_to_model = "1";
				container.navigate("choosemap");
				currentPanel = "choosemap";
			} break;
			case "options":{
				cmd_to_model = "2";
				container.navigate("options");
				currentPanel = "options";
			} break;
			case "highscores":{
				cmd_to_model = "3";
				container.navigate("highscores");
				currentPanel = "highscores";
			} break;
			case "exit":{
				cmd_to_model = "4"; //talán az ablakkal is külön kell majd valamit kezdeni?
				container.dispose();
			} break;
			case "submit":{
				String newName = cmd_from_view[1];
				cmd_to_model = "1" + System.lineSeparator() + newName;
			} break;
			case "togglemusic":{
				boolean music = menu.getOptions().GetMusic();
				cmd_to_model = "2";
				try {
					sendCommandToModel(cmd_to_model);
				} catch (UnsupportedEncodingException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				synchronized(Options.musicIsReadyToSet) {
					try {
						Options.musicIsReadyToSet.wait();
						//System.out.println("megkaptam1");
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				if(music) {
					cmd_to_model = "off";
				}else {
					cmd_to_model = "on";
				}
				//container.toggleMusic();
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
			case "endturn": {
				cmd_to_model = "end turn";
				if(controller.TryFireWithoutWaiting() || controller.checkIfGameLost()) {
					/*synchronized(Controller.gameEnded) {
						try {
							Controller.gameEnded.wait();
							System.out.println("megkaptam1");
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}*/
					//System.out.println("megkaptam2");
					if(controller.checkIfGameLost()) {
						//System.out.println("megkaptam4");
						container.navigate("lose");
						currentPanel = "lose";
					}else if(controller.getWon()) {
						//System.out.println("megkaptam3");
						container.navigate("win");
						currentPanel = "win";
					} 
				}
			}break;
			case "nagy": {
				
				try {
					sendCommandToModel("nagypalya");
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
					sendCommandToModel("N");
					
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
				} catch (Exception e1) {}
				container.navigate("nagy");
				currentPanel = "nagy";
				sendCommand = false;
				//cmd_to_model = "nagypalya"; // összesen ez az egy sor volt itt
			}break;
			case "foci": {
				try {
					sendCommandToModel("focilabda");
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
					sendCommandToModel("N");
					
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
				} catch (Exception e1) {}
				container.navigate("foci");
				currentPanel = "foci";
				sendCommand = false;
				//container.navigate("foci");
			}break;
			case "teszt": {
				try {
					sendCommandToModel("tesztpalya");
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
					sendCommandToModel("N");
					
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
				} catch (Exception e1) {}
				container.navigate("teszt");
				currentPanel = "teszt";
				sendCommand = false;
				//container.navigate("teszt");
			}break;
			case "field": {
				int globalIndex = Integer.parseInt(cmd_from_view[1]); // 0-tól indul
				if(globalIndex == controller.getFields().indexOf(controller.getCurrentPlayer().getCurrentField())) {
					if(controller.getFields().get(globalIndex).getSnow() == 0) {
						cmd_to_model = "pick up item";
					} else {
						cmd_to_model = "remove snow";
					}
				} else {
					Field moveTo = controller.getFields().get(globalIndex); //0-tól indul
					int direction = controller.getCurrentPlayer().getCurrentField().getNeighbors().indexOf(moveTo); // 0-tól indul
					int dirToModel = direction + 1; // a move parancsot 1-től kell indexelni
					cmd_to_model = "move " + dirToModel; 
				}

			}break;
			case "inspect": {
				cmd_to_model = "tesztpalya";
				container.navigate("teszt");
				currentPanel = "teszt";
			}break;
			case "item": {
				String item = cmd_from_view[1];
				cmd_to_model = "use "+ item;
			}break;
			default: {
				
			} break;
		}
		
		try {

			if(sendCommand) {
				sendCommandToModel(cmd_to_model);
			} else {
				sendCommand = true;
			}
			
			
			/*switch(cmd_from_view[0]) {
				case "nagy":{
					synchronized(Controller.mapLoaded) {
						try {
							Controller.mapLoaded.wait();
							
						} catch (InterruptedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
					sendCommandToModel("N");
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
					container.navigate("nagy");
					currentPanel = "nagy";
					//sendCommandToModel("N");
				} break;
				case "foci":{
					synchronized(Controller.mapLoaded) {
						try {
							Controller.mapLoaded.wait();
							
						} catch (InterruptedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
					sendCommandToModel("N");
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
					container.navigate("foci");
					currentPanel = "foci";
				} break;
				case "teszt":{
					synchronized(Controller.mapLoaded) {
						try {
							Controller.mapLoaded.wait();
						} catch (InterruptedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
					sendCommandToModel("N");
					synchronized(Controller.mapLoaded) {
						Controller.mapLoaded.wait();
					}
					container.navigate("teszt");
					currentPanel = "teszt";
				} break;
				case "endturn":{
					

				} break;
				default:break;
			}*/
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		container.repaint(); //biztos ez kell? nem implementáltad, magától meg nem kérdezi le a modellt
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(e.toString()); //Leónak
		String cmd_to_model = "";
		if(currentPanel.contentEquals("win") || currentPanel.contentEquals("lose")) {
			cmd_to_model = "x";
			container.navigate("menu");
			currentPanel = "menu";
		}
		if(key == KeyEvent.VK_ESCAPE) {
			switch(currentPanel) {
			case "menu": break;
			case "choosemap": break;
			/*case "win": {
				cmd_to_model = "1";
				container.navigate("menu");
				currentPanel = "menu";
			} break;
			case "lose":{
				cmd_to_model = "1";
				container.navigate("menu");
				currentPanel = "menu";
			} break;*/
			case "options":{
				cmd_to_model = "3";
				container.navigate("menu");
				currentPanel = "menu";
			} break;
			case "highscores": {
				cmd_to_model = "1";
				container.navigate("menu");
				currentPanel = "menu";
			} break;
			case "teszt": {
				cmd_to_model = "menu"+System.lineSeparator();
				container.navigate("menu");
				currentPanel = "menu";
			} break;
			case "foci": {
				cmd_to_model = "menu"+System.lineSeparator();
				container.navigate("menu");
				currentPanel = "menu";
			} break;
			case "nagy": {
				cmd_to_model = "menu"+System.lineSeparator();
				container.navigate("menu");
				currentPanel = "menu";
			} break;
			default: break;
			}
		}
		try {
			sendCommandToModel(cmd_to_model);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		container.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println(e.toString()); //Leónak
		JButton j = new JButton();
		try {
			j = (JButton) e.getComponent();
		} catch(Exception ex) {}
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			String[] cmd_from_view = j.getActionCommand().split("\\s+"); // az meg lesz oldva hogy ha rossz helyre kattintasz akkor invalid command jön? mondjuk ""
			String cmd_to_model = "";
			//System.out.println("["+controller.getCurrentPlayer().getName()+"]");
			switch(controller.getCurrentPlayer().getName().toUpperCase()) {
				case "SCIENTIST":{
					int globalIndex = Integer.parseInt(cmd_from_view[1]);
					Field toInspect = controller.getFields().get(globalIndex);
					int direction = controller.getCurrentPlayer().getCurrentField().getNeighbors().indexOf(toInspect);
					int dirToModel = direction + 1;
					cmd_to_model = "inspect " + dirToModel;
				} break;
				case "ESKIMO":{
					cmd_to_model = "igloo";
				} break;
				default: break;
			}
			try {
				sendCommandToModel(cmd_to_model);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	private void sendCommandToModel(String cmd) throws UnsupportedEncodingException, InterruptedException {
		cmd = cmd + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(cmd.getBytes("UTF-8")));
		synchronized(Main.lock) {
			Main.lock.notifyAll();
		}
		stdout.println("command to model: ["+cmd+"]"); //debug célból

	}
	
	/*private String getOutputFromModel() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //olvas
		PrintStream ps = new PrintStream(baos); //olvas
		PrintStream stdout = System.out; //olvas
		System.setOut(ps); //olvas
		re
	}*/

}
