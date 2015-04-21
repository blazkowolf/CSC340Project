package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.trifecto.game.gfx.Background;
import com.trifecto.game.main.MainComponent;

public class StoryQK extends State {

	private int currChoice = 0;
	private String[] responses;

	private String question = questions[14].substring(2);

	public StoryQK(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {

		this.backgroundPath = "assets/images/QuakeLogo.jpg";

		try {

			this.background = new Background(this.backgroundPath, 0.5);
			this.background.setVector(-0.1, 0);
			this.questionColor = new Color(128, 0, 0);
			this.questionFont = new Font("Press Start 2P", Font.PLAIN, 8);
			this.responseFont = new Font("Press Start 2P", Font.PLAIN, 30);

		} catch (Exception e) {
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

		// Text centering calculations
		FontMetrics responseFontMetrics = graphics.getFontMetrics(this.responseFont);
		int selectionLength = responseFontMetrics.stringWidth("Hit ENTER");

		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);

		int y = 0;
		for (String line : this.question.split(altNewLine)) {
			graphics.drawString(line, 5, y += (graphics.getFontMetrics()
					.getHeight() + 5));
		}
		
		graphics.setColor(Color.WHITE);
		graphics.setFont(this.responseFont);
		graphics.drawString("Hit ENTER", (MainComponent.WIDTH / 2) - (selectionLength / 2), 255);

	}

	@Override
	public void keyPressed(int key) {

		if (key == KeyEvent.VK_ENTER) {
			select();
		}

		if (key == KeyEvent.VK_UP) {
			currChoice--;
			if (currChoice == -1) {
				currChoice = responses.length - 1;
			}
		}

		if (key == KeyEvent.VK_DOWN) {
			currChoice++;
			if (currChoice == responses.length) {
				currChoice = 0;
			}
		}

	}

	@Override
	public void keyReleased(int key) {

	}

	private void select() {
		stateManager.setState(StateManager.GAMEOVER);
	}

}
