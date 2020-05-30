package graphicInterface;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import CardTest.CardLabel;
import CardTest.CardTester;
import graphicInterfaceController.GUIController;
import model.SeedType;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;

public class PlayerFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerFrame frame = new PlayerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	public PlayerFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 432, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		CardTester twoC = new CardTester("2c", SeedType.FIORI, 2);
		CardTester twoD = new CardTester("2d", SeedType.DENARI, 2);
		CardTester twoH = new CardTester("2h", SeedType.CUORI, 2);
		CardTester twoS = new CardTester("2s", SeedType.PICCHE, 2);
		CardTester threeC = new CardTester("3c", SeedType.FIORI, 3);
		CardTester threeD = new CardTester("3d", SeedType.DENARI, 3);
		CardTester threeH = new CardTester("3h", SeedType.CUORI, 3);
		CardTester threeS = new CardTester("3s", SeedType.PICCHE, 3);
		CardTester fourC = new CardTester("4c", SeedType.FIORI, 4);
		CardTester fourD = new CardTester("4d", SeedType.DENARI, 4);
		
		CardTester[] cT = {twoC, twoD, twoH, twoS, threeC, threeD, threeH, threeS, fourC, fourD};
		
		/*
		 * queste sono le istanze delle carte che mi ero fatto come prova su in altro
		 * programma. Per avere le immagini nelle label dell'interfaccia avevo bisogno
		 * che fossero specificate nel costruttore di modo da poter settare l'icona
		 * della label direttamente dalla carta.
		 */
		
		//non dovrebbe essere così, modificare mettendo GUIController come Singlettons
		GUIController cGUI = GUIController.getDefaultGUIController();

		Border blue = BorderFactory.createLineBorder(Color.BLUE, 3, true);
		
		CardLabel[] c = new CardLabel[10];
		
		for(int i=0; i<10; i++) {
			
			c[i] = cGUI.converter(cT[i]);

			//JLabel lblNewLabel_2 = new JLabel(new ImageIcon(twoC.getImageCard()));
			c[i].addMouseListener(new CardListener(c[i], blue));
			panel.add(c[i]);
		}

		/*
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon(twoD.getImageCard()));
		lblNewLabel_3.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e1) {
				// TODO Auto-generated method stub
				lblNewLabel_3.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e1) {
				// TODO Auto-generated method stub
				lblNewLabel_3.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e1) {
				// TODO Auto-generated method stub
				lblNewLabel_3.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e1) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon(twoH.getImageCard()));
		lblNewLabel_1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e2) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e2) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e2) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel(new ImageIcon(twoS.getImageCard()));
		lblNewLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e3) {
				// TODO Auto-generated method stub
				lblNewLabel.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e3) {
				// TODO Auto-generated method stub
				lblNewLabel.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e3) {
				// TODO Auto-generated method stub
				lblNewLabel.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e3) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(threeC.getImageCard()));
		lblNewLabel_4.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e4) {
				// TODO Auto-generated method stub
				lblNewLabel_4.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e4) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e4) {
				// TODO Auto-generated method stub
				lblNewLabel_4.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e4) {
				// TODO Auto-generated method stub
				lblNewLabel_4.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e4) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(new ImageIcon(threeD.getImageCard()));
		lblNewLabel_5.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e5) {
				// TODO Auto-generated method stub
				lblNewLabel_5.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e5) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e5) {
				// TODO Auto-generated method stub
				lblNewLabel_5.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e5) {
				// TODO Auto-generated method stub
				lblNewLabel_5.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e5) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(new ImageIcon(threeH.getImageCard()));
		lblNewLabel_6.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e6) {
				// TODO Auto-generated method stub
				lblNewLabel_6.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e6) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e6) {
				// TODO Auto-generated method stub
				lblNewLabel_6.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e6) {
				// TODO Auto-generated method stub
				lblNewLabel_6.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e6) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel(new ImageIcon(threeS.getImageCard()));
		lblNewLabel_7.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e7) {
				// TODO Auto-generated method stub
				lblNewLabel_7.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e7) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e7) {
				// TODO Auto-generated method stub
				lblNewLabel_7.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e7) {
				// TODO Auto-generated method stub
				lblNewLabel_7.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e7) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(new ImageIcon(fourC.getImageCard()));
		lblNewLabel_8.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e8) {
				// TODO Auto-generated method stub
				lblNewLabel_8.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e8) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e8) {
				// TODO Auto-generated method stub
				lblNewLabel_8.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e8) {
				// TODO Auto-generated method stub
				lblNewLabel_8.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e8) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel(new ImageIcon(fourD.getImageCard()));
		lblNewLabel_9.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e9) {
				// TODO Auto-generated method stub
				lblNewLabel_9.setVisible(false);
			}

			@Override
			public void mouseReleased(MouseEvent e9) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e9) {
				// TODO Auto-generated method stub
				lblNewLabel_9.setBorder(blue);
			}

			@Override
			public void mouseExited(MouseEvent e9) {
				// TODO Auto-generated method stub
				lblNewLabel_9.setBorder(null);
			}

			@Override
			public void mousePressed(MouseEvent e9) {
				// TODO Auto-generated method stub

			}

		});
		panel.add(lblNewLabel_9);
		*/
		
		contentPane.setLayout(gl_contentPane);

	}

}