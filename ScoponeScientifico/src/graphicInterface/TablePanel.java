package graphicInterface;

import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CardTest.CardLabel;
import CardTest.CardTester;
import model.Card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TablePanel extends JPanel {

	ArrayList<CardLabel> cardsOnTable; // carte sul tavolo
	JLabel background;
	private BufferedImage image;
	private int numberCardsOnTable; //numero di carte sul tavolo

	/**
	 * Create the panel.
	 */
	public TablePanel() {
		this.numberCardsOnTable = 0;
		this.setBackground(new Color(0, 100, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		// this.background = new JLabel(new
		// ImageIcon("Resources/Table/TableDefoult.jpeg")); //immagine di sfondo
		// background.setMinimumSize(this.getSize()); //in modo che non si ridimensioni
		// this.add(background);

		try {
			image = ImageIO.read(new File("Resources/Table/TableDefoult1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.cardsOnTable = new ArrayList<CardLabel>();
		setMinimumSize(new Dimension(25, 15));
		setMaximumSize(new Dimension(25, 15));
		setPreferredSize(new Dimension(25, 15));
	}

	// ho copiato i metodi del tavolo del modello, adattati all'interfaccia

	public void putCardOnTable(CardLabel playedCard) {
		this.cardsOnTable.add(playedCard);
		add(cardsOnTable.get(numberCardsOnTable));
		numberCardsOnTable ++;
		this.repaint();
		this.validate();
	}

	public void removeCardsFromTable(ArrayList<CardLabel> cardsRemoved) {
		ArrayList<CardLabel> toRemove = new ArrayList<CardLabel>();
		for (CardLabel c : cardsOnTable) {
			for (CardLabel c1 : cardsRemoved) {
				if ((c.getSeed() == c1.getSeed()) && (c.getValue() == c1.getValue())) {
					toRemove.add(c);
					this.remove(c);
					numberCardsOnTable --;
				}
			}
		}
		cardsOnTable.removeAll(toRemove);
		this.repaint();
		this.validate();
	}

	public void clearTable() {
		this.cardsOnTable.clear();
		this.removeAll();
		this.repaint();
		this.validate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

}
