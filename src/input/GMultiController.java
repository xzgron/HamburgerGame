package input;
import static org.lwjgl.input.Keyboard.*;

import java.util.LinkedList;

import worldObjects.*;

/*
 * Den här klassen kanske vi aldrig kommer använda men ta inte bort den!
 * 
 * 
 * 
 * 
 * */
public abstract class GMultiController<recType> extends GController {
	
	private LinkedList<recType> recievers;
	

	protected abstract void handleInput();
	
	public GMultiController(){
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
	
	public void add(){
		Input.controllers.add(this);
	}
	
	public void remove(){
		Input.controllers.remove(this);
		
	}
	

}
