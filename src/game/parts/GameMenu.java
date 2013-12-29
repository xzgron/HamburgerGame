package game.parts;

import game.GButton;
import game.GSprite;
import game.GTexture;
import game.Game;
import game.GamePart;
import game.Main;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class GameMenu implements GamePart {

	GSprite background = new GSprite(Display.getWidth() / 2,
			Display.getHeight() / 2, 600, 480, "UI/gameMenuBackground");

	GButton continueButton = new GButton(Display.getWidth() / 2,
			Display.getHeight() / 2 - 100, 300, 80);
	GButton optionButton = new GButton(Display.getWidth() / 2,
			Display.getHeight() / 2, 300, 80);
	GButton exitButton = new GButton(Display.getWidth() / 2,
			Display.getHeight() / 2 + 100, 300, 80);

	Texture continueNormal = GTexture.getTexture("buttons/continueNormal");
	Texture continueClicked = GTexture.getTexture("buttons/continueClicked");

	Texture optionNormal = GTexture.getTexture("buttons/optionNormal");
	Texture optionClicked = GTexture.getTexture("buttons/optionClicked");

	Texture exitNormal = GTexture.getTexture("buttons/exitNormal");
	Texture exitClicked = GTexture.getTexture("buttons/exitClicked");

	public GameMenu() {
		continueButton.setTexture(continueNormal);
		exitButton.setTexture(exitNormal);
	}

	public void handleInput() {
		// ///START BUTTON//////
		if (continueButton.isReleasedOver(0))
			Main.game.setGameState(Game.GState.GAME);

		if (continueButton.isHeldIn(0))
			continueButton.setTexture(continueClicked);
		else
			continueButton.setTexture(continueNormal);

		// ///OPTION BUTTON//////
		if (optionButton.isReleasedOver(0))
			Main.game.setGameState(Game.GState.OPTIONS);

		if (optionButton.isHeldIn(0))
			optionButton.setTexture(optionClicked);
		else
			optionButton.setTexture(optionNormal);

		// ///EXIT BUTTON/////
		if (exitButton.isReleasedOver(0))
			Main.close();

		if (exitButton.isHeldIn(0))
			exitButton.setTexture(exitClicked);
		else
			exitButton.setTexture(exitNormal);
	}

	public void update() {
		continueButton.update();
		optionButton.update();
		exitButton.update();
	}

	public void render() {
		background.render();
		continueButton.render();
		optionButton.render();
		exitButton.render();

	}
}
