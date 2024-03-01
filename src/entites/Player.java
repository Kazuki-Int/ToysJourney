package entites;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.runtime.TemplateRuntime;

import javax.imageio.ImageIO;

import gamestates.Playing;
import main.Game;
import tiles.TileManager;
import utilz.LoadSave;
import utilz.HelpMethods;

public class Player extends Entity {
	
	private float spawnPosX, spawnPosY;

	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25, atkSpeed = 12; // 15
	private int playerAction = IDLE_FRONT;
	private float cameraX;
	private float cameraY;
	private int playerDir = -1; // default at -1 (IDLE)
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down;
	private float normalplayerSpeed = 1.0f * Game.SCALE;
	private float daigonalplayerSpeed = (float)(1.0f * Game.SCALE / Math.sqrt(2));
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	
	//StatusBarUI
	private BufferedImage statusBarImg;
	
	private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);
	
	private int healthBarWidth = (int) (146 * Game.SCALE);
	private int healthBarHeight = (int) (4.5 * Game.SCALE);
	private int healthBarXStart = (int) (37 * Game.SCALE);
	private int healthBarYStart = (int) (22 * Game.SCALE);
	
	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;
	private float originX;
	private float originY;
	
	//AttackBox
	private Rectangle2D.Float attackBox;
	
	private boolean attackChecked;
	private Playing playing;
	
	// Object
	private boolean hasKey = false;
		
	public Player(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height);
		originX = x + 19;
		originY = y + 12;
		this.playing = playing;
		calcStartCameraValues();
		loadAnimations();
		initHitbox(originX, originY, width, height);
		initAttackBox();

	}
	
	public void calcStartCameraValues() {
		cameraX = playing.getTileManager().getCurrentTile().getArrayWidth() / 2 * Game.TILES_SIZE;
		cameraY = playing.getTileManager().getCurrentTile().getArrayHeight() / 2 * Game.TILES_SIZE;
//		System.out.println("check");
	}

	public void setCameraValues(float cameraX, float cameraY) {
		this.cameraX = cameraX;
		this.cameraY = cameraY;
	}

	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(hitbox.x + (hitbox.width/2) - 19, hitbox.y + hitbox.height + (int) (Game.SCALE * 10) - 19, (int) (19 * Game.SCALE), (int) (30 * Game.SCALE));
		
	}

	public void update() {
		updateHealthBar();

		if (currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}
		
		updateAttackBox();
		
		updatePos();
		if (attacking)
			checkAttack();
		if (moving)
			checkPotionTouched();
		
		updateAnimationsTick();
		setAnimation();
	}
	

	private void checkAttack() {
		if (attackChecked || aniIndex != 1)
			return;
		attackChecked = true;
		playing.checkEnemyHit(attackBox);
		
	}

	private void updateAttackBox() {
		if (right) {
			attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 11) - 21;
			attackBox.y = hitbox.y + (hitbox.height/2) - 19;
		} else if (left) {
			attackBox.x = hitbox.x - (int) (Game.SCALE * 12) - 37;
			attackBox.y = hitbox.y + (hitbox.height/2) - 19;
		} else if (up) {
			attackBox.x = hitbox.x + (hitbox.width/2) - 19;
			attackBox.y = hitbox.y - (int) (Game.SCALE * 10) - 40;
		} else if (down) {
			attackBox.x = hitbox.x + (hitbox.width/2) - 19;
			attackBox.y = hitbox.y + hitbox.height + (int) (Game.SCALE * 10) - 19;
		}
		
		if ((up || down) && (attackBox.width > attackBox.height)) {
			attackBox.width = 19 * Game.SCALE;
			attackBox.height = 30 * Game.SCALE;
		}
		else if ((left || right) && (attackBox.width < attackBox.height)) {
			attackBox.width = 30 * Game.SCALE;
			attackBox.height = 19 * Game.SCALE;
		}
			
		
	}

	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
		
	}

	private void checkPotionTouched() {
		playing.checkPotionTouched(hitbox);
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (x), (int) (y), (int)(Game.PLAYER_SIZE*Game.SCALE), (int)(Game.PLAYER_SIZE*Game.SCALE), null); //size 64 == 16
		drawhitbox(g);
		drawAttackBox(g);
		drawUI(g);
	}
	
	private void drawAttackBox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x), (int) (attackBox.y), (int) attackBox.width, (int) attackBox.height);
		
	}

	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
	}


	
	private void updateAnimationsTick() {
		int speed;
		// set speed base on action 
		if (attacking)
			speed = atkSpeed;
		else
			speed = aniSpeed;
		
		aniTick++;
		if (aniTick >= speed) { // 8 animation per second, change pos overy 1/8 = 0.125 second.
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
				attackChecked = false;
			}	
		}
	}

	private void setAnimation() {
		int startAni = playerAction;
		
		if (moving && playerDir == LEFT) {
			playerAction = RUN_LEFT;
		} else if (moving && playerDir == DOWN) {
			playerAction = RUN_FRONT;
		} else if (moving && playerDir == RIGHT) {
			playerAction = RUN_RIGHT;
		} else if (moving && playerDir == UP) {
			playerAction = RUN_BACK;
		} else if (!moving && playerDir == UP){
			playerAction = IDLE_BACK;
		} else if (!moving && playerDir == LEFT) {
			playerAction = IDLE_LEFT;
		} else if (!moving && playerDir == RIGHT){
			playerAction = IDLE_RIGHT;
		} else if (!moving && playerDir == DOWN) {
			playerAction = IDLE_FRONT;
		}
		
		if (attacking && playerDir == RIGHT) {
			playerAction = ATK_RIGHT;
		} else if (attacking && playerDir == DOWN) {
			playerAction = ATK_FRONT;
		} else if (attacking && playerDir == UP) {
			playerAction = ATK_BACK;
		} else if (attacking && playerDir == LEFT) {
			playerAction = ATK_LEFT;
		}
		
		if (startAni != playerAction)
			resetAnitick();
	}
	
	private void resetAnitick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		if (!left & !right & !down & !up & !down)
			return;
		
		if (left && right || up && down)
			return;
			
		float xDelta = 0, yDelta = 0;
		
		if (left && !right) {
			playerDir =0;
			if (up || down)
				xDelta -= daigonalplayerSpeed *-1;
			else
				xDelta -= normalplayerSpeed *-1;
		} else if (right && !left) {
			playerDir = 2;
			if (up || down)
				xDelta += daigonalplayerSpeed *-1;
			else
				xDelta += normalplayerSpeed *-1;
		}
		
		if (up && !down) {
			if (left || right) {
				if (left) 
					playerDir = 0;
				else if (right)
					playerDir = 2;
				yDelta -= daigonalplayerSpeed *-1;
			} else {
				playerDir = 3;
				yDelta -= normalplayerSpeed *-1;
			}	
		} else if (down && !up) {
			if (left || right) {
				if (left) 
					playerDir = 0;
				else if (right)
					playerDir = 2;
				yDelta += daigonalplayerSpeed *-1;
			} else {	
				playerDir = 1;
				yDelta += normalplayerSpeed *-1;
			}	
		}
		
		if (HelpMethods.CanWalkHere(hitbox ,cameraX - xDelta, cameraY - yDelta, playing.getTileManager().getCurrentTile())) { //added
			cameraX += -xDelta;
			cameraY += -yDelta;
			System.out.println(cameraX + " : " + cameraY);
			moving = true;
		}
	}
	
	public void changeHealth(int value) {
		currentHealth += value;
		
		if (currentHealth <= 0) {
			currentHealth = 0;
			//gameOver();
		} else if (currentHealth >= maxHealth)
			currentHealth = maxHealth;
	}
	
	private void loadAnimations() {
		
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.FRIEREN);
		
		animations = new BufferedImage[12][8];
		for (int j = 0; j < animations.length; j++) 
			for (int i = 0; i < animations[j].length; i++) 
					animations[j][i] = img.getSubimage(i*50, j*50, 50, 50);// (imgx, imgy, posx, posy)
		
		statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	public void setDirection(int direction) {
		this.playerDir = direction;
	}
	
	public void setAttack(boolean attack) {
		this.attacking = attack;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void setRectPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isLeft() {
		return left;
	}

	
	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public float getCameraX() {
		return cameraX;
	}
	
	public float getCameraY() {
		return cameraY;
	}

	public void changHealth(int redPotionValue) {
		System.out.println("Heal!");
		changeHealth(redPotionValue);
		
	}

	public void changPower(int bluePotionValue) {
		System.out.println("Power!!!");
		
	}

	public void pickKey() {
		System.out.println("Pick a key!");
		hasKey = true;
	}

	public void resetAll() {
		resetDirBooleans();
		attacking = false;
		moving = false;
		hasKey = false;
		playerAction = IDLE_FRONT;
		currentHealth = maxHealth;
		
		cameraX = (float) (25 * Game.TILES_SIZE);
		cameraY = (float) (25 * Game.TILES_SIZE);
		
	}
}
