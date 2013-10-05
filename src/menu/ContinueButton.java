package menu;

import menuTools.GButton;
import game.GStates;
import game.Main;

public class ContinueButton extends GButton{

	public ContinueButton(float xPos, float yPos, float textureWidth, float textureHeight) {
		super(xPos, yPos, textureWidth, textureHeight);
		setTexture("continueButton");
	}

	@Override
	public void click() {
		Main.game.setGameState(GStates.GAME);	
	}

}
