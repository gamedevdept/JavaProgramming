package scene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.ArrayList;

import character.*;
import character.Player.*;

public class Board{

	public static JPanel board;
	public static JPanel floorPanel;
	public static JPanel actionPanel;
	public static JPanel detailPanel;
	
	public static JPanel statPanel;
	public static JPanel skillPanel;
	public static JPanel itemPanel;
	
	public static JLabel notice1Label;
	public static JLabel notice2Label;
	public static JLabel notice3Label;
	public static Timer noticeTimer;
	
	public static Player player;
	public static Battle battle;
	public static Elite elite;
	public static Rest rest;
	public static Soul soul;
	public static Boss boss;
	
	public static ArrayList<TileLabel> tileLabelList;
	public static ArrayList<ActionLabel> actionLabelList;
	public static ArrayList<Stat> statList;
	public static ArrayList<Skill> skillList;
	public static ArrayList<Item> itemList;
	public static ArrayList<StatLabel> statLabelList;
	public static ArrayList<SkillLabel> skillLabelList;
	public static ArrayList<ItemLabel> itemLabelList;
	
	static JLabel currentFloorLabel;
	static int currentFloor;

	final static String BATTLE = "!";
	final static String ELITE = "!!";
	final static String REST = "@";
	final static String SOUL = "#";
	final static String BOSS = "!!!";

	final static String STAT = "스탯";
	final static String SKILL = "스킬";
	final static String ITEM = "아이템";
	
	final static int THICKNESS = 1;
	
	public Board() {
		player = new Player();
		battle = new Battle();
		elite = new Elite();
		rest = new Rest();
		soul = new Soul();
		boss = new Boss();
		
		board = new JPanel(null);
		floorPanel = new JPanel(null);
		actionPanel = new JPanel(null);
		detailPanel = new JPanel(null);
		
		statPanel = new JPanel(null);
		skillPanel = new JPanel(null);
		itemPanel = new JPanel(null);
		
		notice1Label = new JLabel();
		notice2Label = new JLabel();
		notice3Label = new JLabel();

		tileLabelList = new ArrayList<>();
		actionLabelList = new ArrayList<>();
		statList = new ArrayList<>();
		skillList = new ArrayList<>();
		itemList = new ArrayList<>();
		statLabelList = new ArrayList<>();
		skillLabelList = new ArrayList<>();
		itemLabelList = new ArrayList<>();
		
		currentFloorLabel = new JLabel();
		
		floorPanel.setBackground(Color.black);
		floorPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		floorPanel.setBounds(0, 0, 784, 350);
		board.add(floorPanel);
		
		actionPanel.setBackground(Color.black);
		actionPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		actionPanel.setBounds(0, 350, 150, 211);
		board.add(actionPanel);
		
		detailPanel.setBackground(Color.black);
		detailPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		detailPanel.setBounds(150, 350, 634, 211);
		board.add(detailPanel);

		statPanel.setBackground(Color.black);
		statPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		statPanel.setBounds(0, 0, 634, 211);
		
		skillPanel.setBackground(Color.black);
		skillPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		skillPanel.setBounds(0, 0, 634, 211);
		
		itemPanel.setBackground(Color.black);
		itemPanel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		itemPanel.setBounds(0, 0, 634, 211);

		notice1Label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		notice1Label.setForeground(Color.white);
		notice1Label.setBounds(5, 320, 780, 30);
		notice2Label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		notice2Label.setForeground(Color.gray);
		notice2Label.setBounds(5, 300, 780, 30);
		notice3Label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		notice3Label.setForeground(Color.darkGray);
		notice3Label.setBounds(5, 280, 780, 30);
		
		currentFloorLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		currentFloorLabel.setForeground(Color.white);
		currentFloorLabel.setBounds(15, 10, 300, 30);
		
		currentFloor = 1;

		updateStat();
		updateSkill();
		updateItem();
		generateFloor(currentFloor);
		loadAction();
		loadDetail(statPanel);
		
		SceneManager.loadScene(board);
	}
	
	public static void generateFloor(int floorNum) {
		int tileRate;
		
		currentFloor = floorNum;
		currentFloorLabel.setText(currentFloor + "층");
		
		tileLabelList.clear();
		for(int i=0; i<8; i++) {
			if(i == 0) {
				tileLabelList.add(new TileLabel(BATTLE, floorNum));
				tileLabelList.get(i).setLocation(212, 125);
			}
			else if(i == 1) {
				tileLabelList.add(new TileLabel(SOUL, floorNum));
				tileLabelList.get(i).setLocation(302+(i/4)*90, 125+(i%4+i/4-2)*90);
			}
			else if(i == 3) {
				tileLabelList.add(new TileLabel(REST, floorNum));
				tileLabelList.get(i).setLocation(302+(i/4)*90, 125+(i%4+i/4-2)*90);
			}
			else if(i == 6) {
				tileLabelList.add(new TileLabel(ELITE, floorNum));
				tileLabelList.get(i).setLocation(302+(i/4)*90, 125+(i%4+i/4-2)*90);
			}
			else if(i == 7) {
				tileLabelList.add(new TileLabel(BOSS, floorNum));
				tileLabelList.get(i).setLocation(482, 125);
			}
			else {
				tileRate = (int)(Math.random() * 100);
				if(tileRate < 50) {
					tileRate = (int)(Math.random() * 100);
					if(tileRate < (floorNum - 1) * 50) {
						tileLabelList.add(new TileLabel(ELITE, floorNum));
						tileLabelList.get(i).setLocation(302+(i/4)*90, 125+(i%4+i/4-2)*90);
					}
					else {
						tileLabelList.add(new TileLabel(BATTLE, floorNum));
						tileLabelList.get(i).setLocation(302+(i/4)*90, 125+(i%4+i/4-2)*90);
					}
				}
				else {
					tileLabelList.add(new TileLabel(REST, floorNum));
					tileLabelList.get(i).setLocation(302+(i/4)*90, 125+(i%4+i/4-2)*90);
				}
			}
		}
		
		loadFloor();
	}
	
	public static void loadFloor() {
		clearNotice();
		
		floorPanel.removeAll();
		for(JLabel tile : tileLabelList) {
			floorPanel.add(tile);
		}
		floorPanel.add(currentFloorLabel);
		floorPanel.add(notice1Label);
		floorPanel.add(notice2Label);
		floorPanel.add(notice3Label);
		
		for(ActionLabel label : actionLabelList) {
			label.isSelected = false;
			label.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
		}
		loadDetail(statPanel);

		updateFloorUI();
	}
	
	public static void loadAction() {
		actionLabelList.clear();

		actionLabelList.add(new ActionLabel(SKILL, 0));
		actionLabelList.add(new ActionLabel(ITEM, 1));
		actionLabelList.add(new ActionLabel(STAT, 2));
		
		actionPanel.removeAll();
		for(JLabel action : actionLabelList) {
			actionPanel.add(action);
		}
		
		updateActionUI();
	}

	public static void loadDetail(JPanel panel) {
		detailPanel.removeAll();
		detailPanel.add(panel);
		
		updateDetailUI();
	}
	
	public static void updateStat() {
		statList.clear();
		statLabelList.clear();
		statPanel.removeAll();
		
		for(int i=0; i<player.statList.size(); i++) {
			statList.add(new Stat(player.statList.get(i), player));
			statLabelList.add(new StatLabel(statList.get(i), i));
			
			statPanel.add(statLabelList.get(i).nameLabel);
			statPanel.add(statLabelList.get(i).pointLabel);
			statPanel.add(statLabelList.get(i).descriptionLabel);
		}
	}
	
	public static void updateSkill() {
		skillList.clear();
		skillLabelList.clear();
		skillPanel.removeAll();
		
		for(int i=0; i<player.skillList.size(); i++) {
			skillList.add(new Skill(player.skillList.get(i), player));
			skillLabelList.add(new SkillLabel(skillList.get(i), i));

			skillPanel.add(skillLabelList.get(i).nameLabel);
			skillPanel.add(skillLabelList.get(i).costLabel);
			skillPanel.add(skillLabelList.get(i).descriptionLabel);
		}
	}
	
	public static void updateItem() {
		itemList.clear();
		itemLabelList.clear();
		itemPanel.removeAll();
		
		for(int i=0; i<player.itemList.size(); i++) {
			itemList.add(new Item(player.itemList.get(i), player));
			itemLabelList.add(new ItemLabel(itemList.get(i), i));
			
			itemPanel.add(itemLabelList.get(i).nameLabel);
			itemPanel.add(itemLabelList.get(i).descriptionLabel);
		}
	}
	
	public static void updateNotice(String notice) {
		if(notice2Label.getText() != null) {
			notice3Label.setText(notice2Label.getText());
		}
		if(notice1Label.getText() != null) {
			notice2Label.setText(notice1Label.getText());
		}
		notice1Label.setText(notice);
		
		if(noticeTimer != null && noticeTimer.isRunning()) {
			noticeTimer.stop();
		}
		
		noticeTimer = new Timer(8000, e -> {
			clearNotice();
		});
		noticeTimer.start();
	}
	
	public static void clearNotice() {
		notice1Label.setText(null);
		notice2Label.setText(null);
		notice3Label.setText(null);
	}
	
	public static void updateFloorUI() {
		floorPanel.revalidate();
		floorPanel.repaint();
	}
	
	public static void updateActionUI() {
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	public static void updateDetailUI() {
		detailPanel.getComponent(0).revalidate();
		detailPanel.getComponent(0).repaint();
	}
	
	public static class TileLabel extends JLabel{
		
		final Color RED = new Color(255, 0, 0);
		final Color enteredRED = new Color(135, 0, 0);
		final Color clickedRED = new Color(75, 0, 0);
		final Color GREEN = new Color(0, 255, 0);
		final Color enteredGREEN = new Color(0, 135, 0);
		final Color clickedGREEN = new Color(0, 75, 0);
		final Color BLUE = new Color(0, 0, 255);
		final Color enteredBLUE = new Color(0, 0, 135);
		final Color clickedBLUE = new Color(0, 0, 75);
		final int THICKNESS = 2;
		
		
		public TileLabel(String event, int floorNum) {
			setText(event);
			setHorizontalAlignment(CENTER);
			setFont(new Font("맑은 고딕", Font.BOLD, 40));
			setSize(90, 90);
			
			switch (event) {
			case BATTLE:
				setForeground(RED);
				setBorder(new LineBorder(RED, THICKNESS, false));
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						battle.loadBattle(floorNum);
						removeMouseListener(this);
						setForeground(Color.darkGray);
						setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						setForeground(clickedRED);
						setBorder(new LineBorder(clickedRED, THICKNESS, false));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						setForeground(RED);
						setBorder(new LineBorder(RED, THICKNESS, false));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						setForeground(enteredRED);
						setBorder(new LineBorder(enteredRED, THICKNESS, false));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				break;
			case ELITE:
				setForeground(RED);
				setBorder(new LineBorder(RED, THICKNESS, false));
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						elite.loadBattle(floorNum);
						removeMouseListener(this);
						setForeground(Color.darkGray);
						setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						setForeground(clickedRED);
						setBorder(new LineBorder(clickedRED, THICKNESS, false));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						setForeground(RED);
						setBorder(new LineBorder(RED, THICKNESS, false));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						setForeground(enteredRED);
						setBorder(new LineBorder(enteredRED, THICKNESS, false));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				break;
			case REST:
				setForeground(GREEN);
				setBorder(new LineBorder(GREEN, THICKNESS, false));
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						rest.loadRest();
						removeMouseListener(this);
						setForeground(Color.darkGray);
						setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						setForeground(clickedGREEN);
						setBorder(new LineBorder(clickedGREEN, THICKNESS, false));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						setForeground(GREEN);
						setBorder(new LineBorder(GREEN, THICKNESS, false));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						setForeground(enteredGREEN);
						setBorder(new LineBorder(enteredGREEN, THICKNESS, false));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				break;
			case SOUL:
				setForeground(BLUE);
				setBorder(new LineBorder(BLUE, THICKNESS, false));
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						soul.loadSoul();
						removeMouseListener(this);
						setForeground(Color.darkGray);
						setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						setForeground(clickedBLUE);
						setBorder(new LineBorder(clickedBLUE, THICKNESS, false));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						setForeground(BLUE);
						setBorder(new LineBorder(BLUE, THICKNESS, false));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						setForeground(enteredBLUE);
						setBorder(new LineBorder(enteredBLUE, THICKNESS, false));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				break;
			case BOSS: 
				setForeground(RED);
				setBorder(new LineBorder(RED, THICKNESS, false));
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						boss.loadBattle(floorNum);
						removeMouseListener(this);
						setForeground(Color.darkGray);
						setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						setForeground(clickedRED);
						setBorder(new LineBorder(clickedRED, THICKNESS, false));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						setForeground(RED);
						setBorder(new LineBorder(RED, THICKNESS, false));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						setForeground(enteredRED);
						setBorder(new LineBorder(enteredRED, THICKNESS, false));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				break;
			}
		}
		
	}
	
	public static class ActionLabel extends JLabel{
		
		final int THICKNESS = 1;
		
		boolean isSelected;
		
		public ActionLabel(String type, int i) {
			setText(" " + type);
			setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			setForeground(Color.white);
			setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
			setLocation(25, 15+i*60);
			setSize(100, 50);
			
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if(!isSelected) {
						for(ActionLabel label : Board.actionLabelList) {
							label.isSelected = false;
							label.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						}
						isSelected = true;
						setBorder(new LineBorder(Color.white, THICKNESS, false));
						switch (type) {
						case SKILL:
							loadDetail(skillPanel);
							break;
						case ITEM:
							loadDetail(itemPanel);
							break;
						case STAT:
							loadDetail(statPanel);
							break;
						}
					}
					else {
						isSelected = false;
						setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
						loadDetail(Board.statPanel);
					}
					setForeground(Color.white);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					setForeground(Color.darkGray);
					setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					setForeground(Color.white);
					if(!isSelected) {
						setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
					}
					else {
						setBorder(new LineBorder(Color.white, THICKNESS, false));
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					setForeground(Color.gray);
					setBorder(new LineBorder(Color.gray, THICKNESS, false));
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
//					setForeground(Color.white);
				}
			});
		}
		
	}
	
	public static class StatLabel{

		JLabel nameLabel;
		JLabel pointLabel;
		JLabel descriptionLabel;
		
		final int THICKNESS = 1;
		
		public StatLabel(Stat stat, int i) {
			nameLabel = new JLabel(" " + stat.name);
			nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			nameLabel.setForeground(Color.white);
			nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			nameLabel.setLocation(25+(i/2)*150, 20+(i%2)*60);
			nameLabel.setSize(130, 40);

			pointLabel = new JLabel(stat.point + " ", JLabel.RIGHT);
			pointLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			pointLabel.setForeground(Color.white);
			pointLabel.setLocation(nameLabel.getLocation());
			pointLabel.setSize(nameLabel.getSize());
			
			descriptionLabel = new JLabel(stat.description);
			descriptionLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			descriptionLabel.setForeground(Color.white);
			descriptionLabel.setLocation(25, 160);
			descriptionLabel.setSize(634, 40);
			descriptionLabel.setVisible(false);
			
			nameLabel.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					descriptionLabel.setVisible(false);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					nameLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
					descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
		
	}
	
	public static class SkillLabel{

		String name;
		int cost;
		String target;
		ArrayList<Double> points;
		
		JLabel nameLabel;
		JLabel costLabel;
		JLabel descriptionLabel;
		MouseListener outBattleListener;
		MouseListener inBattleListener;
		
		final int THICKNESS = 1;
		
		boolean isSelected;
		
		public SkillLabel(Skill skill, int i) {
			name = skill.name;
			cost = skill.cost;
			target = skill.target;	
			points = skill.points;
			
			nameLabel = new JLabel(" " + name);
			nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			nameLabel.setForeground(Color.gray);
			nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
			nameLabel.setLocation(25+i*150, 20);
			nameLabel.setSize(130, 40);

			costLabel = new JLabel("SP 소모: " + cost);
			costLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			costLabel.setForeground(Color.white);
			costLabel.setLocation(25, 80);
			costLabel.setSize(130, 40);
			costLabel.setVisible(false);
			
			descriptionLabel = new JLabel(skill.description);
			descriptionLabel.setHorizontalAlignment(JLabel.LEFT);
			descriptionLabel.setVerticalAlignment(JLabel.TOP);
			descriptionLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			descriptionLabel.setForeground(Color.white);
			descriptionLabel.setLocation(25, 130);
			descriptionLabel.setSize(584, 70);
			descriptionLabel.setVisible(false);
			
			outBattleListener = new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					Board.updateNotice("전투 중에만 사용할 수 있습니다.");
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
					costLabel.setVisible(false);
					descriptionLabel.setVisible(false);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					costLabel.setVisible(true);
					descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			};
			inBattleListener = new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					Board.updateNotice("내 턴에만 사용할 수 있습니다.");
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
					costLabel.setVisible(false);
					descriptionLabel.setVisible(false);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					costLabel.setVisible(true);
					descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			};
			nameLabel.addMouseListener(outBattleListener);
		}
		
	}
	
	public static class ItemLabel{

		String name;
		String target;
		ArrayList<Double> points;
		
		JLabel nameLabel;
		JLabel descriptionLabel;
		MouseListener outBattleListener;
		MouseListener inBattleListener;
		
		final int THICKNESS = 1;
		
		boolean isSelected;
		
		public ItemLabel(Item item, int i) {
			name = item.name;
			target = item.target;
			points = item.points;
			
			nameLabel = new JLabel(" " + name);
			nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			nameLabel.setForeground(Color.gray);
			nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
			nameLabel.setLocation(25+(i%4)*150, 20+(i/4)*60);
			nameLabel.setSize(130, 40);
			
			descriptionLabel = new JLabel(item.description);
			descriptionLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			descriptionLabel.setForeground(Color.white);
			descriptionLabel.setLocation(25, 160);
			descriptionLabel.setSize(634, 40);
			descriptionLabel.setVisible(false);
			
			outBattleListener = new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					Board.updateNotice("전투 중에만 사용할 수 있습니다.");
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
					descriptionLabel.setVisible(false);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			};
			inBattleListener = new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					Board.updateNotice("내 턴에만 사용할 수 있습니다.");
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					nameLabel.setBorder(new EmptyBorder(THICKNESS, THICKNESS, THICKNESS, THICKNESS));
					descriptionLabel.setVisible(false);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					nameLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
					descriptionLabel.setVisible(true);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			};
			nameLabel.addMouseListener(outBattleListener);
		}
		
	}
	
}











//784, 561