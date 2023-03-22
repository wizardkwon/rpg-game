package day04_rpg;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Item> itemList = new ArrayList<>();

	public void inventoryMenu() {
		while (true) {
			System.out.println("============ [인벤메뉴] =============");
			System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 0)
				break;
			if (sel == 1) {
				equipMenu();
			}
			if (sel == 2) {
				sellMenu();
			}
		}
	}

	public void equipMenu() {
		Player.guild.printAllUnitStaus();
		System.out.println("아이템 착용할 길드원을 선택하세요 ");
		int selUnit = MainGame.scan.nextInt();
		while (true) {
			if (selUnit != 0) {
				Player.guild.printUnitStaus(selUnit - 1);
				Player.guild.printUnitItem(selUnit - 1);
				printItemList();
				System.out.println("사용할 아이템 번호를 입력하세요 [0.뒤로가기]");
				int selEquip = MainGame.scan.nextInt();
				if (selEquip == 0)
					break;

				selEquip -= 1;
				
				Unit unit = Player.getGuildUnit(selUnit - 1);
				Unit player = Player.getGuildList().get(selUnit - 1);
				
				if (itemList.get(selEquip).getKind() == Item.WEAPON) {
					if (unit.getWeapon() != null) {
						itemList.add(unit.getWeapon());
						Player.guild.guildList.get(selUnit - 1).setAtt((itemList.get(selEquip).getPower()
								+ (player.getAtt() - unit.getWeapon().getPower())));
						unit.setWeapon(itemList.get(selEquip));

					} else {
						unit.setWeapon(itemList.get(selEquip));
						Player.guild.guildList.get(selUnit - 1).setAtt(
								(itemList.get(selEquip).getPower() + player.getAtt()));
					}

				} else if (itemList.get(selEquip).getKind() == Item.ARMOR) {
					if (unit.getArmor() != null) {
						itemList.add(unit.getArmor());
						Player.guild.guildList.get(selUnit - 1).setDef((itemList.get(selEquip).getPower()
								+ (player.getDef() - unit.getArmor().getPower())));
						unit.setArmor(itemList.get(selEquip));
					}else {
						unit.setArmor(itemList.get(selEquip));
						Player.guild.guildList.get(selUnit - 1).setDef(
							(itemList.get(selEquip).getPower() + player.getDef()));
					}
				} else if (itemList.get(selEquip).getKind() == Item.RING) {
					if (unit.getRing() != null) {
						itemList.add(unit.getRing());
						Player.guild.guildList.get(selUnit - 1).setHp((itemList.get(selEquip).getPower()
								+ (player.getHp() - unit.getRing().getPower())));
						Player.guild.guildList.get(selUnit - 1).setMaxHp((itemList.get(selEquip).getPower()
								+ (player.getMaxHp() - unit.getRing().getPower())));
						unit.setRing(itemList.get(selEquip));
					}else {
						unit.setRing(itemList.get(selEquip));
						Player.guild.guildList.get(selUnit - 1).setHp(
								(itemList.get(selEquip).getPower() + player.getHp()));
						Player.guild.guildList.get(selUnit - 1).setMaxHp(
								(itemList.get(selEquip).getPower() + player.getMaxHp()));
					}
				}else if (itemList.get(selEquip).getKind() == Item.POTION) {
					if(itemList.get(selEquip).getPower() + player.getHp() >player.getMaxHp() ) {
						Player.guild.guildList.get(selUnit - 1).setHp(player.getMaxHp());
					}else {
						Player.guild.guildList.get(selUnit - 1).setHp(itemList.get(selEquip).getPower() + player.getHp());
					}
				}
				itemList.remove(selEquip);
			} else {
				System.out.println("길드원을 다시 확인하시오.");
				break;
			}
		}
	}

	public void printItemList() {
		System.out.println("============ [아이템리스트] ==============");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print("[이름 : " + itemList.get(i).getName() + "]");
			System.out.print("[능력 : " + itemList.get(i).getPower() + "]");
			System.out.print("[가격 : " + itemList.get(i).getPrice() + "]");
			System.out.println("");
		}
	}

	public void sellMenu() {
		while (true) {
			printItemList();
			System.out.println("[골드 : " + Player.money + "]");
			System.out.println("판매할 아이템 번호를 입력하세요. (50 % 세금) [0.뒤로가기]");
			int selSell = MainGame.scan.nextInt();
			if (selSell != 0) {
				System.out.println(itemList.get(selSell - 1).getName() + "을 판매합니다.");
				Player.money += (itemList.get(selSell - 1).getPrice() / 2);
				itemList.remove(selSell - 1);
			} else {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addItem(Item item) {
		itemList.add(item);
	}

}