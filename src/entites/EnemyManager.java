package entites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObjectConstants.POTION_HEIGHT;
import static utilz.Constants.ObjectConstants.POTION_WIDTH;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] crabbyArr;
	private ArrayList<Crabby> crabbies = new ArrayList<>();
	
	private float cameraX, cameraY;
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		crabbies = LoadSave.GetCrabs();
		System.out.println("size of crab: " + crabbies.size());
		
	}

	public void update() {
		for (Crabby c : crabbies)
			c.update();
		
	}
	
	public void draw(Graphics g) {
		drawCrabs(g);
		
	}
	
	public void setCameraValues(float x, float y) {
		this.cameraX = x;
		this.cameraY = y;
	}
	
	private void drawCrabs(Graphics g) {
		for (Crabby c : crabbies) {
			int screenX = (int) (c.x - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
			int screenY = (int) (c.y - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
//			System.out.println(cameraX);
			
			if (c.x + Game.TILES_SIZE > cameraX - ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) && 
				c.x - Game.TILES_SIZE < cameraX + ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) &&
				c.y + Game.TILES_SIZE > cameraY - ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2)) && 
				c.y - Game.TILES_SIZE < cameraY + ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2))) {
				g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], screenX, screenY, CRABBY_WIDTH, CRABBY_HEIGHT, null);
				c.hitbox.x = screenX;
				c.hitbox.y = screenY;
				g.setColor(Color.pink);
				g.drawRect((int) (screenX), (int) (screenY), (int) c.hitbox.width, (int) c.hitbox.height);
			}
		}

	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArr.length; j++)
			for (int i = 0; i < crabbyArr[j].length; i++)
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
		
	}
	
}
