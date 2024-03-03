package entites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import gamestates.Playing;
import main.Game;
import utilz.HelpMethods;
import utilz.LoadSave;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObjectConstants.*;


public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] slimeArr;
	private ArrayList<Slime> slimies = new ArrayList<>();
	private int[][] lvlData;

	private float cameraX, cameraY;
	

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		slimies = LoadSave.GetSlimes();
//		System.out.println("size of crab: " + crabbies.size());
		
	}

	public void update(Player player) {
		for (Slime s : slimies)
			if (s.isActive())
				s.update(player);
	}
	
	public void draw(Graphics g, Boolean paused, Boolean gameOver) {
		drawSlimes(g, paused, gameOver);
		
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	public void setCameraValues(float x, float y) {
		this.cameraX = x;
		this.cameraY = y;
	}
	
	private void changeWalkDir(Slime s) {
		int walkDis = Game.TILES_SIZE;
		s.actionCounter ++;
		if (s.actionCounter > 0*walkDis && s.actionCounter <= 2*walkDis)  {
			s.walkDir = UP;
		}
		if (s.actionCounter > 2*walkDis && s.actionCounter <= 6*walkDis) {
			s.walkDir = LEFT;
		}
		if (s.actionCounter > 6*walkDis && s.actionCounter <= 8*walkDis) {
			s.walkDir = DOWN;
		}
		if (s.actionCounter > 8*walkDis && s.actionCounter <= 12*walkDis) {
			s.walkDir = RIGHT;
		}
		if (s.actionCounter > 12*walkDis) {
			s.actionCounter = 0;
		}
		
	}
	
	private void updateSlimes(Slime s) {
		changeWalkDir(s);
		
		float xDelta = 0, yDelta = 0;
		if (s.walkDir == UP)  
			yDelta += s.velocityY;
		if (s.walkDir == DOWN)
			yDelta -= s.velocityY;
		if (s.walkDir == LEFT) 
			xDelta += s.velocityX;
		if (s.walkDir == RIGHT) 
			xDelta -= s.velocityX;
		
		if (HelpMethods.CanWalkHere(s.hitbox ,s.x - xDelta, s.y - yDelta, playing.getTileManager().getCurrentTile())) {
			s.x += -xDelta;
			s.y += -yDelta;
		}
	}
	
	private void drawSlimes(Graphics g, Boolean paused, Boolean gameOver) {
		for (Slime s : slimies) 
			if (s.isActive()) {
				int screenX = (int) (s.x - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(SLIME_WIDTH/2)));
				int screenY = (int) (s.y - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(SLIME_HEIGHT/2)));
				
				g.drawImage(slimeArr[s.getEnemyState()][s.getAniIndex()], screenX, screenY, (int) ((50*Game.SCALE)), (int) (50*Game.SCALE), null);

				screenX = (int) (s.x - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(50*Game.SCALE/3.5)));
				screenY = (int) (s.y - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(50*Game.SCALE/8)));
				s.hitbox.x = screenX + SLIME_WIDTH/2 + s.hitbox.width/2;
				s.hitbox.y = screenY + SLIME_HEIGHT/2 + s.hitbox.height/2;
				g.setColor(Color.pink);
				g.drawRect((int) (s.hitbox.x), (int) (s.hitbox.y), (int) (s.hitbox.width), (int) (s.hitbox.height));
				s.drawAttackBox(g);
				if (!paused && !gameOver)
					updateSlimes(s);

			
			}

	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Slime s : slimies) 
			if (s.isActive())
				if (attackBox.intersects(s.getHitbox())) {
					s.hurt(10);
					return;
				}
	}

	private void loadEnemyImgs() {
		slimeArr = new BufferedImage[5][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SLIME_SPRITE);
		for (int j = 0; j < slimeArr.length; j++)
			for (int i = 0; i < slimeArr[j].length; i++)
				slimeArr[j][i] = temp.getSubimage(i * 50, j * 50, 50, 50);
		
	}
	
	public void resetAllEnemies() {
		for (Slime s : slimies)
			s.resetEnemy();
	}
	
}
