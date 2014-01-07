package game.input;

import game.Main;

public class GTimer {
	
	long startTime;
	long length;
	
	public GTimer(float length){ //length i sekunder
		startTime = Main.getTime();
		
		this.length = (long) (length * 1000000000.0f); 
	}
	
	public boolean hasExceeded(){
		return (Main.getTime() >= startTime + length) && length >= 0; 
	}
	
	public void reset(){
		startTime = Main.getTime();
	}
	
	public void resetFromEnd(){
		startTime += length; 
	}
	
	public void setLength(float length){
		this.length = (long) (length * 1000000000.0f); 
	}
	
	public float getPastTime(){
		return (Main.getTime() - startTime) / 1000000000.0f;
	}
	
	public float getExceededTime(){
		return (Main.getTime() - startTime -length)/1000000000.0f;
		
	}
	
	public float getLength(){
		return length/1000000000.0f;
	}

}
