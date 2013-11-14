package game.parts;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import game.GImage;
import game.GSprite;
import game.GText;
import game.GamePart;

public class Actionbar implements GamePart{

	GSprite background = new GSprite(Display.getWidth()/2, Display.getHeight()-50 , Display.getWidth(), 100, 0.48f,0.69f,0.71f,0.9f);
	public void handleInput() {
		
	}

	public void update() {

	}

	public void render() {
		background.render();
		GText.font.drawString(200, Display.getHeight()-50, "THE MOST EPIC GAME EVAH!!!!11", Color.red);
		//GImage.font2.drawString(100, 100, "NICE LOOKING FONTS!", Color.green);

		
	}

}
