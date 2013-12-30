package game.HUDMaterial;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import world.objects.GIngredient;
import world.objects.GItem;
import game.GButton;
import game.GImage;
import game.GamePart;

public class InventorySlot extends GButton{

	GItem item;

	float itemSize; // visuell storlek av objektet

	TrueTypeFont durabilityFont = new TrueTypeFont(new Font("Times New Roman",Font.BOLD, 12), false);

	public InventorySlot(float x, float y, float size, float itemSize) {
		super(x, y, size, size);
		this.itemSize = itemSize;
	}

	public InventorySlot(float x, float y, float size, float itemSize,
			GItem item) {
		super(x, y, size, size);
		this.itemSize = itemSize;
		this.item = item;
	}

	public InventorySlot(float x, float y, float size, float itemSize,
			String texture) {
		super(x, y, size, size, texture);
		this.itemSize = itemSize;
	}

	public InventorySlot(float x, float y, float size, float itemSize,
			String texture, GItem item) {
		super(x, y, size, size, texture);
		this.itemSize = itemSize;
		this.item = item;
	}

	public void render() {
		this.draw();

		if (item != null) {
			float maxTexSide = Math
					.max(item.getTexWidth(), item.getTexHeight());
			float itemTexWidth = item.getTexWidth() * itemSize / maxTexSide;
			float itemTexHeight = item.getTexHeight() * itemSize / maxTexSide;
			GImage.draw(item.getTexture(), this.getX(), this.getY(),
					itemTexWidth, itemTexHeight);

			if (item instanceof GIngredient) {
				if(((GIngredient) item).getDurability() != -1)
					durabilityFont.drawString(getX() - itemSize/2, getY() - itemSize/2, ((GIngredient) item).getDurability() + "", Color.white);
				else
					durabilityFont.drawString(getX() - itemSize/2, getY() - itemSize/2, "##", Color.white);
			}
		}
	}
	
	public void setItem(GItem i){
		item = i;
	}

	public GItem getItem() {
		return item;
	}


}
