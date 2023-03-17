package day04_rpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dunjeon {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();

	private Player player;

	private final int monsterSize = 4;
	private int count = 0;
	private String[] mName = { "스탈린", "히틀러", "오바마", "푸틴", "김정은", "김일성" };
	private int[] mAtt = { 2, 5, 7, 10, 12, 15 };
	private int[] mDef = { 1, 2, 3, 4, 10, 5 };
	private int[] mHp = { 50, 60, 70, 30, 80, 100 };
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();

	public ArrayList<Unit> guildList = new ArrayList<>();

	public ArrayList<Monster> getMonsterList() {
		return this.monsterList;
	}

	public void setMonsterList(ArrayList<Monster> monsterList) {
		this.monsterList = monsterList;
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
				Monster monster = new Monster(mName[i], mAtt[i], mDef[i], mHp[i]);
				this.monsterList.add(monster);
			}
		}

		figthRun();

	}

	private void printMonster() {
		System.out.println("================던전 몬스터 목록===============");
		for (int i = 0; i < this.monsterList.size(); i++) {
			System.out.println((i + 1) + ". 몬스터: " + this.monsterList.get(i).getMonName() + "	체력: "
					+ this.monsterList.get(i).getMonHp() + " 공격력: " + this.monsterList.get(i).getMonAtt() + " 방어력: "
					+ this.monsterList.get(i).getMonDef());
		}

	}

	private void printPlayer() {
		int count = 1;
	
		System.out.println("==================파티원 목록=================");
		for (int i = 0; i < this.player.guild.guildList.size(); i++) {
			if(this.player.guild.guildList.get(i).getParty()) {
			System.out.println((count++) + ". " + this.player.guild.guildList.get(i).getName() + " 체력 : "
					+ this.player.guild.guildList.get(i).getHp() + " [공격력 : "
					+ this.player.guild.guildList.get(i).getAtt() + "]" + " [방어력 : "
					+ this.player.guild.guildList.get(i).getDef() + "]");
			}

		}

	}

	private int inputNumber() {
		int attNum = this.scan.nextInt() - 1;
		return attNum;
	}

	private void figthRun() {
		int playerDead = 0;
		int monsterDead = 0;

		while (true) {
			for (int i = 0; i < this.player.getGuildSize(); i++) {
				printMonster();
				printPlayer();
				System.out.println("===========" + this.player.getGuildList().get(i).getName() + "님 턴입니다===========");
				System.out.println("공격할 몬스터를 선택하세요.");
				int attNum = inputNumber();
				for (int j = 0; j < this.monsterSize; j++) {
					if (attNum == j) {
						System.out.println("데미지: " + this.player.getGuildList().get(i).getAtt());
						if ((this.monsterList.get(j).getMonHp() - this.player.getGuildList().get(i).getAtt()) < 1) {
							System.out.println(this.monsterList.get(j).getMonName()+" 처치 성공!!!!");
							this.monsterList.remove(j);
							System.out.println("몬스터 숫자: "+this.monsterSize);
						} else {
							this.monsterList.get(j).setMonHp(
									this.monsterList.get(j).getMonHp() - this.player.getGuildList().get(i).getAtt());
						}
					}
				}

			}
			for (int i = 0; i < this.monsterSize; i++) { // 몬스터가 죽었는데 사이즈가 줄지않아 인덱스 에러
				printMonster();
				printPlayer();
				System.out.println("===========" + this.monsterList.get(i).getMonName() + "의 공격이 시작됩니다..");
				int attNum = ran.nextInt(this.player.getGuildSize());
				for (int j = 0; j < this.player.getGuildSize(); j++) {
					int critiNum = ran.nextInt(5);
					critiNum = critiNum == 2 ? 2 : 1;

					if (attNum == j) {
						if (critiNum == 2) {
							System.out.println(this.monsterList.get(i).getMonName() + "의 크리티컬 히트!!!!!");
						}
						System.out.println("데미지: " + this.monsterList.get(i).getMonAtt() * critiNum);
						this.player.getGuildUnit(j).setHp(this.player.getGuildList().get(j).getHp()
								- this.monsterList.get(i).getMonAtt() * critiNum);
					}
					try {

						Thread.sleep(650);
					} catch (InterruptedException e) {
						e.printStackTrace();
						// TODO: handle exception

					}
				}

			}
			if (playerDead == this.player.getGuildSize()) {
				System.out.println("모든 플레이어가 죽었습니다...던전 실패..");
				break;
			}
			if (monsterDead == this.monsterSize) {
				System.out.println("모든 몬스터 사냥에 성공하였습니다.  던전 클리어!!");
				break;
			}
		}

	}

}
