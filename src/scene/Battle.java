package scene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import character.*;
import character.Character;
import character.Character.Effect;
import character.Player.Item;
import character.Player.Skill;
import scene.Board.ItemLabel;
import scene.Board.SkillLabel;

public class Battle{

	Player player;
	
	public JPanel battle;
	public JPanel playerPanel;
	public JPanel enemyPanel;
	public JPanel characterPanel;
	
	JLabel toBoardLabel;
	JLabel toTitieLabel;
	
	ArrayList<Character> characterList;

	Timer battleTimer;
	boolean isPaused = false;
	
	final int BAR_THICKNESS = 15;
	final int BAR_MARGIN = 71;
	final int THICKNESS = 1;
	final String PLAYER = "player";
	final String ENEMY = "enemy";
	
	public Battle() {
		player = Board.player;
		
		battle = new JPanel(null);
		battle.setBackground(Color.black);
		battle.setBorder(new LineBorder(Color.white, 1, false));
		battle.setBounds(0, 0, 784, 350);
		
		playerPanel = new JPanel(null);
		playerPanel.setBackground(Color.black);
		playerPanel.setBounds(25, 25, 180, 250);
		
		enemyPanel = new JPanel(null);
		enemyPanel.setBackground(Color.black);
		enemyPanel.setBounds(210, 25, 560, 250);
		
		toBoardLabel = new JLabel("맵으로 돌아가기", JLabel.CENTER);
		toBoardLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		toBoardLabel.setForeground(Color.white);
		toBoardLabel.setOpaque(true);
		toBoardLabel.setBackground(Color.black);
		toBoardLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		toBoardLabel.setBounds(325, 230, 150, 40);
		
		toTitieLabel = new JLabel("타이틀 화면으로", JLabel.CENTER);
		toTitieLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		toTitieLabel.setForeground(Color.white);
		toTitieLabel.setOpaque(true);
		toTitieLabel.setBackground(Color.black);
		toTitieLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		toTitieLabel.setBounds(325, 230, 150, 40);
		
		characterList = new ArrayList<>();
	}
	
	public JPanel createPlayerPanel(Character character) {
		JPanel panel = new JPanel(null);
	    panel.setBackground(Color.black);
	    panel.setBounds(0, 0, 180, 250);

	    ImageIcon icon = new ImageIcon("images/" + character.name + ".png");
	    Image image = icon.getImage();
	    double ratio = 0.8;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
	    
	    JLabel imageLabel = new JLabel(icon, JLabel.CENTER);
	    imageLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
	    imageLabel.setBounds(30, 20, 120, 180);
	    panel.add(imageLabel);
	    
	    JPanel HPPanel = new JPanel(null);
	    HPPanel.setBackground(Color.black);
	    HPPanel.setBounds(0, 200, 179, BAR_THICKNESS);
	    panel.add(HPPanel);
	    
	    JPanel SPPanel = new JPanel(null);
	    SPPanel.setBackground(Color.black);
	    SPPanel.setBounds(0, 215, 179, BAR_THICKNESS);
	    panel.add(SPPanel);

	    JPanel APPanel = new JPanel(null);
	    APPanel.setBackground(Color.black);
	    APPanel.setBounds(0, 230, 179, BAR_THICKNESS);
	    panel.add(APPanel);
	    
	    JLabel MHPLabel = new JLabel("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    MHPLabel.setForeground(Color.white);
	    MHPLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
	    MHPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MHP, 179), BAR_THICKNESS);
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
	    MSPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MSP, 179), BAR_THICKNESS);
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
	    MAPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MAP, 179), BAR_THICKNESS);
	    APPanel.add(MAPLabel);

	    JLabel APLabel = new JLabel();
	    APLabel.setOpaque(true);
	    APLabel.setBackground(Color.green);
	    APLabel.setBounds(BAR_MARGIN, 0, (character.MAP <= 0) ? 0 : (int)(1.0 * character.AP / character.MAP * (MAPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    APPanel.add(APLabel);

	    return panel;
	}
	
	public JPanel createEnemyPanel(Character character) {
	    JPanel panel = new JPanel(null);
	    panel.setBackground(Color.black);
	    panel.setBounds(0, 0, 180, 250);

	    ImageIcon icon = new ImageIcon("images/" + character.name + ".png");
	    Image image = icon.getImage();
	    double ratio = 0.8;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
	    
	    JLabel imageLabel = new JLabel(icon, JLabel.CENTER);
	    imageLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
	    imageLabel.setBounds(30, 20, 120, 180);
	    panel.add(imageLabel);
	    
	    JPanel HPPanel = new JPanel(null);
	    HPPanel.setBackground(Color.black);
	    HPPanel.setBounds(0, 200, 179, BAR_THICKNESS);
	    panel.add(HPPanel);
	    
	    JPanel SPPanel = new JPanel(null);
	    SPPanel.setBackground(Color.black);
	    SPPanel.setBounds(0, 215, 179, BAR_THICKNESS);
	    panel.add(SPPanel);

	    JPanel APPanel = new JPanel(null);
	    APPanel.setBackground(Color.black);
	    APPanel.setBounds(0, 230, 179, BAR_THICKNESS);
	    panel.add(APPanel);
	    
	    JLabel MHPLabel = new JLabel("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    MHPLabel.setForeground(Color.white);
	    MHPLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
	    MHPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MHP, 179), BAR_THICKNESS);
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
	    MSPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MSP, 179), BAR_THICKNESS);
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
	    MAPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MAP, 179), BAR_THICKNESS);
	    APPanel.add(MAPLabel);

	    JLabel APLabel = new JLabel();
	    APLabel.setOpaque(true);
	    APLabel.setBackground(Color.green);
	    APLabel.setBounds(BAR_MARGIN, 0, (character.MAP <= 0) ? 0 : (int)(1.0 * character.AP / character.MAP * (MAPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    APPanel.add(APLabel);

	    return panel;
	}
	
	public void updateCharacterUI(JPanel panel, Character character) {
		JPanel HPPanel = (JPanel)panel.getComponent(1);
		JPanel SPPanel = (JPanel)panel.getComponent(2);
		JPanel APPanel = (JPanel)panel.getComponent(3);

		JLabel MHPLabel = (JLabel)HPPanel.getComponent(0);
		JLabel HPLabel = (JLabel)HPPanel.getComponent(1);
		JLabel MSPLabel = (JLabel)SPPanel.getComponent(0);
		JLabel SPLabel = (JLabel)SPPanel.getComponent(1);
		JLabel MAPLabel = (JLabel)APPanel.getComponent(0);
		JLabel APLabel = (JLabel)APPanel.getComponent(1);
		
		double correction = 1.0;
		if(character instanceof Player) {
			correction = 1.0;
		}
		if(character instanceof Enemy) {
			correction = 1.0;
			for(ArrayList<String> eliteList : Enemy.allEliteList) {
				if(eliteList.contains(character.name)) {
					correction = 1.5;
				}
			}
			if(Enemy.allBossList.contains(character.name)) {
				correction = 2.0;
			}
		}
	    
		character.HP = Math.min(character.MHP, character.HP);
	    MHPLabel.setText("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setSize(Math.min(BAR_MARGIN + (int)(character.MHP * correction), HPPanel.getWidth()), BAR_THICKNESS);
	    HPLabel.setSize((character.MHP <= 0) ? 0 : (int)(1.0 * character.HP / character.MHP * (MHPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    
	    character.SP = Math.min(character.MSP, character.SP);
	    MSPLabel.setText("SP " + character.SP + "/" + character.MSP + " ");
	    MSPLabel.setSize(Math.min(BAR_MARGIN + (int)(character.MSP * correction), SPPanel.getWidth()), BAR_THICKNESS);
	    SPLabel.setSize((character.MSP <= 0) ? 0 : (int)(1.0 * character.SP / character.MSP * (MSPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);

		character.MAP = Math.max(100-character.EFC, 0);
	    MAPLabel.setText("AP " + character.AP + "/" + character.MAP + " ");
	    MAPLabel.setSize(Math.min(BAR_MARGIN + (int)(character.MAP * correction), APPanel.getWidth()), BAR_THICKNESS);
	    APLabel.setSize((character.MAP <= 0) ? 0 : (int)(1.0 * character.AP / character.MAP * (MAPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
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

		for(int i=0; i<floorNum; i++) {
			int enemyRate = (int)(Math.random()*Enemy.allEnemyList.get(floorNum-1).size());
			characterList.add(new Enemy(Enemy.allEnemyList.get(floorNum-1).get(enemyRate)));
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
			enemyPanel.getComponent(i).setLocation(370-i*185, 0);
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
	
	public void updateBattleUI() {
		battle.revalidate();
		battle.repaint();
	}
	
	public void resetToOriginStat(Character character) {
		character.MHP = character.originStatPointList.get(0);
		character.MSP = character.originStatPointList.get(1);
		character.HPG = character.originStatPointList.get(2);
		character.SPG = character.originStatPointList.get(3);
		character.ATK = character.originStatPointList.get(4);
		character.DEF = character.originStatPointList.get(5);
		character.SPD = character.originStatPointList.get(6);
		character.EFC = character.originStatPointList.get(7);
		character.HP = Math.min(character.HP, character.MHP);
		character.SP = Math.min(character.SP, character.MSP);
		
		if(character instanceof Player) {
			updateCharacterUI((JPanel)playerPanel.getComponent(0), character);
		}
		if(character instanceof Enemy) {
			updateCharacterUI((JPanel)enemyPanel.getComponent(characterList.indexOf(character) - 1), character);
		}
	}
	
	public void updateEffect(Character character) {
		if(!character.effectList.isEmpty()) {
			for(int i=0; i<character.effectList.size(); i++) {
				Effect effect = character.effectList.get(i);
				
				if(effect.duration <= 0) {
					character.effectList.remove(effect);
					i--;
					continue;
				}
			}
		}
		applyEffect(character);
	}
	
	public void applyEffect(Character character) {
		resetToOriginStat(character);
		
		if(!character.effectList.isEmpty()) {
			for(Effect effect : character.effectList) {
				switch(effect.statName) {
				case "MHP":
					character.MHP += effect.point;
					character.HP = Math.min(character.HP, character.MHP);
					break;
				case "MSP":
					character.MSP += effect.point;
					character.SP = Math.min(character.SP, character. MSP);
					break;
				case "HPG":
					character.HPG += effect.point;
					break;
				case "SPG":
					character.SPG += effect.point;
					break;
				case "ATK":
					character.ATK += effect.point;
					break;
				case "DEF":
					character.DEF += effect.point;
					break;
				case "SPD":
					character.SPD += effect.point;
					break;
				case "EFC":
					character.EFC += effect.point;
					character.MAP = Math.max(100-character.EFC, 0);
					break;
				}
			}
		}
		
		if(character instanceof Player) {
			updateCharacterUI((JPanel)playerPanel.getComponent(0), character);
		}
		if(character instanceof Enemy) {
			updateCharacterUI((JPanel)enemyPanel.getComponent(characterList.indexOf(character) - 1), character);
		}
		Board.updateStat();
		Board.updateDetailUI();
	}
	
	public void startBattleLoop() {
		battleTimer = new Timer(100, e -> {	
			if(isPaused)
				return;
			
			for(int i=0; i<characterList.size(); i++) {
				JPanel panel = (JPanel)(i==0 ? playerPanel.getComponent(i) : enemyPanel.getComponent(i-1));
				Character character = characterList.get(i);
				
				if(character.HP <= 0) {
					if(character instanceof Player) {
						characterList.remove(character);
						playerPanel.remove(0);
						updateBattleUI();
						
						loseBattle();
						return;
					}
					if(character instanceof Enemy) {
						if((character.name.charAt(character.name.length() - 1) - 0xAC00) % 28 > 0) {
							Board.updateNotice(character.name + "을 처치했다!");
						}
						else {
							Board.updateNotice(character.name + "를 처치했다!");
						}
						characterList.remove(i);
						enemyPanel.remove(i-1);
						updateBattleUI();
						if(enemyPanel.getComponentCount() <= 0) {
							winBattle();
						}
						return;
					}
				}
				if(character.AP >= character.MAP) {
					isPaused = true;
					startTurn(character);
					return;
				}
				if(character.AP < character.MAP) {
					character.AP = Math.min(character.AP + character.SPD, character.MAP);
					updateCharacterUI(panel, character);
				}
			}
			
			updateBattleUI();
		});
		
		for(SkillLabel label : Board.skillLabelList) {
			label.nameLabel.removeMouseListener(label.outBattleListener);
			label.nameLabel.addMouseListener(label.inBattleListener);
		}
		for(ItemLabel label : Board.itemLabelList) {
			label.nameLabel.removeMouseListener(label.outBattleListener);
			label.nameLabel.addMouseListener(label.inBattleListener);
		}
		player.AP = 0;
		
		isPaused = false;
		battleTimer.start();
	}
	
	public void winBattle() {
		Board.updateNotice("전투에서 승리했다!");
		
		resetToOriginStat(player);
		player.levelUp(1);
		updateCharacterUI((JPanel)playerPanel.getComponent(0), player);
		updateBattleUI();
		player.getItem(Math.min(Board.currentFloor - (int)(Math.random() * 2), 2));
		
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
	
	public void loseBattle() {
		Board.updateNotice("전투에서 패배했다...");
		
		battle.add(toTitieLabel);
		battle.setComponentZOrder(toTitieLabel, 0);
		toTitieLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				SceneManager.loadScene(new Title());
				battle.remove(toTitieLabel);
				toTitieLabel.removeMouseListener(this);
				toTitieLabel.setForeground(Color.white);
				toTitieLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				toTitieLabel.setForeground(Color.darkGray);
				toTitieLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toTitieLabel.setForeground(Color.white);
				toTitieLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				toTitieLabel.setForeground(Color.gray);
				toTitieLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		battleTimer.stop();
	}
	
	public void startTurn(Character character) {
		character.HP = Math.max(Math.min(character.HP + character.HPG, character.MHP), 0);
		character.SP = Math.max(Math.min(character.SP + character.SPG, character.MSP), 0);
		
		updateEffect(character);
		
		if(character instanceof Player) {
			Player player = (Player)character;
			JPanel pPanel = (JPanel)playerPanel.getComponent(0);
			updateCharacterUI(pPanel, character);
			if(character.HPG < 0) {
				Board.updateNotice(character.name + "에게 " + Math.abs(character.HPG) + "의 지속 피해!");
				blinkCharacter(pPanel, 3, null, () -> {
					if(character.HP <= 0) {
						characterList.remove(character);
						playerPanel.remove(pPanel);
						updateBattleUI();
						
						loseBattle();
						return;
					}
					else {
						takePlayerTurn(pPanel, player);
					}
				});
			}
			else {
				takePlayerTurn(pPanel, player);
			}
		}
		if(character instanceof Enemy) {
			Enemy enemy = (Enemy)character;
			JPanel pPanel = (JPanel)playerPanel.getComponent(0);
			JPanel ePanel = (JPanel)enemyPanel.getComponent(characterList.indexOf(enemy) - 1);
			updateCharacterUI(ePanel, enemy);
			if(character.HPG < 0) {
				Board.updateNotice(character.name + "에게 " + Math.abs(character.HPG) + "의 지속 피해!");
				blinkCharacter(ePanel, 3, null, () -> {
					if(enemy.HP <= 0) {
						if((enemy.name.charAt(enemy.name.length() - 1) - 0xAC00) % 28 > 0) {
							Board.updateNotice(enemy.name + "을 처치했다!");
						}
						else {
							Board.updateNotice(enemy.name + "를 처치했다!");
						}
						characterList.remove(enemy);
						enemyPanel.remove(ePanel);
						updateBattleUI();
						
						if(enemyPanel.getComponentCount() <= 0) {
							winBattle();
							return;	
						}
						else {
							isPaused = false;
						}
					}
					else {
						takeEnemyTurn(pPanel, ePanel, enemy);
					}
				});
			}
			else {
				takeEnemyTurn(pPanel, ePanel, enemy);
			}
		}
		
		for(Effect effect : character.effectList) {
			effect.duration--;
		}
		
		character.AP = 0;
	}
	
	public void takePlayerTurn(JPanel pPanel, Player player) {
		Board.updateNotice(player.name + "의 턴!");
		updateCharacterUI(pPanel, player);
		blinkCharacter(pPanel, 1, Color.white, null);
		activateSkillLabel();
		activateItemLabel();
	}
	
	public void takeEnemyTurn(JPanel pPanel, JPanel ePanel, Enemy enemy) {
		updateCharacterUI(ePanel, enemy);
		if(enemy.skill != null && enemy.SP >= enemy.MSP) {
			Board.updateNotice(enemy.name + "의 " + enemy.skill + "!");
			blinkCharacter(ePanel, 3, Color.white, () -> {
				switch(enemy.skill) {
				case Enemy.WAND_SKILL:
					enemy.SP = 0;
					player.HP = Math.max(player.HP - enemy.ATK, 0);
					Board.updateNotice(player.name + "에게 " + enemy.ATK + "의 관통 피해!");
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
				}

				applyEffect(player);
				updateEffect(enemy);
				updateCharacterUI(pPanel, player);
				updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
				updateBattleUI();
				
			});
		}
		else {
			Board.updateNotice(player.name + "의 턴!");
			updateCharacterUI(ePanel, enemy);
			blinkCharacter(ePanel, 1, Color.white, () -> {
				takeDamage(player, enemy.ATK);

				applyEffect(player);
				updateEffect(enemy);
				updateCharacterUI(pPanel, player);
				updateCharacterUI(ePanel, enemy);
				updateBattleUI();
				
				blinkCharacter(pPanel, 3, null, () -> {
					isPaused = false;
				});
			});
		}
	}
	
	public void takeDamage(Character character, int point) {
		int damage = (int)(point / (1.0 + character.DEF * 0.01));
		character.HP = Math.max(character.HP - damage, 0);
		Board.updateNotice(character.name + "에게 " + damage + "의 피해! " + "(방어력에 의해 " + (int)(100 - (100 / (1.0 + character.DEF * 0.01))) + "% 감소)");
	}

	public void activateSkillLabel() {
		Board.updateSkill();
		Board.updateDetailUI();
		
		for(SkillLabel label : Board.skillLabelList) {
			label.nameLabel.setForeground(Color.white);
			for(MouseListener listener : label.nameLabel.getMouseListeners()) {
				label.nameLabel.removeMouseListener(listener);
			}
			label.nameLabel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					JPanel pPanel = (JPanel)playerPanel.getComponent(0);
					
					if(player.SP < label.cost) {
						Board.updateNotice("SP가 부족합니다.");
						label.nameLabel.setForeground(Color.gray);
						label.nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
						return;
					}
					if(pPanel.getMouseListeners() != null) {
						for(MouseListener listener : pPanel.getMouseListeners()) {
							pPanel.removeMouseListener(listener);
							pPanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						}
					}
					for(int i=0; i<enemyPanel.getComponentCount(); i++) {
						JPanel ePanel = (JPanel)enemyPanel.getComponent(i);
						if(ePanel.getMouseListeners() != null) {
							for(MouseListener listener : ePanel.getMouseListeners()) {
								ePanel.removeMouseListener(listener);
								ePanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
							}
						}
					}
					for(ItemLabel iLabel : Board.itemLabelList) {
						iLabel.isSelected = false;
						iLabel.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						iLabel.descriptionLabel.setVisible(false);
					}
					
					if(!label.isSelected) {
						for(SkillLabel sLabel : Board.skillLabelList) {
							sLabel.isSelected = false;
							sLabel.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						}
						label.isSelected = true;
						label.nameLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
						Board.updateNotice("대상을 선택하세요.");
						switch (label.target) {
						case PLAYER:
							pPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
							pPanel.addMouseListener(new MouseListener() {
								
								@Override
								public void mouseReleased(MouseEvent e) {
									updateEffect(player);
									
									switch(label.name) {
									case Skill.CHARGE:
										player.SP = Math.max(player.SP - label.cost, 0);
										player.effectList.add(new Effect("ATK", label.points.get(0).intValue(), label.points.get(1).intValue()));
										player.effectList.add(new Effect("SPD", label.points.get(0).intValue(), -label.points.get(2).intValue()));
										player.effectList.get(player.effectList.size()-1).duration--;
										Board.updateNotice(player.name + "의 " + label.name + "!");
										break;
									case Skill.DASH:
										player.SP = Math.max(player.SP - label.cost, 0);
										player.effectList.add(new Effect("ATK", label.points.get(0).intValue(), label.points.get(1).intValue()));
										player.effectList.add(new Effect("SPD", label.points.get(0).intValue(), label.points.get(2).intValue()));
										Board.updateNotice(player.name + "의 " + label.name + "!");
										break;
									case Skill.DEFENCE:
										player.SP = Math.max(player.SP - label.cost, 0);
										player.effectList.add(new Effect("DEF", label.points.get(0).intValue(), label.points.get(1).intValue()));
										Board.updateNotice(player.name + "의 " + label.name + "!");
										break;
									case Skill.HEALING:
										player.SP = Math.max(player.SP - label.cost, 0);
										player.HP = Math.min((int)(player.HP + label.points.get(0)), player.MHP);
										player.effectList.add(new Effect("HPG", label.points.get(1).intValue(), label.points.get(2).intValue()));
										Board.updateNotice(player.name + "에게 " + label.points.get(0).intValue() + "의 회복!");
										break;
									}

									applyEffect(player);
									updateCharacterUI(pPanel, player);
									updateBattleUI();
									
									Board.updateSkill();
									Board.updateStat();
									Board.updateDetailUI();
									
									blinkCharacter(pPanel, 3, null, () -> {
										isPaused = false;
									});

									pPanel.removeMouseListener(this);
									pPanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
									label.isSelected = false;
									label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
									label.costLabel.setVisible(false);
									label.descriptionLabel.setVisible(false);
									for(SkillLabel sLabel : Board.skillLabelList) {
										sLabel.nameLabel.setForeground(Color.gray);
										for(MouseListener listener : sLabel.nameLabel.getMouseListeners()) {
											sLabel.nameLabel.removeMouseListener(listener);
										}
										sLabel.nameLabel.addMouseListener(sLabel.inBattleListener);
									}
									for(ItemLabel iLabel : Board.itemLabelList) {
										iLabel.nameLabel.setForeground(Color.gray);
										for(MouseListener listener : iLabel.nameLabel.getMouseListeners()) {
											iLabel.nameLabel.removeMouseListener(listener);
										}
										iLabel.nameLabel.addMouseListener(iLabel.inBattleListener);
									}
								}
								
								@Override
								public void mousePressed(MouseEvent e) {
									pPanel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									pPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
								}
								
								@Override
								public void mouseEntered(MouseEvent e) {
									pPanel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
								}
								
								@Override
								public void mouseClicked(MouseEvent e) {
								}
							});
							break;
						case ENEMY:
							for(int i=0; i<enemyPanel.getComponentCount(); i++) {
								final int index = i;
								JPanel ePanel = (JPanel)enemyPanel.getComponent(i);
								ePanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
								ePanel.addMouseListener(new MouseListener() {
									
									@Override
									public void mouseReleased(MouseEvent e) {
										updateEffect(player);
										
										Character enemy = characterList.get(index+1);
										
										switch(label.name) {
										case Skill.ATTACK:
											player.SP = Math.max(player.SP - label.cost, 0);
											takeDamage(enemy, label.points.get(0).intValue());
											break;
										case Skill.STRONG:
											player.SP = Math.max(player.SP - label.cost, 0);
											takeDamage(enemy, label.points.get(0).intValue());
											enemy.effectList.add(new Effect("SPD", label.points.get(1).intValue(), (int)(enemy.SPD / label.points.get(2))));
											break;
										case Skill.FAST:
											player.SP = Math.max(player.SP - label.cost, 0);
											player.effectList.add(new Effect("EFC", label.points.get(1).intValue(), label.points.get(2).intValue()));
											takeDamage(enemy, label.points.get(0).intValue());
											blinkCharacter(pPanel, 3, null, null);
											break;
										case Skill.TACKLE:
											player.SP = Math.max(player.SP - label.cost, 0);
											player.effectList.add(new Effect("DEF", label.points.get(0).intValue(), label.points.get(1).intValue()));
											player.effectList.get(player.effectList.size()-1).duration--;
											takeDamage(enemy, label.points.get(2).intValue());
											blinkCharacter(pPanel, 3, null, null);
											break;
										}
										
										applyEffect(player);
										updateCharacterUI(pPanel, player);
										updateCharacterUI(ePanel, enemy);
										updateBattleUI();

										Board.updateSkill();
										Board.updateStat();
										Board.updateDetailUI();
										
										blinkCharacter(ePanel, 3, null, () -> {
											isPaused = false;
											if(enemy.HP <= 0) {
												if((enemy.name.charAt(enemy.name.length() - 1) - 0xAC00) % 28 > 0) {
													Board.updateNotice(enemy.name + "을 처치했다!");
												}
												else {
													Board.updateNotice(enemy.name + "를 처치했다!");
												}
												characterList.remove(enemy);
												enemyPanel.remove(ePanel);
												updateBattleUI();
												
												if(enemyPanel.getComponentCount() <= 0) {
													winBattle();
													return;	
												}
											}
										});

										for(int i=0; i<enemyPanel.getComponentCount(); i++) {
											JPanel ePanel = (JPanel)enemyPanel.getComponent(i);
											if(ePanel.getMouseListeners() != null) {
												for(MouseListener listener : ePanel.getMouseListeners()) {
													ePanel.removeMouseListener(listener);
													ePanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
												}
											}
										}
										label.isSelected = false;
										label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
										label.costLabel.setVisible(false);
										label.descriptionLabel.setVisible(false);
										for(SkillLabel sLabel : Board.skillLabelList) {
											sLabel.nameLabel.setForeground(Color.gray);
											for(MouseListener listener : sLabel.nameLabel.getMouseListeners()) {
												sLabel.nameLabel.removeMouseListener(listener);
											}
											sLabel.nameLabel.addMouseListener(sLabel.inBattleListener);
										}
										for(ItemLabel iLabel : Board.itemLabelList) {
											iLabel.nameLabel.setForeground(Color.gray);
											for(MouseListener listener : iLabel.nameLabel.getMouseListeners()) {
												iLabel.nameLabel.removeMouseListener(listener);
											}
											iLabel.nameLabel.addMouseListener(iLabel.inBattleListener);
										}
									}
									
									@Override
									public void mousePressed(MouseEvent e) {
										ePanel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
									}
									
									@Override
									public void mouseExited(MouseEvent e) {
										ePanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
									}
									
									@Override
									public void mouseEntered(MouseEvent e) {
										ePanel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
									}
									
									@Override
									public void mouseClicked(MouseEvent e) {
									}
								});
							}
							break;
						}
					}
					else {
						label.isSelected = false;
						label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						label.costLabel.setVisible(false);
						label.descriptionLabel.setVisible(false);
					}
					label.nameLabel.setForeground(Color.white);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					label.nameLabel.setForeground(Color.darkGray);
					label.nameLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					label.nameLabel.setForeground(Color.white);
					if(!label.isSelected) {
						label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						for(SkillLabel sLabel : Board.skillLabelList) {
							if(!sLabel.isSelected) {
								sLabel.costLabel.setVisible(false);
								sLabel.descriptionLabel.setVisible(false);
							}
							else {
								sLabel.costLabel.setVisible(true);
								sLabel.descriptionLabel.setVisible(true);
							}
						}
					}
					else {
						label.nameLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					for(SkillLabel sLabel : Board.skillLabelList) {
						sLabel.costLabel.setVisible(false);
						sLabel.descriptionLabel.setVisible(false);
					}
					label.nameLabel.setForeground(Color.gray);
					label.nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					label.costLabel.setVisible(true);
					label.descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
	}
	
	public void activateItemLabel() {
		Board.updateItem();
		Board.updateDetailUI();
		
		for(ItemLabel label : Board.itemLabelList) {
			label.nameLabel.setForeground(Color.white);
			for(MouseListener listener : label.nameLabel.getMouseListeners()) {
				label.nameLabel.removeMouseListener(listener);
			}
			label.nameLabel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					JPanel pPanel = (JPanel)playerPanel.getComponent(0);
					if(pPanel.getMouseListeners() != null) {
						for(MouseListener listener : pPanel.getMouseListeners()) {
							pPanel.removeMouseListener(listener);
							pPanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						}
					}
					for(int i=0; i<enemyPanel.getComponentCount(); i++) {
						JPanel ePanel = (JPanel)enemyPanel.getComponent(i);
						if(ePanel.getMouseListeners() != null) {
							for(MouseListener listener : ePanel.getMouseListeners()) {
								ePanel.removeMouseListener(listener);
								ePanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
							}
						}
					}
					for(SkillLabel sLabel : Board.skillLabelList) {
						sLabel.isSelected = false;
						sLabel.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						sLabel.costLabel.setVisible(false);
						sLabel.descriptionLabel.setVisible(false);
					}
					
					if(!label.isSelected) {
						for(ItemLabel iLabel : Board.itemLabelList) {
							iLabel.isSelected = false;
							iLabel.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						}
						label.isSelected = true;
						label.nameLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
						Board.updateNotice("대상을 선택하세요.");
						switch (label.target) {
						case PLAYER:
							pPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
							pPanel.addMouseListener(new MouseListener() {
								
								@Override
								public void mouseReleased(MouseEvent e) {
									updateEffect(player);
									
									switch(label.nameLabel.getText().strip()) {
									case Item.POTION:
										player.HP = Math.min((int)(player.HP + player.MHP * label.points.get(0)), player.MHP);
										player.SP = Math.min((int)(player.SP + player.MSP * label.points.get(0)), player.MSP);
										player.AP = Math.min((int)(player.AP + player.MAP * label.points.get(0)), player.MAP);
										Board.updateNotice(player.name + "에게 " + ((int)(player.MHP * label.points.get(0))) + "의 회복!");
										break;
									case Item.SUPER_POTION:
										player.HP = Math.min((int)(player.HP + player.MHP * label.points.get(0)), player.MHP);
										player.SP = Math.min((int)(player.SP + player.MSP * label.points.get(0)), player.MSP);
										player.AP = Math.min((int)(player.AP + player.MAP * label.points.get(0)), player.MAP);
										Board.updateNotice(player.name + "에게 " + ((int)(player.MHP * label.points.get(0))) + "의 회복!");
										break;
									case Item.MEGA_POTION:
										player.HP = Math.min((int)(player.HP + player.MHP * label.points.get(0)), player.MHP);
										player.SP = Math.min((int)(player.SP + player.MSP * label.points.get(0)), player.MSP);
										player.AP = Math.min((int)(player.AP + player.MAP * label.points.get(0)), player.MAP);
										Board.updateNotice(player.name + "에게 " + ((int)(player.MHP * label.points.get(0))) + "의 회복!");
										break;
									case Item.SOUL_ESSENCE:
										player.HP = Math.min((int)(player.HP + player.MHP * label.points.get(0)), player.MHP);
										player.SP = Math.min((int)(player.SP + player.MSP * label.points.get(0)), player.MSP);
										player.AP = Math.min((int)(player.AP + player.MAP * label.points.get(0)), player.MAP);
										Board.updateNotice(player.name + "에게 " + ((int)(player.MHP * label.points.get(0))) + "의 회복!");
										break;
									}
									
									applyEffect(player);
									updateCharacterUI(pPanel, player);
									updateBattleUI();
									
									player.itemList.remove(Board.itemLabelList.indexOf(label));
									Board.updateItem();
									Board.updateStat();
									Board.updateDetailUI();
									
									blinkCharacter(pPanel, 3, null, () -> {
										isPaused = false;
									});

									pPanel.removeMouseListener(this);
									pPanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
									label.isSelected = false;
									label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
									label.descriptionLabel.setVisible(false);
									for(ItemLabel iLabel : Board.itemLabelList) {
										iLabel.nameLabel.setForeground(Color.gray);
										for(MouseListener listener : iLabel.nameLabel.getMouseListeners()) {
											iLabel.nameLabel.removeMouseListener(listener);
										}
										iLabel.nameLabel.addMouseListener(iLabel.inBattleListener);
									}
									for(SkillLabel sLabel : Board.skillLabelList) {
										sLabel.nameLabel.setForeground(Color.gray);
										for(MouseListener listener : sLabel.nameLabel.getMouseListeners()) {
											sLabel.nameLabel.removeMouseListener(listener);
										}
										sLabel.nameLabel.addMouseListener(sLabel.inBattleListener);
									}
								}
								
								@Override
								public void mousePressed(MouseEvent e) {
									pPanel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									pPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
								}
								
								@Override
								public void mouseEntered(MouseEvent e) {
									pPanel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
								}
								
								@Override
								public void mouseClicked(MouseEvent e) {
								}
							});
							break;
						case ENEMY:
							for(int i=0; i<enemyPanel.getComponentCount(); i++) {
								JPanel ePanel = (JPanel)enemyPanel.getComponent(i);
								ePanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
								
								final int index = i;
								
								ePanel.addMouseListener(new MouseListener() {
									
									@Override
									public void mouseReleased(MouseEvent e) {
										updateEffect(player);
										
										Character enemy = characterList.get(index+1);
										
										switch(label.nameLabel.getText().strip()) {
										case Item.SPEAR:
											takeDamage(enemy, label.points.get(0).intValue());
											break;
										case Item.SUPER_SPEAR:
											takeDamage(enemy, label.points.get(0).intValue());
											break;
										case Item.MEGA_SPEAR:
											takeDamage(enemy, label.points.get(0).intValue());
											break;
											
										case Item.ACID:
											enemy.effectList.add(new Effect("ATK", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(4) * label.points.get(1))));
											enemy.effectList.add(new Effect("DEF", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(5) * label.points.get(2))));
											Board.updateNotice(enemy.name + "의 공격력 " + ((int)(enemy.originStatPointList.get(4) * label.points.get(1)) + " 및 방어력 " + ((int)(enemy.originStatPointList.get(5) * label.points.get(2)) + " 감소!")));
											break;
										case Item.SUPER_ACID:
											enemy.effectList.add(new Effect("ATK", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(4) * label.points.get(1))));
											enemy.effectList.add(new Effect("DEF", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(5) * label.points.get(2))));
											Board.updateNotice(enemy.name + "의 공격력 " + ((int)(enemy.originStatPointList.get(4) * label.points.get(1)) + " 및 방어력 " + ((int)(enemy.originStatPointList.get(5) * label.points.get(2)) + " 감소!")));
											break;
										case Item.MEGA_ACID:
											enemy.effectList.add(new Effect("ATK", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(4) * label.points.get(1))));
											enemy.effectList.add(new Effect("DEF", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(5) * label.points.get(2))));
											Board.updateNotice(enemy.name + "의 공격력 " + ((int)(enemy.originStatPointList.get(4) * label.points.get(1)) + " 및 방어력 " + ((int)(enemy.originStatPointList.get(5) * label.points.get(2)) + " 감소!")));
											break;
											
										case Item.POISON:
											enemy.effectList.add(new Effect("HPG", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(0) * label.points.get(1))));
											Board.updateNotice(enemy.name + "에게 " + ((int)(enemy.originStatPointList.get(0) * label.points.get(1))) + "의 중독!");
											break;
										case Item.SUPER_POISON:
											enemy.effectList.add(new Effect("HPG", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(0) * label.points.get(1))));
											Board.updateNotice(enemy.name + "에게 " + ((int)(enemy.originStatPointList.get(0) * label.points.get(1))) + "의 중독!");
											break;
										case Item.MEGA_POISON:
											enemy.effectList.add(new Effect("HPG", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(0) * label.points.get(1))));
											Board.updateNotice(enemy.name + "에게 " + ((int)(enemy.originStatPointList.get(0) * label.points.get(1))) + "의 중독!");
											break;

										case Item.FLAME:
											takeDamage(enemy, (int)(label.points.get(0).intValue()*1.5));
											enemy.effectList.add(new Effect("HPG", label.points.get(1).intValue(), -label.points.get(0).intValue()));
											Board.updateNotice(enemy.name + "에게 " + label.points.get(0).intValue() + "의 화상!");
											break;
										case Item.SUPER_FLAME:
											takeDamage(enemy, (int)(label.points.get(0).intValue()*1.5));
											enemy.effectList.add(new Effect("HPG", label.points.get(1).intValue(), -label.points.get(0).intValue()));
											Board.updateNotice(enemy.name + "에게 " + label.points.get(0).intValue() + "의 화상!");
											break;
										case Item.MEGA_FLAME:
											takeDamage(enemy, (int)(label.points.get(0).intValue()*1.5));
											enemy.effectList.add(new Effect("HPG", label.points.get(1).intValue(), -label.points.get(0).intValue()));
											Board.updateNotice(enemy.name + "에게 " + label.points.get(0).intValue() + "의 화상!");
											break;

										case Item.TIME:
											enemy.AP = 0;
											enemy.effectList.add(new Effect("SPD", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(6) * label.points.get(1))));
											Board.updateNotice(enemy.name + "에게 시간 조작!");
											break;
										case Item.SUPER_TIME:
											enemy.AP = 0;
											enemy.effectList.add(new Effect("SPD", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(6) * label.points.get(1))));
											Board.updateNotice(enemy.name + "에게 시간 조작!");
											break;
										case Item.MEGA_TIME:
											enemy.AP = 0;
											enemy.effectList.add(new Effect("SPD", label.points.get(0).intValue(), -(int)(enemy.originStatPointList.get(6) * label.points.get(1))));
											Board.updateNotice(enemy.name + "에게 시간 조작!");
											break;
										}

										applyEffect(player);
										applyEffect(enemy);
										updateCharacterUI(pPanel, player);
										updateCharacterUI(ePanel, enemy);
										updateBattleUI();
										
										player.itemList.remove(Board.itemLabelList.indexOf(label));
										Board.updateItem();
										Board.updateStat();
										Board.updateDetailUI();
										
										blinkCharacter(ePanel, 3, null, () -> {
											isPaused = false;
											if(enemy.HP <= 0) {
												if((enemy.name.charAt(enemy.name.length() - 1) - 0xAC00) % 28 > 0) {
													Board.updateNotice(enemy.name + "을 처치했다!");
												}
												else {
													Board.updateNotice(enemy.name + "를 처치했다!");
												}
											}
											if(enemy.HP <= 0) {
												characterList.remove(enemy);
												enemyPanel.remove(ePanel);
												updateBattleUI();
												
												if(enemyPanel.getComponentCount() <= 0) {
													winBattle();
													return;	
												}
											}
										});

										for(int i=0; i<enemyPanel.getComponentCount(); i++) {
											JPanel ePanel = (JPanel)enemyPanel.getComponent(i);
											if(ePanel.getMouseListeners() != null) {
												for(MouseListener listener : ePanel.getMouseListeners()) {
													ePanel.removeMouseListener(listener);
													ePanel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
												}
											}
										}
										label.isSelected = false;
										label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
										label.descriptionLabel.setVisible(false);
										for(ItemLabel iLabel : Board.itemLabelList) {
											iLabel.nameLabel.setForeground(Color.gray);
											for(MouseListener listener : iLabel.nameLabel.getMouseListeners()) {
												iLabel.nameLabel.removeMouseListener(listener);
											}
											iLabel.nameLabel.addMouseListener(iLabel.inBattleListener);
										}
										for(SkillLabel sLabel : Board.skillLabelList) {
											sLabel.nameLabel.setForeground(Color.gray);
											for(MouseListener listener : sLabel.nameLabel.getMouseListeners()) {
												sLabel.nameLabel.removeMouseListener(listener);
											}
											sLabel.nameLabel.addMouseListener(sLabel.inBattleListener);
										}
									}
									
									@Override
									public void mousePressed(MouseEvent e) {
										ePanel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
									}
									
									@Override
									public void mouseExited(MouseEvent e) {
										ePanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
									}
									
									@Override
									public void mouseEntered(MouseEvent e) {
										ePanel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
									}
									
									@Override
									public void mouseClicked(MouseEvent e) {
									}
								});
							}
							break;
						}
					}
					else {
						label.isSelected = false;
						label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						label.descriptionLabel.setVisible(false);
					}
					label.nameLabel.setForeground(Color.white);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					label.nameLabel.setForeground(Color.darkGray);
					label.nameLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					label.nameLabel.setForeground(Color.white);
					if(!label.isSelected) {
						label.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						for(ItemLabel sLabel : Board.itemLabelList) {
							if(!sLabel.isSelected) {
								sLabel.descriptionLabel.setVisible(false);
							}
							else {
								sLabel.descriptionLabel.setVisible(true);
							}
						}
					}
					else {
						label.nameLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					for(ItemLabel sLabel : Board.itemLabelList) {
						sLabel.descriptionLabel.setVisible(false);
					}
					label.nameLabel.setForeground(Color.gray);
					label.nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					label.descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
	}
	
	public void blinkCharacter(JPanel panel, int blinkCount, Color blinkColor, Runnable onComplete) {
		Timer blinkTimer = new Timer(50, null);
		final int[] count = {0};
		Color originalColor = panel.getBackground();
		
		blinkTimer.addActionListener(e -> {
			if(count[0] < blinkCount * 2) {
				if(blinkColor != null) {
					panel.setBackground(panel.getBackground() == blinkColor ? originalColor : blinkColor);
				}
				else {
					panel.setVisible(!panel.isVisible());
				}
				count[0]++;
			}
			else {
				blinkTimer.stop();
				if(blinkColor != null) {
					panel.setBackground(originalColor);
				}
				else {
					panel.setVisible(true);
				}
				if(onComplete != null) onComplete.run();
			}
		});
		
		blinkTimer.start();
	}
	
}
