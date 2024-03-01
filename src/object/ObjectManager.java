package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.ObjectConstants.*;
import static utilz.Constants.PlayerConstants.IDLE_FRONT;


public class ObjectManager {

	private Playing playing;
	private BufferedImage[][] potionImgs;
	private ArrayList<Potion> potions;
	
	private BufferedImage[][] key1_Imgs;
	private ArrayList<Key> keys;
	
	private float cameraX, cameraY;
	
	public ObjectManager(Playing playing) {
		this.playing = playing;
		loadImgs();
		
		potions = new ArrayList<>();
		potions.add(new Potion(30*Game.TILES_SIZE + POTION_WIDTH/2 + (int) (3 * Game.SCALE), 17*Game.TILES_SIZE + POTION_HEIGHT/2, RED_POTION)); // RED
		potions.add(new Potion(31*Game.TILES_SIZE + POTION_WIDTH/2 + (int) (3 * Game.SCALE), 17*Game.TILES_SIZE + POTION_HEIGHT/2 - (int) (2 * Game.SCALE), BLUE_POTION)); // BLUE
		
		keys = new ArrayList<>();
		keys.add(new Key(20*Game.TILES_SIZE + KEY_WIDTH/2, 25*Game.TILES_SIZE + KEY_HEIGHT/2, KEY_1));
	}

	public void checkObjectTouched(Rectangle2D.Float hitbox) {
		for (Key k : keys) {
			if (k.isActive()) {
				if (hitbox.intersects(k.getHitbox())) {
					k.setActive(false);
					applyKeyToPlayer(k);
				}
			}
		}
		
		for (Potion p : potions) {
			if (p.isActive()) {
				if (hitbox.intersects(p.getHitbox())) {
					p.setActive(false);
					applyPotionToPlayer(p);
				}
			}
		}
		

	}
	
	public void applyPotionToPlayer(Potion p) {
		if (p.getObjType() == RED_POTION)
			playing.getPlayer().changHealth(RED_POTION_VALUE);
		else
			playing.getPlayer().changPower(BLUE_POTION_VALUE);
		
	}
	
	public void applyKeyToPlayer(Key k) {
		playing.getPlayer().pickKey();

		
	}
	
	private void loadImgs() {
		BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
		potionImgs = new BufferedImage[2][7];
		
		for (int j=0;j<potionImgs.length;j++) {
			for (int i=0;i<potionImgs[j].length;i++) {
				potionImgs[j][i] = potionSprite.getSubimage(12*i, 16*j, 12, 16);
			}
		}
		
		BufferedImage key1_Sprite = LoadSave.GetSpriteAtlas(LoadSave.KEY_1_ATLAS);
		key1_Imgs = new BufferedImage[1][8];
		
		for (int j=0;j<key1_Imgs.length;j++) {
			for (int i=0;i<key1_Imgs[j].length;i++) {
				key1_Imgs[j][i] = key1_Sprite.getSubimage(50*i, 50*j, 50, 50);
			}
		}
		
	}
	
	public void update() {
		for (Potion p: potions)
			if (p.isActive())
				p.update();
		
		for (Key k: keys)
			if (k.isActive())
				k.update();
	
	}
	
	public void setCameraValues(float x, float y) {
		this.cameraX = x;
		this.cameraY = y;
	}
	
	public void draw(Graphics g) {
		drawPotions(g);
		drawKeys(g);

	}

	private void drawKeys(Graphics g) {
		for (Key k: keys)
			if (k.isActive()) {
				int type = 0;
				
				int screenX = (int) (k.worldX - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				int screenY = (int) (k.worldY - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
//				System.out.println(cameraX);
									
				k.hitbox.x = screenX;
				k.hitbox.y = screenY;
				if (k.worldX + Game.TILES_SIZE > cameraX - ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) && 
					k.worldX - Game.TILES_SIZE < cameraX + ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) &&
					k.worldY + Game.TILES_SIZE > cameraY - ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2)) && 
					k.worldY - Game.TILES_SIZE < cameraY + ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2))) {
					g.drawImage(key1_Imgs[type][k.getAniIndex()], screenX, screenY, KEY_WIDTH, KEY_HEIGHT, null);

					g.setColor(Color.pink);
					g.drawRect((int) (screenX), (int) (screenY), (int) k.hitbox.width, (int) k.hitbox.height);
//					System.out.println(cameraX + " : " + cameraY);
				}
			}
			else {
				int type = 0;
				g.drawImage(key1_Imgs[type][0], 24*Game.TILES_SIZE, 35, KEY_WIDTH, KEY_HEIGHT, null);
			}
	}

	private void drawPotions(Graphics g) {
		for (Potion p: potions)
			if (p.isActive()) {
				int type = 0;
				if (p.getObjType() == RED_POTION)
					type = 1;
				
				int screenX = (int) (p.worldX - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				int screenY = (int) (p.worldY - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				if (p.getObjType() == RED_POTION)
//				System.out.println(screenX+ " "+screenY);
				p.hitbox.x = screenX;
				p.hitbox.y = screenY;
				if (p.worldX + Game.TILES_SIZE > cameraX - ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) && 
					p.worldX - Game.TILES_SIZE < cameraX + ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) &&
					p.worldY + Game.TILES_SIZE > cameraY - ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2)) && 
					p.worldY - Game.TILES_SIZE < cameraY + ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2))) {
					g.drawImage(potionImgs[type][p.getAniIndex()], screenX, screenY, POTION_WIDTH, POTION_HEIGHT, null);

					g.setColor(Color.pink);
					g.drawRect((int) (screenX), (int) (screenY), (int) p.hitbox.width, (int) p.hitbox.height);
//					System.out.println(cameraX + " : " + cameraY);
				}
			}
	}
	
	public void resetAllObject() {
		for (Potion p: potions)
			p.reset();
		
		for (Key k: keys)
			k.reset();
		
	}

}
