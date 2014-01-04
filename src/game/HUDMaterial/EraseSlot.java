package game.HUDMaterial;

import world.objects.GItem;

public class EraseSlot extends InventorySlot{

	public EraseSlot(float x, float y, float size, float itemSize,
			String texture) {
		super(x, y, size, itemSize, texture);
	}
	
	public boolean switchItem(InventorySlot slot){ //returnerar om det gick eller inte
		//denna metod körs av slotten som får något släppt på sig.
		GItem slotItem = slot.getItem(); 
		// viktigt att temp item inte är null för skulle en eq slot ge tillbaka itemet över sig
		
		if(slot instanceof HamburgerEquipmentSlot){
			slot.setItem(null);
			if(!((HamburgerEquipmentSlot)slot).isEquipmentLegal()){
				slot.setItem(slotItem);
				return false;
				}
			
			this.setItem(slotItem);
			return true;
		}
		else{
			this.setItem(slotItem);
			slot.setItem(null);
			return true;
		}
	}
		
}
