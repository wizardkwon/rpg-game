package day04_rpg;

import java.util.ArrayList;

public class Shop {
	public ArrayList<Item> itemList = new ArrayList<>();

	public Shop() {
		Item temp = new Item();
		temp.setItem(Item.WEAPON, "나무검", 3, 1000);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.WEAPON, "철검", 5, 2000);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.WEAPON, "레이피어", 7, 2500);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.ARMOR, "티셔츠", 1, 300);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.ARMOR, "가죽갑옷", 4, 800);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.ARMOR, "강철갑옷", 7, 1500);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.RING, "은반지", 7, 3000);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.RING, "금반지", 17, 6000);
		itemList.add(temp);

		temp = new Item();
		temp.setItem(Item.RING, "다이아반지", 35, 20000);
		itemList.add(temp);
		
		temp = new Item();
		temp.setItem(Item.POTION , "기초 영약", 50, 500);
		itemList.add(temp);
		temp = new Item();
		temp.setItem(Item.POTION , "신비의 영약", 100, 900);
		itemList.add(temp);
		temp = new Item();
		temp.setItem(Item.POTION , "초월의 영약", 1000, 1000);
		itemList.add(temp);
	}

	public void shopMng() {
		while (true) {
			System.out.println("=============== [상점] ===============");
			System.out.println("[1.무기] [2.갑옷] [3.반지] [4.물약] [0.뒤로가기]");
			int selKind = MainGame.scan.nextInt();
			if (selKind == 0)
				return;
			while (true) {
				if (selKind == Item.WEAPON)
					System.out.println("=========== [무기] ============");
				else if (selKind == Item.ARMOR)
					System.out.println("=========== [방어구] ============");
				else if (selKind == Item.RING)
					System.out.println("=========== [반지] ============");
				else if (selKind == Item.POTION)
					System.out.println("=========== [물약] ============");
				printItems(selKind);
				System.out.println("[골드 : " + Player.money + "]");
				System.out.println("구입할 아이템 번호를 입력하세요 [0.뒤로가기]");
				int selNum = MainGame.scan.nextInt();
				if (selNum == 0)
					break;
				int count = 0;
				for (int i = 0; i < itemList.size(); i++) {
					if (itemList.get(i).getKind() == selKind) {
						count += 1;
						if (count == selNum) {
							Player.inven.addItem(itemList.get(i));
							Player.money -= itemList.get(i).getPrice();
							System.out.println("[" + itemList.get(i).getName() + "] 을 구입했습니다.");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
			}
		}
	}

	public void printItems(int kind) {
		int count = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getKind() != kind)
				continue;
			System.out.print("[" + (count + 1) + "번]");
			System.out.print("[이름 : " + itemList.get(i).getName() + "]");
			System.out.print("[능력 : " + itemList.get(i).getPower() + "]");
			System.out.print("[가격 : " + itemList.get(i).getPrice() + "]");
			System.out.println("");
			count += 1;
		}
	}

}