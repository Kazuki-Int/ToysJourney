package entites;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.runtime.TemplateRuntime;

import javax.imageio.ImageIO;

import main.Game;
import tiles.TileManager;
import utilz.LoadSave;
import utilz.HelpMethods;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25, atkSpeed = 20; // 15
	private int playerAction = IDLE_FRONT;
	private float cameraX = (float) (25 * Game.TILES_SIZE);
	private float cameraY = (float) (25 * Game.TILES_SIZE);
//	private float cameraX = 0;
//	private float cameraY =0;
	private int playerDir = -1; // default at -1 (IDLE)
	private boolean left, up, right, down;
	private boolean moving = false, attacking = false;
	
	private float normalplayerSpeed = 1.0f * Game.SCALE;
	private float daigonalplayerSpeed = (float)(1.0f * Game.SCALE / Math.sqrt(2));
	
	private int[][] lvlData;


		
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, width, height);

	}

	public void update() {
		updatePos();
		updateAnimationsTick();
		setAnimation();
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x), (int) (hitbox.y), (int)(Game.PLAYER_SIZE*Game.SCALE), (int)(Game.PLAYER_SIZE*Game.SCALE), null); //size 64 == 16
		drawhitbox(g);
	}
	
	public void setDirection(int direction) {
		this.playerDir = direction;
		//moving = direction;
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
			if (aniIndex >= GetSpriteAmount(playerAction))
				aniIndex = 0;
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
			playerAction = ATK_RIGHT;
		} else if (attacking && playerDir == UP) {
			playerAction = ATK_LEFT;
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
		
		if (HelpMethods.CanWalkHere(hitbox ,cameraX - xDelta, cameraY - yDelta, lvlData)) {
			cameraX += -xDelta;
			cameraY += -yDelta;
			System.out.println(cameraX + " : " + cameraY);
			moving = true;
		}
	}
	
	private void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.FRIEREN);
		
		animations = new BufferedImage[10][8];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
					animations[j][i] = img.getSubimage(i*50, j*50, 50, 50);// (imgx, imgy, posx, posy)
			}
		}				
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
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
}
