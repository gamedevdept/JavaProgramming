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

public class Boss extends Battle{
	
	JLabel toNextFloorLabel;
	
	public Boss() {
		enemyPanel.setBounds(210, 25, 550, 250);
		
		toNextFloorLabel = new JLabel("다음 층으로", JLabel.CENTER);
		toNextFloorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		toNextFloorLabel.setForeground(Color.white);
		toNextFloorLabel.setOpaque(true);
		toNextFloorLabel.setBackground(Color.black);
		toNextFloorLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		toNextFloorLabel.setBounds(325, 230, 150, 40);
	}
	
	public JPanel createEnemyPanel(Character character) {
		JPanel panel = new JPanel(null);
	    panel.setBackground(Color.black);
	    panel.setBounds(0, 0, 300, 250);

	    ImageIcon icon = new ImageIcon("images/" + character.name + ".png");
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
	    MHPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MHP * 2), 299), BAR_THICKNESS);
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
	    MSPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MSP * 2), 299), BAR_THICKNESS);
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
	    MAPLabel.setBounds(0, 0, Math.min(BAR_MARGIN + (int)(character.MAP * 2), 299), BAR_THICKNESS);
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
		enemyPanel.add(createEnemyPanel(characterList.get(1)));
		enemyPanel.getComponent(0).setLocation(250, 0);

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

		if(Board.currentFloor < 3) {
			resetToOriginStat(player);
			player.levelUp(3);
			updateCharacterUI((JPanel)playerPanel.getComponent(0), player);
			updateBattleUI();
			player.getItem(player.LEGEND);
		}
		else {
			toNextFloorLabel.setText("다음으로");
		}
		battle.add(toNextFloorLabel);
		battle.setComponentZOrder(toNextFloorLabel, 0);
		toNextFloorLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(Board.currentFloor >= 3) {
					SceneManager.loadScene(new Ending());
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
		battleTimer.stop();
	}
	
	public void takeEnemyTurn(JPanel pPanel, JPanel ePanel, Enemy enemy) {
		updateCharacterUI(ePanel, characterList.get(characterList.indexOf(enemy)));
		if(enemy.SP >= enemy.MSP) {
			Board.updateNotice(enemy.name + "의 " + enemy.skill + "!");
			blinkCharacter(ePanel, 3, Color.white, () -> {
				switch(enemy.skill) {
				case Enemy.WARRIOR_SKULL_SKILL:
					enemy.SP = 0;
					takeDamage(player, enemy.ATK * 2);
					
					blinkCharacter(pPanel, 3, null, () -> {
						isPaused = false;
					});
					break;
				case Enemy.UNDEAD_CHIEF_SKILL:
					enemy.SP = 0;
					if(enemyPanel.getComponentCount() <= 1) {
						int enemyRate = (int)(Math.random()*Enemy.allEliteList.get(1).size());
						characterList.add(new Enemy(Enemy.allEliteList.get(1).get(enemyRate)));
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
						enemyPanel.add(Board.elite.createEnemyPanel(characterList.get(2)));
						enemyPanel.getComponent(1).setLocation(0, 0);
						if((characterList.get(2).name.charAt(characterList.get(2).name.length() - 1) - 0xAC00) % 28 > 0) {
							Board.updateNotice(characterList.get(2).name + "이 나타났다!");
						}
						else {
							Board.updateNotice(characterList.get(2).name + "가 나타났다!");
						}
					}
					else {
						characterList.get(2).SP = characterList.get(2).MSP;
						characterList.get(2).AP = characterList.get(2).MAP;
						updateCharacterUI((JPanel)enemyPanel.getComponent(1), characterList.get(2));
						updateBattleUI();
					}

					isPaused = false;
					break;
				case Enemy.BLACK_MAGE_SKILL:
					enemy.SP = 0;
					player.effectList.add(new Effect("MHP", 99, -(int)(player.originStatPointList.get(0) * 0.20)));
					player.HP = Math.min(player.HP, player.MHP - (int)(player.originStatPointList.get(0) * 0.20));
					player.effectList.add(new Effect("MSP", 99, -(int)(player.originStatPointList.get(1) * 0.20)));
					player.SP = Math.min(player.SP, player.MSP - (int)(player.originStatPointList.get(1) * 0.20));
					player.effectList.add(new Effect("HPG", 99, -(int)(player.originStatPointList.get(2) * 0.20)));
					player.effectList.add(new Effect("SPG", 99, -(int)(player.originStatPointList.get(3) * 0.20)));
					player.effectList.add(new Effect("ATK", 99, -(int)(player.originStatPointList.get(4) * 0.20)));
					player.effectList.add(new Effect("DEF", 99, -(int)(player.originStatPointList.get(5) * 0.20)));
					player.effectList.add(new Effect("SPD", 99, -(int)(player.originStatPointList.get(6) * 0.20)));
					player.effectList.add(new Effect("EFC", 99, -(int)(player.originStatPointList.get(7) * 0.20)));
					Board.updateNotice("저주를 받아 모든 능력치가 20% 감소한다!");

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
				}

				applyEffect(player);
				updateEffect(enemy);
				updateCharacterUI(pPanel, player);
				updateCharacterUI(ePanel, enemy);
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
