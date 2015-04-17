package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Scanner;

import com.trifecto.game.gfx.Background;
import com.trifecto.game.main.MainComponent;

public class ParticipantQ1 extends State {
	
	private Scanner type;
	
	private String oldName;
	
	private Color transparent;
	
	private boolean keyPressFlag = false;
	
	private String text = "Hello, I am a\nCharacter.";
	
	private char[] name = { 'A', 'A', 'A' };
	
	public ParticipantQ1(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {
		
		this.backgroundPath = "assets/images/QuakeLogo.jpg";
		
		try {
			
			this.background = new Background(this.backgroundPath, 0.5);
			this.background.setVector(-1.0, 0);
			this.questionColor = new Color(128, 0, 0);
			this.questionFont = new Font("Press Start 2P", Font.PLAIN, 10);
			this.responseFont = new Font("Press Start 2P", Font.PLAIN, 15);
			
			this.type = new Scanner(System.in);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.questionsPath = "assets/questions/Questions.txt";
		
	}

	@Override
	public void tick() {
		this.background.tick();
	}

	@Override
	public void render(Graphics2D graphics) {
		
		this.background.render(graphics);
		
		// Text centering calculations
		FontMetrics questionFontMetrics = graphics.getFontMetrics(this.questionFont);
        FontMetrics responseFontMetrics = graphics.getFontMetrics(this.responseFont);
        int questionLength = questionFontMetrics.stringWidth("What is your name?");
        int nameLength;
		
		// Draw question
		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);
		graphics.drawString("What is your name?", (MainComponent.WIDTH / 2) - (questionLength / 2), 20);
		
		// Type response
		graphics.setFont(this.responseFont);
		
		for (int i = 0; i < name.length; i++) {
			
			nameLength = responseFontMetrics.stringWidth(Character.toString(name[i]));
			graphics.drawString(Character.toString(name[i]), (MainComponent.WIDTH / 2) + 20 * i, 200);
			
		}
		
		int y = 250;
		for (String line : text.split("\n")) {
			graphics.drawString(line, 100, y += graphics.getFontMetrics().getHeight());
		}
		
	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_ENTER) { select(); }
	}

	@Override
	public void keyReleased(int key) {
		
	}
	
	private void select() {
		keyPressFlag = true;
	}

}
