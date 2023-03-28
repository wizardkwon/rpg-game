package day04_rpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Guild {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	private final int PARTY_SIZE = 4;
	public ArrayList<Unit> guildList = new ArrayList<>();
	private Unit[] partyList;
	private int partyCount=0;
	
	public Guild(int partyCount) {
		this.partyCount = partyCount;
	}
	public Guild() {
	}
	public int getPartyCount() {
		return this.partyCount;
	}
	public void setPartyCount(int partyCount) {
		this.partyCount = partyCount;
	}
	public Unit[] getPartyList() {
		return this.partyList;
	}
	public void setPartyList(Unit[] partyList) {
		this.partyList = partyList;
	}

	public void setGuild() {
		Unit temp1 = new Unit("권기철", 1, 100, 20, 50, 0 , 100);
		Unit temp2 = new Unit("장영재", 1, 100, 20, 5, 0, 100);
		Unit temp3 = new Unit("임종현", 1, 100, 20, 5, 0, 100);
		Unit temp4 = new Unit("joker", 1, 100, 20, 5, 0, 100);
		guildList.add(temp1);
		guildList.add(temp2);
		guildList.add(temp3);
		guildList.add(temp4);
		int index = 0;
		for(int i=0; i<PARTY_SIZE;i++) {
			guildList.get(index++).setParty(true);
			this.partyCount++;
			
		}
		partyList = new Unit[4];
		int n = 0;
		for (int i = 0; i < this.partyCount; i++) {
			if (guildList.get(i).getParty() == true) {
				partyList[n] = guildList.get(i);
				n += 1;
			}
		}

	}

	public Unit getGuildUnit(int num) {
		return guildList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < guildList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + guildList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + guildList.get(i).getLevel() + "]");
			System.out.print(" [체력 : " + guildList.get(i).getHp());
			System.out.println(" / " + guildList.get(i).getMaxHp() + "]");
			System.out.print("[공격력 : " + guildList.get(i).getAtt() + "]");
			System.out.print(" [방어력 : " + guildList.get(i).getDef() + "]");
			System.out.println(" [파티중 : " + guildList.get(i).getParty() + "]");
			System.out.println("");
		}
		System.out.println("=================================");
	}

	public void printUnitStaus(int num) {
		guildList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		guildList.get(num).printEquitedItem();
	}

	public void buyUnit() {
		if (Player.money < 5000)
			return;
		String[] n1 = { "장", "이", "권", "최", "임", "지", "오" };
		String[] n2 = { "명", "기", "종", "상", "영", "정", "광" };
		String[] n3 = { "수", "현", "식", "재", "석", "민", "철" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0, 100);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println(" [경험치 : " + 0 + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		guildList.add(temp);
		Player.money -= 5000;
	}

	public void removeUnit() {
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();
		if (sel >= 0 && sel < this.guildList.size()) {
			if (guildList.get(sel - 1).getParty()) {
				System.out.println("파티중인 멤버는 삭제할수 없습니다.");
			} else {
				System.out.println("=================================");
				System.out.print("[이름 : " + guildList.get(sel - 1).getName() + "]");
				System.out.println("길드원을 삭제합니다.");
				System.out.println("=================================");
				guildList.remove(sel - 1);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("올바른 길드원을 선택하세요.");
		}
	}

	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < 4; i++) {
			
				System.out.print("[" + (i + 1) + "번]");
				System.out.print(" [이름 : " + partyList[i].getName() + "]");
				System.out.print(" [레벨 : " + partyList[i].getLevel() + "]");
				System.out.print(" [체력 : " + partyList[i].getHp());
				System.out.println(" / " + partyList[i].getMaxHp() + "]");
				System.out.print("[공격력 : " + partyList[i].getAtt() + "]");
				System.out.print(" [방어력 : " + partyList[i].getDef() + "]");
				System.out.println(" [파티중 : " + partyList[i].getParty() + "]");
				System.out.println("");
			
		}
		System.out.println("=====================================");
	}

	public void partyChange() {
		if(guildList.size() > 4) {
			printParty();
			System.out.println("교체할 번호를 입력하세요 ");
			int partyNum = MainGame.scan.nextInt();
			printAllUnitStaus();
			System.out.println("참가할 번호를 입력하세요 ");
			int guildNum = MainGame.scan.nextInt();
			System.out.println(guildList.get(guildNum-1).getParty());
			if(!guildList.get(guildNum-1).getParty()) {
			partyList[partyNum - 1].setParty(false);
			
			guildList.get(guildNum - 1).setParty(true);
			
			
			System.out.println("====================================");
			System.out.print("[이름 : " + partyList[partyNum - 1].getName() + "]");
			System.out.print("에서 ");
			System.out.print("[이름 : " + guildList.get(guildNum - 1).getName() + "]");
			System.out.println("으로 교체 합니다. ");
			System.out.println("====================================");
			partyList = new Unit[4];
			int n = 0;
			for (int i = 0; i < guildList.size(); i++) {
				if (guildList.get(i).getParty()) {
					partyList[n] = guildList.get(i);
					n += 1;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}else{
			System.out.println(guildList.get(guildNum-1).getName()+"님은 이미 파티 중입니다.");
			}
			
		}else {
			System.out.println("교체할 파티인원이 없습니다.");
		}
	}

	private void partyAdd() {
		if (this.partyCount < 4) {
			for (int i = 0; i < this.guildList.size(); i++) {
				System.out.println((i + 1) + ". " + "Level: " + this.guildList.get(i).getLevel() + "/ 이름: "
						+ this.guildList.get(i).getName() + "/ 체력: " + this.guildList.get(i).getMaxHp() + "/ 공격력: "
						+ this.guildList.get(i).getAtt() + "/ 방어력: " + this.guildList.get(i).getDef() + " 파티:"
						+ this.guildList.get(i).getParty());
			}

			int index = choiceUnit();
			if (index >= 0 && index < this.guildList.size()) {
				if (!guildList.get(index).getParty()) {
					guildList.get(index).setParty(true);
					this.partyCount++;
				} else {
					System.out.println("이미 파티에 참여중인 길드입니다.");
				}
				System.out.println("=====현재 파티원=====");
				for (int i = 0; i < this.guildList.size(); i++) {
					if (this.guildList.get(i).getParty()) {
						System.out.println((i + 1) + ". level: " + this.guildList.get(i).getLevel() + " "
								+ this.guildList.get(i).getName());
					}
				}
				
				
			} else {
				System.out.println("유닛 번호를 다시 확인하세요.");
			}
		} else {
			System.out.println("파티원이 가득 찼습니다.");
		}

	}

	private void partyDel() {
		if (this.partyCount > 0) {
			System.out.println("=====현재 파티원=====");
			for (int i = 0; i < this.guildList.size(); i++) {
				if (this.guildList.get(i).getParty()) {
					System.out.println((i + 1) + ". level: " + this.guildList.get(i).getLevel() + " "
							+ this.guildList.get(i).getName());
				}
			}

			int index = choiceUnit();
			if (index >= 0 && index < this.guildList.size()) {
				guildList.get(index).setParty(false);
				this.partyCount--;
			} else {
				System.out.println("올바른 파티원을 선택하세요.");
			}
			
		} else {
			System.out.println("참여한 파티원이 없습니다.");
		}
	}

	private int choiceUnit() {
		System.out.println("길드원을 선택하세요.");
		int unitIndex = this.scan.nextInt() - 1;
		return unitIndex;

	}

	private void partySort() {
		Unit temp;
		for (int i = 0; i < this.guildList.size(); i++) {
			for (int j = 0; j < this.guildList.size(); j++) {
				if (this.guildList.get(i).getName().compareTo(this.guildList.get(j).getName()) < 0) {
					temp = this.guildList.get(i);
					this.guildList.set(i,  this.guildList.get(j));
					this.guildList.set(j, temp);
				}
			}
		}
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out
					.println("[1.길드목록] [2.길드원추가] [3.길드원삭제]\n" + "[4.파티원 추가] [5.파티원삭제] [6.파티원교체] [7.정렬]\n" + "[0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				removeUnit();
			} else if (sel == 4) {
				partyAdd();
			} else if (sel == 5) {
				partyDel();
			} else if (sel == 6) {
				partyChange();
			} else if (sel == 7) {
				partySort();
			} else if (sel == 0) {
				break;
			}
		}
	}

}