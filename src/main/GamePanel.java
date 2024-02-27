package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;


// class of game's display
public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;
	
	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;	
		setPanelSize();
//		this.setBackground(new Color(78,129,99));
		this.setBackground(Color.black);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void setPanelSize() {
		Dimension size = new Dimension(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		setPreferredSize(size);
		
	}
	
	public void paintComponent(Graphics g) { // keep calling themselves to make game's screen change.
		super.paintComponent(g);
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}

}