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
	
	HealthBar healthBar = new HealthBar(170, Display.getHeight()-75, 300, 20, 0.2f, 0.8f, 0.2f, 1f, GameWorld.getPlayer());
	GSprite healthBarBackground = new GSprite(healthBar.getX(), healthBar.getY() , healthBar.getTexWidth() + 6, healthBar.getTexHeight() + 6, 0.8f,0.8f,0.8f,1f);
	public void handleInput() {
		 
	}

	public void update() {

	}

	public void render() {
		background.render();
		
		{ //HEALTHBAR//
			//GText.font.drawString(healthBar.getX()-155, healthBar.getY()-37, "Health", Color.yellow);
			healthBarBackground.render();
			healthBar.render();
			GText.font.drawString(healthBar.getX()-145, healthBar.getY()-14, healthBar.getMaxHealth() + "/" + healthBar.getHealth() + "   HP", Color.black);
		}
	}

}
