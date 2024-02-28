package main;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame("Aureole");

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(true);
		jframe.setVisible(true);
		jframe.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}

}