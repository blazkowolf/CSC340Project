package com.trifecto.game.state;

import java.util.ArrayList;

public class StateManager {

	// Holds all of the states
	private ArrayList<State> states;
	
	// Need an index of the state i.e. "currentState"
	private int currentState;
	public static final int MENUSTATE = 0;
	//public static final int PLAYSTATE = 1;
	public static final int PQ1 = 1;
	public static final int PQ2 = 2;
	public static final int PQ3 = 3;
	public static final int SQA1 = 4;
	public static final int SQA2 = 5;
	public static final int SQB = 6;
	public static final int SQD = 7;
	public static final int SQC = 8;
	public static final int SQE = 9;
	public static final int SQG = 10;
	public static final int SQF = 11;
	public static final int SQH = 12;
	public static final int SQI = 13;
	public static final int SQJ = 14;
	public static final int SQK = 15;
	public static final int GAMEOVER = 16;
	
	public StateManager() {
		states = new ArrayList<State>();
		currentState = MENUSTATE;
		states.add(new MenuState(this));
		//states.add(new PlayState(this));
		states.add(new ParticipantQ1(this));
		states.add(new ParticipantQ2(this));
		states.add(new ParticipantQ3(this));
		states.add(new StoryQA1(this));
		states.add(new StoryQA2(this));
		states.add(new StoryQB(this));
		states.add(new StoryQD(this));
		states.add(new StoryQC(this));
		states.add(new StoryQE(this));
		states.add(new StoryQG(this));
		states.add(new StoryQF(this));
		states.add(new StoryQH(this));
		states.add(new StoryQI(this));
		states.add(new StoryQJ(this));
		states.add(new StoryQK(this));
		states.add(new GameOver(this));
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
		states.get(currentState).render(graphics);
	}
	
	public void keyPressed(int key) {
		states.get(currentState).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		states.get(currentState).keyReleased(key);
	}
}
