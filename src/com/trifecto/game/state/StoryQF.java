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

public class StoryQF extends State {

	private int currChoice = 0;
	private String[] responses;

	private String question = questions[10].substring(2);

	public StoryQF(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {
		// DONT FUCK THIS UP
		this.responses = new String[2];

		this.backgroundPath = "assets/images/QuakeLogo.jpg";

		try {

			this.background = new Background(this.backgroundPath, 0.5);
			this.background.setVector(-0.1, 0);
			this.questionColor = new Color(128, 0, 0);
			this.questionFont = new Font("Press Start 2P", Font.PLAIN, 8);
			this.responseFont = new Font("Press Start 2P", Font.PLAIN, 8);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			this.choicesBufferedReader = new BufferedReader(new FileReader(
					"assets/questions/Choices.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line;
		try {

			int i = 0;
			while ((line = this.choicesBufferedReader.readLine()) != null
					&& (i < 2)) {
				if (line.substring(0, 1).equals("F")) {
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
		FontMetrics questionFontMetrics = graphics
				.getFontMetrics(this.questionFont);
		FontMetrics responseFontMetrics = graphics
				.getFontMetrics(this.responseFont);
		int selectionLength;

		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);

		int y = 0;
		for (String line : this.question.split(altNewLine)) {
			graphics.drawString(line, 5, y += (graphics.getFontMetrics()
					.getHeight() + 5));
		}

		graphics.setFont(this.responseFont);
		for (int i = 0; i < responses.length; i++) {
			// WATCH IT DUDE
			selectionLength = responseFontMetrics.stringWidth(responses[i]
					.substring(2));

			if (i == currChoice) {
				graphics.setColor(Color.WHITE);
			} else {
				graphics.setColor(this.questionColor);
			}
			// DONT FUCK THIS UP
			graphics.drawString(responses[i].substring(2),
					(MainComponent.WIDTH / 2) - (selectionLength / 2),
					140 + i * 15);

		}

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
		if (currChoice == 0) {
			stateManager.setState(StateManager.SQA1);
		}

		if (currChoice == 1) {
			stateManager.setState(StateManager.SQH);
		}

	}

}
