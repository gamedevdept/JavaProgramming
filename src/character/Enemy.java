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
	public static final String E_WARRIOR_SKILL = "돌격";
	public static final String E_HUNTER = "정예 언데드 사냥꾼";
	public static final String E_HUNTER_SKILL = "3연사";

	public static final String GOLLEM = "돌골렘";
	public static final String WAND = "지팡이";
	public static final String WAND_SKILL = "마력 탄환";
	public static final String E_GOLLEM = "완벽한 돌골렘";
	public static final String E_GOLLEM_SKILL = "급속 재생";
	public static final String E_WAND = "저주받은 지팡이";
	public static final String E_WAND_SKILL = "저주 탄환";
	
	public static final String WARRIOR_SKULL = "전사의 해골";
	public static final String WARRIOR_SKULL_SKILL = "강타";
	public static final String UNDEAD_CHIEF = "언데드 족장";
	public static final String UNDEAD_CHIEF_SKILL = "강령술";
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
				floor3EnemyList
				));
		floor1EnemyList.addAll(Arrays.asList(
				SKELETON,
				SNAKE,
				MOUSE
				));
		floor2EnemyList.addAll(Arrays.asList(
				WARRIOR,
				HUNTER
				));
		floor3EnemyList.addAll(Arrays.asList(
				GOLLEM,
				WAND
				));
		
		allEliteList.addAll(Arrays.asList(
				floor1EliteList,
				floor2EliteList,
				floor3EliteList
				));
		floor1EliteList.addAll(Arrays.asList(
				E_SKELETON,
				E_SNAKE,
				E_MOUSE
				));
		floor2EliteList.addAll(Arrays.asList(
				E_WARRIOR,
				E_HUNTER
				));
		floor3EliteList.addAll(Arrays.asList(
				E_GOLLEM,
				E_WAND
				));
		
		allBossList.addAll(Arrays.asList(
				WARRIOR_SKULL,
				UNDEAD_CHIEF,
				BLACK_MAGE
				));
	}
	
	public Enemy(String name) {
		this.name = name;
		
		switch(name) {
		case SKELETON:
			HP = 20;
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
			ATK = 14;
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
			ATK = 8;
			DEF = 0;
			SPD = 12;
			EFC = 6;
			skill = null;
			break;
		case E_SKELETON:
			HP = 40;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 14;
			DEF = 14;
			SPD = 12;
			EFC = 6;
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
			EFC = 6;
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
			ATK = 14;
			DEF = 0;
			SPD = 14;
			EFC = 12;
			skill = E_MOUSE_SKILL;
			break;
		case WARRIOR_SKULL:
			HP = 100;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 18;
			DEF = 18;
			SPD = 12;
			EFC = 12;
			skill = WARRIOR_SKULL_SKILL;
			break;
			
		case WARRIOR:
			HP = 50;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 18;
			DEF = 18;
			SPD = 14;
			EFC = 12;
			skill = null;
			break;
		case HUNTER:
			HP = 35;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 22;
			DEF = 0;
			SPD = 8;
			EFC = 12;
			skill = null;
			break;
		case E_WARRIOR:
			HP = 100;
			MHP = HP;
			SP = 30;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 18;
			DEF = 18;
			SPD = 16;
			EFC = 18;
			skill = E_WARRIOR_SKILL;
			break;
		case E_HUNTER:
			HP = 70;
			MHP = HP;
			SP = 40;
			MSP = 40;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 22;
			DEF = 0;
			SPD = 10;
			EFC = 18;
			skill = E_HUNTER_SKILL;
			break;
		case UNDEAD_CHIEF:
			HP = 200;
			MHP = HP;
			SP = 30;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 22;
			DEF = 22;
			SPD = 14;
			EFC = 18;
			skill = UNDEAD_CHIEF_SKILL;
			break;

		case GOLLEM:
			HP = 80;
			MHP = HP;
			SP = 0;
			MSP = 0;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 0;
			ATK = 18;
			DEF = 24;
			SPD = 10;
			EFC = 18;
			skill = null;
			break;
		case WAND:
			HP = 60;
			MHP = HP;
			SP = 0;
			MSP = 10;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 22;
			DEF = 0;
			SPD = 14;
			EFC = 24;
			skill = WAND_SKILL;
			break;
		case E_GOLLEM:
			HP = 120;
			MHP = HP;
			SP = 0;
			MSP = 30;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 22;
			DEF = 28;
			SPD = 12;
			EFC = 24;
			skill = E_GOLLEM_SKILL;
			break;
		case E_WAND:
			HP = 90;
			MHP = HP;
			SP = 0;
			MSP = 10;
			AP = 0;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 26;
			DEF = 0;
			SPD = 16;
			EFC = 30;
			skill = E_WAND_SKILL;
			break;
		case BLACK_MAGE:
			HP = 300;
			MHP = HP;
			SP = 30;
			MSP = 30;
			AP = 100;
			MAP = 100;
			HPG = 0;
			SPG = 10;
			ATK = 24;
			DEF = 30;
			SPD = 16;
			EFC = 30;
			skill = BLACK_MAGE_SKILL;
			break;
		}
		originStatPointList = new ArrayList<>();
		effectList = new ArrayList<>();
	}
	
}
