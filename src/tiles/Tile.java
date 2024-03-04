package tiles;

import java.awt.Container;
import java.util.ArrayList;

import entites.Building;
import entites.Mimic;
import entites.Slime;

public class Tile {
	
	private int[][] tileData;
	private Floor floorType;
	private ArrayList<Building> buildingarrArrayList;
	private ArrayList<Doorway> doorwayArrayList; //added
	private ArrayList<Mimic> mimicArrayList;
	private ArrayList<Container> containerArrayList;

	public Tile(int[][] tileData, Floor floortype, ArrayList<Building> buildingarrArrayList) {
		this.tileData = tileData;
		this.floorType = floortype;
		this.buildingarrArrayList = buildingarrArrayList;
		this.doorwayArrayList = new ArrayList<>(); //added
		this.mimicArrayList = new ArrayList<>();
		this.containerArrayList = new ArrayList<>();
	}
	
	public void addMimic(Mimic mimic) {
		this.mimicArrayList.add(mimic);
	}
	
	public ArrayList<Mimic> getMimicArr(){
		return mimicArrayList;
	}
	
	public void addContainer(Container container) {
		this.containerArrayList.add(container);
	}
	
	public ArrayList<Container> getContainerArr(){
		return containerArrayList;
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
