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
import javax.swing.border.LineBorder;

import character.Player;

public class Soul{

	Player player;
	
	JPanel soul;
	JLabel soulLabel;
	JLabel toNextLabel;
	ArrayList<String> soulList;

	final int THICKNESS = 1;
	
	public Soul() {
		player = Board.player;
		
		soul = new JPanel(null);
		soul.setBackground(Color.black);
		soul.setBorder(new LineBorder(Color.white, 1, false));
		soul.setBounds(0, 0, 784, 350);
		
		ImageIcon icon = new ImageIcon("images/영혼.png");
	    Image image = icon.getImage();
	    double ratio = 0.85;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
		
		soulLabel = new JLabel(icon, JLabel.CENTER);
		soulLabel.setBorder(new LineBorder(Color.white, THICKNESS+2, false));
		soulLabel.setBounds(250, 20, 300, 200);

		toNextLabel = new JLabel("다음으로", JLabel.CENTER);
		toNextLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		toNextLabel.setForeground(Color.white);
		toNextLabel.setOpaque(true);
		toNextLabel.setBackground(Color.black);
		toNextLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		toNextLabel.setBounds(325, 230, 150, 40);

		soulList = new ArrayList<>();
	}
	
	public void loadSoul() {
		Board.clearNotice();
		
		soulList.clear();
		soulList.addAll(Arrays.asList(
				"고대의 영혼과 마주쳤다!",
				"\"힘을 원하는가...\"",
				"\"나의 힘을 주겠다...\"",
				"",
				"\"가서 그를 물리쳐라...\"",
				"영혼이 사라졌다..."));
		
		Board.updateNotice(soulList.get(0));
		soulList.remove(0);
		
		toNextLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				toNextLabel.setForeground(Color.white);
				toNextLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
				
				switch(soulList.size()) {
				case 5:
					if (soulList.get(0) != "" ) Board.updateNotice(soulList.get(0));
					soulList.remove(0);
					break;
				case 4:
					if (soulList.get(0) != "" ) Board.updateNotice(soulList.get(0));
					soulList.remove(0);
					break;
				case 3:
					if (soulList.get(0) != "" ) Board.updateNotice(soulList.get(0));
					soulList.remove(0);
					
					player.levelUp(2);
					player.getSkill();
					break;
				case 2:
					if (soulList.get(0) != "" ) Board.updateNotice(soulList.get(0));
					soulList.remove(0);
					break;
				case 1:
					if (soulList.get(0) != "" ) Board.updateNotice(soulList.get(0));
					soulList.remove(0);
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

		soul.removeAll();
		soul.add(soulLabel);
		soul.add(toNextLabel);
		soul.add(Board.notice1Label);
		soul.add(Board.notice2Label);
		soul.add(Board.notice3Label);
		
		Board.floorPanel.removeAll();
		Board.floorPanel.add(soul);
		soul.revalidate();
		soul.repaint();
	}
	
}
