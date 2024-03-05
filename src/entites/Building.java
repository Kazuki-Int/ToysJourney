package entites;

import java.awt.Point;



public class Building extends Entity{
	
	private Point pos;
	private Buildings buildingType;
	private int width, height;
	
	public Building(Point pos, Buildings buildingType, int width, int height) {
		super(pos.x, pos.y, width, height);
        this.pos = pos;
        this.buildingType = buildingType;
        this.width = width;
        this.height = height;
	}

    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
//	
//	public int getDoorwayX() {
//		return doorwayX;
//	}
//	
//	public int getDoorwayY() {
//		return doorwayY;
//	}

	public Buildings getBuildingType() {
        return buildingType;
    }

    public Point getPos() {
        return pos;
    }

}
