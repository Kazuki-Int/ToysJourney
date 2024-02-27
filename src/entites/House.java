package entites;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class House {

    private Point pos;
    private float cameraX, cameraY;
    private BufferedImage img;
    
    public House(Point pos) {
        this.pos = pos;
        loadHouseImg();
    }

    private void loadHouseImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.HOUSE);
	}

	public Point getPos() {
        return pos;
    }
    
    public void setCameraValues(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public void render(Graphics g) {
    	g.drawImage(img, (int)(pos.x-cameraX), (int)(pos.y-cameraY), (int)(168 * Game.SCALE) , (int)(224 * Game.SCALE) ,null);
    }
}