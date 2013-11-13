package game.parts;

import game.GPhysics;
import game.GamePart;

import java.util.ArrayList;
import java.util.LinkedList;

import controllers.DefaultController;
import controllers.GController;
import world.WorldObject;
import worldObjects.*;
import worldObjects.food.*;

public class GameWorld implements GamePart {

	public static ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();
	
	public static LinkedList<GController> controllers = new LinkedList<GController>();

	private static float gravity = 800;

	private static GFood player = new Hamburger(400, 400, 100);;

	public GameWorld() {
		addGO(player);
		player.setController(new DefaultController(player));
		addGO(new Tree(150, 30, 300));
		addGO(new Tree(350, 80, 400));
		addGO(new Tree(50, 170, 500));
		for (int i = 0; i < 1; i++)
			addGO(new BlueBerry((float) Math.random() * 1000 - 150,
					(float) Math.random() * 1000 - 150));
	}
	
	public void handleInput() {
		for(GController c: controllers)
			c.handle();
		
	}
	
	
	public void update() {
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

	public void addGO(WorldObject GO) {
		worldObjects.add(GO);
	}

	public void removeGO(WorldObject GO) {
		worldObjects.remove(GO);
	}



	private void handleCollision() {
		boolean collide = true;
		while (collide) {
			collide = false;
			for (WorldObject go1 : worldObjects) {
				for (WorldObject go2 : worldObjects) {
					if (go2 != go1){
						if(GPhysics.handleCollision(go1, go2));
							//collide = true;
						
					}
				}
			}		
		}
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
		}
	}

	public static float getGravity() {
		return gravity;
	}


	public static GFood getPlayer(){
		return player;
	}
	
}
