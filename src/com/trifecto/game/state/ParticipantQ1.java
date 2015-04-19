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
	
	private char[] name;
	private int currentChar;
	
	public ParticipantQ1(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {
		
		name = new char[] { 'A', 'A', 'A' };
		currentChar = 0;
		
		this.backgroundPath = "assets/images/QuakeLogo.jpg";
		
		try {
			
			this.background = new Background(this.backgroundPath, 0.5);
			this.background.setVector(-0.1, 0);
			this.questionColor = new Color(128, 0, 0);
			this.questionFont = new Font("Press Start 2P", Font.PLAIN, 12);
			this.responseFont = new Font("Press Start 2P", Font.PLAIN, 40);
			
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
        int questionLength = questionFontMetrics.stringWidth("What is your name, sonny?");
        int nameLength;
		
		// Draw question
		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);
		graphics.drawString("What is your name, sonny?", (MainComponent.WIDTH / 2) - (questionLength / 2), 50);
		
		// Type response
		graphics.setFont(this.responseFont);
		
		for (int i = 0; i < name.length; i++) {
			
			nameLength = responseFontMetrics.stringWidth(Character.toString(name[i]));
			
			if(i == currentChar) {
                graphics.setColor(Color.WHITE);
            } else {
                graphics.setColor(Color.RED);
            }
			
			graphics.drawString(Character.toString(name[i]), 145 + 40 * i, (MainComponent.HEIGHT / 2));
			
		}
		
//		graphics.setColor(Color.RED);
//		
//		int y = 250;
//		for (String line : text.split("\n")) {
//			graphics.drawString(line, 100, y += graphics.getFontMetrics().getHeight());
//		}
		
	}

	@Override
	public void keyPressed(int key) {
		
		if (key == KeyEvent.VK_ENTER) { select(); }
		
		if (key == KeyEvent.VK_UP) {
			if (name[currentChar] == ' ') {
				name[currentChar] = 'Z';
			} else {
				name[currentChar]--;
				if (name[currentChar] < 'A') {
					name[currentChar] = ' ';
				}
			}
		}
		
		if (key == KeyEvent.VK_DOWN) {
			if (name[currentChar] == ' ') {
				name[currentChar] = 'A';
			} else {
				name[currentChar]++;
				if (name[currentChar] > 'Z') {
					name[currentChar] = ' ';
				}
			}
		}
		
		if (key == KeyEvent.VK_LEFT) {
			if (currentChar == 0) {
				currentChar = name.length - 1;
			} else {
				currentChar--;
			}
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			if (currentChar == name.length - 1) {
				currentChar = 0;
			} else {
				currentChar++;
			}
		}
		
	}

	@Override
	public void keyReleased(int key) {
		
	}
	
	private void select() {
		
		for (int i = 0; i < name.length; i++) {
			this.partName += Character.toString(name[i]);
		}
		
		this.stateManager.setState(StateManager.PQ2);
		
	}

}
