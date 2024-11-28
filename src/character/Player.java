package character;

import java.util.ArrayList;
import java.util.Arrays;
import scene.Board;

public class Player extends Character{

	public final int COMMON = 0;
	public final int RARE = 1;
	public final int EPIC = 2;
	public final int LEGEND = 3;
	
	public ArrayList<String> allSkillList;
	public ArrayList<String> allCommonItemList;
	public ArrayList<String> allRareItemList;
	public ArrayList<String> allEpicItemList;
	public ArrayList<String> allLegendItemList;
	public ArrayList<String> statList;
	public ArrayList<String> skillList;
	public ArrayList<String> itemList;
	
	public Player() {
		name = "플레이어";
		HP = 30;
		MHP = HP;
		SP = 15;
		MSP = SP;
		AP = 0;
		MAP = 100;
		HPG = 0;
		SPG = 0;
		ATK = 10;
		DEF = 10;
		SPD = 10;
		EFC = 0;
		originStatPointList = new ArrayList<>();
		effectList = new ArrayList<>();
		
		allSkillList = new ArrayList<>();
		allSkillList.addAll(Arrays.asList(
				Skill.ATTACK,
				Skill.CHARGE_ATTACK,
				Skill.FAST_ATTACK,
				Skill.HEALING));
		allCommonItemList = new ArrayList<>();
		allCommonItemList.addAll(Arrays.asList(
				Item.POTION,
				Item.SPEAR));
		allRareItemList = new ArrayList<>();
		allRareItemList.addAll(Arrays.asList(
				Item.HIGH_POTION,
				Item.METAL_SPEAR
				));
		allEpicItemList = new ArrayList<>();
		allEpicItemList.addAll(Arrays.asList(
				Item.MEGA_POTION,
				Item.BAMBOO_SPEAR
				));
		allLegendItemList = new ArrayList<>();
		allLegendItemList.addAll(Arrays.asList(
				Item.SOUL_ESSENCE
				));

		statList = new ArrayList<>();
		statList.addAll(Arrays.asList("HP", "SP", "HPG", "SPG", "ATK", "DEF", "SPD", "EFC"));
		
		skillList = new ArrayList<>();
		skillList.add(Skill.ATTACK);
		
		itemList = new ArrayList<>();
		itemList.add(Item.POTION);
	}
	
	public void levelUp(int count) {
		int[] increasedPoint = new int[8];
		String increasedStat = "";
		for(int i=0; i<8*count; i++) {
			int randomIndex = (int)(Math.random() * 8);
			int point = 1;
			switch(statList.get(randomIndex)) {
			case "HP":
				HP += point;
				MHP += point;
				increasedPoint[randomIndex] += point;
				break;
			case "SP":
				SP += point;
				MSP += point;
				increasedPoint[randomIndex] += point;
				break;
			case "HPG":
				HPG += point;
				increasedPoint[randomIndex] += point;
				break;
			case "SPG":
				SPG += point;
				increasedPoint[randomIndex] += point;
				break;
			case "ATK":
				ATK += point;
				increasedPoint[randomIndex] += point;
				break;
			case "DEF":
				DEF += point;
				increasedPoint[randomIndex] += point;
				break;
			case "SPD":
				SPD += point;
				increasedPoint[randomIndex] += point;
				break;
			case "EFC":
				EFC += point;
				increasedPoint[randomIndex] += point;
				break;
			}
		}
		MAP = 100 - EFC;
		for(int i=0; i<8; i++) {
			if(increasedPoint[i] != 0) {
				increasedStat += "  " + statList.get(i) + " +" + increasedPoint[i] + "  ";
			}
		}
		
		switch(count) {
		case 1:
			Board.updateNotice("레벨이 1 올랐다! ->" + increasedStat);
			break;
		case 2:
			Board.updateNotice("레벨이 2 올랐다! ->" + increasedStat);
			break;
		case 3:
			Board.updateNotice("레벨이 3 올랐다! ->" + increasedStat);
			break;
		}
		Board.updateStat();
		Board.updateSkill();
		Board.updateItem();
		Board.updateDetailUI();
	}
	
	public void getSkill() {
		String skill;
		do {
			skill = allSkillList.get(1 + (int)(Math.random() * (allSkillList.size() - 1)));
		} while(Board.player.skillList.contains(skill));
		
		if(Board.player.skillList.size() >= 4) {
			if((skillList.get(1).charAt(skillList.get(0).length() - 1) - 0xAC00) % 28 > 0) {
				Board.updateNotice("공간이 부족해 " + skillList.get(1) + "을 잊었다...");
			}
			else {
				Board.updateNotice("공간이 부족해 " + skillList.get(1) + "를 잊었다...");
			}
			skillList.remove(0);
		}
		if((skill.charAt(skill.length() - 1) - 0xAC00) % 28 > 0) {
			Board.updateNotice(skill + "을 배웠다!");
		}
		else {
			Board.updateNotice(skill + "를 배웠다!");
		}
		skillList.add(skill);
		Board.updateSkill();
		Board.updateDetailUI();
	}
	
	public void getItem(int rarity) {
		String item = null;
		
		switch(rarity) {
		case COMMON:
			item = allCommonItemList.get((int)(Math.random() * allCommonItemList.size()));
			break;
		case RARE:
			item = allRareItemList.get((int)(Math.random() * allRareItemList.size()));
			break;
		case EPIC:
			item = allEpicItemList.get((int)(Math.random() * allEpicItemList.size()));
			break;
		case LEGEND:
			item = allLegendItemList.get((int)(Math.random() * allLegendItemList.size()));
			break;
		}
		if(Board.player.itemList.size() >= 8) {
			if((itemList.get(0).charAt(itemList.get(0).length() - 1) - 0xAC00) % 28 > 0) {
				Board.updateNotice("공간이 부족해 " + itemList.get(0) + "을 버렸다...");
			}
			else {
				Board.updateNotice("공간이 부족해 " + itemList.get(0) + "를 버렸다...");
			}
			itemList.remove(0);
		}
		if((item.charAt(item.length() - 1) - 0xAC00) % 28 > 0) {
			Board.updateNotice(item + "을 얻었다!");
		}
		else {
			Board.updateNotice(item + "를 얻었다!");
		}
		itemList.add(item);
		Board.updateItem();
		Board.updateDetailUI();
	}
	
	public static class Stat{
		
		public String name;
		public String point;
		public String description;
		
		public Stat(String name, Player player) {
			this.name = name;
			switch(name) {
			case "HP":
				point = player.HP + "/" + player.MHP;
				description = "현재/최대 체력 수치입니다. 0이 되면 사망합니다.";
				break;
			case "SP":
				point = player.SP + "/" + player.MSP;
				description = "현재/최대 자원 수치입니다. 소모량보다 적으면 해당 스킬은 사용불가합니다.";
				break;
			case "HPG":
				point = String.valueOf(player.HPG);
				description = "체력 재생 수치입니다. 매 턴마다 수치만큼 HP를 회복합니다.";
				break;
			case "SPG":
				point = String.valueOf(player.SPG);
				description = "자원 재생 수치입니다. 매 턴마다 수치만큼 SP를 회복합니다.";
				break;
			case "ATK":
				point = String.valueOf(player.ATK);
				description = "공격력 수치입니다. 높을수록 주는 피해량이 증가합니다.";
				break;
			case "DEF":
				point = String.valueOf(player.DEF);
				description = "방어력 수치입니다. 높을수록 받는 피해량이 감소합니다.";
				break;
			case "SPD":
				point = String.valueOf(player.SPD);
				description = "속도 수치입니다. 높을수록 AP 회복량이 증가합니다.";
				break;
			case "EFC":
				point = String.valueOf(player.EFC);
				description = "효율 수치입니다. 높을수록 AP 최대치가 감소합니다.";
				break;
			}
		}
		
	}
	
	public static class Skill{
		
		public static final String ATTACK = "기본 공격";
		public static final String CHARGE_ATTACK = "강공격";
		public static final String FAST_ATTACK = "속공";
		public static final String HEALING = "치유술";
		final String PLAYER = "player";
		final String ENEMY = "enemy";

		public String name;
		public int cost;
		public String target;
		public ArrayList<Integer> points = new ArrayList<>();
		public String description;
		
		public Skill(String name, Player player) {
			this.name = name;
			switch(name) {
			case ATTACK:
				cost = 0;
				target = ENEMY;
				points.add(player.ATK);
				description = "적에게 피해를 " + points.get(0) + " 줍니다.";
				break;
			case CHARGE_ATTACK:
				cost = 15;
				target = ENEMY;
				points.add((int)(player.ATK * 1.2));
				points.add((int)(player.ATK * 2.4));
				description = "적에게 피해를 " + points.get(0) + " 주고 AP를 " + points.get(1) + " 감소시킵니다.";
				break;
			case FAST_ATTACK:
				cost = 15;
				target = ENEMY;
				points.add(player.ATK);
				points.add(1);
				points.add(30);
				description = "적에게 피해를 " + points.get(0) + " 주고 플레이어의 EFC가 " + points.get(1) + "턴간 " + points.get(2) + " 증가합니다.";
				break;
			case HEALING:
				cost = 15;
				target = PLAYER;
				points.add((int)(player.MHP * 0.5));
				points.add(30);
				description = "플레이어의 HP를 " + points.get(0) + " 회복하고 AP를 " + points.get(1) + " 회복합니다.";
				break;
			}
		}
		
	}
	
	public static class Item{
		
		public static final String POTION = "포션";
		public static final String SPEAR = "목재 창";
		
		public static final String HIGH_POTION = "하이 포션";
		public static final String MEGA_POTION = "메가 포션";
		public static final String METAL_SPEAR = "강철 창";
		public static final String BAMBOO_SPEAR = "죽창";
		
		public static final String SOUL_ESSENCE = "영혼의 정수";
		
		final String PLAYER = "player";
		final String ENEMY = "enemy";

		public String name;
		public String target;
		public ArrayList<Integer> points = new ArrayList<>();
		public String description;
		
		public Item(String name, Player player) {
			this.name = name;
			switch (name) {
			case POTION:
				target = PLAYER;
				points.add((int)(player.MHP * 0.3));
				description = "플레이어의 HP를 " + points.get(0) + " 회복합니다.";
				break;
			case SPEAR:
				target = ENEMY;
				points.add(20);
				description = "적에게 피해를 " + points.get(0) + " 줍니다.";
				break;
				
			case HIGH_POTION:
				target = PLAYER;
				points.add((int)(player.MHP * 0.5));
				description = "플레이어의 HP를 " + points.get(0) + " 회복합니다.";
				break;
			case MEGA_POTION:
				target = PLAYER;
				points.add((int)(player.MHP * 0.7));
				description = "플레이어의 HP를 " + points.get(0) + " 회복합니다.";
				break;
			case METAL_SPEAR:
				target = ENEMY;
				points.add(40);
				description = "적에게 피해를 " + points.get(0) + " 줍니다.";
				break;
			case BAMBOO_SPEAR:
				target = ENEMY;
				points.add(60);
				description = "적에게 피해를 " + points.get(0) + " 줍니다.";
				break;
				
			case SOUL_ESSENCE:
				target = PLAYER;
				points.add(player.MHP);
				description = "플레이어의 체력을 모두 회복합니다.";
				break;
			}
		}
		
	}
	
}
