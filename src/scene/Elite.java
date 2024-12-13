package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import character.Character;
import character.Enemy;
import character.Character.Effect;

public class Elite extends Battle{
	
	public Elite() {
		enemyPanel.setBounds(275, 25, 490, 250);
	}
	
	public JPanel createEnemyPanel(Character character) {
		JPanel panel = new JPanel(null);
	    panel.setBackground(Color.black);
	    panel.setBounds(0, 0, 240, 250);
	    
	    ImageIcon icon;
	    if(character.name.contains(" ")) {
	    	String subName = "";
	    	for(int i=1; i < character.name.split(" ").length; i++) {
	    		subName += character.name.split(" ")[i];
	    		if(i != character.name.split(" ").length-1) {
	    			subName += " ";
	    		}
	    	}
	    	icon = new ImageIcon("images/" + subName + ".png");
	    }
	    else {
	    	icon = new ImageIcon("images/" + character.name + ".png");
	    }
	    Image image = icon.getImage();
	    double ratio = 0.8;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
	    
	    JLabel imageLabel = new JLabel(icon, JLabel.CENTER);
	    imageLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
	    imageLabel.setBounds(60, 20, 120, 180);
	    panel.add(imageLabel);
	    
	    JPanel HPPanel = new JPanel(null);
	    HPPanel.setBackground(Color.black);
	    HPPanel.setBounds(0, 200, 239, BAR_THICKNESS);
	    panel.add(HPPanel);
	    
	    JPanel SPPanel = new JPanel(null);
	    SPPanel.setBackground(Color.black);
	    SPPanel.setBounds(0, 215, 239, BAR_THICKNESS);
	    panel.add(SPPanel);

	    JPanel APPanel = new JPanel(null);
	    APPanel.setBackground(Color.black);
	    APPanel.setBounds(0, 230, 239, BAR_THICKNESS);
	    panel.add(APPanel);
	    
	    JLabel MHPLabel = new JLabel("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    MHPLabel.setForeground(Color.white);
	    MHPLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
	    MHPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MHP * 1.5), 239), BAR_THICKNESS);
	    HPPanel.add(MHPLabel);

	    JLabel HPLabel = new JLabel();
	    HPLabel.setOpaque(true);
	    HPLabel.setBackground(Color.red);
	    HPLabel.setBounds(BAR_MARGIN, 0, (character.MHP <= 0) ? 0 : (int)(1.0 * character.HP / character.MHP * (MHPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    HPPanel.add(HPLabel);
	    
	    JLabel MSPLabel = new JLabel("SP " + character.SP + "/" + character.MSP + " ");
	    MSPLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    MSPLabel.setForeground(Color.white);
	    MSPLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
	    MSPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MSP * 1.5), 239), BAR_THICKNESS);
	    SPPanel.add(MSPLabel);

	    JLabel SPLabel = new JLabel();
	    SPLabel.setOpaque(true);
	    SPLabel.setBackground(Color.yellow);
	    SPLabel.setBounds(BAR_MARGIN, 0, (character.MSP <= 0) ? 0 : (int)(1.0 * character.SP / character.MSP * (MSPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    SPPanel.add(SPLabel);

	    JLabel MAPLabel = new JLabel("AP " + character.AP + "/" + character.MAP + " ");
	    MAPLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    MAPLabel.setForeground(Color.white);
	    MAPLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
	    MAPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MAP * 1.5), 239), BAR_THICKNESS);
	    APPanel.add(MAPLabel);

	    JLabel APLabel = new JLabel();
	    APLabel.setOpaque(true);
	    APLabel.setBackground(Color.green);
	    APLabel.setBounds(BAR_MARGIN, 0, (character.MAP <= 0) ? 0 : (int)(1.0 * character.AP / character.MAP * (MAPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    APPanel.add(APLabel);

	    return panel;
	}
	
	public void loadBattle(int floorNum) {
		Board.clearNotice();
		
		characterList.clear();
		playerPanel.removeAll();
		enemyPanel.removeAll();
		
		characterList.add(player);
		characterList.get(0).originStatPointList = new ArrayList<>(Arrays.asList(
				characterList.get(0).MHP,
				characterList.get(0).MSP,
				characterList.get(0).HPG,
				characterList.get(0).SPG,
				characterList.get(0).ATK,
				characterList.get(0).DEF,
				characterList.get(0).SPD,
				characterList.get(0).EFC));
		characterList.get(0).effectList = new ArrayList<>();
		characterList.get(0).MAP = 100 - characterList.get(0).EFC;
		playerPanel.add(createPlayerPanel(characterList.get(0)));

		for(int i=0; i<1+floorNum/3; i++) {
			int enemyRate = (int)(Math.random()*Enemy.allEliteList.get(floorNum-1).size());
			characterList.add(new Enemy(Enemy.allEliteList.get(floorNum-1).get(enemyRate)));
			characterList.get(i+1).originStatPointList = new ArrayList<>(Arrays.asList(
					characterList.get(i+1).MHP,
					characterList.get(i+1).MSP,
					characterList.get(i+1).HPG,
					characterList.get(i+1).SPG,
					characterList.get(i+1).ATK,
					characterList.get(i+1).DEF,
					characterList.get(i+1).SPD,
					characterList.get(i+1).EFC));
			characterList.get(i+1).effectList = new ArrayList<>();
			characterList.get(i+1).MAP = 100 - characterList.get(i+1).EFC;
			enemyPanel.add(createEnemyPanel(characterList.get(i+1)));
			enemyPanel.getComponent(i).setLocation(250-i*250, 0);
			
			if(floorNum%2 == 0) {
				enemyRate = (int)(Math.random()*Enemy.allEnemyList.get(floorNum-1).size());
				characterList.add(new Enemy(Enemy.allEnemyList.get(floorNum-1).get(enemyRate)));
				characterList.get(2).originStatPointList = new ArrayList<>(Arrays.asList(
						characterList.get(2).MHP,
						characterList.get(2).MSP,
						characterList.get(2).HPG,
						characterList.get(2).SPG,
						characterList.get(2).ATK,
						characterList.get(2).DEF,
						characterList.get(2).SPD,
						characterList.get(2).EFC));
				characterList.get(2).effectList = new ArrayList<>();
				characterList.get(2).MAP = 100 - characterList.get(2).EFC;
				enemyPanel.add(Board.battle.createEnemyPanel(characterList.get(2)));
				enemyPanel.getComponent(1).setLocation(60, 0);
			}
		}

		for(Character character : characterList.subList(1, characterList.size())) {
			if((character.name.charAt(character.name.length() - 1) - 0xAC00) % 28 > 0) {
				Board.updateNotice(character.name + "이 나타났다!");
			}
			else {
				Board.updateNotice(character.name + "가 나타났다!");
			}
		}

		battle.removeAll();
		battle.add(playerPanel);
		battle.add(enemyPanel);
		battle.add(Board.notice1Label);
		battle.add(Board.notice2Label);
		battle.add(Board.notice3Label);
		
		Board.floorPanel.removeAll();
		Board.floorPanel.add(battle);
		updateBattleUI();
		
		startBattleLoop();
	}
	
	public void winBattle() {
		Board.updateNotice("정예 전투에서 승리했다!");
		
		resetToOriginStat(player);
		player.levelUp(2);
		updateCharacterUI((JPanel)playerPanel.getComponent(0), player);
		updateBattleUI();
		player.getItem(Math.min(Board.currentFloor - (int)(Math.random() * (1 + Board.currentFloor/2)), 2));
		
		battle.add(toBoardLabel);
		battle.setComponentZOrder(toBoardLabel, 0);
		toBoardLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				Board.loadFloor();
				battle.remove(toBoardLabel);
				toBoardLabel.removeMouseListener(this);
				toBoardLabel.setForeground(Color.white);
				toBoardLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				toBoardLabel.setForeground(Color.darkGray);
				toBoardLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toBoardLabel.setForeground(Color.white);
				toBoardLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				toBoardLabel.setForeground(Color.gray);
				toBoardLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		battleTimer.stop();
	}
	
	public void takeEnemyTurn(JPanel pPanel, JPanel ePanel, Enemy enemy) {
		updateCharacterUI(ePanel, enemy);
		if(enemy.skill != null && enemy.SP >= enemy.MSP) {
			Board.updateNotice(enemy.name + "의 " + enemy.skill + "!");
			blinkCharacter(ePanel, 3, Color.white, () -> {
				switch(enemy.skill) {
				case Enemy.E_SKELETON_SKILL:
					enemy.SP = 0;
					takeDamage(player, (int)(enemy.ATK * 1.5));
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
					break;
				case Enemy.E_SNAKE_SKILL:
					enemy.SP = 0;
					takeDamage(player, (int)(enemy.ATK * 1.5));
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
					break;
				case Enemy.E_MOUSE_SKILL:
					enemy.SP = 0;
					takeDamage(player, (int)(enemy.ATK * 1.5));
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
					break;
					
				case Enemy.E_WARRIOR_SKILL:
					enemy.SP = 0;
					enemy.effectList.add(new Effect("ATK", 1, (int)(enemy.originStatPointList.get(4)*0.5)));
					enemy.effectList.add(new Effect("SPD", 1, (int)(enemy.originStatPointList.get(6)*2.0)));
					
					isPaused = false;
					break;
				case Enemy.E_HUNTER_SKILL:
					enemy.SP = 0;
					enemy.effectList.add(new Effect("EFC", 3, enemy.MAP));
					
					isPaused = false;
					break;
					
				case Enemy.E_GOLLEM_SKILL:
					enemy.SP = 0;
					enemy.HP = Math.min(enemy.HP + (int)(enemy.MHP * 0.5), enemy.MHP);

					isPaused = false;
					break;
				case Enemy.E_WAND_SKILL:
					enemy.SP = 0;
					player.HP = Math.max(player.HP - enemy.ATK, 0);
					Board.updateNotice(player.name + "에게 " + enemy.ATK + "의 관통 피해!");
					
					int rate = (int)(Math.random() * player.originStatPointList.size());
					switch(rate) {
					case 0:
						player.effectList.add(new Effect("MHP", 99, -(int)(player.originStatPointList.get(rate) * 0.40)));
						player.HP = Math.min(player.HP, player.MHP - (int)(player.originStatPointList.get(rate) * 0.40));
						Board.updateNotice("죽음의 저주를 받아 최대 HP가 " + (int)(player.originStatPointList.get(rate) * 0.40) + " 감소한다!");
						break;
					case 1:
						player.effectList.add(new Effect("MSP", 99, -(int)(player.originStatPointList.get(rate) * 0.40)));
						player.SP = Math.min(player.SP, player.MSP - (int)(player.originStatPointList.get(rate) * 0.40));
						Board.updateNotice("고갈의 저주를 받아 최대 SP가 " + (int)(player.originStatPointList.get(rate) * 0.40) + " 감소한다!");
						break;
					case 2:
						player.effectList.add(new Effect("HPG", 99, -(int)(player.originStatPointList.get(rate) * 1.0)));
						Board.updateNotice("부패의 저주를 받아 HPG가 " + (int)(player.originStatPointList.get(rate) * 1.0) + " 감소한다!");
						break;
					case 3:
						player.effectList.add(new Effect("SPG", 99, -(int)(player.originStatPointList.get(rate) * 1.0)));
						Board.updateNotice("탈진의 저주를 받아 SPG가 " + (int)(player.originStatPointList.get(rate) * 1.0) + " 감소한다!");
						break;
					case 4:
						player.effectList.add(new Effect("ATK", 99, -(int)(player.originStatPointList.get(rate) * 0.40)));
						Board.updateNotice("빈약의 저주를 받아 ATK가 " + (int)(player.originStatPointList.get(rate) * 0.40) + " 감소한다!");
						break;
					case 5:
						player.effectList.add(new Effect("DEF", 99, -(int)(player.originStatPointList.get(rate) * 1.0)));
						Board.updateNotice("허약의 저주를 받아 DEF가 " + (int)(player.originStatPointList.get(rate) * 1.0) + " 감소한다!");
						break;
					case 6:
						player.effectList.add(new Effect("SPD", 99, -(int)(player.originStatPointList.get(rate) * 0.40)));
						Board.updateNotice("둔화의 저주를 받아 SPD가 " + (int)(player.originStatPointList.get(rate) * 0.40) + " 감소한다!");
						break;
					case 7:
						player.effectList.add(new Effect("EFC", 99, -(int)(player.originStatPointList.get(rate) * 1.0)));
						Board.updateNotice("노화의 저주를 받아 EFC가 " + (int)(player.originStatPointList.get(rate) * 1.0) + " 감소한다!");
						break;
					}

					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
					break;
				}

				applyEffect(player);
				updateEffect(enemy);
				updateCharacterUI(pPanel, player);
				updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
				updateBattleUI();
				
			});
		}
		else {
			Board.updateNotice(enemy.name + "의 공격!");
			blinkCharacter(ePanel, 1, Color.white, () -> {
				takeDamage(player, enemy.ATK);

				applyEffect(player);
				updateEffect(enemy);
				updateCharacterUI(pPanel, player);
				updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
				updateBattleUI();
				
				blinkCharacter(pPanel, 3, null, () -> {
					isPaused = false;
				});
			});
		}
	}
	
}
