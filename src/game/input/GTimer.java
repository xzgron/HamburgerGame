package game.input;

import game.Main;

public class GTimer {
	
	long startTime;
	long length;
	
	public GTimer(float length){
		startTime = Main.getTime();
		
		this.length = (long) (length * 1000000000.0); 
	}
	
	public boolean hasExceeded(){
		return (Main.getTime() >= startTime + length); 
	}
	
	public void restart(){
		startTime = Main.getTime();
	}
	
	public void restartFromEnd(){
		startTime += length; 
	}
	
	public void setLength(float length){
		this.length = (long) (length * 1000000000.0); 
	}
	
	public float getPastTime(){
		return (Main.getTime() - startTime) / 1000000000.0f;
	}
	
	public float getLength(){
		return length/1000000000.0f;
	}

}
