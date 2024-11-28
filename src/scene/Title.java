package scene;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Title extends JPanel{
	
	JLabel titleLabel;
	JLabel startLabel;
	JLabel manualLabel;
	JLabel manual;
	
	final int THICKNESS = 1;
	
	public Title() {
		setLayout(null);
		setBackground(Color.black);

		titleLabel = new JLabel("<html>JUNGLE<br>EXPLORER</html>", JLabel.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		titleLabel.setForeground(Color.white);
		titleLabel.setBorder(new LineBorder(Color.white, THICKNESS+2, false));
		titleLabel.setBounds(250, 100, 300, 200);
		add(titleLabel);
		
		startLabel = new JLabel("시작", JLabel.CENTER);
		startLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		startLabel.setForeground(Color.white);
		startLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		startLabel.setBounds(325, 360, 150, 40);
		add(startLabel);
		
		manualLabel = new JLabel("게임 설명", JLabel.CENTER);
		manualLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		manualLabel.setForeground(Color.white);
		manualLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
		manualLabel.setBounds(325, 440, 150, 40);
		add(manualLabel);
		
		manual = new JLabel("<html>플레이어는 탐험가가 되어 탑을 탐험하게 됩니다.<br>"
				+ "<br>탑은 3층까지 있으며 각 층엔 5개의 방과 보스방이 있습니다.<br>"
				+ "<br>각 방은 클릭하여 탐색할 수 있고 탐색 시 무작위 이벤트가 발생합니다.<br>"
				+ "<br>보스를 처치하면 다음 층으로 이동합니다.<br>"
				+ "<br>3층의 보스를 처치해 게임을 클리어하세요.</html>");
		manual.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		manual.setForeground(Color.white);
		manual.setOpaque(true);
		manual.setBackground(Color.black);
		manual.setBorder(new LineBorder(Color.white, THICKNESS, false));
		manual.setBounds(92, 140, 600, 300);
		manual.setVisible(false);
		add(manual);
		
		startLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				SceneManager.loadScene(new Intro());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				startLabel.setForeground(Color.darkGray);
				startLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				startLabel.setForeground(Color.white);
				startLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				startLabel.setForeground(Color.gray);
				startLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		manualLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				manual.setVisible(true);
				titleLabel.setVisible(false);
				startLabel.setVisible(false);
				manualLabel.setVisible(false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				manualLabel.setForeground(Color.darkGray);
				manualLabel.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				manualLabel.setForeground(Color.white);
				manualLabel.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				manualLabel.setForeground(Color.gray);
				manualLabel.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		manual.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				manual.setVisible(false);
				titleLabel.setVisible(true);
				startLabel.setVisible(true);
				manualLabel.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				manual.setForeground(Color.darkGray);
				manual.setBorder(new LineBorder(Color.darkGray, THICKNESS, false));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				manual.setForeground(Color.white);
				manual.setBorder(new LineBorder(Color.white, THICKNESS, false));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				manual.setForeground(Color.gray);
				manual.setBorder(new LineBorder(Color.gray, THICKNESS, false));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
}
