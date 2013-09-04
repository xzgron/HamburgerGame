package menu;

import game.GButton;
import game.GObject;
import game.GStates;
import game.Main;

public class MenuArea extends GObject{

	public MenuArea(float xPos, float yPos, float textureWidth, float textureHeight) {
		super(xPos, yPos, textureWidth, textureHeight);
		setTexture("buttons/area");
	}

	@Override
	public void update() {
		
	}


}
