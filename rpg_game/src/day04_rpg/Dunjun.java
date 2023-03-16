package day04_rpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dunjun {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	
	private Guild guild = new Guild();
	
	
	
	
	private final int monsterSize = 4;
	private int count = 0;
	private String[] mName = { "프랑켄", "디아블로", "바알", "벌쳐", "김정은", "김일성" };
	private int[] mAtt = { 2, 5, 7, 10, 12, 15 };
	private int[] mDef = { 1, 2, 3, 4, 10, 5 };
	private int[] mCriti = { 4, 10, 15, 20, 25, 30 };
	private int[] mHp = { 50, 60, 70, 30, 80, 100 };
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();
	public ArrayList<Unit> guildList = new ArrayList<>();

	public ArrayList<Monster> getMonsterList() {
		return this.monsterList;
	}

	public void setMonsterList(ArrayList<Monster> monsterList) {
		this.monsterList = monsterList;
	}
	
	public ArrayList<Unit> getGuildList() {
		return this.guildList;
	}

	public void setGuildList(ArrayList<Unit> guildList) {
		this.guildList = guildList;
	}



	public void init() {
		
		int[] temp = new int[2];
		boolean[] monCheck = new boolean[6];

		for (int i = 0; i < 2; i++) {
			int ranNum = ran.nextInt(6);
			if (!monCheck[ranNum]) {
				monCheck[ranNum] = true;
				temp[i] = ranNum;
			} else {
				i--;
			}
		}
		for (int i = 0; i < monCheck.length; i++) {
			if (!monCheck[i]) {
				Monster monster = new Monster(mName[i], mAtt[i], mDef[i], mCriti[i], mHp[i]);
				this.monsterList.add(monster);
			}
		}
		System.out.println("	★던전 몬스터 출현★");
		for (int i = 0; i < this.monsterList.size(); i++) {
			System.out.println(this.monsterList.get(i).getMonName()+ "	체력: " 
							+ this.monsterList.get(i).getMonHp() + " 공격력: " 
							+ this.monsterList.get(i).getMonAtt() + " 방어력: "
							+ this.monsterList.get(i).getMonDef() + " 치명타: " 
							+ this.monsterList.get(i).getMonCriticalAtt()
							);
		}
		printPlayer();
	}

	private void printPlayer() {

		System.out.println("플레이어: "+this.guild.guildList.size());
		for(int i=0; i<this.guild.guildList.size();i++) {
			if(this.guild.guildList.get(i).getParty()) {
			System.out.println(" [이름 : " + guildList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + guildList.get(i).getLevel() + "]");
			System.out.print(" [체력 : " + guildList.get(i).getHp());
			System.out.println(" / " + guildList.get(i).getMaxHp() + "]");
			System.out.print("[공격력 : " + guildList.get(i).getAtt() + "]");
			System.out.print(" [방어력 : " + guildList.get(i).getDef() + "]");
			}
		}
		
	}

}
