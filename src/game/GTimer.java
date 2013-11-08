package game;

public class GTimer {
	
	private float time = 0;
	
	private float exceedTime;
	private boolean exceed;
	
	public GTimer(float exceedTime){
		this.exceedTime = exceedTime;
	}
	
	public void update(){
		
		time += Main.getDelta();
		
		if(time >= exceedTime){
			exceed = true;
			time = time-exceedTime;
		}
		else
			exceed = false;
	}
	
	
	public boolean hasExceeded(){
		return exceed;
	}
	
	public float getTime(){
		return time;
	}
	
	public void setTime(float time){
		this.time = time;
	}
	
	public void reset(){
		time = 0;
	}
	
	
}
