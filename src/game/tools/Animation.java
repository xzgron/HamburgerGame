package game.tools;

import java.util.LinkedList;

import org.newdawn.slick.opengl.Texture;

public class Animation {
	private LinkedList<Texture> images = new LinkedList<Texture>();
	private boolean repeating;
	private GTimer animationTimer;
			
	public Animation(float length, boolean repeating){
		this.repeating = repeating;
		animationTimer = new GTimer(length);
	}
	
	public void setLength(float length){
		animationTimer.setLength(length);
	}
	
	public void restart(){
		animationTimer.reset();
	}
	
	//public void startFrom(int frame){}
	
	public void setRepeating(boolean repeating){
		this.repeating = repeating;
	}

	public Texture getCurrentTexture(){ // returns null if done or has no textures
		float fps = images.size() / animationTimer.getLength();
		if(repeating)
			return images.get((int)(animationTimer.getPastTime()*fps)%images.size());
		else {
			if((int)(animationTimer.getPastTime()*fps) < images.size())
				return images.get((int)(animationTimer.getPastTime()*fps));
			else
				return null;
		}
	}
	
	public void addImage(Texture texture){
		images.add(texture);
	}
}
