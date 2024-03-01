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
import static utilz.Constants.ObjectConstants.POTION_HEIGHT;
import static utilz.Constants.ObjectConstants.POTION_WIDTH;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] crabbyArr;
	private ArrayList<Crabby> crabbies = new ArrayList<>();
	private int[][] lvlData;

	private float cameraX, cameraY;
	

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		crabbies = LoadSave.GetCrabs();
//		System.out.println("size of crab: " + crabbies.size());
		
	}

	public void update(Player player) {
		for (Crabby c : crabbies)
			if (c.isActive())
				c.update(player);
	}
	
	public void draw(Graphics g) {
		drawCrabs(g);
		
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	public void setCameraValues(float x, float y) {
		this.cameraX = x;
		this.cameraY = y;
	}
	
	private void changeWalkDir(Crabby c) {
		int walkDis = Game.TILES_SIZE;
		c.actionCounter ++;
		if (c.actionCounter > 0*walkDis && c.actionCounter <= 2*walkDis)  {
			c.walkDir = UP;
		}
		if (c.actionCounter > 2*walkDis && c.actionCounter <= 6*walkDis) {
			c.walkDir = LEFT;
		}
		if (c.actionCounter > 6*walkDis && c.actionCounter <= 8*walkDis) {
			c.walkDir = DOWN;
		}
		if (c.actionCounter > 8*walkDis && c.actionCounter <= 12*walkDis) {
			c.walkDir = RIGHT;
		}
		if (c.actionCounter > 12*walkDis) {
			c.actionCounter = 0;
		}
		
	}
	
	private void updateCrabs(Crabby c) {
		changeWalkDir(c);
		
		float xDelta = 0, yDelta = 0;
		if (c.walkDir == UP)  
			yDelta += c.velocityY;
		if (c.walkDir == DOWN)
			yDelta -= c.velocityY;
		if (c.walkDir == LEFT) 
			xDelta += c.velocityX;
		if (c.walkDir == RIGHT) 
			xDelta -= c.velocityX;
		
		if (HelpMethods.CanWalkHere(c.hitbox ,c.x - xDelta, c.y - yDelta, lvlData)) {
			c.x += -xDelta;
			c.y += -yDelta;
		}
	}
	
	private void drawCrabs(Graphics g) {
		for (Crabby c : crabbies) 
			if (c.isActive()) {
				int screenX = (int) (c.x - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				int screenY = (int) (c.y - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				
				g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], screenX, screenY, CRABBY_WIDTH, CRABBY_HEIGHT, null);
				c.hitbox.x = screenX + CRABBY_WIDTH/2 - c.hitbox.width/2;
				c.hitbox.y = screenY + CRABBY_HEIGHT/2 - c.hitbox.height/2;
				updateCrabs(c);
				g.setColor(Color.pink);
				g.drawRect((int) (c.hitbox.x), (int) (c.hitbox.y), (int) (c.hitbox.width), (int) (c.hitbox.height));
				c.drawAttackBox(g);
			
			}

	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Crabby c : crabbies) 
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(10);
					return;
				}
	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArr.length; j++)
			for (int i = 0; i < crabbyArr[j].length; i++)
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
		
	}
	
	public void resetAllEnemies() {
		for (Crabby c : crabbies)
			c.resetEnemy();
	}
	
}
