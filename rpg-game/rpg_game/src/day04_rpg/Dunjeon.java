package day04_rpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dunjeon {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	public static Inventory inven = new Inventory();
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();
	public ArrayList<Item> itemList = new ArrayList<>();

	public ArrayList<Monster> getMonsterList() {
		return Dunjeon.monsterList;
	}

	public void setMonsterList(ArrayList<Monster> monsterList) {
		Dunjeon.monsterList = monsterList;
	}

	private int dunjeonLv = 1;
	private int tempDunLv = MainGame.getDunjeonLevel();
	private int partySize;
	private int monsterSize;

	private String[] mName;
	private int[] mAtt;
	private int[] mDef;
	private int[] mHp;
	public void init() {
		System.out.println("1. 크로메데우스의 던전 (추천 레벨: 1~5) ");
		System.out.println("2. 야생의 숲 (추천 레벨: 5~10)");
		System.out.println("3. 우미관 (추천 레벨: 7~15)");
		System.out.println("4. 지구 (추천 레벨: 15~20)");
		int sel = scan.nextInt();
		if(sel == 1) {
			 this.mName = new String[]{ "타락 성직자", "유혹의 마법사", "동굴 사냥꾼", "동굴 관리자", "동굴 일꾼", "신전 문지기" };
			 this.mAtt = new int[]{ 20, 20, 15, 15, 20, 20 };
			 this.mDef = new int[]{ 1, 2, 3, 4, 10, 5 };
			 this.mHp = new int[]{ 50, 60, 60, 50, 70, 80 };
		}else if(sel == 2) {
			this.mName = new String[]{ "포악한 토끼", "상냥한 코끼리", "늙은 호랑이", "얼음 늑대", "비정한 유니콘", "날쌘 표범" };
			 this.mAtt = new int[]{ 20, 20, 15, 15, 20, 20 };
			 this.mDef = new int[]{ 1, 2, 3, 4, 10, 5 };
			 this.mHp = new int[]{ 50, 60, 60, 50, 70, 80 };
		}else if(sel == 3) {
			this.mName = new String[]{ "삼수", "개코", "번개", "구마적", "문영철", "김무옥" };
			 this.mAtt = new int[]{ 20, 20, 15, 15, 20, 20 };
			 this.mDef = new int[]{ 1, 2, 3, 4, 10, 5 };
			 this.mHp = new int[]{ 50, 60, 60, 50, 70, 80 };
		}else if(sel == 4) {
			this.mName = new String[]{ "스탈린", "히틀러", "오바마", "푸틴", "김정은", "김일성" };
			 this.mAtt = new int[]{ 20, 20, 15, 15, 20, 20 };
			 this.mDef = new int[]{ 1, 2, 3, 4, 10, 5 };
			 this.mHp = new int[]{ 50, 60, 60, 50, 70, 80 };
		}
		this.monsterSize = 4;
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
		
		if (this.tempDunLv == this.dunjeonLv) {
			for (int i = 0; i < Dunjeon.monsterList.size(); i++) {
				Dunjeon.monsterList.get(i).setMonAtt(Dunjeon.monsterList.get(i).getMonAtt() + (this.tempDunLv * 3));
				Dunjeon.monsterList.get(i).setMonDef(Dunjeon.monsterList.get(i).getMonDef() + (this.tempDunLv * 2));
				Dunjeon.monsterList.get(i).setMonHp(Dunjeon.monsterList.get(i).getMonHp() + (this.tempDunLv * 10));

			}
		}
		figthRun();
	}

	private void printMonster() {
		System.out.println("================================던전 몬스터 목록==============================");
		for (int i = 0; i < Dunjeon.monsterList.size(); i++) {
			System.out.println((i + 1) + ". 몬스터: " + Dunjeon.monsterList.get(i).getMonName() + "	체력: "
					+ Dunjeon.monsterList.get(i).getMonHp() + " 공격력: " + Dunjeon.monsterList.get(i).getMonAtt()
					+ " 방어력: " + Dunjeon.monsterList.get(i).getMonDef());
		}

	}

	private void printPlayer() {
		int count = 1;
		System.out.println("==============================파티원 목록====================================");
		for (int i = 0; i < Player.guild.guildList.size(); i++) {
			Unit guildPlayer = Player.guild.guildList.get(i);
			if (guildPlayer.getParty() && guildPlayer.getHp() > 0) {
					System.out.println((count++) + ". " + guildPlayer.getName() + " [체력 : " + guildPlayer.getHp() + "/"
							+ guildPlayer.getMaxHp() + "] " + " [공격력 : " + guildPlayer.getAtt() + "] " + " [방어력 : "
							+ guildPlayer.getDef() + "] [레벨: " + guildPlayer.getLevel() + "] [경험치 : "
							+ guildPlayer.getExp() + "/" + guildPlayer.getMaxExp());
				
			}
		}
		System.out.println("==========================================================================");

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
				if (partySize == 4) {

					check = true;
				}
			}
		}

		if (!check) {
			System.out.println("파티원이 4명이어야 합니다.");
		}

		while (check) {
			printMonster();
			printPlayer();
			ArrayList<Monster> monster = Dunjeon.monsterList;
			for (int i = 0; i < Player.getGuildSize(); i++) {

				if (Player.getGuildList().get(i).getParty() && Player.getGuildList().get(i).getHp() > 0) {
					System.out.println(
							"■■■■■■■■■■■■■■■ " + Player.getGuildList().get(i).getName() + "님 전투시작 ■■■■■■■■■■■■■■■■");
					System.out.println("공격할 몬스터를 선택하세요. (0)던전 나가기");

					int attNum = inputNumber();
					if (this.monsterSize <= attNum) {
						System.out.println("몬스터가 없습니다.");
						continue;
					}
					if (attNum != -1) {
						System.out.println(
								Player.getGuildList().get(i).getName()+": 받아라!! ["+monster.get(attNum).getMonName() + " 체력 -" + Player.getGuildList().get(i).getAtt()+"]");
						System.out.println(monster.get(attNum).getMonName() + "의 남은 체력: "
								+ (monster.get(attNum).getMonHp() - Player.getGuildList().get(i).getAtt()));
						System.out.println("■■■■■■■■■■■■■■■■■■■ 전투종료 ■■■■■■■■■■■■■■■■■■■");
					}
					if (attNum == -1) {
						System.out.println("던전을 포기합니다...");
						check = false;
						break;
					}

					for (int j = 0; j < this.monsterSize; j++) {
						if (attNum == j) {
							if ((monster.get(j).getMonHp() - Player.getGuildList().get(i).getAtt()) < 1) {
								System.out.println(monster.get(j).getMonName() + " 처치 성공!!!!");
								int ranMoneyDrop = ran.nextInt(2);
								if (ranMoneyDrop == 1) {
									int gold = ran.nextInt(2500) + 500;
									Player.money += gold;
									System.out.println(monster.get(j).getMonName() + "의 주머니에서 " + gold + "원 줍줍");
									System.out.println("나의 골드: " + Player.money + "원");

									int item = ran.nextInt(5);
									if (item == 0) {
										int ranItemDrop = ran.nextInt(3) + 1;
										if (ranItemDrop == Item.WEAPON) {
											int weaponNumber = ran.nextInt(3) + 1;
											if (weaponNumber == 0) {
												Item temp = new Item();
												temp.setItem(Item.WEAPON, "청룡언월도", 20, 5000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("청룡언월도를 획득하였습니다.");
											} else if (weaponNumber == 1) {
												Item temp = new Item();
												temp.setItem(Item.WEAPON, "판테온의 창", 15, 3000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("판테온의 창을 획득하였습니다.");

											} else if (weaponNumber == 2) {
												Item temp = new Item();
												temp.setItem(Item.WEAPON, "왕초의 바가지", 25, 15000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("왕초의 바가지를 획득하였습니다.");
											}

										} else if (ranItemDrop == Item.ARMOR) {
											int weaponNumber = ran.nextInt(3);
											if (weaponNumber == 0) {
												Item temp = new Item();
												temp.setItem(Item.ARMOR, "선조의 용포", 10, 4000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("선조의 용포를 획득하였습니다.");
											} else if (weaponNumber == 1) {
												Item temp = new Item();
												temp.setItem(Item.ARMOR, "거지왕초의 누더기", 20, 10000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("거지왕초의 누더기를 획득하였습니다.");
											} else if (weaponNumber == 2) {
												Item temp = new Item();
												temp.setItem(Item.ARMOR, "아기 저고리", 5, 3000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("아기 저고리를 획득하였습니다.");
											}

										} else if (ranItemDrop == Item.RING) {
											int weaponNumber = ran.nextInt(3);
											if (weaponNumber == 0) {
												Item temp = new Item();
												temp.setItem(Item.RING, "옥 가락지", 10, 5000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("옥 가락지를 획득하였습니다.");
											} else if (weaponNumber == 1) {
												Item temp = new Item();
												temp.setItem(Item.RING, "베토벤의 링", 15, 9000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("베토벤의 링을 획득하였습니다.");
											} else if (weaponNumber == 2) {
												Item temp = new Item();
												temp.setItem(Item.RING, "금 가락지", 20, 10000);
												itemList.add(temp);
												Player.inven.addItem(itemList.get(itemList.size() - 1));
												System.out.println("금 가락지를 획득하였습니다.");
											}
										}
									}

								}
								int plusExp = this.dunjeonLv * 10 + 50;
								int plusMaxExp = this.dunjeonLv * 2;
								Unit guildPlayer = Player.getGuildList().get(i);
								guildPlayer.setExp(Player.getGuildList().get(i).getExp() + plusExp);

								if (guildPlayer.getExp() >= guildPlayer.getMaxExp()) {
									guildPlayer.setLevel(guildPlayer.getLevel() + 1);
									guildPlayer.setAtt(guildPlayer.getAtt() + 5);
									guildPlayer.setDef(guildPlayer.getDef() + 3);
									guildPlayer.setMaxHp(guildPlayer.getMaxHp() + 10);
									guildPlayer.setHp(guildPlayer.getMaxHp());
									guildPlayer.setExp(guildPlayer.getExp() - guildPlayer.getMaxExp());
									guildPlayer.setMaxExp(guildPlayer.getMaxExp() * plusMaxExp);
								}
								monster.remove(j);
								System.out.println("몬스터 숫자: " + this.monsterSize);
								this.monsterSize--;

							} else {
								monster.get(j)
										.setMonHp(monster.get(j).getMonHp() - Player.getGuildList().get(i).getAtt());
							}
						}
					}
					if (monsterDead == this.monsterSize) {
						this.dunjeonLv++;
						this.tempDunLv = this.dunjeonLv;

						System.out.println("모든 몬스터 사냥에 성공하였습니다.  던전 클리어!!");
						check = false;
						break;
					}
				}

			}
			if (check) {
				System.out.println("this.monsterSize: " + this.monsterSize);
				printMonster();
				printPlayer();
				for (int i = 0; i < this.monsterSize; i++) { // 몬스터가 죽었는데 사이즈가 줄지않아 인덱스 에러
					String monsterName = monster.get(i).getMonName();
					System.out.println("■■■■■■■■■■■■■■■■■■ " + monsterName + "의 공격. ■■■■■■■■■■■■■■■■■■");
					int attNum = -1;
					// 유닛의 회피를 위해
					if (this.partySize > 2) {
						attNum = ran.nextInt(4);
					} else {
						attNum = ran.nextInt(3);
					}
					Unit unit = Player.getGuildList().get(attNum);
					if (unit.getParty() && unit.getHp() > 0) {

						if (i == 0) {
							System.out.println(monsterName + ": [후훗 맛좀 봐라!]	★★에너르기 파★★");
						} else if (i == 1) {
							System.out.println(monsterName + ": [이것도 막아낼 수 있을까?]	★★와카라디스★★");
						} else if (i == 2) {
							System.out.println(monsterName + ": [다같이 죽자우~!]	★★ICBM 발사★★");
						} else if (i == 3) {
							System.out.println(monsterName + ": [받으라우! 남조선 간나]	★★핵공격★★");
						}
						System.out.println(unit.getName() + "님이 " + monsterName + "의 공격을 받았습니다.");

						int critiNum = ran.nextInt(5);
						critiNum = critiNum == 2 ? 2 : 1;

						if (critiNum == 2) {
							System.err.println(monsterName + "의 ★★★크리티컬 히트★★★");
						}
						System.err.println("데미지: " + monster.get(i).getMonAtt() * critiNum);
						System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
						if (unit.getDef() > 0) {
							Player.getGuildUnit(attNum)
									.setHp(unit.getHp() - (Dunjeon.monsterList.get(i).getMonAtt() * critiNum - 2));
							Player.getGuildUnit(attNum).setDef(unit.getDef() - 2);
							if (Player.getGuildUnit(attNum).getDef() < 0) {
								Player.getGuildUnit(attNum).setDef(0);
							}
						} else {
							Player.getGuildUnit(attNum)
									.setHp(unit.getHp() - Dunjeon.monsterList.get(i).getMonAtt() * critiNum);
						}
						if (Player.getGuildUnit(attNum).getHp() < 1) {
							int loseMoney = ran.nextInt(5000) + 1000;
							System.out.println(
									Player.getGuildUnit(attNum).getName() + "사망하면서 " + loseMoney + "원을 잃어버렸습니다.");
							Player.money -= loseMoney;
							Player.getGuildUnit(attNum).setHp(0);
							if (Player.getGuildUnit(attNum).getExp() - 50 <= 0) {
								Player.getGuildUnit(attNum).setExp(0);
							} else {
								Player.getGuildUnit(attNum).setExp(Player.getGuildUnit(attNum).getExp() - 50);
								;
							}
							partySize--;

						}

						try {

							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else {
						System.out.println(monsterName + "의 miss");
						continue;
					}
					if (playerDead == partySize) {
						System.out.println("모든 플레이어가 죽었습니다...던전 실패..");
						check = false;
						break;
					}
				}
			}

		}

	}

}
