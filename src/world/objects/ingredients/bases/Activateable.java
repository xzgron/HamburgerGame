package world.objects.ingredients.bases;

import game.parts.GameWorld;
import world.WorldObject;

public interface Activateable {
	
	public void useFirstAbility(WorldObject user, GameWorld world);
	public void useSecondAbility(WorldObject user, GameWorld world);

}
