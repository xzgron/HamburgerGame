package game;

import static org.lwjgl.opengl.GL11.GL_CLAMP;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class GTexture {
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

	
	
	
	
	public static int addAlphaColor(File image, float red, float green, float blue) {
		
		final int BYTES_PER_PIXEL = 4;// 3 for RGB, 4 for RGBA
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(image);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		int[] pixels = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
		
		bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), pixels, 0,
				bufferedImage.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(bufferedImage.getWidth()
				* bufferedImage.getHeight() * BYTES_PER_PIXEL); // 4 for RGBA, 3 for RGB

		for (int y = 0; y < bufferedImage.getHeight(); y++) {
			for (int x = 0; x < bufferedImage.getWidth(); x++) {
				int pixel = pixels[y * bufferedImage.getWidth() + x];
				System.out.println((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component.
															// Only for RGBA
			}
		}

		buffer.flip(); // FOR THE LOVE OF GOD DO NOT FORGET THIS

		// You now have a ByteBuffer filled with the color data of each pixel.
		// Now just create a texture ID and bind it. Then you can load it using
		// whatever OpenGL method you want, for example:

		int textureID = glGenTextures(); // Generate texture ID
		glBindTexture(GL_TEXTURE_2D, textureID); // Bind texture ID

		// Setup wrap mode
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		// Setup texture scaling filtering
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// Send texel data to OpenGL
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, bufferedImage.getWidth(),
				bufferedImage.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		// Return the texture ID so we can bind it later again
		return textureID;
	}



}