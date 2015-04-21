package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.trifecto.game.gfx.Background;

public class StoryQ1 extends State {
	
	private String[] responses;
	
	private String question = questions[3].substring(3);
	
	public StoryQ1(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {
		
		this.responses = new String[3];
		
		this.backgroundPath = "assets/images/QuakeLogo.jpg";
		
		try {
			
			this.background = new Background(this.backgroundPath, 0.5);
			this.background.setVector(-0.1, 0);
			this.questionColor = new Color(128, 0, 0);
			this.questionFont = new Font("Press Start 2P", Font.PLAIN, 8);
			this.responseFont = new Font("Press Start 2P", Font.PLAIN, 8);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.choicesBufferedReader = new BufferedReader(new FileReader("assets/questions/Choices.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line;
		try {
			
			int i = 0;
			while ((line = this.choicesBufferedReader.readLine()) != null && (i < 3)) {
				if (line.substring(0, 2).equals("A1")) {
					this.responses[i] = line;
					i++;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < responses.length; i++) {
			System.out.println(responses[i]);
		}
		
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
		
		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);
		
		int y = 5;
		for (String line : this.question.split("\n")) {
			graphics.drawString(line, 5, y += graphics.getFontMetrics().getHeight());
		}
		
	}

	@Override
	public void keyPressed(int key) {
		
	}

	@Override
	public void keyReleased(int key) {
		
	}

}
