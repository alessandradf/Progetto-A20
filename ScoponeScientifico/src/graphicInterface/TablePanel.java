package graphicInterface;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel that shows all the cards on the table
 */
public class TablePanel extends JPanel {

	ArrayList<CardLabel> cardsOnTable;
	JLabel background;
	private BufferedImage image;
	private int numberCardsOnTable;

	/**
	 * Creates the panel.
	 */
	public TablePanel() {
		this.numberCardsOnTable = 0;
		this.setBackground(new Color(0, 100, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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

	/**
	 * Puts a new card on the panel
	 * 
	 * @param playedCard
	 */
	public void putCardOnTable(CardLabel playedCard) {
		this.cardsOnTable.add(playedCard);
		add(cardsOnTable.get(numberCardsOnTable));
		numberCardsOnTable++;
		this.repaint();
		this.validate();
	}

	/**
	 * Removes a card from the panel
	 * 
	 * @param cardsRemoved
	 */
	public void removeCardsFromTable(ArrayList<CardLabel> cardsRemoved) {
		ArrayList<CardLabel> toRemove = new ArrayList<CardLabel>();
		for (CardLabel c : cardsOnTable) {
			for (CardLabel c1 : cardsRemoved) {
				if ((c.getSeed() == c1.getSeed()) && (c.getValue() == c1.getValue())) {
					toRemove.add(c);
					this.remove(c);
					numberCardsOnTable--;
				}
			}
		}
		cardsOnTable.removeAll(toRemove);
		this.repaint();
		this.validate();
	}

	/**
	 * Removes all the cards from the panel
	 */
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
