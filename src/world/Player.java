package world;

import org.lwjgl.opengl.Display;

import game.HUDMaterial.Inventory;
import world.objects.food.Hamburger;

public class Player extends Hamburger{

	private int experience = 0;
	private int level = 1;
	private int levelPoint = 1;
	

	
	private Inventory inventory = new Inventory(Display.getWidth()/2-80,Display.getHeight()/2+80, 6,3);
	
	public Player(float xPos, float yPos) {
		super(xPos, yPos);
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	
	//////////////EXP OCH LEVEL////////////////
	public void grantExperience(int amt){
		experience += amt;
		if(experience >= getExperienceToNextLevel()){
			experience -= getExperienceToNextLevel();
			levelUp();
		}
	}
	
	public void levelUp(){
		level += 1;
		levelPoint += 1;
	}
	
	public int getExperienceToNextLevel(){
		return level*100;
	}
/////////////////////////////////////////////////
	
	
	
}
