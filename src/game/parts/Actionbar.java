package game.parts;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import world.HealthBar;


import game.GImage;
import game.GSprite;
import game.GText;
import game.GamePart;

public class Actionbar implements GamePart{
	
	HealthBar healthBar = new HealthBar(170, Display.getHeight()-75, 300, 20, 1f, 0, 0, 1f, GameWorld.getPlayer());
	GSprite healthBarBackground = new GSprite(healthBar.getX(), healthBar.getY() , healthBar.getTexWidth() + 6, healthBar.getTexHeight() + 6, 0.8f,0.8f,0.8f,1f);
		
	GSprite background = new GSprite(Display.getWidth()/2, Display.getHeight()-50 , Display.getWidth(), 100, 0.48f,0.69f,0.71f,0.9f);
	public void handleInput() {
		
	}

	public void update() {

	}

	public void render() {
		background.render();
		
		{ //HEALTHBAR///
			//GText.font.drawString(healthBar.getX()-155, healthBar.getY()-37, "Health", Color.yellow);
			healthBarBackground.render();
			healthBar.render();
			GText.font.drawString(healthBar.getX()-145, healthBar.getY()-14, healthBar.getMaxHealth() + "/" + healthBar.getHealth() + "   HP", Color.yellow);
		}
	}

}
