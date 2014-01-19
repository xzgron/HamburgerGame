package world.objects;
import world.WorldObject;

public class GSpawner<spawnType> extends WorldObject{

	public GSpawner(float xPos, float yPos, float texWidth, float texHeight,
			String texture, float footPos, float headPos) {
		super(xPos, yPos, texWidth, texHeight, texture, footPos, headPos);
		setFootZPos(0);
	}
	



	public void update(){
		
	}
}
