package game.parts;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import world.HealthBar;


import game.GImage;
import game.GSprite;
import game.GText;
import game.GamePart;

public class Actionbar implements GamePart{
	
		
	GSprite background = new GSprite(Display.getWidth()/2, Display.getHeight()-40 , Display.getWidth()-400, 80, "UI/actionbar");
	public void handleInput() {
		 
	}

	public void update() {

	}

	public void render() {
		background.render();
		
		{ //HEALTHBAR///
			//GText.font.drawString(healthBar.getX()-155, healthBar.getY()-37, "Health", Color.yellow);
		
		}
	}

}
