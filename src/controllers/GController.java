package controllers;

import game.parts.GameWorld;

import java.util.LinkedList;

import world.objects.food.GFood;

public abstract class GController {

	public abstract void handle(GFood food);

}
