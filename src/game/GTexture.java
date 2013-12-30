package game;
import java.io.*;
import java.util.LinkedList;
import org.newdawn.slick.opengl.*;

public class GTexture {
	private static LinkedList<Texture> textures = new LinkedList<Texture>();
	private static LinkedList<String> textureLocations = new LinkedList<String>();

	public static Texture getTexture(String fileName) {
		Texture t = null;
		for (int i = 0; i < textures.size(); i++){
			if (textureLocations.get(i).equals(fileName)){
				return textures.get(i);
				}
		}

		
		try {
			t = TextureLoader.getTexture("PNG", new FileInputStream(new File("resources/textures/" + fileName + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		textures.add(t);
		textureLocations.add(fileName);
		
		return t;
	}
}
