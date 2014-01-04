package game.HUDMaterial;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import world.objects.GIngredient;
import world.objects.GItem;
import game.GButton;
import game.GImage;
import game.GMath;
import game.GSprite;
import game.GamePart;
import game.input.GMouse;

public class InventorySlot extends GButton{
	
	
	private static InventorySlot grabbedSlot = null;

	private GItem item = null;
	private GSprite itemPicture = null;
	
	private boolean itemHitBox = true;

	private float itemTexSize; // visuell storlek av objektet

	TrueTypeFont durabilityFont = new TrueTypeFont(new Font("Times New Roman",Font.BOLD, 12), false);

	public InventorySlot(float x, float y, float size, float itemSize) {
		super(x, y, size, size);
		this.itemTexSize = itemSize;
	}

	public InventorySlot(float x, float y, float size, float itemSize,
			GItem item) {
		super(x, y, size, size);
		this.itemTexSize = itemSize;
		setItem(item);
	}

	public InventorySlot(float x, float y, float size, float itemSize,
			String texture) {
		super(x, y, size, size, texture);
		this.itemTexSize = itemSize;
	}

	public InventorySlot(float x, float y, float size, float itemSize,
			String texture, GItem item) {
		super(x, y, size, size, texture);
		this.itemTexSize = itemSize;
		setItem(item);
	}

	public void handleInput(){ // hanterar dra och släppa

		if(GMouse.isButtonReleased(GMouse.BUTTON_LEFT) && isCursorWithin() && grabbedSlot != null && grabbedSlot.getItem() != null){ //byta plats om släpps över.
			switchItem(grabbedSlot);
		}
		
		if(getItem() != null && this.isHeldIn(GMouse.BUTTON_LEFT) && (!this.isCursorWithin() || grabbedSlot == this)){  //dras runt
			itemPicture.setPosition(GMouse.getX(), GMouse.getY());
			grabbedSlot = this;
		}
		
		if(getItem() != null && this.isClicked(GMouse.BUTTON_LEFT))
			item.click();
	}
	
	public void update(){
		
		 if(getItem() != null && grabbedSlot == this && !this.isHeldIn(GMouse.BUTTON_LEFT)){ //flyttas tillbaka
			itemPicture.setPosition(this.getX(),this.getY());
			grabbedSlot = null;
			}
		 
		super.update();
	}
	
	public void render() {
		if(this.isHeldIn(GMouse.BUTTON_LEFT))
			GImage.draw(getTexture(), getX(), getY(), getTexWidth(), getTexHeight(), getRed(), getGreen(), getBlue(), getAlpha()*0.7f);	
		else
			this.draw();
		
		if(getItem() != null && itemPicture != null){
			itemPicture.draw();
			if (getItem() instanceof GIngredient) {
				if(((GIngredient) getItem()).getDurability() != -1)
					durabilityFont.drawString(getX() - itemTexSize/2, getY() - itemTexSize/2, ((GIngredient) getItem()).getDurability() + "", Color.white);
				else
					durabilityFont.drawString(getX() - itemTexSize/2, getY() - itemTexSize/2, "##", Color.white);
			}
		}
	}

	public static void renderGrabbedContent(){
		if(grabbedSlot != null && grabbedSlot.getItem() != null){
			grabbedSlot.itemPicture.draw();
			if (grabbedSlot.getItem() instanceof GIngredient) {
				if(((GIngredient) grabbedSlot.getItem()).getDurability() != -1)
					grabbedSlot.durabilityFont.drawString(grabbedSlot.getX() - grabbedSlot.itemTexSize/2, grabbedSlot.getY() - grabbedSlot.itemTexSize/2, ((GIngredient) grabbedSlot.getItem()).getDurability() + "", Color.white);
				else
					grabbedSlot.durabilityFont.drawString(grabbedSlot.getX() - grabbedSlot.itemTexSize/2, grabbedSlot.getY() - grabbedSlot.itemTexSize/2, "##", Color.white);
			}
		}
	}


	
	protected void initItemPicture(){
		if (getItem() != null) {
			float maxTexSide = Math.max(getItem().getTexWidth(), getItem().getTexHeight());
			float itemTexWidth = getItem().getTexWidth() * itemTexSize / maxTexSide;
			float itemTexHeight = getItem().getTexHeight() * itemTexSize / maxTexSide;
			
			itemPicture = new GSprite(this.getX(), this.getY(), itemTexWidth, itemTexHeight);
			itemPicture.setTexture(getItem().getTexture());
		}
		else
			itemPicture = null;
	}
	
	public void setItemHitBox(boolean b){
		itemHitBox = b;
	}
	
	public boolean isCursorWithin(){
		if(itemHitBox)
			return GMath.isPosWithinSquare(GMouse.getX(),GMouse.getY(),this.getX(), this.getY(), itemTexSize, itemTexSize);
		else
			return super.isCursorWithin();
	}
	
	//////////ITEM MOVEMENT///////////
	
	public boolean switchItem(InventorySlot slot){ //returnerar om det gick eller inte
		//denna metod körs av slotten som får något släppt på sig.
		GItem slotItem = slot.getItem(); 
		GItem thisItem = this.getItem(); 
		
		if(slot instanceof HamburgerEquipmentSlot){
			this.setItem(slotItem);
			slot.setItem(thisItem);
	
			if(slotItem == null && !((HamburgerEquipmentSlot)slot).getOwner().isDead()){ // ifall en eqslot byts ut mot inget ska allt det alltid funka så länge inte ägaren dör av det
				return true;
			}
			if(!((HamburgerEquipmentSlot)slot).isEquipmentLegal()){
				this.setItem(thisItem);
				slot.setItem(slotItem);
				return false;
			}
			return true;
		}
		else{
			slot.setItem(thisItem);
			this.setItem(slotItem);
			return true;
		}
	}
	
	public void setItem(GItem i){ //returnerar om det gick eller inte
		item = i;
		initItemPicture();
	}

	public GItem getItem() {
		return item;
	}
	
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		if(itemPicture != null)
			itemPicture.setPosition(x, y);
	}
	
	public void move(float x, float y){
		super.move(x, y);
		if(itemPicture != null)
			itemPicture.move(x, y);
	}
}
