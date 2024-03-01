package tiles;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entites.Building;
import entites.Buildings;
import gamestates.Playing;
import main.Game;
import utilz.HelpMethods;
import utilz.LoadSave;

public class TileManager {

	private Tile tileOne, worldMap, houseMap;
	private float cameraX, cameraY;
	private Playing playing;

	
	public TileManager(Playing playing) {
		this.playing = playing;
		initTestMap();
	}
	
	public void setCameravalues(float x, float y) {
		this.cameraX = x;
		this.cameraY = y;
	}
	

	public void drawBuilding(Graphics g) {
		if (tileOne.getBuildingArrayList() != null)
			for (Building b : tileOne.getBuildingArrayList()) {
				int screenX = (int) (b.getPos().x - cameraX + (int) ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				int screenY = (int) (b.getPos().y - cameraY + (int) ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE*Game.SCALE/2)));
				g.drawImage(b.getBuildingType().getHouseImg(), screenX, screenY, (int)(b.getWidth() * Game.SCALE) , (int)(b.getHeight() * Game.SCALE) ,null);
			}	
    }

	public void drawTiles(Graphics g) {
		
		if (tileOne == worldMap) {
			for(int j = 0; j < tileOne.getTileData().length; j++) 
				for(int i = 0; i < tileOne.getTileData()[0].length; i++) {
					int index = tileOne.getSpriteIndex(i, j);
					
					int worldx = i * Game.TILES_SIZE;
					int worldy = j * Game.TILES_SIZE;
					int screenX = (int) (worldx - cameraX + (int) ((Game.SCREEN_WIDTH/2)));
					int screenY = (int) (worldy - cameraY + (int) ((Game.SCREEN_HEIGHT/2)));
					//System.out.println(cameraX + " : " + cameraY);
	
					if (worldx + Game.TILES_SIZE * 2 > cameraX - ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) && 
						worldx - Game.TILES_SIZE * 2 < cameraX + ((Game.SCREEN_WIDTH/2)-(Game.TILES_SIZE/2)) &&
						worldy + Game.TILES_SIZE * 2 > cameraY - ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2)) && 
						worldy - Game.TILES_SIZE * 2 < cameraY + ((Game.SCREEN_HEIGHT/2)-(Game.TILES_SIZE/2))) {
						g.drawImage(tileOne.getFloorType().getSprite()[index], screenX, screenY, Game.TILES_SIZE, Game.TILES_SIZE, null);
	//					System.out.println(screenX + " : " + screenY);
				}
			}
		} else {
			for (int j = 0; j < tileOne.getTileData().length; j++)
				for (int i = 0; i < tileOne.getTileData()[0].length; i++) {
					int index = tileOne.getSpriteIndex(i, j);
					
					int worldx = i * Game.TILES_SIZE;
					int worldy = j * Game.TILES_SIZE;
					int screenX = (int) (worldx - cameraX + (int) ((Game.SCREEN_WIDTH/2)));
					int screenY = (int) (worldy - cameraY + (int) ((Game.SCREEN_HEIGHT/2)));
					g.drawImage(tileOne.getFloorType().getSprite()[index], screenX, screenY, Game.TILES_SIZE, Game.TILES_SIZE, null);
				}
					
		}
	}
	
	public void draw(Graphics g) {
		drawTiles(g);
		drawBuilding(g); //added
	}
	
	public Doorway isPlayerOnDoorway(Rectangle2D.Float playerHitbox) { //added
		for (Doorway doorway : tileOne.getDoorwayArrayList()) {
			if (doorway.isPlayerInsideDoorway(playerHitbox, cameraX, cameraY))	
				return doorway;
		}
		return null;	
	}
	
	public void changeMap(Doorway doorwayTarget) { //added
		this.tileOne = doorwayTarget.getTileLocatedIn();		
		float cx = doorwayTarget.getPosOfDoorway().x - ((Game.SCREEN_WIDTH/2)-(Game.PLAYER_SIZE));
		float cy = doorwayTarget.getPosOfDoorway().y - ((Game.SCREEN_HEIGHT/2)-(Game.PLAYER_SIZE/2));
		System.out.println(cx + " : " + cy);
		playing.getPlayer().setCameraValues(cx, cy);
		cameraX = cx;
		cameraY = cy;
		System.out.println(tileOne.getFloorType());
		
		playing.setDoorwayJustPassed(true);
	}

	
	public void update() {
		
	}
	
	public Tile getCurrentTile() {
		return tileOne;
	}
	
	private void initTestMap() { //added

		ArrayList<Building> buildingArrayList = new ArrayList<>();
		buildingArrayList.add(new Building(new Point(2000,1400), Buildings.HOUSE, 168, 224));
//		buildingArrayList.add(new Building(new Point(350,1325), Buildings.PICKPUCK_ONE, 50, 50));
        worldMap = new Tile(LoadSave.GetTileData(LoadSave.TILE_DATA, 40 , 40), Floor.WORLD, buildingArrayList);
        houseMap = new Tile(LoadSave.GetTileData(LoadSave.HOUSE_TILE_DATA, 10, 10), Floor.HOUSE , null);
        
//        HelpMethods.AddDoorwayToTile(worldMap, houseMap, 0);
        HelpMethods.ConnectTwoDoorways(
        		worldMap, 
        		HelpMethods.CreateHitboxForDoorWayFloat(worldMap, 0), 
        		houseMap, 
        		HelpMethods.CreateHitboxForDoorway(4, 9));
        
        tileOne = worldMap;
    }


}
