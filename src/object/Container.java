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
			
			initHitbox(45, 43);
		} else {	
			initHitbox(45, 45);
		}
	}
	
	public void update() {
		if (doAnimation)
			updateAnimationTick();
	}

}
