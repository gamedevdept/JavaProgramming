package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import character.Player;

public class Rest{

	Player player;
	
	JPanel rest;
	JLabel restLabel;
	JLabel toNextLabel;
	ArrayList<String> restList;

	final int THICKNESS = 1;
	
	public Rest() {
		player = Board.player;
		
		rest = new JPanel(null);
		rest.setBackground(Color.black);
		rest.setBorder(new LineBorder(Color.white, 1, false));
		rest.setBounds(0, 0, 784, 350);
		
		ImageIcon icon = new ImageIcon();
		
		restLabel = new JLabel(icon, JLabel.CENTER);
		restLabel.setBorder(new LineBorder(Color.white, THICKNESS+2, false));
		restLabel.setBounds(250, 20, 300, 200);

		toNextLabel = new JLabel("다음으로", JLabel.CENTER);
		toNextLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		toNextLabel.setForeground(Color.white);
		toNextLabel.setOpaque(true);
		toNextLabel.setBackground(Color.black);
		toNextLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		toNextLabel.setBounds(325, 230, 150, 40);

		restList = new ArrayList<>();
	}
	
	public void loadRest() {
		Board.clearNotice();
		
		restList.clear();
		restList.addAll(Arrays.asList(
				"휴식처를 발견했다!",
				"잠시 쉬며 체력을 회복했다...",
				"",
				"주위를 살펴보니 뭔가가 떨어져 있었다...",
				"",
				"슬슬 떠날 때가 되었다..."));
		
		Board.updateNotice(restList.get(0));
		restList.remove(0);

		toNextLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				toNextLabel.setForeground(Color.white);
				toNextLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
				
				switch(restList.size()) {
				case 5:
					if (restList.get(0) != "" ) Board.updateNotice(restList.get(0));
					restList.remove(0);
					
					player.HP = player.MHP;
					player.SP = player.MSP;
					Board.updateStat();
					Board.updateDetailUI();
					break;
				case 4:
					if (restList.get(0) != "" ) Board.updateNotice(restList.get(0));
					restList.remove(0);

					player.MHP += 4;
					player.HP += 4;
					player.MSP += 4;
					player.SP += 4;
					
					Board.updateNotice("최대 체력과 SP가 6 증가했다!");
					Board.updateStat();
					Board.updateDetailUI();
					break;
				case 3:
					if (restList.get(0) != "" ) Board.updateNotice(restList.get(0));
					restList.remove(0);
					
					break;
				case 2:
					if (restList.get(0) != "" ) Board.updateNotice(restList.get(0));
					restList.remove(0);
					
					player.getItem((int)(Math.random() * 3));
					break;
				case 1:
					if (restList.get(0) != "" ) Board.updateNotice(restList.get(0));
					restList.remove(0);
					break;
				case 0:
					Board.loadFloor();
					toNextLabel.removeMouseListener(this);
					return;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				toNextLabel.setForeground(Color.darkGray);
				toNextLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toNextLabel.setForeground(Color.white);
				toNextLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				toNextLabel.setForeground(Color.gray);
				toNextLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		rest.removeAll();
		rest.add(restLabel);
		rest.add(toNextLabel);
		rest.add(Board.notice1Label);
		rest.add(Board.notice2Label);
		rest.add(Board.notice3Label);
		
		Board.floorPanel.removeAll();
		Board.floorPanel.add(rest);
		rest.revalidate();
		rest.repaint();
	}
	
}
