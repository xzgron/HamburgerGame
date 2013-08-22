package gameObjects;

public class Human extends GCharacter{

	public Human(float xPos, float yPos) {
		super(xPos, yPos, "human", 150, 100);
		setSpeed(150);
	}
}
