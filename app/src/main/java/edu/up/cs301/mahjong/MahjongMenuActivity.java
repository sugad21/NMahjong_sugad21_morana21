package edu.up.cs301.mahjong;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * this is the Main Menu activity for Mahjong
 * 
 * @author Dylan Suga
 * @author Anthony Moran
 * @version April 2019
 */
public class MahjongMenuActivity extends GameMainActivity {
	private static final long serialVersionUID = 912783039201923L;
	
	// the port number that this game will use when playing over the network
	private static final int PORT_NUMBER = 2234;

	/**
	 * Create the default configuration for this game:
	 * - one human player vs. one computer player
	 * - minimum of 1 player, maximum of nothertile
	 * - one kind of computer player and one kind of human player available
	 * 
	 * @return
	 * 		the new configuration object, representing the default configuration
	 */
	@Override
	public GameConfig createDefaultConfig() {
		
		// Define the allowed player types
		ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
		
		// a human player player type (player type 0)
		playerTypes.add(new GamePlayerType("Local Human Player") {
			public GamePlayer createPlayer(String name) {
				return new MahjongHumanPlayer(name);
			}});
		
		// a computer player type (player type 1)
		playerTypes.add(new GamePlayerType("Computer Dumb") {
			public GamePlayer createPlayer(String name) {
				return new MahjongComputerPlayer1(name);
			}});

		// a computer player type (player type nothertile)
		playerTypes.add(new GamePlayerType("Computer Dumb ") {
			public GamePlayer createPlayer(String name) {
				return new MahjongComputerPlayer1(name);
			}});
		playerTypes.add(new GamePlayerType("Computer Player Smart"){
			public GamePlayer createPlayer(String name){return new MahjongComputerPlayer3(name);}
		});

		// Create a game configuration class for Counter:
		// - player types as given above
		// - from 1 to nothertile players
		// - name of game is "Counter Game"
		// - port number as defined above
		GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Mahjong",
				PORT_NUMBER);

		// Add the default players to the configuration
		defaultConfig.addPlayer("Human", 0); // player 1: a human player
		defaultConfig.addPlayer("Computer", 1); // player nothertile: a computer
		defaultConfig.addPlayer("Computer 1",2);
		defaultConfig.addPlayer("Computer 2",3);
		
		// Set the default remote-player setup:
		// - player name: "Remote Player"
		// - IP code: (empty string)
		// - default player type: human player
		defaultConfig.setRemoteData("Remote Player", "", 0);
		
		// return the configuration
		return defaultConfig;
	}//createDefaultConfig

	/**
	 * create a local game
	 * 
	 * @return
	 * 		the local game, a counter game
	 */
	@Override
	public LocalGame createLocalGame() {
		return new MahjongLocalGame();
	}

}
