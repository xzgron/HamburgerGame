package game;
import static org.lwjgl.input.Mouse.*;

import org.lwjgl.input.Mouse;

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
	
	private boolean isClicked(){
		if(isButtonDown(0) && Mouse.getX() >= this.getX() - this.getTextureWidth()/2 &&  Mouse.getX() <= this.getX() + this.getTextureWidth()/2 && 
				Mouse.getY() >= this.getY() - this.getTextureHeight()/2 &&  Mouse.getY() <= this.getY() + this.getTextureHeight()/2)
			return true;
		else
			return false;
	}

}
