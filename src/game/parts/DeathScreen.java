package game.parts;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import game.GButton;
import game.GamePart;
import game.Main;
import game.Game.GState;
import game.tools.GMouse;

public class DeathScreen extends GamePart{
	private TrueTypeFont deathFont = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 40), false);
	
	private GButton retryButton = new GButton(Display.getWidth()/2-100, Display.getHeight()/2, 200, 100, null);
	private TrueTypeFont retryFont = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 40), false);
	
	@Override
	public void handleInput() {
		if(retryButton.isReleasedOver(GMouse.BUTTON_LEFT)){
			Main.game.setGameState(GState.START_MENU);
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		retryFont.drawString(retryButton.getX() - 10, retryButton.getY() - 21, "RETRY", Color.white);
		
	}

}
