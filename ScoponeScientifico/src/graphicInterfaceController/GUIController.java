package graphicInterfaceController;

import java.util.ArrayList;

import javax.swing.*;

import CardTest.*;
import controller.CardConverter;
import graphicInterface.TablePanel;
import graphicInterface.TotalFrame;
import model.Card;
import model.Player;
import model.SeedType;

public class GUIController {
	
	private CardLabel cardLabel;
	private TotalFrame[] playerView;
	private TablePanel tablePanel;
	//private CardTester card;

	private static GUIController defaultGuiController = null;
	
	public static GUIController getDefaultGUIController() {
		if(defaultGuiController == null) {
			defaultGuiController = new GUIController();
			return defaultGuiController;
		}
		else
			return defaultGuiController;
	}
	
	private GUIController() {
		tablePanel = new TablePanel();
	}

	//"converte" una carta in una CardLabel semplicemente
	//creando una CardLabel ex novo aggiungendo i valori
	//in base a quelli della carta "normale".
/*	public CardLabel converter(CardTester card) {
		String[] path = new String[2];
		path[0] = "Resources/Cards/" + card.getValue() + "di" + card.getSeed() + ".png";
		//System.out.println(path[0]);
		cardLabel = new CardLabel(card.getSeed(), card.getValue(), path);
		return cardLabel;
	}*/
	
	/*
	public static void main(String[] args) {
		GUIController c = new GUIController();
		
		CardTester card = new CardTester("Due di Fiori", SeedType.FIORI, 2);
		
		CardLabel cl = c.converter(card);
		
		JFrame f = new JFrame();
		f.setBounds(100, 100, 450, 300);
		f.setVisible(true);
		JPanel p = new JPanel();
		f.add(p);
		p.add(cl);
	}
	*/
/*	public void init(Player[] players) {
		int i = 0;
		
		for (Player player : players) {
			playerView[i] = new TotalFrame(player.getPlayerName(), tablePanel);
			 playerView[i].getPlayerPanel.setCards(cardsConverter(player.getHandCards()));
			 
			 
		}
		
	}*/
	
	
	private ArrayList<CardLabel> cardsConverter(ArrayList<Card> cards){
		
		ArrayList<CardLabel> cardLabels = new ArrayList<CardLabel>();
		
		for (Card card : cards) {
			cardLabels.add(CardConverter.toCardLabel(card));
		}
		return cardLabels;
	}

	public TotalFrame[] getPlayerView() {
		return playerView;
	}
	
	

}
