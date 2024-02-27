package utilz;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	
	public static boolean CanWalkHere(Rectangle2D hitbox, Float x, Float y, int[][] lvlData) {
		if (x - hitbox.getWidth()/2 < 0 || y - hitbox.getHeight()/2 < 0)
			return false;
		
		if (x + hitbox.getWidth()/2  >= Game.MAP_WIDTH * Game.TILES_SIZE || y + hitbox.getHeight()/2 >= Game.MAP_HEIGHT * Game.TILES_SIZE)
			return false;
		
		Point[] tileCords = GetTileCords(hitbox, x, y);
		int[] tileIds = GetTileIds(tileCords, lvlData);
		return isTileWalkable(tileIds);
	}
	
	 private static Point[] GetTileCords(Rectangle2D hitbox, float x, float y) {
	        Point[] tileCords = new Point[4];
	        //System.out.println(x + " : " + y);
	        
	        int left = (int) ((x - hitbox.getWidth()/2) / Game.TILES_SIZE);
	        int right = (int) ((x + hitbox.getWidth()/2) / Game.TILES_SIZE);
	        int top = (int) ((y) / Game.TILES_SIZE);
	        int bottom = (int) ((y + hitbox.getHeight()/2) / Game.TILES_SIZE);

	        tileCords[0] = new Point(left, top);
	        tileCords[1] = new Point(right, top);
	        tileCords[2] = new Point(left, bottom);
	        tileCords[3] = new Point(right, bottom);

	        return tileCords;

	    }
	 
	 private static int[] GetTileIds(Point[] tileCords, int[][] lvlData) {
	        int[] tileIds = new int[4];

	        for (int i = 0; i < tileCords.length; i++) {
	            tileIds[i] = lvlData[tileCords[i].y][tileCords[i].x];
	        	//System.out.println(tileIds[i]);
	        }
	        return tileIds;
	    }

	public static boolean isTileWalkable(int[] tileIds) {
			for (int i : tileIds) 
				if (!(isTileWalkable(i)))
						return false;
			return true;
		}	
	 
	public static boolean isTileWalkable(int tileId) {
		return (tileId != 94 && tileId != 95 && (tileId < 51 || tileId > 53) && tileId != 108 && tileId != 85 && tileId != 74 && 
				tileId != 62 && tileId != 42 && tileId != 44 && tileId != 45 && tileId != 58 && tileId != 70 && tileId != 82 && 
				tileId != 83 && tileId != 72 && tileId != 60 && tileId != 34 && tileId != 46 && tileId != 55);
	}
	
	
}
