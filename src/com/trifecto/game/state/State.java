package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;

import com.trifecto.game.gfx.Background;

public abstract class State {
	
	protected StateManager stateManager;
	
	protected Background background;
	protected String backgroundPath;
	
	protected String questionsPath;
	
	protected Color questionColor;
	protected Font questionFont;
	
	protected Font responseFont;
	
	protected String partName = "";
	
	public State(StateManager stateManager) {
		this.stateManager = stateManager;
		init();
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(java.awt.Graphics2D graphics);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);

}
