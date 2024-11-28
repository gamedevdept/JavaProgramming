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
	
	private JPanel createCharacterPanel(Character character) {
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
	    MSPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + character.MSP * 2, 180), BAR_THICKNESS);
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
	    
		character.HP = Math.min(character.MHP, character.HP);
	    MHPLabel.setText("HP " + character.HP + "/" + character.MHP + " ");
	    MHPLabel.setSize(Math.min(BAR_MARGIN + character.MHP, 179), BAR_THICKNESS);
	    HPLabel.setSize((character.MHP <= 0) ? 0 : (int)(1.0 * character.HP / character.MHP * (MHPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);
	    
	    character.SP = Math.min(character.MSP, character.SP);
	    MSPLabel.setText("SP " + character.SP + "/" + character.MSP + " ");
	    MSPLabel.setSize(Math.min(BAR_MARGIN + character.MSP * 2, 179), BAR_THICKNESS);
	    SPLabel.setSize((character.MSP <= 0) ? 0 : (int)(1.0 * character.SP / character.MSP * (MSPLabel.getWidth() - BAR_MARGIN)), BAR_THICKNESS);

		character.MAP = 100 - character.EFC;
	    MAPLabel.setText("AP " + character.AP + "/" + character.MAP + " ");
	    MAPLabel.setSize(Math.min(BAR_MARGIN + character.MAP, 179), BAR_THICKNESS);
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
			enemyPanel.add(createCharacterPanel(characterList.get(i+1)));
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
		character.MAP = 100 - character.EFC;
	}
	
	public void applyEffect(Character character) {
		resetToOriginStat(character);
		
		if(!character.effectList.isEmpty()) {
			for(int i=0; i<character.effectList.size(); i++) {
				Effect effect = character.effectList.get(i);
				
				if(effect.duration <= 0) {
					character.effectList.remove(effect);
					continue;
				}
				switch(effect.statName) {
				case "MHP":
					character.MHP = character.originStatPointList.get(0) + effect.point;
					character.HP = Math.min(character.HP, character.MHP);
					break;
				case "MSP":
					character.MSP = character.originStatPointList.get(1) + effect.point;
					character.SP = Math.min(character.SP, character. MSP);
					break;
				case "HPG":
					character.HPG = character.originStatPointList.get(2) + effect.point;
					break;
				case "SPG":
					character.SPG = character.originStatPointList.get(3) + effect.point;
					break;
				case "ATK":
					character.ATK = character.originStatPointList.get(4) + effect.point;
					break;
				case "DEF":
					character.DEF = character.originStatPointList.get(5) + effect.point;
					break;
				case "SPD":
					character.SPD = character.originStatPointList.get(6) + effect.point;
					break;
				case "EFC":
					character.EFC = character.originStatPointList.get(7) + effect.point;
					character.MAP = 100 - character.EFC;
					break;
				}
			}
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
						characterList.remove(i);
						enemyPanel.remove(i-1);
						updateBattleUI();
						if(enemyPanel.getComponentCount() <= 0) {
							Board.updateNotice("전투에서 승리했다!");
							
							resetToOriginStat(player);
							player.levelUp(1);
							updateCharacterUI((JPanel)playerPanel.getComponent(0), player);
							updateBattleUI();
							switch(Board.currentFloor) {
							case 1:
								player.getItem(player.COMMON);
								break;
							case 2:
								player.getItem(player.RARE);
								break;
							case 3:
								player.getItem(player.EPIC);
								break;
							}
							
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
							return;
						}
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
		Board.updateNotice(character.name + "의 턴!");

		for(Effect effect : character.effectList) {
			effect.duration--;
		}
		
		character.HP = Math.min(character.HP + character.HPG, character.MHP);
		character.SP = Math.min(character.SP + character.SPG, character.MSP);
		
		if(character instanceof Player) {
			JPanel pPanel = (JPanel)playerPanel.getComponent(0);
			updateCharacterUI(pPanel, character);
			
			blinkCharacter(pPanel, 1, Color.white, null);
			activateSkillLabel();
			activateItemLabel();
		}
		if(character instanceof Enemy) {
			JPanel pPanel = (JPanel)playerPanel.getComponent(0);
			JPanel ePanel = (JPanel)enemyPanel.getComponent(characterList.indexOf(character) - 1);
			updateCharacterUI(ePanel, characterList.get(characterList.indexOf(character)));
			
			blinkCharacter(ePanel, 1, Color.white, () -> {
				int damage = (int)(character.ATK / (1.0 + player.DEF * 0.01));
				player.HP = Math.max(player.HP - damage, 0);
				Board.updateNotice(player.name + "에게 " + damage + "의 피해!");
				
				updateCharacterUI(pPanel, player);
				updateCharacterUI(ePanel, characterList.get(characterList.indexOf(character)));
				updateBattleUI();
				
				blinkCharacter(pPanel, 3, null, () -> {
					isPaused = false;
				});
			});
		}

		applyEffect(character);
		
		character.AP = 0;
	}

	public void activateSkillLabel() {
		for(SkillLabel label : Board.skillLabelList) {
			label.nameLabel.setForeground(Color.white);
			label.nameLabel.removeMouseListener(label.inBattleListener);
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
					for(ItemLabel iLabel : Board.itemLabelList) {
						iLabel.isSelected = false;
						iLabel.nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						iLabel.descriptionLabel.setVisible(false);
					}
					
					if(player.SP < label.cost) {
						Board.updateNotice("SP가 부족합니다.");
						label.nameLabel.setForeground(Color.gray);
						label.nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
						return;
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
									switch(label.name) {
									case Skill.HEALING:
										player.SP = Math.max(player.SP - label.cost, 0);
										player.HP = Math.min(player.HP + label.points.get(0), player.MHP);
										player.AP = Math.min(player.AP + label.points.get(1), player.MAP);
										Board.updateNotice(player.name + "에게 " + label.points.get(0) + "의 회복!");
										break;
									}
									
									applyEffect(player);
									updateCharacterUI(pPanel, player);
									updateBattleUI();
									
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
										int point;
										Character enemy = characterList.get(index+1);
										switch(label.name) {
										case Skill.ATTACK:
											player.SP = Math.max(player.SP - label.cost, 0);
											point = (int)(label.points.get(0) / (1 + enemy.DEF * 0.01));
											enemy.HP = Math.max(enemy.HP - point, 0);
											Board.updateNotice(enemy.name + "에게 " + point + "의 피해!");
											break;
										case Skill.CHARGE_ATTACK:
											player.SP = Math.max(player.SP - label.cost, 0);
											point = (int)(label.points.get(0) / (1 + enemy.DEF * 0.01));
											enemy.HP = Math.max(enemy.HP - point, 0);
											enemy.AP = Math.max(enemy.AP - label.points.get(1), 0);
											Board.updateNotice(enemy.name + "에게 " + point + "의 피해!");
											break;
										case Skill.FAST_ATTACK:
											player.SP = Math.max(player.SP - label.cost, 0);
											point = (int)(label.points.get(0) / (1 + enemy.DEF * 0.01));
											enemy.HP = Math.max(enemy.HP - point, 0);
											player.effectList.add(new Effect("EFC", label.points.get(1), label.points.get(2)));
											Board.updateNotice(enemy.name + "에게 " + point + "의 피해!");
											break;
										}
										
										applyEffect(player);
										applyEffect(enemy);
										updateCharacterUI(pPanel, player);
										updateCharacterUI(ePanel, enemy);
										updateBattleUI();
										Board.updateStat();
										Board.updateDetailUI();
										
										blinkCharacter(ePanel, 3, null, () -> {
											isPaused = false;
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
		for(ItemLabel label : Board.itemLabelList) {
			label.nameLabel.setForeground(Color.white);
			label.nameLabel.removeMouseListener(label.inBattleListener);
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
									switch(label.nameLabel.getText().strip()) {
									case Item.POTION:
										player.HP = Math.min(player.HP + label.points.get(0), player.MHP);
										Board.updateNotice(player.name + "에게 " + label.points.get(0) + "의 회복!");
										break;
									case Item.HIGH_POTION:
										player.HP = Math.min(player.HP + label.points.get(0), player.MHP);
										Board.updateNotice(player.name + "에게 " + label.points.get(0) + "의 회복!");
										break;
									case Item.MEGA_POTION:
										player.HP = Math.min(player.HP + label.points.get(0), player.MHP);
										Board.updateNotice(player.name + "에게 " + label.points.get(0) + "의 회복!");
										break;
									case Item.SOUL_ESSENCE:
										player.HP = player.MHP;
										Board.updateNotice(player.name + "에게 " + label.points.get(0) + "의 회복!");
										break;
									}
									
									player.itemList.remove(Board.itemLabelList.indexOf(label));
									Board.updateItem();
									Board.loadDetail(Board.itemPanel);
									Board.updateDetailUI();
									
									updateCharacterUI(pPanel, player);
									updateBattleUI();
									
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
										int point;
										Character enemy = characterList.get(index+1);
										switch(label.nameLabel.getText().strip()) {
										case Item.SPEAR:
											point = (int)(label.points.get(0) / (1 + enemy.DEF * 0.01));
											enemy.HP = Math.max(enemy.HP - point, 0);
											Board.updateNotice(enemy.name + "에게 " + point + "의 피해!");
											break;
										case Item.METAL_SPEAR:
											point = (int)(label.points.get(0) / (1 + enemy.DEF * 0.01));
											enemy.HP = Math.max(enemy.HP - point, 0);
											Board.updateNotice(enemy.name + "에게 " + point + "의 피해!");
											break;
										case Item.BAMBOO_SPEAR:
											point = (int)(label.points.get(0) / (1 + enemy.DEF * 0.01));
											enemy.HP = Math.max(enemy.HP - point, 0);
											Board.updateNotice(enemy.name + "에게 " + point + "의 피해!");
											break;
										}
										
										player.itemList.remove(Board.itemLabelList.indexOf(label));
										Board.updateItem();
										Board.loadDetail(Board.itemPanel);
										Board.updateDetailUI();
										
										updateCharacterUI(ePanel, enemy);
										updateBattleUI();
										
										blinkCharacter(ePanel, 3, null, () -> {
											isPaused = false;
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
