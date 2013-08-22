package input;
import static org.lwjgl.input.Keyboard.*;
import java.util.LinkedList;

import gameObjects.*;

public abstract class GController {
	
	private LinkedList<GCharacter> recievers;
	

	protected abstract void handleInput();
	
	public GController() {
		recievers = new LinkedList<GCharacter>();
	}
	
	protected void addReciever(GCharacter GC){
		recievers.add(GC);
	}
	
	protected void removeReciever(GCharacter GC){
		recievers.remove(GC);
	}
	
	protected void emptyRecievers(){
		recievers.clear();
	}
	protected LinkedList<GCharacter> getRecievers(){
		return recievers;
	}
	
	public GCharacter reciever(int i) {
		return recievers.get(i);
	}
	

}
