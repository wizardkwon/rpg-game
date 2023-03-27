package day04_rpg;

public class Unit {
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	private int exp;
	private int maxExp;
	private boolean party;
	private Item weapon;
	private Item armor;
	private Item ring;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHp() {
		return this.hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxHp() {
		return this.maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getAtt() {
		return this.att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getDef() {
		return this.def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getExp() {
		return this.exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMaxExp() {
		return this.maxExp;
	}
	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}
	public boolean getParty() {
		return this.party;
	}
	public void setParty(boolean party) {
		this.party = party;
	}
	public Item getWeapon() {
		return this.weapon;
	}
	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}
	
	public Item getArmor() {
		return this.armor;
	}
	public void setArmor(Item armor) {
		this.armor = armor;
	}
	public Item getRing() {
		return this.ring;
	}
	public void setRing(Item ring) {
		this.ring = ring;
	}
	
	

	public Unit(String n, int l, int h, int a, int d, int e, int m) {
		name = n;
		level = l;
		maxHp = h;
		att = a;
		def = d;
		exp = e;
		maxExp = m;
		hp = maxHp;
		party = false;
		weapon = null;
		armor = null;
		ring = null;
	}

	public Unit(String n, int l, int h, int a, int d, int e,int m, boolean p) {
		name = n;
		level = l;
		maxHp = h;
		att = a;
		def = d;
		exp = e;
		maxExp = m;
		hp = maxHp;
		party = p;
		weapon = null;
		armor = null;
		ring = null;
	}

	public void setItem(Item w, Item a, Item r) {
		weapon = w;
		armor = a;
		ring = r;
	}

	public void printStatus() {
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + level + "]");
		if (ring != null) {
			System.out.print(" [체력 : " + (hp-ring.getPower()) + " + " + ring.getPower());
		} else {
			System.out.print(" [체력 : " + hp);
		}
		if (ring != null) {
			System.out.println(" / " + (maxHp-ring.getPower()) + " + " + ring.getPower() + "]");
		} else {
			System.out.println(" / " + maxHp + "]");
		}
		if (weapon != null) {
			System.out.print("[공격력 : " + (att-weapon.getPower()) + " + " + weapon.getPower() + "]");
		} else {
			System.out.print("[공격력 : " + att + "]");
		}
		if (armor != null) {
			System.out.print(" [방어력 : " + (def-armor.getPower()) + " + " + armor.getPower() + "]");
		} else {
			System.out.print(" [방어력 : " + def + "]");
		}
		System.out.println(" [파티중 : " + party + "]");
	}

	public void printEquitedItem() {
		if (weapon == null) {
			System.out.println("[무기 : 없음 ]");
		} else {
			System.out.println("[무기 : " + weapon.getName() + "]");
		}
		if (armor == null) {
			System.out.println("[방어구 : 없음 ]");
		} else {
			System.out.println("[방어구 : " + armor.getName() + "]");
		}
		if (ring == null) {
			System.out.println("[반지 : 없음 ]");
		} else {
			System.out.println("[반지 : " + ring.getName() + "]");
		}
	}
}