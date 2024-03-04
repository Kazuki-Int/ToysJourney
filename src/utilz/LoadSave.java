 package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import entites.Mimic;
import entites.Slime;
import main.Game;
import static utilz.Constants.EnemyConstants.*;

public class LoadSave {
	
	public static final String FRIEREN = "Frieren_Animation.png";
	public static final String HIMMEL = "Himmel_Animation.png";
	public static final String MIMIC_SPRITE = "mimic_sprite.png";
	public static final String SLIME_SPRITE = "slime_sprite.png";
	public static final String BOSS_SPRITE = "boss_animation.png";
	
	//MAPDATA & ENEMY
	public static final String TILE_ATLAS = "map.png";
	public static final String TILE_DATA = "270 271 298 287 171 168 302 272 287 88 145 146 147 148 149 150 172 302 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "284 285 289 302 272 300 265 286 263 237 159 160 161 162 163 164 165 144 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "169 299 303 229 286 233 298 264 170 251 173 174 175 176 177 178 142 287 143 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "230 302 301 168 0 302 0 284 302 0 187 188 189 190 191 192 109 141 0 143 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "0 0 257 0 284 172 0 0 142 109 108 108 108 108 108 108 108 141 142 157 141 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "0 0 0 0 0 0 0 170 109 108 108 108 199 200 201 202 108 109 154 143 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "109 109 109 109 109 109 109 109 108 108 108 108 213 214 215 216 108 108 0 144 0 143 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 109 144 0 154 171 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "118 0 0 155 143 144 0 154 0 0 0 121 108 108 108 108 108 108 108 109 154 143 155 0 0 0 170 0 0 0 0 0 0 158 0 0 0 0 0 0 \n"
			+ "0 156 141 170 272 157 0 169 168 141 143 0 121 108 108 108 108 108 108 108 109 109 144 0 171 140 141 140 0 156 0 0 170 156 0 156 0 170 144 0 \n"
			+ "169 171 169 156 286 301 0 157 170 155 169 298 0 79 108 108 108 108 108 108 108 108 109 141 154 170 0 0 172 156 171 156 0 156 0 0 172 0 0 0 \n"
			+ "270 271 301 170 169 156 158 6 154 8 9 10 287 79 108 108 108 108 108 108 108 108 108 109 109 109 109 109 210 211 212 109 109 109 109 109 109 109 109 109 \n"
			+ "284 285 168 0 17 18 19 20 21 22 23 24 272 79 108 108 108 108 108 108 108 108 108 108 108 108 108 108 224 225 226 108 108 108 108 108 108 108 108 108 \n"
			+ "298 299 43 44 31 32 33 34 35 36 37 38 286 79 108 108 108 108 108 108 108 108 108 108 108 108 108 108 238 239 240 108 108 108 199 200 201 202 108 108 \n"
			+ "168 56 57 58 45 46 47 48 49 50 51 38 300 79 108 108 108 108 108 0 141 0 144 108 108 108 108 108 252 253 254 108 108 108 213 214 215 216 108 108 \n"
			+ "172 70 71 72 59 60 61 62 63 64 65 52 53 79 108 108 108 108 157 154 0 140 0 0 0 143 108 108 266 267 268 108 108 108 108 108 108 108 108 108 \n"
			+ "169 70 85 86 73 74 75 76 77 132 79 66 67 79 108 108 108 108 142 170 143 142 0 0 156 0 0 170 171 0 144 171 0 0 0 144 108 108 108 108 \n"
			+ "170 70 99 100 79 104 131 79 91 92 79 80 81 79 108 108 108 108 0 142 0 143 158 0 142 0 170 158 273 274 170 0 170 0 141 0 0 0 0 0 \n"
			+ "241 84 113 79 91 92 132 79 79 79 79 94 95 137 108 108 108 108 109 1 141 156 0 0 141 142 0 231 0 170 143 0 0 0 0 143 0 0 0 0 \n"
			+ "255 98 79 79 79 79 90 79 79 79 91 92 137 136 108 108 108 108 108 158 154 0 172 155 170 0 0 172 258 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "269 112 79 79 79 79 79 79 79 79 79 79 135 109 108 108 108 108 108 1 171 168 170 0 0 0 143 259 260 0 0 0 0 0 0 0 0 143 0 0 \n"
			+ "283 126 107 79 79 79 79 79 79 79 79 79 186 145 146 147 148 149 150 154 0 171 140 155 0 172 169 273 274 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "79 91 92 132 79 79 79 79 79 79 79 79 151 159 160 161 162 163 164 1 172 237 170 0 172 0 157 230 258 0 0 0 0 0 0 0 0 0 0 0 \n"
			+ "79 79 79 79 79 79 79 79 79 132 79 115 133 173 174 175 176 177 178 0 141 251 233 234 235 236 233 242 243 172 0 0 0 0 0 0 0 141 0 0 \n"
			+ "79 79 79 79 79 79 79 90 105 79 79 137 136 187 188 189 190 191 192 142 0 0 247 248 249 157 0 171 168 157 0 0 0 0 0 0 0 154 0 141 \n"
			+ "79 79 79 107 79 79 79 104 79 137 134 136 109 108 108 108 108 108 108 109 0 172 261 262 0 0 168 272 171 158 0 0 0 0 0 0 0 144 155 0 \n"
			+ "79 79 132 90 123 137 134 134 134 136 109 109 108 108 199 200 201 202 108 108 0 0 275 276 277 278 170 286 168 0 0 0 0 0 0 0 170 155 155 0 \n"
			+ "134 134 134 134 134 136 109 109 109 109 108 108 108 108 213 214 215 216 108 108 141 172 156 290 291 292 298 169 0 0 0 0 0 0 0 0 0 154 0 141 \n"
			+ "109 109 109 109 109 109 108 108 108 108 108 108 108 108 108 108 108 108 108 108 0 169 0 156 305 306 307 142 0 0 155 0 242 243 0 155 141 0 141 0 \n"
			+ "108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 142 157 142 0 0 172 142 156 287 157 0 172 170 0 288 0 264 237 287 141 0 \n"
			+ "108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 0 144 144 0 172 170 143 0 0 0 0 0 0 228 0 289 244 172 263 272 251 170 142 0 \n"
			+ "108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 108 0 0 0 140 143 169 0 0 0 0 0 234 235 236 232 0 158 301 170 286 301 169 0 170 \n"
			+ "108 108 108 108 108 108 108 108 108 108 108 108 108 0 0 0 0 0 0 0 0 0 0 0 0 0 247 248 249 250 0 158 0 0 171 168 170 300 0 0 \n"
			+ "108 108 108 108 108 108 108 108 108 108 108 0 0 0 0 0 0 0 0 0 0 0 0 0 0 155 261 262 272 284 0 287 288 289 0 0 140 141 0 0 \n"
			+ "108 108 108 108 108 108 108 108 108 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 284 286 170 0 301 302 303 298 0 169 270 271 284 \n"
			+ "108 108 108 108 108 108 108 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 142 258 168 141 0 169 298 273 274 301 170 170 285 0 \n"
			+ "108 108 108 108 108 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 245 246 0 0 0 233 0 140 157 157 300 157 299 0 \n"
			+ "108 108 108 108 0 0 0 0 0 0 0 0 171 0 170 0 0 171 144 0 0 0 0 0 0 288 230 142 170 0 155 272 155 0 0 0 172 0 301 302 \n"
			+ "108 108 108 0 0 0 0 0 0 140 0 171 170 169 141 170 0 144 0 171 141 256 257 170 259 260 155 0 0 0 287 286 169 170 287 0 0 157 0 0 \n"
			+ "108 108 108 0 0 0 0 0 0 0 0 0 0 0 0 0 144 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
	
	public static final String HOUSE = "house.png";
	public static final String HOUSE_TILE_DATA = "0 1 1 1 1 1 1 1 1 2 \n"
			+ "7 4 19 12 27 13 17 18 17 9 \n"
			+ "7 11 26 6 28 20 24 25 24 9 \n"
			+ "7 5 3 3 29 3 3 3 3 9 \n"
			+ "7 10 10 10 10 10 10 10 10 9 \n"
			+ "7 10 10 10 10 10 10 10 10 9 \n"
			+ "7 10 10 10 10 10 10 10 10 9 \n"
			+ "7 10 10 10 10 10 10 10 10 9 \n"
			+ "7 10 10 10 10 10 10 10 10 9 \n"
			+ "14 15 15 21 22 23 15 15 15 16 ";
	public static final String HOUSE_TILE_ATLAS = "1stfloorTileset.png";
	public static final String HOUSE_2_TILE_DATA = "7 8 8 8 8 8 8 8 8 9 \n"
			+ "17 0 1 2 3 3 4 5 6 19 \n"
			+ "17 10 11 12 13 13 14 15 16 19 \n"
			+ "17 20 21 22 23 24 25 25 26 19 \n"
			+ "17 30 31 32 33 34 35 35 36 19 \n"
			+ "17 34 34 34 34 34 34 35 18 19 \n"
			+ "17 34 35 34 34 34 34 34 35 19 \n"
			+ "17 34 34 34 35 35 35 34 35 19 \n"
			+ "17 34 34 34 34 34 35 34 35 19 \n"
			+ "27 28 28 37 38 39 28 28 28 29";
	public static final String HOUSE_2_TILE_ATLAS = "2ndHouseRoomTileset.png";
	
	public static final String CAVE_TILE_DATA = "11 11 11 11 11 11 11 11 11 11 11 12 12 12 12 12 12 13 11 11 11 11 11 11 11 11 11 11 11 11 11 11 12 13 14 12 13 12 13 14 \n"
			+ "11 11 11 11 11 11 11 11 11 11 14 18 18 18 18 18 18 19 14 11 11 11 11 11 11 11 11 11 11 11 11 14 18 19 20 18 19 18 19 20 \n"
			+ "11 11 11 11 11 11 11 11 11 14 20 24 24 24 24 24 24 25 20 11 11 11 11 11 11 11 11 11 11 14 14 20 24 25 26 24 25 24 25 26 \n"
			+ "11 11 11 11 11 11 11 11 11 20 26 3 2 2 2 2 2 1 26 11 11 11 11 11 11 11 11 11 11 20 20 26 3 2 2 2 2 2 2 15 \n"
			+ "11 11 11 11 11 11 11 11 11 26 0 9 5 5 5 5 5 10 2 11 11 11 11 11 11 11 11 11 11 26 26 0 9 5 5 5 5 5 5 5 \n"
			+ "11 11 11 11 11 11 11 11 11 2 6 5 5 5 5 5 5 5 5 11 11 11 11 11 11 11 11 11 11 2 2 6 5 5 5 5 5 5 5 5 \n"
			+ "11 11 11 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 11 11 11 11 11 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 5 5 \n"
			+ "11 11 11 11 11 11 11 11 11 11 11 11 5 5 5 5 5 11 11 11 11 11 11 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 5 5 \n"
			+ "14 14 11 11 11 11 11 11 11 11 11 11 5 5 5 5 11 11 11 11 11 11 11 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 5 5 \n"
			+ "20 20 14 11 11 11 11 11 11 11 11 14 5 5 5 5 14 11 11 11 11 11 11 11 11 11 11 11 11 5 5 5 5 5 5 11 11 5 5 5 \n"
			+ "26 26 20 14 11 11 11 11 11 14 14 20 5 5 5 5 20 12 13 14 11 11 11 11 11 14 11 11 11 11 5 5 5 5 11 11 11 11 5 5 \n"
			+ "2 4 26 20 14 14 14 14 20 20 20 26 5 5 5 5 26 18 19 20 11 11 11 11 12 20 12 11 11 11 11 11 11 11 11 11 11 14 5 5 \n"
			+ "5 7 1 26 20 20 20 20 26 26 26 3 5 5 5 5 4 24 25 26 11 11 11 11 18 26 18 11 11 11 11 11 11 11 11 11 11 20 5 5 \n"
			+ "5 5 7 1 26 26 26 26 26 0 2 9 5 5 5 5 10 2 2 2 11 11 11 11 24 2 24 11 11 11 11 11 11 11 11 11 11 26 5 5 \n"
			+ "5 5 5 7 2 2 2 2 2 6 5 5 5 5 5 5 5 5 5 5 11 11 11 11 3 5 4 11 11 11 11 11 11 11 11 11 14 3 5 5 \n"
			+ "5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 11 11 11 11 5 5 5 11 11 11 11 11 11 11 11 11 20 5 5 5 \n"
			+ "5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 11 11 11 11 5 5 5 11 11 11 11 11 11 11 11 11 26 5 5 5 \n"
			+ "11 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 11 11 11 11 11 5 5 5 11 11 11 11 11 11 11 11 14 3 5 5 5 \n"
			+ "11 11 5 5 5 5 5 5 5 5 5 5 5 11 11 11 5 5 5 11 11 11 11 11 5 5 5 11 11 11 11 11 11 11 11 20 9 5 5 5 \n"
			+ "11 11 5 5 5 5 5 5 11 11 11 11 11 11 11 11 5 5 5 14 14 11 11 14 5 5 5 11 11 11 11 11 11 11 11 26 5 5 5 5 \n"
			+ "11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 5 5 5 20 20 14 14 20 5 5 5 14 11 11 11 11 11 11 14 3 5 5 5 11 \n"
			+ "11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 14 5 5 5 26 26 20 20 26 5 5 5 20 14 11 11 14 14 14 20 9 5 5 5 11 \n"
			+ "11 11 11 11 11 11 11 11 11 11 11 11 11 11 14 20 5 5 5 2 1 26 26 3 5 5 5 26 20 14 14 20 20 20 26 5 5 5 5 11 \n"
			+ "11 11 11 11 11 11 11 12 12 12 12 12 12 12 20 26 5 5 5 5 7 2 2 9 5 5 5 4 26 20 20 26 26 26 3 5 5 5 5 11 \n"
			+ "11 11 11 11 11 11 11 18 18 18 18 18 18 18 26 3 5 5 5 5 5 5 5 5 5 5 5 7 1 26 26 0 2 2 9 5 5 5 11 11 \n"
			+ "11 11 11 11 11 11 11 24 24 24 24 24 24 24 3 5 5 5 5 5 5 5 5 5 5 5 5 5 10 2 2 9 5 5 5 5 5 5 11 11 \n"
			+ "11 11 11 11 11 11 11 2 2 2 2 2 2 2 9 5 5 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 11 11 \n"
			+ "11 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 11 11 11 11 11 11 11 11 11 5 5 5 5 5 5 5 5 5 5 5 11 11 11 11 \n"
			+ "11 11 11 11 11 11 11 5 5 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 \n"
			+ "11 11 11 11 11 11 11 5 5 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 ";
	public static final String CAVE_TILE_ATLAS = "caveRoomtileset.png";
	
	public static final String BOSS_ROOM = "Cemetarydraw.png";
	public static final String BOSS_ROOM_TILE_DATA = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 \n"
			+ "21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 \n"
			+ "42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 \n"
			+ "63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 \n"
			+ "84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 101 102 103 104 \n"
			+ "105 106 107 108 109 110 111 112 113 114 115 116 117 118 119 120 121 122 123 124 125 \n"
			+ "126 127 128 129 130 131 132 133 134 135 136 137 138 139 140 141 142 143 144 145 146 \n"
			+ "147 148 149 150 151 152 153 154 155 156 157 158 159 160 161 162 163 164 165 166 167 \n"
			+ "168 169 170 171 172 173 174 175 176 177 178 179 180 181 182 183 184 185 186 187 188 \n"
			+ "189 190 191 192 193 194 195 196 197 198 199 200 201 202 203 204 205 206 207 208 209 \n"
			+ "210 211 212 213 214 215 216 217 218 219 220 221 222 223 224 225 226 227 228 229 230 \n"
			+ "231 232 233 234 235 236 237 238 239 240 241 242 243 244 245 246 247 248 249 250 251 \n"
			+ "252 253 254 255 256 257 258 259 260 261 262 263 264 265 266 267 268 269 270 271 272 \n"
			+ "273 274 275 276 277 278 279 280 281 282 283 284 285 286 287 288 289 290 291 292 293 ";
	public static final String BOSS_ROOM_TILE_ATLAS = "bossRoomTileset.png";
	
	
	//BACKGROUND & UI
	public static final String GAME_NAME = "background/game_name.png";
	public static final String PAUSE_BACKGROUND = "background/pause_menu.png";
	public static final String MENU_BACKGROUND_IMG = "background/animation_background2.png";
	public static final String FRIEND_BACKGROUND = "background/friend_background.png";
	public static final String GAME_BACKGROUND = "background/masterpiece_background.png";
	public static final String HIMMEL_STATUS_BAR = "background/himmel_health.png";
	public static final String FRIEREN_STATUS_BAR = "background/frieren_health.png";
	public static final String DEATH_SCREEN = "background/death_menu.png";
	public static final String OPTIONS_MENU = "background/options_background.png";
	public static final String SELECT_BACKGROUND = "background/select_background.png";

	
	//BUTTON
	public static final String MENU_BUTTONS = "buttons/button_atlas.png";
	public static final String SOUND_BUTTONS = "buttons/sound_button.png";
	public static final String URM_BUTTONS = "buttons/urm_buttons.png";
	public static final String VOLUME_BUTTONS = "buttons/volume_button.png";
	public static final String SELECT_BUTTONS = "buttons/select_button.png";
	
	//OBJECT
	public static final String POTION_ATLAS = "objects/potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects/objects_sprites.png";
	public static final String KEYHOUSE_ATLAS = "objects/key_sprites.png";
	public static final String TREE_1_ATLAS = "objects/tree.png";

 	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		
		try {
			 img = ImageIO.read(is);			
				
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
//	public static ArrayList<Slime> GetSlimes() {
//		ArrayList<Slime> list = new ArrayList<>();
//		try {
//			
//			InputStream is = LoadSave.class.getResourceAsStream(TILE_DATA);
//            File file = new File(ENEMY_DATA);
//            
//            Scanner scanner = new Scanner(file);
//            int col = 0, row = 0;
//            // Loop through each line in the file
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//
//                String[] numbers = line.split(" ");
//
//                for (String number : numbers) {
//                	int index = Integer.parseInt(number);
//                    if (index == SLIME) 
//                    	list.add(new Slime(col * Game.TILES_SIZE, row * Game.TILES_SIZE));
//                    col++;
//                }
//                col = 0;
//                row++;
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//		
//		return list;
	
//	public static ArrayList<Mimic> GetMimics() {
//		ArrayList<Mimic> list = new ArrayList<>();
//		try {
//			
//			InputStream is = LoadSave.class.getResourceAsStream(TILE_DATA);
//            File file = new File(ENEMY_DATA);
//            
//            Scanner scanner = new Scanner(file);
//            int col = 0, row = 0;
//            // Loop through each line in the file
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//
//                String[] numbers = line.split(" ");
//
//                for (String number : numbers) {
//                	int index = Integer.parseInt(number);
//                    if (index == MIMIC) 
//                    	list.add(new Mimic(col * Game.TILES_SIZE, row * Game.TILES_SIZE));
//                    col++;
//                }
//                col = 0;
//                row++;
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//		
//		return list;
//	}
	
	public static int[][] GetTileData(String fileName, int width, int height) {
		String[] rows = fileName.split("\n");
        int numRows = rows.length;
        int numCols = rows[0].split(" ").length;
        int[][] tileData = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] rowValues = rows[i].trim().split(" ");
            for (int j = 0; j < numCols; j++) {
                tileData[i][j] = Integer.parseInt(rowValues[j]);
            }
        }

        return tileData;
	}
	
}
