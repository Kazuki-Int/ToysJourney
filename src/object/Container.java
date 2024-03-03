package object;
import static utilz.Constants.ObjectConstants.*;

import main.Game;

public class Container extends GameObject {

	public Container(int x, int y, int objType) {
		super(x, y, objType);
		createHitbox();
	}

	private void createHitbox() {
		
		if (objType == BOX) {
			
			initHitbox(23, 25);
			xDrawOffset = (int) (7 * Game.SCALE);
			yDrawOffset = (int) (12 * Game.SCALE);
		} else {	
			
			initHitbox(CONTAINER_WIDTH, CONTAINER_HEIGHT);
			xDrawOffset = (int) (8 * Game.SCALE);
			yDrawOffset = (int) (5 * Game.SCALE);
		}
		
	}
	
	public void update() {
		if (doAnimation)
			updateAnimationTick();
	}

}
