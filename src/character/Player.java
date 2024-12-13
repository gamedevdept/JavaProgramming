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
		SP = 30;
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
				Skill.STRONG,
				Skill.FAST,
				Skill.TACKLE,
				Skill.CHARGE,
				Skill.DASH,
				Skill.DEFENCE,
				Skill.HEALING
				));
		allCommonItemList = new ArrayList<>();
		allCommonItemList.addAll(Arrays.asList(
				Item.POTION,
				Item.SPEAR,
				Item.ACID,
				Item.POISON,
				Item.FLAME,
				Item.TIME
				));
		allRareItemList = new ArrayList<>();
		allRareItemList.addAll(Arrays.asList(
				Item.SUPER_POTION,
				Item.SUPER_SPEAR,
				Item.SUPER_ACID,
				Item.SUPER_POISON,
				Item.SUPER_FLAME,
				Item.SUPER_TIME
				));
		allEpicItemList = new ArrayList<>();
		allEpicItemList.addAll(Arrays.asList(
				Item.MEGA_POTION,
				Item.MEGA_SPEAR,
				Item.MEGA_ACID,
				Item.MEGA_POISON,
				Item.MEGA_FLAME,
				Item.MEGA_TIME
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
		for(int i=0; i<6*count; i++) {
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
				description = "현재/최대 기력 수치입니다. 소모량보다 적으면 해당 스킬은 사용불가합니다.";
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
		public static final String STRONG = "강공격";
		public static final String FAST = "속공";
		public static final String TACKLE = "태클";
		public static final String CHARGE = "집중";
		public static final String DASH = "파고들기";
		public static final String DEFENCE = "방어 태세";
		public static final String HEALING = "치유술";
		
		final String PLAYER = "player";
		final String ENEMY = "enemy";

		public String name;
		public int cost;
		public String target;
		public ArrayList<Double> points = new ArrayList<>();
		public String description;
		
		public Skill(String name, Player player) {
			this.name = name;
			switch(name) {
			case ATTACK:
				cost = 0;
				target = ENEMY;
				points.add(player.ATK * 1.0);
				description = "적에게 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
			case STRONG:
				cost = 20;
				target = ENEMY;
				points.add(player.ATK * 1.5);
				points.add(1.0);
				points.add(1.0 + player.ATK * 0.02);
				description = "적에게 피해를 " + points.get(0).intValue() + " 주고 " + points.get(1).intValue() + "턴간 SPD를 " + (int)(100 - (100 / points.get(2))) + "% 감소시킵니다.";
				break;
			case FAST:
				cost = 20;
				target = ENEMY;
				points.add(player.ATK * 1.0);
				points.add(1.0);
				points.add(player.SPD * 1.0);
				description = "적에게 피해를 " + points.get(0).intValue() + " 주고 " + points.get(1).intValue() + "턴간 플레이어의 EFC가 " + points.get(2).intValue() + " 증가합니다.";
				break;
			case TACKLE:
				cost = 20;
				target = ENEMY;
				points.add(1.0);
				points.add(player.DEF * 1.0);
				points.add((player.DEF + points.get(1)) * 1.0);
				description = points.get(0).intValue() + "턴간 방어력을 " + points.get(1).intValue() + " 얻고 적에게 피해를 " + (int)((player.DEF + points.get(1)) * 1.0) + " 줍니다.";
				break;
			case CHARGE:
				cost = 30;
				target = PLAYER;
				points.add(1.0);
				points.add(player.ATK * 3.0);
				points.add(player.SPD * 0.15);
				description = points.get(0).intValue() + "턴간 플레이어의 ATK가 300% 증가하고 SPD가 15% 감소합니다.";
				break;
			case DASH:
				cost = 30;
				target = PLAYER;
				points.add(2.0);
				points.add(player.ATK * 0.5);
				points.add(player.SPD * 0.5);
				description = points.get(0).intValue() + "턴간 플레이어의 ATK가 50% 증가하고 SPD가 50% 증가합니다.";
				break;
			case DEFENCE:
				cost = 30;
				target = PLAYER;
				points.add(3.0);
				points.add(player.DEF * 3.0);
				description = points.get(0).intValue() + "턴간 플레이어의 DEF가 " + points.get(1).intValue() + " 증가합니다.";
				break;
			case HEALING:
				cost = 30;
				target = PLAYER;
				points.add(player.MHP * 0.6);
				points.add(2.0);
				points.add(player.MHP * 0.2);
				description = "플레이어의 HP를 " + points.get(0).intValue() + " 회복하고 " + points.get(1).intValue() + "턴간 추가로 " + points.get(2).intValue() + " 회복합니다.";
				break;
			}
		}
		
	}
	
	public static class Item{
		
		public static final String POTION = "포션";
		public static final String SPEAR = "목재 창";
		public static final String ACID = "산성 항아리";
		public static final String POISON = "독 병";
		public static final String FLAME = "점화 두루마리";
		public static final String TIME = "초침";
		
		public static final String SUPER_POTION = "슈퍼 포션";
		public static final String SUPER_SPEAR = "강철 창";
		public static final String SUPER_ACID = "부식 항아리";
		public static final String SUPER_POISON = "맹독 병";
		public static final String SUPER_FLAME = "불덩이 두루마리";
		public static final String SUPER_TIME = "분침";

		public static final String MEGA_POTION = "메가 포션";
		public static final String MEGA_SPEAR = "죽창";
		public static final String MEGA_ACID = "분해 항아리";
		public static final String MEGA_POISON = "죽음 병";
		public static final String MEGA_FLAME = "잿더미 두루마리";
		public static final String MEGA_TIME = "시침";
		
		public static final String SOUL_ESSENCE = "영혼의 정수";
		
		final String PLAYER = "player";
		final String ENEMY = "enemy";

		public String name;
		public String target;
		public ArrayList<Double> points = new ArrayList<>();
		public String description;
		
		public Item(String name, Player player) {
			this.name = name;
			switch (name) {
			case POTION:
				target = PLAYER;
				points.add(0.4);
				description = "플레이어의 HP와 SP와 AP를 " + (int)(points.get(0) * 100) + "% 회복합니다.";
				break;
			case SUPER_POTION:
				target = PLAYER;
				points.add(0.6);
				description = "플레이어의 HP와 SP와 AP를 " + (int)(points.get(0) * 100) + "% 회복합니다.";
				break;
			case MEGA_POTION:
				target = PLAYER;
				points.add(0.8);
				description = "플레이어의 HP와 SP와 AP를 " + (int)(points.get(0) * 100) + "% 회복합니다.";
				break;
				
			case SPEAR:
				target = ENEMY;
				points.add(20.0);
				description = "적에게 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
			case SUPER_SPEAR:
				target = ENEMY;
				points.add(45.0);
				description = "적에게 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
			case MEGA_SPEAR:
				target = ENEMY;
				points.add(70.0);
				description = "적에게 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
				
			case ACID:
				target = ENEMY;
				points.add(3.0);
				points.add(0.3);
				points.add(0.5);
				description = points.get(0).intValue() + "턴간 적의 공격력을 " + (int)(points.get(1) * 100) + "% 감소시키고 방어력을 " + (int)(points.get(2) * 100) + "% 감소시킵니다.";
				break;
			case SUPER_ACID:
				target = ENEMY;
				points.add(3.0);
				points.add(0.4);
				points.add(0.75);
				description = points.get(0).intValue() + "턴간 적의 공격력을 " + (int)(points.get(1) * 100) + "% 감소시키고 방어력을 " + (int)(points.get(2) * 100) + "% 감소시킵니다.";
				break;
			case MEGA_ACID:
				target = ENEMY;
				points.add(3.0);
				points.add(0.5);
				points.add(1.0);
				description = points.get(0).intValue() + "턴간 적의 공격력을 " + (int)(points.get(1) * 100) + "% 감소시키고 방어력을 " + (int)(points.get(2) * 100) + "% 감소시킵니다.";
				break;
				
			case POISON:
				target = ENEMY;
				points.add(2.0);
				points.add(0.16);
				description = points.get(0).intValue() + "턴간 적에게 최대 체력의 " + (int)(points.get(1) * 100) + "%만큼 피해를 줍니다.";
				break;
			case SUPER_POISON:
				target = ENEMY;
				points.add(2.0);
				points.add(0.20);
				description = points.get(0).intValue() + "턴간 적에게 최대 체력의 " + (int)(points.get(1) * 100) + "%만큼 피해를 줍니다.";
				break;
			case MEGA_POISON:
				target = ENEMY;
				points.add(2.0);
				points.add(0.24);
				description = points.get(0).intValue() + "턴간 적에게 최대 체력의 " + (int)(points.get(1) * 100) + "%만큼 피해를 줍니다.";
				break;
				
			case FLAME:
				target = ENEMY;
				points.add(8.0);
				points.add(2.0);
				description = "적에게 피해를 " + (int)(points.get(0) * 1.5) + " 주고 " + points.get(1).intValue() + "턴간 추가로 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
			case SUPER_FLAME:
				target = ENEMY;
				points.add(16.0);
				points.add(2.0);
				description = "적에게 피해를 " + (int)(points.get(0) * 1.5) + " 주고 " + points.get(1).intValue() + "턴간 추가로 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
			case MEGA_FLAME:
				target = ENEMY;
				points.add(24.0);
				points.add(2.0);
				description = "적에게 피해를 " + (int)(points.get(0) * 1.5) + " 주고 " + points.get(1).intValue() + "턴간 추가로 피해를 " + points.get(0).intValue() + " 줍니다.";
				break;
				
			case TIME:
				target = ENEMY;
				points.add(1.0);
				points.add(0.2);
				description = "적의 AP를 0으로 만들고 " + points.get(0).intValue() + "턴간 속도를 " + (int)(points.get(1) * 100) + "% 감소시킵니다.";
				break;
			case SUPER_TIME:
				target = ENEMY;
				points.add(1.0);
				points.add(0.4);
				description = "적의 AP를 0으로 만들고 " + points.get(0).intValue() + "턴간 속도를 " + (int)(points.get(1) * 100) + "% 감소시킵니다.";
				break;
			case MEGA_TIME:
				target = ENEMY;
				points.add(1.0);
				points.add(0.6);
				description = "적의 AP를 0으로 만들고 " + points.get(0).intValue() + "턴간 속도를 " + (int)(points.get(1) * 100) + "% 감소시킵니다.";
				break;
				
			case SOUL_ESSENCE:
				target = PLAYER;
				points.add(1.0);
				description = "플레이어의 HP와 SP를 전부 회복하고 즉시 행동합니다.";
				break;
			}
		}
		
	}
	
}
