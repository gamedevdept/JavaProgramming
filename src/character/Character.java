package character;

import java.util.ArrayList;

public class Character {

	public String name;
	public int HP;
	public int MHP;
	public int SP;
	public int MSP;
	public int AP;
	public int MAP;
	public int HPG;
	public int SPG;
	public int ATK;
	public int DEF;
	public int SPD;
	public int EFC;
	public ArrayList<Integer> originStatPointList;
	public ArrayList<Effect> effectList;

	public static class Effect {
		
		public String statName;
		public int point;
		public int duration;
		
		public Effect(String statName, int duration, int point) {
			this.statName = statName;
			this.duration = duration;
			this.point = point;
		}
		
	}
}
