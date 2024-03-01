package tiles;

import java.util.ArrayList;

import entites.Building;

public class Tile {
	
	private int[][] tileData;
	private Floor floorType;
	private ArrayList<Building> buildingarrArrayList;
	private ArrayList<Doorway> doorwayArrayList; //added

	public Tile(int[][] tileData, Floor floortype, ArrayList<Building> buildingarrArrayList) {
		this.tileData = tileData;
		this.floorType = floortype;
		this.buildingarrArrayList = buildingarrArrayList;
		this.doorwayArrayList = new ArrayList<>(); //added
	}
	
	public void addDoorway(Doorway doorway) { //added
		this.doorwayArrayList.add(doorway);
	}
	
	public ArrayList<Doorway> getDoorwayArrayList() { //added
		return doorwayArrayList;
	}
	
	public ArrayList<Building> getBuildingArrayList() { //added
		return buildingarrArrayList;
	}
	
	public int getSpriteIndex(int x, int y) {
		return tileData[y][x];
	}
	
	public int[][] getTileData() {
		return tileData;
	}
	
	public Floor getFloorType() {
		return floorType; //added
	}
	
	public int getArrayWidth() {
        return tileData[0].length;
    }

    public int getArrayHeight() {
        return tileData.length;
    }

}
