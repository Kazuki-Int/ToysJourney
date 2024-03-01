package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;

public class GameOverOverlay {

	private Playing playing;
	public GameOverOverlay(Playing playing) {
		this.playing = playing;
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		
		g.setColor(Color.white);
		g.drawString("Game Over", Game.SCREEN_WIDTH / 2, 150);
		g.drawString("Press esc to enter Main Menu!", Game.SCREEN_WIDTH / 2, 300);
		
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			playing.resetAll();
			Gamestate.state = Gamestate.MENU;
		} 
		
	}
	
}
