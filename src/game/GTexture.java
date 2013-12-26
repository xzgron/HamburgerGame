package game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class GTexture {
	//LinkedList<Texture> textures = new LinkedList<Texture>();
	
	public static Texture getTexture(String fileName) {
		
		Texture t = null;

		try {
			t = TextureLoader.getTexture("PNG", new FileInputStream(new File("resources/textures/" + fileName + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
}
