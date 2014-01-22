package world;

public class LevelHandler {
	private int experience = 0;
	private int level = 1;
	private int levelPoints = 1;
	
	
	
	
	public void grantExperience(int amt){
		experience += amt;
		if(experience >= getExperienceToNextLevel()){
			experience -= getExperienceToNextLevel();
			levelUp();
		}
	}
	
	public void levelUp(){
		level += 1;
		levelPoints += 1;
	}
	
	public int getExperienceToNextLevel(){
		return level*100;
	}

	public int getLevel(){
		return level;
	}
	
	public int getLevelPoints(){
		return levelPoints;
	}
}
