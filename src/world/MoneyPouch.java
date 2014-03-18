package world;

import world.objects.GItem;

public class MoneyPouch {
	private int money = 0;
	
	
	public boolean buy(GItem item){
		return buy(item.getCost());
	}
	
	public boolean buy(int cost){
		if(money - cost > 0){
			money -= cost;
			return true;
			}
		else
			return false;
	}
	
	public void sell(GItem item){
		loot(item.getCost());
	}
	
	public void loot(int money){
		this.money += money;
	}
	
	public void setMoney(int money){
		this.money = money; 
	}
	
	public int getMoney(){
		return money;
	}
}
