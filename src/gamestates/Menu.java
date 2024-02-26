package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {
	
	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImg, backgroundImgPic;
	private int menuX, menuY, menuWidth, menuHeight;

	public Menu(Game game) {
		super(game);
		loadButtons();
//		loadBackground();
		backgroundImgPic = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
	}
	
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
		menuX = Game.SCREEN_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (45 * Game.SCALE);
		
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Game.SCREEN_WIDTH/2, (int) (140 * Game.SCALE), 0, Gamestate.PLAYING); //150
		buttons[1] = new MenuButton(Game.SCREEN_WIDTH/2, (int) (210 * Game.SCALE), 1, Gamestate.OPTIONS); //220
		buttons[2] = new MenuButton(Game.SCREEN_WIDTH/2, (int) (280 * Game.SCALE), 2, Gamestate.QUIT); //290
	}

	@Override
	public void update() {
		for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(backgroundImgPic, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, null);
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
		
		for (MenuButton mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}
		
		resetButtons();
	}
	
	private void resetButtons() {
		for (MenuButton mb : buttons) 
			mb.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			Gamestate.state = Gamestate.PLAYING;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
