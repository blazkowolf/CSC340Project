package com.trifecto.game.state;

public abstract class State {
	
	protected StateManager stateManager;
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(java.awt.Graphics2D graphics);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);

}
