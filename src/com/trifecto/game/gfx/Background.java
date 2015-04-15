package com.trifecto.game.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.trifecto.game.main.MainComponent;

public class Background {

	private BufferedImage image;
	private double x;
	private double y;
	private double deltaX;
	private double deltaY;
	private double moveScale;
	
	public Background(String stream, double moveScale) {
		
		try {
			image = ImageIO.read(new File(stream));
			this.moveScale = moveScale;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % MainComponent.WIDTH;
		this.y = (y * moveScale) % MainComponent.HEIGHT;
	}
	
	public void setVector(double deltaX, double deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	public void tick() {
		//x += deltaX;
		//y += deltaY;
	}
	
	public void render(Graphics2D graphics) {
		//System.out.println("Background render...");
		graphics.drawImage(image, (int) x, (int) y, null);
		
		if (x < 0) {
			graphics.drawImage(image, (int) x + MainComponent.WIDTH, (int) y, null);
		}
		
		if (x > 0) {
			graphics.drawImage(image, (int) x - MainComponent.WIDTH, (int) y, null);
		}
	}
}
