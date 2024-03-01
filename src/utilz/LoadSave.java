  package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import entites.Slime;
import main.Game;
import static utilz.Constants.EnemyConstants.SLIME;

public class LoadSave {
	
	public static final String FRIEREN = "Frieren_Animation.png";
	public static final String CRABBY_SPRITE = "crabby_sprite.png";
	public static final String SLIME_SPRITE = "slime_sprite.png";
	
	//MAPDATA & ENEMY
	public static final String TILE_ATLAS = "map.png";
	public static final String TILE_DATA = "res/map.txt";
	public static final String ENEMY_DATA = "res/map_enemy.txt";
	
	//BACKGROUND & UI
	public static final String GAME_NAME = "background/game_name.png";
	public static final String PAUSE_BACKGROUND = "background/pause_menu.png";
	public static final String MENU_BACKGROUND_IMG = "background/animation_background2.png";
	public static final String FRIEND_BACKGROUND = "background/friend_background.png";
	public static final String GAME_BACKGROUND = "background/masterpiece_background.png";
	public static final String STATUS_BAR = "background/health_power_bar.png";
	
	//BUTTON
	public static final String MENU_BUTTONS = "buttons/button_atlas.png";
	public static final String SOUND_BUTTONS = "buttons/sound_button.png";
	public static final String URM_BUTTONS = "buttons/urm_buttons.png";
	public static final String VOLUME_BUTTONS = "buttons/volume_button.png";
	
	//OBJECT
	public static final String HOUSE = "house.png";
	public static final String POTION_ATLAS = "objects/potions_sprites.png";
	public static final String KEY_1_ATLAS = "objects/Key_1.png";
 	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		
		try {
			 img = ImageIO.read(is);			
				
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static ArrayList<Slime> GetSlimes() {
		ArrayList<Slime> list = new ArrayList<>();
		try {
			
			InputStream is = LoadSave.class.getResourceAsStream(TILE_DATA);
            File file = new File(ENEMY_DATA);
            
            Scanner scanner = new Scanner(file);
            int col = 0, row = 0;
            // Loop through each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] numbers = line.split(" ");

                for (String number : numbers) {
                	int index = Integer.parseInt(number);
                    if (index == SLIME) 
                    	list.add(new Slime(col * Game.TILES_SIZE, row * Game.TILES_SIZE));
                    col++;
                }
                col = 0;
                row++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;
	}
	
	
	public static int[][] GetTileData() {
		int[][] tileData = new int[Game.MAP_HEIGHT][Game.MAP_WIDTH];
		try {
			
			InputStream is = LoadSave.class.getResourceAsStream(TILE_DATA);
            File file = new File(TILE_DATA);
            
            Scanner scanner = new Scanner(file);
            int col = 0, row = 0;
            // Loop through each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] numbers = line.split(" ");

                for (String number : numbers) {
                    // Convert the string to an integer
                    int index = Integer.parseInt(number);
                    tileData[row][col] = index;
                    col++;
                }
                col = 0;
                row++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return tileData;
	}
	
}
