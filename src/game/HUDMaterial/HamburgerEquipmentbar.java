package game.HUDMaterial;

import game.Main;
import game.input.GMouse;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;

import world.Player;
import world.objects.food.Hamburger;

public class HamburgerEquipmentbar {
	LinkedList<HamburgerEquipmentSlot> slots = new LinkedList<HamburgerEquipmentSlot>();	
	
	float slotSize = 60;
	float itemSize = 50;
	
	float xPos;
	float yPos;
	
	Player owner;
	
	public HamburgerEquipmentbar(float xPos, float yPos, Player owner){
		this.xPos = xPos;
		this.yPos = yPos;
		this.owner = owner;
		
		for(int i = 0; i <= owner.getEquipments().size(); i++){
			slots.add(new HamburgerEquipmentSlot(xPos, yPos - i*slotSize, slotSize, itemSize, "buttons/bars", i, owner));
			slots.get(i).setColor(0.2f, 0.7f, 0.2f, 0.9f);
		}
		
	}
	
	public void handleInput(){
		if(GMouse.getDWheel() < 0){
			yPos += 5;
			for(HamburgerEquipmentSlot slot: slots)
				slot.move(0,5);
		}
		if(GMouse.getDWheel() > 0){
			yPos -= 5;
			for(HamburgerEquipmentSlot slot: slots)
				slot.move(0,-5);
		}
		
		for(HamburgerEquipmentSlot slot: slots)
			slot.handleInput();
	}
	
	public void update(){
		int sizeDifferent = slots.size() - owner.getEquipments().size(); // hur många mer platser har slots än equipments
		
		if(sizeDifferent < 1){ //equipments är fler än slots+1 och vi behöver fler slots
			for(int i = slots.size(); i <= owner.getEquipments().size(); i++){ //vi ska ha en mer slot än equipments
				slots.add(new HamburgerEquipmentSlot(xPos, yPos - i*slotSize, slotSize, itemSize, "buttons/bars", i, owner));
				slots.get(i).setColor(0.2f, 0.7f, 0.2f, 0.9f);
			}
		}
		else if(sizeDifferent > 1){ //equipments har färre platser slots+1 och slots behöver tas bort.
			for(int i = 0; i < sizeDifferent-1; i++)
				slots.removeLast();
		}

		
		for(HamburgerEquipmentSlot slot: slots)
			slot.update();
	}
	
	public void render(){
		for(HamburgerEquipmentSlot slot: slots)
			slot.render();
	}
}
