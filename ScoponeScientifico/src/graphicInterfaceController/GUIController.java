package graphicInterfaceController;

import java.io.IOException;

import javax.swing.*;

import CardTest.*;

public class GUIController {
	
	private CardLabel cardLabel;
	//private CardTester card;
	
	public GUIController() {}
	
	//"converte" una carta in una CardLabel semplicemente
	//creando una CardLabel ex novo aggiungendo i valori
	//in base a quelli della carta "normale".
	public CardLabel converter(CardTester card) {
		String[] path = new String[2];
		path[0] = "Resources/Cards/" + card.getValue() + "di" + card.getSeed() + ".png";
		System.out.println(path[0]);
		cardLabel = new CardLabel(path);
		return cardLabel;
	}
	
	public static void main(String[] args) {
		GUIController c = new GUIController();
		
		CardTester card = new CardTester("Due di Fiori", "fiori", 2);
		
		CardLabel cl = c.converter(card);
		
		JFrame f = new JFrame();
		f.setBounds(100, 100, 450, 300);
		f.setVisible(true);
		JPanel p = new JPanel();
		f.add(p);
		p.add(cl);
	}
	
	
	

}
