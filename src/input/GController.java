package input;

import java.util.LinkedList;

import worldObjects.food.GFood;

public abstract class GController {
	
private GFood reciever;
	

	protected abstract void handleInput();
	
	public GController(GFood gf){
		reciever = gf;
	}
	
	protected void setReciever(GFood gf){
		reciever = gf;
	}
	
	protected GFood getReciever(){
		return reciever;
	}
	
	protected void removeReciever(){
		reciever = null;
	}
	


	
	
	public void add(){
		Input.controllers.add(this);
	}
	
	public void remove(){
		Input.controllers.remove(this);
		
	}
	

}
