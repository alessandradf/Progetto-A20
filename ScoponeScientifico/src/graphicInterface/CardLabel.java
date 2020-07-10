package graphicInterface;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.SeedType;

public class CardLabel extends JLabel {

	// immagine della carta
	private Icon imageCard;

	// seme e valore della carta
	private SeedType seed;
	private int value;

	
	// percorsi immagine e dorso carta
	private String[] imagesPaths;
	
	//buleano per abilitare o disabilitare i listener sulla carta
	private boolean isEnable;

	// nel costruttore passo vettore di stringhe in cui al posto 0 c'� percorso
	// dell'immagine della faccia
	// e al posto 1 c'� percorso del retro della carta
	public CardLabel(SeedType seed, int value, String[] imagesPaths) {
		this.seed = seed;
		this.value = value;

		// TODO Auto-generated constructor stub
		this.imagesPaths = imagesPaths;

	
		try {
			imageCard = new ImageIcon(ImageIO.read(new File(imagesPaths[0])));

			this.setIcon(imageCard);

		} catch (IOException e) {
			System.out.println("IO EXCEPTION, NON TROVO PERCORSO FACCIA CARTA (costruttore)");
		}

	}

	
	
	//metodo per abilitare o disabilitare il mouse listenere realitvo
	@Override
	public void setEnabled(boolean b) {
		this.isEnable = b;
	}
	
	//metodo per verifcare se il mouse listener su questa label è attivo o no
	public boolean isEnable() {
		return isEnable;
	}

	

	public SeedType getSeed() {
		return seed;
	}

	public int getValue() {
		return value;
	}

}
