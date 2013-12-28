package game.parts;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import world.ArmorBar;
import world.HealthBar;
import world.objects.food.Hamburger;


import game.GImage;
import game.GSprite;
import game.GText;
import game.GamePart;

public class Actionbar implements GamePart{
	GSprite background = new GSprite(Display.getWidth()/2, Display.getHeight()-40 , Display.getWidth()-400, 80, "UI/actionbar");
	
	HealthBar healthBar = new HealthBar(15+150, 15, 300, 20, GameWorld.getPlayer());
	GSprite healthBarBackground = new GSprite(healthBar.getX(), healthBar.getY() , healthBar.getTexWidth() + 6, healthBar.getTexHeight() + 6, 0.8f,0.8f,0.8f,1f);
	TrueTypeFont healthInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false);
	
	ArmorBar armorBar = new ArmorBar(15+150, 50, 300, 20,(Hamburger) GameWorld.getPlayer());
	GSprite armorBarBackground = new GSprite(armorBar.getX(), armorBar.getY() , armorBar.getTexWidth() + 6, armorBar.getTexHeight() + 6, 0.8f,0.8f,0.8f,1f);
	TrueTypeFont armorInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false);
	
	public Actionbar() {
		healthBar.setColor(0.2f, 0.8f, 0.2f, 1f);
		healthBar.setBackgroundColor(0.8f, 0.2f, 0.2f, 1f);
		
		armorBar.setColor(0.4f, 0.4f, 0.4f, 1f);
		armorBar.setBackgroundColor(0.2f, 0.2f, 0.2f, 1f);
	}
	
	public void handleInput() {
		 
	}

	public void update() {

	}

	public void render() {
		//background.render();
		
		{ //HEALTHBAR//
			//GText.font.drawString(healthBar.getX()-155, healthBar.getY()-37, "Health", Color.yellow);
			healthBarBackground.render();
			healthBar.render();
			healthInfo.drawString(healthBar.getX()-145, healthBar.getY()-14, (int)healthBar.getCurrent() + "/" + (int)healthBar.getMaxHealth() + "   HP", Color.black);
			
			armorBarBackground.render();
			armorBar.render();
			armorInfo.drawString(armorBar.getX()-145, armorBar.getY()-14, (int)armorBar.getCurrent() + "/" + (int)armorBar.getMax() + "   HP", Color.black);
		}
	}

}
