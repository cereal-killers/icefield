package item;

import icefield.Controller;

public class Rocket {
	private Controller controller;
	private Gun gun;
	private Flare flare;
	private Charge charge;

    public Rocket(Controller controller, Gun gun, Flare flare, Charge charge) {
    	this.controller = controller;
    	this.gun = gun;
    	this.flare = flare;
    	this.charge = charge;
    }
    
    private boolean isOnOneField() {
    	//todo
    	return false;
    }

    public void Assemble() {
		System.out.println("Assemble()");
	}
	
	public void Fire() {
		System.out.println("Fire()");
	}
	
}
