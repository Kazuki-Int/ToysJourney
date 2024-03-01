package object;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Key extends GameObject {

	
	public Key(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = true;
		
		initHitbox(KEY_WIDTH, KEY_HEIGHT); 
		
	}
	
	public void update() {
		updateAnimationTick();
	}
}
