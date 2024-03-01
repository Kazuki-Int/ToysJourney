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
	
	public void draw(Graphics g) {
		drawSlimes(g);
		
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
		
		if (HelpMethods.CanWalkHere(s.hitbox ,s.x - xDelta, s.y - yDelta, lvlData)) {
			s.x += -xDelta;
			s.y += -yDelta;
		}
	}
	
	private void drawSlimes(Graphics g) {
		for (Slime s : slimies) 
			if (s.isActive()) {
				int screenX = (int) (s.x - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				int screenY = (int) (s.y - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				
				g.drawImage(slimeArr[s.getEnemyState()][s.getAniIndex()], screenX, screenY, SLIME_WIDTH, SLIME_HEIGHT, null);
				s.hitbox.x = screenX + SLIME_WIDTH/2 - s.hitbox.width/2;
				s.hitbox.y = screenY + SLIME_HEIGHT/2 - s.hitbox.height/2;
				updateSlimes(s);
				g.setColor(Color.pink);
				g.drawRect((int) (s.hitbox.x), (int) (s.hitbox.y), (int) (s.hitbox.width), (int) (s.hitbox.height));
				s.drawAttackBox(g);
			
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
				slimeArr[j][i] = temp.getSubimage(i * SLIME_WIDTH_DEFAULT, j * SLIME_HEIGHT_DEFAULT, SLIME_WIDTH_DEFAULT, SLIME_HEIGHT_DEFAULT);
		
	}
	
	public void resetAllEnemies() {
		for (Slime s : slimies)
			s.resetEnemy();
	}
	
}
