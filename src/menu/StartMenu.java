package menu;

import game.GButton;
import game.Main;

import java.util.LinkedList;

public class StartMenu {
	
	LinkedList<GButton> buttons = new LinkedList<GButton>();
	
	public StartMenu(){
		buttons.add(new StartButton(Main.window_width/2,Main.window_height/2+100, 300, 80));
		buttons.add(new ExitButton(Main.window_width/2,Main.window_height/2-100, 300, 80));
	}
	
	public void update() {
		
		for(GButton b: buttons)
			b.update();
	}

	public void render() {
		for(GButton b: buttons)
			b.render();
	}

}
