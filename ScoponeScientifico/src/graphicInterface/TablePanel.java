package graphicInterface;


import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JPanel;

import CardTest.CardLabel;
import CardTest.CardTester;
import model.Card;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TablePanel extends JPanel {

	ArrayList<CardLabel> cardsOnTable; //carte sul tavolo
	
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		this.setBackground(new Color(0, 100, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.cardsOnTable = new ArrayList<CardLabel>();
		

	}
	
	//ho copiato i metodi del tavolo del modello, adattati all'interfaccia
	
	public void putCardOnTable(CardLabel playedCard) {
		this.cardsOnTable.add(playedCard);
		add(playedCard);
	
	}
	public void removeCardsFromTable(CardLabel[] cardsRemoved) {
		for(CardLabel c : cardsOnTable) {
			for(CardLabel c1 : cardsRemoved) {
				if(c.equals(c1)){
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
	}

}
