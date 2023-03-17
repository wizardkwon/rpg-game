package day04_rpg;

public class Monster {
	private String monName;
	private int monAtt;
	private int monDef;
	private int monHp;
	
	public Monster(String monName, int monAtt, int monDef,  int monHp) {
		this.monName = monName;
		this.monAtt = monAtt;
		this.monDef = monDef;
		
		this.monHp = monHp;
	}
	public String getMonName() {
		return this.monName;
	}
	public void setMonName(String monName) {
		this.monName = monName;
	}
	
	public int getMonAtt() {
		return this.monAtt;
	}
	public void setMonAtt(int monAtt) {
		this.monAtt = monAtt;
	}
	
	public int getMonDef() {
		return this.monDef;
	}
	public void setMonDef(int monDef) {
		this.monDef = monDef;
	}
	
	public int getMonHp() {
		return this.monHp;
	}
	public void setMonHp(int monHp) {
		this.monHp = monHp;
	}
	
	
}
