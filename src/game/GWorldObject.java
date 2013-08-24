package game;

public abstract class GWorldObject extends GObject{

	private float xPrev, yPrev;
	
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

}
