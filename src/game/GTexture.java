package game;
import java.io.*;
import java.util.LinkedList;
import org.newdawn.slick.opengl.*;

public class GTexture {
	private static LinkedList<Texture> textures = new LinkedList<Texture>();

	public static Texture getTexture(String fileName) {
		if(fileName == null)
			return null;
		
		Texture t = null;

		try {
			t = TextureLoader.getTexture("PNG", new FileInputStream(new File("resources/textures/" + fileName + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Texture t2 : textures)
			if (t.equals(t2))
				return t2;

		textures.add(t);
		return t;
	}
}
