package com.trifecto.game.state;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.trifecto.game.gfx.Background;
import com.trifecto.game.main.MainComponent;

public class MenuState extends State {
	
	private Background background;
	
	private String backgroundPath;
	
	private int currChoice = 0;
	private String[] options = {"Start", "Options", "Quit"};
	
	private Color titleColor;
	private Font titleFont;
	private Font selectionFont;
	
	private static final String TITLE = "Foshizzle";
	
	public MenuState(StateManager stateManager) {
		this.stateManager = stateManager;
		init();
	}

	@Override
	public void init() {
		
		// C:/Users/Daniel/Documents/EclipseProjects/TextEngine2/assets
		backgroundPath = "C:/Users/Daniel/Documents/EclipseProjects/TextEngine2/assets/images/QuakeLogo.jpg";
		
		try {
            background = new Background(backgroundPath, 1);
            background.setVector(-0.1, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Press Start 2P", Font.PLAIN, 24);
            selectionFont = new Font("Press Start 2P", Font.PLAIN, 10);
        } catch(Exception e) {
            e.printStackTrace();
        }		
	}

	@Override
	public void tick() {
		background.tick();
	}

	@Override
	public void render(Graphics2D graphics) {
		//System.out.println("MenuState render...");
		
		// Draw background
        background.render(graphics);
        
        // Calculations for centering fonts on the JFrame
        FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
        FontMetrics selectionFontMetrics = graphics.getFontMetrics(selectionFont);
        int titleLength = titleFontMetrics.stringWidth(TITLE);
        int selectionLength;
        
        // Draw title
        graphics.setColor(titleColor);
        graphics.setFont(titleFont);
        graphics.drawString(TITLE, (MainComponent.WIDTH / 2) - (titleLength / 2), 70);
        
        // Draw menu options
        graphics.setFont(selectionFont);
        for(int i = 0; i < options.length; i++) {
        	selectionLength = selectionFontMetrics.stringWidth(options[i]);
            if(i == currChoice) {
                graphics.setColor(Color.WHITE);
            } else {
                graphics.setColor(Color.RED);
            }
            graphics.drawString(options[i], (MainComponent.WIDTH / 2) - (selectionLength / 2), 140 + i * 15);
        }
	}

	@Override
	public void keyPressed(int key) {
		
		if (key == KeyEvent.VK_ENTER) { select(); }
		
		if (key == KeyEvent.VK_UP) {
			currChoice--;
			if (currChoice == -1) { currChoice = options.length -1; }
		}
		
		if (key == KeyEvent.VK_DOWN) {
			currChoice++;
			if (currChoice == options.length) { currChoice = 0; }
		}
			
	}

	@Override
	public void keyReleased(int key) {		
	}
	
	private void select() {
		
		if (currChoice == 0) {
			stateManager.setState(StateManager.PLAYSTATE);
		}
		
		if (currChoice == 1) {
			// Do nothing...yet
		}
		
		if (currChoice == 2) {
			System.exit(0);
		}
		
	}

}
