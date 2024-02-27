package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Menu;
import main.Game;
import object.Key;
import utilz.LoadSave;

public class BackgroundManager {
	
	
	private Menu menu;
	private BufferedImage[][] backgroundImgs;
	private GameBackground background;
	
	public BackgroundManager(Menu menu) {
		this.menu = menu;
		loadImgs();
		
		background = new GameBackground();
	}
	
	private void loadImgs() {
		BufferedImage backgroundSprite = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
		backgroundImgs = new BufferedImage[1][8];
		
		for (int j=0;j<1;j++) {
			for (int i=0;i<7;i++) {
				backgroundImgs[j][i] = backgroundSprite.getSubimage(400*i, 225*j, 400, 225);
			}
		}
	}
	
	public void update() {
		background.updateAnimationTick();
	}
	
	public void draw(Graphics g) {
//		System.out.println(background.getAniIndex());
		g.drawImage(backgroundImgs[0][background.getAniIndex()], 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, null);
	}
}
