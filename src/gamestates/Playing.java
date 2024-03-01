package gamestates;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;

import entites.EnemyManager;
import entites.House;
import entites.Player;
import main.Game;
import object.ObjectManager;
import tiles.TileManager;
import ui.GameOverOverlay;
import ui.PauseOverlay;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
	private Player player;
	private TileManager tileManager;
	private EnemyManager enemyManager;
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;
	
	private House house;
	private ObjectManager objectManager;
	
	
	
	private boolean paused = false;
	
	private boolean gameOver;
	 
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		tileManager = new TileManager(game);
		enemyManager = new EnemyManager(this);
		house = new House(new Point(2800,1856+35));
		player = new Player((Game.SCREEN_WIDTH/2) - (Game.PLAYER_SIZE * Game.SCALE/2), (Game.SCREEN_HEIGHT/2) - (Game.PLAYER_SIZE * Game.SCALE/2), (int)((Game.PLAYER_WIDTH-5)*Game.SCALE), (int)((Game.PLAYER_HEIGHT)*Game.SCALE), this);
		objectManager = new ObjectManager(this);
		player.loadLvlData(tileManager.getCurrentTile().getTileData());
		enemyManager.loadLvlData(tileManager.getCurrentTile().getTileData());
		pauseOverlay = new PauseOverlay(this);
		gameOverOverlay = new GameOverOverlay(this);
		
	}
	
	@Override
	public void update() {
		if (!paused && !gameOver) {
			tileManager.update();
			player.update();
			enemyManager.update(player);
			objectManager.update();
		} else {
			pauseOverlay.update();
		}
		
		
		tileManager.setCameravalues(player.getCameraX(), player.getCameraY());
		house.setCameraValues(player.getCameraX(), player.getCameraY());
		objectManager.setCameraValues(player.getCameraX(), player.getCameraY());
		enemyManager.setCameraValues(player.getCameraX(), player.getCameraY());
		
	}

	@Override
	public void draw(Graphics g) {
//		BufferedImage backgroundSprite = LoadSave.GetSpriteAtlas(LoadSave.GAME_BACKGROUND);
//		g.drawImage(backgroundSprite, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, null);
		tileManager.draw(g);
		house.render(g);
		objectManager.draw(g);
		player.render(g);		
		enemyManager.draw(g);
		
		if (paused)
			pauseOverlay.draw(g);
		else if (gameOver) 
			gameOverOverlay.draw(g);
	}
	
	public void resetAll() {
		//TODO: reset playing, enemy, tile etc.
		gameOver = false;
		paused = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
		objectManager.resetAllObject();
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (gameOver)
			gameOverOverlay.keyPressed(e);
		else
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
		if (!gameOver)
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
		if (!gameOver)
			if (paused)
				pauseOverlay.mouseDragged(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gameOver)
			if (paused) 
				pauseOverlay.mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameOver)
			if (paused)
				pauseOverlay.mouseReleased(e);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameOver)
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
