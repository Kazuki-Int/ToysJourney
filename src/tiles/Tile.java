package tiles;

public class Tile {
	
	private int[][] tileData;
	
	public Tile(int[][] tileData) {
		this.tileData = tileData;
	}
	
	public int getSpriteIndex(int x, int y) {
		return tileData[y][x];
	}
	
	public int[][] getTileData() {
		return tileData;
	}
}
