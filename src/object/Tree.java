package object;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Tree extends GameObject {

	public Tree(int x, int y, int objType) {
		super(x, y, objType);
		doAnimation = false;

		initHitbox(TREE_1_WIDTH, TREE_1_HEIGHT); 

		xDrawOffset = (int) (3 * Game.SCALE); // 3 pix to the left
		yDrawOffset = (int) (2 * Game.SCALE); // 2 pix to the top
		
	}
	
	public void update() {
		updateAnimationTick();
	}
	
}
	
