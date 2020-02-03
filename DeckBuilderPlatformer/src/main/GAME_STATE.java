package main;

/*
 * Contains the states for the game
 * -GAME indicates that the playable game is occuring
 * -MAIN_MENU indicates that the user is at the main menu, which
 * 	will allow the user to start a new game, go to the store, or load a save file
 * 
 * 
 */

public enum GAME_STATE {
	GAME,
	STORE,
	MAIN_MENU,
	HELP_MENU,
	CREDITS,
	LOAD;
}
