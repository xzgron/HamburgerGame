package input;
import static org.lwjgl.input.Keyboard.*;
import java.util.LinkedList;

import gameObjects.*;

public abstract class GController<recType> {
	
	private LinkedList<recType> recievers;
	

	protected abstract void handleInput();
	
	public GController(){
		recievers = new LinkedList<recType>();
	}
	
	protected void addReciever(recType GC){
		recievers.add(GC);
	}
	
	protected void removeReciever(recType GC){
		recievers.remove(GC);
	}
	
	protected void emptyRecievers(){
		recievers.clear();
	}
	protected LinkedList<recType> getRecievers(){
		return recievers;
	}
	
	public recType reciever(int i) {
		return recievers.get(i);
	}
	

}
