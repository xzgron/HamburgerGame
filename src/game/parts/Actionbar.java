package game.parts;

import org.lwjgl.opengl.Display;

import game.GSprite;
import game.GamePart;

public class Actionbar implements GamePart{

	GSprite background = new GSprite(Display.getWidth()/2, 50 , Display.getWidth(), 100, 0.48f,0.69f,0.71f,0.9f);
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		background.render();
		
	}

}
