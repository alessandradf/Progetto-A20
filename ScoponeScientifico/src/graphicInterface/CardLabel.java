package graphicInterface;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JLabel;

import model.SeedType;

/**
 * JLabel that represents the GUI's card
 */
public class CardLabel extends JLabel {

	private Icon imageCard;

	private SeedType seed;
	private int value;

	private boolean isEnable;

	/**
	 * constructor
	 * 
	 * @param seed        card's seed
	 * @param value       card's value
	 * @param imagesPaths path of the card's image
	 */
	public CardLabel(SeedType seed, int value, String imagePath) {
		this.seed = seed;
		this.value = value;

		try {
			imageCard = new ImageIcon(ImageIO.read(new File(imagePath)));

			this.setIcon(imageCard);

		} catch (IOException e) {
			System.out.println("IO EXCEPTION, NON TROVO PERCORSO FACCIA CARTA (costruttore)");
		}

	}

	/**
	 * allows to enable or disable the card's listener
	 * 
	 * @param b true to enable the listener, false to disable it
	 */
	@Override
	public void setEnabled(boolean b) {
		this.isEnable = b;
	}

	/**
	 * @return true if the listener is enabled, false otherwise
	 */
	public boolean isEnable() {
		return isEnable;
	}

	/**
	 * @return card's seed
	 */
	public SeedType getSeed() {
		return seed;
	}

	/**
	 * @return card's value
	 */
	public int getValue() {
		return value;
	}

}
