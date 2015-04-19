package com.trifecto.game.state;

import java.util.ArrayList;

public class StateManager {

	// Holds all of the states
	private ArrayList<State> states;
	
	// Need an index of the state i.e. "currentState"
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int PQ1 = 2;
	public static final int PQ2 = 3;
	public static final int PQ3 = 4;
	
	public StateManager() {
		states = new ArrayList<State>();
		currentState = MENUSTATE;
		states.add(new MenuState(this));
		states.add(new PlayState(this));
		states.add(new ParticipantQ1(this));
	}
	
	public void setState(int state) {
		currentState = state;
		
		// Initialize the state that is current
		// states.get(currentState).init();
	}
	
	public void tick() {
		states.get(currentState).tick();
	}
	
	public void render(java.awt.Graphics2D graphics) {
		//System.out.println("StateManager render...");
		states.get(currentState).render(graphics);
	}
	
	public void keyPressed(int key) {
		states.get(currentState).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		states.get(currentState).keyReleased(key);
	}
}
