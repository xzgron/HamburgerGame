package world.objects.ingredients.bases;

import java.util.LinkedList;

import world.objects.GIngredient;

public class OverPartIngredient extends GIngredient {

	public OverPartIngredient(float xPos, float yPos, float texWidth,
			float texHeight, String texture, float footPos, float headPos,
			float radius, int weight, int durability) {
		super(xPos, yPos, texWidth, texHeight, texture, footPos, headPos, radius,
				weight, durability);
	}

	public boolean isPositionLegal(LinkedList<GIngredient> list){
		return (list.indexOf(this) == list.size()-1);
	}
}
