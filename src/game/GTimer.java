package game;

public class GTimer {
	
	private float time = 0;
	
	private float exceedTime;
	private boolean exceed;
	
	public GTimer(float exceedTime){
		this.exceedTime = exceedTime;
	}
	
	public void update(){
		time += Game.deltaTime;
		
		if(time >= exceedTime){
			exceed = true;
			time = time-exceedTime;
		}
		else
			exceed = false;
	}
	
	
	public boolean exceeded(){
		return exceed;
	}
	
	public float getTime(){
		return time;
	}
	
	public void setTime(float time){
		this.time = time;
	}
	
	public void startOver(){
		time = 0;
	}
	
	
}
