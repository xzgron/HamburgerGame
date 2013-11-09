package controllers;

import game.parts.GameWorld;

import java.util.LinkedList;

import worldObjects.food.GFood;

public abstract class GController {
	
private GFood reciever;
	

	public abstract void handle();
	
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
		GameWorld.controllers.add(this);
	}
	
	public void remove(){
		GameWorld.controllers.remove(this);
		
	}
	

}