package game.HUDMaterial;

import java.util.LinkedList;

import world.Player;
import world.objects.GIngredient;
import world.objects.GItem;
import world.objects.food.Hamburger;

public class HamburgerEquipmentSlot extends InventorySlot{


	Hamburger owner;
	
	int equipmentNumber;
	
	public HamburgerEquipmentSlot(float x, float y, float size, float itemSize, String texture, int equipmentNumber, Hamburger owner ) {
		super(x, y, size, itemSize, texture);
		this.owner = owner;
		this.equipmentNumber = equipmentNumber;
		initItemPicture();
	}
	
	public void setItem(GItem gi){
		if(gi == null){
			if(equipmentNumber < owner.getEquipments().size())
				owner.getEquipments().remove(equipmentNumber);
			}
		else {
			if(equipmentNumber < owner.getEquipments().size()){
				owner.getEquipments().add(equipmentNumber, (GIngredient) gi);
				}
			else
				owner.getEquipments().addLast((GIngredient) gi);
			initItemPicture();
			}
	}
	

	public boolean switchItem(InventorySlot slot){ //returnerar om det gick eller inte
		//denna metod kšrs alltid av equipmentsloten i en switch
		GItem slotItem = slot.getItem(); 
		GItem thisItem = this.getItem(); 


		if(!(slotItem instanceof GIngredient) && slotItem != null) //inte Šr GIngredient
			return false;
		
		if(slot instanceof HamburgerEquipmentSlot){
			if(slotItem !=null)
				this.setItem(null);
			this.setItem(slotItem);
			if(thisItem !=null)
				slot.setItem(null);
			slot.setItem(thisItem);

			
			if(!((HamburgerEquipmentSlot)slot).isEquipmentLegal() || !isEquipmentLegal()){
				if(slotItem !=null)
					this.setItem(null);
				this.setItem(thisItem);
				if(thisItem !=null)
					slot.setItem(null);
				slot.setItem(slotItem);
				return false;
			}
			return true;
		}
		else{
			
			this.setItem(slotItem);
			slot.setItem(null);
	
			if(!isEquipmentLegal()){

				this.setItem(null);
				slot.setItem(slotItem);
				return false;
			}
			return true;
		}
	}
	
	public void handleInput(){
		if(getItem() != super.getItem()) //ifall ett item hoppar ner i listan
			initItemPicture();
		
		super.setItem(getItem());
		
		super.handleInput();
		

	}
	
	public GItem getItem(){
		if(equipmentNumber < owner.getEquipments().size()) //finns equipmentet;
			return owner.getEquipments().get(equipmentNumber);
		else
			return null;
	}
	
	public boolean isEquipmentLegal(){
		return owner.isEquipmentLegal();
	}
	
	public Hamburger getOwner(){
		return owner;
	}

}
