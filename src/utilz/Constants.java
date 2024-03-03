package utilz;

import main.Game;

public class Constants {
	
	public static final int ANI_SPEED = 25;
	
	public static class ObjectConstants {
		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int RED_POTION_VALUE = 100;
		public static final int BLUE_POTION_VALUE = 10;	
		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);
		
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int CONTAINER_WIDTH_DEFAULT = 23;
		public static final int CONTAINER_HEIGHT_DEFAULT = 25;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);
		
		public static final int KEY_1 = 4;
		public static final int KEY_WIDTH_DEFAULT = (int)(50 * 0.76);
		public static final int KEY_HEIGHT_DEFAULT = (int)(50 * 0.76);
		public static final int KEY_WIDTH = (int) (Game.SCALE * KEY_WIDTH_DEFAULT);
		public static final int KEY_HEIGHT = (int) (Game.SCALE * KEY_HEIGHT_DEFAULT);
		
		public static final int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case RED_POTION, BLUE_POTION:
				return 7;
			case BARREL, BOX, KEY_1:
				return 8;
			}
			
			return 1;
		}
	}
	
	public static class EnemyConstants {
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;
		
		//CRABBY
		public static final int CRABBY = 1;
		
		public static final int CRABBY_WIDTH_DEFAULT = 72;
		public static final int CRABBY_HEIGHT_DEFAULT = 32;
		
		public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
		public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
		
		//STRAWBERRY SLIME
		public static final int SLIME = 2;
		
		public static final int SLIME_WIDTH_DEFAULT = (int) (27 * 0.76);
		public static final int SLIME_HEIGHT_DEFAULT = (int) (24 * 0.76);
		
		public static final int SLIME_WIDTH = (int) (SLIME_WIDTH_DEFAULT * Game.SCALE);
		public static final int SLIME_HEIGHT = (int) (SLIME_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int SLIME_DRAWOFFSET_X = (int) (12 * Game.SCALE);
		public static final int SLIME_DRAWOFFSET_Y = (int) (13.5 * Game.SCALE);

		
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
			case SLIME:
				switch (enemy_state) {
				case IDLE: 
					return 6;
				case RUNNING:
					return 6;
				case ATTACK:
					return 6;
				case HIT:
					return 6;
				case DEAD:
					return 6;
				}
			}
			
			return 0;
			
		}
		
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case CRABBY:
				return 10;
			case SLIME:
				return 20;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case CRABBY:
				return 15;
			case SLIME:
				return 20;
			default:
				return 0;
			}
		
		}
	
	}
	
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 126; //140
			public static final int B_HEIGHT_DEFAULT = 59; //56
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * 2);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * 2); //112
		}
		
		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 56;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}
		
		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);	
		}
		
		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 45;
			public static final int VOLUME_DEFAULT_HEIGHT = 45;
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
		public static final int ATK_FRONT = 8;
		public static final int ATK_RIGHT = 9;
		public static final int ATK_LEFT = 10;
		public static final int ATK_BACK = 11;
		public static final int HIT_FRONT = 12;
		public static final int HIT_RIGHT = 13;
		public static final int HIT_LEFT = 14;
		public static final int HIT_BACK = 15;
		public static final int DEAD = 16;
		
		
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
			case ATK_FRONT:
				return 4;
			case ATK_RIGHT:
				return 4;
			case ATK_LEFT:
				return 4;
			case ATK_BACK:
				return 4;
			case HIT_FRONT:
				return 6;
			case HIT_RIGHT:
				return 6;
			case HIT_LEFT:
				return 6;
			case HIT_BACK:
				return 6;
			case DEAD:
				return 8;
			default:
				return 0;
			
			
			}
			
		}
		
	}
	
}

















