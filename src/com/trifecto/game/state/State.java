package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;

import com.trifecto.game.gfx.Background;

public abstract class State {
	
	protected StateManager stateManager;
	
	protected BufferedReader bufferedReader;
	
	protected Background background;
	protected String backgroundPath;
	
	protected static String questionsPath = "assets/questions/Questions.txt";
	protected static String[] questions = new String[15];
	
	protected Color questionColor;
	protected Font questionFont;
	
	protected Font responseFont;
	
	protected static String partName = "";
	protected static String isBro = "";
	protected static int legLength;
	
	public State(StateManager stateManager) {
		this.stateManager = stateManager;
		
		init();
	}
	
	public static String getPartName() {
		return partName;
	}
	
	private static void readFile() {
		
		
		
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(java.awt.Graphics2D graphics);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);

}
