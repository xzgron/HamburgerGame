package menu;

import game.GButton;
import game.GObject;

import game.Main;

import java.util.LinkedList;

public class GameMenu {
	
	LinkedList<GButton> buttons = new LinkedList<GButton>();
	LinkedList<GObject> Areas = new LinkedList<GObject>();
	
	public GameMenu(){
		Areas.add(new MenuArea(Main.window_width/2,Main.window_height/2, 600, 480));
		buttons.add(new ContinueButton(Main.window_width/2,Main.window_height/2+100, 300, 80));
		buttons.add(new ExitButton(Main.window_width/2,Main.window_height/2-100, 300, 80));
	}
	
	public void update() {
		for(GObject b: Areas)
			b.update();
		for(GButton b: buttons)
			b.update();
		
	}

	public void render() {
		for(GObject b: Areas)
			b.render();
		for(GButton b: buttons)
			b.render();
	}

}
