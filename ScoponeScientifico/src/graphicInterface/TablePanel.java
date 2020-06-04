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
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class TablePanel extends JPanel {

	ArrayList<CardLabel> cardsOnTable; // carte sul tavolo
	
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		this.setBackground(new Color(0, 100, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel background = new JLabel(new ImageIcon("Resources/Table/TableDefoult.jpeg")); //immagine di sfondo
		background.setMinimumSize(this.getSize()); //in modo che non si ridimensioni
		this.add(background);
		this.cardsOnTable = new ArrayList<CardLabel>();

	}

	// ho copiato i metodi del tavolo del modello, adattati all'interfaccia

	public void putCardOnTable(CardLabel playedCard) {
		this.cardsOnTable.add(playedCard);
		add(playedCard);
		this.repaint();
		this.validate();
	}

	public void removeCardsFromTable(ArrayList<CardLabel> cardsRemoved) {
		for (CardLabel c : cardsOnTable) {
			for (CardLabel c1 : cardsRemoved) {
				if (c.equals(c1)) {
					cardsOnTable.remove(c1);
					this.remove(c1);
				}
			}
		}
		this.repaint();
		this.validate();
	}

	public void clearTable() {
		this.cardsOnTable.clear();
		this.removeAll();
		this.repaint();
		this.validate();
	}

}
