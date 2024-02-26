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
		tileSprite = new BufferedImage[152];
		for(int j = 0; j < 8; j++) 
			for(int i = 0; i < 19; i++) {
				int index = j*19 + i;
				tileSprite[index] = img.getSubimage(i*16, j*16, 16, 16);
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
//				
				int worldx = i * Game.TILES_SIZE;
				int worldy = j * Game.TILES_SIZE;
				int screenX = (int) (worldx - game.getPlaying().getPlayer().getCameraX() + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				int screenY = (int) (worldy - game.getPlaying().getPlayer().getCameraY() + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				
				if (i*Game.TILES_SIZE > cameraX - ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) && 
					i*-Game.TILES_SIZE < cameraX + ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) &&
					j*Game.TILES_SIZE > cameraY - ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2)) && 
					j*-Game.TILES_SIZE < cameraY + ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2))) {
					g.drawImage(tileSprite[index], screenX, screenY, Game.TILES_SIZE, Game.TILES_SIZE, null);
				}
			}	
	}
	
	public void update() {
		
	}
	
	public Tile getCurrentTile() {
		return tileOne;
	}

}
