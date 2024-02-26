 //package utilz;

//public class Constants {
//	
//	public static class Directions {
//		public static final int LEFT = 0;
//		public static final int DOWN = 1;
//		public static final int RIGHT = 2;
//		public static final int UP = 3;
//	}
	
	// everything here will revolve around the player. 
//	public static class PlayerConstants {
		// value of index in each animations.
//		public static final int IDLE_FRONT = 0;
//		public static final int IDLE_LEFT = 1;
//		public static final int IDLE_RIGHT = 2;
//		public static final int IDLE_BACK = 3;
//		public static final int RUN_FRONT = 4;
//		public static final int RUN_LEFT = 5;
//		public static final int RUN_RIGHT = 6;
//		public static final int RUN_BACK = 7;
//		public static final int ATK_FRONT = 8;
//		public static final int ATK_LEFT = 9;
//		public static final int ATK_RIGHT = 10;
//		public static final int ATK_BACK = 11;
//		public static final int DEAD_FRONT = 12;
//		public static final int DEAD_LEFT = 13;
//		public static final int DEAD_RIGHT = 14;
//		public static final int DEAD_BACK = 15;
//		public static final int DEAD = 16;
		
//		public static final int IDLE_FRONT = 0;
//		public static final int IDLE_RIGHT = 1;
//		public static final int IDLE_LEFT = 2;
//		public static final int IDLE_BACK = 3;
//		public static final int RUN_FRONT = 4;
//		public static final int RUN_RIGHT = 5;
//		public static final int RUN_LEFT = 6;
//		public static final int RUN_BACK = 7;
//		public static final int ATK_LEFT = 8;
//		public static final int ATK_RIGHT = 9;
//		public static int GetSpriteAmount(int player_action) {
//
//			switch(player_action) {
//			case RUN_FRONT:
//			case RUN_BACK:
//			case RUN_RIGHT:
//			case RUN_LEFT:
//				return 6;
//			case IDLE_BACK:
//			case IDLE_FRONT:
//			case IDLE_RIGHT:
//			case IDLE_LEFT:
//				return 12;
//			case ATK_BACK:
//			case ATK_FRONT:
//			case ATK_RIGHT:
//			case ATK_LEFT:
//				return 7;
//			default:
//				return 4;
//			}
//		}
//		
//	}
//
//}

package utilz;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public static final int CRABBY = 0;
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;
		
		public static final int CRABBY_WIDTH_DEFAULT = 72;
		public static final int CRABBY_HEIGHT_DEFAULT = 32;
		
		public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
		public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);
		
		public static int getSpriteAmount(int enemy_type, int enemy_state) {
			
			switch (enemy_type) {
			case CRABBY:
				switch (enemy_state) {
				case IDLE: 
					return 9;
				case RUNNING:
					return 6;
				case ATTACK:
					return 7;
				case HIT:
					return 4;
				case DEAD:
					return 5;
				}
			}
			
			return 0;
			
		}
		
	}
	
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * 2);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * 2); //112
		}
		
		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}
		
		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);	
		}
		
		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;
			
			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);

		}
	}
	
	public static class Directions {
		public static final int LEFT = 0;
		public static final int DOWN = 1;
		public static final int RIGHT = 2;
		public static final int UP = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE_FRONT = 0;
		public static final int IDLE_RIGHT = 1;
		public static final int IDLE_LEFT = 2;
		public static final int IDLE_BACK = 3;
		public static final int RUN_FRONT = 4;
		public static final int RUN_RIGHT = 5;
		public static final int RUN_LEFT = 6;
		public static final int RUN_BACK = 7;
		public static final int ATK_RIGHT = 8;
		public static final int ATK_LEFT = 9;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case IDLE_FRONT:
				return 5;
			case IDLE_RIGHT:
				return 5;
			case IDLE_LEFT:
				return 5;
			case IDLE_BACK:
				return 5;
			case RUN_FRONT:
				return 6;
			case RUN_RIGHT:
				return 6;
			case RUN_LEFT:
				return 6;
			case RUN_BACK:
				return 6;
			case ATK_RIGHT:
				return 8;
			case ATK_LEFT:
				return 8;
			default:
				return 0;
			
			
			}
			
		}
		
	}
	
}

















