package input;
import static org.lwjgl.input.Keyboard.*;

import java.util.LinkedList;

import game.GObject;
import gameObjects.*;

public abstract class GFoodController extends GController<GFoodController>{

	protected abstract void handleInput();
	
	public GFoodController() {
	}
	

	

}
