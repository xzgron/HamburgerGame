package game.parts;

import game.*;
import game.input.*;

import java.awt.Font;
import java.util.*;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import controllers.*;
import world.*;
import world.objects.*;
import world.objects.food.hostile.BlueBerry;
import world.objects.nature.Tree;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.*;
import static game.Game.*;
public class GameWorld extends GamePart {

	public float xTranslation = 0;
	public float yTranslation = 0;
	
	public static LinkedList<WorldObject> worldObjects = new LinkedList<WorldObject>();

	private float gravity = 800;

	private Player player = new Player(0, 0);;

	public GameWorld() {

		
		
		player.setController(new PlayerController());
		spawn(player);
		spawn(new Tree(150, 30, 700,700));
		spawn(new Tree(350, 80, 800,700));
		spawn(new Tree(50, 170, 900,700));
		



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
	float blueberrySize = 0;
	GTimer spawnTimer = new GTimer(1f);
	
	public void update() {		
		///////SPAWNA BLÅBÄR/////////////
		if(spawnTimer.hasExceeded()){
			float xPos = GMath.random(-1f,1f)*Display.getWidth()/2+player.getX();
			float yPos = GMath.random(-1f,1f)*Display.getHeight()/2+player.getY();
			int side = (int)GMath.random(0,4);
			switch(side){
			case 0:
				xPos = 100+Display.getWidth()/2+player.getX();
				break;
			case 1:
				xPos = -100-Display.getWidth()/2+player.getX();
				break;
			case 2:
				yPos = 100+Display.getHeight()/2+player.getY();
				break;
			case 3:
				yPos = -100-Display.getHeight()/2+player.getY();
				break;
			
			}
			spawn(new BlueBerry(xPos,yPos, GMath.random(20,30)+(float)Math.sqrt(blueberrySize)));
			blueberrySize+=0.2f;
			spawnTimer.reset();
		}
	////////////////
			
		for	(WorldObject go : worldObjects){ //rensa dött
			if(go.getClass().isAssignableFrom(GFood.class) && ((GFood) go).isDead())
				deSpawn(go);
			}
		
		int prevSize = worldObjects.size();
		for (int i = 0; i < worldObjects.size(); i++){
			worldObjects.get(i).update();
			if(worldObjects.size() < prevSize)
				i--;
			prevSize = worldObjects.size();
		}
		handleCollision();
		
		///////////FOCUSA PLAYER///////////
		xTranslation = -player.getX() + Display.getWidth() / 2f;
		yTranslation = -player.getY() + Display.getHeight() / 2f;
		///////////
	}

	public void render() {
		glPushMatrix();
		glTranslatef(xTranslation,yTranslation,0);
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
				if (worldObjects.get(i).getY() > worldObjects.get(i + 1).getY()) {
					temp = worldObjects.get(i); // object i flyttas innan i + 1
					worldObjects.set(i, worldObjects.get(i + 1));
					worldObjects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		//////////OBJECT ÖVER ANDRA SKA RENDRAS EFTER/////////FUNKAR FÖR TILLFÄLLET..
		moved = true;
		while (moved) {
			moved = false;
			for (int i = 0; i < worldObjects.size() - 1; i++) {
				if (/*GPhysics.objectsOverlapp(worldObjects.get(i), worldObjects.get(i + 1)) &&*/ worldObjects.get(i).getFootZPos() -1 >= worldObjects.get(i+1).getHeadZPos()) {
					temp = worldObjects.get(i); // object i flyttas innan i + 1
					worldObjects.set(i, worldObjects.get(i + 1));
					worldObjects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		
		////GÖRA OBJECT FRAMFÖR PLAYER GENOMSKINLIGA///
		for (WorldObject go : worldObjects) {
			if (go != player && go.getY() > player.getY() && GPhysics.isPosWithinTex(player.getX(), player.getY() - player.getFootZPos(), go) && go.getFootZPos() < player.getHeadZPos() && go.getHeight() > player.getHeight()) {
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
	
	public float getXTranslation(){	
		return xTranslation;
	}
	
	public float getYTranslation(){	
		return yTranslation;
	}
	
}
