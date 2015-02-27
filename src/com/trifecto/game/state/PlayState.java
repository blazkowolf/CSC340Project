package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.trifecto.game.gfx.Background;
import com.trifecto.game.main.MainComponent;

public class PlayState extends State {
	
	private Background background;

	public Scanner inFileQ;
	public Scanner inFileC;
	
	private String backgroundPath;
	
	private String questionsPath;
	private String choicesPath;
	
	private int currChoice = 0;
	private String[] questions;
	private String[] choices;
	
	private Color transparent;
	
	private Color questionColor;
	private Font questionFont;
	private Font choiceFont;
	
    private boolean loopFlag = false;
	
	public PlayState(StateManager stateManager) {
		this.stateManager = stateManager;
		init();
	}

	@Override
	public void init() {
		
		backgroundPath = "C:/Users/Daniel/Documents/EclipseProjects/TextEngine2/assets/images/QuakeLogo.jpg";
		
		try {
            background = new Background(backgroundPath, 1);
            background.setVector(-0.1, 0);
            transparent = new Color(0,0,0,0);
            questionColor = new Color(128, 0, 0);
            questionFont = new Font("Press Start 2P", Font.PLAIN, 18);
            choiceFont = new Font("Press Start 2P", Font.PLAIN, 10);
        } catch(Exception e) {
            e.printStackTrace();
        }
		
		questionsPath = "C:/Users/Daniel/Documents/EclipseProjects/TextEngine2/assets/questions/Questions.txt";
		choicesPath = "C:/Users/Daniel/Documents/EclipseProjects/TextEngine2/assets/questions/Choices.txt";
		
		try {
			inFileQ = new Scanner(new File(questionsPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Calculate the number of indices the "questions" array will need
		int numIndices = 0;
		while (inFileQ.hasNextLine()) { inFileQ.nextLine(); numIndices++; }
		
		questions = new String[numIndices];
		
		questions = readFile(questions, inFileQ, questionsPath);
		
		// Test for read completion
		for (int i = 0; i < questions.length; i++) {
			System.out.println(questions[i]);
		}
		
		try {
			inFileC = new Scanner(new File(choicesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Calculate the number of indices the "questions" array will need
		numIndices = 0;
		while (inFileC.hasNextLine()) { inFileC.nextLine(); numIndices++; }
				
		choices = new String[numIndices];
	
		choices = readFile(choices, inFileC, choicesPath);
		
		// Test for read completion
		for (int i = 0; i < choices.length; i++) {
			System.out.println(choices[i]);
		}
		
	}
	
	@SuppressWarnings("resource")
	private String[] readFile(String[] arr, Scanner inFile, String path) {
		
		// Re-initialize the Scanner being passed into the method
		// so that the file can be read from the beginning
		try {
			inFile = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = inFile.nextLine();
		}
		
		return arr;
		
	}

	@Override
	public void tick() {		
		background.tick();
	}

	@Override
	public void render(Graphics2D graphics) {
		
		// Draw background
        background.render(graphics);
        
        // Draw questions and choices
        for (int i = 0; i < questions.length; i++) {
        	
        	graphics.setColor(questionColor);
        	graphics.setFont(questionFont);
        	graphics.drawString(questions[i], 10, 70);
        	
        	for (int j = 0; j < choices.length; j++) {

    			graphics.setFont(choiceFont);
    			graphics.drawString(choices[j], 10, 140 + j * 15);
        		
        		while (!loopFlag) {
        			
            		if(j == currChoice) {
                        graphics.setColor(Color.WHITE);
                    } else {
                        graphics.setColor(Color.RED);
                    }
        		}
        		
                //graphics.drawString(choices[j], 10, 140 + j * 15);
        		graphics.setColor(transparent);
        		//graphics.drawString(choices[j], 10, 140 + j * 15);
        		
        	}
        	graphics.drawString(choices[i], 10, 140 + i * 15);
        }
	}

	@Override
	public void keyPressed(int key) {

		if (key == KeyEvent.VK_ENTER) { loopFlag = true; select(); }
		
		if (key == KeyEvent.VK_UP) {
			currChoice--;
			if (currChoice == -1) { currChoice = choices.length - 1; }
		}
		
		if (key == KeyEvent.VK_DOWN) {
			currChoice++;
			if (currChoice == choices.length) { currChoice = 0; }
		}
			
	}

	@Override
	public void keyReleased(int key) {		
	}
	
	private void select() {

		if (currChoice == 0) {
			//stateManager.setState(StateManager.PLAYSTATE);
		}
		
		if (currChoice == 1) {
			// Do nothing...yet
		}
		
		if (currChoice == 2) {
			//System.exit(0);
		}
		
		if (currChoice == 4) {
			// Do nothing...yet
		}
		
	}

}
