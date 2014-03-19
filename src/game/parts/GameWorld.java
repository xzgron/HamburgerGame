package game.parts;

import game.*;
import game.tools.*;

import java.awt.Font;
import java.util.*;

import options.*;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import world.*;
import world.objects.*;
import world.objects.food.HostileFood;
import world.objects.food.hostile.BlueBerry;
import world.objects.food.hostile.Carrot;
import world.objects.nature.Bush;
import world.objects.nature.Tree;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.*;
import static game.Game.*;

// objects listan innehåller allt som finns i spelet. det uppdateras och renderas sedan. Det finns även en del här som ritar ut gräset i bakgrunden
public class GameWorld extends GamePart {

	private float xTranslation = 0;
	private float yTranslation = 0;
	
	public LinkedList<WorldObject> objects = new LinkedList<WorldObject>();
	
	WaveHandler waveHandler = new WaveHandler();
	
	private float gravity = 800;

	private Player player = new Player(0, 0);

	public GameWorld() {
		spawn(player);
		spawn(new Tree(150, 30, 350,700));
		spawn(new Tree(350, 80, 400,700));
		spawn(new Tree(50, 170, 450,700));
		spawn(new Bush(-500, 100, 160,100));
		spawn(new Bush(-500, 40, 230,150));

		waveHandler.nextWave();
		//spawn(new BlueBerry(GMath.random(500,-500),GMath.random(500,-500), 70));
	}
	
	///////////MAIN PART////////////////
	public void handleInput() {
		if(GKeyboard.isKeyPressed(KEY_ESCAPE))
			Main.game.setGameState(GState.GAME_MENU);
		
		if(GKeyboard.isKeyPressed(Controlls.INVENTORY_KEY))
			Main.game.setGameState(GState.INVENTORY_MENU);
	}
	/*
	float blueberrySize = 0;
	GTimer spawnTimer = new GTimer(1f);
	*/
	public void update() {		
		waveHandler.update(this);
		
		///////SPAWNA BLÔøΩB‚Ç¨R/////////////
		/*
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
			if(Math.random() > 0.1)
				spawn(new BlueBerry(xPos,yPos, GMath.random(20,30)+(float)Math.sqrt(blueberrySize),player));
			else
				spawn(new Carrot(xPos, yPos,player));
			blueberrySize+=0.2f;
			spawnTimer.reset();
		}*/
	////////////////

		
		int prevSize = objects.size();
		for (int i = 0; i < objects.size(); i++){
			objects.get(i).update(this);
			if(objects.size() < prevSize)
				i--;
			prevSize = objects.size();
		}
		handleCollision();
		
		///////////FOCUS PLAYER///////////
		xTranslation = -player.getX() + Display.getWidth() / 2f;
		yTranslation = -player.getY() + Display.getHeight() / 2f;
		///////////
	}

	public void render() {
		glPushMatrix();
		glTranslatef(xTranslation,yTranslation,0);
		renderGrass();
		sortObjects();
		for (WorldObject go : objects)
			if(go.isSurface())
				go.render();
		
		for (WorldObject go : objects)
			if(!go.isSurface())
				go.render();
		
		glPopMatrix();
	}
	
	
	
	////////////////////////////////////////////////

	/////////////HANTERA OBJECT/////////////
	

	public void spawn(WorldObject GO) {
		objects.add(GO);
	}

	public void deSpawn(WorldObject GO) {
		objects.remove(GO);
	}

//////////////////////////////////

	private void handleCollision() {
		for (int i = 0; i < objects.size(); i++) 
			for (int j = i +1 ; j < objects.size(); j++)
				GPhysics.handleCollision(objects.get(i), objects.get(j),this);
	}

	private void sortObjects() {
		boolean moved = true;
		WorldObject temp;
		////SORTERA I Y ORDING
		while (moved) {
			moved = false;
			for (int i = 0; i < objects.size() - 1; i++) {
				if (objects.get(i).getY() > objects.get(i + 1).getY()) {
					temp = objects.get(i); // object i flyttas innan i + 1
					objects.set(i, objects.get(i + 1));
					objects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		//////////OBJECT ‚Ä¶VER ANDRA SKA RENDRAS EFTER/////////FUNKAR F‚Ä¶R TILLF‚Ç¨LLET..
		moved = true;
		while (moved) {
			moved = false;
			for (int i = 0; i < objects.size() - 1; i++) {
				if (/*GPhysics.objectsOverlapp(worldObjects.get(i), worldObjects.get(i + 1)) &&*/ objects.get(i).getFootZPos() -1 >= objects.get(i+1).getHeadZPos()) {
					temp = objects.get(i); // object i flyttas innan i + 1
					objects.set(i, objects.get(i + 1));
					objects.set(i + 1, temp);
					moved = true;
				}
			}
		}
		
		////G‚Ä¶RA OBJECT FRAMF‚Ä¶R PLAYER GENOMSKINLIGA///
		for (WorldObject go : objects) {
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
	
	public LinkedList<GFood> getFood(){
		LinkedList<GFood> food = new LinkedList<GFood>();
		for(WorldObject o: objects)
			if(o instanceof GFood)
				food.addLast((GFood) o);
		return food;	
	}
	
	public LinkedList<HostileFood> getAliveHostileFood(){
		LinkedList<HostileFood> food = new LinkedList<HostileFood>();
		for(WorldObject o: objects)
			if(o instanceof HostileFood && !((GFood)o).isDead())
				food.addLast((HostileFood) o);
		return food;	
	}
}
