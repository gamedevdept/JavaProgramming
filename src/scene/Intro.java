package scene;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Intro extends JPanel{

	ArrayList<String> textList;
	JLabel introLabel;
	JLabel introText;
	
	final int THICKNESS = 1;
	
	public Intro() {
		setLayout(null);
		setBackground(Color.black);
		
		textList = new ArrayList<>(Arrays.asList(new String[]{
			"<html>나는 던전을 탐험하다가 이상한 건물을 발견했다.<br>"
			+ "<br>아마 고대의 건축물인 듯 했다.<br>"
			+ "<br>건물 안에는 이상한 비석이 있었다.",
						
			"<html>이 곳에 온 것을 환영하네, 낯선 이여.<br>"
			+ "<br>여기는 던전이라네. 그대를 시험하기 위해 있지.<br>"
			+ "<br>시련을 모두 이겨낸 이에게는 크나큰 영광이 있을 것이네.",
						
			"<html>글을 전부 읽고 나자, 던전의 불이 켜졌다.<br>"
			+ "<br>던전 안에는 수많은 방이 있었다.<br>"
			+ "<br>방을 하나씩 탐험하면서, 던전을 클리어하자."
		}));
		
		ImageIcon icon = new ImageIcon("images/오프닝그림.png");
	    Image image = icon.getImage();
	    double ratio = 0.6;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
		
		introLabel = new JLabel(icon, JLabel.CENTER);
		introLabel.setFont(new Font("맑은 고딕", Font.BOLD, 32));
		introLabel.setForeground(Color.white);
		introLabel.setBorder(new LineBorder(Color.white, 3, false));
		introLabel.setBounds(190, 50, 400, 300);
		add(introLabel);
		
		introText = new JLabel(textList.get(0));
		introText.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		introText.setForeground(Color.white);
		introText.setBounds(200, 380, 500, 150);
		add(introText);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(textList.size() == 1) {
					new Board();
				}
				else {
					textList.remove(0);
					introText.setText(textList.get(0));
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

}
