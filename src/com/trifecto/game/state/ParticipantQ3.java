package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.trifecto.game.gfx.Background;
import com.trifecto.game.main.MainComponent;

public class ParticipantQ3 extends State {
	
	private int currChoice = 0;
	private String [] options = { "a) Long enough", "b) They call me Yao Ming", "c) 5 feet", "d) Too Long" };
	
	private String question = questions[2].substring(3);
	
	public ParticipantQ3(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {
		
		this.backgroundPath = "assets/images/QuakeLogo.jpg";
		
		try {
			
			this.background = new Background(this.backgroundPath, 0.5);
			this.background.setVector(-0.1, 0);
			this.questionColor = new Color(128, 0, 0);
			this.questionFont = new Font("Press Start 2P", Font.PLAIN, 12);
			this.responseFont = new Font("Press Start 2P", Font.PLAIN, 10);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void tick() {
		this.background.tick();
	}

	@Override
	public void render(Graphics2D graphics) {
		
		this.background.render(graphics);
		
		FontMetrics questionFontMetrics = graphics.getFontMetrics(this.questionFont);
		FontMetrics responseFontMetrics = graphics.getFontMetrics(this.responseFont);
		int questionLength = questionFontMetrics.stringWidth(this.question);
		int selectionLength;
		
		// Draw question
		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);
		graphics.drawString(this.question, (MainComponent.WIDTH / 2) - (questionLength / 2), 50);
		
		graphics.setFont(this.responseFont);
		
		// Draw options
		for (int i = 0; i < options.length; i++) {
			
			selectionLength = responseFontMetrics.stringWidth(options[i]);
			
			if(i == currChoice) {
                graphics.setColor(Color.WHITE);
            } else {
                graphics.setColor(this.questionColor);
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
		
		System.exit(0);
		
	}

}
