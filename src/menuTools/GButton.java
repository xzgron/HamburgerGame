package menuTools;

import game.GObject;

public abstract class GButton extends GObject {

	public GButton(float xPos, float yPos, float textureWidth, float textureHeight) {
		super(xPos, yPos, textureWidth, textureHeight);
	}

	public void update() {
		if(isClicked())
			click();
	}
	
	public void setTexture(String fileName){
		super.setTexture("buttons/"+fileName);
	}
	public abstract void click();
}
