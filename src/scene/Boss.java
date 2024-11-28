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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import character.Character;
import character.Enemy;
import character.Player;
import scene.Board.ItemLabel;
import scene.Board.SkillLabel;

public class Boss extends Battle{
	
	JLabel toNextFloorLabel;
	
	public Boss() {
		playerPanel.setBounds(25, 25, 300, 250);
		
		enemyPanel.setBounds(450, 25, 300, 250);
		
		toNextFloorLabel = new JLabel("다음 층으로", JLabel.CENTER);
		toNextFloorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		toNextFloorLabel.setForeground(Color.white);
		toNextFloorLabel.setOpaque(true);
		toNextFloorLabel.setBackground(Color.black);
		toNextFloorLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		toNextFloorLabel.setBounds(325, 230, 150, 40);
	}
	
	private JPanel createCharacterPanel(Character character) {
		JPanel panel = new JPanel(null);
	    panel.setBackground(Color.black);
	    panel.setBounds(0, 0, 300, 250);

	    ImageIcon icon = new ImageIcon();
    	icon = new ImageIcon("images/" + character.name + ".png");
	    Image image = icon.getImage();
	    double ratio = 0.8;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
	    
	    JLabel imageLabel = new JLabel(icon, JLabel.CENTER);
	    imageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
	    imageLabel.setForeground(Color.white);
	    imageLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
	    imageLabel.setBounds(60, 20, 180, 180);
	    panel.add(imageLabel);
	    
	    JPanel HPPanel = new JPanel(null);
	    HPPanel.setBackground(Color.black);
	    HPPanel.setBounds(0, 200, 299, BAR_THICKNESS);
	    panel.add(HPPanel);
	    
	    JPanel SPPanel = new JPanel(null);
	    SPPanel.setBackground(Color.black);
	    SPPanel.setBounds(0, 215, 299, BAR_THICKNESS);
	    panel.add(SPPanel);

	    JPanel APPanel = new JPanel(null);
	    APPanel.setBackground(Color.black);
	    APPanel.setBounds(0, 230, 299, BAR_THICKNESS);
	    panel.add(APPanel);
	    
	    JLabel MHPLabel = new JLabel("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    MHPLabel.setForeground(Color.white);
	    MHPLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
	    MHPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MHP * 2), 239), BAR_THICKNESS);
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
	    MSPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MSP * 4), 239), BAR_THICKNESS);
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
	    MAPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MAP * 2), 239), BAR_THICKNESS);
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
	    
		MHPLabel.setText("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setSize(Math.min(BAR_MARGIN + (int)(character.MHP * 2), 239), BAR_THICKNESS);
	    HPLabel.setSize((character.MHP <= 0) ? 0 : (int)(1.0 * character.HP / character.MHP * (MHPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    
	    MSPLabel.setText("SP " + character.SP + "/" + character.MSP + " ");
	    MSPLabel.setSize(Math.min(BAR_MARGIN + (int)(character.MSP * 4), 239), BAR_THICKNESS);
	    SPLabel.setSize((character.MSP <= 0) ? 0 : (int)(1.0 * character.SP / character.MSP * (MSPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);

		character.MAP = 100 - character.EFC;
	    MAPLabel.setText("AP " + character.AP + "/" + character.MAP + " ");
	    MAPLabel.setSize(Math.min(BAR_MARGIN + (int)(character.MAP * 2), 239), BAR_THICKNESS);
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
		playerPanel.add(createCharacterPanel(characterList.get(0)));

		characterList.add(new Enemy(Enemy.allBossList.get(floorNum-1)));
		characterList.get(1).originStatPointList = new ArrayList<>(Arrays.asList(
				characterList.get(1).MHP,
				characterList.get(1).MSP,
				characterList.get(1).HPG,
				characterList.get(1).SPG,
				characterList.get(1).ATK,
				characterList.get(1).DEF,
				characterList.get(1).SPD,
				characterList.get(1).EFC));
		characterList.get(1).effectList = new ArrayList<>();
		characterList.get(1).MAP = 100 - characterList.get(1).EFC;
		enemyPanel.add(createCharacterPanel(characterList.get(1)));

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
	
	public void startBattleLoop() {
		battleTimer = new Timer(100, e -> {	
			if(isPaused)
				return;
			
			for(int i=0; i<characterList.size(); i++) {
				JPanel panel = (JPanel)(i==0 ? playerPanel.getComponent(i) : enemyPanel.getComponent(i-1));
				Character character = characterList.get(i);
				
				if(character.HP <= 0) {
					if(character instanceof Player) {
						playerPanel.remove(0);
						updateBattleUI();
						
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
						return;
					}
					if(character instanceof Enemy) {
						if((character.name.charAt(character.name.length() - 1) - 0xAC00) % 28 > 0) {
							Board.updateNotice(character.name + "을 처치했다!");
						}
						else {
							Board.updateNotice(character.name + "를 처치했다!");
						}
						enemyPanel.remove(i-1);
						updateBattleUI();
						if(enemyPanel.getComponentCount() <= 0) {
							if(Board.currentFloor < 3) {
								resetToOriginStat(player);
								player.levelUp(3);
								updateCharacterUI((JPanel)playerPanel.getComponent(0), player);
								updateBattleUI();
								player.getItem(player.LEGEND);
							}
							battle.add(toNextFloorLabel);
							battle.setComponentZOrder(toNextFloorLabel, 0);
							toNextFloorLabel.addMouseListener(new MouseListener() {
								
								@Override
								public void mouseReleased(MouseEvent e) {
									if(Board.currentFloor >= 3) {
										new Ending();
									}
									else {
										Board.generateFloor(++Board.currentFloor);
									}
									battle.remove(toNextFloorLabel);
									toNextFloorLabel.removeMouseListener(this);
									toNextFloorLabel.setForeground(Color.white);
									toNextFloorLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
								}
								
								@Override
								public void mousePressed(MouseEvent e) {
									toNextFloorLabel.setForeground(Color.darkGray);
									toNextFloorLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
									
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									toNextFloorLabel.setForeground(Color.white);
									toNextFloorLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
								}
								
								@Override
								public void mouseEntered(MouseEvent e) {
									toNextFloorLabel.setForeground(Color.gray);
									toNextFloorLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
								}
								
								@Override
								public void mouseClicked(MouseEvent e) {
								}
							});
						}
						battleTimer.stop();
						return;
					}
				}
				if(character.AP >= character.MAP) {
					isPaused = true;
					startTurn(character);
					break;
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
		
		battleTimer.start();
	}
	
	public void startTurn(Character character) {
		character.HP = Math.min(character.HP + character.HPG, character.MHP);
		character.SP = Math.min(character.SP + character.SPG, character.MSP);
		
		if(character instanceof Player) {
			JPanel pPanel = (JPanel)playerPanel.getComponent(0);
			updateCharacterUI(pPanel, character);
			
			Board.updateNotice(character.name + "의 턴!");
			
			blinkCharacter(pPanel, 1, Color.white, null);
			activateSkillLabel();
			activateItemLabel();
		}
		if(character instanceof Enemy) {
			Enemy enemy = (Enemy)character;
			JPanel pPanel = (JPanel)playerPanel.getComponent(0);
			JPanel ePanel = (JPanel)enemyPanel.getComponent(characterList.indexOf(enemy) - 1);
			updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
			if(enemy.SP >= enemy.MSP) {
				Board.updateNotice(enemy.name + "의 " + enemy.skill + "!");
				blinkCharacter(ePanel, 3, Color.white, () -> {
					int point;
					switch(enemy.skill) {
					case Enemy.WARRIOR_SKULL_SKILL:
						point = (int)(enemy.ATK * 2 / (1 + player.DEF * 0.01));
						player.HP = Math.max(player.HP - point, 0);
						enemy.SP = 0;
						Board.updateNotice(player.name + "에게 " + point + "의 피해!");
						break;
					case Enemy.MAGIC_CREATURE_SKILL:
						point = (int)(enemy.ATK * 3 / (1 + player.DEF * 0.01));
						player.HP = Math.max(player.HP - point, 0);
						enemy.SP = 0;
						Board.updateNotice(player.name + "에게 " + point + "의 피해!");
						break;
					case Enemy.BLACK_MAGE_SKILL:
						point = (int)(enemy.ATK * 1.5 / (1 + player.DEF * 0.01));
						player.HP = Math.max(player.HP - point, 0);
						Board.updateNotice(player.name + "에게 " + point + "의 피해!");
						break;
					}

					updateCharacterUI(pPanel, player);
					updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
					updateBattleUI();
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
				});
			}
			else {
				Board.updateNotice(enemy.name + "의 공격!");
				blinkCharacter(ePanel, 1, Color.white, () -> {
					int point = (int)(enemy.ATK / (1 + player.DEF * 0.01));
					player.HP = Math.max(player.HP - point, 0);
					Board.updateNotice(player.name + "에게 " + point + "의 피해!");

					updateCharacterUI(pPanel, player);
					updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
					updateBattleUI();
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
				});
			}
		}
		character.AP = 0;
	}
	
}
