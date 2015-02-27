package com.trifecto.game.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.trifecto.game.state.StateManager;

public class MainComponent extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	// Dimensions
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final int SCALE = 2;
	
	public static final String NAME = "Text Engine";
	
	// Game Thread
	//private Thread thread;
	private boolean isRunning;
	public int tickCount = 0;
	private int FPS = 60;
	//private int targetTime = (1000 / FPS);
	
	// Image
	private BufferedImage image;
	private Graphics2D graphics;
	
	// State Manager
	private StateManager stateManager;
	
	private JFrame frame;
	
	public MainComponent() {
		// Sets the canvas to one size
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		// Initialize the JFrame
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// Centers the canvas on the JFrame
		frame.add(this, BorderLayout.CENTER);
		
		// Sets the sizes so they are above or at the preferred size specified above
		frame.pack();
		
		// Cannot resize the JFrame
		frame.setResizable(false);
		
		// Centers the JFrame on the screen
		frame.setLocationRelativeTo(null);
		
		// Makes the JFrame visible on the screen
		frame.setVisible(true);
		
		setFocusable(true);
		requestFocus();
	}
	
	public void init() {
		//System.out.println("init() is called...");
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) image.getGraphics();
		stateManager = new StateManager();
	}
	
	public synchronized void start() {
		isRunning = true;
		addKeyListener(this);
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		isRunning = false;
	}

	@Override
	public void run() {
		
		// Initialize private objects
		init();
		
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		
		boolean ticked = false;
		
		while (isRunning) {
			
			long currentTime = System.nanoTime();
			renderToScreen();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			
			while (unprocessedSeconds > secondsPerTick) {
				
				tick();
				
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				
				if (tickCount % 60 == 0) {
					
					System.out.println(frames + " FPS");
					FPS = frames;
					previousTime += 1000;
					frames = 0;
				}
			}
			
			try {
				Thread.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (ticked) {
				render();
				frames++;
			}
			
			render();
			frames++;
		}
	}
	
	public void tick() {
		stateManager.tick();
	}
	
	public void render() {
		//System.out.println("MainComponent render...");
		stateManager.render(graphics);
	}
	
	public void renderToScreen() {
		Graphics graphics2 = getGraphics();
		graphics2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		graphics2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		stateManager.keyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) {
		stateManager.keyReleased(key.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent key) {		
	}

	public static void main(String[] args) {
		new MainComponent().start();
	}


}

