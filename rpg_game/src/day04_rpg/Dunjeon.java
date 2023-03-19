package day04_rpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dunjeon {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();

//	private Guild guild = new Guild();
	private int partySize;
	private int monsterSize = 4;
	private String[] mName = { "스탈린", "히틀러", "오바마", "푸틴", "김정은", "김일성" };
	private int[] mAtt = { 2, 5, 7, 10, 12, 15 };
	private int[] mDef = { 1, 2, 3, 4, 10, 5 };
	private int[] mHp = { 50, 60, 70, 30, 80, 100 };
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();

	public ArrayList<Unit> guildList = new ArrayList<>();

	public ArrayList<Monster> getMonsterList() {
		return Dunjeon.monsterList;
	}

	public void setMonsterList(ArrayList<Monster> monsterList) {
		Dunjeon.monsterList = monsterList;
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
				Dunjeon.monsterList.add(monster);
			}
		}

		figthRun();

	}

	private void printMonster() {
		System.out.println("================던전 몬스터 목록===============");
		for (int i = 0; i < Dunjeon.monsterList.size(); i++) {
			System.out.println((i + 1) + ". 몬스터: " + Dunjeon.monsterList.get(i).getMonName() + "	체력: "
					+ Dunjeon.monsterList.get(i).getMonHp() + " 공격력: " + Dunjeon.monsterList.get(i).getMonAtt()
					+ " 방어력: " + Dunjeon.monsterList.get(i).getMonDef());
		}

	}

	private void printPlayer() {
		int count = 1;

		System.out.println("==================파티원 목록=================");
		for (int i = 0; i < Player.guild.guildList.size(); i++) {
			if (Player.guild.guildList.get(i).getParty()) {

				System.out.println((count++) + ". " + Player.guild.guildList.get(i).getName() + " 체력 : "
						+ Player.guild.guildList.get(i).getHp() + " [공격력 : " + Player.guild.guildList.get(i).getAtt()
						+ "]" + " [방어력 : " + Player.guild.guildList.get(i).getDef() + "] 레벨: "+Player.guild.guildList.get(i).getLevel());
			}

		}

	}

	private int inputNumber() {
		int attNum = Dunjeon.scan.nextInt() - 1;
		return attNum;
	}

	private void figthRun() {
		int playerDead = 0;
		int monsterDead = 0;
		boolean check = false;
		partySize = 0;

		for (int i = 0; i < Player.getGuildList().size(); i++) {
			if (Player.getGuildList().get(i).getParty()) {
				partySize++;
				check = true;
				System.out.println(check);
			}
		}

		if (!check) {
			System.out.println("파티원이 한명 이상이어야 던전에 입장할 수 있습니다.");
		}
		while (check) {
			for (int i = 0; i < Player.getGuildSize(); i++) {
				printMonster();
				printPlayer();

				if (Player.getGuildList().get(i).getParty()) {
					System.out.println("===========" + Player.getGuildList().get(i).getName() + "님 턴입니다===========");
					System.out.println("공격할 몬스터를 선택하세요. (0)던전 나가기");
					int attNum = inputNumber();
					if(attNum == -1) {
						System.out.println("던전을 포기합니다...");
						check = false;
					}
				
					for (int j = 0; j < this.monsterSize; j++) {
						if (attNum == j) {
							if ((Dunjeon.monsterList.get(j).getMonHp() - Player.getGuildList().get(i).getAtt()) < 1) {
								System.out.println(Dunjeon.monsterList.get(j).getMonName() + " 처치 성공!!!!");
								Player.getGuildList().get(i).setExp(Player.getGuildList().get(i).getExp()+100);
							
								if(Player.getGuildList().get(i).getExp() % 100 == 0) {
									Player.getGuildList().get(i).setLevel(Player.getGuildList().get(i).getLevel()+1);
								}
								Dunjeon.monsterList.remove(j);
								System.out.println("몬스터 숫자: " + this.monsterSize);
								this.monsterSize--;
							} else {
								Dunjeon.monsterList.get(j).setMonHp(
										Dunjeon.monsterList.get(j).getMonHp() - Player.getGuildList().get(i).getAtt());
							}
						}
					}
				}

			}
			if(check) {
			System.out.println("this.monsterSize: " + this.monsterSize);
			for (int i = 0; i < this.monsterSize; i++) { // 몬스터가 죽었는데 사이즈가 줄지않아 인덱스 에러
				printMonster();
				printPlayer();
				System.out.println("=========== " + Dunjeon.monsterList.get(i).getMonName() + "의 공격이 시작됩니다. ===========");
				int attNum = ran.nextInt(partySize);

					if(i == 0) {System.out.println(Dunjeon.monsterList.get(i).getMonName()+": ★★에너르기 파★★ [후훗 맛좀 봐라!]");
					}else if(i == 1) {
						System.out.println(Dunjeon.monsterList.get(i).getMonName()+": ★★와카라디스★★ [이것도 막아낼 수 있을까?]");
					}else if(i == 2) {
						System.out.println(Dunjeon.monsterList.get(i).getMonName()+": ★★ICBM 발사!!★★ [모조리 죽여줄까?]");
					}else if(i == 3) {
						System.out.println(Dunjeon.monsterList.get(i).getMonName()+": ★★핵공격★★ [받으라우! 남조선 간나] ");
					}
					System.out.println(Player.getGuildList().get(attNum).getName() +"님이 "+Dunjeon.monsterList.get(i).getMonName()+"의 공격을 받았습니다.");
				
					
				for (int j = 0; j < partySize; j++) {
					int critiNum = ran.nextInt(5);
					critiNum = critiNum == 2 ? 2 : 1;

					if (attNum == j) {
						if (critiNum == 2) {
							System.err.println(Dunjeon.monsterList.get(i).getMonName() + "의 ★★★크리티컬 히트★★★");
						}
						System.err.println("데미지: "+Dunjeon.monsterList.get(i).getMonAtt() * critiNum );
						
						Player.getGuildUnit(j).setHp(Player.getGuildList().get(j).getHp()
								- Dunjeon.monsterList.get(i).getMonAtt() * critiNum);
						if (Player.getGuildUnit(j).getHp() < 1) {
							System.out.println(Player.getGuildUnit(j).getName() + " 의 사망소식...");
							partySize--;
						}
					}
					try {

						Thread.sleep(650);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
			System.out.println("partySize : " + partySize);
			if (playerDead == partySize) {
				System.out.println("모든 플레이어가 죽었습니다...던전 실패..");
				check = false;
			}
			if (monsterDead == this.monsterSize) {
				System.out.println("모든 몬스터 사냥에 성공하였습니다.  던전 클리어!!");
				check = false;
			}
		
		}

	}

}
