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

// det hŠr Šr allt som synns šver vŠrlden nŠr man spelar likt liv osv. 

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
	
	static TrueTypeFont waveInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 30), false);
	static TrueTypeFont waveTime = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 30), false);	
	static TrueTypeFont enemyCounter = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 30), false);	

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
		int resAdapt = 0;
		if(Display.getWidth()>850)
		resAdapt=Display.getWidth()/6;
		
		if(Display.isFullscreen())
			resAdapt=Display.getWidth()/5-20;
		
		
		{ //HEALTHBAR//
			//GText.font.drawString(healthBar.getX()-155, healthBar.getY()-37, "Health", Color.yellow);
			healthBarBackground.render();
			healthBar.render();
			healthInfo.drawString(healthBar.getX()-145, healthBar.getY()-14, (int)healthBar.getCurrent() + "/" + (int)healthBar.getMaxHealth() + "   HP", Color.black);
			
			armorBarBackground.render();
			armorBar.render();
			armorInfo.drawString(armorBar.getX()-145, armorBar.getY()-14, (int)armorBar.getCurrent() + "/" + (int)armorBar.getMax() + "   ARMOR", Color.black);
		}
		waveTime.drawString(Display.getWidth()/2+67+resAdapt,0,"Time until next wave: " + (int)level.waveTime.getTimeLeft(), Color.white);
		enemyCounter.drawString(Display.getWidth()/2+90+resAdapt,30,"Enemies remaining: " + world.getAliveHostileFood().size(), Color.white);
		waveInfo.drawString(Display.getWidth()/2+264+resAdapt,60, "Wave:"+ level.getLevel());


	}

}
