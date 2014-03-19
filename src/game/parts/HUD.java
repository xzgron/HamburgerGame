package game.parts;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import world.LevelHandler;
import world.WaveHandler;
import world.objects.food.Hamburger;


import game.GImage;
import game.GSprite;
import game.GText;
import game.GamePart;
import game.Main;
import game.HUDMaterial.ArmorBar;
import game.HUDMaterial.HealthBar;
import game.HUDMaterial.PlayerActionbar;

// det här är allt som synns över världen när man spelar likt liv osv. 

public class HUD extends GamePart{
	
	///////////HEALTHBAR////////
	HealthBar healthBar;
	GSprite healthBarBackground;
	static TrueTypeFont healthInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false);
	//////////////////
	
	////////ARMORBAR///////
	ArmorBar armorBar;
	GSprite armorBarBackground;
	static TrueTypeFont armorInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false);	
	///////////////////////
	
	static TrueTypeFont levelInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 40), false);	
	WaveHandler level;
	GameWorld world;
	///////////ACTIONBAR///////////
	PlayerActionbar actionbar;
	
	public HUD(GameWorld world) {
		armorBar = new ArmorBar(15+150, 50, 300, 20,(Hamburger) world.getPlayer());
		healthBar = new HealthBar(15+150, 15, 300, 20, world.getPlayer());
		healthBarBackground = new GSprite(healthBar.getX(), healthBar.getY() , healthBar.getTexWidth() + 6, healthBar.getTexHeight() + 6, 0.8f,0.8f,0.8f,1f);
		actionbar = new PlayerActionbar(world.getPlayer(), world);
		armorBarBackground = new GSprite(armorBar.getX(), armorBar.getY() , armorBar.getTexWidth() + 6, armorBar.getTexHeight() + 6, 0.8f,0.8f,0.8f,1f);

		healthBar.setColor(0.2f, 0.8f, 0.2f, 1f);
		healthBar.setBackgroundColor(0.8f, 0.2f, 0.2f, 1f);
		healthBar.setDamageColor(0.8f, 0.8f, 0.8f, 1f);
		
		armorBar.setColor(0.4f, 0.4f, 0.4f, 1f);
		armorBar.setBackgroundColor(0.2f, 0.2f, 0.2f, 1f);
		armorBar.setDamageColor(0.8f, 0.8f, 0.8f, 1f);
		
		level = world.waveHandler;
		this.world = world;
	}
	
	public void handleInput() {
		actionbar.handleInput();
		 
	}

	public void update() {
		armorBar.update();
		healthBar.update();
		actionbar.update();
	}

	public void render() {
		actionbar.render();
		
		
		{ //HEALTHBAR//
			//GText.font.drawString(healthBar.getX()-155, healthBar.getY()-37, "Health", Color.yellow);
			healthBarBackground.render();
			healthBar.render();
			healthInfo.drawString(healthBar.getX()-145, healthBar.getY()-14, (int)healthBar.getCurrent() + "/" + (int)healthBar.getMaxHealth() + "   HP", Color.black);
			
			armorBarBackground.render();
			armorBar.render();
			armorInfo.drawString(armorBar.getX()-145, armorBar.getY()-14, (int)armorBar.getCurrent() + "/" + (int)armorBar.getMax() + "   ARMOR", Color.black);
		}
		
		levelInfo.drawString(Display.getWidth()/2-10,20, "Wave:"+ level.getLevel() + "  " + (int)level.waveTime.getTimeLeft() + "  " + world.getAliveHostileFood().size(), Color.white);
	}

}
