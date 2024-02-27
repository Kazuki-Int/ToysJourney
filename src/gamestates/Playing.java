package gamestates;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import entites.House;
import entites.Player;
import main.Game;
import object.ObjectManager;
import tiles.TileManager;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods {
	private Player player;
	private TileManager tileManager;
	private House house;
	private ObjectManager objectManager;
	
	private PauseOverlay pauseOverlay;
	
	private boolean paused = false;
	 
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		tileManager = new TileManager(game);
		house = new House(new Point(2800,1856+35));
		player = new Player((Game.SCREEN_WIDTH/2) - (Game.PLAYER_SIZE * Game.SCALE/2), (Game.SCREEN_HEIGHT/2) - (Game.PLAYER_SIZE * Game.SCALE/2), (int)((Game.PLAYER_WIDTH-5)*Game.SCALE), (int)((Game.PLAYER_HEIGHT)*Game.SCALE), this);
		objectManager = new ObjectManager(this);
		player.loadLvlData(tileManager.getCurrentTile().getTileData());
		pauseOverlay = new PauseOverlay(this);
		
	}
	
	@Override
	public void update() {
		if (!paused) {
			tileManager.update();
			player.update();
			objectManager.update();
		} else {
			pauseOverlay.update();
		}
		
		
		tileManager.setCameravalues(player.getCameraX(), player.getCameraY());
		house.setCameraValues(player.getCameraX(), player.getCameraY());
		objectManager.setCameraValues(player.getCameraX(), player.getCameraY());

	}

	@Override
	public void draw(Graphics g) {
		tileManager.draw(g);
		house.render(g);
		objectManager.draw(g);
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
		case KeyEvent.VK_UP:
			player.setUp(true);
			break;
		case KeyEvent.VK_LEFT:
			player.setLeft(true);
			break;
		case KeyEvent.VK_DOWN:
			player.setDown(true);
			break;
		case KeyEvent.VK_RIGHT:
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
		case KeyEvent.VK_UP:
			player.setMoving(false);
			player.setUp(false);			
			break;
		case KeyEvent.VK_LEFT:
			player.setMoving(false);
			player.setLeft(false);
			break;
		case KeyEvent.VK_DOWN:
			player.setMoving(false);
			player.setDown(false);
			break;
		case KeyEvent.VK_RIGHT:
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

	public void checkPotionTouched(Rectangle2D.Float hitbox) {
		objectManager.checkObjectTouched(hitbox);
		
	}

}
