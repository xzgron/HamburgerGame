package game.parts;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import world.HealthBar;


import game.GImage;
import game.GSprite;
import game.GText;
import game.GamePart;

public class Actionbar implements GamePart{
	GSprite background = new GSprite(Display.getWidth()/2, Display.getHeight()-50 , Display.getWidth(), 100, 0.48f,0.69f,0.71f,0.9f);
	
		
<<<<<<< HEAD
	
=======
	GSprite background = new GSprite(Display.getWidth()/2, Display.getHeight()-40 , Display.getWidth()-400, 80, "UI/actionbar");
>>>>>>> 296821e238698245f3741a5f4a500ffdb55360ad
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
