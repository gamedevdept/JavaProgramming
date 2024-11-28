package character;

import java.util.ArrayList;
import java.util.Arrays;

public class Enemy extends Character{

	public static final String SKELETON = "스켈레톤";
	public static final String SNAKE = "뱀";
	public static final String MOUSE = "쥐";
	public static final String E_SKELETON = "단단한 스켈레톤";
	public static final String E_SKELETON_SKILL = "뼈 던지기";
	public static final String E_SNAKE = "사나운 뱀";
	public static final String E_SNAKE_SKILL = "깨물기";
	public static final String E_MOUSE = "재빠른 쥐";
	public static final String E_MOUSE_SKILL = "달려들기";
	
	public static final String WARRIOR = "언데드 전사";
	public static final String HUNTER = "언데드 사냥꾼";
	public static final String E_WARRIOR = "정예 언데드 전사";
	public static final String E_WARRIOR_SKILL = "출혈 베기";
	public static final String E_HUNTER = "포효";
	public static final String E_HUNTER_SKILL = "3연사";
	public static final String E_CHIEF = "언데드 족장";
	public static final String E_CHIEF_SKILL = "촉진";
	
	public static final String WARRIOR_SKULL = "전사의 해골";
	public static final String WARRIOR_SKULL_SKILL = "강타";
	public static final String MAGIC_CREATURE = "마법의 흉물";
	public static final String MAGIC_CREATURE_SKILL = "포식";
	public static final String BLACK_MAGE = "흑마법사";
	public static final String BLACK_MAGE_SKILL = "저주";

	public String skill;

	public static ArrayList<ArrayList<String>> allEnemyList = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> floor1EnemyList = new ArrayList<>();
	public static ArrayList<String> floor2EnemyList = new ArrayList<>();
	public static ArrayList<String> floor3EnemyList = new ArrayList<>();

	public static ArrayList<ArrayList<String>> allEliteList = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> floor1EliteList = new ArrayList<>();
	public static ArrayList<String> floor2EliteList = new ArrayList<>();
	public static ArrayList<String> floor3EliteList = new ArrayList<>();
	
	public static ArrayList<String> allBossList = new ArrayList<>();
	
	static {
		allEnemyList.addAll(Arrays.asList(
				floor1EnemyList, 
				floor2EnemyList, 
				floor3EnemyList));
		floor1EnemyList.addAll(Arrays.asList(
				SKELETON,
				SNAKE,
				MOUSE));
		floor2EnemyList.addAll(Arrays.asList(
				WARRIOR,
				HUNTER
				));
		floor3EnemyList.addAll(Arrays.asList(
				));
		
		allEliteList.addAll(Arrays.asList(
				floor1EliteList,
				floor2EliteList,
				floor3EliteList));
		floor1EliteList.addAll(Arrays.asList(
				E_SKELETON,
				E_SNAKE,
				E_MOUSE));
		floor2EliteList.addAll(Arrays.asList(
				E_WARRIOR,
				E_HUNTER,
				E_CHIEF));
		floor3EliteList.addAll(Arrays.asList(
				));
		
		allBossList.addAll(Arrays.asList(
				WARRIOR_SKULL,
				MAGIC_CREATURE,
				BLACK_MAGE));
	}
	
	public Enemy(String name) {
		this.name = name;
		
		switch(name) {
		case SKELETON:
			HP = 30;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 10;
			DEF = 10;
			SPD = 10;
			EFC = 0;
			skill = null;
			break;
		case SNAKE:
			HP = 20;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 15;
			DEF = 0;
			SPD = 8;
			EFC = 0;
			skill = null;
			break;
		case MOUSE:
			HP = 15;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 10;
			DEF = 0;
			SPD = 12;
			EFC = 10;
			skill = null;
			break;
		case WARRIOR:
			HP = 45;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 15;
			DEF = 15;
			SPD = 12;
			EFC = 10;
			skill = null;
			break;
		case HUNTER:
			HP = 30;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 40;
			DEF = 0;
			SPD = 6;
			EFC = 10;
			skill = null;
			break;
			
		case E_SKELETON:
			HP = 60;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 15;
			DEF = 15;
			SPD = 12;
			EFC = 10;
			skill = E_SKELETON_SKILL;
			break;
		case E_SNAKE:
			HP = 40;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 22;
			DEF = 0;
			SPD = 10;
			EFC = 10;
			skill = E_SNAKE_SKILL;
			break;
		case E_MOUSE:
			HP = 30;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 15;
			DEF = 0;
			SPD = 14;
			EFC = 20;
			skill = E_MOUSE_SKILL;
			break;
		case E_WARRIOR:
			HP = 90;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 20;
			DEF = 20;
			SPD = 14;
			EFC = 20;
			skill = E_WARRIOR_SKILL;
			break;
		case E_HUNTER:
			HP = 60;
			MHP = HP;
			SP = 30;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 40;
			DEF = 0;
			SPD = 4;
			EFC = 20;
			skill = E_HUNTER_SKILL;
			break;
		case E_CHIEF:
			HP = 60;
			MHP = HP;
			SP = 0;
			MSP = 10;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 0;
			DEF = 0;
			SPD = 10;
			EFC = 20;
			skill = E_CHIEF_SKILL;
			
		case WARRIOR_SKULL:
			HP = 100;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 20;
			DEF = 20;
			SPD = 14;
			EFC = 20;
			skill = WARRIOR_SKULL_SKILL;
			break;
		case MAGIC_CREATURE:
			HP = 200;
			MHP = HP;
			SP = 0;
			MSP = 40;
			AP = 0;
			MAP = 100;
			HPG = 20;
			SPG = 10;
			ATK = 40;
			DEF = 20;
			SPD = 15;
			EFC = 30;
			skill = MAGIC_CREATURE_SKILL;
			break;
		case BLACK_MAGE:
			HP = 300;
			MHP = HP;
			SP = 0;
			MSP = 10;
			AP = 0;
			MAP = 100;
			HPG = 20;
			SPG = 5;
			ATK = 60;
			DEF = 30;
			SPD = 20;
			EFC = 50;
			skill = MAGIC_CREATURE_SKILL;
			break;
		}
		originStatPointList = new ArrayList<>();
		effectList = new ArrayList<>();
	}
	
}
