package com.trifecto.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.trifecto.game.gfx.Background;
import com.trifecto.game.main.MainComponent;

public class GameOver extends State {
	
	//private String endText = partName + ", the game is over.";
	//.substring(partName.length() - 3)

	public GameOver(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	public void init() {
		
		//endText = partName + ", the game is over.";
		
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
		
		this.questionsPath = "assets/questions/Questions.txt";
		
	}

	@Override
	public void tick() {
		this.background.tick();
	}

	@Override
	public void render(Graphics2D graphics) {
		
		this.background.render(graphics);
		
		//FontMetrics responseFontMetrics = graphics.getFontMetrics(this.responseFont);
		//int hitEnter = resonseFontMetrics.stringWidth("")
		
		graphics.setColor(this.questionColor);
		graphics.setFont(this.questionFont);
		//partName.substring(partName.length() - 3)
		
		//ParticipantQ1.partName + ParticipantQ2.isBro + "but the\ngame is over."
		
		int y = 20;
		graphics.drawString(partName.substring(partName.length() - 3) + isBro, 10, y);
		graphics.drawString(legLength, 10, (y += graphics.getFontMetrics().getHeight() + 5));
		graphics.drawString("but the game is over.", 10, (y += graphics.getFontMetrics().getHeight() + 5));
		
		
		//graphics.setFont(this.responseFont);
		//graphics.drawString("Hit ENTER to play again", , y);
		
		
	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_ENTER) {
			System.out.println("GameOver partName: " + partName);
			System.out.println("isBro: " + isBro);
			//partName = "";
			//isBro = "";
			//legLength = "";
			stateManager.setState(StateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int key) {
		
	}

}
