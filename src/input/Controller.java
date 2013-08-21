package input;
import static org.lwjgl.input.Keyboard.*;
import java.util.LinkedList;

import gameObjects.*;

public abstract class Controller {
	
	private LinkedList<GCharacter> recievers;
	

	protected abstract void handleInput();
	
	public Controller() {
		recievers = new LinkedList<GCharacter>();
	}
	
	protected void addReciever(GCharacter GC){
		recievers.add(GC);
	}
	
	protected void removeReciever(GCharacter GC){
		recievers.remove(GC);
	}
	
	protected void emptyRecievers(GCharacter GC){
		recievers.clear();
	}
	protected LinkedList<GCharacter> getRecievers(){
		return recievers;
	}
	
	public GCharacter reciever(int i) {
		return recievers.get(i);
	}
	

}
