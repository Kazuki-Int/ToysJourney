package tiles;

import java.awt.image.BufferedImage;

import utilz.LoadSave;

public enum Floor {
	
	WORLD(LoadSave.TILE_ATLAS, 12, 11), // pic
	HOUSE(LoadSave.HOUSE_TILE_ATLAS, 7, 5); // pic
	
	private BufferedImage[] tileSprite;

	
	Floor(String spriteName, int tilesInWidth, int tilesInHeight) {
		BufferedImage img = LoadSave.GetSpriteAtlas(spriteName);
		tileSprite = new BufferedImage[tilesInWidth * tilesInHeight];
		for(int j = 0; j < tilesInHeight; j++) 
			for(int i = 0; i < tilesInWidth; i++) {
				int index = j*tilesInWidth + i;
				tileSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
	}
	
	public BufferedImage[] getSprite() {
		return tileSprite;
	}

}