package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entites.Player;
import main.Game;
import tiles.TileManager;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods {
	private Player player;
	private TileManager tileManager;
	
	private PauseOverlay pauseOverlay;
	
	private boolean paused = false;
	 
	private float cameraX, cameraY;
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		tileManager = new TileManager(game);
		player = new Player((Game.SCREEN_WIDTH/2) - (Game.PLAYER_SIZE * Game.SCALE/2), (Game.SCREEN_HEIGHT/2) - (Game.PLAYER_SIZE * Game.SCALE/2), (int)(50*Game.SCALE), (int)(50*Game.SCALE));
		player.loadLvlData(tileManager.getCurrentTile().getTileData());
		pauseOverlay = new PauseOverlay(this);
		
	}
	
	@Override
	public void update() {
		if (!paused) {
			tileManager.update();
			player.update();
		} else {
			pauseOverlay.update();
		}
		tileManager.setCameravalues(cameraX, cameraY);

	}

	@Override
	public void draw(Graphics g) {
		tileManager.draw(g);
		player.render(g);
		
		if (paused)
			pauseOverlay.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_K:
			player.setAttack(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setMoving(false);
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setMoving(false);
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setMoving(false);
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setMoving(false);
			player.setRight(false);
			break;
		case KeyEvent.VK_K:
			player.setAttack(false);
			break;
		}

	}
	
	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused) 
			pauseOverlay.mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e); 
		
	}
	
	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}
	
	public Player getPlayer() {
		return player;
	}

}
