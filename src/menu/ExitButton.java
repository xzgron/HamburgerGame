package menu;

import org.lwjgl.opengl.Display;

import game.GButton;
import game.Game;

public class ExitButton extends GButton{

	public ExitButton(float xPos, float yPos, float textureWidth,
			float textureHeight) {
		super(xPos, yPos, textureWidth, textureHeight);
		
		setTexture("exitButton");
	}

	@Override
	public void click() {
		Game.close();
	}

}
