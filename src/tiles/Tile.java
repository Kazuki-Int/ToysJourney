package tiles;

import java.awt.Container;
import java.util.ArrayList;

import entites.Boss;
import entites.Building;
import entites.Mimic;
import entites.Slime;
import objectz.*;

public class Tile {
	
	private int[][] tileData;
	private Floor floorType;
	private ArrayList<Building> buildingarrArrayList;
	private ArrayList<Doorway> doorwayArrayList; //added
	private ArrayList<Mimic> mimicArrayList;
	private ArrayList<Containerz> containerArrayList;
	private ArrayList<Boss> bossArrayList;
	private ArrayList<Slime> slimeArrayList;

	public Tile(int[][] tileData, Floor floortype, ArrayList<Building> buildingarrArrayList) {
		this.tileData = tileData;
		this.floorType = floortype;
		this.buildingarrArrayList = buildingarrArrayList;
		this.doorwayArrayList = new ArrayList<>(); //added
		this.mimicArrayList = new ArrayList<>();
		this.containerArrayList = new ArrayList<>();
		this.bossArrayList = new ArrayList<Boss>();
		this.slimeArrayList = new ArrayList<Slime>();
	}
	
	public void addSlime(Slime slime) {
		this.slimeArrayList.add(slime);
	}
	
	public ArrayList<Slime> getslimeArr(){
		return slimeArrayList;
	}
	
	public void addBoss(Boss boss) {
		this.bossArrayList.add(boss);
	}
	
	public ArrayList<Boss> getBossArrayList(){
		return bossArrayList;
	}
	
	public void addMimic(Mimic mimic) {
		this.mimicArrayList.add(mimic);
	}
	
	public ArrayList<Mimic> getMimicArr(){
		return mimicArrayList;
	}
	
	public void addContainerz(Containerz container) {
		this.containerArrayList.add(container);
	}
	
	public ArrayList<Containerz> getContainerArr(){
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
