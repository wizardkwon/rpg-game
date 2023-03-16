package day04_rpg;

public class Item {
	public static final int WEAPON = 1;
	public static final int ARMOR = 2;
	public static final int RING = 3;
	private int kind;
	private String name;
	private int power;
	private int price;
	
	public int getKind() {
		return this.kind;
	}
	public String getName() {
		return this.name;
	}
	public int getPower() {
		return this.power;
	}
	public int getPrice() {
		return this.price;
	}
	

	public void setItem(int k, String n, int p, int pr) {
		kind = k;
		name = n;
		power = p;
		price = pr;
	}

}