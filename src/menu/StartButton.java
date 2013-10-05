package menu;

import menuTools.GButton;
import game.GStates;
import game.Main;

public class StartButton extends GButton{

	public StartButton(float xPos, float yPos, float textureWidth, float textureHeight) {
		super(xPos, yPos, textureWidth, textureHeight);
		setTexture("startButton");
	}

	@Override
	public void click() {
		Main.game.setGameState(GStates.GAME);	
	}

}
