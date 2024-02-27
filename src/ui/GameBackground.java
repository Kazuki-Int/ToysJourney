package ui;

public class GameBackground {
	
	protected boolean doAnimation, active = true;
	protected int aniTick, aniIndex;
	
	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= 200) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 7) {
				aniIndex = 0;
				
			}
		}
	}
	
	public void reset() {
		aniIndex = 0;
		aniTick = 0;
		doAnimation = true;
	}
	
	public int getAniIndex() {
		return aniIndex;
	}
}
