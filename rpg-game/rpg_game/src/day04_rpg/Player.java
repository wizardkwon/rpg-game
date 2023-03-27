package day04_rpg;

import java.util.ArrayList;

public class Player {
	public static int money;
	public static Guild guild = new Guild();
	public static Inventory inven = new Inventory();
	
 
	Player() {
		money = 100000;
		guild.setGuild();
	}
	
//	public int getMoney() {
//		return this.money;
//	}
//	public void setMoney(int money) {
//		this.money = money;
//	}

	public void guildMenu() {
		guild.guildMenu();
	}

	public void inventoryMenu() {
		inven.inventoryMenu();
	}

	static public ArrayList<Unit> getGuildList() {
		return guild.guildList;
	}

	static public ArrayList<Item> getItemList() {
		return inven.itemList;
	}

	static public Unit getGuildUnit(int num) {
		return guild.getGuildUnit(num);
	}

	static public int getGuildSize() {
		return guild.guildList.size();
	}

	static public int getItemSize() {
		return inven.itemList.size();
	}
}