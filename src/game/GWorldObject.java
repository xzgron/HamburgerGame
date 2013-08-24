package game;

public abstract class GWorldObject extends GObject{

	private float xPrev, yPrev;
	
	private float zPos;// en extra y variabel för texture som inte påväerkar objektets position.
	
	private float radie = -1;
	
	private float footPosition = 0.5f;
	
	public GWorldObject(float xPos, float yPos, float texWidth, float texHeight) {
		super(xPos, yPos, texWidth, texHeight);
		// TODO Auto-generated constructor stub
	}
	

	
	public void setRadie(float f){
		radie = f;
	}
	
	public float getRadie(){
		return radie;
	}
	
	
	
	public void setZ(float z){
		zPos = z;
	}
	
	public float getZ(){
		return zPos;
	}
	
	
	// foten bestämmer procentuellt var på bilden som går i marken
	public void setFootPos(float f){
		if(f > 1 || f < 0)
			System.out.println("footen ska vara mellan 0 och 1");
		else
			footPosition = f;
	}
	
	public float getFootPos(){
		return(getY() - getTexHeight()/2 + getTexHeight()*footPosition);
	}
	
	public float getFootPos(float y){
		return(y - getTexHeight()/2 + getTexHeight()*footPosition);
	}
	

}
