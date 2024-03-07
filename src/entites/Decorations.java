package entites;

import java.awt.PageAttributes.OriginType;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public enum Decorations {
	TREE_1(LoadSave.TREE_1, 103, 184),
	TREE_2(LoadSave.TREE_2, 102, 185),
	SMALL_TREE_1(LoadSave.S_TREE_1, 33, 64),
	SMALL_TREE_2(LoadSave.S_TREE_2, 32, 50),
	BUSH(LoadSave.BUSH_1, 52, 31),
	ROCK_1(LoadSave.ROCK_1, 49, 27),
	ROCK_2(LoadSave.ROCK_2, 43, 22),
	SMALL_ROCK_1(LoadSave.S_ROCK_1, 20, 14),
	ELEPHANT(LoadSave.ELEPHANT, 91, 80);
	
	BufferedImage decoImg;
	int width, height;

	Decorations(String filename, int width, int height) {
		this.decoImg = LoadSave.GetSpriteAtlas(filename);
		this.width = width;
		this.height = height;
	}
	
	public BufferedImage getDecoImg() {
		return decoImg;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
