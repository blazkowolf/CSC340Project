package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.trifecto.game.gfx.Background;

public abstract class State {
	
	protected StateManager stateManager;
	
	protected static BufferedReader bufferedReader;
	
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
	
	protected static String endText;
	
	public State(StateManager stateManager) {
		this.stateManager = stateManager;
		
		readFile();
		
		init();
	}
	
	public void setEndText(String end) {
		endText = end;
	}
	
	public static String getPartName() {
		return partName;
	}
	
	private static void readFile() {
		
		try {
			bufferedReader = new BufferedReader(new FileReader("assets/questions/Questions.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line;
		try {
			
			for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
				questions[i] = line;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(java.awt.Graphics2D graphics);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);

}
