package CardTest;
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

	//seme e valore della carta
	private SeedType seed;
	private int value;

	// indica se attualmente l'immagine della carta � frntale
	// o girata con il dorso visibile (� nel mazzo)
	private CardPosition position;

	// percorsi immagine e dorso carta
	private String[] imagesPaths;



	// nel costruttore passo vettore di stringhe in cui al posto 0 c'� percorso
	// dell'immagine della faccia
	// e al posto 1 c'� percorso del retro della carta
	public CardLabel(SeedType seed, int value, String[] imagesPaths) {
		this.seed = seed;
		this.value = value;

		// TODO Auto-generated constructor stub
		this.imagesPaths = imagesPaths;

		// imposto di default l'immagine della faccia della carta
		position = CardPosition.FACE;
		try {
			imageCard = new ImageIcon(ImageIO.read(new File(imagesPaths[0])));

			this.setIcon(imageCard);

		} catch (IOException e) {
			System.out.println("IO EXCEPTION, NON TROVO PERCORSO FACCIA CARTA (costruttore)");
		}

	}

	//cambia posizione della carta (da fronte a retro e viceversa)
	public void changePosition() {
		if (this.position == CardPosition.FACE) {
			position = CardPosition.BACK;
			try {

				imageCard = new ImageIcon(ImageIO.read(new File(imagesPaths[1])));
				this.setIcon(imageCard);
				this.repaint();
				this.validate();

			} catch (IOException e) {
				System.out.println("IO EXCEPTION, NON TROVO PERCORSO CARTA GIRATA");
			}
		} else {
			position = CardPosition.FACE;
			try {

				imageCard = new ImageIcon(ImageIO.read(new File(imagesPaths[0])));
				this.setIcon(imageCard);
				this.repaint();
				this.validate();

			} catch (IOException e) {
				System.out.println("IO EXCEPTION, NON TROVO PERCORSO FACCIA CARTA");
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((CardLabel)obj).getSeed() == this.seed && (((CardLabel)obj).getValue() == this.value )){
			
			return true;
			
		}
		return false;
	}
	
	public CardPosition getPosition() {
		return this.position;
	}

	public SeedType getSeed() {
		return seed;
	}

	public int getValue() {
		return value;
	}

	

}
