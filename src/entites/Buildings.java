package entites;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public enum Buildings {
	
	HOUSE("house.png", 82, 175, 37, 49),// building pic and doorway pos
	PICKPUCK_ONE("pickpuck1.png", 0, 0, 0, 0);
	
	BufferedImage houseImg;
	Rectangle2D.Float hitboxDoorway;

	Buildings(String BuildingType, int doorwayX, int doorwayY, int doorwayWidth, int doorwayHeight) {
		houseImg = LoadSave.GetSpriteAtlas(BuildingType);
		
		hitboxDoorway = new Rectangle2D.Float(doorwayX, doorwayY, doorwayWidth * Game.SCALE, doorwayHeight * Game.SCALE);
	}
	
	public Rectangle2D.Float getHitboxDoorway() {
        return hitboxDoorway;
    }
	
	public BufferedImage getHouseImg() {
		return houseImg;
	}
}