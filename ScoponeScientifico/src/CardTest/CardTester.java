package CardTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CardTester {
	
	private String name;
	private SeedType seed;
	private int value;
	//private BufferedImage imageCard;
	
	public CardTester(String name, SeedType seed, int value) {
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

	public SeedType getSeed() {
		return seed;
	}

	public void setSeed(SeedType seed) {
		this.seed = seed;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
