package entites;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.CanWalkHere;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import main.Game;


public abstract class Enemy extends Entity {
	protected int aniIndex, enemyState, enemyType;
	protected int aniTick, aniSpeed = 25;
	protected float walkSpeed = 1.0f * Game.SCALE;
	protected int walkDir = LEFT;
	protected int tileX, tileY;
	protected float attackDistance = Game.TILES_SIZE;
	protected int maxHealth;
	protected int currentHealth;
	protected boolean active = true;
	protected boolean attackChecked;
	
	private int[][] lvlData;
	
	protected int actionCounter = 0;
	
	protected Rectangle2D.Float attackBox;


	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
		maxHealth = GetMaxHealth(enemyType);
		currentHealth = maxHealth;
		
	}
	
	protected boolean isPlayerCloseForAttack(Player player) {
//		System.out.println(player.hitbox.x+ " " + hitbox.x);
//		System.out.println(player.hitbox.y+ " " + hitbox.y);
//		int absValueX = (int) Math.abs(player.hitbox.x - attackBox.x);
//		int absValueY = (int) Math.abs(player.hitbox.y - attackBox.y);
//		System.out.println(absValueX+" "+absValueY);
//		return (absValueX <= attackDistance && absValueY <= attackDistance);
		
		return player.hitbox.intersects(attackBox);
	}

	protected void newState(int enemyState) {
		this.enemyState = enemyState;
		aniTick = 0;
		aniIndex = 0;
	}
	
	public void hurt(int amount) {
		currentHealth -= amount;
		if (currentHealth <= 0)
			newState(DEAD);
		else
			newState(HIT);
	}
	
	protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
		if (attackBox.intersects(player.hitbox))
			player.changeHealth(-GetEnemyDmg(enemyType));
		attackChecked = true; 
		
	}
	
	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getSpriteAmount(enemyType, enemyState)) {
				aniIndex = 0;
				switch (enemyState) {
				case ATTACK, HIT -> enemyState = IDLE;
				case DEAD -> active = false;
				}
			}
		}
	}
	
	public void resetEnemy() {
		hitbox.x = x;
		hitbox.y = y;
		currentHealth = maxHealth;
		newState(IDLE);
		active = true;
	}
	
	
	public int getAniIndex() {
		return aniIndex;
	}
	
	public int getEnemyState() {
		return enemyState;
	}
	
	public boolean isActive() {
		return active;
	}
	
}
