package game.HUDMaterial;

import java.util.LinkedList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import controllers.Controlls;

import world.objects.GFood;
import world.objects.GIngredient;
import world.objects.GItem;
import world.objects.food.Hamburger;
import world.objects.ingredients.interfaces.Activateable;

import game.GamePart;
import game.Main;
import game.input.GKeyboard;
import game.input.GMouse;
import game.parts.GameWorld;

public class PlayerActionbar {

	int selectedSlot = 1;

	InventorySlot[] slots = new InventorySlot[10];

	float xPos = Display.getWidth() / 2;
	float yPos = Display.getHeight() - 70;

	float slotSize = 70;
	float itemSize = 60;

	public PlayerActionbar() {
		for (int i = 0; i < slots.length; i++)
			slots[i] = new InventorySlot(xPos - slotSize * slots.length / 2 + i
					* slotSize + slotSize / 2, yPos, slotSize, itemSize,
					"buttons/bars");

	}

	public void handleInput() {
		// minus selectedSlot är den avstängda slotten.
		
		int prevSelectedSlot = selectedSlot;
		
		int vissibleSlots = 0;
	
		for(int i = 0; i < slots.length; i++){ //räknar ut antalet vissible slots
			if(slots[i].getItem() == null){
				vissibleSlots = i;
				break;
				}
		}
		
		for(int i = 0; i < vissibleSlots; i++){ //clicka
			if(slots[i].isClicked(GMouse.BUTTON_LEFT)){
				selectedSlot = i+1;
				if(selectedSlot == prevSelectedSlot)
					selectedSlot = -selectedSlot;
				}
		}

		
		if(GMouse.getDWheel() < 0 /*&& selectedSlot > 0*/){
			selectedSlot += Controlls.ACTIONBAR_SCROLL_DIRECTION;
			if(selectedSlot < 1)
				selectedSlot = vissibleSlots;
			if(selectedSlot > vissibleSlots)
				selectedSlot = 1;
			}
		else if(GMouse.getDWheel() > 0 /*&& selectedSlot > 0*/){
			selectedSlot -= Controlls.ACTIONBAR_SCROLL_DIRECTION;
			if(selectedSlot < 1)
				selectedSlot = vissibleSlots;
			if(selectedSlot > vissibleSlots)
				selectedSlot = 1;
		}
		if(GMouse.isButtonPressed(GMouse.BUTTON_WHEEL))
			selectedSlot = -selectedSlot;
			
		
		if (GKeyboard.isKeyPressed(Keyboard.KEY_1) && slots[0].getItem() != null){
			selectedSlot = 1;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_2) && slots[1].getItem() != null){
			selectedSlot = 2;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_3)&& slots[2].getItem() != null){
			selectedSlot = 3;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_4)&& slots[3].getItem() != null){
			selectedSlot = 4;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_5)&& slots[4].getItem() != null){
			selectedSlot = 5;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_6)&& slots[5].getItem() != null){
			selectedSlot = 6;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_7)&& slots[6].getItem() != null){
			selectedSlot = 7;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_8)&& slots[7].getItem() != null){
			selectedSlot = 8;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_9)&& slots[8].getItem() != null){
			selectedSlot = 9;}
		else if (GKeyboard.isKeyPressed(Keyboard.KEY_0)&& slots[9].getItem() != null){
			selectedSlot = 10;}
		else
			return;
		
		//om en knapp var klickad på.
		if (prevSelectedSlot == selectedSlot)
			selectedSlot = -selectedSlot;
	}

	public void update() {// updaterar vilka slots som är synliga och var de ska
							// vara

		Hamburger player = Main.game.world.getPlayer();

		// Detta ger oss en upp och ner vänd lista av players alla activerabara
		// ingredienser.
		LinkedList<GIngredient> sortedIngredients = new LinkedList<GIngredient>();
		for (int i = player.getEquipments().size() - 1; i >= 0; i--)
			if (player.getEquipments().get(i) instanceof Activateable)
				sortedIngredients.add(player.getEquipments().get(i));
		// ///////////////////////////////

		for (int i = 0; i < slots.length; i++) {
			if (i < sortedIngredients.size()) {
				slots[i].setPosition(xPos - slotSize * sortedIngredients.size() / 2 + i * (slotSize + 20) + slotSize / 2, yPos);
				slots[i].setItem(sortedIngredients.get(i));
				
				if (selectedSlot == i+1)
					slots[i].setColor(1f, 1f, 0.3f, 0.9f);
				else
					slots[i].setColor(0.9f, 0.9f, 0.9f, 0.9f);
			}
			else
				slots[i].setItem(null);
		}
	}

	public void render() {
		for (InventorySlot slot : slots)
			if (slot.getItem() != null)
				slot.render();
	}
	
	public GItem getSelectedItem(){
		return slots[selectedSlot-1].getItem();
	}
}
