package game.parts;

import game.GImage;
import game.GMath;
import game.GPhysics;
import game.GSound;
import game.GTexture;
import game.Game;
import game.GamePart;
import game.Main;
import game.input.GKeyboard;

import java.util.ArrayList;
import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import controllers.Controlls;
import controllers.PlayerController;
import controllers.GController;
import world.Player;
import world.WorldObject;
import world.objects.*;
import world.objects.food.*;
import world.objects.food.hostile.BlueBerry;
import world.objects.nature.Tree;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.*;
import static game.Game.*;
public class GameWorld extends GamePart {

	public static ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();

	private float gravity = 800;

	private Player player = new Player(0, 0);;

	public GameWorld() {
		player.setController(new PlayerController());
		spawn(player);
		spawn(new Tree(150, 30, 700,700));
		spawn(new Tree(350, 80, 800,700));
		spawn(new Tree(50, 170, 900,700));
		

		for (int i = 0; i < 5; i++)
			spawn(new BlueBerry(GMath.random(500,-500),
					GMath.random(500,-500), GMath.random(20,30)));

		spawn(new BlueBerry(GMath.random(500,-500),
				GMath.random(500,-500), 70));
	}
	
	///////////MAIN PART////////////////
	public void handleInput() {
		if(GKeyboard.isKeyPressed(KEY_ESCAPE))
			Main.game.setGameState(GState.GAME_MENU);
		
	
		if(GKeyboard.isKeyPressed(Controlls.INVENTORY_KEY))
			Main.game.setGameState(GState.INVENTORY_MENU);
	
	}
	
	public void update() {
		for	(WorldObject go : worldObjects){ //rensa dštt
			if(go.getClass().isAssignableFrom(GFood.class) && ((GFood) go).isDead())
				deSpawn(go);
			}
		
		for (WorldObject go : worldObjects)
			go.update();
		handleCollision();
	}

	public void render() {
		glPushMatrix();
		Game.focusPoint(player.getX(),player.getY());
		renderGrass();
		sortObjects();
		for (WorldObject go : worldObjects)
			if(go.isSurface())
				go.render();
		
		for (WorldObject go : worldObjects)
			if(!go.isSurface())
				go.render();
		
		glPopMatrix();
	}
	
	
	
	////////////////////////////////////////////////

	/////////////HANTERA OBJECT/////////////
	

	public void spawn(WorldObject GO) {
		worldObjects.add(GO);
	}

	public void deSpawn(WorldObject GO) {
		worldObjects.remove(GO);
	}

//////////////////////////////////

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
					temp = worldObjects.get(i); // object i flyttas innan i + 1
					worldObjects.set(i, worldObjects.get(i + 1));
					worldObjects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		//////////OBJECT …VER ANDRA SKA RENDRAS EFTER/////////FUNKAR F…R TILLF€LLET..
		moved = true;
		while (moved) {
			moved = false;
			for (int i = 0; i < worldObjects.size() - 1; i++) {
				if (GPhysics.objectsOverlapp(worldObjects.get(i), worldObjects.get(i + 1)) && worldObjects.get(i).getFootZPos() >= worldObjects.get(i+1).getHeadZPos()) {
					temp = worldObjects.get(i); // object i flyttas innan i + 1
					worldObjects.set(i, worldObjects.get(i + 1));
					worldObjects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		
		////G…RA OBJECT FRAMF…R PLAYER GENOMSKINLIGA///
		for (WorldObject go : worldObjects) {
			if (go != player && go.getGroundYPos() > player.getGroundYPos() && GPhysics.isPosWithinTex(player.getX(), player.getY() - player.getFootZPos(), go) && go.getFootZPos() < player.getHeadZPos() && go.getHeight() > player.getHeight()) {
				go.setAlpha(0.5f);
			} else
				go.setAlpha(1);
		////////////////////////////////
		}
	}

	Texture grass = GTexture.getTexture("nature/grass");
	
	public void renderGrass(){
		//100x100
		float grassSize = 100;
		
		float startX = (player.getX() - Display.getWidth()/2);
		startX = startX-startX%grassSize-grassSize/2;
		//System.out.println(startX);
		float startY = (player.getY() - Display.getHeight()/2);
		startY = startY-startY%grassSize-grassSize/2;
		
		for(int x = (int) startX; x-grassSize/2 <= Display.getWidth()/2+player.getX(); x += grassSize)
			for(int y = (int) startY; y-grassSize/2 <= Display.getHeight()/2+player.getY(); y += grassSize)
				GImage.draw(grass, x, y, grassSize, grassSize);
	}
	
	
	public float getGravity() {
		return gravity;
	}


	public Player getPlayer(){
		return player;
	}
	
}
