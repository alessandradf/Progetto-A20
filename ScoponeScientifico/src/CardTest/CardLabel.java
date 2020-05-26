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

public class CardLabel extends JLabel {
	
	
	
	// immagine della carta
	private Icon imageCard;
	
	//indica se attualmente l'immagine della carta � frntale
	//o girata con il dorso visibile (� nel mazzo)
	private CardPosition position;
	
	//percorsi immagine e dorso carta
	private String[] imagesPaths;
	
	
	//prova di istanza, lascio commentato 
/*	public static void main(String[] args) {
	
		
		String[] imagesPaths = new String[2];
		imagesPaths[0] = "Resources/Cards/2C_1.png";
		imagesPaths[1] = "Resources/BackCards/Red_Back_Resized.png";
		
		CardLabel prova = new CardLabel(imagesPaths);
		
		JFrame provaFrame = new JFrame();
		
		provaFrame.add(prova);
		
		provaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		provaFrame.setVisible(true);
		
		
		
	}*/
	
	// nel costruttore passo vettore di stringhe in cui al posto 0 c'� percorso dell'immagine della faccia
	// e al posto 1 c'� percorso del retro della carta
	public CardLabel(String[] imagesPaths)  {
		
		// TODO Auto-generated constructor stub
		this.imagesPaths = imagesPaths;
		
		//imposto di default l'immagine della faccia della carta
		position = CardPosition.FACE;
		try {
	    imageCard = new ImageIcon( ImageIO.read(new File(imagesPaths[0])));
	
		this.setIcon(imageCard);
		
		}
		catch(IOException e) {
			System.out.println("IO EXCEPTION, NON TROVO PERCORSO FACCIA CARTA");
		}
		
		
	
	}
	
	public void changePosition() {
		position = CardPosition.BACK;
		try {
			
		imageCard = new ImageIcon(ImageIO.read(new File(imagesPaths[1])));
		this.setIcon(imageCard);
		this.repaint();
		this.validate();
		
		}
		catch(IOException e) {
			System.out.println("IO EXCEPTION, NON TROVO PERCORSO CARTA GIRATA");
		}
	}
	
		public CardPosition getPosition() {
			return this.position;
		}

		public Icon getImageCard() {
			return imageCard;
		}

		public void setImageCard(Icon imageCard) {
			this.imageCard = imageCard;
		}
		
	

}
