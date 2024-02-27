package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class TileManager {

	private Game game;

	private BufferedImage[] tileSprite;
	private Tile tileOne;
	private float cameraX, cameraY;
	
	public TileManager(Game game) {
		this.game = game;
		importTiles();
		tileOne = new Tile(LoadSave.GetTileData());
	}
	
	private void importTiles() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.TILE_ATLAS);
		tileSprite = new BufferedImage[132];
		for(int j = 0; j < 11; j++) 
			for(int i = 0; i < 12; i++) {
				int index = j*12 + i;
				tileSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
		
	}
	
	public void setCameravalues(float x, float y) {
		this.cameraX = x;
		this.cameraY = y;
	}
	

	public void draw(Graphics g) {
		
		for(int j = 0; j < tileOne.getTileData().length; j++) 
			for(int i = 0; i < tileOne.getTileData()[0].length; i++) {
				int index = tileOne.getSpriteIndex(i, j);
				
				int worldx = i * Game.TILES_SIZE;
				int worldy = j * Game.TILES_SIZE;
				int screenX = (int) (worldx - cameraX + (int) ((Game.SCREEN_WIDTH/2)));
				int screenY = (int) (worldy - cameraY + (int) ((Game.SCREEN_HEIGHT/2)));
				//System.out.println(cameraX + " : " + cameraY);

				if (worldx + Game.TILES_SIZE * 2 > cameraX - ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) && 
					worldx - Game.TILES_SIZE * 2 < cameraX + ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) &&
					worldy + Game.TILES_SIZE * 2 > cameraY - ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2)) && 
					worldy - Game.TILES_SIZE * 2 < cameraY + ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2))) {
					g.drawImage(tileSprite[index], screenX, screenY, Game.TILES_SIZE, Game.TILES_SIZE, null);
//					System.out.println(screenX + " : " + screenY);
				}
				//g.drawImage(tileSprite[index], i* Game.TILES_SIZE - (int)cameraX, j* Game.TILES_SIZE -(int)cameraY, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}	
	}
	
	public void update() {
		
	}
	
	public Tile getCurrentTile() {
		return tileOne;
	}

}
