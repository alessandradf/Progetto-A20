package CardTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CardTester {
	
	private String name;
	private String seed;
	private int value;
	//private BufferedImage imageCard;
	
	public CardTester(String name, String seed, int value) {
		this.name = name;
		this.seed = seed;
		this.value = value;
		//this.imageCard = imageCard;
	}
	
	/*
	public BufferedImage getImageCard() {
		return imageCard;
	}
	
	public void setImageCard(BufferedImage imageCard) {
		this.imageCard = imageCard;
	}
	*/

	public String getName() {
		return name;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
