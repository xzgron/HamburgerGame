package game.parts;

import game.GPhysics;
import game.GamePart;

import java.util.ArrayList;
import java.util.LinkedList;

import controllers.DefaultController;
import controllers.GController;
import world.WorldObject;
import world.objects.*;
import world.objects.food.*;
import static org.lwjgl.input.Keyboard.*;
import static game.Game.*;
public class GameWorld implements GamePart {

	public static ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();

	private static float gravity = 800;

	private static GFood player = new Hamburger(400, 400, 100);;

	public GameWorld() {
		player.setController(new DefaultController());
		addGO(player);
		addGO(new Tree(150, 30, 300));
		addGO(new Tree(350, 80, 400));
		addGO(new Tree(50, 170, 500));
		
		for (int i = 0; i < 400; i++)
			addGO(new BlueBerry((float) Math.random() * 1000 - 150,
					(float) Math.random() * 1000 - 150));
	}
	
	///////////MAIN PART////////////////
	public void handleInput() {
		if(isKeyDown(KEY_ESCAPE))
			setGameState(GStates.GAME_MENU);
		
	
		if(isKeyDown(KEY_I) && !Inventory.wasIDown){
			setGameState(GStates.INVENTORY_MENU);
			Inventory.wasIDown = true;
			}
		else if(!isKeyDown(KEY_I))
			Inventory.wasIDown = false;
	
	}
	
	public void update() {
		for	(WorldObject go : worldObjects){ //rensa dštt
			if(go.getClass().isAssignableFrom(GFood.class) && ((GFood) go).isDead())
				removeGO(go);
			}
		
		for (WorldObject go : worldObjects)
			go.update();
		handleCollision();
	}

	public void render() {
		sortObjects();
		for (WorldObject go : worldObjects)
			if(go.isSurface())
				go.render();
		
		for (WorldObject go : worldObjects)
			if(!go.isSurface())
				go.render();
	}
	
	
	
	////////////////////////////////////////////////
	
	
	
	
	
	
	

	public void addGO(WorldObject GO) {
		worldObjects.add(GO);
	}

	public void removeGO(WorldObject GO) {
		worldObjects.remove(GO);
	}



	private void handleCollision() {
		for (int i = 0; i < worldObjects.size(); i++) 
			for (int j = i +1 ; j < worldObjects.size(); j++)
				GPhysics.handleCollision(worldObjects.get(i), worldObjects.get(j));
		}

	private void sortObjects() {
		boolean moved = true;
		WorldObject temp;
		////SORTERA I Y ORDING
		while (moved) {
			moved = false;
			for (int i = 0; i < worldObjects.size() - 1; i++) {
				if (worldObjects.get(i).getGroundYPos() > worldObjects.get(i + 1).getGroundYPos()) {
					temp = worldObjects.get(i);
					worldObjects.set(i, worldObjects.get(i + 1));
					worldObjects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		////G…RA OBJECT FRAMF…R PLAYER GENOMSKINLIGA///
		for (WorldObject go : worldObjects) {
			if (go != player && go.getGroundYPos() > player.getGroundYPos() && GPhysics.isPosWithinTex(player.getX(), player.getY() - player.getFootZPos(), go) && go.getFootZPos() < player.getHeadZPos() && go.getHeight() > player.getHeight()) {
				go.setTransparency(0.5f);
			} else
				go.setTransparency(1);
		////////////////////////////////
		}
	}

	public static float getGravity() {
		return gravity;
	}


	public static GFood getPlayer(){
		return player;
	}
	
}
