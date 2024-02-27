package object;

import main.Game;

public class Key extends GameObject {

	
	public Key(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = true;
		// blue_potion_size
//		xDrawOffset = (int) (3 * Game.SCALE); // 3 pix to the left
//		yDrawOffset = (int) (2 * Game.SCALE); // 2 pix to the top
		
	}
	
	public void update() {
		updateAnimationTick();
	}
}
