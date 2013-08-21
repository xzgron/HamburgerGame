package gameObjects;

import game.GObject;
import game.GWorld;

public class Rektangel extends GObject {

	public Rektangel(float textureSize) {
		super(GWorld.player.getX(), GWorld.player.getY(), textureSize);
		setColor(255,200,255);
		setTransparency(0.5f);
		
	}

	@Override
	public void update() {
		setPosition(GWorld.player.getX(),GWorld.player.getY());
		
	}

	
}
